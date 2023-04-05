package com.kbrainc.plum.front.faq.controller;

import com.kbrainc.plum.front.faq.model.FaqClVo;
import com.kbrainc.plum.front.faq.model.FaqVo;
import com.kbrainc.plum.front.faq.service.FaqService;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 자주묻는 질문 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.controller
 * - FaqController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqController
 * @Description : 자주묻는 질문 컨트롤러 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.faqController")
@Controller("front.faqController")
@RequestMapping("/front/faq")
public class FaqController {
    private static final String VIEW_PREFIX = "/front/faq";

    @Resource(name= "front.faqService")
    private FaqService faqService;

    @GetMapping("/faqList.html")
    public String faqList(FaqVo searchVo, Model model, @SiteInfo SiteInfoVo site) throws Exception {
        searchVo.setSite(site);
        searchVo.setOrderField("ORD");
        searchVo.setOrderDirection(ParentRequestVo.ORDER_DIRECTION.desc);
        searchVo.setRowPerPage(20);

        List<FaqVo> list = faqService.selectFaqList(searchVo);
        List<FaqClVo> faqCl = faqService.selectFaqClList(searchVo);

        model.addAttribute("faqClList",faqCl);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("list", list);
        model.addAttribute("totalCount", list.size() > 0 ? list.get(0).getTotalCount() : 0);

        return VIEW_PREFIX + "/faqList";
    }
}
