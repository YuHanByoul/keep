package com.kbrainc.plum.front.member.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kbrainc.plum.cmm.esylgn.service.EsylgnService;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnStartVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.cmm.idntyVrfctn.service.IdntyVrfctnService;
import com.kbrainc.plum.front.member.model.MemberAgreVo;
import com.kbrainc.plum.front.member.model.MemberAuthVo;
import com.kbrainc.plum.front.member.model.MemberTypeVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.service.MemberService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

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
    
    @Autowired
    private IdntyVrfctnService idntyVrfctnService;
    
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
    * 회원가입 1단계 : 약관동의 화면.
    *
    * @Title : membershipStep1
    * @Description : 회원가입 1단계 : 약관동의 화면
    * @param request 요청객체
    * @param response 응답객체
    * @param memberTypeVo MemberTypeVo객체
    * @param model 모델객체
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step1.html")
    public String membershipStep1(HttpServletRequest request, HttpServletResponse response, MemberTypeVo memberTypeVo, Model model, RedirectAttributes redirect) throws Exception {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null){
            MemberAgreVo memberAgreVo = (MemberAgreVo) flashMap.get("data");
            model.addAttribute("data", memberAgreVo);
            memberTypeVo.setType(memberAgreVo.getType());
        } else {
            model.addAttribute("data", memberTypeVo);
        }
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MemberTypeVo>> violations = validator.validate(memberTypeVo);
        
        for (ConstraintViolation<MemberTypeVo> violation : violations) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + violation.getMessage() + "');location.href='/front/membership/step0.html';</script>");
            return null;
        }
        
        return "front/member/step1.html";
    }
    
    /**
    * 회원가입 2단계 : 본인인증 화면.
    *
    * @Title : membershipStep2
    * @Description : 회원가입 2단계 : 본인인증 화면
    * @param request 요청객체
    * @param response 응답객체
    * @param memberAgreVo MemberAgreVo객체
    * @param bindingResult 유효성검증결과
    * @param model 모델객체
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step2.html")
    public String membershipStep2(HttpServletRequest request, HttpServletResponse response, @Valid MemberAgreVo memberAgreVo, BindingResult bindingResult, Model model, RedirectAttributes redirect) throws Exception {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        MemberAuthVo memberAuthVo = null;
        
        if(flashMap != null){
            memberAuthVo = (MemberAuthVo) flashMap.get("data");
            memberAgreVo.setType(memberAuthVo.getType());
            memberAgreVo.setTosAgreYn(memberAuthVo.getTosAgreYn());
            memberAgreVo.setPrivcyAgreYn(memberAuthVo.getPrivcyAgreYn());
            memberAgreVo.setPrvcThptyPvsnAgreYn(memberAuthVo.getPrvcThptyPvsnAgreYn());
            memberAgreVo.setChildJoinAgreYn(memberAuthVo.getChildJoinAgreYn());
            memberAgreVo.setWbznAplyAgreYn(memberAuthVo.getWbznAplyAgreYn());
            memberAgreVo.setReturnUrl(memberAuthVo.getReturnUrl());
            memberAgreVo.setAlertMsg(memberAuthVo.getAlertMsg());
        }
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MemberAgreVo>> violations = validator.validate(memberAgreVo);
        
        for (ConstraintViolation<MemberAgreVo> violation : violations) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + violation.getMessage() + "');location.href='/front/membership/step0.html';</script>");
            return null;
        }
        
        if ("C".equals(memberAgreVo.getType()) && (memberAgreVo.getChildJoinAgreYn() == null|| !"Y".equals(memberAgreVo.getChildJoinAgreYn()))) { // 어린이 회원 일때
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('필수항목을 동의해야 회원가입이 가능합니다.');location.href='/front/membership/step0.html';</script>");
            return null;
        }

        model.addAttribute("data", memberAgreVo);
        
        return "front/member/step2.html";
    }
    
    /**
    * 회원가입 3단계 : 회원정보입력 화면.
    *
    * @Title : membershipStep3
    * @Description : 회원가입 3단계 : 회원정보입력 화면
    * @param response 응답객체
    * @param memberAuthVo MemberAuthVo객체
    * @param bindingResult 유효성검증결과
    * @param model 모델객체
    * @param session 세션객체
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면 경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step3.html")
    public String membershipStep3(HttpServletResponse response, @Valid MemberAuthVo memberAuthVo, BindingResult bindingResult, Model model, HttpSession session, RedirectAttributes redirect) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/membership/step0.html';</script>");
            return null;
        }
        
        if ("C".equals(memberAuthVo.getType()) && (memberAuthVo.getChildJoinAgreYn() == null|| !"Y".equals(memberAuthVo.getChildJoinAgreYn()))) { // 어린이 회원 일때
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('필수항목을 동의해야 회원가입이 가능합니다.');location.href='/front/membership/step0.html';</script>");
            return null;
        }
        
        String encodeData = memberAuthVo.getEncodeData();
        IdntyVrfctnSuccessVo result = new IdntyVrfctnSuccessVo();
        if (!"".equals(StringUtil.nvl(encodeData))) {
            result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(session, encodeData);
            
            if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.print("<script>alert('" + result.getSMessage() + "');location.href='/front/membership/step0.html;</script>");
                return null;
            }
            
            if (!"C".equals(memberAuthVo.getType())) { // 어린이회원이 아닐때
                String ci = result.getSConnInfo();
                MemberVo memberVo = new MemberVo();
                memberVo.setCi(ci);
                // CI값이 동일한 회원이 있는지 확인한다.
                String userid = memberService.selectUseridByCI(memberVo);
                if (userid != null) {
                    memberAuthVo.setAlertMsg("회원정보가 존재합니다.\n아이디 찾기로 확인해주시기 바랍니다.");
                    redirect.addFlashAttribute("data", memberAuthVo);
                    return "redirect:/front/membership/step2.html";
                }
            }
        }
        
        model.addAttribute("data", memberAuthVo);
        model.addAttribute("authData", result);
        return "front/member/step3.html";
    }
    
    /**
    * 회원가입 4단계 : 가입완료 화면.
    *
    * @Title       : membershipStep4 
    * @Description : 회원가입 4단계 : 가입완료 화면.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step4.html")
    public String membershipStep4() throws Exception {
        // 세션값 불일치 오류시 step0으로 보내되 returnUrl있으면 파라미터 추가
        
        return "front/member/step4.html";
    }
    
    /**
    * ID 중복 체크
    *
    * @Title       : checkDuplicationUser 
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/member/checkDuplicationUser.do")
    @ResponseBody
    public Map<String, Object> chekcDuplicationUser(MemberVo memberVo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	List<MemberVo> result = null;
    	
    	int cnt = memberService.checkDuplicationUser(memberVo);
    	
    	if(cnt > 0) {
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