package com.kbrainc.plum.mng.inqry.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.inqry.model.*;
import com.kbrainc.plum.mng.inqry.service.InqryService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 문의 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.controller
 * - InqryController.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : InqryController
 * @Description : 문의 컨트롤러
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class InqryController {

    @Autowired
    private InqryService inqryService;

    /**
     * @return String 이동화면경로
     * @throws Exception
     * @Title : inqryForm
     * @Description : 1:1문의 답변관리 화면 이동
     */
    @RequestMapping(value = "/mng/inqry/inqryList.html")
    public String inqryList() throws Exception {
        return "mng/inqry/inqryList";
    }

    /**
     * @param inqryVO 1:1문의VO 클래스
     * @return Map<String, Object> 1:1문의 목록
     * @throws Exception
     * @Title : selectInqryList
     * @Description : 1:1문의 목록을 조회한다.
     */
    @RequestMapping(value = "/mng/inqry/selectInqryList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(InqryVo inqryVO, @UserInfo UserVo user) throws Exception {
        inqryVO.setUser(user);
        List<InqryVo> list = inqryService.selectInqryList(inqryVO);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
     * @return String 이동화면경로
     * @throws Exception
     * @Title : inqryDetailForm
     * @Description : 1:1문의 답변관리 상세화면 이동
     */
    @RequestMapping(value = "/mng/inqry/inqryForm.html")
    public String inqryForm(InqryVo inqryVO, Model model) throws Exception {

        InqryVo inqryInfo = inqryService.selectInqryInfo(inqryVO);
        InqryAnswrVo inqryAnswrInfo = inqryService.selectInqryAnswrInfo(inqryVO);
        List<ManagerVo> managerInfo = inqryService.selectManagerList(inqryVO);
        if (inqryAnswrInfo == null) {
            inqryAnswrInfo = new InqryAnswrVo();
        }

        model.addAttribute("inqryInfo", inqryInfo);
        model.addAttribute("inqryAnswrInfo", inqryAnswrInfo);
        model.addAttribute("managerInfo", managerInfo);

        if (!StringUtil.nvl(inqryInfo.getFilegrpid()).equals("") && !StringUtil.nvl(inqryInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(inqryInfo.getFilegrpid());
            model.addAttribute("fileList", inqryService.selectAttachFileList(fileVo));
        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        return "mng/inqry/inqryForm";
    }

    @RequestMapping(value = "/mng/inqry/deleteInqryInfo.do")
    @ResponseBody
    public Map<String, Object> deleteInqryInfo(@RequestParam("deleteInqryIds") String[] deleteInqryIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = inqryService.deleteInqryInfo(deleteInqryIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "삭제에 실패했습니다.");
        }

        return reseultMap;
    }

    /**
     * @param inqryAnswrVO  1:1문의답변VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @return Map<String, Object> 1:1문의답변 등록 결과
     * @throws Exception
     * @Title : insertInqryAnswr
     * @Description : 1:1문의답변 등록
     */
    @RequestMapping(value = "/mng/inqry/insertInqryAnswr.do")
    @ResponseBody
    public Map<String, Object> insertInqryAnswr(@Valid InqryAnswrVo inqryAnswrVO, BindingResult bindingResult,
                                                @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        inqryAnswrVO.setUser(user);

        int retVal = 0;

        retVal = inqryService.insertInqryAnswr(inqryAnswrVO);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    /**
     * @param inqryAnswrVO  1:1문의답변VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @return Map<String, Object> 1:1문의답변 수정 결과
     * @throws Exception
     * @Title : updateInqryAnswr
     * @Description : 1:1문의답변 수정
     */
    @RequestMapping(value = "/mng/inqry/updateInqryAnswr.do")
    @ResponseBody
    public Map<String, Object> updateInqryAnswr(@Valid InqryAnswrVo inqryAnswrVO, BindingResult bindingResult,
                                                @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        inqryAnswrVO.setUser(user);
        int retVal = 0;

        retVal = inqryService.updateInqryAnswr(inqryAnswrVO);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }

    @RequestMapping("/mng/inqry/telInqryList.html")
    public String telInqryList() throws Exception {
        return "mng/inqry/telInqryList.html";
    }

    @RequestMapping("/mng/inqry/telInqryForm.html")
    public String telInqryForm(TelInqryVo telInqryVo, Model model, @UserInfo UserVo user) throws Exception {
        telInqryVo.setUser(user);
        TelInqryVo telInqryInfo = inqryService.selectTelInqryInfo(telInqryVo);
        List<ManagerVo> managerInfo = inqryService.selectManagerList(telInqryVo);

        if (telInqryInfo == null) telInqryInfo = new TelInqryVo();
        model.addAttribute("telInqryInfo", telInqryInfo);
        model.addAttribute("managerInfo", managerInfo);
        return "mng/inqry/telInqryForm.html";
    }

    @RequestMapping("/mng/inqry/telInqryMemberSearchPopup.html")
    public String telInqryMemberSearchPopup(TelInqryVo telInqryVo, Model model) throws Exception {
        return "mng/inqry/telInqryMemberSearchPopup.html";
    }

    @RequestMapping(value = "/mng/inqry/selectTelInqryList.do")
    @ResponseBody
    public Map<String, Object> selectTelInqryList(TelInqryVo telInqryVo, @UserInfo UserVo user) throws Exception {
        telInqryVo.setUser(user);
        List<TelInqryVo> list = inqryService.selectTelInqryList(telInqryVo);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/inqry/insertTelInqry.do")
    @ResponseBody
    public Map<String, Object> insertTelInqry(@Valid TelInqryVo telInqryVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        telInqryVo.setUser(user);

        int retVal = 0;

        retVal = inqryService.insertTelInqry(telInqryVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }
        return map;
    }

    @RequestMapping("/mng/inqry/updateTelInqry.do")
    @ResponseBody
    public Map<String, Object> updateTelInqry(@Valid TelInqryVo telInqryVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        telInqryVo.setUser(user);

        int retVal = 0;

        retVal = inqryService.updateTelInqry(telInqryVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }

    @RequestMapping(value = "/mng/inqry/selectMemberList.do")
    @ResponseBody
    public Map<String, Object> selectMemberList(TelInqryVo telInqryVo, @UserInfo UserVo user) throws Exception {
        telInqryVo.setUser(user);

        List<PopupMemberVo> list = inqryService.selectMemberList(telInqryVo);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping("/mng/inqry/managerSearchPopup.html")
    public String managerSearchPopup() throws Exception {
        return "/mng/inqry/managerSearchPopup.html";
    }


}