package com.kbrainc.plum.rte.filter;

import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.kbrainc.plum.rte.configuration.ConfigurationFactory;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResSiteService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class SiteChangeFilter implements Filter {

    private static org.apache.commons.configuration.Configuration applicationConfig = ConfigurationFactory.getInstance().getApplicationConfig();

    private static String sysCompanyRoleid = applicationConfig.getString("system.company.roleid");
    
    private final ResSiteService resSiteService;

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
                //String sysSeCd = (siteInfo != null) ? siteInfo.getSysSeCd() : null;
                //String sysKndCd = (siteInfo != null) ? siteInfo.getSysKndCd() : null;
                String dmn = request.getServerName();
                
                if (siteInfo != null && !dmn.equals(siteInfo.getDmn())) {
                    siteInfo = resSiteService.getSiteInfo(dmn);
                    session.setAttribute("site", siteInfo);
                    
                    UserVo user = (UserVo) session.getAttribute("user");
                    if (user != null) { // 로그인 되어있는 상태에서 도메인 변경시
                        // 역할을 변경한다.
                        for (Map<String, String> authority : user.getAuthorities()) {
                            
                            if ("P".equals(siteInfo.getSysKndCd()) && "I".equals(user.getLoginUserType())) { // 사용자포털사이트 & 기관회원역할
                                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                                authorities.add(new SimpleGrantedAuthority(sysCompanyRoleid));
                                authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여
                                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
                                RoleInfoVo roleInfo = new RoleInfoVo(sysCompanyRoleid, "기관회원", "O", "U", null, null);
                                user.setRoleInfo(roleInfo);
                                break;
                            } else if ("U".equals(siteInfo.getSysSeCd()) && "U".equals(authority.get("se_cd"))) { // 사용자사이트 & 사용자역할
                                if (!sysCompanyRoleid.equals(authority.get("roleid"))) {
                                    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                                    authorities.add(new SimpleGrantedAuthority(authority.get("roleid")));
                                    authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여
                                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
                                    RoleInfoVo roleInfo = new RoleInfoVo((String)authority.get("roleid"), (String)authority.get("nm"), (String)authority.get("knd_cd"), (String)authority.get("se_cd"), null, null);
                                    user.setRoleInfo(roleInfo);
                                    break;
                                }
                            } else if ("A".equals(siteInfo.getSysSeCd()) && "A".equals(authority.get("se_cd")) && Arrays.asList(authority.get("allowed_siteids").split(",")).contains(siteInfo.getSiteid())) { // 관리자사이트 & 관리자역할
                                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                                authorities.add(new SimpleGrantedAuthority(authority.get("roleid")));
                                authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여
                                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
                                RoleInfoVo roleInfo = new RoleInfoVo((String)authority.get("roleid"), (String)authority.get("nm"), (String)authority.get("knd_cd"), (String)authority.get("se_cd"), (String)authority.get("trgt_inst_cd"), (String)authority.get("trgt_rgn_cd"));
                                user.setRoleInfo(roleInfo);
                                break;
                            }
                        }

                    }
                    
                    //sysSeCd = siteInfo.getSysSeCd();
                }
    
                
            } catch (SQLException e) {
            	log.debug("Exception:"+e);
            } catch (Exception e) {
                log.debug("Exception:"+e);
            }
    
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig filterConfig) {
//        init
    }

    public void destroy() {
//        destroy
    }
}
