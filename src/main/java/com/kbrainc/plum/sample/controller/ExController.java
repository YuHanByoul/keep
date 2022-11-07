package com.kbrainc.plum.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

@Controller
public class ExController {

    @Autowired @Qualifier("MailService")
    private MailService mailService;
    
    @ResponseBody
    @RequestMapping(value = "/test/mail")    
    public String testSendMail() throws Exception {
        MailVo mailVo = new MailVo();
        mailVo.setRcptnEmail("jeongsk@kbrainc.com");
        mailVo.setTitle("메일 발송 테스트");
        mailVo.setCntnts("메일 발송 테스트 샘플입니다.");
        Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
        
        List<MailRcptnVo> mailList  = new ArrayList<MailRcptnVo>();
        mailList.add(new MailRcptnVo("jeongsk@kbrainc.com", 0));
        mailList.add(new MailRcptnVo("songjk@kbrainc.com", 0));
        mailVo = new MailVo("plum@gmail.com", null, "다중 메일 테스트", "다중메일 테스트 입니다.", 0, "J", 0);
        Map<String, Object> resMap2 = mailService.sendMultiMail(mailList, mailVo);
    	
        return "OK";
    }
}
