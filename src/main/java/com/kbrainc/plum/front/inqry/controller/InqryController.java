package com.kbrainc.plum.front.inqry.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.inqry.model.InqryAnsVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.front.inqry.service.InqryService;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
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
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private FileServiceImpl fileService;

    private final static String VIEW_PATH = "/front/inqry";

    @GetMapping("/inqryList.html")
    public String inqryList(InqryVo searchVo, @UserInfo UserVo user, Model model) throws Exception {

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        
        return VIEW_PATH + "/inqryList";
    }

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

    @GetMapping("/inqryDetail.html")
    public String inqryDetail(InqryVo searchVo, Model model, @UserInfo UserVo user) throws Exception {
        InqryVo inqry = inqryService.selectInqry(searchVo);
        InqryAnsVo inqryAns = inqryService.selectInqryAns(searchVo);

        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("inqry", inqry);
        model.addAttribute("inqryAns", inqryAns);
        return VIEW_PATH + "/inqryDetail";
    }

    @GetMapping(value = "/selectInqryList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(InqryVo inqryVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<InqryVo> list = inqryService.selectInqryList(inqryVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());

            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @PostMapping("/insertInqry.do")
    @ResponseBody
    public Map<String, Object> insertInqry(@Valid InqryVo inqryVo, BindingResult bindingResult, @UserInfo UserVo userVo, @SiteInfo SiteInfoVo site) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        inqryVo.setSite(site);
        inqryVo.setUser(userVo);

        if (inqryService.insertInqry(inqryVo) > 0) {
            success = true;
            response.put("msg", "등록이 완료되었습니다.");
        } else
            response.put("msg", "등록이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/updateInqry.do")
    @ResponseBody
    public Map<String, Object> updateInqry(@Valid InqryVo inqryVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        inqryVo.setUser(userVo);

        if (inqryService.updateInqry(inqryVo) > 0) {
            success = true;
            response.put("msg", "수정이 완료되었습니다.");
        } else
            response.put("msg", "수정이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/deleteInqry.do")
    @ResponseBody
    public Map<String, Object> deleteInqry(InqryVo inqryVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        inqryVo.setUser(userVo);

        if (inqryService.deleteInqry(inqryVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");

        response.put("success", success);
        return response;
    }

    @PostMapping("/cancelInqry.do")
    @ResponseBody
    public Map<String, Object> cancelInqry(InqryVo inqryVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        inqryVo.setUser(userVo);

        if (inqryService.cancelInqry(inqryVo) > 0) {
            success = true;
            response.put("msg", "취소가 완료되었습니다.");
        } else
            response.put("msg", "취소가 실패하였습니다.");

        response.put("success", success);
        return response;
    }
}
