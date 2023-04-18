package com.kbrainc.plum.mng.inst.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovDigestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.service.MemberServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 기관 관리  컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.inst.controller
 * - InstController.java
 * </pre> 
 *
 * @ClassName : InstController
 * @Description : 기관 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class InstController {

    @Autowired
    private InstService instService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private MemberServiceImpl memberService;

    /**
    * 기관관리 화면 이동.
    *
    * @Title       : instMng 
    * @Description : 개인회원관리 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/instMng.html")
    public String instMng(InstVo instVo, Model model) throws Exception {
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
        return "mng/inst/instList";
    }
    
    /**
    * 기관 목록 조회.
    *
    * @Title       : selectMemberList 
    * @Description : 기관 목록 조회.
    * @param InstVo instVo 객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/selectInstList.do")
    @ResponseBody
    public Map<String, Object> selectInstList(InstVo instVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<InstVo> result = null;
        instVo.setUser(user);
        result = instService.selectInstList(instVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 상세(탭) 화면
     *
     * @Title       : instForm 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instDetail.html")
    public String instDetail(InstVo instVo, Model model) throws Exception {
        model.addAttribute("param", instVo);
        return "mng/inst/instDetail";
    }
    /**
     * 등록 화면
     *
     * @Title       : instForm 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instForm.html")
    public String instForm(InstVo instVo,Model model) throws Exception {
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
        return "mng/inst/instForm";
    }
    
    /**
    * 기관 등록
    *
    * @Title       : insertMember 
    * @Description : 기관 등록.
    * @param InstVo instVo객체
    * @param bindingResult1 memberVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/insertInst.do")
    @ResponseBody
    public Map<String, Object> insertInst(@Valid InstVo instVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        instVo.setUser(user);
        
        if(instService.checkDuplicatedBrnoYn(instVo).equals("Y")){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 등록 된 사업자 번호가 있습니다.");
            return resultMap;
        }

        int retVal = 0;
        
        retVal = instService.insertInst(instVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
     * 기관정보 상세(수정)화면 이동.
     *
     * @Title       : instUpdate 
     * @Description : 기관정보 상세화면 이동
     * @param InstVo instVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instUpdate.html")
    public String instUpdate(InstVo instVo, Model model, @UserInfo UserVo user) throws Exception {
        
        model.addAttribute("param", instVo);
        
        instVo.setUser(user);
        InstVo resultVo = instService.selectInstInfo(instVo);
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        if (resultVo.getFilegrpid() != null && !resultVo.getFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileMap",fileList );
            model.addAttribute("currentFileCnt", fileList.size());
        } else {
            model.addAttribute("fileMap", null);
            model.addAttribute("currentFileCnt", 0);
        }
        
        if (resultVo.getLogoFileid() != null && !resultVo.getLogoFileid().equals(0)) {
            fileVo.setFileid(Integer.parseInt(resultVo.getLogoFileid().toString()));
            
            FileVo logoVo= fileService.getFileInfo(fileVo);
            
            model.addAttribute("logoFile",logoVo );
        } else {
            model.addAttribute("logoFile", null);
        }
        
        model.addAttribute("inst", resultVo);
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
        
        return "mng/inst/instUpdate";
    }
    
    
    /**
    * 기관정보 수정.
    *
    * @Title       : updateInst 
    * @Description : 기관정보 수정.
    * @param InstVo instVo
    * @param bindingResult1 memberVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/updateInst.do")
    @ResponseBody
    public Map<String, Object> updateInst(@Valid InstVo instVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        instVo.setUser(user);
        
        if(instService.checkDuplicatedBrnoYn(instVo).equals("Y")){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 등록 된 사업자 번호가 있습니다.");
            return resultMap;
        }
        
        int retVal = 0;
        retVal = instService.updateInst(instVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
     * 담당자 상세화면 이동.
     *
     * @Title       : instManager 
     * @Description : 기관정보 상세화면 이동
     * @param InstVo instVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instManager.html")
    public String instManager(InstVo instVo, Model model) throws Exception {
        model.addAttribute("param", instVo);
        return "mng/inst/instManager";
    }
    
    /**
    * 기관 담당자 목록 조회.
    *
    * @Title       : selectManagerList 
    * @Description :  기관 담당자 목록 조회.
    * @param InstVo instVo 객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/selectManagerList.do")
    @ResponseBody
    public Map<String, Object> selectManagerList(InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = null;
        
        result = instService.selectManagerList(instVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    
    /**
    * 담당자 역할 수정.
    *
    * @Title       : updateMngRole 
    * @Description : 담당자 역할 수정.
    * @param InstVo instVo
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/updateMngRole.do")
    @ResponseBody
    public Map<String, Object> updateMngRole(MemberVo memberVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        memberVo.setUser(user);
        //마스터 기관회원 일반회원으로 업데이트 방지 
        MemberVo checkVo  = memberService.selectMemberInfo(memberVo);
        
        InstVo instVo = new InstVo();
        instVo.setInstid(memberVo.getInstid());
        
        boolean isThereOtherMasterManager = false;
        
        int retVal = 0;
        
        //마스터에서 일반으로 
        if(!CommonUtil.isEmpty(checkVo.getInstpicRoleCd()) && checkVo.getInstpicRoleCd().equals("109101") && memberVo.getInstpicRoleCd().equals("109102")) {
            
            List<MemberVo> memberList =  instService.selectInstMemberList(instVo);
            if(memberList!=null && memberList.size() > 0 ) {
                for(MemberVo vo : memberList) {
                    if(vo.getUserid() != memberVo.getUserid() && vo.getInstpicRoleCd().equals("109101")) {
                        isThereOtherMasterManager = true;
                    }
                }
            }
            if(!isThereOtherMasterManager) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "마스터 관리자는 반드시 1인이 지정 되어야 합니다.");
                return resultMap;
            }
            
        }else if (!CommonUtil.isEmpty(checkVo.getInstpicRoleCd()) && checkVo.getInstpicRoleCd().equals("109102") && memberVo.getInstpicRoleCd().equals("109101")) {
            //기관 회원 모두 일반으로 업데이트 
            MemberVo paramVo = new MemberVo ();
            paramVo.setUser(user);
            paramVo.setInstid(memberVo.getInstid());
            paramVo.setInstpicRoleCd("109102");
            retVal += instService.updateInstRoleAllUser(paramVo);
        }
        
        //회원 업데이트 
        retVal += memberService.updateInstMemberRole(memberVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 담당자 회원검색 레이어팝업화면.
     *
     * @Title       : instUserSearchPopup 
     * @Description : 담당자 회원검색 레이어팝업화면.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instUserSearchPopup.html")
    public String instUserSearchPopup(InstVo instVo,Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("user",user);
        return "mng/inst/instUserSearchPopup";
    }
    
    /**
    * 기관 등록용 담당자 회원 검색.
    *
    * @Title       : selectInstUserList 
    * @Description :  기관 담당자 회원 검색.
    * @param InstVo instVo 객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/selectInstUser.do")
    @ResponseBody
    public Map<String, Object> selectInstUserList(InstVo instVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = null;
        instVo.setUser(user);
        result = instService.selectInstRegisterMemberList(instVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 담당자 등록
     *
     * @Title       : updateInstUser 
     * @Description :  담당자 등록
     * @param InstVo instVo
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/updateInstUser.do")
    @ResponseBody
    public Map<String, Object> updateInstUser(MemberVo memberVo,  @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        memberVo.setUser(user);
        
        int retVal = 0;
        
        retVal = instService.insertInstUser(memberVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "담당자 등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "담당자 등록에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
     * 담당자 삭제
     *
     * @Title       : deleteManager 
     * @Description : 담당자 삭제.
     * @param InstVo instVo
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/deleteInstManager.do")
    @ResponseBody
    public Map<String, Object> deleteManager(InstVo instVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        int retVal = 0;
        
        List<MemberVo> result = null;
        result = instService.selectInstMemberList(instVo);
        
        boolean isThereInstMasterRole = false;
        
        for(MemberVo vo :result) {
            if(!CommonUtil.isEmpty(vo.getInstpicRoleCd()) & vo.getInstpicRoleCd().equals("109101")) {
                resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
                resultMap.put("msg", "마스터 담당자는 삭제 할 수 없습니다.");
                return resultMap;
            }
        }
        
        instVo.setUser(user);
        retVal = instService.deleteInstUser(instVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    
    
}