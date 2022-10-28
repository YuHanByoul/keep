package com.kbrainc.plum.rte.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kbrainc.plum.rte.lib.tree.TreeNode;
import com.kbrainc.plum.rte.lib.tree.TreeRoot;

/**
 * 
 * MenuTree 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.menu
 * - MenuTree.java
 * </pre> 
 *
 * @ClassName : MenuTree
 * @Description : MenuTree 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class MenuTree extends TreeRoot<MenuItem> {

    /**
     * menu_id or url 기반의 메뉴맵.
     */
    private Map<String, List<MenuItem>> menuMap = null;

    /**
     * Desc : Constructor of MenuTree.java class
     */
    public MenuTree() {
        super();
    }

    /**
     * enuID로 메뉴노드를 찾아 옮.
     * 
     * @Title : getMenuNodeByMenuID
     * @Description : enuID로 메뉴노드를 찾아 옮
     * @param menuID 메뉴ID
     * @return MenuNode 메뉴노드
     */
    public MenuNode getMenuNodeByMenuID(String menuID) {
        if (menuID != null) {
            return getMenuNodeByMenuID((MenuNode) this.getRoot(), menuID);
        } else {
            return null;
        }
    }

    /**
     * 파라미터로 전달된 메뉴노드이하 노드에서 menuID에 해당하는 메뉴노드를 찾는다.
     * 
     * @Title : getMenuNodeByMenuID
     * @Description : 파라미터로 전달된 메뉴노드이하 노드에서 menuID에 해당하는 메뉴노드를 찾는다.
     * @param element 메뉴노드
     * @param menuID  메뉴ID
     * @return MenuNode 메뉴노드
     */
    public MenuNode getMenuNodeByMenuID(MenuNode element, String menuID) {
        MenuNode returnNode = null;
        if (element != null) {
            MenuItem item = element.getData();
            if (menuID.equals(item.getMenuid())) {
                returnNode = (MenuNode) element;
            } else {
                for (TreeNode<MenuItem> data : element.getChildren()) {
                    returnNode = getMenuNodeByMenuID((MenuNode) data, menuID);
                    if (returnNode != null) {
                        break;
                    }
                }
            }
        }
        return returnNode;
    }

    /**
     * 메뉴맵을 셋팅한다.
     * 
     * @Title : setMenuMap
     * @Description : 메뉴맵을 셋팅한다.
     * @param menuMap 메뉴맵
     */
    public void setMenuMap(Map<String, List<MenuItem>> menuMap) {
        this.menuMap.putAll(menuMap);
    }

    /**
     * menu_id로 MenuItem을 찾아 옮.
     * 
     * @Title : getMenuItemByMenuID
     * @Description : menu_id로 MenuItem을 찾아 옮
     * @param menuId 메뉴ID
     * @return MenuItem 메뉴아이템
     */
    public MenuItem getMenuItemByMenuID(String menuId) {
        if (menuId != null) {
            return menuMap.get(menuId).get(0);
        } else {
            return null;
        }
    }

    /**
     * url로 MenuItem 리스트를 돌려줌.
     * 
     * @Title : getMenuItemByURL
     * @Description : url로 MenuItem 리스트를 돌려줌
     * @param siteid 사이트아이디
     * @param url    url
     * @return List MenuItem  메뉴아이템 목록
     */
    public List<MenuItem> getMenuItemByURL(String siteid, String url) {
        if (siteid != null && url != null) {
            return menuMap.get(siteid + "|" + url);
        } else {
            return null;
        }
    }

    /**
     * menu_id로 하위 메뉴의 MenuItem 리스트를 돌려줌.
     * 
     * @Title : getChildrenMenuItemByMenuID
     * @Description : menu_id로 하위 메뉴의 MenuItem 리스트를 돌려줌
     * @param menuId 메뉴ID
     * @return List MenuItem 메뉴아이템 목록
     */
    public List<MenuItem> getChildrenMenuItemByMenuID(String menuId) {
        MenuItem item = null;
        List<MenuItem> itemList = new ArrayList();
        for (TreeNode<MenuItem> treeNode : this.getMenuNodeByMenuID(menuId).getChildren()) {
            item = treeNode.getData();
            itemList.add(item);
            itemList.addAll(this.getChildrenMenuItemByMenuID(item.getMenuid()));
        }
        return itemList;
    }

    /**
     * menuId 상위의 level1 메뉴id를 가지고 온다.
     * 
     * @Title : getL1MenuId
     * @Description : menuId 상위의 level1 메뉴id를 가지고 온다.
     * @param menuId 메뉴ID
     * @return String 메뉴ID
     */
    public String getL1MenuId(String menuId) {
        String rv = null;
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("1".equals(item.getDpth())) {
                return item.getMenuid();
            } else {
                return this.getL1MenuId(item.getUpprMenuid());
            }
        }
        return rv;
    }

    /**
     * menuId 상위의 level1 메뉴Item을 가지고 온다.
     * 
     * @Title : getL1MenuItem
     * @Description : menuId 상위의 level1 메뉴Item을 가지고 온다.
     * @param menuId 메뉴ID
     * @return MenuItem 메뉴아이템
     */
    public MenuItem getL1MenuItem(String menuId) {
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("1".equals(item.getDpth())) {
                return item;
            } else {
                return this.getL1MenuItem(item.getUpprMenuid());
            }
        }
        return null;
    }

    /**
     * menuId 상위의 level2 메뉴id를 가지고 온다.
     * 
     * @Title : getL2MenuId
     * @Description : menuId 상위의 level2 메뉴id를 가지고 온다.
     * @param menuId 메뉴ID
     * @return String 메뉴ID
     */
    public String getL2MenuId(String menuId) {
        String rv = null;
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("2".equals(item.getDpth())) {
                return item.getMenuid();
            } else {
                return this.getL2MenuId(item.getUpprMenuid());
            }
        }
        return rv;
    }

    /**
     * menuId 상위의 level2 메뉴Item을 가지고 온다.
     * 
     * @Title : getL2MenuItem
     * @Description : menuId 상위의 level2 메뉴Item을 가지고 온다.
     * @param menuId 메뉴ID
     * @return MenuItem 메뉴아이템
     */
    public MenuItem getL2MenuItem(String menuId) {
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("2".equals(item.getDpth())) {
                return item;
            } else {
                return this.getL2MenuItem(item.getUpprMenuid());
            }
        }
        return null;
    }

    /**
     * menuId 상위의 level3 메뉴id를 가지고 온다.
     * 
     * @Title : getL3MenuId
     * @Description : menuId 상위의 level3 메뉴id를 가지고 온다.
     * @param menuId 메뉴ID
     * @return String 메뉴ID
     */
    public String getL3MenuId(String menuId) {
        String rv = null;
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("3".equals(item.getDpth())) {
                return item.getMenuid();
            } else {
                return this.getL3MenuId(item.getUpprMenuid());
            }
        }
        return rv;
    }

    /**
     * menuId 상위의 level3 메뉴Item을 가지고 온다.
     * 
     * @Title : getL3MenuItem
     * @Description : menuId 상위의 level3 메뉴Item을 가지고 온다.
     * @param menuId 메뉴ID
     * @return MenuItem 메뉴아이템
     */
    public MenuItem getL3MenuItem(String menuId) {
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("3".equals(item.getDpth())) {
                return item;
            } else {
                return this.getL3MenuItem(item.getUpprMenuid());
            }
        }
        return null;
    }

    /**
     * menuId 상위의 level4 메뉴id를 가지고 온다.
     * 
     * @Title : getL4MenuId
     * @Description : menuId 상위의 level4 메뉴id를 가지고 온다.
     * @param menuId 메뉴ID
     * @return String 메뉴ID
     */
    public String getL4MenuId(String menuId) {
        String rv = null;
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("4".equals(item.getDpth())) {
                return item.getMenuid();
            } else {
                return this.getL4MenuId(item.getUpprMenuid());
            }
        }
        return rv;
    }

    /**
     * menuId 상위의 level4 메뉴Item을 가지고 온다.
     * 
     * @Title : getL4MenuItem
     * @Description : menuId 상위의 level4 메뉴Item을 가지고 온다.
     * @param menuId 메뉴ID
     * @return MenuItem 메뉴아이템
     */
    public MenuItem getL4MenuItem(String menuId) {
        List<MenuItem> itemList = this.menuMap.get(menuId);
        MenuItem item = null;
        item = itemList.get(0);
        if (item != null) {
            if ("4".equals(item.getDpth())) {
                return item;
            } else {
                return this.getL4MenuItem(item.getUpprMenuid());
            }
        }
        return null;
    }

    /**
     * @Title : getParents
     * @Description : root를 제외한 상위 메뉴를 가져 옴
     * @param menu 메뉴아이템
     * @return List<MenuItem> 메뉴아이템 목록
     */
//    public List<MenuItem> getParents(MenuItem menu){
//        List<MenuItem> list  = new ArrayList<MenuItem>();
//        if(menu!= null && !(ResMenuServiceImpl.TREE_MENU_ROOTID.equals(menu.getMenuid()))){
//            list.addAll(getParents(menuMap.get(menu.getUppr_menuid()).get(0)));
//            list.add(menu);
//        }
//        return list;
//    }
}