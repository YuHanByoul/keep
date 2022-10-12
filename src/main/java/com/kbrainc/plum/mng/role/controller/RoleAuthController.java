package com.kbrainc.plum.mng.role.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.role.model.RoleMenuVo;
import com.kbrainc.plum.mng.role.model.RoleUserVo;
import com.kbrainc.plum.mng.role.model.RoleVo;
import com.kbrainc.plum.mng.role.service.RoleAuthService;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.lib.tree.TreeUtil;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResAuthService;

/**
* 
* 역할관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.role.controller
* - RoleAuthController.java
* </pre> 
*
* @ClassName : RoleAuthController
* @Description : 역할관리 컨트롤러
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Controller
public class RoleAuthController {

    @Autowired
    private RoleAuthService roleAuthService;
    
    @Autowired
    private ResAuthService resAuthService;

    /**
    * 역할관리 메인 페이지(Admin용).
    *
    * @Title       : roleMainFormForAdmin 
    * @Description : 역할관리 메인 페이지(Admin용)
    * @param model Model객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleMainFormForAdmin.html")
    public String roleMainFormForAdmin(ModelMap model) throws Exception {
        model.addAttribute("rolelevel", "A");
        return "mng/role/roleMngMain";
    }
    
    /**
    * 역할관리 메인 페이지(User용).
    *
    * @Title       : roleMainFormForUser 
    * @Description : 역할관리 메인 페이지(User용)
    * @param model Model객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleMainFormForUser.html")
    public String roleMainFormForUser(ModelMap model) throws Exception {
        model.addAttribute("rolelevel", "U");
        return "mng/role/roleMngMain";
    }

    /**
    * 역할 dynatree 목록 추출.
    *
    * @Title       : roleMgntTreeList 
    * @Description : 역할 tree 리스트 불러오기
    * @param roleVo RoleVo겍체
    * @param model Model객체
    * @return TreeItem 역할 TreeItem객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleMgntTreeList.do")
    @ResponseBody
    public TreeItem roleMgntTreeList(RoleVo roleVo, ModelMap model) throws Exception {
        String treeRootTitle = "역할관리"; // 역할관리

        List<TreeItem> treeItemlist = roleAuthService.selectRoleTreeList(roleVo);

        treeItemlist.add(0, new TreeItem("", "0", treeRootTitle));
        treeItemlist = TreeUtil.reformatTreeList(treeItemlist);

        return treeItemlist.get(0);
    }

    /**
    * 역할 정의 화면.
    *
    * @Title       : defineRoleForm 
    * @Description : 역할 정의 화면
    * @param paramRoleVo RoleVo겍체
    * @param model Model객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/defineRole.html")
    public String defineRoleForm(RoleVo paramRoleVo, ModelMap model) throws Exception {
        model.addAttribute("se_cd", paramRoleVo.getSeCd());

        if (com.kbrainc.plum.rte.util.CommonUtil.isNotEmpty(paramRoleVo.getRoleid())) {
            model.addAttribute("roleid", paramRoleVo.getRoleid());
        }

        return "mng/role/fragments/defineRole";
    }

    /**
    * 사용자 매핑 페이지.
    *
    * @Title       : roleMgntUserMappingForm 
    * @Description : 사용자역할_사용자 매핑 페이지 이동
    * @param paramRoleVo RoleVo객체
    * @param model Model객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/mappingUserForm.html")
    public String roleMgntUserMappingForm(RoleVo paramRoleVo, ModelMap model) throws Exception {
        model.addAttribute("se_cd", paramRoleVo.getSeCd());

        if (com.kbrainc.plum.rte.util.CommonUtil.isNotEmpty(paramRoleVo.getRoleid())) {
            model.addAttribute("roleid", paramRoleVo.getRoleid());
        }

        return "mng/role/fragments/mappingUser";
    }

    /**
    * 역할 상세정보.
    * 
    * @Title : roleDetailInfo
    * @Description : 역할 상세정보
    * @param commandMap 요청파라미터맵
    * @param model 모델객체
    * @return Map<String, Object> 응답Map객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleDetailInfo.do")
    @ResponseBody
    public Map<String, Object> roleDetailInfo(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
        Map<String, Object> map = roleAuthService.selectRoleInfo(commandMap);
        return map;
    }

    /**
    * 역할 등록/수정.
    * 
    * @Title : roleRegistInfo
    * @Description : 역할 등록/수정
    * @param user 사용자세션정보
    * @param roleVo 요청파라미터
    * @param model  모델객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleRegistInfo.do")
    @ResponseBody
    public Map<String, String> roleRegistInfo(@UserInfo UserVo user, RoleVo roleVo, ModelMap model) throws Exception {
        Map<String, String> response = new HashMap<String, String>();
        String mode = (String) roleVo.getMode();
        String msg = "";

        try {
            roleVo.setUpdtuserid(Integer.parseInt(user.getUserid()));
            roleVo.setReguserid(Integer.parseInt(user.getUserid()));
        } catch (NullPointerException ex) {
            throw new BadCredentialsException("Login Error !!");
        }

        boolean retVal = false;
        if (mode.equals("W") || mode.equals("W2")) {
            retVal = roleAuthService.insertRoleInfo(roleVo);
        } else {
            retVal = roleAuthService.updateRoleInfo(roleVo);
        }
        if (retVal) {
            if (mode.equals("W") || mode.equals("W2")) {
                msg = "등록하였습니다."; // 등록하였습니다.
            } else {
                msg = "수정하였습니다."; // 수정하였습니다.
            }

            response.put("result", Constant.REST_API_RESULT_SUCCESS);
            response.put("msg", msg);
        } else {
            if (mode.equals("W") || mode.equals("W2")) {
                msg = "등록에 실패하였습니다."; // 등록에 실패하였습니다.
            } else {
                msg = "수정에 실패하였습니다."; // 수정에 실패하였습니다.
            }
            response.put("result", Constant.REST_API_RESULT_FAIL);
            response.put("msg", msg);
        }
        return response;
    }

    /**
    * 역할 사용자 추가.
    *
    * @Title : insertRoleUser
    * @Description : 역할 사용자 추가.
    * @param user 사용자세션정보
    * @param roleUserVoParam RoleUserVo객체
    * @return Map String,String
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/insertRoleUser.do")
    @ResponseBody
    public Map<String, String> insertRoleUser(@UserInfo UserVo user, @RequestBody RoleUserVo roleUserVoParam) throws Exception {
        Map<String, String> response = new HashMap<String, String>();
        roleUserVoParam.setReguserid(user.getUserid());

        if (roleAuthService.insertRoleUser(roleUserVoParam)) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }

        return response;
    }

    /**
    * 역할 사용자 다수 추가.
    *
    * @Title : insertRoleUserList
    * @Description : 역할 사용자 다수 추가.
    * @param user 사용자세션정보
    * @param roleUserVoList List<RoleUserVo>객체
    * @return List<Object> 처리결과
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/insertRoleUserList.do")
    @ResponseBody
    public List<Object> insertRoleUserList(@UserInfo UserVo user, @RequestBody List<RoleUserVo> roleUserVoList) throws Exception {
        List<Object> response = new ArrayList<Object>();
        response.add(Constant.REST_API_RESULT_SUCCESS);

        for (RoleUserVo roleUserVo : roleUserVoList) {
            response.add(insertRoleUser(user, roleUserVo));
        }

        return response;
    }

    /**
    * Role User 업데이트.
    *
    * @Title       : roleUserMappSave 
    * @Description : Role User 업데이트
    * @param user 사용자세션정보
    * @param roleUserVo RoleUserVo객체
    * @return Map<String,String> 응답결과Map객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleUserMappSave.do")
    @ResponseBody
    public Map<String, String> roleUserMappSave(@UserInfo UserVo user, @RequestBody RoleUserVo roleUserVo) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        roleUserVo.setUpdtuserid(user.getUserid());
        boolean retVal = roleAuthService.roleUserMappSave(roleUserVo);
        map.put("result", (retVal) ? Constant.REST_API_RESULT_SUCCESS : Constant.REST_API_RESULT_FAIL);

        return map;
    }

    /**
    * 역할 사용자 삭제.
    *
    * @Title : deleteRoleUser
    * @Description : 역할 사용자 삭제
    * @param user 사용자세션객체
    * @param roleUserVo RoleVo객체
    * @return Map<String,String> 응답결과Map객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/deleteRoleUser.do")
    @ResponseBody
    public Map<String, String> deleteRoleUser(@UserInfo UserVo user, @RequestBody RoleUserVo roleUserVo) throws Exception {
        Map<String, String> response = new HashMap<String, String>();

        if (user == null) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        } else {
            response.put("result", (roleAuthService.deleteRoleUser(roleUserVo)) ? Constant.REST_API_RESULT_SUCCESS : Constant.REST_API_RESULT_FAIL);
        }

        return response;
    }

    /**
    * 관리자역할 권한설정_메뉴 페이지.
    * 
    * @Title : adminRoleMenuForm
    * @Description : 관리자역할 권한설정_메뉴 페이지
    * @param commandMap 요청파라미터맵
    * @param model      모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/configRoleMenuForm.html")
    public String adminRoleMenuForm(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
        if (!"".equals(commandMap.get("role_id")) && commandMap.get("role_id") != null) {
            model.addAttribute("role_id", commandMap.get("role_id").toString());
        }
        model.addAttribute("se_cd", commandMap.get("se_cd"));
        
        return "mng/role/fragments/configMenu";
    }

    /**
    * 관리자역할_메뉴 트리.
    * 
    * @Title : roleMgntMenuTreeList
    * @Description : 관리자역할_메뉴 트리
    * @param commandMap 요청파라미터맵
    * @param model      모델객체
    * @return TreeItem 관리자역할 메뉴트리
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleMgntMenuTreeList.do")
    @ResponseBody
    public TreeItem roleMgntMenuTreeList(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
        String roleid = (String) commandMap.get("roleid");

        // 최상위 역할 확인
        String topyn = roleAuthService.selectToproleidyn(roleid);
        commandMap.put("topyn", topyn);

        TreeItem root = new TreeItem("", "0", "");

        if (com.kbrainc.plum.rte.util.CommonUtil.isEmpty(commandMap.get("classrm_nm"))) {
            root.setTitle(roleAuthService.selectRoleTreeSitenm(commandMap.get("siteid").toString()));
        } else {
            root.setTitle(commandMap.get("classrm_nm").toString());
        }
        root.setExt1("Y");
        List<TreeItem> treeItemlist = roleAuthService.selectRoleMenuTreeList(commandMap);
        treeItemlist.add(0, root);
        treeItemlist = TreeUtil.reformatTreeList(treeItemlist);

        return treeItemlist.get(0);
    }

    /**
    * 역할 메뉴 수정.
    *
    * @Title : saveAdminMenuRole
    * @Description : 역할 메뉴 수정.
    * @param user 사용자세션정보
    * @param roleMenuVo RoleMenuVo객체
    * @return Map<String, Object> 응답결과Map객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/saveMenuRole.do")
    @ResponseBody
    public Map<String, Object> saveMenuRole(@UserInfo UserVo user, RoleMenuVo roleMenuVo) throws Exception {
        int retVal = 0;
        roleMenuVo.setReguserid(user.getUserid());

        // 저장
        retVal = roleAuthService.saveAdminMenuRole(roleMenuVo);
        Map<String, Object> response = new HashMap<String, Object>();

        if (retVal > 0) {
            response.put("msg", "저장하였습니다.");
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
            resAuthService.removeCacheForAuth();
        } else {
            response.put("msg", "저장에 실패하였습니다.");
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }

        return response;
    }

    /**
    * 사용자 매핑 리스트 불러오기.
    * 
    * @Title : roleMgntUserMapped
    * @Description : 사용자 매핑 리스트 불러오기
    * @param roleUserParam RoleUserVo객체
    * @param model 모델객체
    * @throws Exception 예외
    * @return Map<String,Object> 응답결과Map객체
    */
    @RequestMapping(value = "/mng/roleauth/roleMgntUserMapped.do")
    @ResponseBody
    public Map<String, Object> roleMgntUserMapped(RoleUserVo roleUserParam, ModelMap model) throws Exception {
        List<RoleUserVo> list = roleAuthService.selectRoleUserMappedList(roleUserParam);
        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }
        response.put("list", list);

        return response;
    }

    /**
    * 역할 설정 사용자 추가 팝업.
    *
    * @Title : roleUserMappPopup
    * @Description : 역할 설정 사용자 추가 팝업
    * @param roleUserVo RoleUserVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/roleUserMappPopup.html")
    public String roleUserMappPopup(RoleUserVo roleUserVo, @RequestParam("roleid") String roleid, ModelMap model) throws Exception {
        model.addAttribute("roleid", roleid);
        return "mng/role/roleUserMappSearchPopup";
    }

    /**
    * 사용자 목록을 출력한다.
    *
    * @Title : selectUserList
    * @Description : 사용자 목록을 출력한다
    * @param roleUserVo RoleUserVo객체
    * @param model 모델객체
    * @return Map<String,Object> 응답결과Map객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/selectUserList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(RoleUserVo roleUserVo, ModelMap model) throws Exception {
        List<RoleUserVo> list = roleAuthService.selectUserList(roleUserVo);
        Map<String, Object> response = new HashMap<String, Object>();

        // JsGrid를 위한 출력.
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }
        response.put("list", list);

        return response;
    }

    /**
    * 역할별 사이트 리스트를 가져온다.
    *
    * @Title       : selectRoleSiteList 
    * @Description : 역할별 사이트 리스트를 가져온다.
    * @param roleVo RoleVo객체
    * @return Map<String,Object> 사이트목록
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/roleauth/selectRoleSiteList.do")
    @ResponseBody
    public Map<String, Object> selectRoleSiteList(RoleVo roleVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        List<SiteVo> list = roleAuthService.selectRoleSiteList(roleVo);
        response.put("data", list);
        return response;
    }
}