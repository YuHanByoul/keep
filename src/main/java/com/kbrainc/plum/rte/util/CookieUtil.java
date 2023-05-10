package com.kbrainc.plum.rte.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 
* 쿠키Util 클래스.
*
* <pre>
* com.kbrainc.plum.rte.util
* - CookieUtil.java
* </pre> 
*
* @ClassName : CookieUtil
* @Description : 쿠키Util 클래스
* @author : KBRAINC
* @date : 2023. 3. 14.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
public class CookieUtil {

    /**
    * 쿠키를 추가한다.
    *
    * @Title : setCookie
    * @Description : 쿠키를 추가한다.
    * @param request 요청객체
    * @param response 응답객체
    * @param sName 쿠키 이름
    * @param sValue 쿠키 값
    * @param domain 도메인
    * @param path 경로
    * @return void
    */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String sName, String sValue, String domain, String path) {
        Cookie c = new Cookie(sName, sValue);
        c.setDomain(domain);
        c.setPath(path);
        c.setHttpOnly(true);
        if (request.isSecure()) {
            c.setSecure(true);
        }
        response.addCookie(c);
    }
    
    /**
    * sName 에 해당되는 쿠키 값을 얻는다.
    *
    * @Title : getCookie
    * @Description : sName 에 해당되는 쿠키 값을 얻는다.
    * @param request 요청객체
    * @param sName 쿠키 이름
    * @return String 쿠키 값
    */
    public static String getCookie (HttpServletRequest request, String sName)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i=0; i < cookies.length; i++){
                String name = cookies[i].getName();
                if (name != null && name.equals(sName)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;    
    }
}