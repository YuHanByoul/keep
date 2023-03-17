package com.kbrainc.plum.front.fclt.controller;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 초록작당소 안내/대관 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.fclt.controller
 * - GreenGroupRntlController.java
 * </pre>
 *
 * @ClassName : GreenGroupRntlController
 * @Description : 초록작당소 안내/대관 Controller
 * @author : KBRAINC
 * @date : 2023. 3. 16.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.GreenGroupRntlController")
@Alias("front.GreenGroupRntlController")
public class GreenGroupRntlController {

    /**
     * 초록작당소 안내/대관 화면
     *
     * @Title : greenGroupRntl
     * @Description : 초록작당소 안내/대관 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/fclt/greenGroupRntl.html")
    public String greenGroupRntl() throws Exception {
        return "/front/fclt/greenGroupRntl";
    }
    
}