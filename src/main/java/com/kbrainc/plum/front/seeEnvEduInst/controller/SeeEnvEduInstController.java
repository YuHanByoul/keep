package com.kbrainc.plum.front.seeEnvEduInst.controller;

import com.kbrainc.plum.front.seeEnvEduInst.model.SeeEnvEduInstVo;
import com.kbrainc.plum.front.seeEnvEduInst.service.SeeEnvEduInstService;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 교육기관/시설 > 사회환경교육기관 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.seeEnvEduInst.controller
 * - SeeEnvEduInstController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstController
 * @Description : 교육기관/시설 > 사회환경교육기관 컨트롤러 클래스
 * @date : 2023. 04. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.seeEnvEduInstController")
@Alias("front.seeEnvEduInstController")
public class SeeEnvEduInstController {
    @Resource(name = "front.seeEnvEduInstService")
    private SeeEnvEduInstService seeEnvEduInstService;

    /**
     * 기관 현황 화면
     *
     * @return string
     * @throws Exception
     * @Title : seeEnvEduInstList
     * @Description : 기관 현황 화면
     */
    @GetMapping("/front/seeEnvEduInst/seeEnvEduInstList.html")
    public String seeEnvEduInstList(Model model) throws Exception {
        List<SeeEnvEduInstVo> seeEnvEduInsts = seeEnvEduInstService.selectSeeEnvEduInstList();
        model.addAttribute("seeEnvEduInsts", seeEnvEduInsts);
        return "/front/seeEnvEduInst/seeEnvEduInstList";
    }

}
