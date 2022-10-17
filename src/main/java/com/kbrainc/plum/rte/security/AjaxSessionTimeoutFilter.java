package com.kbrainc.plum.rte.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

/**
 * 
 * ajax호출시 sessionTimeout등에 적절한 응답코드를 리턴한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - AjaxSessionTimeoutFilter.java
 * </pre> 
 *
 * @ClassName : AjaxSessionTimeoutFilter
 * @Description : ajax호출시 sessionTimeout등에 적절한 응답코드를 리턴한다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class AjaxSessionTimeoutFilter implements Filter {

    private final String ajaxHeader = "AJAX";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (isAjaxRequest(req)) {
            try {
                if(!req.isRequestedSessionIdValid()) {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED);                 
                    return;
                }
                chain.doFilter(req, res);
            } catch (AccessDeniedException e) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            } catch (AuthenticationException e) {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {
//        init
    }

    public void destroy() {
//        destroy
    }

    /**
     * @Title : isAjaxRequest
     * @Description : ajax호출여부를 반환한다.
     * @param req :
     * @return boolean ajax호출여부
     */
    private boolean isAjaxRequest(HttpServletRequest req) {
        return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
    }
}
