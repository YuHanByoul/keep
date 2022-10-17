package com.kbrainc.plum.rte.menu;

import com.kbrainc.plum.rte.lib.tree.TreeNode;

/**
 * 
 * MenuNode 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.menu
 * - MenuNode.java
 * </pre> 
 *
 * @ClassName : MenuNode
 * @Description : MenuNode 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class MenuNode extends TreeNode<MenuItem> {

    /* 메뉴아이템 포함여부 */
    private boolean hasData = false;

    /**
     * Desc : Constructor of MenuNode.java class
     */
    public MenuNode() {
        super();
    }

    /**
     * Desc : Constructor of MenuNode.java class
     * 
     * @param item 메뉴노드의 아이템
     */
    /*
    public MenuNode(MenuItem item) {
        this();
        if (item != null) {
            this.setData(item);
            this.hasData = true;
        }
    }
    */
    public void setMenuNode(MenuItem item) {
        if (item != null) {
            this.setData(item);
            this.hasData = true;
        }
    }

    /**
     * 자식 트리노드 목록에 자식노드를 추가한다.
     * 
     * @Title : addChild
     * @Description : 자식 트리노드 목록에 자식노드를 추가한다.
     * @param child 자식노드
     */
    public void addChild(TreeNode<MenuItem> child) {
        child.setParent(this);
        super.addChild(child);
    }

    /**
     * 메뉴아이템의 메뉴ID를 가져온다.
     * 
     * @Title : getMenu_id
     * @Description : 메뉴아이템의 메뉴ID를 가져온다.
     * @return String 메뉴ID
     */
    public String getMenu_id() {
        if (hasData) {
            return getData().getMenuid();
        } else {
            return null;
        }
    }

    /**
     * 메뉴아이템의 상위메뉴ID를 가져온다.
     * 
     * @Title : getUpr_menu_id
     * @Description : 메뉴아이템의 상위메뉴ID를 가져온다.
     * @return String 메뉴ID
     */
    public String getUpr_menu_id() {
        if (hasData) {
            return getData().getUpprMenuid();
        } else {
            return null;
        }
    }

    /**
     * 메뉴아이템의 메뉴명을 가져온다.
     * 
     * @Title : getMenu_nm
     * @Description : 메뉴아이템의 메뉴명을 가져온다.
     * @return String 메뉴명
     */
    public String getMenu_nm() {
        if (hasData) {
            return getData().getNm();
        } else {
            return null;
        }
    }
}