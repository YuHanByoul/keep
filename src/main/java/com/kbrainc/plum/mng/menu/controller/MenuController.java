package com.kbrainc.plum.mng.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.menu.model.MenuVo;
import com.kbrainc.plum.mng.menu.service.MenuService;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.lib.tree.TreeUtil;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResMenuService;

/**
* 
* 메뉴관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.menu.controller
* - MenuController.java
* </pre> 
*
* @ClassName : MenuController
* @Description : 메뉴관리 컨트롤러
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Controller
public class MenuController {

    @Resource(name = "mng.menu.menuService")
    private MenuService menuService;
    
    @Autowired
    private ResMenuService resMenuService;

    /**
    * 메뉴 관리 화면 이동.
    * 
    * @Title : menuMgntForm
    * @Description : 메뉴 관리 화면 이동
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/menu/menuMgntForm.html")
    public String menuMgntForm() throws Exception {
        return "mng/menu/menuMngtForm";
    }

    /**
    * 사이트별 메뉴 트리 검색.
    * 
    * @Title : menuTreeSearchList
    * @Description : 사이트별 메뉴 트리 검색
    * @param commandMap 요청파라미터맵
    * @return TreeItem 특정사이트의 메뉴트리
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/menu/menuTreeSearchList.do", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody TreeItem menuTreeSearchList(@RequestParam Map<String, Object> commandMap) throws Exception {

        TreeItem rootMenu = new TreeItem();
        rootMenu.setPkey("");
        rootMenu.setKey("0");
        if (!"".equals(commandMap.get("site_nm"))) {
            rootMenu.setTitle((String) commandMap.get("site_nm"));
        }

        List<TreeItem> treeItemlist = menuService.selectMenuTreeSearchList(commandMap);
        treeItemlist.add(0, rootMenu);

        treeItemlist = TreeUtil.reformatTreeList(treeItemlist);

        return treeItemlist.get(0);
    }

    /**
    * 메뉴상세정보.
    * 
    * @Title : getMenuInfo
    * @Description : 메뉴상세정보
    * @param menu 메뉴 도메인 클래스
    * @return ModelAndView
    * @throws Exception 예외
    */
    @RequestMapping("/mng/menu/getMenuInfo.do")
    public @ResponseBody MenuVo getMenuInfo(MenuVo menu) throws Exception {
        MenuVo data = menuService.selectMenuDetailInfo(menu);

        return data;
    }

    /**
    * 메뉴 정보 수정.
    * 
    * @Title : menuRegistInfo
    * @Description : 메뉴 정보 수정
    * @param menu 메뉴 도메인 클래스
    * @param bindingResult 유효성검증결과
    * @param user 로그인사용자정보
    * @throws Exception 예외
    * @return ModelAndView
    */
    @RequestMapping("/mng/menu/menuRegistInfo.do")
    public @ResponseBody Map<String, Object> menuRegistInfo(@Valid MenuVo menu, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        int retVal = 0;
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        menu.setUser(user);
        // 파일이 수정이 되었다면 기존것 삭제후 새로운파일 id 입력
        if ("W".equals(menu.getMode()) || "C".equals(menu.getMode())) { // 직접 등록
            retVal = menuService.insertMenuDirectInsertInfo(menu);
        } else { // 기존 메뉴 수정
            retVal = menuService.updateMenuInfo(menu);
        }
        
        if (retVal > 0) {
            if (!"U".equals(menu.getMode())) {
                map.put("msg", "저장에 성공하였습니다.");// 저장에 성공하였습니다.
            } else {
                map.put("msg", "수정에 성공하였습니다.");// 수정에 성공하였습니다.
            }
            resMenuService.removeCacheForSiteid(String.valueOf(menu.getSiteid()));
        } else {
            if (!"U".equals(menu.getMode())) {
                map.put("msg", "저장에 실패하였습니다.");// 저장에 실패하였습니다.
            } else {
                map.put("msg", "수정에 실패하였습니다.");// 수정에 실패하였습니다.
            }
        }
        return map;
    }

    /**
    * 메뉴 정보 삭제.
    * 
    * @Title : menuDeleteInfo
    * @Description : 메뉴 정보 삭제
    * @param menu 메뉴 도메인 클래스
    * @return ModelAndView
    * @throws Exception 예외
    */
    @RequestMapping("/mng/menu/menuDeleteInfo.do")
    public @ResponseBody HashMap<String, String> menuDeleteInfo(MenuVo menu) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        int retVal = menuService.deleteMenuInfo(menu);
        if (retVal > 0) {
            map.put("msg", "삭제에 성공하였습니다.");// 삭제에 성공하였습니다.
            resMenuService.removeCacheForSiteid(String.valueOf(menu.getSiteid()));
        } else {
            map.put("msg", "삭제에 실패하였습니다.");// 삭제에 실패하였습니다.
        }
        return map;
    }

    /**
    * 메뉴tree 저장.
    * 
    * @Title : saveMenuTreeList
    * @Description : 메뉴tree 저장
    * @param commandMap 요청파라미터맵
    * @param user 로그인사용자정보
    * @return ModelAndView
    * @throws Exception 예외
    */
    @RequestMapping("/mng/menu/saveMenuTreeList.do")
    public @ResponseBody Map<String, String> saveMenuTreeList(@RequestParam Map<String, Object> commandMap, @UserInfo UserVo user) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        commandMap.put("user", user);
        int retVal = menuService.insertMenuTreeList(commandMap, user);
        if (retVal > 0) {            
            map.put("msg", "저장에 성공하였습니다.");
            resMenuService.removeCacheForSiteid(String.valueOf((String) commandMap.get("siteid")));
        } else {
            map.put("msg", "저장에 실패하였습니다.");
        }
        return map;
    }

    /**
    * 메뉴tree 순서 변경.
    * 
    * @Title : menuTreeReorder
    * @Description : 메뉴tree 순서 변경
    * @param commandMap 요청파라미터맵
    * @param user 로그인사용자정보
    * @return ModelAndView
    * @throws Exception 예외
    */
    @RequestMapping("/mng/menu/menuTreeReorder.do")
    public @ResponseBody HashMap<String, String> menuTreeReorder(@RequestParam Map<String, Object> commandMap, @UserInfo UserVo user) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        commandMap.put("user", user);
        int retVal = menuService.updateMenuTreeReorder(commandMap);
        if (retVal > 0) {
            map.put("msg", "저장에 성공하였습니다.");// 저장에 성공하였습니다.
            resMenuService.removeCacheForSiteid(String.valueOf((String) commandMap.get("siteid")));
        } else {
            map.put("msg", "저장에 실패하였습니다.");// 저장에 실패하였습니다.
        }
        return map;
    }

    /**
    * 폴더 참조 메뉴 설정 팝업.
    * 
    * @Title : refMenuIdPopup
    * @Description : 폴더 참조 메뉴 설정 팝업
    * @param menu 메뉴 도메인 클래스
    * @param map 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping("/mng/menu/refMenuIdPopup.html")
    public String refMenuIdPopup(MenuVo menu, Model model) throws Exception {
        menu.setType("P");// 프로그램만 가지고 온다.
        menu.setPtypeCd("02");// 메뉴만 가지고 온다.
        List<MenuVo> list = menuService.selectMenuChildTreeList(menu);
        model.addAttribute("underMenuList", list);
        return "mng/menu/refMenuIdPopup";
    }
}