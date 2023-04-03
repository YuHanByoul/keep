package com.kbrainc.plum.cmm.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportingController {
    
    @RequestMapping("/cmm/reporting/dsgnPrgrmOutl.html")
    public String dsgnPrgrmOutl(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/dsgnPrgrmOutl.html";
    }
    
    @RequestMapping("/cmm/reporting/dsgnPrgrmDetail.html")
    public String dsgnPrgrmDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/dsgnPrgrmDetail.html";
    }
    
    @RequestMapping("/cmm/reporting/cnsltngResult.html")
    public String cnsltngResult(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult.html";
    }
    
    @RequestMapping("/cmm/reporting/prgrmDetail.html")
    public String prgrmDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/prgrmDetail.html";
    }
    
    @RequestMapping("/cmm/reporting/pcntstDelvryAply.html")
    public String pcntstDelvryAply(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/pcntstDelvryAply.html";
    }
    
    @RequestMapping("/cmm/reporting/cnsltngResult_online.html")
    public String cnsltngResult_online(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult_online.html";
    }
    
    @RequestMapping("/cmm/reporting/cnsltngResult_offline.html")
    public String reportDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult_offline.html";
    }
}
