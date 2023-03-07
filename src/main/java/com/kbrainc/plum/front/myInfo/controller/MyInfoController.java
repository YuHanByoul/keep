package com.kbrainc.plum.front.myInfo.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.type.Alias;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.myInfo.service.MyInfoService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

@Controller("front.myInfoController")
@Alias("front.myInfoController")
public class MyInfoController {

    @Resource(name = "front.myInfoServiceImpl")
    private MyInfoService myInfoService;
    
    @Value("${crypto.key}")
    private String encryptKey;
    
    @RequestMapping(value = "/front/myInfo/myInfoUpdate.html")
    public String myInfoUpdate(MemberVo memberVo, @UserInfo UserVo userVo, Model model) throws Exception {
        model.addAttribute("userVo", userVo);
        
        memberVo.setUser(userVo);
        MemberVo resultVo = myInfoService.selectMemberInfo(memberVo);
        
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(resultVo.getGndr());
        
        resultVo.setGndr(decStr);
        
        model.addAttribute("memberInfo", resultVo);
        model.addAttribute("returnUrl", memberVo.getReturnUrl());
        
        if(resultVo.getItrstfldCd() == null || resultVo.getItrstfldCd() == "") {
            model.addAttribute("itrstfldCd", "Empty");
        }
        if(resultVo.getEnvfldCd() == null || resultVo.getEnvfldCd() == "") {
            model.addAttribute("envfldCd", "Empty");
        }
        
        return "front/myInfo/myInfoUpdate";
    }
    
    @RequestMapping(value = "/front/myInfo/moblCertStep.html")
    public String moblCertStep() throws Exception {
        return "front/myInfo/moblCertStep";
    }
    
    @RequestMapping(value = "/front/myInfo/updateMyInfo.do")
    @ResponseBody
    public Map<String, Object> updateMyInfo(MemberVo memberVo, @UserInfo UserVo user, String itrstfldCdEpty, String envfldCdEpty) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(memberVo.getEml().equals("@")) {
            resultMap.put("msg", "이메일을 입력해 주십시오.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            return resultMap;
        }else if(memberVo.getEml1().equals("")) {
            resultMap.put("msg", "이메일 아이디를 입력해 주십시오.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            return resultMap;
        }else if(memberVo.getEml2().equals("")) {
            resultMap.put("msg", "이메일 도메인을 입력해 주십시오.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            return resultMap;
        }
        
        memberVo.setUser(user);
        
        int retVal = 0 ;
        retVal = myInfoService.updateMyInfo(memberVo, itrstfldCdEpty, envfldCdEpty);
        
        if(retVal > 0) {
            resultMap.put("msg", "회원 정보 변경이 완료되었습니다");
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        }else {
            resultMap.put("msg", "회원 정보 변경에 실패하였습니다.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return resultMap;
    }
    
    @RequestMapping(value = "/front/myInfo/pswdUpdate.html")
    public String pswdUpdate(MemberVo memberVo, @UserInfo UserVo userVo, Model model) throws Exception {
        model.addAttribute("userVo", userVo);
        model.addAttribute("returnUrl", memberVo.getReturnUrl());
        
        return "front/myInfo/pswdUpdate";
    }
    
    @RequestMapping(value = "/front/myInfo/updatePswd.do")
    @ResponseBody
    public Map<String, Object> updatePswd(MemberVo memberVo, @UserInfo UserVo user, String chgPswd, String chgPswdIdnty) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        memberVo.setUser(user);
        
        String password = memberVo.getPswd();
        String hashPassword = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(password.getBytes("UTF-8")));
        
        MemberVo resultVo = myInfoService.selectMemberInfo(memberVo);
        
        int retVal = 0 ;
        if(!hashPassword.equals(resultVo.getPswd())) {
            resultMap.put("msg", "현재 비밀번호와 다릅니다.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            return resultMap;
        }else {
            if(!chgPswd.equals(chgPswdIdnty)) {
                resultMap.put("msg", "비밀번호 조건이 일치하지 않습니다.");
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                return resultMap;
            }else {
                String changePassword = chgPswdIdnty;
                String hashChangePassword = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(changePassword.getBytes("UTF-8")));
                memberVo.setPswd(hashChangePassword);
                retVal = myInfoService.updatePswd(memberVo);   
            }            
        }
        
        if(retVal > 0) {
            resultMap.put("msg", "비밀번호 변경이 완료되었습니다");
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
        }else {
            resultMap.put("msg", "비밀번호 변경에 실패하였습니다.");
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return resultMap;
    }
}
