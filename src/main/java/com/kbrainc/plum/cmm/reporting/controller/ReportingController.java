package com.kbrainc.plum.cmm.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 레포팅 컨트롤러
*
* <pre>
* com.kbrainc.plum.cmm.reporting.controller
* - ReportingController.java
* </pre>
*
* @ClassName : ReportingController
* @Description : 레포팅 컨트롤러
* @author : JD
* @date : 2023. 4. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ReportingController {
    
    @RequestMapping("/cmm/reporting/sample_canvas.html")
    public String sample() throws Exception {
        
        return "cmm/reporting/sample_canvas.html";
    }
    
    /**
    * 우수프로그램 지정제사업 > 지정제 운영 관리 > 지정 프로그램 > 상세 > 지정정보 tab > 지정프로그램 개요보기 팝업
    *
    * @Title : dsgnPrgrmOutl
    * @Description : 우수프로그램 지정제사업 > 지정제 운영 관리 > 지정 프로그램 > 상세 > 지정정보 tab > 지정프로그램 개요보기 팝업
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/dsgnPrgrmOutl.html")
    public String dsgnPrgrmOutl(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/dsgnPrgrmOutl.html";
    }
    
    /**
    * 우수프로그램 지정제사업 > 지정제 운영 관리 > 지정 프로그램 > 상세 > 지정정보 tab > 지정프로그램 상세보기 팝업
    *
    * @Title : dsgnPrgrmDetail
    * @Description : 우수프로그램 지정제사업 > 지정제 운영 관리 > 지정 프로그램 > 상세 > 지정정보 tab > 지정프로그램 상세보기 팝업
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/dsgnPrgrmDetail.html")
    public String dsgnPrgrmDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/dsgnPrgrmDetail.html";
    }
    
    /**
    * 우수프로그램 지정제사업 > 컨설팅 관리 > 컨설팅 관리 상세 > 컨설팅 결과
    *
    * @Title : cnsltngResult
    * @Description : 우수프로그램 지정제사업 > 컨설팅 관리 > 컨설팅 관리 상세 > 컨설팅 결과
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/cnsltngResult.html")
    public String cnsltngResult(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult.html";
    }
    
    /**
    * 체험환경교육 지원사업 > 사업신청관리 > 신청관리 > 상세 > 프로그램 상세보기 팝업
    *
    * @Title : prgrmDetail
    * @Description : 체험환경교육 지원사업 > 사업신청관리 > 신청관리 > 상세 > 프로그램 상세보기 팝업
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/prgrmDetail.html")
    public String prgrmDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/prgrmDetail.html";
    }
    
    /**
    * 체험환경교육 지원사업 > 교부관리 > 상세 > 교부신청서 모달 팝업
    *
    * @Title : pcntstDelvryAply
    * @Description : 체험환경교육 지원사업 > 교부관리 > 상세 > 교부신청서 모달 팝업
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/pcntstDelvryAply.html")
    public String pcntstDelvryAply(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/pcntstDelvryAply.html";
    }
    
    /**
    * 체험환경교육 지원사업 > 사업보고 관리 > 컨설팅관리 > 상세 온라인
    *
    * @Title : cnsltngResult_online
    * @Description : 체험환경교육 지원사업 > 사업보고 관리 > 컨설팅관리 > 상세 온라인
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/cnsltngResult_online.html")
    public String cnsltngResult_online(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult_online.html";
    }
    
    /**
    * 체험환경교육 지원사업 > 사업보고 관리 > 컨설팅관리 > 상세 오프라인
    *
    * @Title : reportDetail
    * @Description : 체험환경교육 지원사업 > 사업보고 관리 > 컨설팅관리 > 상세 오프라인
    * @param id
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping("/cmm/reporting/cnsltngResult_offline.html")
    public String reportDetail(@RequestParam String id, Model model) throws Exception {
        model.addAttribute("id", id);
        
        return "cmm/reporting/cnsltngResult_offline.html";
    }
}
