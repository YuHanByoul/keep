package com.kbrainc.plum.front.mypage.exprtPool.controller;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyExprtMdfcnVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyExprtVo;
import com.kbrainc.plum.front.mypage.exprtPool.service.MyExprtPoolService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 마이페이지 > 환경교육 전문가 풀 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool
 * - MyExprtPoolController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtPoolController
 * @Description : 마이페이지 > 환경교육 전문가 풀 컨트롤러 클래스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/exprtPool")
public class MyExprtPoolController {
    private static final String VIEW_PATH = "/front/mypage/exprtPool";

    @Autowired
    MyExprtPoolService myExprtPoolService;

    /**
     * 전문가 이력 정보 화면
     *
     * @param exprtVo
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : exprtDetail
     * @Description : 전문가 이력 정보 화면
     */
    @GetMapping("/exprtDetail.html")
    public String exprtDetail(MyExprtVo exprtVo, @UserInfo UserVo user, Model model) throws Exception {
        exprtVo.setUser(user);
        MyExprtVo exprt = myExprtPoolService.selectMyExprt(exprtVo);
        model.addAttribute("exprt", exprt);
        return VIEW_PATH + "/exprtDetail";
    }

    @GetMapping("/exprtMdfcnForm.html")
    public String exprtMdfcnForm(MyExprtVo exprtVo, @UserInfo UserVo user, Model model) throws Exception {
        exprtVo.setUser(user);
        MyExprtMdfcnVo exprt = myExprtPoolService.selectMyExprtMdfcn(exprtVo);
        model.addAttribute("exprt", exprt);

        return VIEW_PATH + "/exprtMdfcnForm";
    }

    @PostMapping("/updateRlsAndRcptn.do")
    @ResponseBody
    public Map<String, Object> updateRlsAndRcptn(MyExprtVo exprtVo, @UserInfo UserVo user) throws Exception {
        exprtVo.setUser(user);

        Map<String, Object> response = new HashMap<>();

        boolean successInsert = myExprtPoolService.updateRlsAndRcptn(exprtVo) > 0;

        if (successInsert) {
            response.put("msg", "변경이 완료되었습니다.");
        } else {
            response.put("msg", "변경이 실패하였습니다.");
        }

        response.put("success", successInsert);

        return response;
    }
}
