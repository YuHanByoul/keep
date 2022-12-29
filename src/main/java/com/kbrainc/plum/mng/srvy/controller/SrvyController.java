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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kbrainc.plum.mng.example.excel.service.ExcelService;
import com.kbrainc.plum.mng.srvy.model.SrvyInstVo;
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
     * @Title : srvyMngForm
     * @Description : 설문관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyMngForm.html")
    public String srvyMngForm() throws Exception {
        return "mng/srvy/srvyMng";
    }
    
    /**
     * 대상자설문 등록 화면
     *
     * @Title : trprSrvyInsertForm
     * @Description : 대상자설문 등록 화면
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
     * @Title : trprSrvyListForm
     * @Description : 대상자설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvyListForm.html")
    public String trprSrvyListForm() throws Exception {
        return "mng/srvy/trprSrvyList";
    }
    
    /**
     * 기관설문 등록 화면
     *
     * @Title : instSrvyInsertForm
     * @Description : 기관설문 등록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvyInsertForm.html")
    public String instSrvyInsertForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110101");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        return "mng/srvy/instSrvyInsert";
    }
    
    /**
     * 기관설문 목록 화면
     *
     * @Title : instSrvyListForm
     * @Description : 기관설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvyListForm.html")
    public String instSrvyListForm() throws Exception {
        return "mng/srvy/instSrvyList";
    }
    
    /**
     * 기관설문 수정 화면
     *
     * @Title : instSrvyUpdateForm
     * @Description : 기관설문 수정 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvyUpdateForm.html")
    public String instSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110101");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/instSrvyUpdate";
    }
    
    /**
     * 기관 목록 팝업
     *
     * @Title : instListPopup
     * @Description : 기관 목록 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instListPopup.html")
    public String instListPopup() throws Exception {
        return "mng/srvy/instListPopup";
    }
    
    /**
     * 대상기관 엑셀업로드 팝업
     *
     * @Title : instExcelUploadPopup
     * @Description : 대상기관 엑셀업로드 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instExcelUploadPopup.html")
    public String instExcelUploadPopup() throws Exception {
        return "mng/srvy/instExcelUploadPopup";
    }
    
    /**
     * 컨설팅만족도설문 등록 화면
     *
     * @Title : cnsltngDgstfnSrvyInsertForm
     * @Description : 컨설팅만족도설문 등록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvyInsertForm.html")
    public String cnsltngDgstfnSrvyInsertForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110102");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        return "mng/srvy/cnsltngDgstfnSrvyInsert";
    }
    
    /**
     * 컨설팅만족도설문 목록 화면
     *
     * @Title : cnsltngDgstfnSrvyListForm
     * @Description : 컨설팅만족도설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvyListForm.html")
    public String cnsltngDgstfnSrvyListForm() throws Exception {
        return "mng/srvy/cnsltngDgstfnSrvyList";
    }
    
    /**
     * 컨설팅만족도설문 수정 화면
     *
     * @Title : cnsltngDgstfnSrvyUpdateForm
     * @Description : 컨설팅만족도설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvyUpdateForm.html")
    public String cnsltngDgstfnSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110102");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/cnsltngDgstfnSrvyUpdate";
    }
    
    /**
     * 설문결과관리 화면
     *
     * @Title : srvyRsltMngForm
     * @Description : 설문결과관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyRsltMngForm.html")
    public String srvyRsltMngForm() throws Exception {
        return "mng/srvy/srvyRsltMng";
    }
    
    /**
     * 대상자설문 결과 목록 화면
     *
     * @Title : trprSrvyRsltListForm
     * @Description : 대상자설문 결과 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvyRsltListForm.html")
    public String trprSrvyRsltListForm() throws Exception {
        return "mng/srvy/trprSrvyRsltList";
    }
    
    /**
     * 대상자설문 결과 상세 화면
     *
     * @Title : trprSrvyRsltDetailForm
     * @Description : 대상자설문 결과 상세 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvyRsltDetailForm.html")
    public String trprSrvyRsltDetailForm() throws Exception {
        return "mng/srvy/trprSrvyRsltDetail";
    }
    
    /**
     * 기관설문 결과 목록 화면
     *
     * @Title : instSrvyRsltListForm
     * @Description : 기관설문 결과 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvyRsltListForm.html")
    public String instSrvyRsltListForm() throws Exception {
        return "mng/srvy/instSrvyRsltList";
    }
    
    /**
     * 기관설문 결과 상세 화면
     *
     * @Title : instSrvyRsltDetailForm
     * @Description : 기관설문 결과 상세 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvyRsltDetailForm.html")
    public String instSrvyRsltDetailForm() throws Exception {
        return "mng/srvy/instSrvyRsltDetail";
    }
    
    /**
     * 컨설팅만족도설문 결과 목록 화면
     *
     * @Title : cnsltngDgstfnSrvyRsltListForm
     * @Description : 컨설팅만족도설문 결과 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvyRsltListForm.html")
    public String cnsltngDgstfnSrvyRsltListForm() throws Exception {
        return "mng/srvy/cnsltngDgstfnSrvyRsltList";
    }
    
    /**
     * 컨설팅만족도설문 결과 상세 화면
     *
     * @Title : cnsltngDgstfnSrvyRsltDetailForm
     * @Description : 컨설팅만족도설문 결과 상세 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvyRsltDetailForm.html")
    public String cnsltngDgstfnSrvyRsltDetailForm() throws Exception {
        return "mng/srvy/cnsltngDgstfnSrvyRsltDetail";
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
     * @Title : selectTrprSrvyList
     * @Description : 대상자설문 목록 조회
     * @param srvyVo SrvyVo 객체
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
     * 기관설문 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 기관설문 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectInstSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectInstSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectInstSrvyList(srvyVo);
                    
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
    
    /**
     * 대상자설문 업데이트
     *
     * @Title : updateTrprSrvy
     * @Description : 대상자설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @param bindingResult srvyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/updateTrprSrvy.do")
    @ResponseBody
    public Map<String, Object> updateTrprSrvy(@Valid SrvyVo srvyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        retVal = srvyService.updateTrprSrvy(srvyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
            
        return resultMap;
    }
    
    /**
     * 기관 목록 조회
     *
     * @Title : selectInstList
     * @Description : 기관 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectInstList.do")
    @ResponseBody
    public Map<String, Object> selectInstList(SrvyInstVo srvyInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyInstVo> result = srvyService.selectInstList(srvyInstVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 기관 전체 대상 설문 대상기관 등록
     *
     * @Title : insertInstTrgtSrvyInst
     * @Description : 기관 전체 대상 설문 대상기관 등록
     * @param srvyInstVo SrvyInstVo 객체
     * @param bindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertInstTrgtSrvyInst.do")
    @ResponseBody
    public Map<String, Object> insertInstTrgtSrvyInst(@Valid SrvyInstVo srvyInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyInstVo.setUser(user);
        retVal = srvyService.insertInstTrgtSrvyInst(srvyInstVo);
        
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
     * 기관설문 등록
     *
     * @Title : insertInstSrvy
     * @Description : 대상자설문 등록
     * @param srvyVo SrvyVo 객체
     * @param srvyBindingResult srvyVo 유효성 검증결과
     * @param srvyInstVo SrvyInstVo 객체
     * @param srvyInstBindingResult srvyUserVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertInstSrvy.do")
    @ResponseBody
    public Map<String, Object> insertInstSrvy(@Valid SrvyVo srvyVo, BindingResult srvyBindingResult, @Valid SrvyInstVo srvyInstVo, BindingResult srvyInstBindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(srvyBindingResult.hasErrors()) {
            FieldError fieldError = srvyBindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        if(srvyInstBindingResult.hasErrors()) {
            FieldError fieldError = srvyInstBindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        srvyInstVo.setUser(user);
        retVal = srvyService.insertInstSrvy(srvyVo, srvyInstVo);
        
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
     * 기관설문 업데이트
     *
     * @Title : updateInstSrvy
     * @Description : 기관설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @param bindingResult srvyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/updateInstSrvy.do")
    @ResponseBody
    public Map<String, Object> updateInstSrvy(@Valid SrvyVo srvyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        retVal = srvyService.updateInstSrvy(srvyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
            
        return resultMap;
    }
    
    /**
     * 설문 대상기관 등록
     *
     * @Title : insertSrvyInst
     * @Description : 설문 대상기관 등록
     * @param srvyInstVo SrvyInstVo 객체
     * @param bindingResult srvyInstVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertSrvyInst.do")
    @ResponseBody
    public Map<String, Object> insertSrvyInst(@Valid SrvyInstVo srvyInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyInstVo.setUser(user);
        retVal = srvyService.insertSrvyInst(srvyInstVo);
        
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
     * 설문 대상기관 목록 조회
     *
     * @Title : selectSrvyInstList
     * @Description : 설문 대상기관 목록 조회
     * @param srvyInstVo srvyInstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectSrvyInstList.do")
    @ResponseBody
    public Map<String, Object> selectSrvyInstList(SrvyInstVo srvyInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyInstVo> result = srvyService.selectSrvyInstList(srvyInstVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 설문 대상기관 삭제
     *
     * @Title : deleteSrvyInst
     * @Description : 설문 대상기관 삭제
     * @param srvyInstVo SrvyInstVo 객체
     * @param bindingResult srvyInstVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/deleteSrvyInst.do")
    @ResponseBody
    public Map<String, Object> deleteSrvyInst(@Valid SrvyInstVo srvyInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvyInstVo.setUser(user);
        retVal = srvyService.deleteSrvyInst(srvyInstVo);
        
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
     * 대상기관 엑셀 데이터 정합성 체크
     *
     * @Title : instExcelDataCheck
     * @Description : 대상기관 엑셀 데이터 정합성 체크
     * @param multiRequest MultipartHttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instExcelDataCheck.do")
    @ResponseBody
    public Map<String, Object> instExcelDataCheck(MultipartHttpServletRequest multiRequest, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        MultipartFile file = multiRequest.getFile("instExcelFile");
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
        Map<String, Object> result = srvyService.instExcelDataCheck(excelList);
        resultMap.put("checkList", (ArrayList) result.get("checkList"));
        resultMap.put("validYn", (String) result.get("validYn"));
            
        return resultMap;
    }
    
    /**
     * 컨설팅만족도설문 등록
     *
     * @Title : insertInstSrvy
     * @Description : 컨설팅만족도설문 등록
     * @param srvyVo SrvyVo 객체
     * @param srvyBindingResult srvyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/insertCnsltngDgstfnSrvy.do")
    @ResponseBody
    public Map<String, Object> insertCnsltngDgstfnSrvy(@Valid SrvyVo srvyVo, BindingResult srvyBindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(srvyBindingResult.hasErrors()) {
            FieldError fieldError = srvyBindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        retVal = srvyService.insertCnsltngDgstfnSrvy(srvyVo);
        
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
     * 컨설팅만족도설문 목록 조회
     *
     * @Title : selectCnsltngDgstfnSrvyList
     * @Description : 컨설팅만족도설문 목록 조회
     * @param srvyVo SrvyVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectCnsltngDgstfnSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectCnsltngDgstfnSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectCnsltngDgstfnSrvyList(srvyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 컨설팅만족도설문 컨설팅 목록 조회
     *
     * @Title : selectCnsltngList
     * @Description : 컨설팅만족도설문 컨설팅 목록 조회
     * @param srvyInstVo SrvyInstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectCnsltngList.do")
    @ResponseBody
    public Map<String, Object> selectCnsltngList(SrvyInstVo srvyInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyInstVo> result = srvyService.selectCnsltngList(srvyInstVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 컨설팅만족도설문 업데이트
     *
     * @Title : updateCnsltngDgstfnSrvy
     * @Description : 컨설팅만족도설문 업데이트
     * @param srvyVo SrvyVo 객체
     * @param bindingResult srvyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/updateCnsltngDgstfnSrvy.do")
    @ResponseBody
    public Map<String, Object> updateCnsltngDgstfnSrvy(@Valid SrvyVo srvyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvyVo.setUser(user);
        retVal = srvyService.updateCnsltngDgstfnSrvy(srvyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
            
        return resultMap;
    }
    
}
