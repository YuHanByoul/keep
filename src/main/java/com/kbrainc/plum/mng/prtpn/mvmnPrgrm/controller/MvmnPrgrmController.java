package com.kbrainc.plum.mng.prtpn.mvmnPrgrm.controller;

import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.service.EduSareaService;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service.MvmnPrgrmService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 유아환경교육 -> 교육프로그램관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnPrgrm.controller
* - MvmnPrgrmController.java
* </pre>
**
@ClassName : MvmnPrgrmController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class MvmnPrgrmController {
    
    @Autowired
    private MvmnPrgrmService mvmnPrgrmService;
    
    @Autowired
    private EduSareaService eduSareaService;

    @Autowired
    private ResCodeService resCodeService;

    /**
    * 교육프로그램관리 리스트화면으로 이동
    *
    * @Title : mvmnPrgrmListForm
    * @Description : 교육프로그램관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/mvmnPrgrmListForm.html")
    public String mvmnPrgrmListForm(Model model, HttpServletRequest request) throws Exception {
        
        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));

        List<Integer> years = getYears();
        model.addAttribute("years", years);

        return "mng/prtpn/mvmnPrgrm/mvmnPrgrmList";
    }



    /**
    * 교육프로그램관리 등록화면으로 이동
    *
    * @Title : mvmnPrgrmInsertForm
    * @Description : 교육프로그램관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/mvmnPrgrmInsertForm.html")
    public String mvmnPrgrmInsertForm(Model model) throws Exception {

        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));
        model.addAttribute("aplcntDgstfnSrvyList", mvmnPrgrmService.selectAplcntDgstfnSrvyList());
        model.addAttribute("stdntDgstfnSrvyList", mvmnPrgrmService.selectStdntDgstfnSrvyList());

        List<Integer> years = getYears();
        model.addAttribute("years", years);
        
        return "mng/prtpn/mvmnPrgrm/mvmnPrgrmInsertForm";
    }
    
    /**
    * 교육프로그램관리 수정화면으로 이동
    *
    * @Title : mvmnPrgrmUpdateForm
    * @Description : 교육프로그램관리 수정화면으로 이동
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/mvmnPrgrmUpdateForm.html")
    public String mvmnPrgrmUpdateForm(MvmnPrgrmVo mvmnPrgrmVo, Model model) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());

        List<Integer> years = getYears();
        model.addAttribute("years", years);

        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));
        model.addAttribute("aplcntDgstfnSrvyList", mvmnPrgrmService.selectAplcntDgstfnSrvyList());
        model.addAttribute("stdntDgstfnSrvyList", mvmnPrgrmService.selectStdntDgstfnSrvyList());
        
        MvmnPrgrmVo result = null;
        List<MvmnPrgrmVo> resultTmeList = null;
        
        result = mvmnPrgrmService.selectMvmnPrgrmInfo(mvmnPrgrmVo);
        resultTmeList =  mvmnPrgrmService.selectMvmnPrgrmTmeList(mvmnPrgrmVo);

        List<CodeInfoVo> codeList = resCodeService.getCodeList("155");
        Map<String, List<CodeInfoVo>> subCodeMap = new HashMap<>();

        for (CodeInfoVo codeInfoVo : codeList) {
            List<CodeInfoVo> subCodeList = resCodeService.getCodeList("155", codeInfoVo.getCd());
            subCodeMap.put(codeInfoVo.getCd(), subCodeList);
        }
        model.addAttribute("codeList", codeList);
        model.addAttribute("subCodeMap", subCodeMap);

        
        //교육소개 첨부파일
        //if(mvmnPrgrmVo.getEduIntrcnFileid() != 0 && result.getEduIntrcnFileIdntfcKey() != null) {
        if(result.getEduIntrcnFileIdntfcKey() != null) {
            StringBuffer eduIntrcnFileBtn = new StringBuffer();
            eduIntrcnFileBtn.append("<div class ='label label-inverse text-white' id='");
            eduIntrcnFileBtn.append(mvmnPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("'>");
            
            eduIntrcnFileBtn.append("<a href=javascript:downloadFileByFileid('");
            eduIntrcnFileBtn.append(mvmnPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("','");
            eduIntrcnFileBtn.append(result.getEduIntrcnFileIdntfcKey());
            eduIntrcnFileBtn.append("') class='text-white'>");
            eduIntrcnFileBtn.append(result.getEduIntrcnOrginlFileNm());
            eduIntrcnFileBtn.append("&nbsp;&nbsp;</a>");
            
            eduIntrcnFileBtn.append("<a href=javascript:fn_deleteFileList('");
            eduIntrcnFileBtn.append(mvmnPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("','");
            eduIntrcnFileBtn.append(result.getEduIntrcnFileIdntfcKey());
            eduIntrcnFileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("eduIntrcnFileBtn", eduIntrcnFileBtn.toString());
            
        }
        //교육사진 첨부파일
        //if(mvmnPrgrmVo.getEduPhotoFileid() != 0 && result.getEduPhotoFileIdntfcKey() != null) {
        if(result.getEduPhotoFileIdntfcKey() != null) {
            StringBuffer eduPhotoFileBtn = new StringBuffer();
            
            eduPhotoFileBtn.append("<div class ='label label-inverse text-white' id='");
            eduPhotoFileBtn.append(mvmnPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("'>");
            
            eduPhotoFileBtn.append("<a href=javascript:downloadFileByFileid('");
            eduPhotoFileBtn.append(mvmnPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("','");
            eduPhotoFileBtn.append(result.getEduPhotoFileIdntfcKey());
            eduPhotoFileBtn.append("') class='text-white'>");
            eduPhotoFileBtn.append(result.getEduPhotoOrginlFileNm());
            eduPhotoFileBtn.append("&nbsp;&nbsp;</a>");
            
            eduPhotoFileBtn.append("<a href=javascript:fn_deleteFileList('");
            eduPhotoFileBtn.append(mvmnPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("','");
            eduPhotoFileBtn.append(result.getEduPhotoFileIdntfcKey());
            eduPhotoFileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("eduPhotoFileBtn", eduPhotoFileBtn.toString());
            
        }
        model.addAttribute("mvmnPrgrm", result);
        model.addAttribute("resultTmeList", resultTmeList);
        
        return "mng/prtpn/mvmnPrgrm/mvmnPrgrmUpdate";
    }
   
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/selectMvmnPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnPrgrmList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnPrgrmVo> result = null;
        result =  mvmnPrgrmService.selectMvmnPrgrmList(mvmnPrgrmVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 게시글 등록 기능
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 수정 기능
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/insertMvmnPrgrm.do")
    @ResponseBody
    public Map<String, Object> insertMvmnPrgrm(@Valid @RequestBody MvmnPrgrmVo mvmnPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = mvmnPrgrmService.insertMvmnPrgrm(mvmnPrgrmVo);
        
//        retVal = mvmnPrgrmService.insertMvmnPrgrmTme(mvmnPrgrmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
            resultMap.put("prgrmId", mvmnPrgrmVo.getPrgrmId());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
            resultMap.put("prgrmId", 0);
        }
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 게시글 복사 기능
    **
    @Title : insertMvmnPrgrmCopy
    * @Description : 교육프로그램관리 게시글 복사 기능
    * @param copyPrgrmIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/insertMvmnPrgrmCopy.do")
    @ResponseBody
    public Map<String, Object> insertMvmnPrgrmCopy(@RequestParam("copyPrgrmIds") String[] copyPrgrmIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = mvmnPrgrmService.insertMvmnPrgrmCopy(copyPrgrmIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "복사에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "복사에 실패했습니다.");
        }

        return reseultMap;
    }

    
    /**
    * 교육프로그램관리 게시글 수정 기능
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 수정 기능
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/updateMvmnPrgrm.do")
    @ResponseBody
    public Map<String, Object> updateMvmnPrgrm(@Valid @RequestBody MvmnPrgrmVo mvmnPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = mvmnPrgrmService.updateMvmnPrgrm(mvmnPrgrmVo);
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
    * 교육프로그램 회차 리스트 등록 기능
    **
    @Title : insertMvmnPrgrmTmeList
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param mvmnPrgrmVoList
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return List<Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/insertMvmnPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> insertMvmnPrgrmTmeList(@RequestBody MvmnPrgrmVo mvmnPrgrmVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        mvmnPrgrmVo.setUser(user);

        int retVal = 0;
        retVal = mvmnPrgrmService.insertMvmnPrgrmTme(mvmnPrgrmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;        
    }

    /**
    * 교육프로그램 회차 리스트 등록 기능
    **
    @Title : insertMvmnPrgrmTme
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param user
    * @param mvmnPrgrmVoParam
    * @return
    * @throws Exception
    * @return Map<String,String>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/insertMvmnPrgrmTme.do")
    @ResponseBody
    public Map<String, String> insertMvmnPrgrmTme(@UserInfo UserVo user, @RequestBody MvmnPrgrmVo mvmnPrgrmVoParam) throws Exception {

        Map<String, String> response = new HashMap<String, String>();
        mvmnPrgrmVoParam.setUser(user);
        int retVal = 0;
        retVal = mvmnPrgrmService.insertMvmnPrgrmTme(mvmnPrgrmVoParam);
        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return response;
    }

    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/selectMvmnPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnPrgrmTmeList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnPrgrmVo> result = null;
        result =  mvmnPrgrmService.selectMvmnPrgrmTmeList(mvmnPrgrmVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("mvmnPrgrmTmeList", result);
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnPrgrm/selectPrgrmSettingList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmSettingList(@RequestParam String operFomCd, @RequestParam Integer sareaId) throws Exception {
        List<MvmnPrgrmVo> result = null;
        result =  mvmnPrgrmService.selectPrgrmSettingList(operFomCd, sareaId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("prgrmSttList", result);
        return resultMap;
            
    }

    private static List<Integer> getYears() {
        int srcYear = 2022;
        int dstYear = LocalDate.now().getYear() + 3;
        List<Integer> years = new ArrayList<>();

        for( int i= srcYear; i<= dstYear; i++) {
            years.add(i);
        }
        return years;
    }
}
