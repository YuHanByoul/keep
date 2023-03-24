package com.kbrainc.plum.front.helpdesk.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskAnsVo;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskVo;
import com.kbrainc.plum.front.helpdesk.service.HelpdeskService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 헬프데스크 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.helpdesk.controller
 * - HelpdeskController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpdeskController
 * @Description : 헬프데스크 컨트롤러 클래스
 * @date : 2023. 02. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.helpdeskController")
@Controller("front.helpdeskController")
@RequestMapping("/front/helpdesk")
public class HelpdeskController {
    private final static String VIEW_PREFIX = "/front/helpdesk";

    @Resource(name = "front.helpdeskService")
    private HelpdeskService helpdeskService;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 헬프데스크 신청 목록화면
     *
     * @param searchVo
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : helpdeskList
     * @Description : 헬프데스크 신청 목록화면
     */
    @GetMapping("/helpdeskList.html")
    public String helpdeskList(HelpdeskVo searchVo, @UserInfo UserVo user, Model model) throws Exception {
        searchVo.setOrderField("REG_DT");

        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);

        return VIEW_PREFIX + "/helpdeskList";
    }

    /**
     * 헬프데스크 상세화면
     *
     * @param searchVo
     * @param model
     * @param user
     * @return string
     * @throws Exception
     * @Title : helpdeskDetail
     * @Description : 헬프데스크 상세화면
     */
    @GetMapping("/helpdeskDetail.html")
    public String helpdeskDetail(HelpdeskVo searchVo, Model model, @UserInfo UserVo user, HttpServletResponse response) throws Exception {
        HelpdeskVo helpdesk = helpdeskService.selectHelpdesk(searchVo);

        if(helpdesk.getRlsYn().equals("N")) {
            if(!helpdesk.getUserid().equals(Integer.valueOf(user.getUserid()))) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.print("<script>alert('비공개 글의 경우 작성자만 볼 수 있습니다.');history.back();</script>");
                return null;
            }
        }

        HelpdeskAnsVo helpdeskAns = helpdeskService.selectHelpdeskAns(searchVo);

        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("helpdesk", helpdesk);
        model.addAttribute("helpdeskAns", helpdeskAns);

        return VIEW_PREFIX + "/helpdeskDetail";
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

        return VIEW_PREFIX + "/helpdeskForm";
    }

    @GetMapping("/helpdeskIntro.html")
    public String helpdeskIntro() throws Exception {
        return VIEW_PREFIX + "/helpdeskIntro";
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
        List<HelpdeskVo> list = helpdeskService.selectHelpdeskList(searchVo);

        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());

            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @PostMapping("/insertHelpdesk.do")
    @ResponseBody
    public Map<String, Object> insertHelpdesk(@Valid HelpdeskVo helpdeskVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        helpdeskVo.setUser(userVo);

        if (helpdeskService.insertHelpdesk(helpdeskVo) > 0) {
            success = true;
            response.put("msg", "등록이 완료되었습니다.");
        } else
            response.put("msg", "등록이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/updateHelpdesk.do")
    @ResponseBody
    public Map<String, Object> updateHelpdesk(@Valid HelpdeskVo helpdeskVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        helpdeskVo.setUser(userVo);

        if (helpdeskService.updateHelpdesk(helpdeskVo) > 0) {
            success = true;
            response.put("msg", "수정이 완료되었습니다.");
        } else
            response.put("msg", "수정이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/deleteHelpdesk.do")
    @ResponseBody
    public Map<String, Object> deleteHelpdesk(HelpdeskVo helpdeskVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        helpdeskVo.setUser(userVo);

        if (helpdeskService.deleteHelpdesk(helpdeskVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");

        response.put("success", success);
        return response;
    }

    @PostMapping("/cancelHelpdesk.do")
    @ResponseBody
    public Map<String, Object> cancelHelpdesk(HelpdeskVo helpdeskVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        helpdeskVo.setUser(userVo);

        if (helpdeskService.cancelHelpdesk(helpdeskVo) > 0) {
            success = true;
            response.put("msg", "취소가 완료되었습니다.");
        } else
            response.put("msg", "취소가 실패하였습니다.");

        response.put("success", success);
        return response;
    }

}
