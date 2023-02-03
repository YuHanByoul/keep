package com.kbrainc.plum.rte.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.util.Base64Utils;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.util.StringUtil;

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
        
        HttpSession session = request.getSession();
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        boolean isPswdChangeLayer = request.getAttribute("pswdChangeLayer") == null ? false : true;
        boolean isAlertMsg = request.getAttribute("alertMessage") == null ? false : true;
        String alertMessage = (String)request.getAttribute("alertMessage");
        StringBuffer alertMessageBuffer = new StringBuffer("");
        
        if (isAlertMsg) {
            if (isPswdChangeLayer) {
                alertMessageBuffer.append("&");
            } else {
                alertMessageBuffer.append("?");
            }
            alertMessageBuffer.append("alertMsg=").append(Base64Utils.encodeToUrlSafeString(alertMessage.getBytes()));
        }
        String returnUrl = request.getParameter("returnUrl") != null ? (String) request.getParameter("returnUrl").split("::")[0] : ""; // 관리자에서는 사용하지않으며, 디지털원패스때문에 split하여 사용
        if (returnUrl == null || "".equals(returnUrl)) { 
            super.setDefaultTargetUrl(securityProperties.getDEFAULT_TARGET_URL() + (isPswdChangeLayer ? "?pwd" : "") + alertMessageBuffer.toString());
        } else {
            super.setDefaultTargetUrl(returnUrl + (isPswdChangeLayer ? "?pwd" : "") + alertMessageBuffer.toString());
        }
        
        if (savedRequest != null) {
            if (isPswdChangeLayer || isAlertMsg) {
                String queryString = savedRequest.getQueryString();
                String redirectUrl = savedRequest.getRedirectUrl();
                if (redirectUrl.endsWith("?")) {
                    redirectUrl = redirectUrl.substring(0, redirectUrl.length() - 1);
                }
                session.removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
                super.setDefaultTargetUrl(redirectUrl + (isPswdChangeLayer ? "?pwd" : "") + alertMessageBuffer.toString() + ("".equals(queryString) ? "" : "&" + queryString));
            }
        }

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
