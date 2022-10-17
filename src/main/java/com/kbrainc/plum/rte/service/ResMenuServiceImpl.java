package com.kbrainc.plum.rte.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.menu.MenuItem;
import com.kbrainc.plum.rte.menu.MenuNode;
import com.kbrainc.plum.rte.menu.MenuTree;
import com.kbrainc.plum.rte.model.ResMenuDao;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
* 
* 메뉴정보를 메모리에 적재하는 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.rte.service
* - ResMenuServiceImpl.java
* </pre> 
*
* @ClassName : ResMenuServiceImpl
* @Description : 메뉴정보를 메모리에 적재하는 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Service("cmm.resMenuService")
public class ResMenuServiceImpl extends PlumAbstractServiceImpl implements ResMenuService {

    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(ResMenuServiceImpl.class);
    public final static String TREE_MENU_ROOTID = "0";
    public final static String MENU_ITEM = "MENU_ITEM";
    public final static String MENU_NAME = "MENU_NAME";
    public final static String MENU_POPNOTI_ID = "MENU_POPNOTI_ID";

    /** 트리맵 */
    private Map<String, MenuTree> treeMap = null;

    @Autowired
    private ResMenuDao resMenuDao;
    
    @Autowired
    CacheManager cacheManager;

    /**
    * @Title : getMenuTree
    * @Description : 메뉴트리 정보를 얻어온다
    * @param siteid 사이트아이디
    * @return MenuTree 메뉴트리
    * @throws Exception 예외
    */
    @Override
    public MenuTree getMenuTree(String siteid) throws Exception {
        // if(treeMap.size() == 0)
        // makeTreeMenuInfo();
        if (treeMap == null || !treeMap.containsKey(siteid)) {
            try {
                makeTreeMenuInfoSite(siteid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return treeMap.get(siteid);
    }

    /**
    * @Title : makeTreeMenuInfo
    * @Description : 트리맵의 홈페이지별 메뉴트리정보를 갱신한다
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    public void makeTreeMenuInfo() throws Exception {
        if (treeMap == null) {
            treeMap = new HashMap<String, MenuTree>();
        }

        synchronized (treeMap) {
            try {
                treeMap.clear();
                String prevSiteid = "";
                List<MenuItem> menuList = resMenuDao.selectSiteMenuList(null);
                List<MenuItem> menuList2 = new ArrayList<MenuItem>();
                MenuTree menuTree = null;
                for (MenuItem menuItem : menuList) {
                    if (!"".equals(prevSiteid) && !prevSiteid.equals(menuItem.getSiteid())) {
                        menuTree = makeMenuTree(menuList2);
                        treeMap.put(prevSiteid, menuTree);
                        menuList2 = new ArrayList<MenuItem>();
                    }
                    menuList2.add(menuItem);
                    prevSiteid = menuItem.getSiteid();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * @Title : makeTreeMenuInfoSite
    * @Description : 특정 사이트의 메뉴트리정보를 갱신한다
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void makeTreeMenuInfoSite(String siteid) throws Exception {
        if (treeMap == null) {
            treeMap = new HashMap<String, MenuTree>();
        }

        synchronized (treeMap) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("siteid", siteid);
            List<MenuItem> menuList = resMenuDao.selectSiteMenuList(param);
            MenuTree menuTree = makeMenuTree(menuList);
            treeMap.put(siteid, menuTree);
        }
    }

    /**
    * @Title : makeMenuTree
    * @Description : 메뉴목록으로 MenuTree를 만듬
    * @param menuList 메뉴정보리스트
    * @return MenuTree 메뉴트리
    * @throws Exception 예외
    */
    private MenuTree makeMenuTree(List<MenuItem> menuList) throws Exception {
        MenuTree tree = new MenuTree();
        Map<String, List<MenuItem>> menuMap = new HashMap<String, List<MenuItem>>();

        for (MenuItem item : menuList) {
            MenuNode node = new MenuNode();
            node.setMenuNode(item);

            MenuNode pNode = null;

            if (TREE_MENU_ROOTID.equals(item.getMenuid())) {
                tree.setRoot(node);
            } else {
                pNode = tree.getMenuNodeByMenuID(item.getUpprMenuid());
            }

            if (pNode != null) {
                pNode.addChild(node);
            }

            List<MenuItem> menuItemList = menuMap.get(item.getMenuid());
            if (menuItemList == null) {
                menuItemList = new ArrayList<MenuItem>();
                menuMap.put(item.getMenuid(), menuItemList);
            }
            menuItemList.add(item);

            menuItemList = menuMap.get(item.getSiteid() + "|" + item.getUrl());
            if (menuItemList == null) {
                menuItemList = new ArrayList<MenuItem>();
                menuMap.put(item.getSiteid() + "|" + item.getUrl(), menuItemList);
            }
            menuItemList.add(item);

        }

        tree.setMenuMap(menuMap);

        return tree;
    }
    
    /**
    * siteid를 키로하는 캐시를 삭제한다.
    *
    * @Title       : removeCacheForSiteid 
    * @Description : siteid를 키로하는 캐시를 삭제한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @Override
    public void removeCacheForSiteid(String siteid) throws Exception {
        Ehcache menuMap = (Ehcache)cacheManager.getCache("menuMap").getNativeCache();
        Element element = menuMap.get(siteid);
        
        if (element != null) {
            menuMap.remove(siteid);
        }
    }
    
    /**
    * siteid를 키로하는 캐시를 등록한다.
    *
    * @Title       : putCacheForSiteid 
    * @Description : siteid를 키로하는 캐시를 등록한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void putCacheForSiteid(String siteid) throws Exception {
        Ehcache menuMap = (Ehcache)cacheManager.getCache("menuMap").getNativeCache();
        Element element = menuMap.get(siteid);
        
        if (element == null) {
            menuMap.put(new Element(siteid, true));
        }
    }
}