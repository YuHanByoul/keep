package com.kbrainc.plum.front.intro.envEduPlcyAndBiz.controller;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 환경교육 제도 사업 컨트롤러 서비스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyAndBiz.controller
 * - envEduPlcyAndBizController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : envEduPlcyAndBizController
 * @Description : 환경교육 제도 사업 컨트롤러 서비스
 * @date : 2023. 03. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/intro/envEduPlcyAndBiz")
public class envEduPlcyAndBizIntroController {
    private static final String VIEW_PATH = "/front/intro/envEduPlcyAndBiz";

    /**
     * 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : main
     * @Description : 목록 화면
     */
    @GetMapping("/main.html")
    public String envEduPlcyAndBizMain() throws Exception {
        return VIEW_PATH + "/main";
    }

    /**
     * 유아환경교육관 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro1
     * @Description : 유아환경교육관 화면
     */
    @GetMapping("/intro1.html")
    public String intro1() throws Exception {
        return VIEW_PATH + "/intro1";
    }

    /**
     * 푸름이 이동환경교실 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro2
     * @Description : 푸름이 이동환경교실 화면
     */
    @GetMapping("/intro2.html")
    public String intro2() throws Exception {
        return VIEW_PATH + "/intro2";
    }

    /**
     * 환경교육 교구대여 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro3
     * @Description : 환경교육 교구대여 화면
     */
    @GetMapping("/intro3.html")
    public String intro3() throws Exception {
        return VIEW_PATH + "/intro3";
    }

    /**
     * 학교 환경교육지원사업 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro4
     * @Description : 학교 환경교육지원사업 화면
     */
    @GetMapping("/intro4.html")
    public String intro4() throws Exception {
        return VIEW_PATH + "/intro4";
    }

    /**
     * 사회 환경교육지원사업 화면
     *
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : intro5
     * @Description : 사회 환경교육지원사업 화면
     */
    @GetMapping("/intro5.html")
    public String intro5(@UserInfo UserVo user, Model model) throws Exception {
        model.addAttribute("user", user);
        return VIEW_PATH + "/intro5";
    }

    /**
     * 환경교육사 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro6
     * @Description : 환경교육사 화면
     */
    @GetMapping("/intro6.html")
    public String intro6() throws Exception {
        return VIEW_PATH + "/intro6";
    }

    /**
     * 우수 환경교육프로그램 지정제 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro7
     * @Description : 우수 환경교육프로그램 지정제 화면
     */
    @GetMapping("/intro7.html")
    public String intro7() throws Exception {
        return VIEW_PATH + "/intro7";
    }

    /**
     * 우수환경도서 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro8
     * @Description : 우수환경도서 화면
     */
    @GetMapping("/intro8.html")
    public String intro8() throws Exception {
        return VIEW_PATH + "/intro8";
    }

    /**
     * 환경일기 쓰기 및 방학 프로젝트
     *
     * @return string
     * @throws Exception
     * @Title : intro9
     * @Description : 환경일기 쓰기 및 방학 프로젝트
     */
    @GetMapping("/intro9.html")
    public String intro9() throws Exception {
        return VIEW_PATH + "/intro9";
    }

    /**
     * 국가환경교육지원단 화면
     *
     * @return string
     * @throws Exception
     * @Title : intro10
     * @Description : 국가환경교육지원단 화면
     */
    @GetMapping("/intro10.html")
    public String intro10() throws Exception {
        return VIEW_PATH + "/intro10";
    }

    /**
     * 자연환경연수원 지원
     *
     * @return string
     * @throws Exception
     * @Title : intro11
     * @Description : 자연환경연수원 지원
     */
    @GetMapping("/intro11.html")
    public String intro11() throws Exception {
        return VIEW_PATH + "/intro11";
    }

    /**
     * 환경교육한마당
     *
     * @return string
     * @throws Exception
     * @Title : intro12
     * @Description : 환경교육한마당
     */
    @GetMapping("/intro12.html")
    public String intro12() throws Exception {
        return VIEW_PATH + "/intro12";
    }

    /**
     * 사회환경교육기관 지정제
     *
     * @return string
     * @throws Exception
     * @Title : intro13
     * @Description : 사회환경교육기관 지정제
     */
    @GetMapping("/intro13.html")
    public String intro13() throws Exception {
        return VIEW_PATH + "/intro13";
    }

    /**
     * 환경교육 우수학교 지정제
     *
     * @return string
     * @throws Exception
     * @Title : intro14
     * @Description : 환경교육 우수학교 지정제
     */
    @GetMapping("/intro14.html")
    public String intro14() throws Exception {
        return VIEW_PATH + "/intro14";
    }

    /**
     * 환경교육도시
     *
     * @return string
     * @throws Exception
     * @Title : intro14
     * @Description : 환경교육도시
     */
    @GetMapping("/intro15.html")
    public String intro15() throws Exception {
        return VIEW_PATH + "/intro15";
    }
}
