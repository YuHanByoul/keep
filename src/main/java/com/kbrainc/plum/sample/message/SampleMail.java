package com.kbrainc.plum.sample.message;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 메일 발송 예제.
 *
 * <pre>
 * com.kbrainc.plum.sample.message
 * - SampleMail.java
 * </pre> 
 *
 * @ClassName : SampleMail
 * @Description : 메일 발송을 위한 예제.
 * @author : KBRAINC
 * @date : 2021. 2. 22.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Slf4j
public class SampleMail {
    @Autowired
    private MailService mailService;

    /**
     * 
     * 단 건 메일을 발송한다. 
     *
     * @Title : sendMail
     * @Description : 단 건 메일을 발송한다.
     * @return boolean
     */
    public boolean sendMail() {
    	try {
    		MailVo mailVo = new MailVo();
    		mailVo.setRcptnEmail("comnics@gmail.com");
    		mailVo.setTitle("메일발송 테스트입니다.");
    		mailVo.setCntnts("메일발송 테스트입니다.");
    		
			mailService.sendMail(mailVo);
		} catch (SQLException e) {
			log.error("sendMail.SQLException.48L");
		} catch (Exception e) {
            log.error("sendMail.Exception.48L");
        }
    	
    	return true;
    }
}
