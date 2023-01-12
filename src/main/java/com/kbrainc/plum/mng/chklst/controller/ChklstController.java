package com.kbrainc.plum.mng.chklst.controller;

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

import com.kbrainc.plum.mng.chklst.model.ChklstQitemMapngVo;
import com.kbrainc.plum.mng.chklst.model.ChklstQitemVo;
import com.kbrainc.plum.mng.chklst.model.ChklstVo;
import com.kbrainc.plum.mng.chklst.service.ChklstServiceImpl;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 * 체크리스트관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.controller
 * - ChklstController.java
 * </pre>
 *
 * @ClassName : ChklstController
 * @Description : 체크리스트관리 Controller
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ChklstController {

    @Autowired
    private ChklstServiceImpl chklstService;
    
    /**
     * 체크리스트 문항 관리 목록 화면
     *
     * @Title : chklstQitemListForm
     * @Description : 체크리스트 문항 관리 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstQitemListForm.html")
    public String chklstQitemListForm(CodeVo codeVo, Model model) throws Exception {
        model.addAttribute("codeList", chklstService.selectChklstQitemCdList(codeVo));
        return "mng/chklst/chklstQitemList";
    }
    
     /**
      * 체크리스트 문항 등록 화면
      *
      * @Title : chklstQitemInsertForm
      * @Description : 체크리스트 문항 등록 화면
      * @return String 화면경로
      * @throws Exception 예외
      */
    @RequestMapping(value = "/mng/chklst/chklstQitemInsertForm.html")
    public String chklstQitemInsertForm(CodeVo codeVo, Model model) throws Exception {
        model.addAttribute("codeList", chklstService.selectChklstQitemCdList(codeVo));
        return "mng/chklst/chklstQitemInsert";
    }
     
    /**
     * 체크리스트 문항 수정 화면
     *
     * @Title : chklstQitemUpdateForm
     * @Description : 체크리스트 문항 수정 화면
     * @param chklstQitemVo ChklstQitemVo 객체
     * @param codeVo CodeVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstQitemUpdateForm.html")
    public String chklstQitemUpdateForm(ChklstQitemVo chklstQitemVo, CodeVo codeVo, Model model) throws Exception {
        model.addAttribute("qitemInfo", chklstService.selectChklstQitemInfo(chklstQitemVo));
        model.addAttribute("codeList", chklstService.selectChklstQitemCdList(codeVo));
        return "mng/chklst/chklstQitemUpdate";
    }
    
    /**
     * 체크리스트 목록 화면
     *
     * @Title : chklstListForm
     * @Description : 체크리스트 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstListForm.html")
    public String chklstListForm(CodeVo codeVo, Model model) throws Exception {
        return "mng/chklst/chklstList";
    }
    
    /**
     * 체크리스트 등록 탭 화면
     *
     * @Title : chklstRegistForm
     * @Description : 체크리스트 등록 탭 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstRegistForm.html")
    public String chklstRegistForm() throws Exception {
        return "mng/chklst/chklstRegist";
    }
    
     /**
      * 체크리스트 등록 화면
      *
      * @Title : chklstInsertForm
      * @Description : 체크리스트 등록 화면
      * @return String 화면경로
      * @throws Exception 예외
      */
    @RequestMapping(value = "/mng/chklst/chklstInsertForm.html")
    public String chklstInsertForm() throws Exception {
        return "mng/chklst/chklstInsert";
    }
    
    /**
     * 체크리스트 상세 탭 화면
     *
     * @Title : chklstDetailForm
     * @Description : 체크리스트 상세 탭 화면
     * @param qestnrVo QestnrVo 객체
     * @return  String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstDetailForm.html")
    public String chklstDetailForm(ChklstVo chklstVo, Model model) throws Exception {
        return "mng/chklst/chklstDetail";
    }
    
    /**
     * 체크리스트 수정 화면
     *
     * @Title : chklstUpdateForm
     * @Description : 체크리스트 수정 화면
     * @param chklstVo ChklstVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstUpdateForm.html")
    public String chklstUpdateForm(ChklstVo chklstVo, Model model) throws Exception {
        model.addAttribute("chklstInfo", chklstService.selectChklstInfo(chklstVo));
        return "mng/chklst/chklstUpdate";
    }
    
    /**
     * 체크리스트 문항구성 목록 화면
     *
     * @Title : chklstQitemMapngListForm
     * @Description : 체크리스트 문항구성 목록 화면
     * @param chklstQitemMapngVo ChklstQitemMapngVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstQitemMapngListForm.html")
    public String chklstQitemMapngListForm(ChklstQitemMapngVo chklstQitemMapngVo, Model model) throws Exception {
        model.addAttribute("qitemMapngList", chklstService.selectChklstQitemMapngList(chklstQitemMapngVo));
        return "mng/chklst/chklstQitemMapngList";
    }
    
    /**
     * 체크리스트 문항 목록 팝업
     *
     * @Title : userListPopup
     * @Description : 체크리스트 문항 목록 팝업
     * @param chklstQitemVo ChklstQitemVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstQitemListPopup.html")
    public String chklstQitemListPopup(ChklstQitemVo chklstQitemVo, Model model) throws Exception {
        return "mng/chklst/chklstQitemListPopup";
    }
    
    /**
     * 체크리스트 문항 등록
     *
     * @Title : insertChklstQitem
     * @Description : 체크리스트 문항 등록
     * @param chklstQitemVo ChklstQitemVo 객체
     * @param bindingResult qitemVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/insertChklstQitem.do")
    @ResponseBody
    public Map<String, Object> insertChklstQitem(@Valid ChklstQitemVo chklstQitemVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        chklstQitemVo.setUser(user);
        retVal = chklstService.insertChklstQitem(chklstQitemVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
     * 체크리스트 문항 목록 조회
     *
     * @Title : selectChklstQitemList
     * @Description : 체크리스트 문항 목록 조회
     * @param qitemVo QitemVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/selectChklstQitemList.do")
    @ResponseBody
    public Map<String, Object> selectChklstQitemList(ChklstQitemVo chklstQitemVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ChklstQitemVo> result = chklstService.selectChklstQitemList(chklstQitemVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 체크리스트 문항 정보 업데이트
     *
     * @Title : chklstQitemVo
     * @Description : 체크리스트 문항 정보 업데이트
     * @param chklstQitemVo ChklstQitemVo 객체
     * @param bindingResult qitemVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/updateChklstQitem.do")
    @ResponseBody
    public Map<String, Object> updateChklstQitem(@Valid ChklstQitemVo chklstQitemVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        chklstQitemVo.setUser(user);
        retVal = chklstService.updateChklstQitem(chklstQitemVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
            
        return resultMap;
    }
    
    /**
     * 체크리스트 등록
     *
     * @Title : insertChklst
     * @Description : 체크리스트 등록
     * @param chklstVo ChklstVo 객체
     * @param bindingResult chklstVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/insertChklst.do")
    @ResponseBody
    public Map<String, Object> insertChklst(@Valid ChklstVo chklstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
                
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
                
        int retVal = 0;
        chklstVo.setUser(user);
        retVal = chklstService.insertChklst(chklstVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
            resultMap.put("chklstid", chklstVo.getChklstid());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
        
        return resultMap;
    }
    
    /**
     * 체크리스트 목록 조회
     *
     * @Title : selectChklstList
     * @Description : 체크리스트 목록 조회
     * @param chklstVo ChklstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/selectChklstList.do")
    @ResponseBody
    public Map<String, Object> selectChklstList(ChklstVo chklstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ChklstVo> result = chklstService.selectChklstList(chklstVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 사용중인 체크리스트 여부 확인
     *
     * @Title : isUseChklst
     * @Description : 사용중인 체크리스트 여부 확인
     * @param chklstVo ChklstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/isUseChklst.do")
    @ResponseBody
    public Map<String, Object> isUseChklst(ChklstVo chklstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        ChklstVo result = chklstService.isUseChklst(chklstVo);
        resultMap.put("chklstInfo", result);
        
        return resultMap;
    }
    
    /**
     * 체크리스트 정보 업데이트
     *
     * @Title : updateChklst
     * @Description : 체크리스트 정보 업데이트
     * @param chklstVo ChklstVo 객체
     * @param bindingResult qestnrVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/updateChklst.do")
    @ResponseBody
    public Map<String, Object> updateChklst(@Valid ChklstVo chklstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
            
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        chklstVo.setUser(user);
        retVal = chklstService.updateChklst(chklstVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
            
        return resultMap;
    }
    
}
