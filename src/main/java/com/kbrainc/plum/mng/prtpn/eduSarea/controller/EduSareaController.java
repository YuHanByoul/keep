package com.kbrainc.plum.mng.prtpn.eduSarea.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.code.service.CodeService;
import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.service.EduSareaService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 유아환경교육 -> 운영권역관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduSarea.controller
* - EduSareaController.java
* </pre>
**
@ClassName : EduSareaController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EduSareaController {
    
    @Autowired
    private EduSareaService eduSareaService;

    @Autowired
    private CommonService commonService;
    
    @Autowired
    private CodeService codeService; 
    
    /**
    * 운영권역관리 리스트화면으로 이동
    *
    * @Title : eduSareaListForm
    * @Description : 운영권역관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/eduSareaListForm.html")
    public String eduSareaListForm(Model model, HttpServletRequest request) throws Exception {
        
        return "mng/prtpn/eduSarea/eduSareaList";
    }
    
    @RequestMapping(value = "/mng/prtpn/eduSarea/eudSareaSignguSettingPopup.html")
    public String infntSchdlInsertPopup(@RequestParam(value ="sareaid",required = false) int sareaid, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("sareaid", sareaid);
        
        model.addAttribute("ctprvnCdList", eduSareaService.selectCtprvnCdList(sareaid));
        
        return "mng/prtpn/eduSarea/eudSareaSignguSettingPopup";
    }
    
    /**
     * 시군구코드 목록을 조회한다.
     * 
     * @Title : signguCodeList
     * @Description : 시군구코드 목록을 조회한다.
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return Map<String, Object> 코드 목록
     */
    @RequestMapping(value = "/mng/prtpn/eduSarea/signguCodeList.do")
    @ResponseBody
    public Map<String, Object> signguCodeList(EduSareaVo eduSareaVo) throws Exception {

        //List<EduSareaVo> list = eduSareaService.selectSignguCodeList(eduSareaVo);
        List<EduSareaVo> list = eduSareaService.selectAddrSignguList(eduSareaVo);

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("list", list);

        return response;
    }    
    
    /**
    * 운영권역관리 등록화면으로 이동
    *
    * @Title : eduSareaInsertForm
    * @Description : 운영권역관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/eduSareaInsertForm.html")
    public String eduSareaInsertForm(Model model) throws Exception {
        model.addAttribute("ctprvnCdList", eduSareaService.selectAddrCtprvnList());
        return "mng/prtpn/eduSarea/eduSareaInsertForm";
    }
    
    /**
    * 운영권역관리 수정화면으로 이동
    *
    * @Title : eduSareaUpdateForm
    * @Description : 운영권역관리 수정화면으로 이동
    * @param eduSareaVo 운영권역관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/eduSareaUpdateForm.html")
    public String eduSareaUpdateForm(EduSareaVo eduSareaVo, Model model) throws Exception {
        EduSareaVo result = null;
        result = eduSareaService.selectEduSareaInfo(eduSareaVo);
        model.addAttribute("eduSarea", result);
        model.addAttribute("ctprvnCdList", eduSareaService.selectAddrCtprvnList());

        return "mng/prtpn/eduSarea/eduSareaUpdate";
    }
   
    
    /**
    * 운영권역관리 게시글 목록 조회
    *
    * @Title : selectEduSareaList
    * @Description : 운영권역관리 게시글 목록 조회
    * @param eduSareaVo 운영권역관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/selectEduSareaList.do")
    @ResponseBody
    public Map<String, Object> selectEduSareaList(EduSareaVo eduSareaVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EduSareaVo> result = null;
        
        result =  eduSareaService.selectEduSareaList(eduSareaVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        List<Map<String, Object>> instList = commonService.selectAlowedInstList();
        
        resultMap.put("list", result);
        resultMap.put("instList", instList);

        return resultMap;
    }
    
    /**
    * 운영권역관리 게시글 등록 기능
    *
    * @Title : insertEduSarea
    * @Description : 운영권역관리 수정 기능
    * @param eduSareaVo 운영권역관리 객체
    * @param bindingResult 운영권역관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/insertEduSarea.do")
    @ResponseBody
    public Map<String, Object> insertEduSarea(@Valid EduSareaVo eduSareaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        eduSareaVo.setUser(user);
        List<String> dplctCtprvnCdList = eduSareaService.selectDplctCtprvnCdList(eduSareaVo);

        int dplctCnt = 0;
        int retVal = 0;

        for (String ctprvnCd : eduSareaVo.getCtprvnCds()) {
            if (dplctCtprvnCdList.contains(ctprvnCd)) {
                dplctCnt++;
            }
        }

        if (dplctCnt > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "타 운영권역과 중복되는 지역이 있습니다.");
        }else {
            retVal = eduSareaService.insertEduSarea(eduSareaVo);
            
            if (retVal > 0) {
                resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
                resultMap.put("msg", "등록에 성공하였습니다.");
            } else {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "등록에 실패했습니다.");
            }
        }

        return resultMap;
    }
    
    /**
    * 운영권역관리 게시글 수정 기능
    *
    * @Title : updateEduSarea
    * @Description : 운영권역관리 게시글 수정 기능
    * @param eduSareaVo 운영권역관리 객체
    * @param bindingResult 운영권역관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/updateEduSarea.do")
    @ResponseBody
    public Map<String, Object> updateEduSarea(@Valid EduSareaVo eduSareaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        eduSareaVo.setUser(user);
        List<String> dplctCtprvnCdList  = eduSareaService.selectDplctCtprvnCdList(eduSareaVo);

        int dplctCnt = 0;
        int retVal = 0;

        for (String ctprvnCd : eduSareaVo.getCtprvnCds()) {
            if (dplctCtprvnCdList.contains(ctprvnCd)) {
                dplctCnt++;
            }
        }

        if (dplctCnt > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "타 운영권역과 중복되는 지역이 있습니다.");
        }else {
            retVal = eduSareaService.updateEduSarea(eduSareaVo);
            
            if (retVal > 0) {
                resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
                resultMap.put("msg", "수정에 성공하였습니다.");
            } else {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "수정에 실패했습니다.");
            }
        }

        return resultMap;
    }
    
    /**
    * 운영권역관리 세부지역설정
    *
    * @Title : updateEduSareaSignguSetting
    * @Description : 운영권역관리 세부지역설정
    * @param clssrmId
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/eduSarea/updateEduSareaSignguSetting.do")
    @ResponseBody
    public Map<String, Object> updateEduSareaSignguSetting(@Valid EduSareaVo eduSareaVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        eduSareaVo.setUser(user);
        eduSareaVo.setSareaId(eduSareaVo.getSignguList().get(0).getSareaId());
        eduSareaVo.setCtprvnGrp(eduSareaVo.getSignguList().get(0).getCtprvnCd());
        retVal = eduSareaService.updateEduSareaSignguSetting(eduSareaVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
        
        return resultMap;
            
    }        
}
