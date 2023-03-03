package com.kbrainc.plum.front.mypage.inqry.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskAnsVo;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskVo;
import com.kbrainc.plum.front.helpdesk.service.HelpdeskService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 마이페이지 > 탄소중립 환경교육 헬프 데스크 신청 내역
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.inqry
 * - MyHelpdeskController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyHelpdeskController
 * @Description : 마이페이지 > 탄소중립 환경교육 헬프 데스크 신청 내역
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/inqry")
public class MyHelpdeskController {
    private static final String VIEW_PATH = "/front/mypage/inqry";

    @Resource(name = "front.helpdeskService")
    private HelpdeskService helpdeskService;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 헬프데스크 신청 목록화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : helpdeskList
     * @Description : 헬프데스크 신청 목록화면
     */
    @GetMapping("/helpdeskList.html")
    public String helpdeskList(HelpdeskVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);

        return VIEW_PATH + "/helpdeskList";
    }

    /**
     * 헬프데스크 상세화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : helpdeskDetail
     * @Description : 헬프데스크 상세화면
     */
    @GetMapping("/helpdeskDetail.html")
    public String helpdeskDetail(HelpdeskVo searchVo, Model model) throws Exception {
        HelpdeskVo helpdesk = helpdeskService.selectHelpdesk(searchVo);
        HelpdeskAnsVo helpdeskAns = helpdeskService.selectHelpdeskAns(searchVo);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("helpdesk", helpdesk);
        model.addAttribute("helpdeskAns", helpdeskAns);

        return VIEW_PATH + "/helpdeskDetail";
    }

    @GetMapping("/helpdeskForm.html")
    public String helpdeskForm(HelpdeskVo searchVo, Model model) throws Exception {
        HelpdeskVo helpdesk = helpdeskService.selectHelpdesk(searchVo);
        if (helpdesk == null) {
            helpdesk = new HelpdeskVo();
        }

        Map<String, Object> fileConfiguration = fileService.getConfigurationByFilegrpName("helpdesk_file");

        String uploadFileExtsn = ((HashMap<String, String>) fileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("fileConfiguration", fileConfiguration);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("helpdesk", helpdesk);

        return VIEW_PATH + "/helpdeskForm";
    }

    /**
     * 헬프데스크 신청 목록 조회
     *
     * @param searchVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : selectHelpdeskList
     * @Description : 헬프데스크 신청 목록 조회
     */
    @GetMapping(value = "/selectHelpdeskList.do")
    @ResponseBody
    public Map<String, Object> selectHelpdeskList(HelpdeskVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        searchVo.setUser(user);

        List<HelpdeskVo> list = helpdeskService.selectMyHelpdeskList(searchVo);

        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());

            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

}
