package com.kbrainc.plum.rte.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 
 * 리소스와 리소스에 접근가능한 역할에 대하여 metadatasource를 로드/리로드 한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - ReloadableFilterSecurityInterceptorMetadataSource.java
 * </pre> 
 *
 * @ClassName : ReloadableFilterSecurityInterceptorMetadataSource
 * @Description : 리소스와 리소스에 접근가능한 역할에 대하여 metadatasource를 로드/리로드 한다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class ReloadableFilterSecurityInterceptorMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "urlResourcesRolesMapFactoryBean")
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    @Autowired
    private SecuredObjectService securedObjectService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> result = null;
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reload() throws Exception {
        synchronized (this) {
            LinkedHashMap<RequestMatcher, List<ConfigAttribute>> reloadedMap = securedObjectService.getRolesAndUrl();

            Iterator<Entry<RequestMatcher, List<ConfigAttribute>>> iterator = reloadedMap.entrySet().iterator();

            // 이전 데이터 삭제
            requestMap.clear();

            while (iterator.hasNext()) {
                Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
                requestMap.put(entry.getKey(), entry.getValue());
            }

            if (logger.isInfoEnabled()) {
                logger.info("Secured Url Resources - Role Mappings reloaded at Runtime!");
            }
        }
    }
}