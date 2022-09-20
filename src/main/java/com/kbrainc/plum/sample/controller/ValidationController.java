package com.kbrainc.plum.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vc")
public class ValidationController {

    @GetMapping("/test.html")
    public String testValidattion(Model model, ModelAndView mav) {
        model.addAttribute("grpcd", "G0001");
        return "vcheck";
    }

}
