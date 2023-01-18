package com.kbrainc.plum.mng.prtpn.infntPrgrm.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.service.EduClssRmService;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVoList;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.service.InfntPrgrmService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 유아환경교육 -> 교육프로그램관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.controller
* - InfntPrgrmController.java
* </pre>
**
@ClassName : InfntPrgrmController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class InfntPrgrmController {
    
    @Autowired
    private InfntPrgrmService infntPrgrmService;
    
    @Autowired
    private EduClssRmService eduClssRmService; 
    
    /**
    * 교육프로그램관리 리스트화면으로 이동
    *
    * @Title : infntPrgrmListForm
    * @Description : 교육프로그램관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmListForm.html")
    public String infntPrgrmListForm(Model model, HttpServletRequest request) throws Exception {
        
        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        return "mng/prtpn/infntPrgrm/infntPrgrmList";
    }
    
    /**
    * 교육프로그램관리 등록화면으로 이동
    *
    * @Title : infntPrgrmInsertForm
    * @Description : 교육프로그램관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmInsertForm.html")
    public String infntPrgrmInsertForm(Model model) throws Exception {

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));

        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        return "mng/prtpn/infntPrgrm/infntPrgrmInsertForm";
    }
    
    /**
    * 교육프로그램관리 수정화면으로 이동
    *
    * @Title : infntPrgrmUpdateForm
    * @Description : 교육프로그램관리 수정화면으로 이동
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/infntPrgrmUpdateForm.html")
    public String infntPrgrmUpdateForm(InfntPrgrmVo infntPrgrmVo, Model model) throws Exception {
        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        InfntPrgrmVo result = null;
        result = infntPrgrmService.selectInfntPrgrmInfo(infntPrgrmVo);
        model.addAttribute("infntPrgrm", result);
        return "mng/prtpn/infntPrgrm/infntPrgrmUpdate";
    }
   
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/selectInfntPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectInfntPrgrmList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntPrgrmVo> result = null;
        result =  infntPrgrmService.selectInfntPrgrmList(infntPrgrmVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 게시글 등록 기능
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 수정 기능
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrm.do")
    @ResponseBody
    public Map<String, Object> insertInfntPrgrm(@Valid InfntPrgrmVo infntPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.insertInfntPrgrm(infntPrgrmVo);
        
        //retVal = infntPrgrmService.insertInfntPrgrmTme(infntPrgrmVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
            resultMap.put("prgrmId", infntPrgrmVo.getPrgrmId());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
            resultMap.put("prgrmId", 0);
        }
        return resultMap;
    }
    
    /**
    * 교육프로그램관리 게시글 수정 기능
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정 기능
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @param bindingResult 교육프로그램관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/updateInfntPrgrm.do")
    @ResponseBody
    public Map<String, Object> updateInfntPrgrm(@Valid InfntPrgrmVo infntPrgrmVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntPrgrmVo.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.updateInfntPrgrm(infntPrgrmVo);
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
    * 교육프로그램 회차 리스트 등록 기능
    **
    @Title : insertInfntPrgrmTmeList
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param infntPrgrmVoList
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return List<Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> insertInfntPrgrmTmeList(@RequestBody InfntPrgrmVo infntPrgrmVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        infntPrgrmVo.setUser(user);

        int retVal = 0;
/*        List<Object> response = new ArrayList<Object>();
        response.add(Constant.REST_API_RESULT_SUCCESS);
        if (bindingResult.hasErrors()) {
            return response;
        }
        for (InfntPrgrmVo infntPrgrmVo : infntPrgrmVoList.getInfntPrgrmVoList()) {
            response.add(insertInfntPrgrmTme(user, infntPrgrmVo));
        }
        return response; */

        retVal = infntPrgrmService.insertInfntPrgrmTme(infntPrgrmVo);
        
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
    * 교육프로그램 회차 리스트 등록 기능
    **
    @Title : insertInfntPrgrmTme
    * @Description : 교육프로그램 회차 리스트 등록 기능
    * @param user
    * @param infntPrgrmVoParam
    * @return
    * @throws Exception
    * @return Map<String,String>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/insertInfntPrgrmTme.do")
    @ResponseBody
    public Map<String, String> insertInfntPrgrmTme(@UserInfo UserVo user, @RequestBody InfntPrgrmVo infntPrgrmVoParam) throws Exception {

        Map<String, String> response = new HashMap<String, String>();
        infntPrgrmVoParam.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.insertInfntPrgrmTme(infntPrgrmVoParam);
        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return response;
    }

    /**
     * 교육프로그램 회차 리스트 수정 기능
     **
    @Title : updateInfntPrgrmTmeList
     * @Description : 교육프로그램 회차 리스트 수정 기능
     * @param infntPrgrmVoList
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return List<Object>
     */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/updateInfntPrgrmTmeList.do")
    @ResponseBody
    public List<Object> updateInfntPrgrmTmeList(@RequestBody @Valid InfntPrgrmVoList infntPrgrmVoList, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        
        List<Object> response = new ArrayList<Object>();
        response.add(Constant.REST_API_RESULT_SUCCESS);
        if (bindingResult.hasErrors()) {
            return response;
        }
        for (InfntPrgrmVo infntPrgrmVo : infntPrgrmVoList.getInfntPrgrmVoList()) {
            response.add(updateInfntPrgrmTme(user, infntPrgrmVo));
        }
        return response;
    }
    
    /**
     * 교육프로그램 회차 리스트 수정 기능
     **
    @Title : updateInfntPrgrmTme
     * @Description : 교육프로그램 회차 리스트 수정 기능
     * @param user
     * @param infntPrgrmVoParam
     * @return
     * @throws Exception
     * @return Map<String,String>
     */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/updateInfntPrgrmTme.do")
    @ResponseBody
    public Map<String, String> updateInfntPrgrmTme(@UserInfo UserVo user, @RequestBody InfntPrgrmVo infntPrgrmVoParam) throws Exception {
        
        Map<String, String> response = new HashMap<String, String>();
        infntPrgrmVoParam.setUser(user);
        int retVal = 0;
        retVal = infntPrgrmService.updateInfntPrgrmTme(infntPrgrmVoParam);
        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return response;
    }

    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntPrgrm/selectInfntPrgrmTmeList.do")
    @ResponseBody
    public Map<String, Object> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntPrgrmVo> result = null;
        result =  infntPrgrmService.selectInfntPrgrmTmeList(infntPrgrmVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("infntPrgrmTmeList", result);
        return resultMap;
    }
}
