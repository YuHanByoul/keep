package com.kbrainc.plum.rte.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 
 * 스프링시큐리티 커스터마이징에 사용되는 공통 서비스 인터페이스이다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - SecuredObjectService.java
 * </pre> 
 *
 * @ClassName : SecuredObjectService
 * @Description : 스프링시큐리티 커스터마이징에 사용되는 공통 서비스 인터페이스이다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface SecuredObjectService {

    /**
    * @Title : selectUserLoginInfo
    * @Description : 로그인 정보와 일치하는 사용자 정보를 가지고 온다.(로그인용)
    * @param loginid  로그인아이디
    * @throws Exception
    * @return Map 사용자 정보
    */
    public Map selectUserLoginInfo(String loginid) throws Exception;
    
    /**
    * @Title : selectUserLoginInfoForOnepass
    * @Description : 사용자키와 일치하는 사용자 정보를 가지고 온다.(디지털원패스 로그인용)
    * @param userKey  사용자키
    * @throws Exception
    * @return Map 사용자 정보
    */
    public Map selectUserLoginInfoForOnepass(String userKey) throws Exception;

    /**
    * @Title : selectGrantedAuthority
    * @Description : 로그인아이디에 부여된 사용자역할 목록을 가지고 온다.
    * @param loginid 로그인아이디
    * @throws Exception
    * @return List<Map> 역할 목록
    */
    public List<Map<String, Object>> selectGrantedAuthority(String loginid) throws Exception;

    /**
    * URL에 대한 역할의 매핑 정보를 얻어온다.
    * 
    * @return
    * @throws Exception
    */
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception;

    /**
    * URL에 대한 https의 매핑 정보를 얻어온다.
    * 
    * @return
    * @throws Exception
    */
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getHttpsAndUrl() throws Exception;

    /**
    * 특정 URL에 대한 롤매핑 정보를 얻어온다.
    * 
    * @param url
    * @return
    * @throws Exception
    */
    // public List<ConfigAttribute> getMatchedRequestMapping(String url) throws
    // Exception;
}