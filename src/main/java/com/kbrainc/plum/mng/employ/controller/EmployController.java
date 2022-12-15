package com.kbrainc.plum.mng.employ.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.bbs.model.BbsVo;
import com.kbrainc.plum.mng.bbs.service.BbsService;
import com.kbrainc.plum.mng.employ.model.EmployVo;
import com.kbrainc.plum.mng.employ.service.EmployService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class EmployController {

    private static final int EMPLOY_BBSID = 12;

    @Autowired
    private EmployService employService;

    @Autowired
    private BbsService bbsService;

    @RequestMapping("/mng/employ/employForm.html")
    public String employForm() throws Exception {
        return "mng/employ/employForm";
    }

    @RequestMapping("/mng/employ/selectEmployList.do")
    @ResponseBody
    public Map<String,Object> selectEmployList(EmployVo employVo, @UserInfo UserVo user) throws Exception
    {
        employVo.setUser(user);
        employVo.setBbsid(EMPLOY_BBSID);

        List<EmployVo> list = employService.selectEmployList(employVo);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/employ/employDetailForm.html")
    public String employDetailForm(EmployVo employVo, Model model) throws Exception
    {
        EmployVo employInfo = employService.selectEmployInfo(employVo);

        BbsVo paramVo = new BbsVo();
        paramVo.setBbsid(EMPLOY_BBSID);
        BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);

        if(employInfo == null) {
            employInfo = new EmployVo();
        }

        if(!StringUtil.nvl(employInfo.getFilegrpid()).equals("") && !StringUtil.nvl(employInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(employInfo.getFilegrpid());
            model.addAttribute("fileList",employService.selectAttachFileList(fileVo));
        }else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        model.addAttribute("employInfo", employInfo);
        model.addAttribute("bbsInfo",bbsInfo);
        return "mng/employ/employDetailForm";
    }

    @RequestMapping("/mng/employ/insertEmploy.do")
    @ResponseBody
    public Map<String,Object> insertEmploy(EmployVo employVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        employVo.setUser(user);

        int retVal = 0;
        retVal += employService.insertEmploy(employVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        return resultMap;
    }

    @RequestMapping("/mng/employ/updateEmploy.do")
    @ResponseBody
    public Map<String,Object> updateEmploy(EmployVo employVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String,Object> resultMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        employVo.setUser(user);

        int retVal = 0;
        retVal += employService.updateEmploy(employVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        return resultMap;
    }

    @RequestMapping("/mng/employ/deleteEmploy.do")
    @ResponseBody
    public Map<String,Object> deleteEmploy(@RequestParam("deleteEmployIds") String[] deleteEmployIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = employService.deleteEmploy(deleteEmployIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "삭제에 실패했습니다.");
        }

        return reseultMap;
    }
}
