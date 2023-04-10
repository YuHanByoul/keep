package com.kbrainc.plum.front.mypage.mvmnAplyHist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.mypage.mvmnAplyHist.model.MvmnAplyHistVo;
import com.kbrainc.plum.front.mypage.mvmnAplyHist.service.MvmnAplyHistService;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 마이페이지 > 국가 환경교육 신청 이력 > 푸름이 이동환경교실
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.mvmnAplyHist
 * - MyMvmnAplyHistController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : MyMvmnAplyHistController
 * @Description : 마이페이지 > 국가 환경교육 신청 이력 > 푸름이 이동환경교실
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/mvmnAplyHist")
public class MvmnAplyHistController {
    private static final String VIEW_PATH = "/front/mypage/mvmnAplyHist";

    @Resource(name = "front.mvmnAplyHistServiceImpl")
    private MvmnAplyHistService mvmnAplyHistService;

    @GetMapping("/mvmnAplyHistListForm.html")
    public String mvmnAplyHistList(MvmnAplyHistVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/mvmnAplyHistList";
    }

    @GetMapping(value = "/selectMvmnAplyHistList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyHistList(MvmnAplyHistVo mvmnAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        mvmnAplyHistVo.setUser(user);
        List<MvmnAplyHistVo> list = mvmnAplyHistService.selectMvmnAplyHistList(mvmnAplyHistVo);
        //List<MvmnAplyVo> list = null;
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @GetMapping("/mvmnAplyHistDetail.html")
    public String mvmnAplyHistDetail(MvmnAplyHistVo mvmnAplyHistVo, Model model, @UserInfo UserVo user) throws Exception {
        MvmnAplyHistVo mvmnAplyHistInfoVo = mvmnAplyHistService.selectMvmnAplyHistInfo(mvmnAplyHistVo);
        MvmnAplyHistVo mvmnAplyHistRegVo = mvmnAplyHistService.selectMvmnAplyHistDetail(mvmnAplyHistVo);
        
        model.addAttribute("mvmnAplyHistInfoVo", mvmnAplyHistInfoVo);
        
        if(!"".equals(mvmnAplyHistRegVo.getHopeEduBgngTm())){
            String[] splitHopeEduBgngTmStr = mvmnAplyHistRegVo.getHopeEduBgngTm().split(":");
            mvmnAplyHistRegVo.setHopeEduBgngTmHour(splitHopeEduBgngTmStr[0]);
            mvmnAplyHistRegVo.setHopeEduBgngTmMin(splitHopeEduBgngTmStr[1]);
            
            String[] splitHopeEduEndTmStr = mvmnAplyHistRegVo.getHopeEduEndTm().split(":");
            mvmnAplyHistRegVo.setHopeEduEndTmHour(splitHopeEduEndTmStr[0]);
            mvmnAplyHistRegVo.setHopeEduEndTmMin(splitHopeEduEndTmStr[1]);
        }
        
        model.addAttribute("mvmnAplyHistRegVo", mvmnAplyHistRegVo);
        
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        
        if(!"".equals(StringUtil.nvl(mvmnAplyHistRegVo.getTrgtCd(), ""))){
            String[] splitTrgtCdStr = mvmnAplyHistRegVo.getTrgtCd().split(",");
            String[] splitTrgtNmStr = mvmnAplyHistRegVo.getTrgtNm().split(",");
            for(int k=0; k<splitTrgtCdStr.length; k++){
                Map<String, String> map = new HashMap<String, String>();
                String trgtChk = "";
                if(!"".equals(StringUtil.nvl(mvmnAplyHistRegVo.getTrgtCd(), ""))){
                    String[] splitEduTrgtCdStr = mvmnAplyHistRegVo.getTrgtCd().split(",");
                    for(int i=0; i<splitEduTrgtCdStr.length; i++){
                        if(splitEduTrgtCdStr[i].equals(splitTrgtCdStr[k])) {
                            trgtChk = "checked";
                        }
                    }
                }
                map.put("TRGTCD", splitTrgtCdStr[k]);
                map.put("TRGTNM", splitTrgtNmStr[k]);
                map.put("TRGTCHK", trgtChk);
                
                listMap.add(map);

            }
        }
        model.addAttribute("trgtList", listMap);        
        model.addAttribute("mvmnAplyHistVo", mvmnAplyHistVo);        
        return VIEW_PATH + "/mvmnAplyHistDetail";
    }
    
    @PostMapping("/updateSttsMvmnAply.do")
    @ResponseBody
    public Map<String, Object> updateSttsMvmnAply(MvmnAplyHistVo mvmnAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        mvmnAplyHistVo.setUser(userVo);

        if (mvmnAplyHistService.updateSttsMvmnAply(mvmnAplyHistVo) > 0) {
            success = true;
            if("180104".equals(mvmnAplyHistVo.getUpdCd())){
                response.put("msg", "취소가 완료되었습니다.");
            }else if("180105".equals(mvmnAplyHistVo.getUpdCd())){
                response.put("msg", "승인거부가 완료되었습니다.");                
            }
        } else
            if("180104".equals(mvmnAplyHistVo.getUpdCd())){
                response.put("msg", "취소가 실패하였습니다.");
            }
            if("180105".equals(mvmnAplyHistVo.getUpdCd())){
                response.put("msg", "승인거부가 실패하였습니다.");
            }
        response.put("success", success);
        return response;
    }   
    /**
    * 푸름이 이동환경교실 교육 신청 이력 설문공유. 
    *
    * @Title : mvmnAplyHistSrvySendPopup
    * @Description : 푸름이 이동환경교실 교육 신청 이력 설문공유
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mvmnAplyHistSrvySendPopup.html")
    public String mvmnAplyHistSrvySendPopup(MvmnAplyHistVo mvmnAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (mvmnAplyHistVo != null) {
            //srvyDetail = mvmnAplyHistService.detailSplmnt(mvmnAplyHistVo);
            srvyVo.setSrvyid(mvmnAplyHistVo.getStdntDgstfnSrvyid());
            model.addAttribute("mvmnPrgrmAplyid", mvmnAplyHistVo.getAplyid());
            model.addAttribute("prgrmNm", mvmnAplyHistVo.getPrgrmNm());
            model.addAttribute("srvyDetail", mvmnAplyHistService.selectMvmnSrvyInfo(srvyVo));
            
        }
        //model.addAttribute("srvyDetail", srvyDetail);
        return VIEW_PATH + "/mvmnAplyHistSrvySendPopup";
    }    

    /**
     * 푸름이 이동환경교실 교육 신청 이력 공유설문 결과. 
     *
     * @Title : mvmnAplyHistSrvyRsltPopup
     * @Description : 푸름이 이동환경교실 교육 신청 이력 공유설문 결과
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/mvmnAplyHistSrvyRsltPopup.html")
    public String mvmnAplyHistSrvyRsltPopup(MvmnAplyHistVo mvmnAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (mvmnAplyHistVo != null) {
            //srvyDetail = mvmnAplyHistService.detailSplmnt(mvmnAplyHistVo);
            srvyVo.setMvmnPrgrmAplyid(mvmnAplyHistVo.getAplyid());
            srvyVo.setSrvyid(mvmnAplyHistVo.getStdntDgstfnSrvyid());
        }
        model.addAttribute("mvmnPrgrmAplyid", mvmnAplyHistVo.getAplyid());
        model.addAttribute("srvySendList", mvmnAplyHistService.selectMvmnSrvySendList(srvyVo));
        return VIEW_PATH + "/mvmnAplyHistSrvyRsltPopup";
    }       
    
    @RequestMapping(value = "/updateMvmnAply.do")
    @ResponseBody
    public Map<String, Object> updateMvmnAply(MvmnAplyHistVo mvmnAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        mvmnAplyHistVo.setUser(user);
        retVal = mvmnAplyHistService.updateMvmnAply(mvmnAplyHistVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }        
        return resultMap;    
    }        
}
