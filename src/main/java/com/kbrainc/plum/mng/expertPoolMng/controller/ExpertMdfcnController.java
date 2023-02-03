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

    /**
     * 전문가 정보변경 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : expertMdfcnList
     * @Description : 전문가 정보변경 목록 화면
     */
    @RequestMapping("/expertMdfcnList.html")
    public String expertMdfcnList() throws Exception {
        return "mng/expertPoolMng/expertMdfcnList.html";
    }

    /**
     * 전문가 정보변경 목록 조회
     *
     * @param expertMdfcnVo
     * @return map
     * @throws Exception
     * @Title : selectExpertMdfcnList
     * @Description : 전문가 정보변경 목록 조회
     */
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

    /**
     * 전문가 정보변경 상세화면
     *
     * @param expertMdfcnVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertMdfcnDetail
     * @Description : 전문가 정보변경 상세화면
     */
    @RequestMapping("/expertMdfcnDetail.html")
    public String expertMdfcnDetail(ExpertMdfcnVo expertMdfcnVo, Model model) throws Exception {
        model.addAttribute("expertMdfcnInfo", expertMdfcnService.selectExpertMdfcn(expertMdfcnVo));
        model.addAttribute("existingExpertInfo", expertMdfcnService.selectExistingExpertInfo(expertMdfcnVo));
        model.addAttribute("newExpertInfo", expertMdfcnService.selectNewExpertInfo(expertMdfcnVo));
        return "mng/expertPoolMng/expertMdfcnDetail.html";
    }

    /**
     * 전문가 정보변경 상태변경
     *
     * @param expertMdfcnVo
     * @param expertLogVo
     * @param user
     * @return int
     * @throws Exception
     * @Title : updateMdfcnStts
     * @Description : 전문가 정보변경 상태변경
     */
    @RequestMapping("/updateMdfcnStts.do")
    @ResponseBody
    public int updateMdfcnStts(ExpertMdfcnVo expertMdfcnVo, ExpertLogVo expertLogVo, @UserInfo UserVo user) throws Exception {
        expertLogVo.setUser(user);
        return expertMdfcnService.updateStts(expertMdfcnVo, expertLogVo);
    }
}
