package com.kbrainc.plum.front.mypage.infntAplyHist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
import com.kbrainc.plum.front.mypage.infntAplyHist.service.InfntAplyHistService;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 마이페이지 > 국가 환경교육 신청 이력 > 유아환경교육관
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.infntAplyHist
 * - MyInfntAplyHistController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : MyInfntAplyHistController
 * @Description : 마이페이지 > 국가 환경교육 신청 이력 > 유아환경교육관
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/infntAplyHist")
public class InfntAplyHistController {
    private static final String VIEW_PATH = "/front/mypage/infntAplyHist";

    @Autowired
    private ResCodeService resCodeService;

    @Resource(name = "front.infntAplyHistServiceImpl")
    private InfntAplyHistService infntAplyHistService;
    
    @GetMapping("/infntAplyHistListForm.html")
    public String infntAplyHistList(InfntAplyHistVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/infntAplyHistList";
    }

    @GetMapping(value = "/selectInfntAplyHistList.do")
    @ResponseBody
    public Map<String, Object> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        infntAplyHistVo.setUser(user);
        List<InfntAplyHistVo> list = infntAplyHistService.selectInfntAplyHistList(infntAplyHistVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @GetMapping("/infntAplyHistDetail.html")
    public String infntAplyHistDetail(InfntAplyHistVo infntAplyHistVo, Model model, @UserInfo UserVo user) throws Exception {
        infntAplyHistVo.setUser(user);
        InfntAplyHistVo infntAplyHistInfoVo = infntAplyHistService.selectInfntAplyHistInfo(infntAplyHistVo);
        InfntAplyHistVo infntAplyHistRegVo = infntAplyHistService.selectInfntAplyHistDetail(infntAplyHistVo);
        String eduTrgtCd = infntAplyHistService.selectEduTrgtCd(infntAplyHistVo);
        
        model.addAttribute("infntAplyHistInfoVo", infntAplyHistInfoVo);
        model.addAttribute("infntAplyHistRegVo", infntAplyHistRegVo);
        model.addAttribute("eduTrgtCd", eduTrgtCd);

        List<CodeInfoVo> codeInfoList = new ArrayList<>();
        for (String tc : infntAplyHistInfoVo.getTrgtCd().split(",")) {
            codeInfoList.add(resCodeService.getCodeInfo(tc));
        }

        model.addAttribute("codeInfoList", codeInfoList);
        model.addAttribute("infntAplyHistVo", infntAplyHistVo);
        return VIEW_PATH + "/infntAplyHistDetail";
    }
    
    @PostMapping("/updateSttsInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateSttsInfntAply(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        infntAplyHistVo.setUser(userVo);

        if (infntAplyHistService.updateSttsInfntAply(infntAplyHistVo) > 0) {
            success = true;
            if("180104".equals(infntAplyHistVo.getUpdCd())){
                response.put("msg", "취소가 완료되었습니다.");
            }else if("180105".equals(infntAplyHistVo.getUpdCd())){
                response.put("msg", "승인거부가 완료되었습니다.");                
            }
        } else
            if("180104".equals(infntAplyHistVo.getUpdCd())){
                response.put("msg", "취소가 실패하였습니다.");
            }
            if("180105".equals(infntAplyHistVo.getUpdCd())){
                response.put("msg", "승인거부가 실패하였습니다.");
            }
        response.put("success", success);
        return response;
    }    
    
    /**
    * 유아환경교육관 교육 신청 이력 설문공유. 
    *
    * @Title : infntAplyHistSrvySendPopup
    * @Description : 유아환경교육관 교육 신청 이력 설문공유
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/infntAplyHistSrvySendPopup.html")
    public String infntAplyHistSrvySendPopup(InfntAplyHistVo infntAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (infntAplyHistVo != null) {
            //srvyDetail = infntAplyHistService.detailSplmnt(infntAplyHistVo);
            srvyVo.setSrvyid(infntAplyHistVo.getStdntDgstfnSrvyid());
            model.addAttribute("infntPrgrmAplyid", infntAplyHistVo.getAplyid());
            model.addAttribute("prgrmNm", infntAplyHistVo.getPrgrmNm());
            model.addAttribute("srvyDetail", infntAplyHistService.selectInfntSrvyInfo(srvyVo));
            
        }
        //model.addAttribute("srvyDetail", srvyDetail);
        return VIEW_PATH + "/infntAplyHistSrvySendPopup";
    }    

    /**
     * 유아환경교육관 교육 신청 이력 공유설문 결과. 
     *
     * @Title : infntAplyHistSrvyRsltPopup
     * @Description : 유아환경교육관 교육 신청 이력 공유설문 결과
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value="/infntAplyHistSrvyRsltPopup.html")
    public String infntAplyHistSrvyRsltPopup(InfntAplyHistVo infntAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (infntAplyHistVo != null) {
            //srvyDetail = infntAplyHistService.detailSplmnt(infntAplyHistVo);
            srvyVo.setInfntPrgrmAplyid(infntAplyHistVo.getAplyid());
            srvyVo.setSrvyid(infntAplyHistVo.getStdntDgstfnSrvyid());
        }
        model.addAttribute("infntPrgrmAplyid", infntAplyHistVo.getAplyid());
        model.addAttribute("srvySendList", infntAplyHistService.selectInfntSrvySendList(srvyVo));
        return VIEW_PATH + "/infntAplyHistSrvyRsltPopup";
    }    
    
    @RequestMapping(value = "/updateInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateInfntAply(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        infntAplyHistVo.setUser(user);
        retVal = infntAplyHistService.updateInfntAply(infntAplyHistVo);
        
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
