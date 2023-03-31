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
import com.kbrainc.plum.mng.qestnr.model.QestnrVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.mng.qestnr.service.QestnrServiceImpl;
import com.kbrainc.plum.mng.srvy.model.SrvyAnsVo;
import com.kbrainc.plum.mng.srvy.model.SrvyInstVo;
import com.kbrainc.plum.mng.srvy.model.SrvySiteVo;
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
 * com.kbrainc.plum.mng.srvy.controller
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
    private QestnrServiceImpl qestnrService;
    
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
    @RequestMapping(value = {"/mng/srvy/srvyMngForm.html", "/mng/srvyInvstg/srvyMngForm.html"})
    public String srvyMngForm() throws Exception {
        return "mng/srvy/srvyMng";
    }
    
    /**
    * 홈페이지설문 등록 화면
    *
    * @Title : homeSrvyInsertForm
    * @Description : 홈페이지설문 등록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/homeSrvyInsertForm.html")
    public String homeSrvyInsertForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110107");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        return "mng/srvy/homeSrvyInsert";
    }
    
    /**
    * 홈페이지설문 수정 화면
    *
    * @Title : homeSrvyUpdateForm
    * @Description : 대상자설문 수정 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/homeSrvyUpdateForm.html")
    public String homeSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110107");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/homeSrvyUpdate";
    }
    
    /**
    * 사이트 목록 팝업
    *
    * @Title : siteListPopup
    * @Description : 사이트 목록 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/siteListPopup.html")
    public String siteListPopup() throws Exception {
        return "mng/srvy/siteListPopup";
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
    * 홈페이지설문 목록 화면
    *
    * @Title : homeSrvyListForm
    * @Description : 홈페이지설문 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/homeSrvyListForm.html")
    public String homeSrvyListForm(QestnrVo qestnrVo, Model model) throws Exception {
        model.addAttribute("siteList", qestnrService.selectSiteList(qestnrVo));
        return "mng/srvy/homeSrvyList";
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
    * 유아환경교육(신청자) 목록 화면
    *
    * @Title : aplcntEnvEduSrvyListForm
    * @Description : 유아환경교육(신청자) 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvEduSrvyListForm.html")
    public String aplcntEnvEduSrvyListForm() throws Exception {
        return "mng/srvy/aplcntEnvEduSrvyList";
    }
    
    /**
    * 유아환경교육(신청자) 등록 화면
    *
    * @Title : aplcntEnvEduSrvyInsertForm
    * @Description : 유아환경교육(신청자) 등록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvEduSrvyInsertForm.html")
    public String aplcntEnvEduSrvyInsertForm() throws Exception {
        return "mng/srvy/aplcntEnvEduSrvyInsert";
    }
    
    /**
    * 유아환경교육(신청자) 수정 화면
    *
    * @Title : aplcntEnvEduSrvyUpdateForm
    * @Description : 유아환경교육(신청자) 수정 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvEduSrvyUpdateForm.html")
    public String aplcntEnvEduSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110105");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/aplcntEnvEduSrvyUpdate";
    }
    
    /**
     * 유아환경교육(학생) 목록 화면
     *
     * @Title : stdntEnvEduSrvyListForm
     * @Description : 유아환경교육(신청자) 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
     @RequestMapping(value = "/mng/srvy/stdntEnvEduSrvyListForm.html")
     public String stdntEnvEduSrvyListForm() throws Exception {
         return "mng/srvy/stdntEnvEduSrvyList";
     }
    
     /**
      * 유아환경교육(학생) 등록 화면
      *
      * @Title : stdntEnvEduSrvyInsertForm
      * @Description : 유아환경교육(학생) 등록 화면
      * @return String 화면경로
      * @throws Exception 예외
      */
      @RequestMapping(value = "/mng/srvy/stdntEnvEduSrvyInsertForm.html")
      public String stdntEnvEduSrvyInsertForm() throws Exception {
          return "mng/srvy/stdntEnvEduSrvyInsert";
      }
     
    /**
    * 유아환경교육(학생) 수정 화면
    *
    * @Title : stdntEnvEduSrvyUpdateForm
    * @Description : 유아환경교육(학생) 수정 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/stdntEnvEduSrvyUpdateForm.html")
    public String stdntEnvEduSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110106");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/stdntEnvEduSrvyUpdate";
    }
    
    /**
    * 푸름이아동환경교실(신청자) 목록 화면
    *
    * @Title : aplcntEnvClassroomSrvyListForm
    * @Description : 푸름이아동환경교실(신청자) 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvClassroomSrvyListForm.html")
    public String aplcntEnvClassroomSrvyListForm() throws Exception {
        return "mng/srvy/aplcntEnvClassroomSrvyList";
    }
    
    /**
    * 푸름이아동환경교실(신청자) 등록 화면
    *
    * @Title : aplcntEnvClassroomSrvyListForm
    * @Description : 푸름이아동환경교실(신청자) 등록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvClassroomSrvyInsertForm.html")
    public String aplcntEnvClassroomSrvyInsertForm() throws Exception {
        return "mng/srvy/aplcntEnvClassroomSrvyInsert";
    }
    
    /**
    * 푸름이아동환경교실(신청자) 수정 화면
    *
    * @Title : aplcntEnvClassroomSrvyUpdateForm
    * @Description : 푸름이아동환경교실(신청자) 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/aplcntEnvClassroomSrvyUpdateForm.html")
    public String aplcntEnvClassroomSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110103");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/aplcntEnvClassroomSrvyUpdate";
    }
    
    /**
    * 푸름이아동환경교실(학생) 목록 화면
    *
    * @Title : stdntEnvClassroomSrvyListForm
    * @Description : 푸름이아동환경교실(학생) 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/stdntEnvClassroomSrvyListForm.html")
    public String stdntEnvClassroomSrvyListForm() throws Exception {
        return "mng/srvy/stdntEnvClassroomSrvyList";
    }
    
    /**
    * 푸름이아동환경교실(학생) 등록 화면
    *
    * @Title : stdntEnvClassroomSrvyUpdateForm
    * @Description : 푸름이아동환경교실(학생) 등록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/stdntEnvClassroomSrvyInsertForm.html")
    public String stdntEnvClassroomSrvyInsertForm() throws Exception {
        return "mng/srvy/stdntEnvClassroomSrvyInsert";
    }
    
    /**
    * 푸름이아동환경교실(학생) 수정 화면
    *
    * @Title : stdntEnvClassroomSrvyUpdateForm
    * @Description : 푸름이아동환경교실(학생) 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/stdntEnvClassroomSrvyUpdateForm.html")
    public String stdntEnvClassroomSrvyUpdateForm(SrvyVo srvyVo, Model model) throws Exception {
        srvyVo.setQestnrKndCd("110104");
        model.addAttribute("qestnrList", srvyService.selectQestnrList(srvyVo));
        model.addAttribute("srvyInfo", srvyService.selectSrvyInfo(srvyVo));
        return "mng/srvy/stdntEnvClassroomSrvyUpdate";
    }
    
    /**
    * 설문결과관리 화면
    *
    * @Title : srvyRsltMngForm
    * @Description : 설문결과관리 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = {"/mng/srvy/srvyRsltMngForm.html", "/mng/srvyInvstg/srvyRsltMngForm.html"})
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
    * 설문 결과 상세 화면
    *
    * @Title : srvyRsltDetailForm
    * @Description : 설문 결과 상세 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/srvyRsltDetailForm.html")
    public String srvyRsltDetailForm(QitemVo qitemVo, Model model) throws Exception {
        model.addAttribute("qitemList", srvyService.selectSrvyRsltQitmeList(qitemVo));
        return "mng/srvy/srvyRsltDetail";
    }
    
    /**
    * 단답형, 서술형, 혼합형(기타) 답변 목록 팝업
    *
    * @Title : ansResultPopupForm
    * @Description : 단답형, 서술형, 혼합형(기타) 답변 목록 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/ansListPopup.html")
    public String ansListPopup(SrvyAnsVo srvyAnsVo, Model model) throws Exception {
        List<SrvyAnsVo> list = srvyService.selectAnsList(srvyAnsVo);
        int totalCount = list.size();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("ansList", list);
        return "mng/srvy/ansListPopup";
    }
    
    /**
    * 홈페이지설문 목록 조회
    *
    * @Title : selectHomeSrvyList
    * @Description : 홈페이지설문 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/selectHomeSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectHomeSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectHomeSrvyList(srvyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
    * 홈페이지설문 등록
    *
    * @Title : insertHomeSrvy
    * @Description : 대상자설문 등록
    * @param srvyVo SrvyVo 객체
    * @param srvyBindingResult srvyVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/insertHomeSrvy.do")
    @ResponseBody
    public Map<String, Object> insertHomeSrvy(@Valid SrvyVo srvyVo, BindingResult srvyBindingResult, @UserInfo UserVo user) throws Exception {
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
        retVal = srvyService.insertHomeSrvy(srvyVo);
        
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
    * 사이트 목록 조회
    *
    * @Title : selectSiteList
    * @Description : 사이트 목록 조회
    * @param qestnrVo QestnrVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/selectSiteList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(QestnrVo qestnrVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<QestnrVo> result = qestnrService.selectSiteList(qestnrVo);
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
    * 홈페이지설문 대상사이트 목록 조회
    *
    * @Title : selectSrvySiteList
    * @Description : 홈페이지설문 대상사이트 목록 조회
    * @param srvySiteVo SrvySiteVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/selectSrvySiteList.do")
    @ResponseBody
    public Map<String, Object> selectSrvySiteList(SrvySiteVo srvySiteVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvySiteVo> result = srvyService.selectSrvySiteList(srvySiteVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
    * 홈페이지설문 대상사이트 등록
    *
    * @Title : insertSrvySite
    * @Description : 홈페이지설문 대상사이트 등록
    * @param srvySiteVo SrvySiteVo 객체
    * @param bindingResult srvySiteVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/insertSrvySite.do")
    @ResponseBody
    public Map<String, Object> insertSrvySite(@Valid SrvySiteVo srvySiteVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvySiteVo.setUser(user);
        retVal = srvyService.insertSrvySite(srvySiteVo);
        
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
    * 홈페이지설문 대상사이트 삭제
    *
    * @Title : deleteSrvySite
    * @Description : 홈페이지설문 대상사이트 삭제
    * @param srvySiteVo SrvySiteVo 객체
    * @param bindingResult srvySiteVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/deleteSrvySite.do")
    @ResponseBody
    public Map<String, Object> deleteTrpr(@Valid SrvySiteVo srvySiteVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        srvySiteVo.setUser(user);
        retVal = srvyService.deleteSrvySite(srvySiteVo);
        
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
    * 홈페이지설문 업데이트
    *
    * @Title : updateHomeSrvy
    * @Description : 홈페이지설문 업데이트
    * @param srvyVo SrvyVo 객체
    * @param bindingResult srvyVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/updateHomeSrvy.do")
    @ResponseBody
    public Map<String, Object> updateHomeSrvy(@Valid SrvyVo srvyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
        retVal = srvyService.updateHomeSrvy(srvyVo);
        
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
    
    /**
    * 유아환경교육설문 목록 조회
    *
    * @Title : selectEnvEduSrvyList
    * @Description : 유아환경교육설문 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/selectEnvEduSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectEnvEduSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectEnvEduSrvyList(srvyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
    * 유아환경교육설문/푸름이아동환경교실 등록
    *
    * @Title : insertInstSrvy
    * @Description : 유아환경교육설문/푸름이아동환경교실 등록
    * @param srvyVo SrvyVo 객체
    * @param srvyBindingResult srvyVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/insertEnvSrvy.do")
    @ResponseBody
    public Map<String, Object> insertEnvSrvy(@Valid SrvyVo srvyVo, BindingResult srvyBindingResult, @UserInfo UserVo user) throws Exception {
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
        retVal = srvyService.insertEnvSrvy(srvyVo);
        
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
    * 유아환경교육설문/푸름이아동환경교실 업데이트
    *
    * @Title : updateEnvSrvy
    * @Description : 유아환경교육설문/푸름이아동환경교실 업데이트
    * @param srvyVo SrvyVo 객체
    * @param bindingResult srvyVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/srvy/updateEnvSrvy.do")
    @ResponseBody
    public Map<String, Object> updateEnvSrvy(@Valid SrvyVo srvyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
        retVal = srvyService.updateEnvSrvy(srvyVo);
        
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
