package com.kbrainc.plum.rte.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Component;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * 스프링시큐리티 커스터마이징에 사용되는 공통 DAO이다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - SecuredObjectDao.java
 * </pre> 
 *
 * @ClassName : SecuredObjectDao
 * @Description : 스프링시큐리티 커스터마이징에 사용되는 공통 DAO이다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Component
public class SecuredObjectDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String sqlUserLoginInfo;

    private String sqlGrantedAuthority;

    private String sqlRolesAndUrl;

    private String sqlHttpsAndUrl;
    
    @Autowired
    public SecuredObjectDao(SecurityProperties securityProperties) {
        this.sqlUserLoginInfo = securityProperties.getDEF_USER_LOGIN_INFO_QUERY();
        this.sqlGrantedAuthority = securityProperties.getDEF_GRANTED_AUTHORITY_QUERY();
        this.sqlRolesAndUrl = securityProperties.getDEF_ROLES_AND_URL_QUERY();
        this.sqlHttpsAndUrl = securityProperties.getDEF_HTTPS_AND_URL_QUERY();
    }

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * URL에 대한 역할정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlRolesAndUrl() {
        return sqlRolesAndUrl;
    }

    /**
     * URL에대한 역할정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlRolesAndUrl
     */
    public void setSqlRolesAndUrl(String sqlRolesAndUrl) {
        this.sqlRolesAndUrl = sqlRolesAndUrl;
    }

    /**
     * URL에 대한 channel정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlHttpsAndUrl() {
        return sqlHttpsAndUrl;
    }

    /**
     * URL에대한 channel정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlHttpsAndUrl
     */
    public void setSqlHttpsAndUrl(String sqlHttpsAndUrl) {
        this.sqlHttpsAndUrl = sqlHttpsAndUrl;
    }

    public Map selectUserLoginInfo(String loginid) throws Exception {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("loginid", loginid);
        return this.namedParameterJdbcTemplate.queryForMap(sqlUserLoginInfo, paramSource);
    }

    public List<Map<String, Object>> selectGrantedAuthority(String loginid) throws Exception {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("loginid", loginid);
        return this.namedParameterJdbcTemplate.queryForList(sqlGrantedAuthority, paramSource);
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType) throws Exception {

        LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap = new LinkedHashMap<Object, List<ConfigAttribute>>();
        LinkedHashMap<String, Object> objMappingMap = new LinkedHashMap<String, Object>();

        String sqlRolesAndResources;
        sqlRolesAndResources = getSqlRolesAndUrl();

        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(sqlRolesAndResources,
                new HashMap<String, String>());

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        String preResource = null;
        String presentResourceStr;
        Object presentResource;
        int i = 0;
        while (itr.hasNext()) {
            i++;
            tempMap = itr.next();

            presentResourceStr = (String) tempMap.get(resourceType);
            String bbsId = StringUtil.nvl(tempMap.get("bbsid"));

            if (!"".equals(bbsId)) {
                presentResource = new RegexRequestMatcher(presentResourceStr, null);
            } else {
                presentResource = new AntPathRequestMatcher(presentResourceStr, null);
            }

            List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

            if (preResource != null && presentResourceStr.equals(preResource)) {
                Object obj = objMappingMap.get(presentResourceStr);
                List<ConfigAttribute> preAuthList = resourcesMap.get(obj);
                resourcesMap.remove(obj);
                Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();
                while (preAuthItr.hasNext()) {
                    SecurityConfig tempConfig = (SecurityConfig) preAuthItr.next();
                    configList.add(tempConfig);
                }
            }

            configList.add(new SecurityConfig(String.valueOf(tempMap.get("authority"))));

            resourcesMap.put(presentResource, configList);
            objMappingMap.put(presentResourceStr, presentResource);
            preResource = presentResourceStr;
        }

        return resourcesMap;
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
        return getRolesAndResources("url");
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getHttpsAndResources(String resourceType) throws Exception {

        LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap = new LinkedHashMap<Object, List<ConfigAttribute>>();
        LinkedHashMap<String, Object> objMappingMap = new LinkedHashMap<String, Object>();

        String sqlHttpsAndResources;
        sqlHttpsAndResources = getSqlHttpsAndUrl();

        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(sqlHttpsAndResources,
                new HashMap<String, String>());

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        String preResource = null;
        String presentResourceStr;
        Object presentResource;
        int i = 0;
        while (itr.hasNext()) {
            i++;
            tempMap = itr.next();

            presentResourceStr = (String) tempMap.get(resourceType);
            String bbsId = StringUtil.nvl(tempMap.get("bbsid"));

            if (!"".equals(bbsId)) {
                presentResource = new RegexRequestMatcher(presentResourceStr, null);
            } else {
                presentResource = new AntPathRequestMatcher(presentResourceStr, null);
            }

            List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

            if (preResource != null && presentResourceStr.equals(preResource)) {
                Object obj = objMappingMap.get(presentResourceStr);
                // REQUIRES_SECURE_CHANNEL 가 아닌 경우(REQUIRES_INSECURE_CHANNEL, http) 제거 동일 url에
                // 대하여 https(REQUIRES_SECURE_CHANNEL)인경우는 유지한다.
                List<ConfigAttribute> preChannelList = resourcesMap.get(obj);
                if (!"REQUIRES_SECURE_CHANNEL".equals(((SecurityConfig) preChannelList.get(0)).getAttribute())) {
                    resourcesMap.remove(obj);
                }
            }

            configList.add(new SecurityConfig(String.valueOf(tempMap.get("channel"))));

            resourcesMap.put(presentResource, configList);
            objMappingMap.put(presentResourceStr, presentResource);
            preResource = presentResourceStr;
        }

        return resourcesMap;
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getHttpsAndUrl() throws Exception {
        return getHttpsAndResources("url");
    }
}