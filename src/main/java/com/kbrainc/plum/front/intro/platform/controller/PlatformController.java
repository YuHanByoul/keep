package com.kbrainc.plum.front.intro.platform.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 국가환경교육 통합플랫폼 소개안내 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.platform.controller
 * - PlatformController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PlatformController
 * @Description : 국가환경교육 통합플랫폼 소개안내 컨트롤러 클래스
 * @date : 2023. 04. 18.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/intro/platform")
public class PlatformController {
    /**
     * 소개
     *
     * @return string
     * @throws Exception
     * @Title : intro1
     * @Description : 소개
     */
    @GetMapping("/intro1.html")
    public String intro1() throws Exception {
        return "/front/intro/platform/intro1";
    }

    /**
     * 이용방법
     *
     * @return string
     * @throws Exception
     * @Title : intro2
     * @Description : 이용방법
     */
    @GetMapping("/intro2.html")
    public String intro2() throws Exception {
        return "/front/intro/platform/intro2";
    }
}
