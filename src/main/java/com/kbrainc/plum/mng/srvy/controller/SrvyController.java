package com.kbrainc.plum.mng.srvy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kbrainc.plum.mng.example.excel.service.ExcelService;
import com.kbrainc.plum.mng.srvy.model.SrvyUserVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.mng.srvy.service.SrvyServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.ExcelUtil;

/**
 * 
 * 설문관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.controller
 * - SrvyController.java
 * </pre>
 *
 * @ClassName : SrvyController
 * @Description : 설문관리 Controller
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SrvyController {

    @Autowired
    private SrvyServiceImpl srvyService;
    
    @Autowired
    ExcelService excelService;
    
    /**
     * 설문관리 화면
     *
     * @Title : srvyMng
     * @Description : 설문관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyMng.html")
    public String srvyMng() throws Exception {
        return "mng/srvy/srvyMng";
    }
    
    /**
     * 대상자설문 등록 화면
     *
     * @Title : trprSrvyInsertForm
     * @Description : 설문관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvyInsertForm.html")
    public String trprSrvyInsertForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110100");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        return "mng/srvy/trprSrvyInsert";
    }
    
    /**
     * 대상자설문 수정 화면
     *
     * @Title : trprSrvyUpdateForm
     * @Description : 대상자설문 수정 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvyUpdateForm.html")
    public String trprSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110100");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/trprSrvyUpdate";
    }
    
    /**
     * 회원 목록 팝업
     *
     * @Title : userListPopup
     * @Description : 회원 목록 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/userListPopup.html")
    public String userListPopup() throws Exception {
        return "mng/srvy/userListPopup";
    }
    
    /**
     * 대상자 엑셀업로드 팝업
     *
     * @Title : trprExcelUploadPopup
     * @Description : 대상자 엑셀업로드 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprExcelUploadPopup.html")
    public String trprExcelUploadPopup() throws Exception {
        return "mng/srvy/trprExcelUploadPopup";
    }
    
    /**
     * 대상자설문 목록 화면
     *
     * @Title : srvyMng
     * @Description : 대상자설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvy.html")
    public String trprSrvy() throws Exception {
        return "mng/srvy/trprSrvyList";
    }
    
    /**
     * 기관설문 목록 화면
     *
     * @Title : instSrvy
     * @Description : 기관설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvy.html")
    public String instSrvy() throws Exception {
        return "mng/srvy/instSrvyList";
    }
    
    /**
     * 컨설팅만족도설문 목록 화면
     *
     * @Title : cnsltngDgstfnSrvy
     * @Description : 컨설팅만족도설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvy.html")
    public String cnsltngDgstfnSrvy() throws Exception {
        return "mng/srvy/cnsltngDgstfnSrvyList";
    }
    
    /**
     * 설문결과관리 화면
     *
     * @Title : srvyRsltMng
     * @Description : 설문결과관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyRsltMng.html")
    public String srvyRsltMng() throws Exception {
        return "mng/srvy/srvyRsltMng";
    }
    
    /**
     * 대상자설문 등록
     *
     * @Title : insertTrprSrvy
     * @Description : 대상자설문 등록
     * @param srvyVo SrvyVo 객체
     * @param srvyBindingResult srvyVo 유효성 검증결과
     * @param srvyUserVo SrvyUserVo 객체
     * @param srvyUserBindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertTrprSrvy.do")
    @ResponseBody
    public Map<String, Object> insertTrprSrvy(@Valid SrvyVo srvyVo, BindingResult srvyBindingResult, @Valid SrvyUserVo srvyUserVo, BindingResult srvyUserBindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(srvyBindingResult.hasErrors()) {
            FieldError fieldError = srvyBindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        if(srvyUserBindingResult.hasErrors()) {
            FieldError fieldError = srvyUserBindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        srvyUserVo.setUser(user);
        retVal = srvyService.insertTrprSrvy(srvyVo, srvyUserVo);
        
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
     * 대상자설문 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 대상자설문 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectTrprSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectTrprSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectTrprSrvyList(srvyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 설문 대상자 목록 조회
     *
     * @Title : selectTrprList
     * @Description : 설문 대상자 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectTrprList.do")
    @ResponseBody
    public Map<String, Object> selectTrprList(SrvyUserVo srvyUserVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyUserVo> result = srvyService.selectTrprList(srvyUserVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 회원 목록 조회
     *
     * @Title : selectUserList
     * @Description : 회원 목록 조회
     * @param srvyUserVo SrvyUserVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectUserList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(SrvyUserVo srvyUserVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyUserVo> result = srvyService.selectUserList(srvyUserVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 개인회원 전체 대상 설문 대상자 등록
     *
     * @Title : insertIndvdlMbrSrvyUser
     * @Description : 개인회원 전체 대상 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo 객체
     * @param bindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertIndvdlMbrSrvyUser.do")
    @ResponseBody
    public Map<String, Object> insertIndvdlMbrSrvyUser(@Valid SrvyUserVo srvyUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyUserVo.setUser(user);
        retVal = srvyService.insertIndvdlMbrSrvyUser(srvyUserVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
        
        return resultMap;
    }
    
    /**
     * 설문 대상자 등록
     *
     * @Title : insertTrpr
     * @Description : 설문 대상자 등록
     * @param srvyUserVo SrvyUserVo 객체
     * @param bindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertTrpr.do")
    @ResponseBody
    public Map<String, Object> insertTrpr(@Valid SrvyUserVo srvyUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyUserVo.setUser(user);
        retVal = srvyService.insertTrpr(srvyUserVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
        
        return resultMap;
    }
    
    /**
     * 설문 대상자 삭제
     *
     * @Title : deleteTrpr
     * @Description : 설문 대상자 삭제
     * @param srvyUserVo SrvyUserVo 객체
     * @param bindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/deleteTrpr.do")
    @ResponseBody
    public Map<String, Object> deleteTrpr(@Valid SrvyUserVo srvyUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyUserVo.setUser(user);
        retVal = srvyService.deleteTrpr(srvyUserVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패하였습니다");
        }
        
        return resultMap;
    }
    
    /**
     * 대상자 엑셀 데이터 정합성 체크
     *
     * @Title : trprExcelDataCheck
     * @Description : 대상자 엑셀 데이터 정합성 체크
     * @param multiRequest MultipartHttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprExcelDataCheck.do")
    @ResponseBody
    public Map<String, Object> trprExcelDataCheck(MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        MultipartFile file = multiRequest.getFile("trprExcelFile");
        ArrayList excelList = null;
        String fileName = null;
        if(file != null) {
            fileName = file.getOriginalFilename();
        }
        if(fileName != null) {
            if(fileName.indexOf(".xlsx") > -1) {
                excelList = ExcelUtil.getExcelPoiArrayList(file.getInputStream());
            } else if(fileName.indexOf(".xls") > -1) {
                excelList = ExcelUtil.getExcelJxlArrayList(file.getInputStream());
            }
        }
        Map<String, Object> result = srvyService.trprExcelDataCheck(excelList);
        resultMap.put("checkList", (ArrayList) result.get("checkList"));
        resultMap.put("validYn", (String) result.get("validYn"));
            
        return resultMap;
    }
    
}
