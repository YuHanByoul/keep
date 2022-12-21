package com.kbrainc.plum.mng.siteApply.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyMenuVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyVo;
import com.kbrainc.plum.mng.siteApply.service.SiteApplyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 사이트관리 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.mng.siteApply.controller 
 * - SiteApplyController.java
 * </pre> 
 *
 * @ClassName : SiteApplyController
 * @Description : 사이트관리 컨트롤러.
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping
@Slf4j
public class SiteApplyController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    SiteApplyService siteApplyService;
    
    
    /**
     * @Title : siteApplyListForm
     * @Description : 사이트 신청 관리 리스트화면 이동
     * @param 
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/siteApply/siteApplyList.html")
    public String siteApplyListForm(SiteVo site, Model model) throws Exception {
        return "mng/siteApply/siteApplyList";
    }
    
    /**
     * @Title : selectSiteApplyList
     * @Description : 사이트 신청 리스트 가져오기
     * @param SiteApplyVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/siteApply/selectSiteApplyList.do")
    public @ResponseBody Map<String, Object> selectSiteApplyList(SiteApplyVo siteApplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SiteApplyVo> result = new ArrayList<SiteApplyVo>();
        try {

            result = siteApplyService.selectSiteApplyList(siteApplyVo);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
            } else {
                resultMap.put("totalCount", 0);
            }
            resultMap.put("list", result);
        } catch (SQLException e) {
            log.error("selectPopUpNtcList.SQLException.103L");
        } catch (Exception e) {
            log.error("selectPopUpNtcList.Exception.103L");
        }
        return resultMap;
    }
    
    /**
     * @Title : siteApplyInfoForm
     * @Description : siteApplyInfoForm화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @param SiteApplyVo     인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/siteApply/siteApplyInfoForm.html")
    public String siteApplyInfoForm(SiteApplyVo siteApplyVo, Model model, @UserInfo UserVo user) throws Exception {
        
        siteApplyVo.setUser(user);
        SiteApplyVo result = siteApplyService.selectSiteApplyInfo(siteApplyVo);
        List<SiteApplyMenuVo> menuList = siteApplyService.selectSiteApplyMenuList(siteApplyVo);
        
        model.addAttribute("siteApplyMenuList", menuList);
        model.addAttribute("siteApplyVo", result);
        
        return "mng/siteApply/siteApplyForm";
    }
    
    /**
     * @Title : updateSiteApplyStatus
     * @Description : 사이트 신청 상태 변경
     * @param SiteApplyVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/siteApply/updateSiteApplyStatus.do")
    @ResponseBody
    public Map<String, Object> updateSiteApplyStatus(SiteApplyVo siteApplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        int retVal = 0;

        siteApplyVo.setUser(user);
        retVal = siteApplyService.updateSiteApplyStatus(siteApplyVo);
        
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "상태 변경에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "상태 변경에 실패했습니다.");
        }
        return map;
    }
    
    
    
    

}
