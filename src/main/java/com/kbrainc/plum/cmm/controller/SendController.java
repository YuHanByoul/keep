package com.kbrainc.plum.cmm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.cmm.service.SmsNhnService;

/**
 * 
 * 발송 Controller
 *
 * <pre>
 * com.kbrainc.plum.cmm.controller
 * - SendController.java
 * </pre>
 *
 * @ClassName : SendController
 * @Description : 발송 Controller
 * @author : KBRAINC
 * @date : 2023. 01. 31.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SendController {

    @Autowired
    private SmsNhnService smsService;
    
    @Autowired
    private AlimtalkNhnService alimtalkService;
    
    /**
    * 문자 발송
    *
    * @Title : sendSms
    * @Description : 문자 발송
    * @return Map<String, Object> 발송결과
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/sms/sendSms.do")
    @ResponseBody
    public Map<String, Object> sendSms(@RequestParam("type") String type, @RequestParam(value="title", required=false) String title, @RequestParam("msg") String msg, @RequestParam("phoneList") String[] phoneList) throws Exception {
        Map<String, Object> result = null;
        if("sms".equals(type)) {
            result = smsService.sendSms(msg, phoneList);
        } else if("mms".equals(type)) {
            result = smsService.sendMms(title, msg, phoneList);
        } else {
            result = smsService.sendAuth(msg, phoneList);
        }
        
        return result;
    }
    
    /**
    * 알림톡 발송
    *
    * @Title : sendAlimtalk
    * @Description : 알림톡 발송
    * @return Map<String, Object> 발송결과
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/alimtalk/sendAlimtalk.do")
    @ResponseBody
    public Map<String, Object> sendAlimtalk(@RequestParam(value="templateCode") String templateCode, @RequestParam("recipientList") String recipientList) throws Exception {
        return alimtalkService.sendAlimtalk(templateCode, recipientList);
    }
    
}
