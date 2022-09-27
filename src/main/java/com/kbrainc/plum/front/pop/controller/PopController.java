package com.kbrainc.plum.front.pop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.pop.model.PopUpNtcVo;
import com.kbrainc.plum.front.pop.service.PopServiceImpl;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

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
 * @Description : 팝업 공지사항 관리 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.popController")
@Alias("front.popController")
public class PopController {

    @Resource(name = "front.popServiceImpl")
    private PopServiceImpl popService;

    /**
     * @Title : getDataForCommnonPopup
     * @Description : 팝업공지사항 삭제
     * @param PopUpNtcVo
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/pop/getDataForCommnonPopup.do")
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
    @RequestMapping(value = "/front/pop/getPopup.html")
    public String getPopup(@RequestParam(name="popupntcid",required=true) int popupntcid, PopUpNtcVo paramVO, Model model) throws Exception {
    	model.addAttribute("item", popService.selectPopUpNtc(paramVO));
        return "layout/front/commonPopup";
    }

}
