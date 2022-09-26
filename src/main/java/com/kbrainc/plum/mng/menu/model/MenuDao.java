package com.kbrainc.plum.mng.menu.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
* 
* 메뉴관리 DAO 클래스
*
* <pre>
* com.kbrainc.plum.mng.menu.model
* - MenuDao.java
* </pre> 
*
* @ClassName : MenuDao
* @Description : 메뉴관리 DAO 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MenuDao {

    /**
    * 사이트별 메뉴구조 트리리스트
    * 
    * @Title : selectMenuTreeSearchList
    * @Description : 사이트별 메뉴구조 트리리스트
    * @param param Map타입의 인자
    * @return List<TreeItem> 메뉴구조 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectMenuTreeSearchList(Map param) throws Exception;

    /**
    * 사이트별 메뉴구조 URL검색 트리리스트
    * 
    * @Title : selectMenuTreeUrlSearchList
    * @Description : 사이트별 메뉴구조 URL검색 트리리스트
    * @param param Map타입의 인자
    * @return List<TreeItem> 메뉴트리정보 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectMenuTreeUrlSearchList(Map param) throws Exception;

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
    * 메뉴 정보 수정
    * 
    * @Title : updateMenuInfo
    * @Description : 메뉴 정보 수정
    * @param menu 메뉴 도메인 클래스
    * @throws Exception
    * @return int update로우수
    */
    public int updateMenuInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴 정보 직접등록
    * 
    * @Title : insertMenuDirectInsertInfo
    * @Description : 메뉴 정보 직접등록
    * @param menu 메뉴 도메인 클래스
    * @return int 메뉴아이디
    * @throws Exception 예외
    */
    public int insertMenuDirectInsertInfo(MenuVo menu) throws Exception;

    /**
    * 메뉴 트리순서 조정
    * 
    * @Title : updateMenuTreeOrder
    * @Description : 메뉴 트리순서 조정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuTreeOrder(Map param) throws Exception;

    /**
    * 메뉴 트리뎁스 조정
    * 
    * @Title : updateMenuTreeDepth
    * @Description : 메뉴 트리뎁스 조정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuTreeDepth(Map param) throws Exception;

    /**
    * 메뉴 정보 삭제
    * 
    * @Title : deleteMenuInfo
    * @Description : 메뉴 정보 삭제
    * @param menu 메뉴 도메인 클래스
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteMenuInfo(MenuVo menu) throws Exception;

    /**
     * 메뉴 트리 저장
     * 
     * @Title : insertMenuTreeList
     * @Description : 메뉴 트리 저장
     * @param param Map타입의 인자
     * @return int 메뉴ID
     * @throws Exception 예외
     */
    public int insertMenuTreeList(MenuVo menu) throws Exception;

    /**
    * 메뉴 정보 order 다시 조정
    * 
    * @Title : updateMenuReOrder
    * @Description : 메뉴 정보 order 다시 조정
    * @param menu 메뉴 도메인 클래스
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuReOrder(MenuVo menu) throws Exception;

    /**
    * 메뉴 트리저장시 order 조정
    * 
    * @Title : updateMenuTreeReOrder
    * @Description : 메뉴 트리저장시 order 조정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuTreeReOrder(Map param) throws Exception;

    /**
    * 메뉴 트리저장시 menu 정보 수정 new
    * 
    * @Title : updateMenuTreeInfoNew
    * @Description : 메뉴 트리저장시 menu 정보 수정 new
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuTreeInfoNew(Map param) throws Exception;

    /**
    * 메뉴 트리저장시 이전 menu 정보 수정 new
    * 
    * @Title : updateMenuPreOrder
    * @Description : 메뉴 트리저장시 이전 menu 정보 수정 new
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateMenuPreOrder(Map param) throws Exception;

    /**
    * 자식 메뉴 리스트
    * 
    * @Title : selectMenuChildTreeList
    * @Description : 자식 메뉴 리스트
    * @param menu 메뉴 도메인 클래스
    * @return List<Menu> 자식 메뉴 목록
    * @throws Exception 예외
    */
    public List<MenuVo> selectMenuChildTreeList(MenuVo menu) throws Exception;

    /**
    * 권한에 매핑된 메뉴를 삭제
    * 
    * @Title : deleteRoleMenuAuth
    * @Description : 권한에 매핑된 메뉴를 삭제
    * @param menu 메뉴 도메인 클래스
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteRoleMenuAuth(MenuVo menu) throws Exception;
}