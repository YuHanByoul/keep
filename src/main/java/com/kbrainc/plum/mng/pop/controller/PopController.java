package com.kbrainc.plum.mng.pop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.pop.model.PopUpNtcVo;
import com.kbrainc.plum.mng.pop.service.PopServiceImpl;

/**
 * 
 * 팝업 공지사항 관리 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.controller
 * - PopController.java
 * </pre> 
 *
 * @ClassName : PopController
 * @Description : 팝업 곤지사항 관리 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class PopController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private PopServiceImpl popService;

    /**
     * @Title : popupForm
     * @Description : user관리 리스트화면 이동
     * @param
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/pop/popupForm.html")
    public String popupForm(SiteVo site, Model model) throws Exception {
        List<SiteVo> siteList = commonService.selectSiteList(site);
        model.addAttribute("siteList", siteList);
        return "mng/pop/popupForm";
    }

    /**
     * @Title : popupInsertForm
     * @Description : user관리 리스트화면 이동
     * @param
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/pop/popupInsertForm.html")
    public String popupInsertForm(SiteVo site, Model model) throws Exception {
        List<SiteVo> siteList = commonService.selectSiteList(site);
        model.addAttribute("siteList", siteList);
        return "mng/pop/popupInsert";
    }

    /**
     * @Title : selectPopUpNtcList
     * @Description : 팝업공지사항 리스트 가져오기
     * @param BbsVO
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/pop/selectPopUpNtcList.do")
    public @ResponseBody Map<String, Object> selectPopUpNtcList(PopUpNtcVo paramVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PopUpNtcVo> result = new ArrayList<PopUpNtcVo>();
        try {

            result = popService.selectPopUpNtcList(paramVO);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
            } else {
                resultMap.put("totalCount", 0);
            }
            resultMap.put("list", result);
        } catch (Exception e) {
            log.error("selectPopUpNtcList.Exception.103L");
        }
        return resultMap;
    }

    /**
     * @Title : insertPopUpNtc
     * @Description : 팝업공지사항 등록
     * @param inqryAnswrVO
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/pop/insertPopUpNtc.do")
    @ResponseBody
    public Map<String, Object> insertPopUpNtc(PopUpNtcVo paramVO, BindingResult bindingResult, @UserInfo UserVo user)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        paramVO.setUser(user);

        int retVal = 0;
        retVal = popService.insertPopUpNtc(paramVO);
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
     * @Title : updatePopUpNtc
     * @Description : 팝업공지사항 업데이트
     * @param PopUpNtcVo
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/pop/updatePopUpNtc.do")
    @ResponseBody
    public Map<String, Object> updatePopUpNtc(PopUpNtcVo paramVO, BindingResult bindingResult, @UserInfo UserVo user)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        paramVO.setUser(user);
        
        int retVal = 0;
        retVal = popService.updatePopUpNtc(paramVO);
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정 실패했습니다.");
        }
        return map;
    }

    /**
     * @Title : popupUpdateForm
     * @Description : popupUpdateForm화면 이동
     * @param PopUpNtcVo 인자
     * @param Model      인자
     * @param SiteVo     인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/pop/popupUpdateForm.html")
    public String popupUpdateForm(PopUpNtcVo paramVO, Model model, SiteVo site) throws Exception {
        List<SiteVo> siteList = commonService.selectSiteList(site);
        model.addAttribute("siteList", siteList);
        model.addAttribute("paramVO", popService.selectPopUpNtc(paramVO));
        return "mng/pop/popupModify";
    }

    /**
     * @Title : deletePopUpNtc
     * @Description : 팝업공지사항 삭제
     * @param PopUpNtcVo
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/pop/deletePopUpNtc.do")
    @ResponseBody
    public Map<String, Object> deletePopUpNtc(PopUpNtcVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        paramVO.setUser(user);
        
        int retVal = 0;
        retVal = popService.deletePopUpNtc(paramVO);
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "삭제 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "삭제 실패했습니다.");
        }
        return map;
    }

    /**
     * @Title : openMenuAndRolPopup
     * @Description : 메뉴 및 역할 선택 팝업창 화면 이동
     * @param UserTempVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/pop/openPop.html")
    public String openMenuAndRolPopup() throws Exception {
        return "mng/pop/popntcPopUp";
    }
    
    /**
     * @Title : deletePopUpNtc
     * @Description : 팝업공지사항 삭제
     * @param PopUpNtcVo
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/pop/getDataForCommnonPopup.do")
    @ResponseBody
    public Map<String, Object> getDataForCommnonPopup(PopUpNtcVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        paramVO.setUser(user);
        List<PopUpNtcVo> list = popService.getDataForCommnonPopup(paramVO, user);
        map.put("popupList", list);
        return map;
    }
    
    /**
     * @Title : getPopup
     * @Description : openCommonPopup 
     * @param UserTempVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/pop/getPopup.html")
    public String getPopup(@RequestParam(name="popupntcid",required=true) int popupntcid, PopUpNtcVo paramVO, Model model) throws Exception {
    	model.addAttribute("item", popService.selectPopUpNtc(paramVO));
        return "layout/mng/commonPopup";
    }

}
