package com.kbrainc.plum.front.myInfo.controller;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.myInfo.service.MyInfoService;
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
        
        return "front/myInfo/myInfoUpdate";
    }
    
    @RequestMapping(value = "/front/myInfo/pswdUpdate.html")
    public String pswdUpdate() throws Exception {
        return "front/myInfo/pswdUpdate";
    }
}
