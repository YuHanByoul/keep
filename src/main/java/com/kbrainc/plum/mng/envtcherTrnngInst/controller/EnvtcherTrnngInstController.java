package com.kbrainc.plum.mng.envtcherTrnngInst.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.controller
* - EnvtcherTrnngInstController.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstController
* @Description : TODO
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EnvtcherTrnngInstController {
    
    @Autowired
    private EnvtcherTrnngInstService envtcherTrnngInstService;
    
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstListForm.html")
    public String envtcherTrnngInstListForm() throws Exception {
        
        return "/mng/envtcherTrnngInst/envtcherTrnngInstList";
    }
    
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstInsertForm.html")
    public String envtcherTrnngInstInsertForm() throws Exception {
        
        return "/mng/envtcherTrnngInst/envtcherTrnngInstInsertForm";
    }
    
    @RequestMapping(value = "/mng/envtcherTrnngInst/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        
        return "mng/envtcherTrnngInst/instSearchPopup";
    }
    
    @RequestMapping(value = "/mng/envtcherTrnngInst/envtcherTrnngInstUpdateForm.html")
    public String envtcherTrnngInstUpdateForm(Model model, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        EnvtcherTrnngInstVo result = null;
        result =  envtcherTrnngInstService.selectEnvtcherTrnngInstInfo(envtcherTrnngInstVo);
        model.addAttribute("envtcherTrnngInst", result);
        
        return "/mng/envtcherTrnngInst/envtcherTrnngInstUpdate";
    }
    
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
}
