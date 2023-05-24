package com.kbrainc.plum.mng.envtcherTrnngInst.controller;

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

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.mng.envtcherTrnngInst.service.EnvtcherTrnngInstService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 환경교육사 양성기관 현황 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.controller
* - EnvtcherTrnngInstController.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstController
* @Description : 환경교육사 양성기관 현황 컨트롤러 클래스
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EnvtcherTrnngInstController {
    
    @Autowired
    private EnvtcherTrnngInstService envtcherTrnngInstService;
    
    /**
    * 환경교육사 양성기관 현황으로 이동
    *
    * @Title : envtcherTrnngInstListForm
    * @Description : 환경교육사 양성기관 현황으로 이동
    * @param model 객체
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstListForm.html")
    public String envtcherTrnngInstListForm(Model model, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        List<EnvtcherTrnngInstVo> result = null;
        result =  envtcherTrnngInstService.selectAddrCtpvnList(envtcherTrnngInstVo);
        model.addAttribute("ctprvn", result);
        
        return "/mng/envtcherTrnngInst/envtcherTrnngInstList";
    }
    
    /**
    * 환경교육사 양성기관 등록으로 이동
    *
    * @Title : envtcherTrnngInstInsertForm
    * @Description : 환경교육사 양성기관 등록으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstInsertForm.html")
    public String envtcherTrnngInstInsertForm() throws Exception {
        return "/mng/envtcherTrnngInst/envtcherTrnngInstInsertForm";
    }
    
    /**
    * 기관검색 팝업으로 이동
    *
    * @Title : instSearchPopup
    * @Description : 기관검색 팝업으로 이동
    * @param model 객체
    * @param request 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/instSearchPopup.html")
    public String instSearchPopup(Model model, HttpServletRequest request) throws Exception {
        String instNm = request.getParameter("instNm");
        model.addAttribute("instNm", instNm);
        
        return "mng/envtcherTrnngInst/instSearchPopup";
    }
    
    /**
    * 환경교육사 양성기관 수정화면으로 이동
    *
    * @Title : envtcherTrnngInstUpdateForm
    * @Description : 환경교육사 양성기관 수정화면으로 이동
    * @param model 객체
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstUpdateForm.html")
    public String envtcherTrnngInstUpdateForm(Model model, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        EnvtcherTrnngInstVo result = null;
        result =  envtcherTrnngInstService.selectEnvtcherTrnngInstInfo(envtcherTrnngInstVo);
        model.addAttribute("envtcherTrnngInst", result);
        
        if(result.getThmbnFileid() != null && !result.getThmbnFileid().equals(0)) {
            StringBuffer fileBtn = new StringBuffer();
            
            fileBtn.append("<div class ='label label-inverse text-white' id='");
            fileBtn.append(envtcherTrnngInstVo.getThmbnFileid());
            fileBtn.append("'>");
            
            fileBtn.append("<a href=javascript:downloadFileByFileid('");
            fileBtn.append(envtcherTrnngInstVo.getThmbnFileid());
            fileBtn.append("','");
            fileBtn.append(result.getFileIdntfcKey());
            fileBtn.append("') class='text-white'>");
            fileBtn.append(result.getOrginlFileNm());
            fileBtn.append("&nbsp;&nbsp;</a>");
            
            fileBtn.append("<a href=javascript:fn_deleteFileList('");
            fileBtn.append(envtcherTrnngInstVo.getThmbnFileid());
            fileBtn.append("','");
            fileBtn.append(result.getFileIdntfcKey());
            fileBtn.append("') class='text-white'>X</a></div>");
            
            model.addAttribute("fileBtn", fileBtn);
        }
        
        return "/mng/envtcherTrnngInst/envtcherTrnngInstUpdate";
    }
    
    /**
    * 환경교육사 양성기관 목록 조회
    *
    * @Title : selectEnvtcherTrnngInstList
    * @Description : 환경교육사 양성기관 목록 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/selectEnvtcherTrnngInstList.do")
    @ResponseBody
    public Map<String, Object> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvtcherTrnngInstVo> result = null;
        
        result =  envtcherTrnngInstService.selectEnvtcherTrnngInstList(envtcherTrnngInstVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 환경교육사 양성기관 등록 기능
    *
    * @Title : insertEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 등록 기능
    * @param envtcherTrnngInstVo 객체
    * @param bindingResult 유효성 검증 결과
    * @param user 유저 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/insertEnvtcherTrnngInst.do")
    @ResponseBody
    public Map<String, Object> insertEnvtcherTrnngInst(@Valid EnvtcherTrnngInstVo envtcherTrnngInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        envtcherTrnngInstVo.setUser(user);

        int retVal = 0;
                
        retVal = envtcherTrnngInstService.insertEnvtcherTrnngInst(envtcherTrnngInstVo);
        
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
    * 환경교육사 양성기관 수정 기능
    *
    * @Title : updateEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 수정 기능
    * @param envtcherTrnngInstVo 객체
    * @param bindingResult 유효성 검증 결과
    * @param user 유저 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/updateEnvtcherTrnngInst.do")
    @ResponseBody
    public Map<String, Object> updateEnvtcherTrnngInst(@Valid EnvtcherTrnngInstVo envtcherTrnngInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        envtcherTrnngInstVo.setUser(user);

        int retVal = 0;
                
        retVal = envtcherTrnngInstService.updateEnvtcherTrnngInst(envtcherTrnngInstVo);
        
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
    * 엑셀다운로드
    *
    * @Title : envtcherTrnngInstExcelDownload
    * @Description : 엑셀다운로드
    * @param request 객체
    * @param response 객체
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return void
    */
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstExcelDownload.do")
    public void envtcherTrnngInstExcelDownload(HttpServletRequest request, HttpServletResponse response, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        envtcherTrnngInstService.envtcherTrnngInstExcelDownload(request, response, envtcherTrnngInstVo);
    } 
}
