package com.kbrainc.plum.front.intro.rgnEnvEduCntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 지역환경교육센터 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.rgnEnvEduCntr
 * - RgnEnvEduCntrController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : RgnEnvEduCntrController
 * @Description : 지역환경교육센터 컨트롤러 클래스
 * @date : 2023. 03. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class RgnEnvEduCntrIntroController {
    /**
     * 지역환경교육센터 소개
     *
     * @return string
     * @throws Exception
     * @Title : intro1
     * @Description : 지역환경교육센터 소개
     */
    @GetMapping("/front/intro/rgnEnvEduCntr/intro1.html")
    public String intro1() throws Exception {
        return "/front/intro/rgnEnvEduCntr/intro1";
    }

}
