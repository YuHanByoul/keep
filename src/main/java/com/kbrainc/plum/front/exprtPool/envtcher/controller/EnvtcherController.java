package com.kbrainc.plum.front.exprtPool.envtcher.controller;

import com.kbrainc.plum.front.exprtPool.envtcher.model.EnvtcherAgncyVo;
import com.kbrainc.plum.front.exprtPool.envtcher.service.EnvtcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 전문가/강사 > 환경교육사 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.envtcher.controller
 * - EnvtcherController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvtcherController
 * @Description : 전문가/강사 > 환경교육사 컨트롤러 클래스
 * @date : 2023. 03. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/envtcher")
public class EnvtcherController {
    private static final String VIEW_PATH = "/front/envtcher";

    @Autowired
    private EnvtcherService envtcherService;

    @GetMapping("/intro1.html")
    public String intro1() throws Exception {
        return VIEW_PATH + "/intro1";
    }

    @GetMapping("/intro2.html")
    public String intro2() throws Exception {
        return VIEW_PATH + "/intro2";
    }

    @GetMapping("/intro3.html")
    public String intro3(Model model) throws Exception {
        List<EnvtcherAgncyVo> envtcherAgncys = envtcherService.selectEnvtcherAgncyList();
        model.addAttribute("envtcherAgncys", envtcherAgncys);
        return VIEW_PATH + "/intro3";
    }
}
