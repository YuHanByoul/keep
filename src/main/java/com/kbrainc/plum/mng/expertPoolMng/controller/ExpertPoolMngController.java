package com.kbrainc.plum.mng.expertPoolMng.controller;

import com.kbrainc.plum.mng.expertPoolMng.model.ExpertLogVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertPoolMngService;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 전문가 풀 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.controller
 * - ExpertPoolMngController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertPoolMngController
 * @Description : 전문가 풀 관리 컨트롤러 클래스
 * @date : 2022. 12. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ExpertPoolMngController {

    @Autowired
    private ExpertPoolMngService expertPoolMngService;

    @Value("${crypto.key}")
    private String encryptKey;

    @RequestMapping("/mng/expertPoolMng/expertList.html")
    public String expertList() throws Exception {
        return "mng/expertPoolMng/expertList";
    }

    @RequestMapping("/mng/expertPoolMng/selectExpertList.do")
    @ResponseBody
    public Map<String, Object> selectExpertList(ExpertVo expertVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ExpertVo> list = expertPoolMngService.selectExpertList(expertVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @RequestMapping("/mng/expertPoolMng/expertDetail.html")
    public String expertDetail(ExpertVo expertVo, Model model) throws Exception {
        model.addAttribute("expertInfo", expertVo);
        return "mng/expertPoolMng/expertDetail";
    }

    @RequestMapping("/mng/expertPoolMng/expertApplyInfoForm.html")
    public String expertApplyInfoForm(ExpertVo expertVo, Model model) throws Exception {
        ExpertVo expertApplyInfo = expertPoolMngService.selectExpertApplyInfo(expertVo);
        model.addAttribute("expertApplyInfo" , expertApplyInfo);
        return "mng/expertPoolMng/expertApplyInfoForm";
    }


    @RequestMapping("/mng/expertPoolMng/expertInfoForm.html")
    public String expertInfoForm(ExpertVo expertVo, Model model) throws Exception {

        ExpertVo expertInfo = expertPoolMngService.selectExpertInfo(expertVo);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(expertInfo.getGndr());

        expertInfo.setGndr(decStr);
        model.addAttribute("expertInfo", expertInfo);
        return "mng/expertPoolMng/expertInfoForm";
    }

    @RequestMapping("/mng/expertPoolMng/expertReviewHistoryForm.html")
    public String expertReviewHistoryForm() throws Exception {
        return "mng/expertPoolMng/expertReviewHistoryForm";
    }

    @RequestMapping("/mng/expertPoolMng/expertStatusChangeHistoryForm.html")
    public String expertStatusChangeHistoryForm() throws Exception {
        return "mng/expertPoolMng/expertStatusChangeHistoryForm";
    }

    @RequestMapping("/mng/expertPoolMng/updateExpertStatus.do")
    @ResponseBody
    public boolean updateExpertStatus(ExpertVo expertVo) throws Exception {
//        boolean b = expertPoolMngService.updateExpertStatus(expertVo);
        return true;
    }


    @RequestMapping("/mng/expertPoolMng/selectStatusChangeHistoryList.do")
    @ResponseBody
    public boolean selectStatusChangeHistoryList(ExpertVo expertVo) throws Exception {

        return true;
    }

    @RequestMapping("/mng/expertPoolMng/selectReviewHistoryList.do")
    @ResponseBody
    public boolean selectReviewHistoryList(ExpertVo expertVo) throws Exception {

        return true;
    }

    @RequestMapping("/mng/expertPoolMng/insertExpertLog.do")
    @ResponseBody
    public boolean insertExpertLog(ExpertLogVo expertLogVo, @UserInfo UserVo user) throws Exception {
        expertLogVo.setUser(user);
//        expertPoolMngService.insertExpertLog(expertLogVo);
        return true;
    }

    @RequestMapping("/mng/expertPoolMng/selectExpertLogList.do")
    public Map<String,Object> selectExpertList(ExpertLogVo expertLogVo) throws Exception {

        Map<String, Object> result = new HashMap<>();

        List<ExpertLogVo> list = expertPoolMngService.selectExpertLogList(expertLogVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }
}
