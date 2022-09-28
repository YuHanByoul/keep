package com.kbrainc.plum.rte.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kbrainc.plum.rte.exception.PageNotFoundException;
import com.kbrainc.plum.rte.menu.MenuItem;
import com.kbrainc.plum.rte.menu.MenuTree;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.service.ResMenuService;
import com.kbrainc.plum.rte.service.ResMenuServiceImpl;
import com.kbrainc.plum.rte.service.ResSiteService;
import com.kbrainc.plum.rte.util.StringUtil;


@Component
@Qualifier(value = "httpInterceptor")
public class HttpInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @Autowired
    ResSiteService resSiteService;
    @Autowired
    ResMenuService resMenuService;

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

            String menu_url = servletPath;

            MenuItem menuItem = null;
            MenuItem menuItem2 = null;

            if (!"".equals(StringUtil.nvl(request.getQueryString()))) {
                menu_url += "?" + request.getQueryString();
            }

            String menu_id = "";
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
                    if (!menu_url.equals(menuItem.getUrl()) && !servletPath.equals(menuItem.getUrl())) {
                        List<MenuItem> itemList = menuTree.getChildrenMenuItemByMenuID(menuKey); // 메뉴의 하위메뉴리스트
                        if (itemList == null || itemList.size() == 0) {
                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                        }
                        if (itemList != null && itemList.size() > 0) {
                            menuItem2 = this.getMenuItm(itemList, menu_url);
                            if (menuItem2 == null) {
                                menuItem2 = this.getMenuItm(itemList, servletPath); // 역순으로 메뉴 찾는 함수
                            }
                            if (menuItem2 != null) {
                                if ("05".equals(menuItem2.getPtype_cd())) {
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
                                if (!menu_url.equals(menuItem.getUrl())) {
                                    itemList = menuTree.getChildrenMenuItemByMenuID(menuItem.getMenuid()); // p메뉴의 하위 메뉴리스트
                                    
                                    if (itemList == null || itemList.size() == 0) {
                                        itemList = menuTree.getMenuItemByURL(urlkey, menu_url);
                                        if (itemList == null || itemList.size() == 0) {
                                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                                        }
                                    }
                                    menuItem2 = this.getMenuItm(itemList, menu_url); // 역순으로 메뉴 찾는 함수
                                    if (menuItem2 == null) {
                                        menuItem2 = this.getMenuItm(itemList, servletPath); // 역순으로 메뉴 찾는 함수
                                    }
                                    if (menuItem2 != null) {
                                        if ("05".equals(menuItem2.getPtype_cd())) {
                                            menuItem = menuItem2;
                                        }
                                    }
                                }
                            }
                        }
                    } else { // 2.2.2
                        List<MenuItem> itemList = menuTree.getMenuItemByURL(urlkey, menu_url);
                        if (itemList == null || itemList.size() == 0) {
                            itemList = menuTree.getMenuItemByURL(urlkey, servletPath);
                        }
                        if (itemList != null && itemList.size() > 0) {
                            menuItem = this.getMenuItm(itemList, menu_url); // 역순으로 메뉴 찾는 함수
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
            
            if(siteInfo.equals(null)){
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
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return menuItem;
    }
}
