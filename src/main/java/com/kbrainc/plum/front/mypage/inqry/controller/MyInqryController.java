package com.kbrainc.plum.front.mypage.inqry.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.inqry.model.InqryAnsVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.front.inqry.service.InqryService;
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
 * 마이페이지 > 1:1 문의 내역 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.inqry
 * - InqryController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryController
 * @Description : 마이페이지 > 1:1 문의 내역 컨트롤러 클래스
 * @date : 2023. 02. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/inqry")
public class MyInqryController {
    private static final String VIEW_PATH = "/front/mypage/inqry";

    @Resource(name = "front.inqryService")
    private InqryService inqryService;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 1:1문의 목록 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : inqryList
     * @Description : 1:1문의 목록 화면
     */
    @GetMapping("/inqryList.html")
    public String inqryList(InqryVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/inqryList";
    }

    /**
     * 1:1문의 등록 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : inqryForm
     * @Description : 1:1문의 등록 화면
     */
    @GetMapping("/inqryForm.html")
    public String inqryForm(InqryVo searchVo, Model model) throws Exception {
        InqryVo inqry = inqryService.selectInqry(searchVo);
        if (inqry == null) {
            inqry = new InqryVo();
        }

        Map<String, Object> inqryFile = fileService.getConfigurationByFilegrpName("inqry_file");
        String uploadFileExtsn = ((HashMap<String, String>) inqryFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("fileConfiguration", inqryFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("inqry", inqry);
        return VIEW_PATH + "/inqryForm";
    }

    /**
     * 1:1문의 상세 화면
     *
     * @param searchVo
     * @param model
     * @param user
     * @return string
     * @throws Exception
     * @Title : inqryDetail
     * @Description : 1:1문의 상세 화면
     */
    @GetMapping("/inqryDetail.html")
    public String inqryDetail(InqryVo searchVo, Model model, @UserInfo UserVo user) throws Exception {
        InqryVo inqry = inqryService.selectInqry(searchVo);
        InqryAnsVo inqryAns = inqryService.selectInqryAns(searchVo);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("inqry", inqry);
        model.addAttribute("inqryAns", inqryAns);
        return VIEW_PATH + "/inqryDetail";
    }

    /**
     * 1:1문의 목록 조회
     *
     * @param inqryVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : selectInqryList
     * @Description : 1:1문의 목록 조회
     */
    @GetMapping(value = "/selectInqryList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(InqryVo inqryVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        inqryVo.setUser(user);
        List<InqryVo> list = inqryService.selectMypageInqryList(inqryVo);
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
