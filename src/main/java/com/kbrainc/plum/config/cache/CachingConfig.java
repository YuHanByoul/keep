package com.kbrainc.plum.config.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.kbrainc.plum.rte.util.ApplicationContextProvider;


@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {

    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/cache/ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        
        return ehCacheManagerFactoryBean;
    }
	
    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        ApplicationContextProvider applicationContextProvider = (ApplicationContextProvider) applicationContext.getBean("applicationContextProvider");
        applicationContextProvider.setApplicationContext(applicationContext);
        cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        
        return cacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
    
    @Override
    public CacheResolver cacheResolver() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public CacheErrorHandler errorHandler() {
        // TODO Auto-generated method stub
        return null;
    }
}