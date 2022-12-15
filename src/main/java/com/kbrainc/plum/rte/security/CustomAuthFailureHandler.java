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
        String defaultFailureUrl = "/?error=true";
        String message = (String)request.getAttribute("message");
        
        if (!"".equals(StringUtil.nvl(message))) {
            defaultFailureUrl += "&msg=" + Base64Utils.encodeToUrlSafeString(message.getBytes());
        }
        setDefaultFailureUrl(defaultFailureUrl);
 
        super.onAuthenticationFailure(request, response, exception);
    }
}
