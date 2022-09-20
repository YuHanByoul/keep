package com.kbrainc.plum.mng.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.code.model.CodeGrpVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.lib.tree.TreeUtil;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;

/**
 * 
 * 코드 관리 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.code.controller
 * - CodeController.java
 * </pre> 
 *
 * @ClassName : CodeController
 * @Description : 코드 관리 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;
    
    @Autowired
    private ResCodeService resCodeService;
    
    /**
     * 코드관리 등록 폼 이동.
     * 
     * @Title : codeRegistForm
     * @Description : 코드관리 등록 폼 이동
     * @throws Exception :
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/code/codeMgntForm.html")
    public String codeMgntForm() throws Exception {
        return "mng/code/codeMgntForm";
    }

    /**
     * 코드그룹 목록을 조회한다.
     * 
     * @Title : getCodeGrpList
     * @Description : 코드그룹 목록을 조회한다.
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return Map<String, Object> 코드그룹 리스트
     */
    @RequestMapping(value = "/mng/code/codeGrpList.do")
    @ResponseBody
    public Map<String, Object> getCodeGrpList(CodeGrpVo codeGrpVO) throws Exception {

        List<CodeGrpVo> list = codeService.selectCodeGrpList(codeGrpVO);

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
     * 코드검색 폼 이동.
     * 
     * @Title : codeSearchForm
     * @Description : 코드검색 폼 이동
     * @throws Exception :
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/code/codeSearchForm.html")
    public String codeSearchForm() throws Exception {
        return "mng/code/codeSearchForm";
    }

    /**
     * 코드 목록을 조회한다.
     * 
     * @Title : getCodeList
     * @Description : 코드 목록을 조회한다.
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return Map<String, Object> 코드 목록
     */
    @RequestMapping(value = "/mng/code/codeList.do")
    @ResponseBody
    public Map<String, Object> getCodeList(CodeVo codeVo) throws Exception {

        List<CodeVo> list = codeService.selectCodeList(codeVo);

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
     * 코드그룹 정보를 조회한다.
     * 
     * @Title : getCodeGrpInfo
     * @Description : 코드그룹 정보를 조회한다.
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return CodeGrpVO 코드그룹 정보
     */
    @RequestMapping(value = "/mng/code/codeGrpInfo.do")
    @ResponseBody
    public CodeGrpVo getCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception {

        CodeGrpVo codeGrpInfo = codeService.selectCodeGrpInfo(codeGrpVO);
        return codeGrpInfo;
    }

    /**
     * 코드그룹 등록.
     * 
     * @Title : insertCodeGrpInfo
     * @Description : 코드그룹 등록
     * @param codeGrpVO     코드그룹VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception :
     * @return Map<String, Object> 코드그룹 저장 결과
     */
    @RequestMapping(value = "/mng/code/insertCodeGrpInfo.do")
    @ResponseBody
    public Map<String, Object> insertCodeGrpInfo(@Valid CodeGrpVo codeGrpVO, BindingResult bindingResult,
            @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        codeGrpVO.setUser(user);
        
        int retVal = 0;

        // 코드그룹아이디 중복체크
        CodeGrpVo codeGrpInfo = codeService.selectCodeGrpInfo(codeGrpVO);
        if (codeGrpInfo != null) {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("resultCode", "EXIST");
            map.put("msg", "동일한 코드그룹ID가 존재합니다.");
            return map;
        }

        retVal = codeService.insertCodeGrpInfo(codeGrpVO);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    /**
     * 코드그룹 수정.
     * 
     * @Title : updateCodeGrpInfo
     * @Description : 코드그룹 수정
     * @param codeGrpVO     코드그룹VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception :
     * @return Map<String, Object> 코드그룹 수정 결과
     */
    @RequestMapping(value = "/mng/code/updateCodeGrpInfo.do")
    @ResponseBody
    public Map<String, Object> updateCodeGrpInfo(CodeGrpVo codeGrpVO, BindingResult bindingResult,
            @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        codeGrpVO.setUser(user);

        int retVal = 0;

        retVal = codeService.updateCodeGrpInfo(codeGrpVO);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
            removeCacheForCdgrp(codeGrpVO.getCdgrpid());
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }

    /**
     * 코드 정보를 조회한다.
     * 
     * @Title : getCodeInfo
     * @Description : 코드 정보를 조회한다.
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return CodeVo 코드 정보
     */
    @RequestMapping(value = "/mng/code/codeInfo.do")
    @ResponseBody
    public CodeVo getCodeInfo(CodeVo codeVo) throws Exception {

        CodeVo codeInfo = codeService.selectCodeInfo(codeVo);
        return codeInfo;
    }

    /**
     * 코드 트리.
     * 
     * @Title : selectCodeTreeList
     * @Description : 코드 트리
     * @param codeGrpVO :
     * @throws Exception :
     * @return List<TreeItem> 코드 트리 정보 목록
     */
    @RequestMapping(value = "/mng/code/selectCodeTreeList.do")
    @ResponseBody
    public List<TreeItem> selectCodeTreeList(CodeGrpVo codeGrpVO) throws Exception {


        TreeItem rootItem = new TreeItem();
        rootItem.setPkey("");
        rootItem.setKey("0");
        rootItem.setTitle(codeGrpVO.getCdgrp_nm());

        List<TreeItem> treeItemList = codeService.selectCodeTreeList(codeGrpVO);
        treeItemList.add(0, rootItem);
        treeItemList = TreeUtil.reformatTreeList(treeItemList);
        return treeItemList;
    }

    /**
     * 코드tree 순서 변경.
     * 
     * @Title : codeTreeReorder
     * @Description : 코드tree 순서 변경
     * @param codeVo 코드VO 클래스
     * @param user   로그인사용자정보
     * @throws Exception :
     * @return Map<String, String> 코드tree 순서 변경 결과
     */
    @RequestMapping("/mng/code/codeTreeReorder.do")
    @ResponseBody
    public Map<String, String> codeTreeReorder(CodeVo codeVo, @UserInfo UserVo user) throws Exception {
        codeVo.setUser(user);
        Map<String, String> map = new HashMap<String, String>();

        int retVal = codeService.updateCodeTreeReorder(codeVo);

        if (retVal > 0) {
            map.put("msg", "저장에 성공하였습니다.");
            removeCacheForCdgrp(codeVo.getCdgrpid());
        } else {
            map.put("msg", "저장에 실패하였습니다");
        }
        return map;
    }

    /**
     * 코드 등록.
     * 
     * @Title : insertCodeInfo
     * @Description : 코드 등록
     * @param codeVo        코드VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception :
     * @return Map<String, Object> 코드 저장 결과
     */
    @RequestMapping(value = "/mng/code/insertCodeInfo.do")
    @ResponseBody
    public Map<String, Object> insertCodeInfo(@Valid CodeVo codeVo, BindingResult bindingResult, @UserInfo UserVo user)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        codeVo.setUser(user);

        int retVal = 0;

        // 코드 중복체크
        CodeVo codeInfo = codeService.selectCodeInfo(codeVo);
        if (codeInfo != null) {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("resultCode", "EXIST");
            map.put("msg", "동일한 코드가 존재합니다.");
            return map;
        }

        retVal = codeService.insertCodeInfo(codeVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
            removeCacheForCdgrp(codeVo.getCdgrpid());
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    /**
     * 코드 수정.
     * 
     * @Title : updateCodeInfo
     * @Description : 코드 수정
     * @param codeVo        코드VO 클래스
     * @param bindingResult 유효성검증결과
     * @param user          로그인사용자정보
     * @throws Exception :
     * @return Map<String, Object> 코드 수정 결과
     */
    @RequestMapping(value = "/mng/code/updateCodeInfo.do")
    @ResponseBody
    public Map<String, Object> updateCodeInfo(CodeVo codeVo, BindingResult bindingResult, @UserInfo UserVo user)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        codeVo.setUser(user);

        int retVal = 0;

        retVal = codeService.updateCodeInfo(codeVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
            removeCacheForCdgrp(codeVo.getCdgrpid());
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }

    /**
    * 코드그룹에 해당하는 upprcd 목록을 조회하여 캐쉬를 제거한다.
    *
    * @Title       : removeCacheForCdgrp 
    * @Description : 코드그룹에 해당하는 upprcd 목록을 조회하여 캐쉬를 제거한다.
    * @param cdgrpid 코드그룹아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void removeCacheForCdgrp(String cdgrpid) throws Exception {
        for (CodeInfoVo codeInfo : resCodeService.selectCdgrpidAndUpprcdList(cdgrpid)) {
            resCodeService.removeCacheForCdgrp(codeInfo.getCdgrpid(), codeInfo.getUppr_cd());
        }
    }
    
    

    
    
    
    
    
}