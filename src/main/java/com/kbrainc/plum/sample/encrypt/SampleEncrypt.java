package com.kbrainc.plum.sample.encrypt;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import groovyjarjarcommonscli.ParseException;

/**
 * 
 * SampleEncrypt
 *
 * <pre>
 * com.kbrainc.plum.sample.encrypt - SampleEncrypt.java
 * </pre>
 *
 * @ClassName : SampleEncrypt
 * @Description : TODO
 * @author : KBRAINC
 * @date : 2021. 2. 17.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class SampleEncrypt {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${crypto.key}")
    private String cryptoKey;
	
	@Resource(name="digestService")
	EgovDigestService digestService;
	
    @Resource(name="ariaCryptoService")
    EgovCryptoService cryptoService;
	
	public void encryptPBESample() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		
		// saltGenerator를 지정하지 않으면 RandomSaltGenerator를 default로 사용.
		// RandomSaltGenerator를 사용하는 경우는 암호화된 결과 값이 매번 바뀜.
		encryptor.setSaltGenerator(new StringFixedSaltGenerator("someFixedSalt"));
		
		encryptor.setPassword(cryptoKey);
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		
		String str = "PlumFramework";
		String encStr = encryptor.encrypt(str);
		String decStr = encryptor.decrypt(encStr);
		
		logger.info("Plain Text : {}, Encrypt Text : {}, Decrypt Text : {}", str, encStr, decStr);
	}
	
	public void digestSample() {
		String data = "PlumFramework";
		byte[] digested = digestService.digest(data.getBytes());
		
		logger.info("Hashed : {}", digested.toString());
		
		// crypto.hashed.password 생성시작
    	//EgovPasswordEncoder passwordEncoder = new EgovPasswordEncoder();
    	//passwordEncoder.setAlgorithm("SHA-256");
    	//String hashed = passwordEncoder.encryptPassword(cryptoKey);
    	//logger.info("Hashed : {}", hashed);
    	// crypto.hashed.password 생성끝
	}
	
	public void ARIACryptoSample() {
		
		String[] testString = {
				"This is a testing...\nHello!",
				"한글 테스트입니다...",
				"!@#$%^&*()_+|~{}:\"<>?-=\\`[];',./"
		};
		
		String password = cryptoKey;
		 
		try {
			for (String str : testString) {
				byte[] encrypted = cryptoService.encrypt(str.getBytes("UTF-8"), password);
				logger.info("encrypted Text : {}", encrypted.toString());
		 
				byte[] decrypted = cryptoService.decrypt(encrypted, password);
				logger.info("decrypted Text : {}", new String(decrypted, "UTF-8"));
			}
		} catch (EncryptionOperationNotPossibleException e) {
			logger.error("ARIACryptoSample.EncryptionOperationNotPossibleException.88L");
		} catch (Exception e) {
            logger.error("ARIACryptoSample.Exception.88L");
        }		
	}

}
