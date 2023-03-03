package com.kbrainc.plum.front.mypage.inqry.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.serivce.PltfomImprvmPropslService;
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
 * 마이페이지 > 플랫폼 개선 제안 내역 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.inqry
 * - MyPltfomImprvmPropslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyPltfomImprvmPropslController
 * @Description : 마이페이지 > 플랫폼 개선 제안 내역 컨트롤러 클래스
 * @date : 2023. 02. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/inqry")
public class MyPltfomImprvmPropslController {
    private static final String VIEW_PATH = "/front/mypage/inqry";

    @Resource(name = "front.pltfomImprvmPropslService")
    private PltfomImprvmPropslService pltfomImprvmPropslService;

    @Autowired
    private FileServiceImpl fileService;

    @GetMapping("/pltfomImprvmPropslList.html")
    public String pltfomImprvPropslList(PltfomImprvmPropslVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/pltfomImprvmPropslList";
    }

    @GetMapping("/pltfomImprvmPropslForm.html")
    public String pltfomImprvPropslForm(PltfomImprvmPropslVo searchVo, Model model) throws Exception {
        PltfomImprvmPropslVo propslVo = pltfomImprvmPropslService.selectPropsl(searchVo);
        if (propslVo == null) {
            propslVo = new PltfomImprvmPropslVo();
        }

        Map<String, Object> propslFileConfiguration = fileService.getConfigurationByFilegrpName("pltfom_imprvm_propsl_file");
        String uploadFileExtsn = ((HashMap<String, String>) propslFileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnName -> "." + fileExtsnName.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("fileConfiguration", propslFileConfiguration);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("propsl", propslVo);
        return VIEW_PATH + "/pltfomImprvmPropslForm";
    }

    @GetMapping("/pltfomImprvmPropslDetail.html")
    public String pltfomImprvPropslDetail(PltfomImprvmPropslVo searchVo, Model model, @UserInfo UserVo user) throws Exception {
        PltfomImprvmPropslVo propsl = pltfomImprvmPropslService.selectPropsl(searchVo);
        PltfomImprvmPropslAnsVo propslAns = pltfomImprvmPropslService.selectPropslAns(searchVo);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("propsl", propsl);
        model.addAttribute("propslAns", propslAns);

        return VIEW_PATH + "/pltfomImprvmPropslDetail";
    }

    @GetMapping("/selectPltfomImprvmPropslList.do")
    @ResponseBody
    public Map<String, Object> selectPltfomImprvmPropslList(PltfomImprvmPropslVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        searchVo.setUser(user);

        List<PltfomImprvmPropslVo> list = pltfomImprvmPropslService.selectMyPltfomImprvmPropslList(searchVo);

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
