package com.kbrainc.plum.front.envOrg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 환경부 소속산하기관 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.envOrg.controller
 * - EnvOrgController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvOrgController
 * @Description : 환경부 소속산하기관 Controller
 * @date : 2023. 02. 16.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class EnvOrgController {

    /**
     * 환경부 소속/산하기관 > 외청/소속기관 목록
     * Title : envGroupOrgList
     * Description : 환경부 소속/산하기관 > 외청/소속기관 목록
     *
     * @return string
     */
    @RequestMapping(value="/front/envOrg/envGroupOrgList.html")
    public String envGroupOrgList(){
        return "front/envOrg/envGroupOrgList";
    }

    /**
     * 환경부 소속/산하기관 > 외청/소속기관 목록
     * Title : envAffiOrgList
     * Description : 환경부 소속/산하기관 > 외청/소속기관 목록
     *
     * @return string
     */
    @RequestMapping(value="/front/envOrg/envAffiOrgList.html")
    public String envAffiOrgList(){
        return "front/envOrg/envAffiOrgList";
    }
}
