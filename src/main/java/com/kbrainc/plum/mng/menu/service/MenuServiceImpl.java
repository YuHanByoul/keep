package com.kbrainc.plum.mng.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.menu.model.MenuDao;
import com.kbrainc.plum.mng.menu.model.MenuVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;

/**
* 
* 메뉴관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.menu.service
* - MenuServiceImpl.java
* </pre> 
*
* @ClassName : MenuServiceImpl
* @Description : 메뉴관리 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Service("mng.menu.menuService")
public class MenuServiceImpl extends PlumAbstractServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
    * 사이트별 메뉴구조 트리리스트
    * 
    * @Title : selectMenuTreeSearchList
    * @Description : 사이트별 메뉴구조 트리리스트
    * @param param Map타입의 인자
    * @return List<TreeItem> 메뉴트리 목록
    * @throws Exception 예외
    */
    @Override
    public List<TreeItem> selectMenuTreeSearchList(Map param) throws Exception {
        if ("".equals(StringUtil.nvl(param.get("srchUrl")))) {
            return menuDao.selectMenuTreeSearchList(param);
        } else {
            return menuDao.selectMenuTreeUrlSearchList(param);
        }
    }

    /**
    * 메뉴 상세정보
    * 
    * @Title : selectMenuDetailInfo
    * @Description : 메뉴 상세정보
    * @param menuVo 메뉴 도메인 클래스
    * @return Menu 메뉴 상세정보
    * @throws Exception 예외
    */
    @Override
    public MenuVo selectMenuDetailInfo(MenuVo menuVo) throws Exception {
        return menuDao.selectMenuDetailInfo(menuVo);
    }

    /**
    * 메뉴 정보 직접등록
    * 
    * @Title : insertMenuDirectInsertInfo
    * @Description : 메뉴 정보 직접등록
    * @param menuVo 메뉴 도메인 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    public int insertMenuDirectInsertInfo(MenuVo menuVo) throws Exception {
        int retVal = 0;
        if ("D".equals(menuVo.getTypeCd())) {
            menuVo.setPopupYn("N"); // 폴더인 경우 팝업사용여부 N
        }

        int menuid = menuDao.insertMenuDirectInsertInfo(menuVo); // 메뉴를 먼저 등록
        // 트리순서 재조정
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("siteid", menuVo.getSiteid());
        retVal = menuDao.updateMenuTreeOrder(map);
        retVal = 1;
        
        return retVal;
    }

    /**
    * 메뉴정보 수정
    * 
    * @Title : updateMenuInfo
    * @Description : 메뉴정보 수정
    * @param menuVo 메뉴 도메인 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Transactional
    public int updateMenuInfo(MenuVo menuVo) throws Exception {
        int retVal = 0;
        retVal = menuDao.updateMenuInfo(menuVo);

        // 트리순서 재조정
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("siteid", menuVo.getSiteid());
        retVal = menuDao.updateMenuTreeOrder(map);
        retVal = 1;
        return retVal;
    }

    /**
    * 메뉴 정보 삭제
    * 
    * @Title : deleteMenuInfo
    * @Description : 메뉴 정보 삭제
    * @param menuVo 메뉴 도메인 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int deleteMenuInfo(MenuVo menuVo) throws Exception {

    	int parentId = menuVo.getMenuid();
        // 자식리스트 불러오기
        List<MenuVo> menuList = menuDao.selectMenuChildTreeList(menuVo);
        MenuVo childVo =new MenuVo(); 
        for (int i = 0; i < menuList.size(); i++) {
            childVo.setMenuid(((MenuVo) menuList.get(i)).getMenuid());
            // 메뉴 권한 삭제
            menuDao.deleteRoleMenuAuth(childVo);
        }
        menuDao.deleteRoleMenuAuth(menuVo);
        //Integer menuid = menuVo.getMenuid();
        // menu_ord조정
        menuVo.setHitMode("after");
        menuVo.setUpperYn("N");
        menuVo.setTmenuid(parentId);
        menuVo.setMenuid(null);
        menuDao.updateMenuReOrder(menuVo);
        menuVo.setMenuid(parentId);
        int ret = menuDao.deleteMenuInfo(menuVo);// 메뉴삭제

        // 트리depth 재조정
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("siteid", menuVo.getSiteid());
        menuDao.updateMenuTreeDepth(map);
        
        return ret;
    }

    /**
    * 메뉴tree 저장
    * 
    * @Title : insertMenuTreeList
    * @Description : 메뉴tree 저장
    * @param param Map타입의 인자
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    public int insertMenuTreeList(Map param, UserVo user) throws Exception {
        int retVal = 0;
        int datalen = Integer.parseInt((String) param.get("datalen"));
        String pkey = "";
        String key = "";
        String order = "";
        int menuid = 0;
        Map<String, Integer> keyToMenuid = new HashMap<String, Integer>();
        
        for (int i = 0; i < datalen; i++) {
            pkey = (String) param.get("saveData[" + i + "][parent_key]");
            key = (String) param.get("saveData[" + i + "][key]");
            order = (String) param.get("saveData[" + i + "][order]");
            
            MenuVo menu = new MenuVo();
            menu.setUser(user);
            menu.setPrgrmid(Integer.parseInt(key.substring(1, key.length())));
            menu.setOrd(Integer.parseInt(order));
            menu.setSiteid(Integer.parseInt((String) param.get("siteid")));

            param.put("prgrmid", key.substring(1, key.length()));
            param.put("order", order);

            if (pkey.equals("")) { // pkey의 값을 부모로 입력
                pkey = "0";
            }
                        
            if (!"_".equals(pkey.substring(0, 1))) {
                param.put("upprMenuid", pkey);
                menu.setUpprMenuid(Integer.parseInt(pkey));
                menuDao.updateMenuTreeReOrder(param);
            } else {
                menu.setUpprMenuid(keyToMenuid.get(pkey));
            }
            menuDao.insertMenuTreeList(menu);
            keyToMenuid.put(key, menu.getMenuid());
        }
        // 트리순서 재조정
        retVal = menuDao.updateMenuTreeOrder(param);
        
        return retVal;
    }

    /**
    * 메뉴tree 순서 변경
    * 
    * @Title : updateMenuTreeReorder
    * @Description : 메뉴tree 순서 변경
    * @param param Map타입의 인자
    * @return int 처리성공값
    * @throws Exception 예외
    */
    @Override
    public int updateMenuTreeReorder(Map param) throws Exception {
        int retVal = 0;
        menuDao.updateMenuPreOrder(param);
        String[] menuArr = ((String) param.get("menuArr")).split(",");
        int ord = 1;
        for (int i = 0; i < menuArr.length; i++) {
            param.put("menuid", menuArr[i]);
            param.put("ord", ord);
            param.put("upprMenuid", param.get("upprMenuid"));
            menuDao.updateMenuTreeInfoNew(param);
            ord++;
        }

        // 트리순서 조정
        retVal = menuDao.updateMenuTreeOrder(param);
        retVal = 1;
        return retVal;
    }

    /**
    * 하위메뉴 리스트
    * 
    * @Title : selectMenuChildTreeList
    * @Description : 하위메뉴 리스트
    * @param menuVo 메뉴 도메인 클래스
    * @return List<Menu> 하위메뉴 목록
    * @throws Exception 예외
    */
    @Override
    public List<MenuVo> selectMenuChildTreeList(MenuVo menuVo) throws Exception {
        return menuDao.selectMenuChildTreeList(menuVo);
    }
    
    /**
    * 현재 폴더의 하위메뉴 목록을 조회한다.
    *
    * @Title : selectChildMenuList
    * @Description : 현재 폴더의 하위메뉴 목록을 조회한다
    * @param menu MenuVo객체
    * @return List<MenuVo> 하위메뉴 목록
    * @throws Exception 예외
    */
    @Override
    public List<MenuVo> selectChildMenuList(MenuVo menu) throws Exception {
        return menuDao.selectChildMenuList(menu);
    }
}