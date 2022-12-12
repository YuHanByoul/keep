package com.kbrainc.plum.mng.wbzn.controller;

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

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.wbzn.model.EnveduVo;
import com.kbrainc.plum.mng.wbzn.service.EnveduService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

@Controller
public class EnveduController {
    
    @Autowired
    private EnveduService enveduService;
    
    @RequestMapping(value = "/mng/wbzn/enveduForm.html")
    public String enveduForm(Model model) throws Exception {
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
        
        return "mng/wbzn/envedu/enveduForm";
    }
    
    @RequestMapping(value = "/mng/wbzn/enveduInsertForm.html")
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
        
        return "mng/wbzn/envedu/enveduInsert";
    }
    
    @RequestMapping(value = "/mng/wbzn/enveduUpdateForm.html")
    public String enveduUpdateForm(EnveduVo enveduVo, Model model) throws Exception {
        model.addAttribute("envedu", enveduService.selectEnveduInfo(enveduVo));
        
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
        
        return "mng/wbzn/envedu/enveduUpdate";
    }
   
    
    @RequestMapping(value = "/mng/wbzn/selectEnveduList.do")
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
    
    @RequestMapping(value = "/mng/wbzn/insertEnvedu.do")
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
    
    @RequestMapping(value = "/mng/wbzn/updateEnvedu.do")
    @ResponseBody
    public Map<String, Object> updateMmnws(@Valid EnveduVo enveduVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
