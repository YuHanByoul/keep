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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.util.Base64Utils;

import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.CookieUtil;
import com.kbrainc.plum.rte.util.StringUtil;

import WiseAccess.SSO;

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
    
    @Value("${app.sso.isuse}")
    private boolean ssoIsUse;
    
    @Value("${sso.apikey}")
    private String ssoApikey;
    
    @Value("${sso.host}")
    private String ssoHost;
    
    @Value("${sso.port}")
    private int ssoPort;
    
    @Value("${sso.serverid}")
    private String ssoServerid;
    
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

        if (ssoIsUse) {
            String sToken = CookieUtil.getCookie(request, "ssotoken"); // 쿠키에 저장된 토큰을 받아 저장
            
            if ("".equals(StringUtil.nvl(sToken))) { // 토큰이 없으면
                SSO sso = new SSO(ssoApikey);
                sso.setHostName(ssoHost); // engine이 설치된 아이피
                sso.setPortNumber(ssoPort); // engine이 사용하고 있는 포트넘버
    
                // SSO 세션 생성
                String clientIp = CommonUtil.getClientIp(request);
                int nResult = sso.regUserSession(((UserVo) session.getAttribute("user")).getUserid(), clientIp, true);
                sso.putValue("loginUserType" , (String) request.getAttribute("loginUserType")); // 토큰에 삽입할 값을 넣어줌
                String ssoToken = sso.makeToken(3, sso.getToken(), ssoServerid, clientIp); // 세션토큰과 합께 추가된(putValue)값을 토큰으로 생성 후 리턴
                nResult = sso.getLastError(); // 가장 마지막에 발생한 에러값을 저장
                
                // SSO 세션 생성 성공
                if (nResult >= 0) {
                    // 인증 세션을 가져와 새로운 쿠키 생성
                    CookieUtil.setCookie(request, response, "JSESSIONID", request.getSession().getId(), serverCookieDomain, "/");
                    CookieUtil.setCookie(request, response, "ssotoken", ssoToken, serverCookieDomain, "/");
                } else {         // 세션 생성 실패
                    session.setAttribute("user", null);
                    CookieUtil.setCookie(request, response, "ssotoken", "", serverCookieDomain, "/");
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(String.format("<script>alert('사용자 인증 오류입니다[오류코드 %s].\\n로그인 화면으로 이동합니다.');history.back();</script>", nResult));
                    return;
                }
            } else { // 토큰이 있으면
                CookieUtil.setCookie(request, response, "JSESSIONID", request.getSession().getId(), serverCookieDomain, "/");
            }
        } else {
            CookieUtil.setCookie(request, response, "JSESSIONID", request.getSession().getId(), serverCookieDomain, "/");
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
