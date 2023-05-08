package com.kbrainc.plum.mng.wbzn.carbon.envedu.controller;

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

import com.kbrainc.plum.mng.wbzn.carbon.envedu.model.CarbonEnveduVo;
import com.kbrainc.plum.mng.wbzn.carbon.envedu.service.CarbonEnveduService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 탄소중립환경교육 -> 환경교육관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.controller
* - EnveduController.java
* </pre>
*
* @ClassName : EnveduController
* @Description : 탄소중립환경교육 -> 환경교육관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CarbonEnveduController {
    
    @Autowired
    private CarbonEnveduService carbonEnveduService;
    
    /**
    * 환경교육관리 리스트화면으로 이동
    *
    * @Title : enveduListForm
    * @Description : 환경교육관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/enveduListForm.html")
    public String enveduListForm(Model model, HttpServletRequest request) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        return "mng/wbzn/carbon/envedu/enveduList";
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
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/enveduInsertForm.html")
    public String enveduInsertForm(Model model) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        return "mng/wbzn/carbon/envedu/enveduInsertForm";
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
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/enveduUpdateForm.html")
    public String enveduUpdateForm(CarbonEnveduVo enveduVo, Model model) throws Exception {
        CarbonEnveduVo result = null;
        result = carbonEnveduService.selectEnveduInfo(enveduVo);
        model.addAttribute("envedu", result);
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        if(enveduVo.getThmbnFileid() != 0 && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            
            fileBtn.append("<div class ='label label-inverse text-white' id='");
            fileBtn.append(enveduVo.getThmbnFileid());
            fileBtn.append("'>");
            
            fileBtn.append("<a href=javascript:downloadFileByFileid('");
            fileBtn.append(enveduVo.getThmbnFileid());
            fileBtn.append("','");
            fileBtn.append(result.getFileIdntfcKey());
            fileBtn.append("') class='text-white'>");
            fileBtn.append(result.getOrginlFileNm());
            fileBtn.append("&nbsp;&nbsp;</a>");
            
            fileBtn.append("<a href=javascript:fn_deleteFileList('");
            fileBtn.append(enveduVo.getThmbnFileid());
            fileBtn.append("','");
            fileBtn.append(result.getFileIdntfcKey());
            fileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("fileBtn", fileBtn);
        }
        
        return "mng/wbzn/carbon/envedu/enveduUpdate";
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
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/selectEnveduList.do")
    @ResponseBody
    public Map<String, Object> selectEnveduList(CarbonEnveduVo enveduVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarbonEnveduVo> result = null;
        
        result =  carbonEnveduService.selectEnveduList(enveduVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 환경교육관리 게시글 등록 기능
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 수정 기능
    * @param enveduVo 환경교육관리 객체
    * @param bindingResult 환경교육관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/insertEnvedu.do")
    @ResponseBody
    public Map<String, Object> insertEnvedu(@Valid CarbonEnveduVo enveduVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
                
        retVal = carbonEnveduService.insertEnvedu(enveduVo);
        
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
    @RequestMapping(value = "/mng/wbzn/carbon/envedu/updateEnvedu.do")
    @ResponseBody
    public Map<String, Object> updateEnvedu(@Valid CarbonEnveduVo enveduVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
                
        retVal = carbonEnveduService.updateEnvedu(enveduVo);
        
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
