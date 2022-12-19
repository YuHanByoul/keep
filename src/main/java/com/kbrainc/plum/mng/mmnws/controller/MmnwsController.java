package com.kbrainc.plum.mng.mmnws.controller;

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

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.mng.mmnws.service.MmnwsService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 언론보도관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : MmnwsController
* @Description : 언론보도관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class MmnwsController {
    
    @Autowired
    private MmnwsService mmnwsService;
    
    /**
    * 언론보도관리 리스트화면으로 이동
    *
    * @Title : mmnwsForm
    * @Description : 언론보도관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/mmnws/mmnwsListForm.html")
    public String mmnwsListForm() throws Exception {
        return "mng/mmnws/mmnwsList";
    }
    
    /**
    * 언론보도관리 등록화면으로 이동
    *
    * @Title : mmnwsInsertForm
    * @Description : 언론보도관리 등록화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/mmnws/mmnwsInsertForm.html")
    public String mmnwsInsertForm() throws Exception {
        return "mng/mmnws/mmnwsInsertForm";
    }
    
    /**
    * 언론보도관리 수정화면으로 이동
    *
    * @Title : mmnwsUpdateForm
    * @Description : 언론보도관리 수정화면으로 이동
    * @param mmnwsVo 언론보도관리 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/mmnws/mmnwsUpdateForm.html")
    public String mmnwsUpdateForm(MmnwsVo mmnwsVo, Model model) throws Exception {
        model.addAttribute("mmnws", mmnwsService.selectMmnwsInfo(mmnwsVo));
        
        return "mng/mmnws/mmnwsUpdate";
    }
   
    /**
    * 언론보도관리 리스트 기능
    *
    * @Title : selectMmnwsList
    * @Description : 언론보도관리 리스트 기능
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mmnws/selectMmnwsList.do")
    @ResponseBody
    public Map<String, Object> selectMmnwsList(MmnwsVo mmnwsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MmnwsVo> result = null;
        
        result =  mmnwsService.selectMmnwsList(mmnwsVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 언론보도관리 등록 기능
    *
    * @Title : insertMmnws
    * @Description : 언론보도관리 등록 기능
    * @param mmnwsVo 언론보도관리 객체
    * @param bindingResult 언론보도관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mmnws/insertMmnws.do")
    @ResponseBody
    public Map<String, Object> insertMmnws(@Valid MmnwsVo mmnwsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        mmnwsVo.setUser(user);

        int retVal = 0;
                
        retVal = mmnwsService.insertMmnws(mmnwsVo);
        
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
    * 언론보도관리 수정 기능
    *
    * @Title : updateMmnws
    * @Description : 언론보도관리 수정 기능
    * @param mmnwsVo 언론보도관리 객체
    * @param bindingResult 언론보도관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mmnws/updateMmnws.do")
    @ResponseBody
    public Map<String, Object> updateMmnws(@Valid MmnwsVo mmnwsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        mmnwsVo.setUser(user);

        int retVal = 0;
                
        retVal = mmnwsService.updateMmnws(mmnwsVo);
        
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
    * 언론보도관리 삭제 기능
    *
    * @Title : deleteMmnws
    * @Description : 언론보도관리 글 목록 삭제 기능
    * @param request 글 id값
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mmnws/deleteMmnws.do")
    @ResponseBody
    public Map<String, Object> deleteMmnws(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        String[] nscvrgids = request.getParameterValues("nscvrgids");
        
        retVal = mmnwsService.deleteMmnws(nscvrgids);
        
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
