package com.kbrainc.plum.front.pltfomImprvmPropsl.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.serivce.PltfomImprvmPropslService;
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
 * 플랫폼 개선 제안 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.controller
 * - pltfomImprvmPropslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : pltfomImprvmPropslController
 * @Description : 플랫폼 개선 제안 컨트롤러 클래스
 * @date : 2023. 02. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.pltfomImprvmPropslController")
@Controller
@RequestMapping("/front/pltfomImprvmPropsl")
public class PltfomImprvmPropslController {
    private static final String VIEW_PREFIX = "/front/pltfomImprvmPropsl";

    @Resource(name = "front.pltfomImprvmPropslService")
    private PltfomImprvmPropslService pltfomImprvmPropslService;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 플랫폼 개선 제안 목록 화면
     *
     * @param searchVo
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : pltfomImprvPropslList
     * @Description : 플랫폼 개선 제안 목록 화면
     */
    @GetMapping("/pltfomImprvmPropslList.html")
    public String pltfomImprvPropslList(PltfomImprvmPropslVo searchVo, @UserInfo UserVo user, Model model) throws Exception {
        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);

        return VIEW_PREFIX + "/pltfomImprvmPropslList";
    }

    /**
     * 플랫폼 개선 제안 등록 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : pltfomImprvPropslForm
     * @Description : 플랫폼 개선 제안 등록 화면
     */
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
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("fileConfiguration", propslFileConfiguration);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        model.addAttribute("propsl", propslVo);
        return VIEW_PREFIX + "/pltfomImprvmPropslForm";
    }

    /**
     * 플랫폼 개선 제안 상세 화면
     *
     * @param searchVo
     * @param model
     * @param user
     * @return string
     * @throws Exception
     * @Title : pltfomImprvPropslDetail
     * @Description : 플랫폼 개선 제안 상세 화면
     */
    @GetMapping("/pltfomImprvmPropslDetail.html")
    public String pltfomImprvPropslDetail(PltfomImprvmPropslVo searchVo, Model model, @UserInfo UserVo user) throws Exception {

        PltfomImprvmPropslVo propsl = pltfomImprvmPropslService.selectPropsl(searchVo);
        PltfomImprvmPropslAnsVo propslAns = pltfomImprvmPropslService.selectPropslAns(searchVo);

        model.addAttribute("loginUserid", user != null ? Integer.valueOf(user.getUserid()) : null);
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("propsl", propsl);
        model.addAttribute("propslAns", propslAns);

        return VIEW_PREFIX + "/pltfomImprvmPropslDetail";
    }

    /**
     * 플랫폼 개선 제안 목록 조회
     *
     * @param searchVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    @GetMapping("/selectPltfomImprvmPropslList.do")
    @ResponseBody
    public Map<String, Object> selectPltfomImprvmPropslList(PltfomImprvmPropslVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<PltfomImprvmPropslVo> list = pltfomImprvmPropslService.selectPltfomImprvmPropslList(searchVo);

        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 플랫폼 개선 제안 생성
     *
     * @param pltfomImprvmPropslVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertPltfomImprvPropsl
     * @Description : 플랫폼 개선 제안 생성
     */
    @PostMapping("/insertPltfomImprvmPropsl.do")
    @ResponseBody
    public Map<String, Object> insertPltfomImprvPropsl(@Valid PltfomImprvmPropslVo pltfomImprvmPropslVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        pltfomImprvmPropslVo.setUser(user);

        if (pltfomImprvmPropslService.insertPltfomImprvmPropsl(pltfomImprvmPropslVo) > 0) {
            success = true;
            response.put("msg", "등록이 완료되었습니다.");
        } else
            response.put("msg", "등록이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    /**
     * 플랫폼 개선 제안 수정
     *
     * @param pltfomImprvmPropslVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : updatePltfomImprvPropsl
     * @Description : 플랫폼 개선 제안 수정
     */
    @PostMapping("/updatePltfomImprvmPropsl.do")
    @ResponseBody
    public Map<String, Object> updatePltfomImprvPropsl(@Valid PltfomImprvmPropslVo pltfomImprvmPropslVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        boolean success = false;
        pltfomImprvmPropslVo.setUser(user);

        if (pltfomImprvmPropslService.updatePltfomImprvmPropsl(pltfomImprvmPropslVo) > 0) {
            success = true;
            response.put("msg", "수정이 완료되었습니다.");
        } else
            response.put("msg", "수정이 실패하였습니다.");

        response.put("success", success);

        return response;
    }

    /**
     * 플랫폼 개선 제안 삭제
     *
     * @param pltfomImprvmPropslVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : deletePltfomImprvPropsl
     * @Description : 플랫폼 개선 제안 삭제
     */
    @PostMapping("/deletePltfomImprvmPropsl.do")
    @ResponseBody
    public Map<String, Object> deletePltfomImprvPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        pltfomImprvmPropslVo.setUser(user);

        if (pltfomImprvmPropslService.deletePltfomImprvmPropsl(pltfomImprvmPropslVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");

        response.put("success", success);
        return response;
    }

    /**
     * 플랫폼 개선 제안 취소
     *
     * @param pltfomImprvmPropslVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : cancelPltfomImprvPropsl
     * @Description : 플랫폼 개선 제안 취소
     */
    @PostMapping("/cancelPltfomImprvmPropsl.do")
    @ResponseBody
    public Map<String, Object> cancelPltfomImprvPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        pltfomImprvmPropslVo.setUser(user);

        if (pltfomImprvmPropslService.cancelPltfomImprvmPropsl(pltfomImprvmPropslVo) > 0) {
            success = true;
            response.put("msg", "취소가 완료되었습니다.");
        } else
            response.put("msg", "취소가 실패하였습니다.");

        response.put("success", success);
        return response;
    }
}
