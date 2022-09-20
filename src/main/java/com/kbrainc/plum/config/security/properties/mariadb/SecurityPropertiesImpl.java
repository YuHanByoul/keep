package com.kbrainc.plum.config.security.properties.mariadb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

@ConfigurationProperties("security")
public class SecurityPropertiesImpl implements SecurityProperties {

	private static org.apache.commons.configuration.Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();

    private static String sysSeCd = applicationConfig.getString("system.separation.code");
    
	
	/** 디폴트타겟URL */
	private final String DEFAULT_TARGET_URL = "/main.html";
	
	/**
     * 사용자 로그인 정보를 확인하기 위한 쿼리
     */
	private String DEF_USER_LOGIN_INFO_QUERY = 
			"  SELECT  A.USERID, A.NM, A.PWD "
			+ "FROM  TB_USER A "
            + "LEFT OUTER JOIN TB_USER_DTL D "
            + "ON A.USERID = D.USERID "
            + "WHERE A.ACNT = :loginid "
            + "AND A.DEL_YN = 'N' "
            + "AND A.ACNT_LOCK_YN = 'N' ";

    /**
     * 사용자에게 부여된 역할을 확인하기 위한 쿼리
     */
	private final String DEF_GRANTED_AUTHORITY_QUERY = 
			"  SELECT B.ROLEID, C.NM "
			+ "FROM TB_USER A, "
            + "     TB_ROLE_USER B, "
            + "     TB_ROLE C "
            + "WHERE A.USERID = B.USERID "
            + "AND A.ACNT = :loginid "
            + "AND B.ROLE_STRT_DT <= NOW() "
            + "AND B.ROLE_END_DT >= NOW() "
            + "AND B.ROLEID = C.ROLEID "
            + "AND C.SE_CD = '" + sysSeCd + "' ";

    /**
     * url 형식인 보호자원 - Role 맵핑정보를 조회하는 default 쿼리
     */
	private final String DEF_ROLES_AND_URL_QUERY = 
			"  SELECT DISTINCT URL, 0 AS AUTHORITY "
			+ "FROM ( "
            + "SELECT C.URL, A.ROLEID AS AUTHORITY "
            + "FROM TB_MENU B "
            + "LEFT OUTER JOIN TB_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "INNER JOIN TB_ROLE_MENU A  "
            + "ON A.MENUID = B.MENUID "
            + "WHERE B.LOGIN_YN ='N' "
            + "AND B.SITEID IN (SELECT SITEID FROM TB_SITE WHERE SYS_SE_CD = '" + sysSeCd + "' AND USE_YN ='Y') "
            + "AND C.URL IS NOT NULL "
            + "AND C.URL <> ''"
            + ") T "
            + "UNION ALL "
            + "SELECT URL, AUTHORITY "
            + "FROM ( "
            + "SELECT C.URL, A.ROLEID AS AUTHORITY "
            + "FROM TB_MENU B LEFT OUTER JOIN TB_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "AND C.URL IS NOT NULL "
            + "AND C.URL <> '' "
            + "LEFT OUTER JOIN TB_ROLE_MENU A "
            + "ON A.MENUID = B.MENUID "
            + "WHERE B.SITEID IN (SELECT SITEID FROM TB_SITE WHERE SYS_SE_CD = '" + sysSeCd + "' AND USE_YN ='Y')"
            + ") T "
            + "WHERE URL IS NOT NULL "
            + "AND URL != '' "
            + "UNION ALL "
            + "SELECT B.URL, A.ROLEID AS AUTHORITY "
            + "FROM ("
            + "SELECT ROLEID "
            + "FROM TB_ROLE "
            + "UNION ALL "
            + "SELECT 0"
            + ") A, "
            + "(SELECT '/' AS URL "
            + "UNION ALL "
            + "SELECT '/login' AS URL "
            + "UNION ALL "
            + "SELECT '/logout' AS URL "
            + "UNION ALL "
            + "SELECT '/error' AS URL "
            + "UNION ALL "
            + "SELECT '/dupLogout' AS URL "
            + "UNION ALL " 
            + "SELECT '/mng/pop/getDataForCommnonPopup.do' AS URL"
            + " UNION ALL "
            + "SELECT '/cmm/changeRole.do' AS URL) AS B "
            + "ORDER BY URL ";

    /**
     * url 형식인 보호자원 - channel 맵핑정보를 조회하는 default 쿼리
     */
	private final String DEF_HTTPS_AND_URL_QUERY = 
			"  SELECT URL, "
			+ "       CASE HTTPS_USE_YN WHEN 'N' THEN 'REQUIRES_INSECURE_CHANNEL' "
			+ "                         WHEN 'Y' THEN 'REQUIRES_SECURE_CHANNEL' "
			+ "       END AS CHANNEL " // ANY_CHANNEL도 있음
            + "FROM ( "
            + "SELECT C.URL, B.HTTPS_USE_YN "
            + "FROM TB_MENU B "
            + "LEFT OUTER JOIN TB_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "AND C.URL IS NOT NULL "
            + "WHERE B.SITEID IN (SELECT SITEID FROM TB_SITE WHERE SYS_SE_CD = '" + sysSeCd + "' AND USE_YN ='Y')"
            + ") T "
            + "WHERE URL IS NOT NULL "
            + "AND URL != '' "
            + "UNION ALL "
            + "SELECT '/' AS URL, 'REQUIRES_SECURE_CHANNEL' AS CHANNEL "
            + "UNION ALL "
            + "SELECT '/login' AS URL, 'REQUIRES_SECURE_CHANNEL' AS CHANNEL "
            + "ORDER BY URL ";

    /**
    * 디폴트타겟URL 반환.
    *
    * @Title       : getDEFAULT_TARGET_URL 
    * @Description : 디폴트타겟URL 반환.
    * @return String 디폴트타겟URL
    */
	@Override
    public String getDEFAULT_TARGET_URL() {
		return DEFAULT_TARGET_URL;
	}

	/**
	* 사용자 로그인 정보를 확인하기 위한 SQL 반환.
	*
	* @Title       : getDEF_USER_LOGIN_INFO_QUERY 
	* @Description : 사용자 로그인 정보를 확인하기 위한 SQL 반환.
	* @return String 사용자 로그인 정보를 확인하기 위한 SQL 문자열 
	*/
    @Override
	public String getDEF_USER_LOGIN_INFO_QUERY() {
		return DEF_USER_LOGIN_INFO_QUERY;
	}

    /**
    * 사용자에게 부여된 역할을 확인하기 위한 SQL 반환.
    *
    * @Title       : getDEF_GRANTED_AUTHORITY_QUERY 
    * @Description : 사용자에게 부여된 역할을 확인하기 위한 SQL 반환.
    * @return String 사용자에게 부여된 역할을 확인하기 위한 SQL 문자열
    */
    @Override
	public String getDEF_GRANTED_AUTHORITY_QUERY() {
		return DEF_GRANTED_AUTHORITY_QUERY;
	}

    /**
    * url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 반환.
    *
    * @Title       : getDEF_ROLES_AND_URL_QUERY 
    * @Description : url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 반환.
    * @return String url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 문자열.
    */
    @Override
	public String getDEF_ROLES_AND_URL_QUERY() {
		return DEF_ROLES_AND_URL_QUERY;
	}

    /**
    * url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 반환.
    *
    * @Title       : getDEF_HTTPS_AND_URL_QUERY 
    * @Description : url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 반환.
    * @return String url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 문자열.
    */
    @Override
	public String getDEF_HTTPS_AND_URL_QUERY() {
		return DEF_HTTPS_AND_URL_QUERY;
	}
}
