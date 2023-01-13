package com.kbrainc.plum.config.locale;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
* locale 관련 설정.
*
* <pre>
* com.kbrainc.plum.config.locale
* - LocaleConfig.java
* </pre>
*
* @ClassName : LocaleConfig
* @Description : locale 관련 설정.
* @author : KBRAINC
* @date : 2023. 1. 10.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver cookieLocaleResolver = new SessionLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public MessageSource messageSource(@Value("${spring.messages.basename}") String basename, @Value("${spring.messages.encoding}") String encoding) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);

        return messageSource;
    }
    
    @Bean
    public MessageSourceAccessor messageSourceAccessor(@Qualifier("messageSource") MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }
}