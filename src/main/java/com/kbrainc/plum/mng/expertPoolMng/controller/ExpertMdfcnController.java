package com.kbrainc.plum.mng.expertPoolMng.controller;

import com.kbrainc.plum.mng.expertPoolMng.model.*;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertMdfcnService;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertPoolMngService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 전문가 정보변경 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.controller
 * - ExpertMdfcnController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertMdfcnController
 * @Description : 전문가 정보변경 관리 컨트롤러 클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/expertPoolMng")
public class ExpertMdfcnController {

    @Autowired
    private ExpertMdfcnService expertMdfcnService;

    @Autowired
    private ExpertPoolMngService expertPoolMngService;

    @RequestMapping("/expertMdfcnList.html")
    public String expertMdfcnList() throws Exception {
        return "mng/expertPoolMng/expertMdfcnList.html";
    }

    @RequestMapping("/selectExpertMdfcnList.do")
    @ResponseBody
    public Map<String, Object> selectExpertMdfcnList(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ExpertMdfcnVo> list = expertMdfcnService.selectExpertMdfcnList(expertMdfcnVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @RequestMapping("/expertMdfcnDetail.html")
    public String expertMdfcnDetail(ExpertMdfcnVo expertMdfcnVo, Model model) throws Exception {
        model.addAttribute("expertMdfcnInfo", expertMdfcnService.selectExpertMdfcn(expertMdfcnVo));
        ExpertVo expertVo = new ExpertVo();
        expertVo.setUserid(expertMdfcnVo.getUserid());

        ExpertVo existingExpertInfo = expertPoolMngService.selectExpertApplyInfo(expertVo);
        model.addAttribute("existingExpertInfo", existingExpertInfo);
        ExpertVo expertVo1 = expertMdfcnService.selectExpertMdfcnInfo(expertMdfcnVo);

        for (ExpertCareerVo expertCareerVo : expertVo1.getExpertCareerList()) {
            for (ExpertCareerVo item : existingExpertInfo.getExpertCareerList()) {
            }
        }
        for (ExpertCrtfctVo expertCrtfctVo : expertVo1.getExpertCrtfctList()) {

        }
        for (ExpertHdofVo expertHdofVo : expertVo1.getExpertHdofList()) {
        }
        model.addAttribute("newExpertInfo",expertVo1);
        return "mng/expertPoolMng/expertMdfcnDetail.html";
    }

    @RequestMapping("/updateSttsCd.do")
    @ResponseBody
    public boolean updateSttsCd(ExpertMdfcnVo expertMdfcnVo, ExpertLogVo expertLogVo, @UserInfo UserVo user) throws Exception {
        expertLogVo.setUser(user);
        /*#{userid}
             , #{prcsSeCd}
             , #{rsnCn}
             , NOW()
             , #{user.userid}*/
        return expertMdfcnService.updateSttsCd(expertMdfcnVo);
    }
}
