package com.kbrainc.plum.mng.qlityChk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.qlityChk.model.QlityChkArtclVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChkVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChklstVo;
import com.kbrainc.plum.mng.qlityChk.service.QlityChkService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResSiteService;

@Controller
public class QlityChkController {

    @Autowired
    private QlityChkService qlityChkService;
    
    @RequestMapping(value = "/mng/qlityChk/openCheckListPopup.html")
    public String openCheckListPopup(String type, String trgtCd, String cntntsid, Model model, HttpServletRequest request) throws Exception {
        if(type.equals("insert")) {
            List<QlityChklstVo> chkList =  qlityChkService.selectQlityChkList();
            model.addAttribute("chkList", chkList);
        }else {
            List<QlityChkArtclVo> chkArtclList =  qlityChkService.selectQlityChkArtclList(cntntsid);
            model.addAttribute("chkList", chkArtclList);
        }
        model.addAttribute("type", type);
        model.addAttribute("trgtCd", trgtCd);
        
        return "/mng/qlityChk/checkListPopup";
    }
    
    
    @RequestMapping(value = "/mng/qlityChk/insertQlityChkList.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertQlityChkList(String type, String trgtCd, QlityChkVo qlityChkVo, QlityChkArtclVo qlityChkArtclVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        qlityChkVo.setUser(userVo);
        qlityChkVo.setTrgtCd(trgtCd);
        if(type.equals("insert")) {
            qlityChkVo.setEvntCd("247001");
        }else {
            qlityChkVo.setEvntCd("247002");
        }
        
        qlityChkArtclVo.setUser(userVo);

        int retVal = 0;
        /* retVal = qlityChkService.insertQlityChkList(qlityChkVo, qlityChkArtclVo); */

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "체크리스트 등록에 실패했습니다.");
        }
        return resultMap;
    }
     
}
