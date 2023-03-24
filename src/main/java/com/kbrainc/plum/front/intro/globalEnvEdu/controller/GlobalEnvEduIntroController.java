package com.kbrainc.plum.front.intro.globalEnvEdu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 국제 환경교육 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.globalEnvEdu.controller
 * - GlobalEnvEduController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : GlobalEnvEduController
 * @Description : 국제 환경교육 컨트롤러 클래스
 * @date : 2023. 03. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class GlobalEnvEduIntroController {
    @GetMapping("/front/intro/globalEnvEdu/intro1.html")
    public String intro1() throws Exception{
        return "/front/intro/globalEnvEdu/intro1";
    }
}
