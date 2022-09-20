package com.kbrainc.plum.sample.tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {

    @Bean
    public HelloDialect extraLinkDialect() {
        return new HelloDialect();
    }
    
}
