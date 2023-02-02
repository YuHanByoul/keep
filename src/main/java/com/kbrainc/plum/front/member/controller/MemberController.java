package com.kbrainc.plum.front.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.member.model.MemberVo;
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
* @date : 2023. 1. 31.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Controller("front.memberController")
@Alias("front.memberController")
public class MemberController {

    @Resource(name = "front.memberServiceImpl")
    private MemberService memberService;
    
    /**
    * 회원가입 0단계 : 회원가입 유형 선택 화면.
    *
    * @Title       : membershipStep0 
    * @Description : 회원가입 0단계 : 회원가입 유형 선택 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step0.html")
    public String membershipStep0() throws Exception {
        return "front/member/step0.html";
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
    * @param memberVo MemberVo
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/member/insertMember.do")
    @ResponseBody
    public Map<String, Object> insertMember(MemberVo memberVo) throws Exception {
    	
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	memberVo.setTosAgreYn("Y");
    	memberVo.setSttsCd("2");
    	
    	int checkDuplicationID = memberService.insertMember(memberVo);
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
    * @param memberVo MemberVo
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/member/updateMember.do")
    @ResponseBody
    public Map<String, Object> updateMember(MemberVo memberVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	memberVo.setUser(user);
    	memberVo.setUserid(Integer.parseInt(user.getUserid()));

    	int checkDuplicationID = memberService.updateMember(memberVo);
    	
    	if(checkDuplicationID > 0) {
    		resultMap.put("result", true);
    	}else {
    		resultMap.put("result", false);
    	}
    	return resultMap;
    }

    
}