package com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.controller;

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

import com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model.CarbonPrgrmgdVo;
import com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.service.CarbonPrgrmgdService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 탄소중립환경교육 -> 프로그램안내관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.controller
* - PrgrmgdController.java
* </pre>
*
* @ClassName : PrgrmgdController
* @Description : 환경교육NOW -> 프로그램안내관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CarbonPrgrmgdController {
    
    @Autowired
    private CarbonPrgrmgdService prgrmgdService;
    
    /**
    * 프로그램안내관리 리스트화면으로 이동
    *
    * @Title : prgrmgdListForm
    * @Description : 프로그램안내관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/prgrmgdListForm.html")
    public String prgrmgdListForm(Model model, HttpServletRequest request) throws Exception {
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        int curMonth = Integer.valueOf(DateTimeUtil.getMonth());
        model.addAttribute("curMonth", curMonth);
        
        String[] Month = new String[12];
        
        for(int i = 1, j = 0; i <= 12 && j < 12; i++, j++) {
            Month[j] = String.format( "%1$02d" , i );
        }
        model.addAttribute("month", Month);
        
        return "mng/wbzn/carbon/prgrmgd/prgrmgdList";
    }
    
    /**
    * 프로그램안내관리 등록화면으로 이동
    *
    * @Title : prgrmgdInsertForm
    * @Description : 프로그램안내관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/prgrmgdInsertForm.html")
    public String prgrmgdInsertForm(Model model) throws Exception {
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
        
        return "mng/wbzn/carbon/prgrmgd/prgrmgdInsertForm";
    }
    
    /**
    * 프로그램안내관리 수정화면으로 이동
    *
    * @Title : prgrmgdUpdateForm
    * @Description : 프로그램안내관리 수정화면으로 이동
    * @param prgrmgdVo 프로그램안내관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/prgrmgdUpdateForm.html")
    public String prgrmgdUpdateForm(CarbonPrgrmgdVo prgrmgdVo, Model model) throws Exception {
        CarbonPrgrmgdVo result = null;
        result = prgrmgdService.selectPrgrmgdInfo(prgrmgdVo);
        model.addAttribute("prgrmgd", result);
        
        int curYear = Integer.valueOf(DateTimeUtil.getYear());
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        if(prgrmgdVo.getThmbnFileid() != 0 && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            fileBtn.append("<div class ='label label-inverse text-white' id='" + prgrmgdVo.getThmbnFileid() + "'>");
            fileBtn.append("<a href=javascript:downloadFileByFileid('" + prgrmgdVo.getThmbnFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
            fileBtn.append("<a href=javascript:fn_deleteFileList('" + prgrmgdVo.getThmbnFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("fileBtn", fileBtn);
        }
        
        return "mng/wbzn/carbon/prgrmgd/prgrmgdUpdate";
    }
   
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/selectPrgrmgdList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmgdList(CarbonPrgrmgdVo prgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarbonPrgrmgdVo> result = null;
        
        result =  prgrmgdService.selectPrgrmgdList(prgrmgdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 프로그램안내관리 게시글 수정 기능
    *
    * @Title : insertPrgrmgd
    * @Description : 프로그램안내관리 수정 기능
    * @param prgrmgdVo 프로그램안내관리 객체
    * @param bindingResult 프로그램안내관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/insertPrgrmgd.do")
    @ResponseBody
    public Map<String, Object> insertPrgrmgd(@Valid CarbonPrgrmgdVo prgrmgdVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        prgrmgdVo.setUser(user);

        int retVal = 0;
                
        retVal = prgrmgdService.insertPrgrmgd(prgrmgdVo);
        
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
    * 프로그램안내관리 게시글 수정 기능
    *
    * @Title : updatePrgrmgd
    * @Description : 프로그램안내관리 게시글 수정 기능
    * @param prgrmgdVo 프로그램안내관리 객체
    * @param bindingResult 프로그램안내관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/carbon/prgrmgd/updatePrgrmgd.do")
    @ResponseBody
    public Map<String, Object> updatePrgrmgd(@Valid CarbonPrgrmgdVo prgrmgdVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        prgrmgdVo.setUser(user);

        int retVal = 0;
                
        retVal = prgrmgdService.updatePrgrmgd(prgrmgdVo);
        
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
