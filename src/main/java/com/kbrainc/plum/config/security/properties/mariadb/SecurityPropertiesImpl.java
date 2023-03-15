package com.kbrainc.plum.config.security.properties.mariadb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.configuration.ConfigurationFactory;

@ConfigurationProperties("security")
public class SecurityPropertiesImpl implements SecurityProperties {

	/*private static org.apache.commons.configuration.Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();*/

	/** 디폴트타겟URL */
	private static final String DEFAULT_TARGET_URL = "/main.html";
	
	/**
    * 사용자 로그인 정보를 확인하기 위한 쿼리
    */
	private final String DEF_USER_LOGIN_INFO_QUERY = 
			"  SELECT  A.USERID, A.ACNT, A.NM, NVL(A.PSWD, D.PSWD) AS PSWD, A.INSTID, A.INSTPIC_ROLE_CD, A.ACNT_LOCK_YN, A.ACNT_LOCK_CD, A.PRVC_VLDTY, DATE_FORMAT(D.REG_DT, '%Y-%m-%d') AS DRMNCY_REG_DT, A.PSWD_MDFCN_DT, A.LGN_FAIL_CNT, B.APRV_STTS_CD AS INST_APRV_STTS_CD, B.USE_YN AS INST_USE_YN, "
	        + "NVL((SELECT USE_YN AS SITE_USE_YN FROM TB_CMM_SITE WHERE INSTID = A.INSTID AND SYS_KND_CD = 'T' AND USE_YN = 'Y' LIMIT 1), 'N') AS SITEAPLY_USE_YN, "
	        + "NVL((SELECT 'Y' FROM TB_CMM_USER_ESYLGN WHERE USERID = A.USERID AND ESYLGN_CD = '106101' LIMIT 1), 'N') AS ONEPASS_LINK_YN, "
	        + "(SELECT COUNT(USERID) FROM TB_CMM_USER_ESYLGN WHERE USERID = A.USERID) AS ESYLGN_LINK_CNT, "
	        + "A.CI_PARNTS, "
	        + "E.USERID AS EXPRT_USERID "
			+ "FROM  TB_CMM_USER A LEFT OUTER JOIN TB_CMM_INST B ON A.INSTID = B.INSTID "
			+ "      LEFT OUTER JOIN TB_CMM_USER_DRMNCY D ON A.USERID = D.USERID "
			+ "      LEFT OUTER JOIN TB_ASS_EXPRT E ON A.USERID = E.USERID AND E.STTS_CD = '134103' "
            + "WHERE A.ACNT = :loginid "
            + "AND A.DEL_YN = 'N' ";
	
	/**
    * 디지털원패스 사용자 로그인 정보를 확인하기 위한 쿼리
    */
    private final String DEF_USER_LOGIN_INFO_FOR_ONEPASS_QUERY = 
            "  SELECT  A.USERID, A.ACNT, A.NM, NVL(A.PSWD, D.PSWD) AS PSWD, A.INSTID, A.INSTPIC_ROLE_CD, A.ACNT_LOCK_YN, A.ACNT_LOCK_CD, A.PRVC_VLDTY, DATE_FORMAT(D.REG_DT, '%Y-%m-%d') AS DRMNCY_REG_DT, A.PSWD_MDFCN_DT, A.LGN_FAIL_CNT, B.APRV_STTS_CD AS INST_APRV_STTS_CD, B.USE_YN AS INST_USE_YN, "
            + "NVL((SELECT USE_YN AS SITE_USE_YN FROM TB_CMM_SITE WHERE INSTID = A.INSTID AND SYS_KND_CD = 'T' AND USE_YN = 'Y' LIMIT 1), 'N') AS SITEAPLY_USE_YN, "
            + "'Y' AS ONEPASS_LINK_YN, "
            + "(SELECT COUNT(USERID) FROM TB_CMM_USER_ESYLGN WHERE USERID = A.USERID) AS ESYLGN_LINK_CNT, "
            + "A.CI_PARNTS, "
            + "E.USERID AS EXPRT_USERID  "
            + "FROM  TB_CMM_USER A LEFT OUTER JOIN TB_CMM_INST B ON A.INSTID = B.INSTID "
            + "      LEFT OUTER JOIN TB_CMM_USER_DRMNCY D ON A.USERID = D.USERID "
            + "      LEFT OUTER JOIN TB_ASS_EXPRT E ON A.USERID = E.USERID AND E.STTS_CD = '134103' "
            + "WHERE A.USERID = (SELECT USERID FROM TB_CMM_USER_ESYLGN WHERE USERKEY = :userKey AND ESYLGN_CD = '106101') "
            + "AND A.DEL_YN = 'N' ";

    /**
    * 사용자 로그인 정보를 확인하기 위한 쿼리
    */
    private final String DEF_USER_LOGIN_INFO_FOR_SSO_QUERY = 
            "  SELECT  A.USERID, A.ACNT, A.NM, NVL(A.PSWD, D.PSWD) AS PSWD, A.INSTID, A.INSTPIC_ROLE_CD, A.ACNT_LOCK_YN, A.ACNT_LOCK_CD, A.PRVC_VLDTY, DATE_FORMAT(D.REG_DT, '%Y-%m-%d') AS DRMNCY_REG_DT, A.PSWD_MDFCN_DT, A.LGN_FAIL_CNT, B.APRV_STTS_CD AS INST_APRV_STTS_CD, B.USE_YN AS INST_USE_YN, "
            + "NVL((SELECT USE_YN AS SITE_USE_YN FROM TB_CMM_SITE WHERE INSTID = A.INSTID AND SYS_KND_CD = 'T' AND USE_YN = 'Y' LIMIT 1), 'N') AS SITEAPLY_USE_YN, "
            + "NVL((SELECT 'Y' FROM TB_CMM_USER_ESYLGN WHERE USERID = A.USERID AND ESYLGN_CD = '106101' LIMIT 1), 'N') AS ONEPASS_LINK_YN, "
            + "(SELECT COUNT(USERID) FROM TB_CMM_USER_ESYLGN WHERE USERID = A.USERID) AS ESYLGN_LINK_CNT, "
            + "A.CI_PARNTS, "
            + "E.USERID AS EXPRT_USERID "
            + "FROM  TB_CMM_USER A LEFT OUTER JOIN TB_CMM_INST B ON A.INSTID = B.INSTID "
            + "      LEFT OUTER JOIN TB_CMM_USER_DRMNCY D ON A.USERID = D.USERID "
            + "      LEFT OUTER JOIN TB_ASS_EXPRT E ON A.USERID = E.USERID AND E.STTS_CD = '134103' "
            + "WHERE A.USERID = :userid "
            + "AND A.DEL_YN = 'N' ";
    
    /**
    * 사용자에게 부여된 역할을 확인하기 위한 쿼리
    */
	private final String DEF_GRANTED_AUTHORITY_QUERY = 
			"  SELECT B.ROLEID, C.NM, C.SE_CD, C.TRGT_INST_CD, C.TRGT_RGN_CD, D.ALLOWED_SITEIDS, C.KND_CD "
			+ "FROM TB_CMM_USER A, "
            + "     TB_CMM_ROLE_USER B, "
            + "     TB_CMM_ROLE C, "
            + "     ( "
            + "         SELECT "
            + "             ROLEID, " 
            + "             GROUP_CONCAT(SITEID) AS ALLOWED_SITEIDS "
            + "         FROM "
            + "         ( "
            + "             SELECT DISTINCT "
            + "                 R.ROLEID, "
            + "                 M.SITEID "
            + "             FROM  "
            + "                 TB_CMM_ROLE_MENU R INNER JOIN TB_CMM_MENU M ON R.MENUID = M.MENUID "
            + "         ) T "
            + "         GROUP BY ROLEID "
            + "     ) D "
            + "WHERE A.USERID = B.USERID "
            + "AND A.ACNT = :loginid "
            + "AND B.ROLE_STRT_DD <= NOW() "
            + "AND B.ROLE_END_DD >= NOW() "
            + "AND C.USE_YN = 'Y' "
            + "AND B.ROLEID = C.ROLEID "
	        + "AND C.ROLEID = D.ROLEID ";

    /**
    * url 형식인 보호자원 - Role 맵핑정보를 조회하는 default 쿼리
    */
	private final String DEF_ROLES_AND_URL_QUERY = 
	        " WITH CTE AS "
	        + "( "
	        + "    SELECT A.SITEID, B.DMN " 
	        + "    FROM TB_CMM_SITE A "
	        + "    INNER JOIN TB_CMM_SITE_DMN B "
	        + "    ON A.SITEID = B.SITEID "
	        + "    WHERE A.USE_YN ='Y' "
	        + ") "
	        + "SELECT DISTINCT URL, 0 AS AUTHORITY "
			+ "FROM ( "
			+ "SELECT "
			+ "CASE WHEN C.URL = '' THEN B.MENUID "
			+ "WHEN C.URL IS NOT NULL THEN CONCAT(CTE.DMN, C.URL) "
			+ "WHEN C.URL IS NULL THEN B.MENUID "
			+ "END AS URL "
			+ ", A.ROLEID AS AUTHORITY "
            + "FROM TB_CMM_MENU B "
            + "LEFT OUTER JOIN TB_CMM_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "INNER JOIN TB_CMM_ROLE_MENU A  "
            + "ON A.MENUID = B.MENUID "
            + "INNER JOIN CTE " 
            + "ON B.SITEID = CTE.SITEID "
            + "WHERE B.LOGIN_YN ='N' "
            + "AND B.SITEID IN (SELECT SITEID FROM TB_CMM_SITE WHERE USE_YN ='Y') "
            + ") T "
            + "UNION ALL "
            + "SELECT "
            + "CASE WHEN C.URL = '' THEN B.MENUID "
            + "WHEN C.URL IS NOT NULL THEN CONCAT(CTE.DMN, C.URL) "
            + "WHEN C.URL IS NULL THEN B.MENUID "
            + "END AS URL "
            + ", A.ROLEID AS AUTHORITY "
            + "FROM TB_CMM_MENU B LEFT OUTER JOIN TB_CMM_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "LEFT OUTER JOIN TB_CMM_ROLE_MENU A "
            + "ON A.MENUID = B.MENUID "
            + "INNER JOIN CTE " 
            + "ON B.SITEID = CTE.SITEID "
            + "WHERE B.SITEID IN (SELECT SITEID FROM TB_CMM_SITE WHERE USE_YN ='Y') "
            + "UNION ALL "
            + "SELECT B.URL, A.ROLEID AS AUTHORITY "
            + "FROM ( "
            + "SELECT ROLEID "
            + "FROM TB_CMM_ROLE "
            + "UNION ALL "
            + "SELECT 0 "
            + ") A "
            + "CROSS JOIN "
            + "( "
            + "SELECT CONCAT(CTE.DMN, S2.URL) AS URL "
            + "FROM CTE "
            + "CROSS JOIN "
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
            + "SELECT '/cmm/changeRole.do' AS URL "
            + "UNION ALL "
            + "SELECT '/example2/fileUpDown.html' AS URL"            
            + ") S2 "
            + ") B "
            + "ORDER BY URL ";

    /**
    * url 형식인 보호자원 - channel 맵핑정보를 조회하는 default 쿼리
    */
	private final String DEF_HTTPS_AND_URL_QUERY = 
	        " WITH CTE AS "
	        + "( "
	        + "    SELECT A.SITEID, B.DMN " 
	        + "    FROM TB_CMM_SITE A "
	        + "    INNER JOIN TB_CMM_SITE_DMN B "
	        + "    ON A.SITEID = B.SITEID "
	        + "    WHERE A.USE_YN ='Y' "
	        + ") "
	        + "SELECT DISTINCT URL, CHANNEL "
	        + "FROM "
	        + "( "
			+ "SELECT URL, "
			+ "       CASE HTTPS_USE_YN WHEN 'N' THEN 'REQUIRES_INSECURE_CHANNEL' "
			+ "                         WHEN 'Y' THEN 'REQUIRES_SECURE_CHANNEL' "
			+ "       END AS CHANNEL " // ANY_CHANNEL도 있음
            + "FROM ( "
            + "SELECT CONCAT(CTE.DMN, C.URL) AS URL, B.HTTPS_USE_YN "
            + "FROM TB_CMM_MENU B "
            + "LEFT OUTER JOIN TB_CMM_PRGRM C "
            + "ON B.PRGRMID = C.PRGRMID "
            + "AND C.URL IS NOT NULL "
            + "AND C.URL != '' "
            + "INNER JOIN CTE "
            + "ON B.SITEID = CTE.SITEID "
            + "WHERE B.SITEID IN (SELECT SITEID FROM TB_CMM_SITE WHERE USE_YN ='Y') "
            + ") T "
            + "WHERE URL IS NOT NULL "
            + "AND URL != '' "
            + "UNION ALL "
            + "SELECT CONCAT(CTE.DMN, S2.URL) AS URL, S2.CHANNEL "
            + "FROM CTE "
            + "CROSS JOIN "
            + "( "
            + "SELECT '/' AS URL, 'REQUIRES_SECURE_CHANNEL' AS CHANNEL "
            + "UNION ALL "
            + "SELECT '/login' AS URL, 'REQUIRES_SECURE_CHANNEL' AS CHANNEL "
            + ") S2 "
            + "ORDER BY URL "
	        + ") F ";

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
    * 디지털원패스 사용자 로그인 정보를 확인하기 위한 SQL 반환.
    *
    * @Title       : getDEF_USER_LOGIN_INFO_FOR_ONEPASS_QUERY 
    * @Description : 디지털원패스 사용자 로그인 정보를 확인하기 위한 SQL 반환.
    * @return String 디지털원패스 사용자 로그인 정보를 확인하기 위한 SQL 문자열 
    */
    @Override
    public String getDEF_USER_LOGIN_INFO_FOR_ONEPASS_QUERY() {
        return DEF_USER_LOGIN_INFO_FOR_ONEPASS_QUERY;
    }
    
    /**
    * SSO 사용자 로그인 정보를 확인하기 위한 SQL 반환.
    *
    * @Title       : getDEF_USER_LOGIN_INFO_FOR_SSO_QUERY 
    * @Description : SSO 사용자 로그인 정보를 확인하기 위한 SQL 반환.
    * @return String SSO 사용자 로그인 정보를 확인하기 위한 SQL 문자열 
    */
    @Override
    public String getDEF_USER_LOGIN_INFO_FOR_SSO_QUERY() {
        return DEF_USER_LOGIN_INFO_FOR_SSO_QUERY;
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
