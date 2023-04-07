package com.kbrainc.plum.mng.etcDta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.etcDta.model.EtcDtaVo;
import com.kbrainc.plum.mng.etcDta.service.EtcDtaService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 기타자료 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.etcDta.controller
 * - EtcDtaController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : EtcDtaController
 * @Description : 기타자료 관리 컨트롤러 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class EtcDtaController {
    @Autowired
    private EtcDtaService etcDtaService;

    /**
     * 기타자료 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : etcDtaList
     * @Description : 기타자료 목록 화면
     */
    @GetMapping("/mng/etcDta/etcDtaList.html")
    public String etcDtaList() throws Exception {
        return "/mng/etcDta/etcDtaList";
    }

    /**
     * 기타자료 상세 화면
     *
     * @return string
     * @throws Exception
     * @Title : etcDtaForm
     * @Description : 기타자료 상세 화면
     */
    @GetMapping("/mng/etcDta/etcDtaForm.html")
    public String etcDtaForm(EtcDtaVo etcDtaVo, @UserInfo UserVo user,  Model model) throws Exception {
        EtcDtaVo etcDta = etcDtaService.selectEtcDta(etcDtaVo);
        etcDta.setUser(user);
        model.addAttribute("etcDta", etcDta);
        return "/mng/etcDta/etcDtaForm";
    }

    /**
     * 기타자료 목록 조회
     *
     * @param etcDtaVo
     * @return map
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 기타자료 목록 조회
     */
    @GetMapping("/mng/etcDta/selectEtcDtaList.do")
    @ResponseBody
    public Map<String, Object> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<EtcDtaVo> list = etcDtaService.selectEtcDtaList(etcDtaVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 기타자료 등록
     *
     * @param etcDtaVo
     * @return map
     * @throws Exception
     * @Title : insertEtcDta
     * @Description : 기타자료 등록
     */
    @PostMapping("/mng/etcDta/insertEtcDta.do")
    @ResponseBody
    public Map<String, Object> insertEtcDta(@Valid EtcDtaVo etcDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();


        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        etcDtaVo.setUser(user);

        int retVal = 0;
        retVal = etcDtaService.insertEtcDta(etcDtaVo);

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
     * 기타자료 수정
     *
     * @param etcDtaVo
     * @return map
     * @throws Exception
     * @Title : updateEtcDta
     * @Description : 기타자료 수정
     */
    @PostMapping("/mng/etcDta/updateEtcDta.do")
    @ResponseBody
    public Map<String, Object> updateEtcDta(@Valid EtcDtaVo etcDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        etcDtaVo.setUser(user);

        int retVal = 0;
        retVal = etcDtaService.updateEtcDta(etcDtaVo);

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
     * 기타자료 삭제
     *
     * @param etcDtaVo
     * @return map
     * @throws Exception
     * @Title : deleteEtcDta
     * @Description : 기타자료 삭제
     */
    @PostMapping("/mng/etcDta/deleteEtcDta.do")
    @ResponseBody
    public Map<String, Object> deleteEtcDta(EtcDtaVo etcDtaVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        etcDtaVo.setUser(user);
        int retVal = 0;
        retVal = etcDtaService.deleteEtcDta(etcDtaVo);

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
