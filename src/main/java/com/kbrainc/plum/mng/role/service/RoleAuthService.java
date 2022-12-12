package com.kbrainc.plum.mng.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.role.model.RoleInstVo;
import com.kbrainc.plum.mng.role.model.RoleMenuVo;
import com.kbrainc.plum.mng.role.model.RoleRgnVo;
import com.kbrainc.plum.mng.role.model.RoleUserVo;
import com.kbrainc.plum.mng.role.model.RoleVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 역할관리 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.mng.role.service
* - RoleAuthService.java
* </pre>
*
* @ClassName   : RoleAuthService 
* @Description : 역할관리 서비스 인터페이스 
* @author      : KBRAINC
* @date        : 2021. 3. 15.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
public interface RoleAuthService {

    /**
    * 역할에 메뉴 추가.
    *
    * @Title       : insertRoleMenuAuth 
    * @Description : 역할에 메뉴 추가.
    * @param roleMenuVo RoleMenuVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    boolean insertRoleUser(RoleUserVo roleUserVoParam) throws Exception;

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
    public List<TreeItem> selectRoleTreeList(RoleVo roleVo) throws Exception;

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
    * 매핑된 사용자 기간 수정.
    * 
    * @Title : roleUserMappSave
    * @Description : 매핑된 사용자 기간 수정
    * @param roleUserVo RoleUserVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public boolean roleUserMappSave(RoleUserVo roleUserVoParam) throws Exception;

    /**
    * 역할사용자를 삭제한다.
    *
    * @Title       : deleteRoleUser 
    * @Description : 역할사용자를 삭제한다.
    * @param roleUserVo RoleUserVo객체
    * @return boolean delete여부
    * @throws Exception 예외
    */
    public boolean deleteRoleUser(RoleUserVo roleUserVoParam) throws Exception;

    
    /**
    * 역할 메뉴 수정.
    *
    * @Title       : saveAdminMenuRole 
    * @Description : 역할 메뉴 수정.
    * @param roleMenuVo RoleMenuVo객체
    * @return int 정상실행
    * @throws Exception 예외
    */
    public int saveAdminMenuRole(RoleMenuVo roleMenuVo) throws Exception;

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
    * 사용자 리스트를 구한다.
    *
    * @Title       : selectUserList 
    * @Description : 사용자 리스트를 구한다
    * @param roleUserVo RoleUserVo객체
    * @throws Exception 예외
    * @return List<RoleUserVo> 사용자목록
    */
    public List<RoleUserVo> selectUserList(RoleUserVo roleUserVo) throws Exception;
    
    /**
    * 역할 기관 맵핑목록정보 / 기관목록 조회.
    *
    * @Title : selectRoleInstInfo
    * @Description : 역할 기관 맵핑목록정보 / 기관목록 조회
    * @param param Map타입의 인자
    * @return Map<String,Object> 역할/전체 기관 목록 정보 
    * @throws Exception 예외
    */
    public Map<String, Object> selectRoleInstInfo(Map<String, Object> param) throws Exception;
    
    /**
    * 역할 기관 저장.
    *
    * @Title : saveInstRole
    * @Description : 역할 기관 저장 
    * @param roleInstVo RoleInstVo객체
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void saveInstRole(RoleInstVo roleInstVo) throws Exception;
    
    /**
    * 역할 지역 맵핑목록정보 / 지역목록 조회.
    *
    * @Title : selectRoleRgnInfo
    * @Description : 역할 지역 맵핑목록정보 / 지역목록 조회
    * @param param Map타입의 인자
    * @return Map<String,Object> 역할/전체 지역 목록 정보 
    * @throws Exception 예외
    */
    public Map<String, Object> selectRoleRgnInfo(Map<String, Object> param) throws Exception;
    
    /**
    * 역할 기관 저장.
    *
    * @Title : saveRgnRole
    * @Description : 역할 지역 저장 
    * @param roleRgnVo RoleRgnVo객체
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void saveRgnRole(RoleRgnVo roleRgnVo) throws Exception;
}