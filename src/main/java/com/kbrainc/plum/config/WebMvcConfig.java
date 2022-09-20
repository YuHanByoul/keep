package com.kbrainc.plum.config;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.kbrainc.plum.config.annotation.AnnotationUserInfoArgumentResolver;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("httpInterceptor")
    private HandlerInterceptorAdapter interceptor;
    
    @Value("${file.upload-dir}")
 	private String uploadImagesPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("=========== Add Interceptors ===========");
        registry.addInterceptor(interceptor).addPathPatterns("/**/*.html","/**/*.do");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        logger.info("=========== Add Argument Resolvers ===========");
        resolvers.add(new AnnotationUserInfoArgumentResolver());

    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
            .addResourceLocations("/resources/static/images/", "classpath:/static/images/")
            .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        
        registry.addResourceHandler("/fonts/**")
        .addResourceLocations("/resources/static/fonts/", "classpath:/static/fonts/")
        .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        
	    registry.addResourceHandler("/ckEimg/**")
        .addResourceLocations("file:///" +uploadImagesPath + "/ckEimg/")
        .setCachePeriod(3600)
        .resourceChain(true)
        .addResolver(new PathResourceResolver());
    }
}
