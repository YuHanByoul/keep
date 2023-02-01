package com.kbrainc.plum.mng.expertPoolMng.controller;

import com.kbrainc.plum.mng.expertPoolMng.model.ExpertRelationVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertPoolMngService;
import com.kbrainc.plum.mng.expertPoolMng.service.ExpertRelationMngService;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.service.MemberService;
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
 * 전문가 섭외 현황 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.controller
 * - ExpertRelationMngController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertRelationMngController
 * @Description : 전문가 섭외 현황 컨트롤러
 * @date : 2023. 01. 05.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ExpertRelationMngController {

    @Autowired
    private ExpertRelationMngService expertRelationMngService;

    @Autowired
    private ExpertPoolMngService expertPoolMngService;

    @Autowired
    private MemberService memberService;

    @Value("${crypto.key}")
    private String encryptKey;

    /**
     * 전문가 섭외 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : expertRelationList
     * @Description : 전문가 섭외 목록 화면
     */
    @RequestMapping("/mng/expertPoolMng/expertRelationList.html")
    public String expertRelationList() throws Exception {
        return "mng/expertPoolMng/expertRelationList.html";
    }

    /**
     * 전문가 섭외 목록 조회
     *
     * @param expertRelationVo
     * @return map
     * @throws Exception
     * @Title : selectExpertRelationList
     * @Description : 전문가 섭외 목록 조회
     */
    @RequestMapping("/mng/expertPoolMng/selectExpertRelationList.do")
    @ResponseBody
    public Map<String, Object> selectExpertRelationList(ExpertRelationVo expertRelationVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<ExpertRelationVo> list = expertRelationMngService.selectExpertRelationList(expertRelationVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 섭외 정보 상세 탭 이동
     *
     * @param expertRelationVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertRelationDetail
     * @Description : 섭외 정보 상세 탭 이동
     */
    @RequestMapping("/mng/expertPoolMng/expertRelationDetail.html")
    public String expertRelationDetail(ExpertRelationVo expertRelationVo, Model model) throws Exception {
        model.addAttribute("expertRelationInfo", expertRelationVo);
        return "mng/expertPoolMng/expertRelationDetail.html";
    }

    /**
     * 매칭정보 화면 이동
     *
     * @param expertRelationVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertMatchForm
     * @Description : 매칭정보 화면 이동
     */
    @RequestMapping("/mng/expertPoolMng/expertMatchForm.html")
    public String expertMatchForm(ExpertRelationVo expertRelationVo, Model model) throws Exception {
        ExpertRelationVo expertRelationInfo = expertRelationMngService.selectExpertRelationInfo(expertRelationVo);

        model.addAttribute("expertLctrDmndInfo", expertRelationInfo);
        ExpertVo expertVo = new ExpertVo();
        expertVo.setUserid(expertRelationInfo.getExprtid());
        ExpertVo expertInfo = expertPoolMngService.selectExpertApplyInfo(expertVo);
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(expertInfo.getGndr());
        expertInfo.setGndr(decStr);
        model.addAttribute("expertInfo", expertInfo);

        MemberVo memberVo = new MemberVo();
        memberVo.setUserid(expertRelationInfo.getUserid());
        MemberVo memberInfo = memberService.selectMemberInfo(memberVo);

        model.addAttribute("memberInfo", memberInfo);

        return "mng/expertPoolMng/expertMatchForm.html";
    }

    /**
     * 강의요청서 화면 이동
     *
     * @param expertRelationVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : expertLctrDmndForm
     * @Description : 강의요청서 화면 이동
     */
    @RequestMapping("/mng/expertPoolMng/expertLctrDmndForm.html")
    public String expertLctrDmndForm(ExpertRelationVo expertRelationVo, Model model) throws Exception {
        ExpertRelationVo expertRelationInfo = expertRelationMngService.selectExpertRelationInfo(expertRelationVo);
        model.addAttribute("expertRelationInfo", expertRelationInfo);
        return "mng/expertPoolMng/expertLctrDmndForm.html";
    }
}
