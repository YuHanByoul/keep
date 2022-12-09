package com.kbrainc.plum.mng.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.role.model.RoleAuthDao;
import com.kbrainc.plum.mng.role.model.RoleMenuVo;
import com.kbrainc.plum.mng.role.model.RoleUserVo;
import com.kbrainc.plum.mng.role.model.RoleVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;

/**
* 역할관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.role.service
* - RoleAuthServiceImpl.java
* </pre>
*
* @ClassName   : RoleAuthServiceImpl 
* @Description : 역할관리 서비스 구현 클래스 
* @author      : KBRAINC
* @date        : 2021. 3. 15.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Service("mng.roleauth.roleAuthService")
public class RoleAuthServiceImpl extends PlumAbstractServiceImpl implements RoleAuthService {

    @Autowired
    private RoleAuthDao roleAuthDao;

    /**
    * 역할에 메뉴 추가.
    *
    * @Title       : insertRoleMenuAuth 
    * @Description : 역할에 메뉴 추가.
    * @param roleMenuVo RoleMenuVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    @Override
    public boolean insertRoleUser(RoleUserVo roleUserVo) throws Exception {
        boolean ret = false;

        try {
            ret = roleAuthDao.insertRoleUser(roleUserVo);
        } catch (DuplicateKeyException e) {
            ret = roleAuthDao.updateRoleUser(roleUserVo);
        }
        return ret;
    }

    /**
    * 역할사용자를 삭제한다.
    *
    * @Title       : deleteRoleUser 
    * @Description : 역할사용자를 삭제한다.
    * @param roleUserVo RoleUserVo객체
    * @return boolean delete여부
    * @throws Exception 예외
    */
    @Override
    public boolean deleteRoleUser(RoleUserVo roleUserVo) throws Exception {
        return roleAuthDao.deleteRoleUser(roleUserVo);
    }

    /**
    * 역할 메뉴 수정.
    *
    * @Title       : saveAdminMenuRole 
    * @Description : 역할 메뉴 수정.
    * @param roleMenuVo RoleMenuVo객체
    * @return int 정상실행
    * @throws Exception 예외
    */
    @Override
    public int saveAdminMenuRole(RoleMenuVo roleMenuVo) throws Exception {
        // 기존 메뉴 매핑 제거
        String[] delId = StringUtil.nvl(roleMenuVo.getMenuDel()).split(",");
        if (!delId[0].equals("")) {
            for (int i = 0; i < delId.length; i++) {
                roleMenuVo.setMenuid(delId[i]);

                // 하위 권한 및 자신의 메뉴권한에 삭제
                roleAuthDao.deleteAuthMenuMpp(roleMenuVo);
            }
        }

        // 메뉴 매핑 추가
        String[] addId = StringUtil.nvl(roleMenuVo.getMenuAdd()).split(",");
        if (!addId[0].equals("")) {
            for (int i = 0; i < addId.length; i++) {
                roleMenuVo.setMenuid(addId[i]);

                // 역할별 메뉴 권한 추가
                roleAuthDao.insertRoleMenuAuth(roleMenuVo);
            }
        }
        
        return 1;
    }

    /**
    * [공통] 최상위 역할 확인.
    * 
    * @Title : selectToproleidyn
    * @Description : [공통] 최상위 역할 확인
    * @param roleid 역할ID
    * @return String 최상위 역할 확인값
    * @throws Exception 예외
    */
    @Override
    public String selectToproleidyn(String roleid) throws Exception {
        return roleAuthDao.selectToproleidyn(roleid);
    }

    /**
    * 역할 tree 리스트.
    * 
    * @Title : selectRoleTreeList
    * @Description : 역할 tree 리스트
    * @param param Map타입의 인자
    * @return List Map String,Object 역할 트리 목록
    * @throws Exception 예외
    */
    @Override
    public List<TreeItem> selectRoleTreeList(RoleVo param) throws Exception {
        return roleAuthDao.selectRoleTreeList(param);
    }

    /**
    * [공통] 사이트명 구하기.
    * 
    * @Title : selectRoleTreeSitenm
    * @Description : [공통] 사이트명 구하기
    * @param siteid 사이트아이디
    * @return String 사이트명
    * @throws Exception 예외
    */
    @Override
    public String selectRoleTreeSitenm(String siteid) throws Exception {
        return roleAuthDao.selectRoleTreeSitenm(siteid);
    }

    /**
    * 역할 상세정보.
    * 
    * @Title : selectRoleInfo
    * @Description : 역할 상세정보
    * @param param Map타입의 인자
    * @return Map String,Object 역할 상세정보
    * @throws Exception 예외
    */
    @Override
    public Map<String, Object> selectRoleInfo(Map<String, Object> param) throws Exception {
        return roleAuthDao.selectRoleInfo(param);
    }

    /**
    * 역할 등록.
    * 
    * @Title : insertRoleInfo
    * @Description : 역할 등록
    * @param param Map타입의 인자
    * @return int 역할ID
    * @throws Exception 예외
    */
    @Override
    public boolean insertRoleInfo(RoleVo param) throws Exception {
        return roleAuthDao.insertRoleInfo(param);
    }

    /**
    * 역할 상세 수정.
    * 
    * @Title : updateRoleInfo
    * @Description : 역할 상세 수정
    * @param param Map타입의 인자
    * @return int update로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public boolean updateRoleInfo(RoleVo param) throws Exception {
        roleAuthDao.updateRoleAuth(param); // 하위역할 수정
        return roleAuthDao.updateRoleInfo(param);
    }

    /**
    * 역할에 매핑된 사용자 리스트.
    * 
    * @Title : selectRoleUserMappedList
    * @Description : 역할에 매핑된 사용자 리스트
    * @param roleUserParam Map타입의 인자
    * @return 사용자 목록
    * @throws Exception 예외
    */
    @Override
    public List<RoleUserVo> selectRoleUserMappedList(RoleUserVo roleUserParam) throws Exception {
        return roleAuthDao.selectRoleUserMappedList(roleUserParam);
    }

    /**
    * 매핑된 사용자 기간 수정.
    * 
    * @Title : roleUserMappSave
    * @Description : 매핑된 사용자 기간 수정
    * @param roleUserVo RoleUserVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    @Override
    public boolean roleUserMappSave(RoleUserVo roleUserVo) throws Exception {
        return roleAuthDao.updateRoleUserMappedTerm(roleUserVo);
    }

    /**
    * 관리자역할_메뉴 최상위역할 트리.
    * 
    * @Title : selectRoleMenuTreeList
    * @Description : 관리자역할_메뉴 최상위역할 트리
    * @param param Map타입의 인자
    * @return List Map String,Object 트리 목록
    * @throws Exception 예외
    */
    @Override
    public List<TreeItem> selectRoleMenuTreeList(Map<String, Object> param) throws Exception {
        return roleAuthDao.selectRoleMenuTreeList(param);
    }

    /**
    * 역할별 사이트 리스트를 가져온다.
    *
    * @Title       : selectRoleSiteList 
    * @Description : 역할별 사이트 리스트를 가져온다.
    * @param roleVo RoleVo객체
    * @return List<SiteVo> 사이트목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectRoleSiteList(RoleVo roleVo) throws Exception {
        return roleAuthDao.selectRoleSiteList(roleVo);
    }
    
    /**
    * 사용자 리스트를 구한다.
    *
    * @Title       : selectUserList 
    * @Description : 사용자 리스트를 구한다
    * @param roleUserVo RoleUserVo객체
    * @throws Exception 예외
    * @return List<RoleUserVo> 사용자목록
    */
    public List<RoleUserVo> selectUserList(RoleUserVo roleUserVo) throws Exception {
        return roleAuthDao.selectUserList(roleUserVo);
    }
}