package com.kbrainc.plum.config.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 * UserInfo 어노테이션 ArgumentResolver.
 *
 * <pre>
 * com.kbrainc.plum.config.annotation
 * - AnnotationUserInfoArgumentResolver.java
 * </pre> 
 *
 * @ClassName : AnnotationUserInfoArgumentResolver
 * @Description : UserInfo 어노테이션 ArgumentResolver
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Configuration
public class AnnotationUserInfoArgumentResolver implements HandlerMethodArgumentResolver {
	
    public AnnotationUserInfoArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return com.kbrainc.plum.rte.model.UserVo.class.isAssignableFrom(parameter.getParameterType())
                && parameter.hasParameterAnnotation(UserInfo.class);
    }

    /**
     * .
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserVo userInfo = null;
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        userInfo = (UserVo) request.getSession().getAttribute("user");
        return userInfo;
    }
}