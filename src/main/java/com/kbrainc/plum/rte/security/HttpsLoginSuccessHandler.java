package com.kbrainc.plum.rte.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.kbrainc.plum.config.security.properties.SecurityProperties;

/**
 * 
 * 로그인 성공시 https에서 http로의 라우팅과 인증세션공유를 처리한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - HttpsLoginSuccessHandler.java
 * </pre> 
 *
 * @ClassName : HttpsLoginSuccessHandler
 * @Description : 로그인 성공시 https에서 http로의 라우팅과 인증세션공유를 처리한다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class HttpsLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${server.port}")
    private String serverHttpsPort;
    
    @Value("${server.servlet.session.cookie.domain}")
    private String serverCookieDomain;
    
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        // 인증 성공 후 HTTP 영역으로 이동
        String serverHttpPortStr = "";
        if (!"443".equals(serverHttpsPort)) {
            serverHttpPortStr = ":" + serverHttpsPort;
        }
        /*StringBuffer defaultTargetUrl = new StringBuffer();
        defaultTargetUrl.append("https://").append(request.getServerName()).append(serverHttpPortStr).append(securityProperties.getDEFAULT_TARGET_URL());
        super.setDefaultTargetUrl(defaultTargetUrl.toString());*/
        super.setDefaultTargetUrl(securityProperties.getDEFAULT_TARGET_URL());

        // 인증 세션을 가져와 새로운 쿠키 생성
        Cookie k = new Cookie("JSESSIONID", request.getSession().getId());
        k.setDomain(serverCookieDomain);
        k.setPath("/");
        k.setHttpOnly(true);
        if (request.isSecure()) {
            k.setSecure(true);
        }
        response.addCookie(k);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
