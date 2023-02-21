package com.kbrainc.plum.front.member.controller;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.cmm.esylgn.service.EsylgnService;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.cmm.idntyVrfctn.service.IdntyVrfctnService;
import com.kbrainc.plum.front.member.model.MemberAcntPswdVo;
import com.kbrainc.plum.front.member.model.MemberAgreVo;
import com.kbrainc.plum.front.member.model.MemberAuthVo;
import com.kbrainc.plum.front.member.model.MemberInstSearchVo;
import com.kbrainc.plum.front.member.model.MemberInstVo;
import com.kbrainc.plum.front.member.model.MemberParamVo;
import com.kbrainc.plum.front.member.model.MemberTypeVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.service.MemberService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

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
    
    @Autowired
    private EsylgnService esylgnService;
    
    @Autowired
    private FileServiceImpl fileService;
    
    @Resource(name="ariaCryptoService")
    EgovCryptoService cryptoService;
    
    @Value("${crypto.key}")
    private String cryptoKey;
            
    /**
    * 회원가입 0단계 : 회원가입 유형 선택 화면.
    *
    * @Title       : membershipStep1 
    * @Description : 회원가입 1단계 : 회원가입 유형 선택 화면
    * @param request 요청객체
    * @param memberParamVo MemberParamVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step1.html")
    public String membershipStep1(HttpServletRequest request, MemberParamVo memberParamVo, Model model) throws Exception {
        model.addAttribute("data", memberParamVo);
        return "front/member/step1.html";
    }
    
    /**
    * 회원가입 2단계 : 약관동의 화면.
    *
    * @Title : membershipStep2
    * @Description : 회원가입 2단계 : 약관동의 화면
    * @param request 요청객체
    * @param response 응답객체
    * @param memberTypeVo MemberTypeVo객체
    * @param model 모델객체
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step2.html")
    public String membershipStep2(HttpServletRequest request, HttpServletResponse response, MemberTypeVo memberTypeVo, Model model) throws Exception {
        model.addAttribute("data", memberTypeVo);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MemberTypeVo>> violations = validator.validate(memberTypeVo);
        
        for (ConstraintViolation<MemberTypeVo> violation : violations) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + violation.getMessage() + "');location.href='/front/membership/step1.html';</script>");
            return null;
        }
        
        return "front/member/step2.html";
    }
    
    /**
    * 회원가입 3단계 : 본인인증 화면.
    *
    * @Title : membershipStep3
    * @Description : 회원가입 3단계 : 본인인증 화면
    * @param request 요청객체
    * @param response 응답객체
    * @param memberAgreVo MemberAgreVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step3.html")
    public String membershipStep3(HttpServletRequest request, HttpServletResponse response, MemberAgreVo memberAgreVo, Model model) throws Exception {
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
            writer.print("<script>alert('" + violation.getMessage() + "');location.href='/front/membership/step1.html';</script>");
            return null;
        }
        
        if ("C".equals(memberAgreVo.getType()) && (memberAgreVo.getChildJoinAgreYn() == null|| !"Y".equals(memberAgreVo.getChildJoinAgreYn()))) { // 어린이 회원 일때
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('필수항목을 동의해야 회원가입이 가능합니다.');location.href='/front/membership/step1.html';</script>");
            return null;
        }

        model.addAttribute("data", memberAgreVo);
        
        return "front/member/step3.html";
    }
    
    /**
    * 회원가입 4단계 : 회원정보입력 화면.
    *
    * @Title : membershipStep4
    * @Description : 회원가입 4단계 : 회원정보입력 화면
    * @param response 응답객체
    * @param memberAuthVo MemberAuthVo객체
    * @param bindingResult 유효성검증결과
    * @param model 모델객체
    * @param session 세션객체
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면 경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step4.html")
    public String membershipStep4(HttpServletResponse response, @Valid MemberAuthVo memberAuthVo, BindingResult bindingResult, Model model, HttpSession session, RedirectAttributes redirect) throws Exception {
        String encodeData = "";
        String onepassEncodeData = "";
        MemberVo defaultMemberInfo = new MemberVo();
        String ci = null;
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/membership/step1.html';</script>");
            return null;
        }
        
        if ("C".equals(memberAuthVo.getType()) && (memberAuthVo.getChildJoinAgreYn() == null|| !"Y".equals(memberAuthVo.getChildJoinAgreYn()))) { // 어린이 회원 일때
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('필수항목을 동의해야 회원가입이 가능합니다.');location.href='/front/membership/step1.html';</script>");
            return null;
        }
        
        // 디지털원패스 회원연동일때
        onepassEncodeData = memberAuthVo.getOnepassEncodeData();
        if (!"".equals(StringUtil.nvl(onepassEncodeData))) {
            // 자체 복호화 수행
            byte[] decode = Base64.getDecoder().decode(onepassEncodeData);
            byte[] decrypted = cryptoService.decrypt(decode, cryptoKey);
            String jsonRes = new String(decrypted, "UTF-8");
            JsonObject convertedObject = new Gson().fromJson(jsonRes, JsonObject.class);
            ci = convertedObject.get("ci").getAsString();
            String name = convertedObject.get("name").getAsString();
            String email =convertedObject.get("email").getAsString();
            defaultMemberInfo.setNm(name);
            defaultMemberInfo.setEml(email);
        }
        
        
        if ("C".equals(memberAuthVo.getType())) {
            encodeData = memberAuthVo.getPencodeData();
        } else {
            encodeData = memberAuthVo.getEncodeData();
        }
        IdntyVrfctnSuccessVo result = new IdntyVrfctnSuccessVo();
        
        if (!"".equals(StringUtil.nvl(encodeData))) {
            result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(session, encodeData);
            
            if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.print("<script>alert('" + result.getSMessage() + "');location.href='/front/membership/step1.html;</script>");
                return null;
            }
            ci = result.getSConnInfo();
        }
            
        model.addAttribute("memberInfo", defaultMemberInfo);
        if (!"C".equals(memberAuthVo.getType())) { // 어린이회원이 아닐때
            MemberVo memberVo = new MemberVo();
            memberVo.setCi(ci);
            // CI값이 동일한 회원이 있는지 확인한다.
            MemberVo memberInfo = memberService.selectUserInfoByCI(memberVo);
            if (memberInfo != null) {
                if ("I".equals(memberAuthVo.getType()) && memberAuthVo.getUserid() != null && memberAuthVo.getUserid().intValue() == memberInfo.getUserid().intValue()) {
                    model.addAttribute("memberInfo", memberInfo);
                } else {
                    memberAuthVo.setAlertMsg("회원정보가 존재합니다.\n아이디 찾기로 확인해주시기 바랍니다.");
                    if (!"".equals(StringUtil.nvl(onepassEncodeData))) {
                        MemberAgreVo memberAgreVo = new MemberAgreVo();
                        memberAgreVo.setAlertMsg(memberAuthVo.getAlertMsg());
                        memberAgreVo.setOnepassEncodeData(memberAuthVo.getOnepassEncodeData());
                        memberAgreVo.setReturnUrl(memberAuthVo.getReturnUrl());
                        memberAgreVo.setType(memberAuthVo.getType());
                        memberAgreVo.setTosAgreYn(memberAuthVo.getTosAgreYn());
                        memberAgreVo.setPrivcyAgreYn(memberAuthVo.getPrivcyAgreYn());
                        memberAgreVo.setPrvcThptyPvsnAgreYn(memberAuthVo.getPrvcThptyPvsnAgreYn());
                        memberAgreVo.setWbznAplyAgreYn(memberAuthVo.getWbznAplyAgreYn());
                        memberAgreVo.setAllAgreYn(memberAuthVo.getAllAgreYn());
                        redirect.addFlashAttribute("data", memberAgreVo);
                        return "redirect:/front/membership/step2.html";
                    } else {
                        redirect.addFlashAttribute("data", memberAuthVo);
                        return "redirect:/front/membership/step3.html";
                    }
                }
            }
        }
        
        Map<String, Object> bizFileConf = fileService.getConfigurationByFilegrpName("biz_file");
        String bizFileExtsn = ((HashMap<String, String>) bizFileConf.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));
        
        Map<String, Object> bizLogoConf = fileService.getConfigurationByFilegrpName("biz_logo");
        String bizLogoExtsn = ((HashMap<String, String>) bizLogoConf.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));

        model.addAttribute("acceptUploadBizFileExt", bizFileExtsn);
        model.addAttribute("acceptUploadBizLogoExt", bizLogoExtsn);
        
        model.addAttribute("data", memberAuthVo);
        
        if ("C".equals(memberAuthVo.getType())) {
            model.addAttribute("pauthData", result);
            model.addAttribute("authData", new IdntyVrfctnSuccessVo());
        } else {
            model.addAttribute("pauthData", new IdntyVrfctnSuccessVo());
            model.addAttribute("authData", result);
        }
        
        return "front/member/step4.html";
    }
    
    /**
    * 회원가입 5단계 : 가입완료 화면.
    *
    * @Title       : membershipStep5 
    * @Description : 회원가입 5단계 : 가입완료 화면.
    * @param response 응답객체
    * @param memberTypeVo MemberTypeVo객체
    * @param bindingResult 유효성검증결과
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/step5.html")
    public String membershipStep5(HttpServletResponse response, @Valid MemberTypeVo memberTypeVo, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            fieldError.getDefaultMessage();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + fieldError.getDefaultMessage() + "');location.href='/front/membership/step1.html';</script>");
            return null;
        }
        
        model.addAttribute("data", memberTypeVo);
        
        return "front/member/step5.html";
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
    	
    	int cnt = memberService.checkDuplicationUser(memberVo);
    	
    	if(cnt > 0) {
    		resultMap.put("result", false);
    	}else {
    		resultMap.put("result", true);
    	}
    	return resultMap;
    }
    
    /**
    * 사업자등록번호 중복 체크
    *
    * @Title       : checkDuplicationBrno 
    * @Description : 사업자등록번호 중복 체크
    * @param memberVo MemberVo객체
    * @param user 사용자세션정보
    * @return MemberInstVo 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/member/checkDuplicationBrno.do")
    @ResponseBody
    public MemberInstVo checkDuplicationBrno(MemberInstVo memberInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();    
        MemberInstVo returnInfo = memberService.checkDuplicationBrno(memberInstVo);
    
        return returnInfo;
    }
    
    /**
    * 회원등록.
    *
    * @Title : insertMember
    * @Description : 회원등록
    * @param memberVo MemberVo객체
    * @param memberInstVo MemberInstVo(기관정보)
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/member/insertMember.do")
    @ResponseBody
    public Map<String, Object> insertMember(MemberVo memberVo, MemberInstVo memberInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        
        if (memberVo.getUserid() == null) { // 회원가입
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<MemberVo>> violations = validator.validate(memberVo);
            
            for (ConstraintViolation<MemberVo> violation : violations) {
                resultMap.put("msg", violation.getMessage());
                return resultMap;
            }
            
            if ("".equals(StringUtil.nvl(memberVo.getOnepassEncodeData()))) { // 디지털원패스 회원연동이 아닌경우
                // 계정과 비밀번호 유효성 체크
                MemberAcntPswdVo memberAcntPswdVo = new MemberAcntPswdVo();
                memberAcntPswdVo.setAcnt(memberVo.getAcnt());
                memberAcntPswdVo.setPswd(memberVo.getPswd());
                factory = Validation.buildDefaultValidatorFactory();
                validator = factory.getValidator();
                
                for (ConstraintViolation<MemberAcntPswdVo> violation : validator.validate(memberAcntPswdVo)) {
                    resultMap.put("msg", violation.getMessage());
                    return resultMap;
                }
            }
            
            if ("C".equals(memberVo.getType()) && (memberVo.getChildJoinAgreYn() == null|| !"Y".equals(memberVo.getChildJoinAgreYn()))) { // 어린이 회원 일때
                resultMap.put("msg", "필수항목을 동의해야 회원가입이 가능합니다.");
                return resultMap;
            }
            
            if (!"C".equals(memberVo.getType()) && "".equals(StringUtil.nvl(memberVo.getMoblphon()))) { // 어린이회원이 아닌 경우 휴대전화 필수 
                resultMap.put("msg", "휴대전화를 입력 해주십시오.");
                return resultMap;
            }
            if ("".equals(memberVo.getMoblphon())) {
                memberVo.setMoblphon(null);
            }
    
            // 아이디 중복 확인
            int cnt = memberService.checkDuplicationUser(memberVo);
            if(cnt > 0) {
                resultMap.put("msg", "이미 사용중인 아이디입니다.");
                return resultMap;
            }
        } else { // 기관회원전환(동의여부 유효성 검사)
            MemberAgreVo memberAgreVo = new MemberAgreVo();
            memberAgreVo.setType(memberVo.getType());
            memberAgreVo.setTosAgreYn(memberVo.getTosAgreYn());
            memberAgreVo.setPrivcyAgreYn(memberVo.getPrivcyAgreYn());
            memberAgreVo.setPrvcThptyPvsnAgreYn(memberVo.getPrvcThptyPvsnAgreYn());
            memberAgreVo.setWbznAplyAgreYn(memberVo.getWbznAplyAgreYn());
            
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<MemberAgreVo>> violations = validator.validate(memberAgreVo);
            
            for (ConstraintViolation<MemberAgreVo> violation : violations) {
                resultMap.put("msg", violation.getMessage());
                return resultMap;
            }
        }
        
        String ciParnts = null;
        String ci = null;
        String nm = null;
        String moblphon = null;
        String userKey = null;
        String type = null;

        // 본인인증 암호화 데이터에서 추출(휴대전화를 입력하지않은 어린이 제외)
        if (!"".equals(StringUtil.nvl(memberVo.getEncodeData())) && !("C".equals(memberVo.getType()) && "".equals(StringUtil.nvl(memberVo.getMoblphon())))) {
            IdntyVrfctnSuccessVo result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(null, memberVo.getEncodeData());
            
            if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
                resultMap.put("msg", result.getSMessage());
                return resultMap;
            } else {                
                ci = result.getSConnInfo();
                nm = result.getSName();
                moblphon = result.getSMobileNo();
                memberVo.setCi(ci);

                // 본인인증 암호화 데이터와 파라미터로 넘어온 이름과 모바일 번호 일치 하는지 확인
                if (memberVo.getUserid() == null && ((nm != null && !nm.equals(memberVo.getNm())) || (moblphon != null && !moblphon.equals(memberVo.getMoblphon())))) {
                    resultMap.put("msg", "입력데이터가 본인인증 정보와 다릅니다.");
                    return resultMap;
                }
            }
        }
        
        // 디지털원패스 회원연동으로 진입시
        if (!"".equals(StringUtil.nvl(memberVo.getOnepassEncodeData()))) {
            // 디지털원패스 암호화 데이터 복호화
            byte[] decode = Base64.getDecoder().decode(memberVo.getOnepassEncodeData());
            byte[] decrypted = cryptoService.decrypt(decode, cryptoKey);
            String jsonRes = new String(decrypted, "UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject convertedObject = new Gson().fromJson(jsonRes, JsonObject.class);
            userKey = convertedObject.get("userKey").getAsString();
            ci = convertedObject.get("ci").getAsString();
            nm = convertedObject.get("name").getAsString();
            if (convertedObject.has("type")) {
                type = convertedObject.get("type").getAsString();
            }
            
            if ("C".equals(type) || !"C".equals(memberVo.getType())) { // 원패스 암호화 데이터에 어린이가 아니었는데 어린이 회원으로 가입하는 경우는 이름을 비교하지않도록 함.
                if ((nm != null && !nm.equals(memberVo.getNm()))) {
                    resultMap.put("msg", "입력데이터가 본인인증 정보와 다릅니다.");
                    return resultMap;
                }
            }
        }
        

        // ci중복체크(본인인증은 ci, 디지털원패스 회원연동으로 진입시 디지털원패스 ci값으로 비교)
        if (ci != null) {
            // CI값이 동일한 회원이 있는지 확인한다.
            String userid = memberService.selectUseridByCI(memberVo);
            if (memberVo.getUserid() != null) {
                if (!String.valueOf(memberVo.getUserid()).equals(userid)) {
                    resultMap.put("msg", "입력데이터가 본인인증 정보와 다릅니다.");
                    return resultMap;
                }
            } else if (userid != null) {
                resultMap.put("msg", "회원정보가 존재합니다.\n아이디 찾기로 확인해주시기 바랍니다.");
                return resultMap;
            }
        }
                
        // 디지털원패스 회원연동으로 진입시
        if (!"".equals(StringUtil.nvl(memberVo.getOnepassEncodeData()))) {
            // userKey 사용중인지 확인
            EsylgnVo esylgnVo = new EsylgnVo();
            esylgnVo.setEsylgnCd("106101");
            esylgnVo.setUserkey(userKey);
            String userid = esylgnService.selectUseridByEsylgnUserkey(esylgnVo);
            if (userid != null) {
                resultMap.put("msg", "이미 다른 사용자계정과 연동된 디지털원패스 계정입니다.");
                return resultMap;
            }
            
            memberVo.setEsylgnVo(esylgnVo);
        }
        
        // 부모CI 추출
        if (!"".equals(StringUtil.nvl(memberVo.getPencodeData()))) {
            IdntyVrfctnSuccessVo result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(null, memberVo.getPencodeData());
            
            if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
                resultMap.put("msg", result.getSMessage());
                return resultMap;
            } else {                
                ciParnts = result.getSConnInfo();
                memberVo.setCiParnts(ciParnts);
                
                // 부모의 ci와 나의 이름으로 중복사용자가 있는지 확인
                String userid = memberService.selectUseridByParntsCIandName(memberVo);
                if (userid != null) {
                    resultMap.put("msg", "회원정보가 존재합니다.\n아이디 찾기로 확인해주시기 바랍니다.");
                    return resultMap;
                }
            }
        }
        
        if ("I".equals(memberVo.getType())) { // 기관회원일때
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<MemberInstVo>> violations = validator.validate(memberInstVo);
            
            for (ConstraintViolation<MemberInstVo> violation : violations) {
                resultMap.put("msg", violation.getMessage());
                return resultMap;
            }
            
            if ("Y".equals(memberInstVo.getDirectYn())) { // 직접입력시 사업자등록증을 첨부했는지 체크한다.
                if (memberInstVo.getBizfileFilegrpid() == null || memberInstVo.getBizfileFilegrpid() == 0) {
                    resultMap.put("msg", "사업자등록증을 등록 해주십시오.");
                    return resultMap;
                }
            }
            
            MemberInstVo returnInfo = memberService.checkDuplicationBrno(memberInstVo);
            if (!returnInfo.isResult()) {
                resultMap.put("msg", returnInfo.getMsg());
                return resultMap;
            } else {
                memberInstVo.setInstid(returnInfo.getInstid());
            }
        }
        
    	int retVal = memberService.insertMember(memberVo, memberInstVo);
    	
    	if(retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		if (memberVo.getEsylgnVo() != null) {
    		    String alertMessage = "앞으로 디지털원패스 아이디로\n서비스를 이용할 수 있습니다.";
    		    resultMap.put("msg", Base64Utils.encodeToUrlSafeString(alertMessage.getBytes()));
    		}
    	}else {
    	    resultMap.put("msg", "회원가입에 실패하였습니다.");
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    	}
    	return resultMap;
    }
    
    /**
    * 기관 검색 팝업.
    *
    * @Title       : membershipStep5 
    * @Description : 기관 검색 팝업
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        return "front/member/instSearch.html";
    }
    
    /**
    * 기관풀 검색 리스트 조회
    *
    * @Title       : selectInstSearchList 
    * @Description : 기관풀 검색 리스트 조회
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/selectInstSearchList.do")
    @ResponseBody
    public Map<String, Object> selectInstSearchList(MemberInstSearchVo memberInstSearchVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberInstSearchVo> result = null;
        
        result = memberService.selectInstSearchList(memberInstSearchVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination",PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
    * 기관 정보 조회(기관풀).
    *
    * @Title : selectInstPoolInfo
    * @Description : 기관 정보 조회(기관풀)
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return MemberInstSearchVo 기관정보
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/membership/selectInstPoolInfo.do")
    @ResponseBody
    public MemberInstSearchVo selectInstPoolInfo(MemberInstSearchVo memberInstSearchVo) throws Exception {
        MemberInstSearchVo result = memberService.selectInstPoolInfo(memberInstSearchVo);

        // 주소로 시군구코드 조회
        result.setSignguCd(memberService.getSignguCdWithaddress(result.getAddr()));
        
        return result;
    }
}