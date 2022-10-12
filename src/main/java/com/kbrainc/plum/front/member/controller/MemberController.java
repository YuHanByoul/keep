package com.kbrainc.plum.front.member.controller;

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

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.model.TeacherVo;
import com.kbrainc.plum.front.member.service.MemberService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 회원정보 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.controller
* - MemberController.java
* </pre> 
*
* @ClassName : MemberController
* @Description : 회원정보 컨트롤러 클래스
* @author : KBRAINC
* @date : 2021. 11. 22.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Controller("front.memberController")
@Alias("front.memberController")
public class MemberController {

    @Resource(name = "front.memberServiceImpl")
    private MemberService memberService;

    /**
     * 회원가입 step1 화면 이동.
     *
     * @Title       : 회원가입 step1 
     * @Description : 회원가입 step1 화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/provision.html")
    public String Provision() throws Exception {
    	return "front/member/provision";
    }
    /**
     * 회원가입 step2 화면 이동.
     *
     * @Title       : 회원가입 step2 
     * @Description : 회원가입 step2 화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/join.html")
    public String join(@RequestParam(required = false) String agreYn, Model model) throws Exception {
    	model.addAttribute("agreYn", agreYn);
    	return "front/member/join";
    }
    /**
     * 회원가입 step3  리스트화면 이동.
     *
     * @Title       : 회원가입 step3 
     * @Description : 회원가입 step3 화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/joinComplete.html")
    public String joinComplete() throws Exception {
    	return "front/member/joinComplete";
    }
    
    /**
     * 마이페이지 (회원정보 수정창) 화면 이동
     *
     * @Title       : 마이페이지  
     * @Description : 마이페이지 (회원정보 수정창) 화면 이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/myPage.html")
    public String myPage(MemberVo memberVo, Model model, @UserInfo UserVo user) throws Exception {
    	
    	memberVo.setUserid(Integer.parseInt(user.getUserid()));
    	model.addAttribute("userInfo", memberService.selectTeacherMemberInfo(memberVo));
    	
    	return "front/member/myPage";
    }
    
    /**
     * ID 중복 체크
     *
     * @Title       : chekcDuplicationUser 
     * @Description : ID 중복 체크
     * @param memberVo MemberVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/chekcDuplicationUser.do")
    @ResponseBody
    public Map<String, Object> chekcDuplicationUser(MemberVo memberVo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	int checkDuplicationID = memberService.chekcDuplicationUser(memberVo);
    	
    	if(checkDuplicationID > 0) {
    		resultMap.put("result", false);
    	}else {
    		resultMap.put("result", true);
    	}
    	return resultMap;
    }
    
    /**
     * 회원등록
     *
     * @Title       : insertMember 
     * @Description : 회원등록
     * @param memberVo MemberVo , TeacherVo TeacherVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/insertMember.do")
    @ResponseBody
    public Map<String, Object> insertMember(MemberVo memberVo,TeacherVo teacherVo) throws Exception {
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	memberVo.setTosAgreYn("Y");
    	memberVo.setSttsCd("2");
    	
    	int checkDuplicationID = memberService.insertMember(memberVo,teacherVo);
    	if(checkDuplicationID > 0) {
    		resultMap.put("result", true);
    	}else {
    		resultMap.put("result", false);
    	}
    	return resultMap;
    }
    
    /**
     * 회원 정보 수정
     *
     * @Title       : insertMember 
     * @Description : 회원 정보 수정
     * @param memberVo MemberVo , TeacherVo TeacherVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/updateMember.do")
    @ResponseBody
    public Map<String, Object> updateMember(MemberVo memberVo, TeacherVo teacherVo , @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	memberVo.setUser(user);
    	memberVo.setUserid(Integer.parseInt(user.getUserid()));
    	teacherVo.setUser(user);
    	teacherVo.setUserid(Integer.parseInt(user.getUserid()));
    	
    	int checkDuplicationID = memberService.updateMember(memberVo,teacherVo);
    	
    	if(checkDuplicationID > 0) {
    		resultMap.put("result", true);
    	}else {
    		resultMap.put("result", false);
    	}
    	return resultMap;
    }

    
}