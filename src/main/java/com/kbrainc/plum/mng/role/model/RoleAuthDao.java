package com.kbrainc.plum.mng.role.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
* 역할관리 DAO 클래스.
*
* <pre>
* com.kbrainc.plum.mng.role.model
* - RoleAuthDao.java
* </pre>
*
* @ClassName   : RoleAuthDao 
* @Description : 역할관리 DAO 클래스 
* @author      : KBRAINC
* @date        : 2021. 3. 15.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Mapper
public interface RoleAuthDao {

    /**
    * 역할에 메뉴 추가.
    *
    * @Title       : insertRoleMenuAuth 
    * @Description : 역할에 메뉴 추가.
    * @param roleMenuVo RoleMenuVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertRoleMenuAuth(RoleMenuVo roleMenuVo) throws Exception;

    /**
    * 역할에 매핑된 메뉴권한 삭제.
    *
    * @Title       : deleteAuthMenuMpp 
    * @Description : 역할에 매핑된 메뉴권한 삭제.
    * @param roleMenuVo RoleMenuVo객체
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteAuthMenuMpp(RoleMenuVo roleMenuVo) throws Exception;

    /**
    * [공통] 최상위 역할 확인.
    * 
    * @Title : selectToproleidyn
    * @Description : [공통] 최상위 역할 확인
    * @param roleid 역할ID
    * @return String 최상위 역할 확인값
    * @throws Exception 예외
    */
    public String selectToproleidyn(String roleid) throws Exception;

    /**
    * [공통] 사이트명 구하기.
    * 
    * @Title : selectRoleTreeSitenm
    * @Description : [공통] 사이트명 구하기
    * @param siteid 사이트아이디
    * @return String 사이트명
    * @throws Exception 예외
    */
    public String selectRoleTreeSitenm(String siteid) throws Exception;

    /**
    * 역할 tree 리스트.
    * 
    * @Title : selectRoleTreeList
    * @Description : 역할 tree 리스트
    * @param param Map타입의 인자
    * @return List Map String,Object 역할 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectRoleTreeList(RoleVo param) throws Exception;

    /**
    * 역할 상세정보.
    * 
    * @Title : selectRoleInfo
    * @Description : 역할 상세정보
    * @param param Map타입의 인자
    * @return Map String,Object 역할 상세정보
    * @throws Exception 예외
    */
    public Map<String, Object> selectRoleInfo(Map<String, Object> param) throws Exception;

    /**
    * 역할 등록.
    * 
    * @Title : insertRoleInfo
    * @Description : 역할 등록
    * @param param Map타입의 인자
    * @return int 역할ID
    * @throws Exception 예외
    */
    public boolean insertRoleInfo(RoleVo param) throws Exception;

    /**
    * 역할 상세 수정.
    * 
    * @Title : updateRoleInfo
    * @Description : 역할 상세 수정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public boolean updateRoleInfo(RoleVo param) throws Exception;

    /**
    * 역할 수정시 하위 역할 수정.
    * 
    * @Title : updateRoleAuth
    * @Description : 역할 수정시 하위 역할 수정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updateRoleAuth(RoleVo param) throws Exception;

    /**
    * 역할에 매핑된 사용자 리스트.
    * 
    * @Title : selectRoleUserMappedList
    * @Description : 역할에 매핑된 사용자 리스트
    * @param roleUserParam Map타입의 인자
    * @return 사용자 목록
    * @throws Exception 예외
    */
    public List<RoleUserVo> selectRoleUserMappedList(RoleUserVo roleUserParam) throws Exception;

    /**
    * Role User를 업데이트 한다.
    * 
    * @Title : updateRoleUserMappedTerm
    * @Description : 역할에 매핑된 사용자의 권한 기간을 수정
    * @param roleUserVo Map타입의 인자
    * @return boolean update여부
    * @throws Exception 예외
    */
    public boolean updateRoleUserMappedTerm(RoleUserVo roleUserVo) throws Exception;

    /**
    * 관리자역할_메뉴 최상위역할 트리.
    * 
    * @Title : selectRoleMenuTreeList
    * @Description : 관리자역할_메뉴 최상위역할 트리
    * @param param Map타입의 인자
    * @return List Map String,Object 트리 목록
    * @throws Exception 예외
    */
    public List<TreeItem> selectRoleMenuTreeList(Map<String, Object> param) throws Exception;
    
    /**
    * 역할별 사이트 리스트를 가져온다.
    *
    * @Title       : selectRoleSiteList 
    * @Description : 역할별 사이트 리스트를 가져온다.
    * @param roleVo RoleVo객체
    * @return List<SiteVo> 사이트목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectRoleSiteList(RoleVo roleVo) throws Exception;
    
    /**
    * 역할사용자를 추가한다.
    *
    * @Title       : insertRoleUser 
    * @Description : 역할사용자를 추가한다.
    * @param roleUserVo RoleUserVo객체
    * @return boolean insert여부
    * @throws Exception 예외
    */
    boolean insertRoleUser(RoleUserVo roleUserVo) throws Exception;

    /**
    * 역할사용자를 수정한다.
    *
    * @Title       : updateRoleUser 
    * @Description : 역할사용자를 수정한다. 
    * @param roleUserVo RoleUserVo객체
    * @return boolean update여부
    * @throws Exception 예외
    */
    boolean updateRoleUser(RoleUserVo roleUserVo) throws Exception;

    /**
    * 역할사용자를 삭제한다.
    *
    * @Title       : deleteRoleUser 
    * @Description : 역할사용자를 삭제한다.
    * @param roleUserVo RoleUserVo객체
    * @return boolean delete여부
    * @throws Exception 예외
    */
    boolean deleteRoleUser(RoleUserVo roleUserVo) throws Exception;
    
    /**
    * 사용자 리스트를 구한다.
    *
    * @Title       : selectUserList 
    * @Description : 사용자 리스트를 구한다
    * @param roleUserVo RoleUserVo객체
    * @throws Exception 예외
    * @return List<RoleUserVo> 사용자목록
    */
    public List<RoleUserVo> selectUserList(RoleUserVo roleUserVo) throws Exception;
}