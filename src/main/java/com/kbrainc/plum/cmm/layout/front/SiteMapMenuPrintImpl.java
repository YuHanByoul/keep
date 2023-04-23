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
import com.kbrainc.plum.rte.menu.MenuTree;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResMenuService;
import com.kbrainc.plum.rte.service.ResMenuServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * 사이트맵을 출력하기 위한 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.layout.front
 * - SiteMapMenuPrintImpl.java
 * </pre> 
 *
 * @ClassName : SiteMapMenuPrintImpl
 * @Description : GNB영역에 메뉴를 출력하기 위한 클래스
 * @author : KBRAINC
 * @date : 2023. 1. 16.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class SiteMapMenuPrintImpl {

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

        MenuItem menuItem1 = null;
        MenuItem menuItem2 = null;
        MenuItem menuItem3 = null;
        boolean isMenuView = false;
        UserVo user = (UserVo) session.getAttribute("user");
        String mkey = "";
        String url = "";
        StringBuffer menuTag = new StringBuffer();
        int i = 0;
        for (TreeNode<MenuItem> treeNode1 : menuTree.getRoot().getChildren()) {
            menuItem1 = treeNode1.getData();

            if ("02".equals(menuItem1.getPtypeCd()) && ("N".equals(menuItem1.getHideYn())) || treeNode1.hasHideNChildrenMenuOrFolder()) {
                if ((isMenuAuth(menuItem1.getUrl())) && treeNode1.hasChildren() || ("N".equals(menuItem1.getNmExpsrTrgtCd()) && user == null)) {
                    isMenuView = true;
                } else {
                    isMenuView = false;
                }
            } else {
                isMenuView = false;
            }
            if (isMenuView) { // 프로그램유형코드가 메뉴/디렉토리이면서 숨김여부가 N이면서 현재역할로 메뉴에 접근가능할때만
                i++;
                
                menuTag.append("    <li>\n");
                if ("D".equals(menuItem1.getTypeCd())) { // 메뉴타입코드가 디렉토리인경우
                    mkey = menuItem1.getRefMenuid();
                    if (StringUtil.isNumber(menuItem1.getUrl()) || "".equals(StringUtil.nvl(menuItem1.getUrl(), "")) || mkey == null) {
                        menuTag.append("        <a href=\"javascript:void(0)\"").append(">");
                    } else {
                        menuTag.append("        <a href=\"javascript:goMenu('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                    }
                } else {
                    if ("".equals(StringUtil.nvl(menuItem1.getUrl(), ""))) {
                        menuTag.append("        <a href=\"javascript:void(0)\"").append(">");
                    } else {
                        mkey = menuItem1.getMenuid();

                        if ("Y".equals(menuItem1.getPopupYn())) {
                            if ("N".equals(menuItem1.getPopupTrgtCd())) { // 새창
                                menuTag.append("        <a href=\"javascript:goMenuNewWin('").append(menuItem1.getUrl()).append("','").append(mkey).append("')\"").append(">");
                            } else { // 현재창
                                menuTag.append("        <a href=\"javascript:goMenuPop('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getPopupWd()).append("','").append(menuItem1.getPopupHg()).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                            }
                        } else {
                            if ("N".equals(menuItem1.getPopupTrgtCd())) { // 새창
                                menuTag.append("        <a href=\"").append(menuItem1.getUrl()).append("\" target=\"_blank\" title=\"새 창\"").append(">");
                            } else { // 현재창
                                menuTag.append("        <a href=\"javascript:goMenu('").append(menuItem1.getUrl()).append("','").append(mkey).append("','").append(menuItem1.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                            }
                        }
                    }
                }
                menuTag.append(menuItem1.getNm()).append("</a>\n");
                if (treeNode1.hasChildren()) {

                    menuTag.append("        <div class=\"dep02\">\n");
                    menuTag.append("            <div class=\"dep02-inner\">\n");
                    menuTag.append("                <ul>\n");

                    for (TreeNode<MenuItem> treeNode2 : treeNode1.getChildren()) {
                        menuItem2 = treeNode2.getData();

                        if ("02".equals(menuItem2.getPtypeCd()) && "N".equals(menuItem2.getHideYn())) {
                            if ((isMenuAuth(menuItem2.getUrl()))
                                    || ("N".equals(menuItem2.getNmExpsrTrgtCd()) && user == null)) {
                                isMenuView = true;
                            } else {
                                isMenuView = false;
                            }
                        } else {
                            isMenuView = false;
                        }
                        
                        if (isMenuView) {
                            String menuItemNm2 = menuItem2.getNm();
                            menuTag.append("                    <li>");
                            if ("D".equals(menuItem2.getTypeCd())) { // 메뉴타입코드가 디렉토리인경우
                                mkey = menuItem2.getRefMenuid();
                                if (StringUtil.isNumber(menuItem2.getUrl()) || "".equals(StringUtil.nvl(menuItem2.getUrl(), "")) || mkey == null) {
                                    menuTag.append("<a href=\"javascript:void(0)\"").append(">");
                                } else {
                                    menuTag.append("<a href=\"javascript:goMenu('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">\n");
                                }
                            } else {
                                if ("".equals(StringUtil.nvl(menuItem2.getUrl(), ""))) {
                                    menuTag.append("<a href=\"javascript:void(0)\"").append(">");
                                } else {
                                    mkey = menuItem2.getMenuid();

                                    if ("Y".equals(menuItem2.getPopupYn())) {
                                        if ("N".equals(menuItem2.getPopupTrgtCd())) { // 새창
                                            menuTag.append("<a href=\"javascript:goMenuNewWin('").append(menuItem2.getUrl()).append("','").append(mkey).append("')\"").append(">");
                                        } else { // 현재창
                                            menuTag.append("<a href=\"javascript:goMenuPop('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getPopupWd()).append("','").append(menuItem2.getPopupHg()).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                                        }
                                    } else {
                                        if ("N".equals(menuItem2.getPopupTrgtCd())) { // 새창
                                            menuTag.append("<a href=\"").append(menuItem2.getUrl()).append("\" target=\"_blank\" title=\"새 창\"").append(">");
                                        } else { // 현재창
                                            if ("/login.html".equals(menuItem2.getUrl()) && user != null) {
                                                menuItemNm2 = "로그아웃";
                                                menuTag.append("<a href=\"javascript:logout()\">");
                                            } else {
                                                menuTag.append("<a href=\"javascript:goMenu('").append(menuItem2.getUrl()).append("','").append(mkey).append("','").append(menuItem2.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                                            }
                                        }
                                    }
                                }
                            }
                            menuTag.append(menuItemNm2).append("</a>\n");
                            
                            // 3depth
                            if (treeNode2.hasChildren()) {
                                menuTag.append("                        <ul class=\"dep03\">\n");
                                for (TreeNode<MenuItem> treeNode3 : treeNode2.getChildren()) {
                                    menuItem3 = treeNode3.getData();

                                    if ("02".equals(menuItem3.getPtypeCd()) && "N".equals(menuItem3.getHideYn())) {
                                        if ((isMenuAuth(menuItem3.getUrl()))
                                                || ("N".equals(menuItem3.getNmExpsrTrgtCd()) && user == null)) {
                                            isMenuView = true;
                                        } else {
                                            isMenuView = false;
                                        }
                                    } else {
                                        isMenuView = false;
                                    }
                                    
                                    if (isMenuView) {
                                        
                                        menuTag.append("                            <li>\n");
                                        if ("D".equals(menuItem3.getTypeCd())) { // 메뉴타입코드가 디렉토리인경우
                                            mkey = menuItem3.getRefMenuid();
                                            if (StringUtil.isNumber(menuItem3.getUrl()) || "".equals(StringUtil.nvl(menuItem3.getUrl(), "")) || mkey == null) {
                                                menuTag.append("<a href=\"javascript:void(0)\"").append(">");
                                            } else {
                                                menuTag.append("<a href=\"javascript:goMenu('").append(menuItem3.getUrl()).append("','").append(mkey).append("','").append(menuItem3.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                                            }
                                        } else {
                                            if ("".equals(StringUtil.nvl(menuItem3.getUrl(), ""))) {
                                                menuTag.append("<a href=\"javascript:void(0)\"").append(">");
                                            } else {
                                                mkey = menuItem3.getMenuid();

                                                if ("Y".equals(menuItem3.getPopupYn())) {
                                                    if ("N".equals(menuItem3.getPopupTrgtCd())) { // 새창
                                                        menuTag.append("<a href=\"javascript:goMenuNewWin('").append(menuItem3.getUrl()).append("','").append(mkey).append("')\"").append(">");
                                                    } else { // 현재창
                                                        menuTag.append("<a href=\"javascript:goMenuPop('").append(menuItem3.getUrl()).append("','").append(mkey).append("','").append(menuItem3.getPopupWd()).append("','").append(menuItem3.getPopupHg()).append("','").append(menuItem3.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                                                    }
                                                } else {
                                                    if ("N".equals(menuItem3.getPopupTrgtCd())) { // 새창
                                                        menuTag.append("<a href=\"").append(menuItem3.getUrl()).append("\" target=\"_blank\" title=\"새 창\"").append(">");
                                                    } else { // 현재창
                                                        menuTag.append("<a href=\"javascript:goMenu('").append(menuItem3.getUrl()).append("','").append(mkey).append("','").append(menuItem3.getLoginYn()).append("','").append(user == null ? "N" : "Y").append("')\"").append(">");
                                                    }
                                                }
                                            }
                                        }
                                        menuTag.append(menuItem3.getNm()).append("</a></li>\n");
                                    }
                                }
                                menuTag.append("                        </ul>\n");
                            }
                            menuTag.append("                    </li>\n");
                        }
                    }
                    menuTag.append("                </ul>\n");
                    menuTag.append("            </div>\n");
                    menuTag.append("        </div>\n");
                }
                menuTag.append("    </li>\n");
            }
        }
        return menuTag.toString();
    }
}