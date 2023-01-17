package com.kbrainc.plum.cmm.layout.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kbrainc.plum.rte.lib.tree.TreeNode;
import com.kbrainc.plum.rte.menu.MenuItem;
import com.kbrainc.plum.rte.menu.MenuNode;
import com.kbrainc.plum.rte.menu.MenuTree;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResMenuService;
import com.kbrainc.plum.rte.service.ResMenuServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 *
 * LNB영역에 메뉴를 출력하기 위한 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.layout
 * - LeftMenuPrintImpl.java
 * </pre>
 *
 * @ClassName : LeftMenuPrintImpl
 * @Description : LNB영역에 메뉴를 출력하기 위한 클래스
 * @author : KBRAINC
 * @date : 2023. 01. 17.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class LnbMenuPrintImpl {

    /** 인가평가자. */
    @Autowired
    @Qualifier("defaultWebInvocationPrivilegeEvaluator")
    private WebInvocationPrivilegeEvaluator wipe;

    /** 메뉴정보를 메모리에 적재하는 서비스 구현 클래스. */
    @Resource(name = "cmm.resMenuService")
    private ResMenuService resMenuService;

    private HttpServletRequest request = null;

    /**
    * 현재 인증정보로 url에 접근 가능한지를 판단한다.
    *
    * @Title       : isMenuAuth
    * @Description : 현재 인증정보로 url에 접근 가능한지를 판단한다.
    * @param url URL
    * @return boolean 인가여부
    * @throws Exception 예외
    */
    private boolean isMenuAuth(String url) throws Exception {
        if ("".equals(StringUtil.nvl(url))) {
            return true;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return wipe.isAllowed(url, authentication);
    }

    /**
    * 현재 접속사이트의 메뉴트리 정보를 불러와 HTML 마크업을 출력한다.
    *
    * @Title       : menuPrint
    * @Description : 현재 접속사이트의 메뉴트리 정보를 불러와 HTML 마크업을 출력한다.
    * @return String 메뉴HTML마크업
    * @throws Exception 예외
    */
    public String menuPrint() throws Exception {
        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        MenuItem menuItem = (MenuItem) request.getAttribute(ResMenuServiceImpl.MENU_ITEM);
        if (menuItem == null) {
            return "";
        }
        HttpSession session = request.getSession();
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        MenuTree menuTree = null;
        if (siteInfo != null) {
            menuTree = resMenuService.getMenuTree(siteInfo.getSiteid());
        }

        if (menuTree == null) {
            return "";
        }
        
        MenuItem menuItemL1 = menuTree.getL1MenuItem(menuItem.getMenuid());
        MenuItem menuItem1 = null;
        MenuItem menuItem2 = null;
        MenuItem menuItem3 = null;
        boolean isMenuView = false;
        UserVo user = (UserVo) session.getAttribute("user");
        String mkey = "";
        StringBuffer menuTag = new StringBuffer();

        for (TreeNode<MenuItem> treeNode0 : menuTree.getRoot().getChildren()) {
            if (menuItemL1.getMenuid().equals(treeNode0.getData().getMenuid())) {
                menuTag.append("                    <div class=\"navigation-inner\">\n");
            	menuTag.append("                       <strong class=\"nav-title\">").append(treeNode0.getData().getNm()).append("</strong>\n");
            	menuTag.append("                       <ul class=\"dep01\">\n");

            	for (TreeNode<MenuItem> treeNode1 : treeNode0.getChildren()) {
                    menuItem1 = treeNode1.getData();

		            if ("02".equals(menuItem1.getPtypeCd()) && "N".equals(menuItem1.getHideYn())) {
		                if ((user != null && isMenuAuth(menuItem1.getUrl())) || ("N".equals(menuItem1.getNmExpsrTrgtCd()) && user == null)) {
		                    isMenuView = true;
		                } else {
		                    isMenuView = false;
		                }
		            } else {
		                isMenuView = false;
		            }
		            if (isMenuView) { // 프로그램유형코드가 메뉴/디렉토리이면서 숨김여부가 N이면서 현재역할로 메뉴에 접근가능할때만

		            	menuTag.append("                         <li class=\"");
		            	if (menuItem1.getMenuid().equals(menuItem.getMenuid()) || menuTree.getMenuNodeByMenuID((MenuNode)treeNode1, menuItem.getMenuid()) != null) {
		            		menuTag.append("active ");
		            	}
		                menuTag.append("\">");
		                
		                if ("D".equals(menuItem1.getTypeCd())) { // 메뉴타입코드가 디렉토리인경우
		                    mkey = menuItem1.getRefMenuid();
		                    if (StringUtil.isNumber(menuItem1.getUrl()) || "".equals(StringUtil.nvl(menuItem1.getUrl(), "")) || mkey == null) {
		                        menuTag.append("<a href=\"javascript:void(0)\">");
		                    } else {
		                        menuTag.append("<a href=\"javascript:goMenu('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                    }
		                } else {
		                    if ("".equals(StringUtil.nvl(menuItem1.getUrl(), ""))) {
		                        menuTag.append("<a href=\"javascript:void(0)\">");
		                    } else {
		                        mkey = menuItem1.getMenuid();

		                        if ("Y".equals(menuItem1.getPopupYn())) {
		                            if ("N".equals(menuItem1.getPopupTrgtCd())) { // 새창
		                                menuTag.append("<a href=\"javascript:goMenuNewWin('").append(menuItem1.getUrl()).append("','").append(mkey).append("')\">");
		                            } else { // 현재창
		                                menuTag.append("<a href=\"javascript:goMenuPop('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getPopupWd()).append("','").append(menuItem1.getPopupHg()).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                            }
		                        } else {
		                            menuTag.append("<a href=\"javascript:goMenu('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                        }
		                    }
		                }
		                menuTag.append(menuItem1.getNm());
		                menuTag.append("</a>\n");

		                if (treeNode1.hasChildren()) {
		                	String ptypeCd = "";
		                	boolean existUlTag = false;
		                	for (TreeNode<MenuItem> child : treeNode1.getChildren()) {
		                		ptypeCd = child.getData().getPtypeCd();
		                		if("01".equals(ptypeCd) || "02".equals(ptypeCd)) { // 자식중에 디렉토리나 메뉴가 있으면
		                			existUlTag = true;
		                			menuTag.append("                               <div class=\"dep02\">\n");
		                			menuTag.append("                                   <ul>\n");
		                			break;
		                		}
		                	}

		                    for (TreeNode<MenuItem> treeNode2 : treeNode1.getChildren()) {
		                        menuItem2 = treeNode2.getData();

		                        if ("02".equals(menuItem2.getPtypeCd()) && "N".equals(menuItem2.getHideYn())) {
		                            if ((user != null && isMenuAuth(menuItem2.getUrl()))
		                                    || ("N".equals(menuItem2.getNmExpsrTrgtCd()) && user == null)) {
		                                isMenuView = true;
		                            } else {
		                                isMenuView = false;
		                            }
		                        } else {
		                            isMenuView = false;
		                        }
		                        if (isMenuView) {
		                            
		                            menuTag.append("				<li class=\""); // active필요
		                            if (menuItem2.getMenuid().equals(menuItem.getMenuid())) {
		                                menuTag.append("active ");
		                            }
		                            menuTag.append("\">");
		                            
		                            if ("D".equals(menuItem2.getTypeCd())) { // 메뉴타입코드가 디렉토리인경우
		                                mkey = menuItem2.getRefMenuid();
		                                if (StringUtil.isNumber(menuItem2.getUrl()) || "".equals(StringUtil.nvl(menuItem2.getUrl(), "")) || mkey == null) {
		                                    menuTag.append("<a href=\"javascript:void(0)\">");
		                                } else {
											menuTag.append("<a href=\"javascript:goMenu('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                                }
		                            } else {
		                                if ("".equals(StringUtil.nvl(menuItem2.getUrl(), ""))) {
		                                    menuTag.append("<a href=\"javascript:void(0)\">");
		                                } else {
		                                    mkey = menuItem2.getMenuid();

		                                    if ("Y".equals(menuItem2.getPopupYn())) {
		                                        if ("N".equals(menuItem2.getPopupTrgtCd())) { // 새창
		                                            menuTag.append("<a href=\"javascript:goMenuNewWin('").append(menuItem2.getUrl()).append("','").append(mkey).append("')\">");
		                                        } else { // 현재창
		                                            menuTag.append("<a href=\"javascript:goMenuPop('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getPopupWd()).append("','").append(menuItem2.getPopupHg()).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                                        }
		                                    } else {
		                                        menuTag.append("<a href=\"javascript:goMenu('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\">");
		                                    }
		                                }
		                            }
		                            menuTag.append(menuItem2.getNm());
		    		                menuTag.append("</a></li>\n");
		                        }
		                    }
		                    if (existUlTag) {
		                    	menuTag.append("                                 </ul>\n");
		                    	menuTag.append("                             </div>\n");
		                    }
		                }
		                menuTag.append("                          </li>\n");
		            }
            	}
            	menuTag.append("                       </ul>\n");
            	menuTag.append("                   </div>\n");
            }
        }
        return menuTag.toString();
    }
}