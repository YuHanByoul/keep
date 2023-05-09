package com.kbrainc.plum.mng.envWord.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.kbrainc.plum.mng.envWord.model.EnvWordVo;
import com.kbrainc.plum.mng.envWord.service.EnvWordService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 환경교육용어사전 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.envWord.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : EnvWordController
* @Description : 환경교육용어사전 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EnvWordController {
    
    @Autowired
    private EnvWordService envWordService;
    
    /**
    * 환경교육용어사전 리스트화면으로 이동
    *
    * @Title : envWordForm
    * @Description : 환경교육용어사전 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envWord/envWordListForm.html")
    public String envWordListForm() throws Exception {
        return "mng/envWord/envWordList";
    }
    
    /**
    * 환경교육용어사전 등록화면으로 이동
    *
    * @Title : envWordInsertForm
    * @Description : 환경교육용어사전 등록화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envWord/envWordInsertForm.html")
    public String envWordInsertForm(Model model, @UserInfo UserVo user) throws Exception {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        model.addAttribute("regDate", formatter.format(date));
        
        model.addAttribute("userid", user.getUserid());
        model.addAttribute("useridNm", user.getAcnt()+"("+user.getNm()+")");
        
        return "mng/envWord/envWordInsertForm";
    }
    
    /**
    * 환경교육용어사전 수정화면으로 이동
    *
    * @Title : envWordUpdateForm
    * @Description : 환경교육용어사전 수정화면으로 이동
    * @param envWordVo 환경교육용어사전 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/envWord/envWordUpdateForm.html")
    public String envWordUpdateForm(EnvWordVo envWordVo, Model model) throws Exception {
        model.addAttribute("envWord", envWordService.selectEnvWordInfo(envWordVo));
        
        return "mng/envWord/envWordUpdate";
    }
   
    /**
    * 환경교육용어사전 리스트 기능
    *
    * @Title : selectEnvWordList
    * @Description : 환경교육용어사전 리스트 기능
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envWord/selectEnvWordList.do")
    @ResponseBody
    public Map<String, Object> selectEnvWordList(EnvWordVo envWordVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvWordVo> result = null;
        
        result =  envWordService.selectEnvWordList(envWordVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 환경교육용어사전 등록 기능
    *
    * @Title : insertEnvWord
    * @Description : 환경교육용어사전 등록 기능
    * @param envWordVo 환경교육용어사전 객체
    * @param bindingResult 환경교육용어사전 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envWord/insertEnvWord.do")
    @ResponseBody
    public Map<String, Object> insertEnvWord(@Valid EnvWordVo envWordVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        envWordVo.setUser(user);

        int retVal = 0;
                
        retVal = envWordService.insertEnvWord(envWordVo);
        
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
    * 환경교육용어사전 수정 기능
    *
    * @Title : updateEnvWord
    * @Description : 환경교육용어사전 수정 기능
    * @param envWordVo 환경교육용어사전 객체
    * @param bindingResult 환경교육용어사전 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envWord/updateEnvWord.do")
    @ResponseBody
    public Map<String, Object> updateEnvWord(@Valid EnvWordVo envWordVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        envWordVo.setUser(user);

        int retVal = 0;
                
        retVal = envWordService.updateEnvWord(envWordVo);
        
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
    * 환경교육용어사전 삭제 기능
    *
    * @Title : deleteEnvWord
    * @Description : 환경교육용어사전 글 목록 삭제 기능
    * @param request 글 id값
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/envWord/deleteEnvWord.do")
    @ResponseBody
    public Map<String, Object> deleteEnvWord(EnvWordVo envWordVo, HttpServletRequest request, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        String[] ids = request.getParameterValues("nscvrgids");
        envWordVo.setWordids(ids);
        envWordVo.setUser(user);
        
        retVal = envWordService.deleteEnvWord(envWordVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
    
}
