package com.kbrainc.plum.mng.inst.controller;

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

import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstMngService;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.service.MemberServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 기관 관리  컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.inst.controller
 * - InstMngController.java
 * </pre> 
 *
 * @ClassName : InstMngController
 * @Description : 기관 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class InstMngController {

    @Autowired
    private InstMngService instMngService;
    
    @Autowired
    private MemberServiceImpl memberService;
    
    //@Value("${front.server.host}")
    private String frontServerHost;
    
    @Resource(name="digestService")
    EgovDigestService digestService;
    
    @Resource(name="ariaCryptoService")
    EgovCryptoService cryptoService;
    
    @Value("${crypto.key}")
    private String encryptKey;
    
    
    /**
    * 기관관리 화면 이동.
    *
    * @Title       : instMng 
    * @Description : 개인회원관리 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/instMng.html")
    public String instMng() throws Exception {
        return "mng/inst/inst";
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
    public Map<String, Object> selectInstList(InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<InstVo> result = null;
        
        result = instMngService.selectInstList(instVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
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
    public String instForm(Model model) throws Exception {
        
        return "mng/member/memberForm";
    }
    
    /**
    * 기관 등록.
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

        int retVal = 0;
        

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
     * 기관정보 상세화면 이동.
     *
     * @Title       : instUpdate 
     * @Description : 기관정보 상세화면 이동
     * @param InstVo instVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/instUpdate.html")
    public String instUpdate(InstVo instVo, Model model) throws Exception {
        
        //model.addAttribute("etcInfo", memberService.selectEtcInfo(memberVo.getUserid()));
        //model.addAttribute("member", resultVo);
        
        return "mng/member/memberUpdate";
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

        int retVal = 0;
        
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
        
        //model.addAttribute("etcInfo", memberService.selectEtcInfo(memberVo.getUserid()));
        //model.addAttribute("member", resultVo);
        
        return "mng/member/memberUpdate";
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
        
       // result = memberService.selectMemberList(memberVo);
        
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
    public Map<String, Object> updateMngRole(@Valid InstVo instVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        instVo.setUser(user);

        int retVal = 0;
        
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
     * 담당자 삭제
     *
     * @Title       : deleteManager 
     * @Description : 담당자 삭제.
     * @param InstVo instVo
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/inst/deleteManager.do")
    @ResponseBody
    public Map<String, Object> deleteManager(@Valid InstVo instVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        instVo.setUser(user);
        
        int retVal = 0;
        
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
    public String instUserSearchPopup(InstVo instVo,Model model) throws Exception {
        //model.addAttribute("tempPwdVo",tempPwdVo);
        return "mng/member/tempPwdPopup";
    }
    
    /**
    * 기관 담당자 회원 검색.
    *
    * @Title       : selectInstUserList 
    * @Description :  기관 담당자 회원 검색.
    * @param InstVo instVo 객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/inst/selectInstUser.do")
    @ResponseBody
    public Map<String, Object> selectInstUserList(InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = null;
        
       // result = memberService.selectMemberList(memberVo);
        
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
    public Map<String, Object> updateInstUser(@Valid InstVo instVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        instVo.setUser(user);
        
        int retVal = 0;
        
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