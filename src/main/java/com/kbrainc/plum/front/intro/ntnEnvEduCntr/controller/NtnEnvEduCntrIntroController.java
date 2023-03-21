package com.kbrainc.plum.front.intro.ntnEnvEduCntr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 국가환경교육센터 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.ntnEnvEduCntr.controller
 * - NtnEnvEduCntrController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : NtnEnvEduCntrController
 * @Description : 국가환경교육센터 컨트롤러 클래스
 * @date : 2023. 03. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/intro/ntnEnvEduCntr")
public class NtnEnvEduCntrIntroController {
    /**
     * 인사말
     *
     * @return string
     * @throws Exception
     * @Title : intro1
     * @Description : 인사말
     */
    @GetMapping("/intro1.html")
    public String intro1() throws Exception {
        return "/front/intro/ntnEnvEduCntr/intro1";
    }

    /**
     * 조직 및 업무
     *
     * @return string
     * @throws Exception
     * @Title : intro2
     * @Description : 조직 및 업무
     */
    @GetMapping("/intro2.html")
    public String intro2() throws Exception {
        return "/front/intro/ntnEnvEduCntr/intro2";
    }

    /**
     * 비전 및 목표
     *
     * @return string
     * @throws Exception
     * @Title : intro3
     * @Description : 비전 및 목표
     */
    @GetMapping("/intro3.html")
    public String intro3() throws Exception {
        return "/front/intro/ntnEnvEduCntr/intro3";
    }

    /**
     * 찾아오시는길
     *
     * @return string
     * @throws Exception
     * @Title : intro4
     * @Description : 찾아오시는길
     */
    @GetMapping("/intro4.html")
    public String intro4() throws Exception {
        return "/front/intro/ntnEnvEduCntr/intro4";
    }

}
