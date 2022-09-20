package com.kbrainc.plum.config.crypto;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.egovframe.rte.fdl.cryptography.EgovPasswordEncoder;
import org.egovframe.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;
import org.egovframe.rte.fdl.cryptography.impl.EgovDigestServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kbrainc.plum.rte.crypto.CryptoAES256;

/**
 * 
 * 암복호화 관련 컨피그.
 *
 * <pre>
 * com.kbrainc.plum.config.crypto
 * - CryptoConfig.java
 * </pre> 
 *
 * @ClassName : CryptoConfig
 * @Description : 암복호화 관련 컨피그.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Configuration
public class CryptoConfig {

    @Value("${crypto.key}")
    private String cryptoKey;
    
    @Value("${crypto.password.algorithm}")
    private String passwordAlgorithm;

    @Value("${crypto.hashed.password}")
    private String hashedPassword;

    /**
    * 암복호화 사용을 위한 crypto빈 등록.
    *
    * @Title       : crypto 
    * @Description : 암복호화 사용을 위한 crypto빈 등록
    * @throws Exception 예외
    * @return CryptoAES256 CryptoAES256객체
    */
    @Bean
    public CryptoAES256 cryptoAES256() throws Exception {
        return new CryptoAES256(cryptoKey);
    }
    
    /**
     * 
     * Hash 사용을 위한 빈 등록. 
     *
     * @Title : digestService
     * @Description : Hash 사용을 위해 egov의 EgovDigestServiceImpl을 빈 등록한다.
     * @throws Exception
     * @return EgovDigestService
     */
    @Bean
    public EgovDigestService digestService() throws Exception {
    	return new EgovDigestServiceImpl();
    }

    /**
     * 
     * ARIA 암호화 사용을 위한 빈 등록.
     *
     * @Title : ariaCryptoService
     * @Description : ARIA 암호화 사용을 위해 egov의 EgovARIACryptoServiceImpl을 빈 등록한다.
     * @throws Exception
     * @return EgovCryptoService
     */
    @Bean
    public EgovCryptoService ariaCryptoService() throws Exception {
    	EgovPasswordEncoder passwordEncoder = new EgovPasswordEncoder();
    	passwordEncoder.setAlgorithm(passwordAlgorithm);
    	passwordEncoder.setHashedPassword(hashedPassword);
    	
    	EgovCryptoService cryptoService = new EgovARIACryptoServiceImpl();
    	cryptoService.setPasswordEncoder(passwordEncoder);
    	
    	return cryptoService;
    }
}