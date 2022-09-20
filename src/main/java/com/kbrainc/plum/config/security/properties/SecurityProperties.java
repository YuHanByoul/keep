package com.kbrainc.plum.config.security.properties;

public interface SecurityProperties {
	
	/**
    * 디폴트타겟URL 반환.
    *
    * @Title       : getDEFAULT_TARGET_URL 
    * @Description : 디폴트타겟URL 반환.
    * @return String 디폴트타겟URL
    */
    public String getDEFAULT_TARGET_URL();
    
	/**
	* 사용자 로그인 정보를 확인하기 위한 SQL 반환.
	*
	* @Title       : getDEF_USER_LOGIN_INFO_QUERY 
	* @Description : 사용자 로그인 정보를 확인하기 위한 SQL 반환.
	* @return String 사용자 로그인 정보를 확인하기 위한 SQL 문자열 
	*/
	public String getDEF_USER_LOGIN_INFO_QUERY();
	
    /**
    * 사용자에게 부여된 역할을 확인하기 위한 SQL 반환.
    *
    * @Title       : getDEF_GRANTED_AUTHORITY_QUERY 
    * @Description : 사용자에게 부여된 역할을 확인하기 위한 SQL 반환.
    * @return String 사용자에게 부여된 역할을 확인하기 위한 SQL 문자열
    */
    public String getDEF_GRANTED_AUTHORITY_QUERY();
    
    /**
    * url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 반환.
    *
    * @Title       : getDEF_ROLES_AND_URL_QUERY 
    * @Description : url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 반환.
    * @return String url 형식인 보호자원 - Role 맵핑정보를 조회하는 SQL 문자열.
    */
    public String getDEF_ROLES_AND_URL_QUERY();

    /**
    * url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 반환.
    *
    * @Title       : getDEF_HTTPS_AND_URL_QUERY 
    * @Description : url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 반환.
    * @return String url 형식인 보호자원 - channel 맵핑정보를 조회하는 default SQL 문자열.
    */
    public String getDEF_HTTPS_AND_URL_QUERY();
}
