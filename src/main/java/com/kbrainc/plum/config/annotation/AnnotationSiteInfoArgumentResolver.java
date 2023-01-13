package com.kbrainc.plum.config.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;

/**
 * 
 * SiteInfo 어노테이션 ArgumentResolver.
 *
 * <pre>
 * com.kbrainc.plum.config.annotation
 * - AnnotationSiteInfoArgumentResolver.java
 * </pre> 
 *
 * @ClassName : AnnotationSiteInfoArgumentResolver
 * @Description : SiteInfo 어노테이션 ArgumentResolver
 * @author : KBRAINC
 * @date : 2023. 1. 11.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Configuration
public class AnnotationSiteInfoArgumentResolver implements HandlerMethodArgumentResolver {
	
    public AnnotationSiteInfoArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return com.kbrainc.plum.rte.model.SiteInfoVo.class.isAssignableFrom(parameter.getParameterType())
                && parameter.hasParameterAnnotation(SiteInfo.class);
    }

    /**
     * .
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SiteInfoVo siteInfo = null;
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        siteInfo = (SiteInfoVo) request.getSession().getAttribute("site");
        return siteInfo;
    }
}