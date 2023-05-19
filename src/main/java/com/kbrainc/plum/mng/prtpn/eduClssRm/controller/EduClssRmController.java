package com.kbrainc.plum.mng.prtpn.eduClssRm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.service.EduClssRmService;
import com.kbrainc.plum.mng.prtpn.eduSarea.service.EduSareaService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 유아환경교육 -> 교육관관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduClssRm.controller
* - EduClssRmController.java
* </pre>
**
@ClassName : EduClssRmController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EduClssRmController {
    
    @Autowired
    private EduClssRmService eduClssRmService;
    
    @Autowired
    private EduSareaService eduSareaService;
    
    @Autowired
    private CommonService commonService;
    
    /**
    * 교육관관리 리스트화면으로 이동
    *
    * @Title : eduClssRmListForm
    * @Description : 교육관관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/eduClssRmListForm.html")
    public String eduClssRmListForm(Model model, HttpServletRequest request) throws Exception {
        List<Map<String, Object>> instList = commonService.selectAlowedInstList();
        model.addAttribute("instList", instList);
        model.addAttribute("ctprvnCdList", eduSareaService.selectAddrCtprvnList());
        return "mng/prtpn/eduClssRm/eduClssRmList";
    }
    
    /**
    * 교육관관리 등록화면으로 이동
    *
    * @Title : eduClssRmInsertForm
    * @Description : 교육관관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/eduClssRmInsertForm.html")
    public String eduClssRmInsertForm(Model model) throws Exception {
        model.addAttribute("ctprvnCdList", eduSareaService.selectAddrCtprvnList());
        return "mng/prtpn/eduClssRm/eduClssRmInsertForm";
    }
    
    /**
    * 교육관관리 수정화면으로 이동
    *
    * @Title : eduClssRmUpdateForm
    * @Description : 교육관관리 수정화면으로 이동
    * @param eduClssRmVo 교육관관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/eduClssRmUpdateForm.html")
    public String eduClssRmUpdateForm(EduClssRmVo eduClssRmVo, Model model) throws Exception {
        EduClssRmVo result = null;
        result = eduClssRmService.selectEduClssRmInfo(eduClssRmVo);
        model.addAttribute("eduClssRm", result);
        model.addAttribute("ctprvnCdList", eduSareaService.selectAddrCtprvnList());
        return "mng/prtpn/eduClssRm/eduClssRmUpdate";
    }
   
    
    /**
    * 교육관관리 게시글 목록 조회
    *
    * @Title : selectEduClssRmList
    * @Description : 교육관관리 게시글 목록 조회
    * @param eduClssRmVo 교육관관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/selectEduClssRmList.do")
    @ResponseBody
    public Map<String, Object> selectEduClssRmList(EduClssRmVo eduClssRmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EduClssRmVo> result = null;
        
        result =  eduClssRmService.selectEduClssRmList(eduClssRmVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 교육관관리 게시글 등록 기능
    *
    * @Title : insertEduClssRm
    * @Description : 교육관관리 수정 기능
    * @param eduClssRmVo 교육관관리 객체
    * @param bindingResult 교육관관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/insertEduClssRm.do")
    @ResponseBody
    public Map<String, Object> insertEduClssRm(@Valid EduClssRmVo eduClssRmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        eduClssRmVo.setUser(user);

        int retVal = 0;
                
        retVal = eduClssRmService.insertEduClssRm(eduClssRmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * 교육관관리 게시글 수정 기능
    *
    * @Title : updateEduClssRm
    * @Description : 교육관관리 게시글 수정 기능
    * @param eduClssRmVo 교육관관리 객체
    * @param bindingResult 교육관관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/updateEduClssRm.do")
    @ResponseBody
    public Map<String, Object> updateEduClssRm(@Valid EduClssRmVo eduClssRmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        eduClssRmVo.setUser(user);

        int retVal = 0;
                
        retVal = eduClssRmService.updateEduClssRm(eduClssRmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * 교육관관리 교육관 교육유형 코드 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육관관리 교육관 교육유형 코드 조회
    * @param clssrmId
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/selectClssrmEduTypeCd.do")
    @ResponseBody
    public Map<String, Object> selectClssrmEduTypeCd(String clssrmId) throws Exception {
        EduClssRmVo resultVo = new EduClssRmVo();
        resultVo =  eduClssRmService.selectClssrmEduTypeCd(clssrmId);
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("eduTypeCd", resultVo.getEduTypeCd());
        resultMap.put("clssrmViewngMaxAplyNope", resultVo.getClssrmViewngMaxAplyNope());

        return resultMap;
            
    }    
    
    /**
    * 기관검색 팝업으로 이동
    *
    * @Title : eduClssRmInstSearchPopup
    * @Description : 기관검색 팝업으로 이동
    * @param model 객체
    * @param request 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduClssRm/eduClssRmInstSearchPopup.html")
    public String eduClssRmInstSearchPopup(Model model, HttpServletRequest request) throws Exception {
        String instNm = request.getParameter("instNm");
        model.addAttribute("instNm", instNm);
        
        return "mng/prtpn/eduClssRm/eduClssRmInstSearchPopup";
    }    
}
