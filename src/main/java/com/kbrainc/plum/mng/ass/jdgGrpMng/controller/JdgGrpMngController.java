package com.kbrainc.plum.mng.ass.jdgGrpMng.controller;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.ui.Model;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.service.JdgGrpMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JdgGrpMngController {

    @Autowired
    private JdgGrpMngService jdgGrpMngService;

    @RequestMapping("/mng/ass/jdgGrpMngList.html")
    public String jdgGrpMngList() throws Exception {
        return "/mng/ass/jdgGrpMngList";
    }

    @RequestMapping("/mng/ass/jdgGrpDetail.html")
    public String jdgGrpMngDetail(JdgGrpVo jdgGrpVo, Model model) throws Exception {
        JdgGrpVo jdgGrpInfo = jdgGrpMngService.selectJdgGrpInfo(jdgGrpVo);
        if(jdgGrpInfo == null) jdgGrpInfo = new JdgGrpVo();
        model.addAttribute("jdgGrpInfo", jdgGrpInfo);
        return "mng/ass/jdgGrpDetail";
    }

    @RequestMapping("/mng/ass/selectJdgGrpList.do")
    @ResponseBody
    public Map<String, Object> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        List<JdgGrpVo> list = jdgGrpMngService.selectJdgGrpList(jdgGrpVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/ass/selectExpertList.do")
    @ResponseBody
    public Map<String, Object> selectExpertList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        List<JdgGrpExpertVo> list = jdgGrpMngService.selectJdgGrpExpertList(jdgGrpVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/ass/jdgGrpMngExpertModal.html")
    public String jdgGrpMngExpertModal() throws Exception {
        return "mng/ass/jdgGrpMngExpertModal";
    }

    @RequestMapping("/mng/ass/selectJdgGrpMngExpertSearchList.do")
    @ResponseBody
    public Map<String, Object> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        List<JdgGrpExpertVo> list = jdgGrpMngService.selectJdgGrpMngExpertSearchList(jdgGrpExpertVo);
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/ass/insertJdgGrpInfo.do")
    @ResponseBody
    public Map<String, Object> insertJdgGrpInfo(JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);

        int retVal = 0;

//        retVal = jdgGrpMngService.insertJdgGrpInfo(jdgGrpVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    @RequestMapping("/mng/ass/updateJdgGrpInfo.do")
    @ResponseBody
    public Map<String, Object> updateJdgGrpInfo(JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);
        
        int retVal = 0;

//        retVal = jdgGrpMngService.updateJdgGrpInfo(jdgGrpVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }


}
