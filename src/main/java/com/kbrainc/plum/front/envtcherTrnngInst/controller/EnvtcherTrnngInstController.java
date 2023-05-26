package com.kbrainc.plum.front.envtcherTrnngInst.controller;

import com.kbrainc.plum.front.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.front.envtcherTrnngInst.service.EnvtcherTrnngInstService;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 환경교육사 양성기관 현황 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.envtcherTrnngInst.controller
 * - EnvtcherTrnngInstController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvtcherTrnngInstController
 * @Description : 환경교육사 양성기관 현황 Controller
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Alias("front.EnvtcherTrnngInstController")
@Controller("front.EnvtcherTrnngInstController")
public class EnvtcherTrnngInstController {
    @Resource(name="front.EnvtcherTrnngInstService")
    private EnvtcherTrnngInstService envtcherTrnngInstService;

    /**
     * 환경교육사 양성기관 현황 목록 화면
     * Title : envtcherTrnngInstSituList
     * Description : 환경교육사 양성기관 현황 목록 화면
     *
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/envtcherTrnngInst/envtcherTrnngInstSituList.html")
    public String envtcherTrnngInstSituList(Model model) throws Exception {
        List<EnvtcherTrnngInstVo> list = envtcherTrnngInstService.selectEnvtcherTrnngInstSituList();
        model.addAttribute("list",list);
        return "front/envtcherTrnngInst/envtcherTrnngInstSituList";
    }

    /**
     * 환경교육사 안내 화면
     * Title : envtcherIntro
     * Description : 환경교육사 안내 화면
     *
     * @return string
     */
    @RequestMapping(value = "/front/envtcherTrnngInst/envtcherIntro.html")
    public String envtcherIntro() {
        return "front/envtcherTrnngInst/envtcherIntro";
    }
}
