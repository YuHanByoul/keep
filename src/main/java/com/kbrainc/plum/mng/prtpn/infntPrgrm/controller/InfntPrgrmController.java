package com.kbrainc.plum.mng.prtpn.infntPrgrm.controller;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.service.EduClssRmService;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.service.InfntPrgrmService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 유아환경교육 -> 교육프로그램관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.controller
* - InfntPrgrmController.java
* </pre>
**
@ClassName : InfntPrgrmController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class InfntPrgrmController {
    
    @Autowired
    private InfntPrgrmService infntPrgrmService;
    
    @Autowired
    private EduClssRmService eduClssRmService; 
    
    /**
    * 교육프로그램관리 리스트화면으로 이동
    *
    * @Title : infntPrgrmListForm
    * @Description : 교육프로그램관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmListForm.html")
    public String infntPrgrmListForm(Model model, HttpServletRequest request) throws Exception {
        
        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        return "mng/prtpn/infntPrgrm/infntPrgrmList";
    }
    
    /**
    * 교육프로그램관리 등록화면으로 이동
    *
    * @Title : infntPrgrmInsertForm
    * @Description : 교육프로그램관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmInsertForm.html")
    public String infntPrgrmInsertForm(Model model) throws Exception {

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        model.addAttribute("aplcntDgstfnSrvyList", infntPrgrmService.selectAplcntDgstfnSrvyList());
        model.addAttribute("stdntDgstfnSrvyList", infntPrgrmService.selectStdntDgstfnSrvyList());
        
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        return "mng/prtpn/infntPrgrm/infntPrgrmInsertForm";
    }
    
    /**
    * 교육프로그램관리 수정화면으로 이동
    *
    * @Title : infntPrgrmUpdateForm
    * @Description : 교육프로그램관리 수정화면으로 이동
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmUpdateForm.html")
    public String infntPrgrmUpdateForm(InfntPrgrmVo infntPrgrmVo, Model model) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        model.addAttribute("aplcntDgstfnSrvyList", infntPrgrmService.selectAplcntDgstfnSrvyList());
        model.addAttribute("stdntDgstfnSrvyList", infntPrgrmService.selectStdntDgstfnSrvyList());
        
        InfntPrgrmVo result = null;
        List<InfntPrgrmVo> resultTmeList = null;
        
        result = infntPrgrmService.selectInfntPrgrmInfo(infntPrgrmVo);
        resultTmeList =  infntPrgrmService.selectInfntPrgrmTmeList(infntPrgrmVo);
        
        //교육소개 첨부파일
        //if(infntPrgrmVo.getEduIntrcnFileid() != 0 && result.getEduIntrcnFileIdntfcKey() != null) {
        if(result.getEduIntrcnFileIdntfcKey() != null) {
            StringBuffer eduIntrcnFileBtn = new StringBuffer();
            eduIntrcnFileBtn.append("<div class ='label label-inverse text-white' id='");
            eduIntrcnFileBtn.append(infntPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("'>");
            
            eduIntrcnFileBtn.append("<a href=javascript:downloadFileByFileid('");
            eduIntrcnFileBtn.append(infntPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("','");
            eduIntrcnFileBtn.append(result.getEduIntrcnFileIdntfcKey());
            eduIntrcnFileBtn.append("') class='text-white'>");
            eduIntrcnFileBtn.append(result.getEduIntrcnOrginlFileNm());
            eduIntrcnFileBtn.append("&nbsp;&nbsp;</a>");
            
            eduIntrcnFileBtn.append("<a href=javascript:fn_deleteFileList('");
            eduIntrcnFileBtn.append(infntPrgrmVo.getEduIntrcnFileid());
            eduIntrcnFileBtn.append("','");
            eduIntrcnFileBtn.append(result.getEduIntrcnFileIdntfcKey());
            eduIntrcnFileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("eduIntrcnFileBtn", eduIntrcnFileBtn.toString());            
        }
        //교육사진 첨부파일
        //if(infntPrgrmVo.getEduPhotoFileid() != 0 && result.getEduPhotoFileIdntfcKey() != null) {
        if(result.getEduPhotoFileIdntfcKey() != null) {
            StringBuffer eduPhotoFileBtn = new StringBuffer();
            eduPhotoFileBtn.append("<div class ='label label-inverse text-white' id='");
            eduPhotoFileBtn.append(infntPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("'>");
            
            eduPhotoFileBtn.append("<a href=javascript:downloadFileByFileid('");
            eduPhotoFileBtn.append(infntPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("','");
            eduPhotoFileBtn.append(result.getEduPhotoFileIdntfcKey());
            eduPhotoFileBtn.append("') class='text-white'>");
            eduPhotoFileBtn.append(result.getEduPhotoOrginlFileNm());
            eduPhotoFileBtn.append("&nbsp;&nbsp;</a>");
            
            eduPhotoFileBtn.append("<a href=javascript:fn_deleteFileList('");
            eduPhotoFileBtn.append(infntPrgrmVo.getEduPhotoFileid());
            eduPhotoFileBtn.append("','");
            eduPhotoFileBtn.append(result.getEduPhotoFileIdntfcKey());
            eduPhotoFileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("eduPhotoFileBtn", eduPhotoFileBtn.toString());
        }
        model.addAttribute("infntPrgrm", result);
        model.addAttribute("resultTmeList", resultTmeList);
        
        return "mng/prtpn/infntPrgrm/infntPrgrmUpdate";
    }
   
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/selectInfntPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectInfntPrgrmList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntPrgrmVo> result = null;
        result =  infntPrgrmService.selectInfntPrgrmList(infntPrgrmVo);
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
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 수정 기능
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrm.do")
    @ResponseBody
    public Map<String, Object> insertInfntPrgrm(@Valid @RequestBody InfntPrgrmVo infntPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.insertInfntPrgrm(infntPrgrmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
            resultMap.put("prgrmId", infntPrgrmVo.getPrgrmId());
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
    @Title : insertInfntPrgrmCopy
    * @Description : 교육프로그램관리 게시글 복사 기능
    * @param copyPrgrmIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrmCopy.do")
    @ResponseBody
    public Map<String, Object> insertInfntPrgrmCopy(@RequestParam("copyPrgrmIds") String[] copyPrgrmIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = infntPrgrmService.insertInfntPrgrmCopy(copyPrgrmIds, user);

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
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정 기능
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/updateInfntPrgrm.do")
    @ResponseBody
    public Map<String, Object> updateInfntPrgrm(@Valid @RequestBody InfntPrgrmVo infntPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.updateInfntPrgrm(infntPrgrmVo);
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
    @Title : insertInfntPrgrmTmeList
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param infntPrgrmVoList
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return List<Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> insertInfntPrgrmTmeList(@RequestBody InfntPrgrmVo infntPrgrmVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        infntPrgrmVo.setUser(user);

        int retVal = 0;
        retVal = infntPrgrmService.insertInfntPrgrmTme(infntPrgrmVo);
        
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
    @Title : insertInfntPrgrmTme
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param user
    * @param infntPrgrmVoParam
    * @return
    * @throws Exception
    * @return Map<String,String>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrmTme.do")
    @ResponseBody
    public Map<String, String> insertInfntPrgrmTme(@UserInfo UserVo user, @RequestBody InfntPrgrmVo infntPrgrmVoParam) throws Exception {

        Map<String, String> response = new HashMap<String, String>();
        infntPrgrmVoParam.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.insertInfntPrgrmTme(infntPrgrmVoParam);
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
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/selectInfntPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntPrgrmVo> result = null;
        result =  infntPrgrmService.selectInfntPrgrmTmeList(infntPrgrmVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("infntPrgrmTmeList", result);
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/selectPrgrmSettingList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmSettingList(String rcptMthdCd) throws Exception {
        List<InfntPrgrmVo> result = null;
        result =  infntPrgrmService.selectPrgrmSettingList(rcptMthdCd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("prgrmSttList", result);
        return resultMap;
            
    }
    
}
