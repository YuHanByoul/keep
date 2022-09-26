package com.kbrainc.plum.mng.menu.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.menu.model.MenuVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 
* 메뉴 관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.menu.service
* - MenuService.java
* </pre> 
*
* @ClassName : MenuService
* @Description : 메뉴 관리 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
public interface MenuService {

    /**
    * 사이트별 메뉴구조 트리리스트
    * 
    * @Title : selectMenuTreeSearchList
    * @Description : 사이트별 메뉴구조 트리리스트
    * @param param Map타입의 인자
    * @return List<TreeItem> 메뉴트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectMenuTreeSearchList(Map param) throws Exception;

    /**
    * 메뉴 상세정보
    * 
    * @Title : selectMenuDetailInfo
    * @Description : 메뉴 상세정보
    * @param menu 메뉴 도메인 클래스
    * @return Menu 메뉴 상세정보
    * @throws Exception 예외
    */
    public MenuVo selectMenuDetailInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴 정보 직접등록
    * 
    * @Title : insertMenuDirectInsertInfo
    * @Description : 메뉴 정보 직접등록
    * @param menu 메뉴 도메인 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int insertMenuDirectInsertInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴정보 수정
    * 
    * @Title : updateMenuInfo
    * @Description : 메뉴정보 수정
    * @param menu 메뉴 클래스 도메인
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int updateMenuInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴 정보 삭제
    * 
    * @Title : deleteMenuInfo
    * @Description : 메뉴 정보 삭제
    * @param menu 메뉴 도메인 클래스
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int deleteMenuInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴tree 저장
    * 
    * @Title : insertMenuTreeList
    * @Description : 메뉴tree 저장
    * @param param Map타입의 인자
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int insertMenuTreeList(Map param, UserVo user) throws Exception;

    /**
    * 메뉴tree 순서 변경
    * 
    * @Title : updateMenuTreeReorder
    * @Description : 메뉴tree 순서 변경
    * @param param Map타입의 인자
    * @return int 처리성공값
    * @throws Exception 예외
    */
    public int updateMenuTreeReorder(Map param) throws Exception;

    /**
    * 하위메뉴 리스트
    * 
    * @Title : selectMenuChildTreeList
    * @Description : 하위메뉴 리스트
    * @param menu 메뉴 도메인 클래스
    * @return List<Menu> 하위메뉴 목록
    * @throws Exception 예외
    */
    public List<MenuVo> selectMenuChildTreeList(MenuVo menu) throws Exception;
}