package com.kbrainc.plum.mng.tchaid.controller;

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

import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.cmpnt.model.CmpntWrhousngVo;
import com.kbrainc.plum.mng.tchaid.service.TchaidService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 교구관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.controller - TchaidController.java
 * </pre>
 *
 * @ClassName : TchaidController
 * @Description :  교구관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 02.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class TchaidController {

    @Autowired
    private TchaidService tchaidService;
    
    /**
     * 구성품 관리
     *
     * @Title : tchaidCmpntList
     * @Description : 구성품 관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidCmpntList.hrml")
    public String tchaidCmpntList(Model model) throws Exception {
        return "mng/tchaid/cmpnt/cmpntList";
    }
    
    /**
     * 구성품 목록 조회
     *
     * @Title : selectCmpntList
     * @Description :구성품 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/selectCmpntList.do")
    @ResponseBody
    public Map<String, Object> selectCmpntList(CmpntVo cmpntVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<CmpntVo> result = null;
        cmpntVo.setUser(user);
        
        //result = tchaidService.selectCmpntList(cmpntVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 구성품 입고 내역 조회
     *
     * @Title : selectCmpntWrhousngList
     * @Description :구성품 입고 내역 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/selectCmpntWrhousngList.do")
    @ResponseBody
    public Map<String, Object> selectCmpntWrhousngList(CmpntWrhousngVo cmpntWrhousngVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<CmpntWrhousngVo> result = null;
        cmpntWrhousngVo.setUser(user);
        
        //result = tchaidService.selectCmpntWrhousngList(cmpntWrhousngVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 구성품 입력 팝업
     *
     * @Title : insertCmpntPopup
     * @Description : 구성품 등록 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/insertCmpntPopup.html")
    public String insertCmpntPopup(Model model) throws Exception {
        return "mng/tchaid/cmpnt/insertCmpntPopup";
    }
    
    /**
     * 구성품 입고 팝업
     *
     * @Title : insertCmpntWrhousngPopup
     * @Description : 구성품 입고 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/insertCmpntWrhousngPopup.html")
    public String insertCmpntWrhousngPopup(Model model) throws Exception {
        return "mng/tchaid/cmpnt/insertCmpntWrhousngPopup";
    }
    
    /**
     * 구성품 등록
     *
     * @Title : resultMap
     * @Description : 구성품 등록
     * @param CmpntVo         CmpntVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/insertCmpnt.do")
    @ResponseBody
    public Map<String, Object> insertCmpnt(@Valid CmpntVo cmpntVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        cmpntVo.setUser(user);
        
        //retVal = tchaidService.insertCmpnt(cmpntVo);
        
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
     * 구성품  입고 등록
     *
     * @Title : insertCmpntWrhousng
     * @Description : 구성품  입고 등록
     * @param CmpntWrhousngVo         CmpntWrhousngVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/insertCmpntWrhousng.do")
    @ResponseBody
    public Map<String, Object> insertCmpntWrhousng(@Valid CmpntWrhousngVo cmpntWrhousngVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        cmpntWrhousngVo.setUser(user);
        
        retVal = tchaidService.insertCmpntWrhousng(cmpntWrhousngVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}