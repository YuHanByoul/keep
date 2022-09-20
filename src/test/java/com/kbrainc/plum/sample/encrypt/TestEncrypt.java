package com.kbrainc.plum.sample.encrypt;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEncrypt {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="digestService")
	EgovDigestService digestService;
	
    @Resource(name="ariaCryptoService")
    EgovCryptoService cryptoService;
	
	@Test
	public void encryptPBETest() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		
		// saltGenerator를 지정하지 않으면 RandomSaltGenerator를 default로 사용.
		// RandomSaltGenerator를 사용하는 경우는 암호화된 결과 값이 매번 바뀜.
		encryptor.setSaltGenerator(new StringFixedSaltGenerator("someFixedSalt"));
		
		encryptor.setPassword("password1234");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		
		String str = "PlumFramework";
		String encStr = encryptor.encrypt(str);
		String decStr = encryptor.decrypt(encStr);
		
		logger.info("Plain Text : {}, Encrypt Text : {}, Decrypt Text : {}", str, encStr, decStr);
	}
	
	@Test
	public void digestTest() {
		String data = "PlumFramework";
		byte[] digested = digestService.digest(data.getBytes());
		
		logger.info("Hashed : {}", digested.toString());
		
//		String password = "Plumframework";
//    	EgovPasswordEncoder passwordEncoder = new EgovPasswordEncoder();
//    	passwordEncoder.setAlgorithm("SHA-256");
//    	String hashed = passwordEncoder.encryptPassword(password);
//		
//    	logger.info("Hashed : {}", hashed);
    	
		assertTrue(digestService.matches(data.getBytes(), digested));
	}
	
	@Test
	public void ARIACryptoTest() {
		
		String[] testString = {
				"This is a testing...\nHello!",
				"한글 테스트입니다...",
				"!@#$%^&*()_+|~{}:\"<>?-=\\`[];',./"
		};
		
		String password = "Plumframework";
		 
		try {
			for (String str : testString) {
				byte[] encrypted = cryptoService.encrypt(str.getBytes("UTF-8"), password);
				logger.info("encrypted Text : {}", encrypted.toString());
		 
				byte[] decrypted = cryptoService.decrypt(encrypted, password);
				logger.info("decrypted Text : {}", decrypted.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
