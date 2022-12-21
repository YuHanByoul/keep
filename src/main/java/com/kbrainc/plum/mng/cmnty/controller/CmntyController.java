package com.kbrainc.plum.mng.cmnty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.cmnty.model.CmntyCmntVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyCtgryVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyMbrVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyPstVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.mng.cmnty.service.CmntyServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 * 설문지관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.controller
 * - CmntyController.java
 * </pre>
 *
 * @ClassName : CmntyController
 * @Description : 커뮤니티 관리 Controller
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class CmntyController {

    @Autowired
    private CmntyServiceImpl cmntyService;
    
    /**
     * 커뮤니티 목록 화면
     *
     * @Title : cmntyForm
     * @Description : 커뮤니티 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyMng.html")
    public String cmntyForm() throws Exception {
        return "mng/cmnty/cmntyList";
    }
    
    /**
     * 커뮤니티 상세 탭 화면
     *
     * @Title : cmntyDetailForm
     * @Description : 커뮤니티 상세 탭 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyDetail.html")
    public String cmntyDetailForm(CmntyCtgryVo cmntyCtgryVo, Model model) throws Exception {
        model.addAttribute("ctgryList", cmntyService.selectCmntyCtgryList(cmntyCtgryVo));
        return "mng/cmnty/cmntyDetail";
    }
    
    /**
     * 커뮤니티 정보 화면
     *
     * @Title : cmntyUpdateForm
     * @Description : 커뮤니티 수정 화면
     * @param cmntyVo CmntyVo 객체
     * @param model 모델객체
     * @param user 사용자 세션 정보
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyUpdateForm.html")
    public String cmntyUpdateForm(CmntyVo cmntyVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("cmnty", cmntyService.selectCmntyInfo(cmntyVo));
        
        return "mng/cmnty/cmntyUpdate";
    }
    
    /**
     * 커뮤니티 회원 목록 화면
     *
     * @Title : cmntyMbrForm
     * @Description : 커뮤니티 회원 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyMbr.html")
    public String cmntyMbrForm() throws Exception {
        return "mng/cmnty/cmntyMbrList";
    }
    
    /**
     * 커뮤니티 게시글 목록 화면
     *
     * @Title : cmntyPstForm
     * @Description : 커뮤니티 게시글 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyPst.html")
    public String cmntyPstForm() throws Exception {
        return "mng/cmnty/cmntyPstList";
    }
    
    /**
     * 커뮤니티 게시글 정보 화면
     *
     * @Title : cmntyPstUpdateForm
     * @Description : 커뮤니티 게시글 정보 화면
     * @param cmntyPstVo CmntyPstVo 객체
     * @param model 모델객체
     * @param user 사용자 세션 정보
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyPstUpdateForm.html")
    public String cmntyPstUpdateForm(CmntyPstVo cmntyPstVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("cmntyPst", cmntyService.selectCmntyPstInfo(cmntyPstVo));
        cmntyPstVo.setOrderField("CMNT_GRP DESC, SORTORDR");
        cmntyPstVo.setOrderDirection(CmntyPstVo.ORDER_DIRECTION_ASC);
        model.addAttribute("cmntyCmntList", cmntyService.selectCmntyCmntList(cmntyPstVo));
        
        return "mng/cmnty/cmntyPstUpdate";
    }
    
    /**
     * 커뮤니티 목록 조회
     *
     * @Title : selectCmntyList
     * @Description : 커뮤니티 목록 조회
     * @param cmntyVo CmntyVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyList(CmntyVo cmntyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyVo> result = cmntyService.selectCmntyList(cmntyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 회원 목록 조회
     *
     * @Title : selectCmntyMbrList
     * @Description : 커뮤니티 회원 목록 조회
     * @param cmntyMbrVo CmntyMbrVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyMbrList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyMbrVo> result = cmntyService.selectCmntyMbrList(cmntyMbrVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 게시글 목록 조회
     *
     * @Title : selectCmntyPstList
     * @Description : 커뮤니티 게시글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyPstList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyPstList(CmntyPstVo cmntyPstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyPstVo> result = cmntyService.selectCmntyPstList(cmntyPstVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 게시글 삭제
     *
     * @Title : deleteCmntyPst
     * @Description : 커뮤니티 게시글 삭제
     * @param cmntyPstVo CmntyPstVo 객체
     * @param bindingResult cmntyPstVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/deleteCmntyPst.do")
    @ResponseBody
    public Map<String, Object> deleteCmntyPst(@Valid CmntyPstVo cmntyPstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        cmntyPstVo.setUser(user);
        retVal = cmntyService.deleteCmntyPst(cmntyPstVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패하였습니다");
        }
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 댓글 삭제
     *
     * @Title : deleteQitem
     * @Description : 커뮤니티 댓글 삭제
     * @param cmntyCmntVo CmntyCmntVo 객체
     * @param bindingResult cmntyPstVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/deleteCmntyCmnt.do")
    @ResponseBody
    public Map<String, Object> deleteCmntyCmnt(@Valid CmntyCmntVo cmntyCmntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        cmntyCmntVo.setUser(user);
        retVal = cmntyService.deleteCmntyCmnt(cmntyCmntVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패하였습니다");
        }
            
        return resultMap;
    }
    
}
