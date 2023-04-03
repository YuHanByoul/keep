package com.kbrainc.plum.cmm.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportingController {
    
    @RequestMapping("/cmm/reporting/reportDetail.html")
    public String reportDetail(@RequestParam String fileName, @RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/"+fileName+".html";
    }
}
