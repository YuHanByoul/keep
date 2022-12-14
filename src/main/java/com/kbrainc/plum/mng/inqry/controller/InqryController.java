package com.kbrainc.plum.mng.inqry.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.inqry.model.InqryAnswrVo;
import com.kbrainc.plum.mng.inqry.model.InqryVo;
import com.kbrainc.plum.mng.inqry.service.InqryService;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

import javax.validation.Valid;

/**
 * 
 * 1:1문의 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.controller
 * - InqryController.java
 * </pre> 
 *
 * @ClassName : InqryController
 * @Description : 1:1문의 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class InqryController {

    @Autowired
    private InqryService inqryService;
    
    /**
     * @Title : inqryMgntForm
     * @Description : 1:1문의 답변관리 화면 이동
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/inqry/inqryMgntForm.html")
    public String inqryMgntForm() throws Exception {
        return "mng/inqry/inqryMgntForm";
    }

    /**
     * @Title : getInqryList
     * @Description : 1:1문의 목록을 조회한다.
     * @param inqryVO 1:1문의VO 클래스
     * @throws Exception
     * @return Map<String,Object> 1:1문의 목록
     */
    @RequestMapping(value = "/mng/inqry/selectInqryList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(InqryVo inqryVO) throws Exception {

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
     * @Title : inqryDetailForm
     * @Description : 1:1문의 답변관리 상세화면 이동
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/inqry/inqryDetailForm.html")
    public String inqryDetailForm(InqryVo inqryVO, Model model) throws Exception {

        InqryVo inqryInfo = inqryService.selectInqryInfo(inqryVO);
        InqryAnswrVo inqryAnswrInfo = inqryService.selectInqryAnswrInfo(inqryVO);

        if (inqryAnswrInfo == null) {
            inqryAnswrInfo = new InqryAnswrVo();
        }

        model.addAttribute("inqryInfo", inqryInfo);
        model.addAttribute("inqryAnswrInfo", inqryAnswrInfo);
        
        if(!StringUtil.nvl(inqryInfo.getFilegrpid()).equals("") && !StringUtil.nvl(inqryInfo.getFilegrpid()).equals(0)) {
        	 FileVo fileVo = new FileVo();
             fileVo.setFilegrpid(inqryInfo.getFilegrpid());
             model.addAttribute("fileList",inqryService.selectAttachFileList(fileVo));
        }else {
        	 model.addAttribute("fileList", Collections.emptyList());
        }
        
        return "mng/inqry/inqryDetailForm";
    }

    @RequestMapping(value="/mng/inqry/deleteInqryInfo.do")
    @ResponseBody
    public Map<String,Object> deleteInqryInfo(@RequestParam("deleteInqryIds") String[] deleteInqryIds, @UserInfo UserVo user) throws Exception {
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
     * @Title : insertInqryAnswr
     * @Description : 1:1문의답변 등록
     * @param inqryAnswrVO  1:1문의답변VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object> 1:1문의답변 등록 결과
     */
    @RequestMapping(value = "/mng/inqry/insertInqryAnswr.do")
    @ResponseBody
    public Map<String, Object> insertInqryAnswr(InqryAnswrVo inqryAnswrVO, BindingResult bindingResult,
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
     * @Title : updateInqryAnswr
     * @Description : 1:1문의답변 수정
     * @param inqryAnswrVO  1:1문의답변VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object> 1:1문의답변 수정 결과
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
}