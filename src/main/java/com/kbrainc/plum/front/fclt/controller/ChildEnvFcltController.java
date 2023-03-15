package com.kbrainc.plum.front.fclt.controller;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 푸름이 아동환경교실 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.fclt.controller
 * - ChildEnvFcltController.java
 * </pre>
 *
 * @ClassName : ChildEnvFcltController
 * @Description : 푸름이 아동환경교실 Controller
 * @author : KBRAINC
 * @date : 2023. 3. 10.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.ChildEnvFcltController")
@Alias("front.ChildEnvFcltController")
public class ChildEnvFcltController {

    /**
     * 푸름이 아동환경교실 화면
     *
     * @Title : childEnvFclt
     * @Description : 푸름이 아동환경교실 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/fclt/childEnvFclt.html")
    public String childEnvFclt() throws Exception {
        return "front/fclt/childEnvFclt";
    }
    
}