package com.kbrainc.plum.front.envPrpsl.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslAnsVo;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslVo;
import com.kbrainc.plum.front.envPrpsl.service.EnvPrpslSerivce;
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
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 환경교육 제안 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.envPrpsl.controller
 * - EnvPrpslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvPrpslController
 * @Description : 환경교육 제안 컨트롤러 클래스
 * @date : 2023. 02. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.envPrpslController")
@Controller("front.envPrpslController")
@RequestMapping("/front/envPrpsl")
public class EnvPrpslController {
    private static final String VIEW_PATH = "/front/envPrpsl";

    @Resource(name = "front.envPrpslService")
    private EnvPrpslSerivce envPrpslSerivce;

    @Autowired
    private FileServiceImpl fileService;

    @GetMapping("/envPrpslList.html")
    public String envPrpslList(EnvPrpslVo searchVO, Model model) throws Exception {

        model.addAttribute("searchVo", searchVO);
        return VIEW_PATH + "/envPrpslList.html";
    }

    @GetMapping("/envPrpslForm.html")
    public String envPrpslForm(EnvPrpslVo searchVo, Model model) throws Exception {
        EnvPrpslVo envPrpsl = envPrpslSerivce.selectEnvPrpsl(searchVo);
        if (envPrpsl == null) envPrpsl = new EnvPrpslVo();

        Map<String, Object> envPrpslFile = fileService.getConfigurationByFilegrpName("envPrpsl_file");

        String uploadFileExtsn = ((HashMap<String, String>) envPrpslFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnName -> "." + fileExtsnName.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("fileConfiguration", envPrpslFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("envPrpsl", envPrpsl);

        return VIEW_PATH + "/envPrpslForm";
    }

    @GetMapping("/envPrpslDetail.html")
    public String envPrpslDetail(EnvPrpslVo searchVo, Model model, @UserInfo UserVo user) throws Exception {
        EnvPrpslVo envPrpsl = envPrpslSerivce.selectEnvPrpsl(searchVo);
        EnvPrpslAnsVo envPrpslAns = envPrpslSerivce.selectEnvPrpslAns(searchVo);

        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("envPrpsl", envPrpsl);
        model.addAttribute("envPrpslAns", envPrpslAns);
        return VIEW_PATH + "/envPrpslDetail";
    }

    @GetMapping("/selectEnvPrpslList.do")
    @ResponseBody
    public Map<String, Object> selectEnvPrpslList(EnvPrpslVo envPrpslVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<EnvPrpslVo> list = envPrpslSerivce.selectEnvPrpslList(envPrpslVo);
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @PostMapping("/insertEnvPrpsl.do")
    @ResponseBody
    public Map<String, Object> insertEnvPrpsl(@Valid EnvPrpslVo envPrpslVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        envPrpslVo.setUser(userVo);

        if (envPrpslSerivce.insertEnvPrpsl(envPrpslVo) > 0) {
            success = true;
            response.put("msg", "등록이 완료되었습니다.");
        } else
            response.put("msg", "등록이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/updateEnvPrpsl.do")
    @ResponseBody
    public Map<String, Object> updateEnvPrpsl(@Valid EnvPrpslVo envPrpslVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        envPrpslVo.setUser(userVo);

        if (envPrpslSerivce.updateEnvPrpsl(envPrpslVo) > 0) {
            success = true;
            response.put("msg", "수정이 완료되었습니다.");
        } else
            response.put("msg", "수정이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    @PostMapping("/deleteEnvPrpsl.do")
    @ResponseBody
    public Map<String, Object> deleteEnvPrpsl(EnvPrpslVo envPrpslVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        envPrpslVo.setUser(userVo);

        if (envPrpslSerivce.deleteEnvPrpsl(envPrpslVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");

        response.put("success", success);
        return response;
    }

    @PostMapping("/cancelEnvPrpsl.do")
    @ResponseBody
    public Map<String, Object> cancelEnvPrpsl(EnvPrpslVo envPrpslVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        envPrpslVo.setUser(userVo);

        if (envPrpslSerivce.cancelEnvPrpsl(envPrpslVo) > 0) {
            success = true;
            response.put("msg", "취소가 완료되었습니다.");
        } else
            response.put("msg", "취소가 실패하였습니다.");

        response.put("success", success);
        return response;
    }
}
