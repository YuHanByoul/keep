package com.kbrainc.plum.cmm.layout;

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
 * GNB영역에 메뉴를 출력하기 위한 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.layout
 * - MenuPrintImpl.java
 * </pre> 
 *
 * @ClassName : MenuPrintImpl
 * @Description : GNB영역에 메뉴를 출력하기 위한 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Component
public class MenuPrintImpl {

    /** 인가평가자. */
    @Autowired
    @Qualifier("defaultWebInvocationPrivilegeEvaluator")
    private WebInvocationPrivilegeEvaluator wipe;

    /** 메뉴정보를 메모리에 적재하는 서비스 구현 클래스. */
    @Resource(name = "cmm.resMenuService")
    private ResMenuService resMenuService;

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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
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

        String curMenuid = "";
        String upprMenuid = "";

        if ("3".equals(menuItem.getDpth())) {
            curMenuid = menuItem.getUppr_menuid();
            upprMenuid = menuTree.getMenuItemByMenuID(curMenuid).getUppr_menuid();
        } else {
            curMenuid = menuItem.getMenuid();
            upprMenuid = menuItem.getUppr_menuid();
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

            if ("02".equals(menuItem1.getPtype_cd()) && "N".equals(menuItem1.getHide_yn())) {
                if ((user != null && isMenuAuth(menuItem1.getUrl())) && treeNode1.hasChildren()
                        && isMenuAuth(((TreeNode<MenuItem>) treeNode1.getChildAt(0)).getData().getUrl())
                        || ("N".equals(menuItem1.getNm_expsr_trgt_cd()) && user == null)) {
                    isMenuView = true;
                } else {
                    isMenuView = false;
                }
            } else {
                isMenuView = false;
            }
            if (isMenuView) { // 프로그램유형코드가 메뉴/디렉토리이면서 숨김여부가 N이면서 현재역할로 메뉴에 접근가능할때만
                i++;

                menuTag.append("    <li class=\"");
                if (treeNode1.hasChildren()) {
                    menuTag.append("has-sub ");
                }
                if (menuItem1.getMenuid().equals(curMenuid)) {
                    menuTag.append("highlight ");
                }
                if (menuItem1.getMenuid().equals(upprMenuid)) {
                    menuTag.append("highlight active ");
                }

                menuTag.append("\">\n");
                if ("D".equals(menuItem1.getType_cd())) { // 메뉴타입코드가 디렉토리인경우
                    mkey = menuItem1.getRef_menuid();
                    if (StringUtil.isNumber(menuItem1.getUrl()) || "".equals(StringUtil.nvl(menuItem1.getUrl(), "")) || mkey == null) {
                        menuTag.append("                <a href=\"javascript:void(0)\">\n");
                    } else {
                        menuTag.append("                <a href=\"javascript:goMenu('" + menuItem1.getUrl() + "','"
                                + mkey + "')\">\n");
                    }
                } else {
                    if ("".equals(StringUtil.nvl(menuItem1.getUrl(), ""))) {
                        menuTag.append("    <a href=\"javascript:void(0)\" class=\"nolink\">\n");
                    } else {
                        mkey = menuItem1.getMenuid();

                        if ("Y".equals(menuItem1.getPopup_yn())) {
                            if ("N".equals(menuItem1.getPopup_trgt_cd())) { // 새창
                                menuTag.append("    <a href=\"javascript:goMenuNewWin('" + menuItem1.getUrl() + "','"
                                        + mkey + "')\">\n");
                            } else { // 현재창
                                menuTag.append("    <a href=\"javascript:goMenuPop('" + menuItem1.getUrl() + "','"
                                        + mkey + "','" + menuItem1.getPopup_wd() + "','" + menuItem1.getPopup_hg()
                                        + "')\">\n");
                            }
                        } else {
                            menuTag.append("    <a href=\"javascript:goMenu('" + menuItem1.getUrl() + "','" + mkey
                                    + "')\">\n");
                        }
                    }
                }
                menuTag.append("<span><p class=\"glyphicon glyphicon-cloud\" aria-hidden=\"true\"></p> <strong>"
                        + menuItem1.getNm() + "</strong></span></a>\n");
                if (treeNode1.hasChildren()) {

                    if (menuItem1.getMenuid().equals(upprMenuid)) {
                        menuTag.append("        <ul style=\"display: block\">\n");
                    } else {
                        menuTag.append("        <ul>\n");
                    }

                    for (TreeNode<MenuItem> treeNode2 : treeNode1.getChildren()) {
                        menuItem2 = treeNode2.getData();

                        if ("02".equals(menuItem2.getPtype_cd()) && "N".equals(menuItem2.getHide_yn())) {
                            if ((user != null && isMenuAuth(menuItem2.getUrl()))
                                    || ("N".equals(menuItem2.getNm_expsr_trgt_cd()) && user == null)) {
                                isMenuView = true;
                            } else {
                                isMenuView = false;
                            }
                        } else {
                            isMenuView = false;
                        }
                        if (isMenuView) {
                            menuTag.append("<li>\n");
                            if ("D".equals(menuItem2.getType_cd())) { // 메뉴타입코드가 디렉토리인경우
                                mkey = menuItem2.getRef_menuid();
                                if (StringUtil.isNumber(menuItem2.getUrl()) || "".equals(StringUtil.nvl(menuItem2.getUrl(), "")) || mkey == null) {
                                    menuTag.append("                <a href=\"javascript:void(0)\">\n");
                                } else {
                                    menuTag.append("                <a href=\"javascript:goMenu('" + menuItem2.getUrl()
                                            + "','" + mkey + "')\">\n");
                                }
                            } else {
                                if ("".equals(StringUtil.nvl(menuItem2.getUrl(), ""))) {
                                    menuTag.append("                <a href=\"javascript:void(0)\" class=\"nolink\">\n");
                                } else {
                                    mkey = menuItem2.getMenuid();

                                    if ("Y".equals(menuItem2.getPopup_yn())) {
                                        if ("N".equals(menuItem2.getPopup_trgt_cd())) { // 새창
                                            menuTag.append("    <a href=\"javascript:goMenuNewWin('"
                                                    + menuItem2.getUrl() + "','" + mkey + "')\">\n");
                                        } else { // 현재창
                                            menuTag.append("    <a href=\"javascript:goMenuPop('" + menuItem2.getUrl()
                                                    + "','" + mkey + "','" + menuItem2.getPopup_wd() + "','"
                                                    + menuItem2.getPopup_hg() + "')\">\n");
                                        }
                                    } else {
                                        menuTag.append("    <a");
                                        if (mkey.equals(curMenuid) && "2".equals(menuItem.getDpth())) {
                                            menuTag.append(" class=\"select\"");
                                        }
                                        menuTag.append(" href=\"javascript:goMenu('" + menuItem2.getUrl() + "','" + mkey
                                                + "')\">\n");
                                    }
                                }
                            }
                            menuTag.append("<span>" + menuItem2.getNm() + "</span></a>\n");

                            // menuTag.append("<ul><li><a class=\"select\" style=\"padding-left:55px\"
                            // href=\"javascript:void(0)\"><span>테스트</span></a></li></ul>\n");
                            // 3depth
                            if (treeNode2.hasChildren()) {
                                menuTag.append("        <ul>\n");
                                for (TreeNode<MenuItem> treeNode3 : treeNode2.getChildren()) {
                                    menuItem3 = treeNode3.getData();

                                    if ("02".equals(menuItem3.getPtype_cd()) && "N".equals(menuItem3.getHide_yn())) {
                                        if ((user != null && isMenuAuth(menuItem3.getUrl()))
                                                || ("N".equals(menuItem3.getNm_expsr_trgt_cd()) && user == null)) {
                                            isMenuView = true;
                                        } else {
                                            isMenuView = false;
                                        }
                                    } else {
                                        isMenuView = false;
                                    }
                                    if (isMenuView) {
                                        menuTag.append("<li>\n");
                                        if ("D".equals(menuItem3.getType_cd())) { // 메뉴타입코드가 디렉토리인경우
                                            mkey = menuItem3.getRef_menuid();
                                            if (StringUtil.isNumber(menuItem3.getUrl()) || "".equals(StringUtil.nvl(menuItem3.getUrl(), "")) || mkey == null) {
                                                menuTag.append("                <a href=\"javascript:void(0)\">\n");
                                            } else {
                                                menuTag.append("                <a href=\"javascript:goMenu('"
                                                        + menuItem3.getUrl() + "','" + mkey + "')\">\n");
                                            }
                                        } else {
                                            if ("".equals(StringUtil.nvl(menuItem3.getUrl(), ""))) {
                                                menuTag.append("                <a href=\"javascript:void(0)\" class=\"nolink\">\n");
                                            } else {
                                                mkey = menuItem3.getMenuid();

                                                if ("Y".equals(menuItem3.getPopup_yn())) {
                                                    if ("N".equals(menuItem3.getPopup_trgt_cd())) { // 새창
                                                        menuTag.append("    <a href=\"javascript:goMenuNewWin('"
                                                                + menuItem3.getUrl() + "','" + mkey + "')\">\n");
                                                    } else { // 현재창
                                                        menuTag.append("    <a href=\"javascript:goMenuPop('"
                                                                + menuItem3.getUrl() + "','" + mkey + "','"
                                                                + menuItem3.getPopup_wd() + "','"
                                                                + menuItem3.getPopup_hg() + "')\">\n");
                                                    }
                                                } else {
                                                    menuTag.append("    <a");
                                                    if (mkey.equals(menuItem.getMenuid())) {
                                                        menuTag.append(" class=\"select\" style=\"padding-left:55px\"");
                                                    }
                                                    menuTag.append(" href=\"javascript:goMenu('" + menuItem3.getUrl()
                                                            + "','" + mkey + "')\">\n");
                                                }
                                            }
                                        }
                                        menuTag.append("<span>" + menuItem3.getNm() + "</span></a></li>\n");
                                    }
                                }
                                menuTag.append("</ul>\n");
                            }
                            menuTag.append("</li>\n");
                        }
                    }
                    menuTag.append("</ul>\n");
                }
                menuTag.append("</li>\n");
            }
        }
        return menuTag.toString();
    }
}