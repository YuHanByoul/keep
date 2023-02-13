package com.kbrainc.plum.mng.tchaid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.tchaid.model.TchaidEduTrgtVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidWrhousngVo;
import com.kbrainc.plum.mng.tchaid.service.TchaidService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

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
     * 교구 관리 화면
     *
     * @Title : tchaidList
     * @Description : 교구 관리 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidList.html")
    public String tchaidList(Model model) throws Exception {
        return "mng/tchaid/tchaidList";
    }
    
    /**
     * 교구 목록 조회
     *
     * @Title : selectCmpntList
     * @Description : 교구 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/selectTchaidList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidList( TchaidVo TchaidVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<TchaidVo> result = null;
        TchaidVo.setUser(user);
        
        result = tchaidService.selectTchaidList(TchaidVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 교구 관리 상세 (탭) 화면
     *
     * @Title : tchaidDetail
     * @Description : 교구 관리 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidDetail.html")
    public String tchaidDetail(TchaidVo tchaidVo , Model model) throws Exception {
        model.addAttribute("param", tchaidVo);
        model.addAttribute("mode", tchaidVo.getMode());
        return "mng/tchaid/tchaidDetail";
    }
    
    /**
     * 교구 등록 화면
     *
     * @Title : tchaidModifyForm
     * @Description : 교구 등록 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidModifyForm.html")
    public String tchaidModifyForm(TchaidVo tchaidVo , Model model) throws Exception {
        
        //교육 주제 리스트 호출         
        Map<String,String> map = new HashMap();
        map.put("cdgrpid", "155");
        model.addAttribute("sbjctCdLsit", tchaidService.selectTchaidCdList(map)  );
        model.addAttribute("cmpntLsit", tchaidService.selectAllCmpntList(map)  );
        
        if(tchaidVo.getMode().equals("I")){
            return "mng/tchaid/tchaidInsertForm";
        }else {
            model.addAttribute("tchaidVo", tchaidService.selectTchaid(tchaidVo)  );
            model.addAttribute("mySbjctLsit", tchaidService.selectTchaidEduSbjctList(tchaidVo)  );
            
            List<String> trgtCds = new ArrayList();
            List<TchaidEduTrgtVo> list = tchaidService.selectTchaidEduTrgtList(tchaidVo); 
            for(TchaidEduTrgtVo vo :list) {
                trgtCds.add(vo.getEduTrgtCd());
            }
            
            model.addAttribute("mytrgtLsit", list );
            model.addAttribute("mytrgtCds", String.join(",",trgtCds) );
            
            map.put("tchaidid", String.valueOf(tchaidVo.getTchaidid()));
            model.addAttribute("myCmpntLsit", tchaidService.selectTchaidCmpntList(map)  );
            return "mng/tchaid/tchaidModifyForm";
        }
    }
    
    /**
     * 교구 등록
     *
     * @Title : insertTchaid
     * @Description : 교구 등록
     * @param CmpntVo         CmpntVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/insertTchaid.do")
    @ResponseBody
    public Map<String, Object> insertTchaid(@Valid @RequestBody TchaidVo TchaidVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        TchaidVo.setUser(user);
        
        retVal = tchaidService.insertTchaid(TchaidVo);
        
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
     * 교구 수정
     *
     * @Title : updateTchaid
     * @Description :  교구 수정
     * @param TchaidVo TchaidVo 객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/updateTchaid.do")
    @ResponseBody
    public Map<String, Object> updateTchaid(@Valid @RequestBody TchaidVo TchaidVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        TchaidVo.setUser(user);
        
        if(StringUtil.isNotNull(TchaidVo.getMode()) && TchaidVo.getMode().equals("use")) {
            //use_yn 정보만 변경
            retVal = tchaidService.updateTchaidUseYn(TchaidVo);
        }else {
            retVal = tchaidService.updateTchaid(TchaidVo);
        }
        
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 교구 삭제
     *
     * @Title : deleteTchaid
     * @Description :  교구 삭제
     * @param TchaidVo TchaidVo 객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/deleteTchaid.do")
    @ResponseBody
    public Map<String, Object> deleteTchaid(@Valid TchaidVo TchaidVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        TchaidVo.setUser(user);
        
        int packageCnt = tchaidService.selectTchaidCntForPackage(TchaidVo);
        
        if(packageCnt > 0 ) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 꾸러미로 구성되어 삭제 할 수 없습니다.");
            return resultMap;
        }
        
        retVal = tchaidService.deleteTchaid(TchaidVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 교구 구성 상세 

     *
     * @Title : tchaidCmpntCmpstnForm
     * @Description : 교구 구성 상세
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidCmpntCmpstnForm.html")
    public String tchaidCmpntCmpstnForm(TchaidVo tchaidVo,Model model) throws Exception {
        model.addAttribute("tchaidVo", tchaidVo);
        model.addAttribute("cmpntLsit", tchaidService.selectTchaidCmpntCmpstnDetailList(tchaidVo)  );
        return "mng/tchaid/tchaidCmpntCmpstnForm";
    }
    /**
     * 교구 구성 수정 
     *
     * @Title : modifyCmpntCmpstn
     * @Description : 교구 구성 수정
     * @param TchaidVo TchaidVo 객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/modifyCmpntCmpstn.do")
    @ResponseBody
    public Map<String, Object> modifyCmpntCmpstn(@Valid TchaidVo TchaidVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        TchaidVo.setUser(user);
        
        retVal = tchaidService.createTchaidFromCmpnt(TchaidVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "교구생성에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "교구생성에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
     * 교구 입고 상세  
     *
     * @Title : tchaidWrhousngDetail
     * @Description : 교구 입고 상세 
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidWrhousngDetail.html")
    public String tchaidWrhousngDetail(TchaidVo tchaidVo,Model model) throws Exception {
        model.addAttribute("param", tchaidVo);
        return "mng/tchaid/tchaidWrhousngForm";
    }
    
    
    /**
     * 교구 입고 목록 호출
     *
     * @Title : selectTchaidWrhousngList
     * @Description : 교구 입고 목록 호출
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/selectTchaidWrhousngList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidWrhousngList( TchaidVo TchaidVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<TchaidWrhousngVo> result = null;
        TchaidVo.setUser(user);
        
        result = tchaidService.selectTchaidWrhousngList(TchaidVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 교구 입고 처리 모달  
     *
     * @Title : tchaidWrhousngPopup
     * @Description : 교구 입고 처리 모달 
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/tchaidWrhousngPopup.html")
    public String tchaidWrhousngPopup(TchaidVo tchaidVo,Model model) throws Exception {
        model.addAttribute("tchaidVo", tchaidService.selectTchaid(tchaidVo)  );
        return "mng/tchaid/tchaidWrhousngPopup";
    }
    
    /**
     * 교구 입고 처리 
     *
     * @Title : modifyTchaidWrhousng
     * @Description : 교구 입고 처리 
     * @param TchaidVo TchaidVo 객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/tchaid/modifyTchaidWrhousng.do")
    @ResponseBody
    public Map<String, Object> modifyTchaidWrhousng(@Valid TchaidVo TchaidVo,TchaidWrhousngVo tchaidWrhousngVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        TchaidVo.setUser(user);
        tchaidWrhousngVo.setUser(user);
        
        retVal = tchaidService.insertTchaidWrhousng(tchaidWrhousngVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "입고처리 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "입고처리에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    
    
       
    
}