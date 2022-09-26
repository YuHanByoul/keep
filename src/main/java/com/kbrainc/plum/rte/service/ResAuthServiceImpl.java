package com.kbrainc.plum.rte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.model.RoleInfoVo;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 
 * 자원에 따른 역할ID를 메모리에 적재하는 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.service
 * - ResAuthServiceImpl.java
 * </pre> 
 *
 * @ClassName : ResAuthServiceImpl
 * @Description : 자원에 따른 역할ID를 메모리에 적재하는 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class ResAuthServiceImpl extends PlumAbstractServiceImpl implements ResAuthService {
    
    @Autowired
    CacheManager cacheManager;

    /**
    * 캐시를 삭제한다.
    *
    * @Title       : removeCacheForAuth 
    * @Description : 캐시를 삭제한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    public void removeCacheForAuth() throws Exception {
        Ehcache authMap = (Ehcache)cacheManager.getCache("authMap").getNativeCache();
        Element element = authMap.get("auth");
        
        if (element != null) {
            authMap.remove("auth");
        }
    }


}
