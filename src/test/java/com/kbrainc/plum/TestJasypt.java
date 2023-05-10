package com.kbrainc.plum;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

/**
 * 
 * TestEncrypt
 *
 * <pre>
 * com.kbrainc.plum.sample.encrypt - TestEncrypt.java
 * </pre>
 *
 * @ClassName : TestEncrypt
 * @Description : TODO
 * @author : KBRAINC
 * @date : 2021. 2. 17.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class TestJasypt {

	static {
	    ConfigurationFactory.profile = "local";
	}
	
	@Value("${jasypt.encryptor.password}")
    private String encryptKey;
	
	@Test
    public void jasyptTest() {
	    String key = "";
        String url = "";
        String username = "";
        String password = "";

        //System.out.println(jasyptEncoding(key));
        //System.out.println(jasyptEncoding(url));
        //System.out.println(jasyptEncoding(username));
        //System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(encryptKey);
        return pbeEnc.encrypt(value);
    }

}
