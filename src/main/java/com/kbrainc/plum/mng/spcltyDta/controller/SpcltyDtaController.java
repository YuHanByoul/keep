package com.kbrainc.plum.mng.spcltyDta.controller;

import com.kbrainc.plum.mng.bsnsOperDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.mng.bsnsOperDta.service.BsnsOperDtaService;
import com.kbrainc.plum.mng.spcltyDta.model.SpcltyDtaVo;
import com.kbrainc.plum.mng.spcltyDta.service.SpcltyDtaService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 전문자료 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spcltyDta.controller
 * - SpcltyDtaController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaController
 * @Description : 전문자료 관리 컨트롤러 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SpcltyDtaController {
    @Autowired
    private SpcltyDtaService spcltyDtaService;

    /**
     * 전문자료 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : spcltyDtaList
     * @Description : 전문자료 목록 화면
     */
    @GetMapping("/mng/spcltyDta/spcltyDtaList.html")
    public String spcltyDtaList() throws Exception {
        return "/mng/spcltyDta/spcltyDtaList";
    }

    /**
     * 전문자료 상세 화면
     *
     * @return string
     * @throws Exception
     * @Title : spcltyDtaForm
     * @Description : 전문자료 상세 화면
     */
    @GetMapping("/mng/spcltyDta/spcltyDtaForm.html")
    public String spcltyDtaForm(SpcltyDtaVo spcltyDtaVo, @UserInfo UserVo user,  Model model) throws Exception {
        SpcltyDtaVo spcltyDta = spcltyDtaService.selectSpcltyDta(spcltyDtaVo);
        spcltyDta.setUser(user);
        model.addAttribute("spcltyDta", spcltyDta);
        return "/mng/spcltyDta/spcltyDtaForm";
    }

    /**
     * 전문자료 목록 조회
     *
     * @param spcltyDtaVo
     * @return map
     * @throws Exception
     * @Title : selectSpcltyDtaList
     * @Description : 전문자료 목록 조회
     */
    @GetMapping("/mng/spcltyDta/selectSpcltyDtaList.do")
    @ResponseBody
    public Map<String, Object> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<SpcltyDtaVo> list = spcltyDtaService.selectSpcltyDtaList(spcltyDtaVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 전문자료 등록
     *
     * @param spcltyDtaVo
     * @return map
     * @throws Exception
     * @Title : insertSpcltyDta
     * @Description : 전문자료 등록
     */
    @PostMapping("/mng/spcltyDta/insertSpcltyDta.do")
    @ResponseBody
    public Map<String, Object> insertSpcltyDta(@Valid SpcltyDtaVo spcltyDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();


        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        spcltyDtaVo.setUser(user);

        int retVal = 0;
        retVal = spcltyDtaService.insertSpcltyDta(spcltyDtaVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "등록에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "등록에 실패했습니다.");
        }

        return result;
    }

    /**
     * 전문자료 수정
     *
     * @param spcltyDtaVo
     * @return map
     * @throws Exception
     * @Title : updateSpcltyDta
     * @Description : 전문자료 수정
     */
    @PostMapping("/mng/spcltyDta/updateSpcltyDta.do")
    @ResponseBody
    public Map<String, Object> updateSpcltyDta(@Valid SpcltyDtaVo spcltyDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        spcltyDtaVo.setUser(user);

        int retVal = 0;
        retVal = spcltyDtaService.updateSpcltyDta(spcltyDtaVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "수정에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "수정에 실패했습니다.");
        }

        return result;
    }

    /**
     * 전문자료 삭제
     *
     * @param spcltyDtaVo
     * @return map
     * @throws Exception
     * @Title : deleteSpcltyDta
     * @Description : 전문자료 삭제
     */
    @PostMapping("/mng/spcltyDta/deleteSpcltyDta.do")
    @ResponseBody
    public Map<String, Object> deleteSpcltyDta(SpcltyDtaVo spcltyDtaVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        spcltyDtaVo.setUser(user);
        int retVal = 0;
        retVal = spcltyDtaService.deleteSpcltyDta(spcltyDtaVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "삭제에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "삭제에 실패했습니다.");
        }

        return result;
    }
}
