package com.kbrainc.plum.rte.security;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

/**
 * 
 * http와 https의 requestMap을 로드하여 반환하는 팩토리빈 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - UrlResourcesHttpsMapFactoryBean.java
 * </pre> 
 *
 * @ClassName : UrlResourcesHttpsMapFactoryBean
 * @Description : http와 https의 requestMap을 로드하여 반환하는 팩토리빈 클래스 이다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("urlResourcesHttpsMapFactoryBean")
public class UrlResourcesHttpsMapFactoryBean
        implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

    @Autowired
    private SecuredObjectService securedObjectService;

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;

    @PostConstruct
    public void init() throws Exception {
        requestMap = securedObjectService.getHttpsAndUrl();
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if (requestMap == null) {
            requestMap = securedObjectService.getHttpsAndUrl();
        }
        return requestMap;
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}