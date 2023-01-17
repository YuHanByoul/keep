package com.kbrainc.plum.rte.service;

import com.kbrainc.plum.rte.menu.MenuItem;
import com.kbrainc.plum.rte.menu.MenuTree;

/**
 * 
 * 메뉴정보를 조회하는 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.rte.service
 * - ResMenuService.java
 * </pre> 
 *
 * @ClassName : ResMenuService
 * @Description : 메뉴정보를 조회하는 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface ResMenuService {

    /**
    * @Title : getMenuTree
    * @Description : 메뉴트리 정보를 얻어온다
    * @param siteid 사이트아이디
    * @return MenuTree 메뉴트리
    * @throws Exception 예외
    */
    public MenuTree getMenuTree(String siteid) throws Exception;

    /**
    * @Title : makeTreeMenuInfo
    * @Description : 트리맵의 홈페이지별 메뉴트리정보를 갱신한다
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void makeTreeMenuInfo() throws Exception;
    
    /**
    * siteid를 키로하는 캐시를 삭제한다.
    *
    * @Title       : removeCacheForSiteid 
    * @Description : siteid를 키로하는 캐시를 삭제한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void removeCacheForSiteid(String siteid) throws Exception;
    
    /**
    * siteid를 키로하는 캐시를 등록한다.
    *
    * @Title       : putCacheForSiteid 
    * @Description : siteid를 키로하는 캐시를 등록한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void putCacheForSiteid(String siteid) throws Exception;
    
    /**
    * 특정 사이트의 menuID에 해당하는 MenuItem을 반환한다.
    *
    * @Title : getMenuItemByMenuID
    * @Description : 특정 사이트의 menuID에 해당하는 MenuItem을 반환한다.
    * @param siteid 사이트아이디
    * @param menuID 메뉴아이디
    * @return MenuItem 메뉴아이템 정보
    * @throws Exception 예외
    */
    public MenuItem getMenuItemByMenuID(String siteid, String menuID) throws Exception;
}