package com.kbrainc.plum.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

@Controller
public class ExController {

    @Autowired @Qualifier("MailNhnService")
    private MailService mailService;
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @ResponseBody
    @RequestMapping(value = "/test/mail")    
    public String testSendMail() throws Exception {
        Context context = new Context();
        context.setVariable("title", "신청하신 환경교육이 <b style=\"color:#237db1;\">2023-02-28</b> 시작합니다.");
        
        context.setVariable("content", "<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">신청하신 환경교육이 곧 시작됩니다. <br> 교육 일정을 확인해주세요. </td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
        context.setVariable("portalUrl", CommonUtil.portalUrl);
        String contents = templateEngine.process("mail/mail_basic_template", context);
    
        MailVo mailVo = new MailVo();
        mailVo.setRcptnEmail("jeongsk@kbrainc.com");
        mailVo.setTitle("메일 발송 테스트");
        mailVo.setCntnts(contents);
        Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
        
        List<MailRcptnVo> mailList  = new ArrayList<MailRcptnVo>();
        mailList.add(new MailRcptnVo("jeongsk@kbrainc.com", 0));
        mailList.add(new MailRcptnVo("songjk@kbrainc.com", 0));
        mailVo = new MailVo(null, null, "다중 메일 테스트", contents, 0, "J", 0);
        Map<String, Object> resMap2 = mailService.sendMultiMail(mailList, mailVo);
    	
        return "OK";
    }
}
