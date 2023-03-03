package com.kbrainc.plum.front.cmnty.controller;

import com.kbrainc.plum.front.cmnty.service.CmntyService;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 환경동아리 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.controller
 * - CmntyController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyController
 * @Description : 환경동아리 Controller
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller("front.CmntyController")
@Alias("front.CmntyController")
public class CmntyController {
    @Resource(name = "front.CmntyService")
    private CmntyService cmntyService;

    /**
     * 환경동아리 목록
     * Title : cmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/cmnty/cmntyList.html")
    public String cmntyList(@UserInfo UserVo userVo, CmntyVo cmntyVo, Model model) throws Exception{
        cmntyVo.setUser(userVo);
        List<CmntyVo> cmntyList = cmntyService.selectCmntyList(cmntyVo);
        model.addAttribute("cmntyList",cmntyList);
        model.addAttribute("userid",userVo.getUserid());
        model.addAttribute("esterid", cmntyVo.getEsterid());
        return "front/cmnty/cmntyList";
    }
}
