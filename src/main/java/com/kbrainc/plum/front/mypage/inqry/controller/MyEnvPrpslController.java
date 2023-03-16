package com.kbrainc.plum.front.mypage.inqry.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslAnsVo;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslVo;
import com.kbrainc.plum.front.envPrpsl.service.EnvPrpslSerivce;
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
 * 마이페이지 > 환경교육 제안 내역 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.inqry
 * - MyEnvPrpslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyEnvPrpslController
 * @Description : 마이페이지 > 환경교육 제안 내역 컨트롤러 클래스
 * @date : 2023. 02. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/inqry")
public class MyEnvPrpslController {
    private static final String VIEW_PATH = "/front/mypage/inqry";

    @Resource(name = "front.envPrpslService")
    private EnvPrpslSerivce envPrpslSerivce;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 환경교육 제안 목록 화면
     *
     * @param searchVO
     * @param model
     * @return string
     * @throws Exception
     * @Title : envPrpslList
     * @Description : 환경교육 제안 목록 화면
     */
    @GetMapping("/envPrpslList.html")
    public String envPrpslList(EnvPrpslVo searchVO, Model model) throws Exception {
        model.addAttribute("searchVo", searchVO);
        return VIEW_PATH + "/envPrpslList.html";
    }

    /**
     * 환경교육 제안 등록 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : envPrpslForm
     * @Description : 환경교육 제안 등록 화면
     */
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

    /**
     * 환경교육 제안 상세 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : envPrpslDetail
     * @Description : 환경교육 제안 상세 화면
     */
    @GetMapping("/envPrpslDetail.html")
    public String envPrpslDetail(EnvPrpslVo searchVo, Model model) throws Exception {
        EnvPrpslVo envPrpsl = envPrpslSerivce.selectEnvPrpsl(searchVo);
        EnvPrpslAnsVo envPrpslAns = envPrpslSerivce.selectEnvPrpslAns(searchVo);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("envPrpsl", envPrpsl);
        model.addAttribute("envPrpslAns", envPrpslAns);
        return VIEW_PATH + "/envPrpslDetail";
    }

    /**
     * 환경교육 제안 목록 조회
     *
     * @param envPrpslVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : selectEnvPrpslList
     * @Description : 환경교육 제안 목록 조회
     */
    @GetMapping("/selectEnvPrpslList.do")
    @ResponseBody
    public Map<String, Object> selectEnvPrpslList(EnvPrpslVo envPrpslVo, @UserInfo UserVo user) throws Exception {
        envPrpslVo.setUser(user);
        Map<String, Object> response = new HashMap<>();

        List<EnvPrpslVo> list = envPrpslSerivce.selectMyEnvPrpslList(envPrpslVo);
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

}
