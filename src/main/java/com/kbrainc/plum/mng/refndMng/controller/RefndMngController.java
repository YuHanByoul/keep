package com.kbrainc.plum.mng.refndMng.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;
import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.mng.refndMng.service.RefndMngService;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.mng.resveReqst.service.ResveReqstService;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 교육 시설 환불 관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : RefndMngController
* @Description : 교육 시설 환불 관리 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class RefndMngController {
    
    @Autowired
    private RefndMngService refndMngService;

    @Autowired
    private SpceService spceService;

    @Autowired
    private ResveReqstService resveReqstService;
    
    /**
    * 환불관리 리스트화면으로 이동
    *
    * @Title : refndMngList
    * @Description : 환불관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/refndMng/refndMngList.html")
    public String refndMngList() throws Exception {
        return "mng/refndMng/refndMngList";
    }
    
    /**
     * 상세(탭) 화면
     *
     * @Title       : refndMngDetail 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndMngDetail.html")
    public String refndMngDetail(RefndMngVo refndMngVo, Model model, SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        model.addAttribute("param", refndMngVo);
        // 공간 보유개수 조회
        spceVo.setFcltid(refndMngVo.getFcltid());
        spceVo.setUser(user);
        return "mng/refndMng/refndMngDetail";
    }

    /**
     * 환불완료 상세 페이지 이동
     *
     * @Title       : refndMngCompleteList
     * @Description : 공간정보 조회화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndMngCompleteList.html")
    public String refndMngCompleteList() throws Exception {
        return "mng/refndMng/refndMngCompleteList";
    }
    
    
    /**
    * 환불완료 목록 조회
    *
    * @Title : selectRefndMngCompleteList
    * @Description : 환불완료 목록 조회
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/selectRefndMngCompleteList.do")
    @ResponseBody
    public Map<String, Object> selectRefndMngCompleteList(RefndMngVo refndMngVo, @UserInfo UserVo user, SpceVo spceVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SpceVo> result = null;
        
        // 공간 보유개수 조회
        spceVo.setFcltid(refndMngVo.getFcltid());
        spceVo.setUser(user);
        result = spceService.selectSpceList(spceVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/mng/refndMng/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        
        return "mng/refndMng/instSearchPopup";
    }
    
    /**
    * 시설 수정화면으로 이동
    *
    * @Title : refndMngView
    * @Description : 시설 수정화면으로 이동
    * @param refndMngVo 시설 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/refndMng/refndMngView.html")
    public String refndMngView(ResveReqstVo resveReqstVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        model.addAttribute("param", resveReqstVo);
        resveReqstVo.setUser(user);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("aplyVo", resultVo);
        return "mng/refndMng/refndMngView";
    }
   
    /**
    * 시설 리스트 기능
    *
    * @Title : selectRefndMngList
    * @Description : 시설 리스트 기능
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/selectRefndMngList.do")
    @ResponseBody
    public Map<String, Object> selectRefndMngList(ResveReqstVo resveReqstVo, @UserInfo UserVo user, InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ResveReqstVo> result = null;

        result =  refndMngService.selectRefndMngList(resveReqstVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * @Title : refndCancelPopup
     * @Description : 환불요청취소 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndCancelPopup.html")
    public String refndCancelPopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("param", resveReqstVo);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("aplyVo", resultVo);
        return "mng/refndMng/refndCancelPopup";
    }

    /**
     * @Title : refndCompletePopup
     * @Description : 환불 완료 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndCompletePopup.html")
    public String refndCompletePopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("param", resveReqstVo);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("aplyVo", resultVo);
        return "mng/refndMng/refndCompletePopup";
    }

    /**
     * 환불 완료 처리
     *
     * @Title : updateRefndComplete
     * @Description : 환불 완료 처리
     * @param refndMngVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/refndMng/updateRefndComplete.do")
    @ResponseBody
    public Map<String, Object> updateRefndComplete(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resveReqstVo.setUser(user);
        int retVal = 0;
        
        resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        
        retVal = refndMngService.updateRefndComplete(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "환불 완료처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "환불 완료처리에 실패했습니다.");
        }

        return resultMap;
    }

    /**
     * 환불 요청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 예약 신청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/refndMng/updateRefndCancel.do")
    @ResponseBody
    public Map<String, Object> updateRefndCancel(@Valid ResveReqstVo resveReqstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        resveReqstVo.setUser(user);
        int retVal = 0;
        retVal = refndMngService.updateRefndCancel(resveReqstVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "환불요청 취소에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "환불요청 취소에 실패했습니다.");
        }

        return resultMap;
    }

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 환불 완료취소 처리
     * @param refndMngVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/refndMng/updateRefndRollback.do")
    @ResponseBody
    public Map<String, Object> updateRefndRollback(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resveReqstVo.setUser(user);
        int retVal = 0;
        retVal = refndMngService.updateRefndRollback(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "환불완료취소 처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "환불완료취소 처리에 실패했습니다.");
        }

        return resultMap;
    }
}
