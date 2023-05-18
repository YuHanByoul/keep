package com.kbrainc.plum.front.fclt.controller;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 푸름이 아동환경교실 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.fclt.controller
 * - InfntEnveduFlctController.java
 * </pre>
 *
 * @ClassName : InfntEnveduFlctController
 * @Description : 유아환경교욱관 Controller
 * @author : KBRAINC
 * @date : 2023. 3. 10.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.InfntEnveduFlctController")
@Alias("front.InfntEnveduFlctController")
public class InfntEnveduFlctController {
    
    /**
     * 푸름이 아동환경교실 화면
     *
     * @Title : childEnvFclt
     * @Description : 푸름이 아동환경교실 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/fclt/infntEnveduFclt.html")
    public String infntEnveduFclt() throws Exception {
        return "front/fclt/infntEnveduFclt";
    }

    @RequestMapping(value = "/front/fclt/infntEnveduFclt2.html")
    public String infntEnveduFclt2() throws Exception {
        return "front/fclt/infntEnveduFclt2";
    }
}
