package com.kbrainc.plum.front.mypage.infntAplyHist.controller;

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

import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
import com.kbrainc.plum.front.mypage.infntAplyHist.service.InfntAplyHistService;
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
        //List<InfntAplyVo> list = null;
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
        InfntAplyHistVo infntAplyHistInfoVo = infntAplyHistService.selectInfntAplyHistInfo(infntAplyHistVo);
        InfntAplyHistVo infntAplyHistRegVo = infntAplyHistService.selectInfntAplyHistDetail(infntAplyHistVo);
        
        model.addAttribute("infntAplyHistInfoVo", infntAplyHistInfoVo);
        model.addAttribute("infntAplyHistRegVo", infntAplyHistRegVo);
        
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        
        if(!"".equals(StringUtil.nvl(infntAplyHistRegVo.getTrgtCd(), ""))){
            String[] splitTrgtCdStr = infntAplyHistRegVo.getTrgtCd().split(",");
            String[] splitTrgtNmStr = infntAplyHistRegVo.getTrgtNm().split(",");
            for(int k=0; k<splitTrgtCdStr.length; k++){
                Map<String, String> map = new HashMap<String, String>();
                String trgtChk = "";
                if(!"".equals(StringUtil.nvl(infntAplyHistRegVo.getTrgtCd(),""))){
                    String[] splitEduTrgtCdStr = infntAplyHistRegVo.getTrgtCd().split(",");
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
        model.addAttribute("infntAplyHistVo", infntAplyHistVo);        
        return VIEW_PATH + "/infntAplyHistDetail";
    }
    
    @PostMapping("/updateCancelInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateCancelInfntAply(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        infntAplyHistVo.setUser(userVo);

        if (infntAplyHistService.updateCancelInfntAply(infntAplyHistVo) > 0) {
            success = true;
            response.put("msg", "취소가 완료되었습니다.");
        } else
            response.put("msg", "취소가 실패하였습니다.");

        response.put("success", success);
        return response;
    }    
}
