package com.kbrainc.plum.rte.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.util.Base64Utils;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.CookieUtil;
import com.kbrainc.plum.rte.util.StringUtil;

import WiseAccess.SSO;

/**
 * 
 * 로그아웃을 처리한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - CustomLogoutSuccessHandler.java
 * </pre> 
 *
 * @ClassName : CustomLoginSuccessHandler
 * @Description : 로그아웃을 처리한다.
 * @author : KBRAINC
 * @date : 2023. 3. 14.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${app.sso.isuse}")
    private boolean ssoIsUse;
    
    @Value("${sso.apikey}")
    private String ssoApikey;
    
    @Value("${sso.host}")
    private String ssoHost;
    
    @Value("${sso.port}")
    private int ssoPort;
    
    @Value("${server.servlet.session.cookie.domain}")
    private String serverCookieDomain;
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        
        if (ssoIsUse) {
            CookieUtil.setCookie(request, response, "ssotoken", "", serverCookieDomain, "/");
            String sToken = CookieUtil.getCookie(request, "ssotoken"); // 쿠키에 저장된 토큰을 받아 저장
            
            if (!"".equals(StringUtil.nvl(sToken))) { // 토큰이 있으면
                SSO sso = new SSO(ssoApikey);
                sso.setHostName(ssoHost); // engine이 설치된 아이피
                sso.setPortNumber(ssoPort); // engine이 사용하고 있는 포트넘버
                // SSO 토큰 파괴
                sso.unregUserSession(sToken);
            }
        }
        
        response.sendRedirect("/");
    }
}
