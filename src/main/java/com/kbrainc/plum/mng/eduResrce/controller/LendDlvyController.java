package com.kbrainc.plum.mng.eduResrce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.lend.service.LendService;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 대여 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.LendDlvyController - LendController.java
 * </pre>
 *
 * @ClassName : LendDlvyController
 * @Description :  대여 출고 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 03. 20.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class LendDlvyController {

    @Autowired
    private LendService lendService;
    
    /**
     * 대여 신청관리
     *
     * @Title : lendAplyList
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/eduResrceMain.html")
    public String eduResrceMain(LendAplyVo lendAplyVo,Model model) throws Exception {
        return "mng/eduResrce/eduRersceMain";
    }
    /**
     * 출고 대여 신청 리스트 화면 
     *
     * @Title : lendAplyList
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/dlvyList.html")
    public String dlvyList(LendVo lendVo,Model model) throws Exception {
        model.addAttribute("rndList",lendService.selectLendRcritRndList(lendVo));
        return "mng/eduResrce/dlvy/lendDlvyList";
    }
    /**
     * 출고 대여신청 리스트 호출  
     *
     * @Title : selectLendAplyList
     * @Description : 대여 신청관리 목록 호출 
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/selectLendAplyDlvyList.do")
    @ResponseBody
    public Map<String, Object> selectLendAplyDlvyList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<LendAplyVo> result = null;
        lendAplyVo.setUser(user);
        
        result = lendService.selectLendAplyList(lendAplyVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            //resultMap.put("pagination",PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 출고 대여 관리 상세 화면
     *
     * @Title : dlvyDetailForm
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/dlvyDetailForm.html")
    public String dlvyDetailForm(LendAplyVo lendAplyVo,Model model) throws Exception {
        model.addAttribute("lendAplyInfo", lendService.selectLendAplyInfo(lendAplyVo));
        return "mng/eduResrce/dlvy/dlvyDetailForm";
    }
    
    /**
     * 출고 대여 관리 꾸러미 개체 선택 팝업 
     *
     * @Title : selectPackageindvdPopup
     * @Description : 출고 대여 관리 꾸러미 개체 선택 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/selectPackageindvdPopup.html")
    public String selectPackageindvdPopup(LendAplyVo lendAplyVo,Model model) throws Exception {
        model.addAttribute("params", lendAplyVo);
        return "mng/eduResrce/dlvy/selectPackageindvdPopup";
    }
    
    /**
     * 출고 꾸러미 개체 검색 목록 호출   
     *
     * @Title : selectLendAplyList
     * @Description : 대여 신청관리 목록 호출 
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/selectPackageindvdList.do")
    @ResponseBody
    public Map<String, Object> selectPackageindvdList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageindvdVo> result = null;
        packageindvdVo.setUser(user);
        
        result = lendService.searchPackageindvdList(packageindvdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.size());
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    /**
     * 출고 꾸러미 개체 등록
     * 
     * @Title : insertPackageindvdDlvy
     * @Description : 출고 꾸러미 개체 등록
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduResrce/insertPackageindvdDlvy.do")
    @ResponseBody
    public Map<String, Object> insertPackageindvdDlvy(@Valid LendAplyVo lendAplyVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        lendAplyVo.setUser(user);
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        retVal = lendService.insertLendAplyDlivy(lendAplyVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        return resultMap;
    }
    
}