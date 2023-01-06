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

import com.kbrainc.plum.mng.chklst.model.ChklstQitemVo;
import com.kbrainc.plum.mng.chklst.service.ChklstServiceImpl;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeServiceImpl;
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
    
    @Autowired
    private CodeServiceImpl codeService;
    
    /**
     * 체크리스트 문항 관리 목록 화면
     *
     * @Title : chklstQitemListForm
     * @Description : 체크리스트 문항 관리 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/chklst/chklstQitemList.html")
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
     * 설문지 문항 목록 화면
     *
     * @Title : qitemForm
     * @Description : 설문지 문항 목록 화면
     * @param qestnrVo QestnrVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/qitem.html")
//    public String qitemForm(QestnrVo qestnrVo, Model model) throws Exception {
//        return "mng/qestnr/qitemList";
//    }
       
    /**
     * 설문지 문항 정보 등록 화면
     *
     * @Title : qestnrForm
     * @Description : 설문지 문항 정보 등록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/qitemInsertForm.html")
//    public String qitemInsertForm() throws Exception {
//        return "mng/qestnr/qitemInsert";
//    }
    
    /**
     * 설문지 문항 수정 화면
     *
     * @Title : qitemUpdateForm
     * @Description : 설문지문항 수정 화면
     * @param qitemVo QitemVo 객체
     * @param qitemExVo QitemExVo 객체
     * @param model 모델객체
     * @return String 화면경로
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/qitemUpdateForm.html")
//    public String qitemUpdateForm(QitemVo qitemVo, QitemExVo qitemExVo, Model model) throws Exception {
//        model.addAttribute("qitem", qestnrService.selectQitemInfo(qitemVo));
//        model.addAttribute("qitemEx", qestnrService.selectQitemExList(qitemVo));
//        return "mng/qestnr/qitemUpdate";
//    }
       
    /**
     * 설문지 등록
     *
     * @Title : insertQestnr
     * @Description : 설문지 등록
     * @param qestnrVo QestnrVo 객체
     * @param bindingResult qestnrVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/insertQestnr.do")
//    @ResponseBody
//    public Map<String, Object> insertQestnr(@Valid QestnrVo qestnrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//                
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//                
//        int retVal = 0;
//        qestnrVo.setUser(user);
//        retVal = qestnrService.insertQestnr(qestnrVo);
//        
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "등록에 성공하였습니다");
//            resultMap.put("qestnrid", qestnrVo.getQestnrid());
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "등록에 실패하였습니다");
//        }
//        
//        return resultMap;
//    }
    
    /**
     * 설문지 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 설문지 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/selectQestnrList.do")
//    @ResponseBody
//    public Map<String, Object> selectQestnrList(QestnrVo qestnrVo) throws Exception {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<QestnrVo> result = qestnrService.selectQestnrList(qestnrVo);
//                    
//        if(result.size() > 0) {
//            resultMap.put("totalCount", (result.get(0).getTotalCount()));
//        } else {
//            resultMap.put("totalCount", 0);
//        }
//        resultMap.put("list", result);
//            
//        return resultMap;
//    }
        
    /**
     * 설문지 정보 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 정보 업데이트
     * @param qestnrVo QestnrVo 객체
     * @param bindingResult qestnrVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/updateQestnr.do")
//    @ResponseBody
//    public Map<String, Object> updateQestnr(@Valid QestnrVo qestnrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//            
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//        
//        int retVal = 0;
//        qestnrVo.setUser(user);
//        retVal = qestnrService.updateQestnr(qestnrVo);
//        
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "수정에 성공하였습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "수정에 실패하였습니다");
//        }
//            
//        return resultMap;
//    }
        
    /**
     * 설문지 문항 순서 업데이트
     *
     * @Title : updateQestnr
     * @Description : 설문지 문항 순서 업데이트
     * @param qestnrVo QestnrVo 객체
     * @param bindingResult qitemVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/updateQitemOrdr.do")
//    @ResponseBody
//    public Map<String, Object> updateQitemOrdr(@Valid QitemVo qitemVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//            
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//        
//        int retVal = 0;
//        qitemVo.setUser(user);
//        retVal = qestnrService.updateQitemOrdr(qitemVo);
//        
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "순서 변경에 성공하였습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "순서 변경에 실패하였습니다");
//        }
//            
//        return resultMap;
//    }
    
    /**
     * 설문지 문항 삭제
     *
     * @Title : deleteQitem
     * @Description : 설문지 문항 삭제
     * @param qitemVo QitemVo 객체
     * @param bindingResult qitemVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
//    @RequestMapping(value = "/mng/qestnr/deleteQitem.do")
//    @ResponseBody
//    public Map<String, Object> deleteQitem(@Valid QitemVo qitemVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//            
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//        
//        int retVal = 0;
//        retVal = qestnrService.deleteQitem(qitemVo);
//        
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "삭제에 성공하였습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "삭제에 실패하였습니다");
//        }
//            
//        return resultMap;
//    }
    
}
