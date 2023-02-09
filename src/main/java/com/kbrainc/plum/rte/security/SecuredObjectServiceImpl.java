package com.kbrainc.plum.rte.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 스프링시큐리티 공통서비스.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - SecuredObjectServiceImpl.java
 * </pre> 
 *
 * @ClassName : SecuredObjectServiceImpl
 * @Description : 스프링시큐리티 커스터마이징에 사용되는 공통 서비스구현체이다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class SecuredObjectServiceImpl extends PlumAbstractServiceImpl implements SecuredObjectService {

    @Autowired
    private SecuredObjectDao securedObjectDao;

    @Override
    public Map selectUserLoginInfo(String loginid) throws Exception {
        return securedObjectDao.selectUserLoginInfo(loginid);
    }
    
    @Override
    public Map selectUserLoginInfoForOnepass(String userKey) throws Exception {
        return securedObjectDao.selectUserLoginInfoForOnepass(userKey);
    }

    @Override
    public List<Map<String, Object>> selectGrantedAuthority(String loginid) throws Exception {
        return securedObjectDao.selectGrantedAuthority(loginid);
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
        LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDao.getRolesAndUrl();
        Set<Object> keys = data.keySet();
        for (Object key : keys) {
            if (key instanceof CustomAntPathRequestMatcher) {
                ret.put((CustomAntPathRequestMatcher) key, data.get(key));
            } else if (key instanceof CustomRegexRequestMatcher) {
                ret.put((CustomRegexRequestMatcher) key, data.get(key));
            }
        }
        return ret;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getHttpsAndUrl() throws Exception {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
        LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDao.getHttpsAndUrl();
        Set<Object> keys = data.keySet();
        for (Object key : keys) {
            if (key instanceof CustomAntPathRequestMatcher) {
                ret.put((CustomAntPathRequestMatcher) key, data.get(key));
            } else if (key instanceof CustomRegexRequestMatcher) {
                ret.put((CustomRegexRequestMatcher) key, data.get(key));
            }
        }
        return ret;
    }

    /*
     * @Override public List<ConfigAttribute> getMatchedRequestMapping(String url)
     * throws Exception { return securedObjectDao.getMatchedRequestMapping(url); }
     */
}