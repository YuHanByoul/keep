package com.kbrainc.plum.mng.cmmCntnts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import com.kbrainc.plum.mng.etcDta.model.EtcDtaVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 콘텐츠 품질관리 체크리스트 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.cmmCntnts.controller
* - CmmCntntsController.java
* </pre>
**
@ClassName : CmmCntntsController
* @Description : TODO
* @author : 이한명
* @date : 2023. 4. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CmmCntntsController {
    
    @Autowired
    private CmmCntntsService cmmCntntsService;
    
    
    /**
    * 콘텐츠 품질관리 체크리스트 팝업으로 이동
    *
    * @Title : cmmCntntsQlityPopup
    * @Description : 콘텐츠 품질관리 체크리스트 팝업으로 이동
    * @param model 객체
    * @param request 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/cmmCntnts/cmmCntntsQlityPopup.html")
    public String cmmCntntsQlityPopup(Model model, HttpServletRequest request) throws Exception {
        String trgtCd = request.getParameter("trgtCd");
        String evntCd = request.getParameter("evntCd");
        String cntntsid = request.getParameter("cntntsid");
        model.addAttribute("trgtCd", trgtCd);
        model.addAttribute("evntCd", evntCd);
        model.addAttribute("cntntsid", cntntsid);
        
        return "mng/cmmCntnts/cmmCntntsQlityPopup";
    }    
    
    /**
    * 콘텐츠 품질관리 체크리스트 목록 조회
    *
    * @Title : selectCmmCntntsQlityChklstList
    * @Description : 콘텐츠 품질관리 체크리스트 목록 조회
    * @param cmmCntntsVo 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cmmCntnts/selectCmmCntntsQlityChklstList.do")
    @ResponseBody
    public Map<String, Object> selectCmmCntntsQlityChklstList(CmmCntntsVo cmmCntntsVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmmCntntsVo> result = null;
        Integer checkid =null;
        cmmCntntsVo.setUser(user);
        
        checkid = cmmCntntsService.selectCmmCntntsQlityChkId(cmmCntntsVo);    
        
        if(null != checkid) {
            cmmCntntsVo.setCheckid(checkid);
        }
        result =  cmmCntntsService.selectCmmCntntsQlityChklstList(cmmCntntsVo);
        
        resultMap.put("chklist", result);
        //resultMap.put("cmmCntnts", cmmCntnts);
        
        return resultMap;
    }
    
    /**
    * 게시글 품질관리 체크리스트 저장 게시글 등록 기능
    *
    * @Title : insertCmmCntnts
    * @Description : 게시글 품질관리 체크리스트 저장 수정 기능
    * @param cmmCntntsVo 게시글 품질관리 체크리스트 저장 객체
    * @param bindingResult 게시글 품질관리 체크리스트 저장 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cmmCntnts/insertCmmCntntsQlity.do")
    @ResponseBody
    public Map<String, Object> insertCmmCntntsQlity(@Valid CmmCntntsVo cmmCntntsVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        cmmCntntsVo.setUser(user);
        int retVal = 0;
        retVal = cmmCntntsService.insertCmmCntnts(cmmCntntsVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        return resultMap;
    }
  
}
