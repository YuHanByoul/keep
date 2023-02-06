package com.kbrainc.plum.mng.rsvtAply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.rsvtAply.model.FclAplyRsvtdeVo;
import com.kbrainc.plum.mng.rsvtAply.service.RsvtAplyService;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 예약 신청 캘린더 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.spce.controller - RsvtAplyController.java
 * </pre>
 *
 * @ClassName : RsvtAplyController
 * @Description : 예약 신청 캘린더 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class RsvtAplyController {

    @Autowired
    private RsvtAplyService rsvtAplyService;
    
    @Autowired
    private CommonService commonService;

    /**
     * 예약 신청 캘린더
     *
     * @Title : rsvtApplyCalendarForm
     * @Description : 예약 신청 캘린더
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rsvtApply/rsvtApplyCalendarForm.html")
    public String rsvtApplyCalendarForm(Model model) throws Exception {
        model.addAttribute("instList", commonService.selectAlowedInstList());      
        return "mng/rsvtAply/rsvtAplyCalendar";
    }
    
    /**
     * 시설 검색 조회.
     *
     * @Title : selectFcltList
     * @Description :설 검색 조회.
     * @param SpceVo SpceVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rsvtApply/selectSpceList.do")
    @ResponseBody
    public Map<String, Object> selectFcltLista(SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List< Map<String, Object>> result = null;
        result = rsvtAplyService.selectSpcetList(spceVo);
        resultMap.put("spceList", result);
        return resultMap;
    }
    
    /**
     * 예약신청 목록 조회.
     *
     * @Title : selectRsvtApplyList
     * @Description : 예약신청 목록 조회.
     * @param InstVo instVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rsvtApply/selectRsvtApplyList.do")
    @ResponseBody
    public Map<String, Object> selectRsvtApplyList(FclAplyRsvtdeVo fclAplyRsvtdeVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<FclAplyRsvtdeVo> result = null;
        fclAplyRsvtdeVo.setUser(user);
        
        result = rsvtAplyService.selectRsvtAplyList(fclAplyRsvtdeVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    
}