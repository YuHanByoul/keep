package com.kbrainc.plum.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.example.model.PrgrmVo;
import com.kbrainc.plum.example.service.ExampleService;

/**
* 예제 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.example.controller
* - ExampleController.java
* </pre>
*
* @ClassName   : ExampleController 
* @Description : 예제 컨트롤러 클래스 
* @author      : KBRAINC
* @date        : 2022. 9. 28.
* @Version     : 
* @Company     : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ExampleController {
    
    @Autowired
    private ExampleService exampleService;
    
    @RequestMapping(value = "/example/index.html")
    public String index(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "예제 Index");
        request.setAttribute("MENU_NAVI", "예제 / Index");
        
        return "example/index";
    }
    
    @RequestMapping(value = "example/validation.html")
    public String validation(HttpServletRequest request, Model model) {
        request.setAttribute("MENU_NAME", "폼유효성체크(관리자)");
        request.setAttribute("MENU_NAVI", "예제 / 폼유효성체크(관리자)");
        
        return "example/validation";
    }
    
    @RequestMapping(value = "example/validation2.html")
    public String validation2(HttpServletRequest request, Model model) {
        request.setAttribute("MENU_NAME", "폼유효성체크(사용자)");
        request.setAttribute("MENU_NAVI", "예제 / 폼유효성체크(사용자)");
        
        return "example/validation2";
    }
    
    @RequestMapping(value = "example/customTag.html")
    public String customTag(HttpServletRequest request, Model model) {
        request.setAttribute("MENU_NAME", "커스텀 태그(thymeleaf)");
        request.setAttribute("MENU_NAVI", "예제 / 커스텀 태그(thymeleaf)");

        model.addAttribute("grpcd", "G0001");
        model.addAttribute("selectedCd", "A0002");
        return "example/customTag";
    }
    
    /**
    * 샘플화면으로 이동한다. 
    *
    * @Title : temp
    * @Description : 샘플화면으로 이동한다.
    * @param prgrmVo PrgrmVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/example/submitPaging.html")
    public String syncPaging(PrgrmVo prgrmVo, HttpServletRequest request, Model model) throws Exception {
        request.setAttribute("MENU_NAME", "페이징(submit)");
        request.setAttribute("MENU_NAVI", "예제 / 페이징(submit)");
        
        List<PrgrmVo> result = exampleService.selectPrgrmList(prgrmVo);
        model.addAttribute("list", result); // 페이징 데이터
        return "example/submitPaging";
    }
    
    @RequestMapping(value = "/example2/fileUpDown.html")
    public String fileUpDown(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "파일업로드 및 다운로드");
        request.setAttribute("MENU_NAVI", "예제 / 파일업로드 및 다운로드");
        
        return "example/fileUpDown";
    }
    
    @RequestMapping(value = "/example/editor.html")
    public String editor(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "에디터(CKEditor4)");
        request.setAttribute("MENU_NAVI", "예제 / 에디터(CKEditor4)");
        
        return "example/ckeditor4";
    }
    
    @RequestMapping(value = "/example/grid.html")
    public String grid(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "그리드(jsGrid)");
        request.setAttribute("MENU_NAVI", "예제 / 그리드(jsGrid)");
        
        return "example/jsgrid";
    }
    
    @RequestMapping(value = "/example/excel.html")
    public String excel(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "엑셀 일괄등록 및 다운로드");
        request.setAttribute("MENU_NAVI", "예제 / 엑셀 일괄등록 및 다운로드");
        
        return "example/excel";
    }
    
    @RequestMapping(value = "/example/serverValidation.html")
    public String serverValidation(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "서버 validation");
        request.setAttribute("MENU_NAVI", "예제 / 서버 validation");
        
        return "example/serverValidation";
    }
    
    @RequestMapping(value = "/example/sendMail.html")
    public String sendMail(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "메일발송");
        request.setAttribute("MENU_NAVI", "예제 / 메일발송");
        
        return "example/sendMail";
    }
    
    @RequestMapping(value = "/example/sendSms.html")
    public String sendSms(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "SMS발송");
        request.setAttribute("MENU_NAVI", "예제 / SMS발송");
        
        return "example/sendSms";
    }
    
    @RequestMapping(value = "/example/compress.html")
    public String compress(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "압축/압축해제");
        request.setAttribute("MENU_NAVI", "예제>압축 / 압축해제");
        
        return "example/compress";
    }
    
    @RequestMapping(value = "/example/encrypt.html")
    public String encrypt(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "문자열 암호화");
        request.setAttribute("MENU_NAVI", "예제 / 문자열 암호화");
        
        return "example/encrypt";
    }
    
    @RequestMapping(value = "/example/layout.html")
    public String layout(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "레이아웃(thymeleaf)");
        request.setAttribute("MENU_NAVI", "예제 / 레이아웃(thymeleaf)");
        
        return "example/layout";
    }
    
    @RequestMapping(value = "/example/formSubmit.html")
    public String formSubmit(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "form submit");
        request.setAttribute("MENU_NAVI", "예제 / form submit");
        
        return "example/formSubmit";
    }
    
    @RequestMapping(value = "/example/spinner.html")
    public String spinner(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "spinner");
        request.setAttribute("MENU_NAVI", "예제 / spinner");
        
        return "example/spinner";
    }
    
    @RequestMapping(value = "/example/authUiUx.html")
    public String authUiUx(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "접근권한 View단 처리");
        request.setAttribute("MENU_NAVI", "예제 / 접근권한 View단 처리");
        
        return "example/authUiUx";
    }
    
    @RequestMapping(value = "/example/code.html")
    public String code(HttpServletRequest request) throws Exception {
        request.setAttribute("MENU_NAME", "code 사용");
        request.setAttribute("MENU_NAVI", "예제 / code 사용");
        
        return "example/code";
    }
}