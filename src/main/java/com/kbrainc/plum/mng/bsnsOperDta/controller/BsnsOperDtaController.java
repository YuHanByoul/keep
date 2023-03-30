package com.kbrainc.plum.mng.bsnsOperDta.controller;

import com.kbrainc.plum.mng.bsnsOperDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.mng.bsnsOperDta.service.BsnsOperDtaService;
import com.kbrainc.plum.mng.inqry.model.InqryVo;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 사업운영자료 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bsnsOperDta.controller
 * - BsnsOperDtaController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaController
 * @Description : 사업운영자료 컨트롤러 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class BsnsOperDtaController {
    @Autowired
    private BsnsOperDtaService bsnsOperDtaService;

    @Autowired
    private InstService instService;

    /**
     * 사업운영자료 관리 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : bsnsOperDta
     * @Description : 사업운영자료 관리 목록 화면
     */
    @GetMapping("/mng/bsnsOperDta/bsnsOperDtaList.html")
    public String bsnsOperDta() throws Exception {
        return "/mng/bsnsOperDta/bsnsOperDtaList";
    }

    /**
     * 사업운영자료 관리 상세 화면
     *
     * @param bsnsOperDtaVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : bsnsOperDtaForm
     * @Description : 사업운영자료 관리 상세 화면
     */
    @GetMapping("/mng/bsnsOperDta/bsnsOperDtaForm.html")
    public String bsnsOperDtaForm(BsnsOperDtaVo bsnsOperDtaVo, @UserInfo UserVo user,  Model model) throws Exception {
        BsnsOperDtaVo bsnsOperDta = bsnsOperDtaService.selectBsnsOperDta(bsnsOperDtaVo);
        bsnsOperDta.setUser(user);
        model.addAttribute("bsnsOperDta", bsnsOperDta);
        return "/mng/bsnsOperDta/bsnsOperDtaForm";
    }

    /**
     * 기관검색 팝업 화면
     *
     * @return string
     * @throws Exception
     * @Title : instSearchPopup
     * @Description : 기관검색 팝업 화면
     */
    @GetMapping("/mng/bsnsOperDta/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        return "/mng/bsnsOperDta/instSearchPopup";
    }


    /**
     * 사업운영자료 목록 조회
     *
     * @param bsnsOperDtaVo
     * @return map
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @GetMapping("/mng/bsnsOperDta/selectBsnsOperDtaList.do")
    @ResponseBody
    public Map<String, Object> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<BsnsOperDtaVo> list = bsnsOperDtaService.selectBsnsOperDtaList(bsnsOperDtaVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 사업운영자료 등록
     *
     * @param bsnsOperDtaVo
     * @return map
     * @throws Exception
     * @Title : insertBsnsOperDta
     * @Description : 사업운영자료 등록
     */
    @PostMapping("/mng/bsnsOperDta/insertBsnsOperDta.do")
    @ResponseBody
    public Map<String, Object> insertBsnsOperDta(@Valid BsnsOperDtaVo bsnsOperDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();


        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        bsnsOperDtaVo.setUser(user);

        int retVal = 0;
        retVal = bsnsOperDtaService.insertBsnsOperDta(bsnsOperDtaVo);

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
     * 사업운영자료 수정
     *
     * @param bsnsOperDtaVo
     * @return map
     * @throws Exception
     * @Title : updateBsnsOperDta
     * @Description : 사업운영자료 수정
     */
    @PostMapping("/mng/bsnsOperDta/updateBsnsOperDta.do")
    @ResponseBody
    public Map<String, Object> updateBsnsOperDta(@Valid BsnsOperDtaVo bsnsOperDtaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }
        bsnsOperDtaVo.setUser(user);

        int retVal = 0;
        retVal = bsnsOperDtaService.updateBsnsOperDta(bsnsOperDtaVo);

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
     * 사업운영자료 삭제
     *
     * @param bsnsOperDtaVo
     * @return map
     * @throws Exception
     * @Title : deleteBsnsOperDta
     * @Description : 사업운영자료 삭제
     */
    @PostMapping("/mng/bsnsOperDta/deleteBsnsOperDta.do")
    @ResponseBody
    public Map<String, Object> deleteBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        int retVal = 0;
        retVal = bsnsOperDtaService.deleteBsnsOperDta(bsnsOperDtaVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "삭제에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "삭제에 실패했습니다.");
        }

        return result;
    }

    /**
     * 기관 목록 조회
     *
     * @param instVo
     * @return map
     * @throws Exception
     * @Title : selectInstList
     * @Description : 기관 목록 조회
     */
    @GetMapping("/mng/bsnsOperDta/selectInstList.do")
    @ResponseBody
    public Map<String, Object> selectInstList(InstVo instVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        instVo.setUser(user);
        List<InstVo> list = instService.selectInstList(instVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

}
