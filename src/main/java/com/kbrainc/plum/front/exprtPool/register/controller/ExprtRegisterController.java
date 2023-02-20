package com.kbrainc.plum.front.exprtPool.register.controller;

import com.kbrainc.plum.front.exprtPool.register.model.DefaultMemberInfoVo;
import com.kbrainc.plum.front.exprtPool.register.model.ExprtRegisterVo;
import com.kbrainc.plum.front.exprtPool.register.service.ExprtRegisterService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.apache.ibatis.type.Alias;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 전문가 등재신청 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.controller
 * - ExprtRegisterController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterController
 * @Description : 전문가 등재신청 클래스
 * @date : 2023. 02. 19.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.exprtRegisterController")
@Controller("front.exprtRegisterController")
@RequestMapping("/front/exprtPool")
public class ExprtRegisterController {
    private final static String VIEW_PATH = "/front/exprtPool";

    @Autowired
    private ExprtRegisterService exprtRegisterService;

    @Value("${crypto.key}")
    private String encryptKey;
    StandardPBEStringEncryptor encryptor;

    @GetMapping("/registerStep1.html")
    public String registerStep1(@UserInfo UserVo user, Model model) throws Exception {
        model.addAttribute("user", user);
        if (user != null) {
            String exprtStts = exprtRegisterService.selectExprtStts(user);
            model.addAttribute("exprtStts", exprtStts);
        }

        return VIEW_PATH + "/registerStep1";
    }

    @GetMapping("/registerStep2.html")
    public String registerStep2(ExprtRegisterVo exprtRegisterVo, Model model) throws Exception {
        model.addAttribute("exprtRegisterVo", exprtRegisterVo);
        return VIEW_PATH + "/registerStep2";
    }

    @PostMapping("/registerStep2.html")
    public String nextRegisterStep2() {
        return null;
    }

    @GetMapping("/registerStep3.html")
    public String registerStep3(ExprtRegisterVo exprtRegisterVo, @UserInfo UserVo user, Model model) throws Exception {
        exprtRegisterVo.setUser(user);
        DefaultMemberInfoVo defaultMemberInfo = exprtRegisterService.selectDefaultMemberInfo(exprtRegisterVo);

        encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String decStr = encryptor.decrypt(defaultMemberInfo.getGndr());
        defaultMemberInfo.setGndr(decStr);

        model.addAttribute("exprtRegisterVo", exprtRegisterVo);
        model.addAttribute("defaultMemberInfo", defaultMemberInfo);

        return VIEW_PATH + "/registerStep3";
    }

    @GetMapping("/registerStep4.html")
    public String registerStep4() {
        return VIEW_PATH + "/registerStep4";
    }

}
