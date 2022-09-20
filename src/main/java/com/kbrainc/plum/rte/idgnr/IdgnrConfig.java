package com.kbrainc.plum.rte.idgnr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kbrainc.plum.rte.crypto.CryptoAES256;

/**
* id generation을 서비스하는 bean을 생성.
*
* <pre>
* com.kbrainc.plum.rte.idgnr
* - IdgnrConfig.java
* </pre>
*
* @ClassName   : IdgnrConfig 
* @Description : id generation을 서비스하는 bean을 생성. 
* @author      : Zeniel
* @date        : 2021. 2. 24.
* @Version     : 1.0 
* @Company     : CopyrightⒸ ZENIEL. All Rights Reserved
*/
@Configuration
public class IdgnrConfig {

	@Value("${uuid.gen.address}")
    private String address;

    @Bean
    public UUIdGnr uuidGnr() throws Exception {
    	UUIdGnr uuidGnr = new UUIdGnr();
    	uuidGnr.setAddress(address);
        return uuidGnr;
    }
}
