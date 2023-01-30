package com.kbrainc.plum.rte.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.Base64Utils;

import com.kbrainc.plum.rte.util.StringUtil;

/**
* 로그인 실패URL과 실패메시지를 설정한다.
*
* <pre>
* com.kbrainc.plum.rte.security
* - CustomAuthFailureHandler.java
* </pre>
*
* @ClassName : CustomAuthFailureHandler
* @Description : 로그인 실패URL과 실패메시지를 설정한다.
* @author : KBRAINC
* @date : 2022. 12. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        StringBuffer defaultFailureUrl = new StringBuffer("/?error=true&id=");
        String message = (String)request.getAttribute("message");
        String loginUserType = (String)request.getAttribute("loginUserType");
        String loginType = (String)request.getAttribute("loginType");
        String returnUrl = (String)request.getAttribute("returnUrl");
        
        defaultFailureUrl.append(request.getAttribute("loginid"));
        
        if (loginUserType != null) {
            defaultFailureUrl.append("&userType=").append(loginUserType);
        }
        
        if (loginType != null) {
            defaultFailureUrl.append("&loginType=").append(loginType);
        }
        
        if (returnUrl != null) {
            defaultFailureUrl.append("&returnUrl=").append(returnUrl);
        }
                
        if (!"".equals(StringUtil.nvl(message))) {
            defaultFailureUrl.append("&msg=").append(Base64Utils.encodeToUrlSafeString(message.getBytes()));
        }

        setDefaultFailureUrl(defaultFailureUrl.toString());
 
        super.onAuthenticationFailure(request, response, exception);
    }
}
