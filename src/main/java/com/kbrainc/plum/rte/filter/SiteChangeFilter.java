package com.kbrainc.plum.rte.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kbrainc.plum.rte.exception.PageNotFoundException;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResSiteService;

/**
* 사이트 변경시 역할을 변경해주는 필터.
*
* <pre>
* com.kbrainc.plum.rte.filter
* - SiteChangeFilter.java
* </pre>
*
* @ClassName   : SiteChangeFilter 
* @Description : 사이트 변경시 역할을 변경해준다.
* @author      : KBRAINC
* @date        : 2022. 9. 23.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
public class SiteChangeFilter implements Filter {

    private ResSiteService resSiteService;

    public SiteChangeFilter(ResSiteService resSiteService) {
        this.resSiteService = resSiteService;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        if(!(servletPath.equals("/") || servletPath.endsWith(".html"))) {
            chain.doFilter(req, res);
        } else {
        
            try {
                HttpSession session = req.getSession();
                SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
                String sysSeCd = (siteInfo != null) ? siteInfo.getSys_se_cd() : null;
                String dmn = request.getServerName();
                
                if (siteInfo != null && !dmn.equals(siteInfo.getDmn())) {
                    siteInfo = resSiteService.getSiteInfo(dmn);
                    
                    UserVo user = (UserVo) session.getAttribute("user");
                    if (user != null) { // 로그인 되어있는 상태에서 도메인 변경시
                        // 시스템_구분_코드가 다를때
                        if (!sysSeCd.equals(siteInfo.getSys_se_cd())) {
                            // 역할을 변경한다.
                            for (Map<String, String> authority : user.getAuthorities()) {
                                if (("A".equals(siteInfo.getSys_se_cd()) && "A".equals(authority.get("se_cd"))) || ("U".equals(siteInfo.getSys_se_cd()) && "U".equals(authority.get("se_cd")))) {
                                    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                                    authorities.add(new SimpleGrantedAuthority(authority.get("roleid")));
                                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
                                    break;
                                }
                            }
                        }
                    }
                    
                    sysSeCd = siteInfo.getSys_se_cd();
                }
                
            } catch (Exception e) {
                
            }
    
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
