package com.kbrainc.plum.mng.cmmCntnts.controller;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 콘텐츠 품질관리 체크리스트 컨트롤러 클래스
 * *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cmmCntnts.controller
 * - CmmCntntsController.java
 * </pre>
 * *
 *
 * @author : 이한명
 * @ClassName : CmmCntntsController
 * @Description : TODO
 * @date : 2023. 4. 19.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class CmmCntntsController {

    @Autowired
    private CmmCntntsService cmmCntntsService;


    /**
     * 콘텐츠 품질관리 체크리스트 팝업으로 이동
     *
     * @param model   객체
     * @param request 객체
     * @return String
     * @throws Exception 예외
     * @Title : cmmCntntsQlityPopup
     * @Description : 콘텐츠 품질관리 체크리스트 팝업으로 이동
     */
    @RequestMapping(value = "/mng/cmmCntnts/cmmCntntsQlityPopup.html")
    public String cmmCntntsQlityPopup(Model model, HttpServletRequest request) throws Exception {
        String functionName = request.getParameter("functionName");
        String trgtCd = request.getParameter("trgtCd");
        String evntCd = request.getParameter("evntCd");
        String cntntsid = request.getParameter("cntntsid");

        model.addAttribute("functionName", functionName);
        model.addAttribute("trgtCd", trgtCd);
        model.addAttribute("evntCd", evntCd);
        model.addAttribute("cntntsid", cntntsid);

        return "mng/cmmCntnts/cmmCntntsQlityPopup";
    }

    /**
     * 콘텐츠 품질관리 체크리스트 목록 조회
     *
     * @param cmmCntntsVo 콘텐츠 품질관리 체크리스트 객체
     * @return Map<String, Object>
     * @throws Exception
     * @Title : selectCmmCntntsQlityChklstList
     * @Description : 콘텐츠 품질관리 체크리스트 목록 조회
     */
    @RequestMapping(value = "/mng/cmmCntnts/selectCmmCntntsQlityChklstList.do")
    @ResponseBody
    public Map<String, Object> selectCmmCntntsQlityChklstList(CmmCntntsVo cmmCntntsVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmmCntntsVo> result = null;
        Integer checkid = null;
        cmmCntntsVo.setUser(user);

        checkid = cmmCntntsService.selectCmmCntntsQlityChkId(cmmCntntsVo);

        if (null != checkid) {
            cmmCntntsVo.setCheckid(checkid);
        }
        result = cmmCntntsService.selectCmmCntntsQlityChklstList(cmmCntntsVo);

        resultMap.put("chklist", result);

        return resultMap;
    }
}
