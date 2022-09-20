package com.kbrainc.plum;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestMail {
	
	@Autowired @Qualifier("MailService")
    private MailService mailService;

	@Test
	void test() {
    	try {

    		MailVo mailVo = new MailVo("comnics@gmail.com", "comnics@gmail.com", "메일발송 테스트입니다.", "메일발송 테스트입니다.", 0, "S", 1233456789);
    		
			mailService.sendMail(mailVo);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Check Messaging Server(8087).");
		}

	}

}
