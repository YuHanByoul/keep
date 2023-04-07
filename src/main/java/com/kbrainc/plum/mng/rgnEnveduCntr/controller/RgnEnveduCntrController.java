package com.kbrainc.plum.mng.rgnEnveduCntr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;
import com.kbrainc.plum.mng.rgnEnveduCntr.service.RgnEnveduCntrService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 지역환경교육센터 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.rgnEnveduCntr.controller
* - RgnEnveduCntrController.java
* </pre>
*
* @ClassName : RgnEnveduCntrController
* @Description : 지역환경교육센터 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 30.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class RgnEnveduCntrController {
    
    @Autowired
    private RgnEnveduCntrService rgnEnveduCntrService;
    
    /**
    * 지역환경교육센터 목록 화면
    *
    * @Title : rgnEnveduCntrListForm
    * @Description : 지역환경교육센터 목록
    * @param model 객체
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/rgnEnveduCntrListForm.html")
    public String rgnEnveduCntrListForm(Model model, RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        List<RgnEnveduCntrVo> result = null;
        
        result =  rgnEnveduCntrService.selectCmmCdList(rgnEnveduCntrVo);
        model.addAttribute("cmmCd", result);
        
        result =  rgnEnveduCntrService.selectAddrCtpvnList(rgnEnveduCntrVo);
        model.addAttribute("ctprvn", result);   
        
        return "/mng/rgnEnveduCntr/rgnEnveduCntrList";
    }
    
    /**
    * 지역환경교육센터 등록 화면
    *
    * @Title : rgnEnveduCntrInsertForm
    * @Description : 지역환경교육센터 등록 화면
    * @param model 객체
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/rgnEnveduCntrInsertForm.html")
    public String rgnEnveduCntrInsertForm(Model model, RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        List<RgnEnveduCntrVo> result = null;
        
        result =  rgnEnveduCntrService.selectCmmCdList(rgnEnveduCntrVo);
        model.addAttribute("cmmCd", result);
        
        result =  rgnEnveduCntrService.selectAddrCtpvnList(rgnEnveduCntrVo);
        model.addAttribute("ctprvn", result);
        
        return "/mng/rgnEnveduCntr/rgnEnveduCntrInsertForm";
    }
    
    /**
    * 지역환경교육센터 수정 화면
    *
    * @Title : rgnEnveduCntrUpdateForm
    * @Description : 지역환경교육센터 수정 화면
    * @param model 객체
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/rgnEnveduCntrUpdateForm.html")
    public String rgnEnveduCntrUpdateForm(Model model, RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        List<RgnEnveduCntrVo> result = null;
        
        result =  rgnEnveduCntrService.selectCmmCdList(rgnEnveduCntrVo);
        model.addAttribute("cmmCd", result);
        
        result =  rgnEnveduCntrService.selectAddrCtpvnList(rgnEnveduCntrVo);
        model.addAttribute("ctprvn", result);
        
        RgnEnveduCntrVo rgnEnveduCntr = null;
        rgnEnveduCntr =  rgnEnveduCntrService.selectRgnEnveduCntrInfo(rgnEnveduCntrVo);
        model.addAttribute("rgnEnveduCntr", rgnEnveduCntr);
        
        return "/mng/rgnEnveduCntr/rgnEnveduCntrUpdate";
    }
    
    /**
    * 지역환경교육센터 목록 조회
    *
    * @Title : selectEnveduCntrList
    * @Description : 지역환경교육센터 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/selectRgnEnveduCntrList.do")
    @ResponseBody
    public Map<String, Object> selectRgnEnveduCntrList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<RgnEnveduCntrVo> result = null;
        
        result =  rgnEnveduCntrService.selectRgnEnveduCntrList(rgnEnveduCntrVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 지역환경교육센터 엑셀다운로드
    *
    * @Title : rgnEnveduCntrExcelDownload
    * @Description : 지역환경교육센터 엑셀다운로드
    * @param request 객체
    * @param response 객체
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return void
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/rgnEnveduCntrExcelDownload.do")
    public void rgnEnveduCntrExcelDownload(HttpServletRequest request, HttpServletResponse response, RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        rgnEnveduCntrService.selectRgnEnveduCntrExcelDownload(rgnEnveduCntrVo, response, request);
    } 
    
    @RequestMapping(value = "/mng/rgnEnveduCntr/insertEnveduCntr.do")
    @ResponseBody
    public Map<String, Object> insertEnveduCntr(@Valid RgnEnveduCntrVo rgnEnveduCntrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        rgnEnveduCntrVo.setUser(user);

        int retVal = 0;
                
        retVal = rgnEnveduCntrService.insertRgnEnveduCntr(rgnEnveduCntrVo);
        
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
    * 지역환경교육센터 등록
    *
    * @Title : insertRgnEnveduCntr
    * @Description : 지역환경교육센터 등록
    * @param rgnEnveduCntrVo RgnEnveduCntrVo 객체
    * @param bindingResult rgnEnveduCntrVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/insertRgnEnveduCntr.do")
    @ResponseBody
    public Map<String, Object> insertRgnEnveduCntr(@Valid RgnEnveduCntrVo rgnEnveduCntrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        rgnEnveduCntrVo.setUser(user);
        retVal = rgnEnveduCntrService.insertRgnEnveduCntr(rgnEnveduCntrVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("srvyid", retVal);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
        
        return resultMap;
    }
    
    /**
    * 지역환경교육센터 삭제 기능
    *
    * @Title : deleteEnveduCntr
    * @Description : 지역환경교육센터 삭제 기능
    * @param RgnEnveduCntrVo 지역환경교육센터 객체
    * @param user 유저 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/deleteRgnEnveduCntr.do")
    @ResponseBody
    public Map<String, Object> deleteEnveduCntr(RgnEnveduCntrVo RgnEnveduCntrVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        RgnEnveduCntrVo.setUser(user);

        int retVal = 0;
                
        retVal = rgnEnveduCntrService.deleteRgnEnveduCntr(RgnEnveduCntrVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * 지역환경교육센터 수정 기능
    *
    * @Title : updateEnveduCntr
    * @Description : 지역환경교육센터 수정 기능
    * @param RgnEnveduCntrVo 지역환경교육센터 객체
    * @param bindingResult 지역환경교육센터 유효성 검증결과
    * @param user 유저 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/rgnEnveduCntr/updateRgnEnveduCntr.do")
    @ResponseBody
    public Map<String, Object> updateRgnEnveduCntr(@Valid RgnEnveduCntrVo RgnEnveduCntrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        RgnEnveduCntrVo.setUser(user);

        int retVal = 0;
                
        retVal = rgnEnveduCntrService.updateRgnEnveduCntr(RgnEnveduCntrVo);
        
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
