package com.kbrainc.plum.front.inqry.controller;

import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.front.inqry.service.InqryService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1:1문의 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.controller
 * - InqryController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryController
 * @Description : 1:1문의 컨트롤러 클래스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.inqryController")
@Alias("front.inqryController")
@RequestMapping("/front/inqry")
public class InqryController {

    @Resource(name = "front.inqryService")
    private InqryService inqryService;

    private final static String VIEW_PATH = "/front/inqry";

    @GetMapping("/inqryList.html")
    public String inqryList(@UserInfo UserVo user, Model model) throws Exception {
        model.addAttribute("userInfo", user);
        return VIEW_PATH + "/inqryList";
    }

    @GetMapping("/inqryForm.html")
    public String inqryForm() throws Exception {
        return VIEW_PATH + "/inqryForm";
    }

    @GetMapping("/inqryDetail.html")
    public String inqryDetail(InqryVo inqryVo, Model model) throws Exception {
        InqryVo inqryDetail = inqryService.selectInqryDetail(inqryVo);
        model.addAttribute("inqryDetail", inqryDetail);
        return VIEW_PATH + "/inqryDetail";
    }


    @GetMapping(value = "/selectInqryList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(InqryVo inqryVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        inqryVo.setUser(user);
        List<InqryVo> list = inqryService.selectInqryList(inqryVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        }
         else
             result.put("totalCount", 0);

        result.put("list", list);

        return result;
    }

    @PostMapping("/insertInqry.do")
    @ResponseBody
    public Map<String, Object> insertInqry(InqryVo inqryVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    @PostMapping("/updateInqry.do")
    @ResponseBody
    public Map<String, Object> updateInqry(InqryVo inqryVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    @PostMapping("/deleteInqry.do")
    @ResponseBody
    public Map<String, Object> deleteInqry(InqryVo inqryVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        return result;
    }
}
