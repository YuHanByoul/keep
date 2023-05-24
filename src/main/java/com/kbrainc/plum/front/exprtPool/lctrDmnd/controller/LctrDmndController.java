package com.kbrainc.plum.front.exprtPool.lctrDmnd.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.LctrDmndVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.service.LctrDmndService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.controller
 * - LctrDmndController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndController
 * @Description : 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.lctrDmndController")
@Controller("front.lctrDmndController")
@RequestMapping("/front/exprtPool")
public class LctrDmndController {
    private static final String VIEW_PATH = "/front/exprtPool";

    @Resource(name = "front.lctrDmndService")
    private LctrDmndService lctrDmndService;

    @Autowired
    private FileServiceImpl fileService;

    /**
     * 섭외요청 목록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndList
     * @Description : 섭외요청 목록 화면
     */
    @GetMapping("/{exprtTypePath}/lctrDmndList.html")
    public String lctrDmndList(
            @PathVariable String exprtTypePath
            , @ModelAttribute("searchVo") ExprtVo searchVo
            , Model model
            , @UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        searchVo.setExprtTypePath(exprtTypePath);
        return VIEW_PATH + "/lctrDmndList";
    }

    @GetMapping("/envEduExprtList.html")
    public String envEduExprtList() {
        return VIEW_PATH + "/envEduExprtList";
    }

    /**
     * 섭외요청 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndDetail
     * @Description : 섭외요청 상세 화면
     */
    @GetMapping("/lctrDmndDetail.html")
    public String lctrDmndDetail(@ModelAttribute("searchVo") ExprtVo searchVo, Model model, @UserInfo UserVo user) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String referer = request.getHeader("referer");

        searchVo.setUser(user);
        ExprtVo exprt = lctrDmndService.selectExprt(searchVo);

        model.addAttribute("referer", referer == null ? "/front/exprtPool/133/lctrDmndList.html" : referer);
        model.addAttribute("exprt", exprt);
        model.addAttribute("user", user);
        return VIEW_PATH + "/lctrDmndDetail";
    }

    /**
     * 섭외 요청 등록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception/
     * @Title : lctrDmndForm
     * @Description : 섭외 요청 등록 화면
     */
    @GetMapping("/lctrDmndForm.html")
    public String lctrDmndForm(@ModelAttribute("searchVo") ExprtVo searchVo, @UserInfo UserVo user, Model model, HttpServletResponse response) throws Exception {
        if (user == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('14세 이상 개인회원 또는 기관회원 가입 후 전문가 요청이 가능합니다.');history.back();</script>");
            return null;
        }

        if (user.getUserType() != null && user.getUserType().equals("C")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('14세 미만 어린이 회원은 전문가 요청을 할 수 없습니다.');history.back();</script>");
            return null;
        }

        Map<String, Object> fileConfiguration = fileService.getConfigurationByFilegrpName("lctrDmnd_file");
        String uploadFileExtsn = ((HashMap<String, String>) fileConfiguration.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnKey -> "." + fileExtsnKey.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("fileConfiguration", fileConfiguration);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);

        return VIEW_PATH + "/lctrDmndForm";
    }

    /**
     * 섭외 요청 완료 화면
     *
     * @return string
     * @throws Exception
     * @Title : lctrDmndSuccess
     * @Description : 섭외 요청 완료 화면
     */
    @GetMapping("/lctrDmndSuccess.html")
    public String lctrDmndSuccess() throws Exception {
        return VIEW_PATH + "/lctrDmndSuccess";
    }

    /**
     * 전문가 목록 조회
     *
     * @param searchVo
     * @return map
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    @GetMapping("/selectExprtList.do")
    @ResponseBody
    public Map<String, Object> selectExprtList(ExprtVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();

        searchVo.setUser(user);

        List<ExprtVo> list = lctrDmndService.selectExprtList(searchVo);

        if (list.size() > 0) {
            response.put("totalCount", list.get(0).getTotalCount());
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
     * 전문가 섭외 요청 등록
     *
     * @param lctrDmndVo
     * @return map
     * @throws Exception
     * @Title : insertLctrDmnd
     * @Description : 전문가 섭외 요청 등록
     */
    @PostMapping("/insertLctrDmnd.do")
    @ResponseBody
    public Map<String, Object> insertLctrDmnd(@Valid LctrDmndVo lctrDmndVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("result", Constant.REST_API_RESULT_FAIL);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                response.put("msg", fieldError.getDefaultMessage());
            }
            return response;
        }

        lctrDmndVo.setUser(user);
        int retVal = lctrDmndService.insertLctrDmnd(lctrDmndVo);

        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        }

        return response;
    }

    /**
     * 관심인력 등록
     *
     * @param exprtVo
     * @param user
     * @return map
     * @Title : insertLtrstExprt
     * @Description : 관심인력 등록
     */
    @PostMapping("/insertItrstExprt.do")
    @ResponseBody
    public Map<String, Object> insertItrstExprt(ExprtVo exprtVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        exprtVo.setUser(user);

        boolean checkAlreadyRegistedItrstExprt = lctrDmndService.checkAlreadyRegistedItrstExprt(exprtVo);
        if (checkAlreadyRegistedItrstExprt) {
            response.put("msg", "이미 등록된 관심 전문가입니다. ");
            return response;
        }

        if (lctrDmndService.insertItrstExprt(exprtVo) > 0) {
            response.put("msg", "관심 전문가로 등록되었습니다.");
            response.put("success", true);
        } else
            response.put("msg", "관심 전문가 등록이 실패하였습니다.");

        return response;
    }

    /**
     * 관심인력 삭제
     *
     * @param exprtVo
     * @param user
     * @return map
     * @Title : deleteItrstExprt
     * @Description : 관심인력 삭제
     */
    @PostMapping("/deleteItrstExprt.do")
    @ResponseBody
    public Map<String, Object> deleteItrstExprt(ExprtVo exprtVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        exprtVo.setUser(user);

        if (lctrDmndService.deleteItrstExprt(exprtVo) > 0) {
            response.put("msg", "관심 전문가를 해제하였습니다.");
            response.put("success", true);
        } else
            response.put("msg", "관심 전문가 해제가 실패하였습니다.");

        return response;
    }

}
