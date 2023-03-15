package com.kbrainc.plum.rte.mvc.interceptor;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kbrainc.plum.rte.exception.PageNotFoundException;
import com.kbrainc.plum.rte.menu.MenuItem;
import com.kbrainc.plum.rte.menu.MenuTree;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.security.SSOUseridLoginUserTypeAuthenticationToken;
import com.kbrainc.plum.rte.service.ResMenuService;
import com.kbrainc.plum.rte.service.ResMenuServiceImpl;
import com.kbrainc.plum.rte.service.ResSiteService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.CookieUtil;
import com.kbrainc.plum.rte.util.StringUtil;

import WiseAccess.SSO;


@Component
@Qualifier(value = "httpInterceptor")
public class HttpInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ResSiteService resSiteService;
    
    @Autowired
    ResMenuService resMenuService;
    
    @Autowired
    LocaleResolver localeResolver;
        
    @Value("${server.servlet.session.cookie.domain}")
    private String serverCookieDomain;
    
    @Value("${app.sso.isuse}")
    private boolean ssoIsUse;
    
    @Value("${sso.apikey}")
    private String ssoApikey;
    
    @Value("${sso.host}")
    private String ssoHost;
    
    @Value("${sso.port}")
    private int ssoPort;
    
    /** 다국어를 적용할 포털의 URL */
    String[] localeAllowedPageUrls = {"/main.html"};
    Set<String> localeAllowedPageUrlSet = new HashSet<String>(Arrays.asList(localeAllowedPageUrls));


    /**.
     * @Title : preHandle
     * @Description : 기본 전처리 메서드
     * @param request  요청객체
     * @param response 응답객체
     * @param handler  핸들러정보
     * @throws Exception :
     * @return boolean 전처리 성공여부
     */
    @SuppressWarnings("unused")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String targetURI = request.getRequestURI();
        HttpSession session = request.getSession();
        
        ////////////////////////////////////////////////////////////////////////////////////
        // 접속사이트정보
        ////////////////////////////////////////////////////////////////////////////////////
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        String dmn = request.getServerName();
        
        if (siteInfo == null || !dmn.equals(siteInfo.getDmn())) {
            siteInfo = resSiteService.getSiteInfo(dmn);
            
            if (siteInfo == null) {
                session.removeAttribute("site");
                logger.debug("########## 존재하지않는 사이트입니다. ##########");
                // 존재하지않는 사이트입니다.
                throw new PageNotFoundException("존재하지않는 사이트입니다.");
            }
            session.setAttribute("site", siteInfo);
        }
        String sysSeCd = siteInfo.getSysSeCd();
        
        ////////////////////////////////////////////////////////////////////////////////////
        // SSO토큰 검사
        ////////////////////////////////////////////////////////////////////////////////////
        if (ssoIsUse) {
            String sToken = CookieUtil.getCookie(request, "ssotoken"); // 쿠키에 저장된 토큰을 받아 저장
            
            if (!"".equals(StringUtil.nvl(sToken))) { // 토큰이 있으면
                SSO sso = new SSO(ssoApikey);
                sso.setHostName(ssoHost); // engine이 설치된 아이피
                sso.setPortNumber(ssoPort); // engine이 사용하고 있는 포트넘버
                int nResult = sso.verifyToken(sToken); //토큰검증 (검증 오류시 음수값 반환)
                
                if (nResult >= 0) { // 올바른 토큰이라면
                    String userid = sso.getValueUserID(); // 사용자 아이디값을 얻는다.
                    String loginUserType = sso.getValue("loginUserType"); // 토큰에 putValue로 추가한 정보를 얻으려면 삽입할때 Key값을 파라미터로 getValue() 사용
                    UserVo user = (UserVo) session.getAttribute("user");
                    
                    if (user == null) {
                        try {
                            SecurityContextHolder.clearContext();
                            ssoLogin(request, response, userid, loginUserType);
                            return false;
                        } catch (AuthenticationException e) {
                            session.setAttribute("user", null);
                            SecurityContextHolder.clearContext();
                            CookieUtil.setCookie(request, response, "ssotoken", "", serverCookieDomain, "/");
                            response.setContentType("text/html;charset=UTF-8");
                            PrintWriter writer = response.getWriter();
                            writer.print(String.format("<script>alert('사용자 인증 오류입니다.\\n%s 화면으로 이동합니다.');location.href='/main.html';</script>", sysSeCd.equals("U") ? "메인" : "로그인"));
                            return false;
                        }
                    } else {
                       if (!userid.equals(user.getUserid())) { // 토큰과 세션사용자가 다를때
                           session.setAttribute("user", null);
                           SecurityContextHolder.clearContext();
                           CookieUtil.setCookie(request, response, "ssotoken", "", serverCookieDomain, "/");
                           response.setContentType("text/html;charset=UTF-8");
                           PrintWriter writer = response.getWriter();
                           writer.print(String.format("<script>alert('사용자 인증 오류입니다.\\n%s 화면으로 이동합니다.');location.href='/main.html';</script>", sysSeCd.equals("U") ? "메인" : "로그인"));
                           return false;
                       }
                    }
                } else {
                    // 토큰검증 실패 시 처리
                    session.setAttribute("user", null);
                    SecurityContextHolder.clearContext();
                    CookieUtil.setCookie(request, response, "ssotoken", "", serverCookieDomain, "/");
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    
                    if (nResult == -9404) {
                        writer.print(String.format("<script>alert('중복로그인 되었습니다.\\n%s 화면으로 이동합니다.');location.href='/main.html';</script>", sysSeCd.equals("U") ? "메인" : "로그인"));
                    } else {
                        writer.print(String.format("<script>alert('사용자 인증 오류입니다[오류코드 %s].\\n%s 화면으로 이동합니다.');location.href='/main.html';</script>", nResult, sysSeCd.equals("U") ? "메인" : "로그인"));
                    }
                    return false;
                }
            } else {
                // 토큰이 없을때 처리
                UserVo user = (UserVo) session.getAttribute("user");
                if (user != null) {
                    session.setAttribute("user", null);
                    SecurityContextHolder.clearContext();
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(String.format("<script>alert('사용자 인증 오류입니다.\\n%s 화면으로 이동합니다.');location.href='/main.html';</script>", sysSeCd.equals("U") ? "메인" : "로그인"));
                    return false;
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////
        // locale 초기화
        ////////////////////////////////////////////////////////////////////////////////////
        if ("P".equals(siteInfo.getSysKndCd()) && targetURI.endsWith(".html")) { // 포탈인 경우(화면호출시) 
            if (!localeAllowedPageUrlSet.contains(targetURI)) {
                localeResolver.setLocale(request, response, null);
            }
        } else if(!"P".equals(siteInfo.getSysKndCd())) { // 포탈이 아니면 locale 사용하지 않음.
            localeResolver.setLocale(request, response, null);
        }
        
        ////////////////////////////////////////////////////////////////////////////////////
        // 메뉴정보
        ////////////////////////////////////////////////////////////////////////////////////
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            MenuTree menuTree = null;

            // 요청되는 url 정보로 강의실 메뉴 여부 판단
            String servletPath = request.getServletPath();

            menuTree = resMenuService.getMenuTree(siteInfo.getSiteid());

            if (menuTree == null) {
                logger.debug("########## 사이트의 메뉴정보가 없습니다. ##########");
                throw new PageNotFoundException("사이트의 메뉴정보가 없습니다.");
            }

            String menuUrl = servletPath;

            MenuItem menuItem = null;
            MenuItem menuItem2 = null;

            if (!"".equals(StringUtil.nvl(request.getQueryString()))) {
                menuUrl += "?" + request.getQueryString();
            }

            String menuId = "";
            String urlkey = siteInfo.getSiteid();

            /**
             * 접근 메뉴정보 체크 로직 mkey : 현재 화면 메뉴키 cmkey : 이동할 메뉴 키 menu_url : 파라메터가 포함된 url
             * servletPath : 파라메터 제외된 url purl : 부모의 url
             * 
             * 1. cmkey 가 있을경우 - cmkey 의 메뉴정보를 확인
             * 
             * 2. cmkey 가 없을경우 - 2.1 : mkey가 있을경우 - mkey 하위의 url 을 검사하여 체크 , 이동후 mkey는 cmkey
             * 세팅 - 2.2 : mkey가 없을경우 - 2.2.1 : purl 이 있을경우 - purl을 전체 트리에서 검색하여 mkey로 세팅후
             * 하위에서 url을 검색 - 2.2.2 : purl 이 없을경우 - 전체트리에서 url을 검색하고 1개일경우 키세팅, 많을 경우 키세팅 안함
             * 
             * 예외) mkey, cmkey 있을경우 - 1번으로 실행
             * 
             */
            String menuKey = (String) request.getParameter("mkey");
            String currentMenuKey = (String) request.getParameter("cmkey");
            String parentUrl = (String) request.getParameter("purl");
            if (!"".equals(StringUtil.nvl(currentMenuKey, ""))) { // 1
                menuItem = menuTree.getMenuItemByMenuID(currentMenuKey);
                if (menuItem != null) {
                    menuKey = menuItem.getMenuid();
                }
            } else { // 2
                if (!"".equals(StringUtil.nvl(menuKey, ""))) { // 2.1
                    menuItem = menuTree.getMenuItemByMenuID(menuKey);
                    if (!menuUrl.equals(menuItem.getUrl()) && !servletPath.equals(menuItem.getUrl())) {
                        List<MenuItem> itemList = menuTree.getChildrenMenuItemByMenuID(menuKey); // 메뉴의 하위메뉴리스트
                        if (itemList.isEmpty() || itemList.size() == 0) {
                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                        }
                        if (itemList != null && itemList.size() > 0) {
                            menuItem2 = this.getMenuItm(itemList, menuUrl);
                            if (menuItem2 == null) {
                                menuItem2 = this.getMenuItm(itemList, servletPath); // 역순으로 메뉴 찾는 함수
                            }
                            if (menuItem2 != null) {
                                if ("05".equals(menuItem2.getPtypeCd())) {
                                    menuItem = menuItem2;
                                }
                            }
                        }
                    }
                } else { // 2.2
                    if (!"".equals(StringUtil.nvl(parentUrl, ""))) { // 2.2.1
                        List<MenuItem> itemList = menuTree.getMenuItemByURL(urlkey, parentUrl);
                        if (itemList != null && itemList.size() > 0) {
                            // 역순으로 url에 해당하는 메뉴를 찾는다.
                            menuItem = this.getMenuItm(itemList, parentUrl); // 역순으로 메뉴 찾는 함수
                            if (menuItem != null) {
                                if (!menuUrl.equals(menuItem.getUrl())) {
                                    itemList = menuTree.getChildrenMenuItemByMenuID(menuItem.getMenuid()); // p메뉴의 하위 메뉴리스트
                                    
                                    if (itemList.isEmpty() || itemList.size() == 0) {
                                        itemList = menuTree.getMenuItemByURL(urlkey, menuUrl);
                                        if (itemList == null || itemList.size() == 0) {
                                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                                        }
                                    }
                                    menuItem2 = this.getMenuItm(itemList, menuUrl); // 역순으로 메뉴 찾는 함수
                                    if (menuItem2 == null) {
                                        menuItem2 = this.getMenuItm(itemList, servletPath); // 역순으로 메뉴 찾는 함수
                                    }
                                    if (menuItem2 != null) {
                                        if ("05".equals(menuItem2.getPtypeCd())) {
                                            menuItem = menuItem2;
                                        }
                                    }
                                }
                            }
                        }
                    } else { // 2.2.2
                        List<MenuItem> itemList = menuTree.getMenuItemByURL(urlkey, menuUrl);
                        if (itemList == null || itemList.size() == 0) {
                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                        }
                        if (itemList != null && itemList.size() > 0) {
                            menuItem = this.getMenuItm(itemList, menuUrl); // 역순으로 메뉴 찾는 함수
                            if (menuItem == null) {
                                menuItem = this.getMenuItm(itemList, servletPath); // 역순으로 메뉴 찾는 함수
                            }
                        }
                    }
                }
            }
            if (menuItem == null) {
                request.setAttribute("mkey", "");
                request.setAttribute(ResMenuServiceImpl.MENU_ITEM, new MenuItem());
                request.setAttribute(ResMenuServiceImpl.MENU_NAME, "");
            } else {
                request.setAttribute("mkey", menuKey);
                request.setAttribute(ResMenuServiceImpl.MENU_ITEM, menuItem);
                request.setAttribute(ResMenuServiceImpl.MENU_NAME, menuItem.getNm());
            }
            
            if(siteInfo == null){
            	request.setAttribute("popupYn", "N");
            }else {
            	request.setAttribute("popupYn", "Y");
            	request.setAttribute("menuid", menuItem != null ? menuItem.getMenuid() : null);
            	request.setAttribute("siteid", siteInfo.getSiteid());
            }
            
             
            // request.setAttribute(ResMenuServiceImpl.MENU_POPNOTI_ID,
            // StringUtil.nvl(menuItem.getPopnotiid())); // 콤마(,)로 구분된 popnoti_id 값
        }
        return true;
    }

    /**.
     * @Title : getMenuItm
     * @Description : 메뉴아이템 목록에서 역순으로 탐색하여 url과 일치하는 메뉴아이템 1개를 찾는 즉시 리턴한다.
     * @param list 메뉴아이템 목록
     * @param url  url
     * @return MenuItem 메뉴아이템
     */
    private MenuItem getMenuItm(List<MenuItem> list, String url) {
        MenuItem menuItem = null;
        // 역순으로 url에 해당하는 메뉴를 찾는다.
        for (int i = list.size() - 1; i >= 0; i--) {
            try {
                if (url.equals(list.get(i).getUrl())) {
                    menuItem = list.get(i);
                    return menuItem;
                }
            } catch (NullPointerException e) {
                logger.error("getMenuItm.NullPointerException.228L");
            } catch (Exception e) {
                logger.error("getMenuItm.Exception.230L");
            }
        }
        return menuItem;
    }
    
    /**
    * SSO 로그인 처리(세션생성).
    *
    * @Title : ssoLogin
    * @Description : SSO 로그인 처리(세션생성)
    * @param request 요청객체
    * @param response 응답객체
    * @param userid 사용자아이디
    * @param loginUserType 포털로그인사용자타입(개인회원:P, 기관회원:I)
    * @return void 리턴값 없음
    * @throws Exception 예외
    */
    public void ssoLogin(HttpServletRequest request, HttpServletResponse response, String userid, String loginUserType) throws Exception {
        SSOUseridLoginUserTypeAuthenticationToken authReq = new SSOUseridLoginUserTypeAuthenticationToken(userid, loginUserType);
        AuthenticationManager authenticaltionManager = ((AuthenticationManager)CommonUtil.getBean("authenticationManagerBean"));
        Authentication auth = authenticaltionManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        SavedRequestAwareAuthenticationSuccessHandler successHandler = ((SavedRequestAwareAuthenticationSuccessHandler)CommonUtil.getBean("httpsLoginSuccessHandler"));
        successHandler.onAuthenticationSuccess(request, response, auth);
    }
}
