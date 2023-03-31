package com.kbrainc.plum.cmm.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.rte.util.CommonUtil;

@Controller
public class ReportingController {
    
    
    @RequestMapping("/reportingTest.html")
    public String reportingTest(Model model) throws Exception {
        model.addAttribute("host", CommonUtil.reportingUrl);
        
        return "cmm/reporting/sample_canvas.html";
    }
}
