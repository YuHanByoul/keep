package com.kbrainc.plum;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class TestMail {
	
	@Autowired @Qualifier("MailService")
    private MailService mailService;
	
	static {
        ConfigurationFactory.profile = "local";
    }

	@Test
	void test() {
    	try {

    		MailVo mailVo = new MailVo("comnics@gmail.com", "comnics@gmail.com", "메일발송 테스트입니다.", "메일발송 테스트입니다.", 0, "S", 1233456789);
    		
			mailService.sendMail(mailVo);
		} catch (ResponseStatusException e) {
			log.error("test.ResponseStatusException.33L");
			fail("Check Messaging Server(8087).");
		} catch (Exception e) {
            log.error("test.Exception.33L");
            fail("Check Messaging Server(8087).");
        }

	}

}
