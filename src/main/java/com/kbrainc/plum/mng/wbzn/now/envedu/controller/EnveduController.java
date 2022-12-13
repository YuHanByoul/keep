package com.kbrainc.plum.mng.wbzn.now.envedu.controller;

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

import com.kbrainc.plum.mng.wbzn.now.envedu.model.EnveduVo;
import com.kbrainc.plum.mng.wbzn.now.envedu.service.EnveduService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 환경교육NOW -> 환경교육관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.controller
* - EnveduController.java
* </pre>
*
* @ClassName : EnveduController
* @Description : 환경교육NOW -> 환경교육관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EnveduController {
    
    @Autowired
    private EnveduService enveduService;
    
    /**
    * 환경교육관리 리스트화면으로 이동
    *
    * @Title : enveduForm
    * @Description : 환경교육관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/enveduForm.html")
    public String enveduForm(Model model, HttpServletRequest request) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        String[] Month = new String[12];
        
        for(int i = 1, j = 0; i <= 12 && j < 12; i++, j++) {
            Month[j] = String.format( "%1$02d" , i );
        }
        model.addAttribute("month", Month);
        
        return "mng/wbzn/now/envedu/enveduForm";
    }
    
    /**
    * 환경교육관리 등록화면으로 이동
    *
    * @Title : enveduInsertForm
    * @Description : 환경교육관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/enveduInsertForm.html")
    public String enveduInsertForm(Model model) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        String[] Month = new String[12];
        
        for(int i = 1, j = 0; i <= 12 && j < 12; i++, j++) {
            Month[j] = String.format( "%1$02d" , i );
        }
        model.addAttribute("month", Month);
        
        return "mng/wbzn/now/envedu/enveduInsert";
    }
    
    /**
    * 환경교육관리 수정화면으로 이동
    *
    * @Title : enveduUpdateForm
    * @Description : 환경교육관리 수정화면으로 이동
    * @param enveduVo 환경교육관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/enveduUpdateForm.html")
    public String enveduUpdateForm(EnveduVo enveduVo, Model model) throws Exception {
        EnveduVo result = null;
        result = enveduService.selectEnveduInfo(enveduVo);
        model.addAttribute("envedu", result);
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        String[] Month = new String[12];
        
        for(int i = 1, j = 0; i <= 12 && j < 12; i++, j++) {
            Month[j] = String.format( "%1$02d" , i );
        }
        model.addAttribute("month", Month);
        
        if(enveduVo.getThmbnFileid() != 0 && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            fileBtn.append("<div class ='label label-inverse text-white' id='" + enveduVo.getThmbnFileid() + "'>");
            fileBtn.append("<a href=javascript:downloadFileByFileid('" + enveduVo.getThmbnFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
            fileBtn.append("<a href=javascript:fn_deleteFileList('" + enveduVo.getThmbnFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("fileBtn", fileBtn);
        }
        
        return "mng/wbzn/now/envedu/enveduUpdate";
    }
   
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/selectEnveduList.do")
    @ResponseBody
    public Map<String, Object> selectEnveduList(EnveduVo enveduVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnveduVo> result = null;
        
        result =  enveduService.selectEnveduList(enveduVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 환경교육관리 게시글 수정 기능
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 수정 기능
    * @param enveduVo 환경교육관리 객체
    * @param bindingResult 환경교육관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/insertEnvedu.do")
    @ResponseBody
    public Map<String, Object> insertEnvedu(@Valid EnveduVo enveduVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        enveduVo.setUser(user);

        int retVal = 0;
                
        retVal = enveduService.insertEnvedu(enveduVo);
        
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
    * 환경교육관리 게시글 수정 기능
    *
    * @Title : updateEnvedu
    * @Description : 환경교육관리 게시글 수정 기능
    * @param enveduVo 환경교육관리 객체
    * @param bindingResult 환경교육관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/now/envedu/updateEnvedu.do")
    @ResponseBody
    public Map<String, Object> updateEnvedu(@Valid EnveduVo enveduVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        enveduVo.setUser(user);

        int retVal = 0;
                
        retVal = enveduService.updateEnvedu(enveduVo);
        
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
