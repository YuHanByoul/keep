package com.kbrainc.plum.mng.pvtEnveduGrp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;
import com.kbrainc.plum.mng.pvtEnveduGrp.service.PvtEnveduGrpService;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.controller
* - PvtEnveduGrp.java
* </pre>
*
* @ClassName : PvtEnveduGrp
* @Description : TODO
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class PvtEnveduGrpController {
    
    @Autowired
    private PvtEnveduGrpService pvtEnveduGrpService; 
    
    @RequestMapping(value = "/mng/pvtEnveduGrp/pvtEnveduGrpListForm.html")
    public String pvtEnveduGrpListForm(Model model, PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception {
        List<PvtEnvEduGrpVo> result = null;
        
        result =  pvtEnveduGrpService.selectAddrCtpvnList(pvtEnvEduGrpVo);
        model.addAttribute("ctprvn", result);   
        
        return "/mng/pvtEnveduGrp/pvtEnveduGrpList";
    }
    
    @RequestMapping(value = "/mng/pvtEnveduGrp/selectPvtEnveduGrpList.do")
    @ResponseBody
    public Map<String, Object> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PvtEnvEduGrpVo> result = null;
        
        result =  pvtEnveduGrpService.selectPvtEnveduGrpList(pvtEnvEduGrpVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/mng/pvtEnveduGrp/pvtEnveduGrpExcelDownload.do")
    public void pvtEnveduGrpExcelDownload(HttpServletRequest request, HttpServletResponse response, PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception {
        pvtEnveduGrpService.selectPvtEnveduGrpExcelDownload(pvtEnvEduGrpVo, response, request);
    } 
}
