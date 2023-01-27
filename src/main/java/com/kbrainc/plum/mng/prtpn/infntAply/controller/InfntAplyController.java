package com.kbrainc.plum.mng.prtpn.infntAply.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.service.EduClssRmService;
import com.kbrainc.plum.mng.prtpn.infntAply.model.InfntAplyVo;
import com.kbrainc.plum.mng.prtpn.infntAply.service.InfntAplyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 유아환경교육 -> 교육신청관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.controller
* - InfntAplyController.java
* </pre>
**
@ClassName : InfntAplyController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class InfntAplyController {
    
    @Autowired
    private InfntAplyService infntAplyService;
    
    @Autowired
    private EduClssRmService eduClssRmService; 
    
    /**
    * 교육신청관리 리스트화면으로 이동
    *
    * @Title : infntAplyListForm
    * @Description : 교육신청관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/infntAplyListForm.html")
    public String infntAplyListForm(Model model, HttpServletRequest request) throws Exception {
        
        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        return "mng/prtpn/infntAply/infntAplyList";
    }
    
    /**
    * 교육신청관리 등록화면으로 이동
    *
    * @Title : infntAplyInsertForm
    * @Description : 교육신청관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/infntAplyInsertForm.html")
    public String infntAplyInsertForm(Model model) throws Exception {

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        return "mng/prtpn/infntAply/infntAplyInsertForm";
    }
    
    /**
    * 교육신청관리 수정화면으로 이동
    *
    * @Title : infntAplyUpdateForm
    * @Description : 교육신청관리 수정화면으로 이동
    * @param infntAplyVo 교육신청관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/infntAplyUpdateForm.html")
    public String infntAplyUpdateForm(InfntAplyVo infntAplyVo, Model model) throws Exception {
        int curYear = 2022;
        Integer[] years = new Integer[4];
        
        for(int i = curYear, j = 0; i <= i+3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        EduClssRmVo eduClssRmVo = new EduClssRmVo(); 
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));
        
        InfntAplyVo result = null;
        result = infntAplyService.selectInfntAplyInfo(infntAplyVo);

        model.addAttribute("infntAply", result);
        return "mng/prtpn/infntAply/infntAplyUpdate";
    }
   
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/selectInfntAplyList.do")
    @ResponseBody
    public Map<String, Object> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntAplyVo> result = null;
        result =  infntAplyService.selectInfntAplyList(infntAplyVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 교육신청관리 게시글 등록 기능
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 수정 기능
    * @param infntAplyVo 교육신청관리 객체
    * @param bindingResult 교육신청관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/insertInfntAply.do")
    @ResponseBody
    public Map<String, Object> insertInfntAply(@Valid InfntAplyVo infntAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntAplyVo.setUser(user);
        int retVal = 0;
        retVal = infntAplyService.insertInfntAply(infntAplyVo);
        
        //retVal = infntAplyService.insertInfntAplyTme(infntAplyVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
            resultMap.put("aplyId", infntAplyVo.getAplyId());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
            resultMap.put("aplyId", 0);
        }
        return resultMap;
    }
    
    /**
    * 교육신청관리 게시글 복사 기능
    **
    @Title : insertInfntAplyCopy
    * @Description : 교육신청관리 게시글 복사 기능
    * @param copyAplyIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/insertInfntAplyCopy.do")
    @ResponseBody
    public Map<String, Object> insertInfntAplyCopy(@RequestParam("copyAplyIds") String[] copyAplyIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = infntAplyService.insertInfntAplyCopy(copyAplyIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "복사에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "복사에 실패했습니다.");
        }

        return reseultMap;
    }

    
    /**
    * 교육신청관리 게시글 수정 기능
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 게시글 수정 기능
    * @param infntAplyVo 교육신청관리 객체
    * @param bindingResult 교육신청관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/updateInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateInfntAply(@Valid InfntAplyVo infntAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntAplyVo.setUser(user);
        int retVal = 0;
        retVal = infntAplyService.updateInfntAply(infntAplyVo);
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
    * 교육신청 회차 리스트 등록 기능
    **
    @Title : insertInfntAplyTmeList
    * @Description : 교육신청 회차 리스트 등록 기능
    * @param infntAplyVoList
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return List<Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/insertInfntAplyTmeList.do")
    @ResponseBody
    public Map<String, Object> insertInfntAplyTmeList(@RequestBody InfntAplyVo infntAplyVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        infntAplyVo.setUser(user);

        int retVal = 0;
/*        List<Object> response = new ArrayList<Object>();
        response.add(Constant.REST_API_RESULT_SUCCESS);
        if (bindingResult.hasErrors()) {
            return response;
        }
        for (InfntAplyVo infntAplyVo : infntAplyVoList.getInfntAplyVoList()) {
            response.add(insertInfntAplyTme(user, infntAplyVo));
        }
        return response; */

        retVal = infntAplyService.insertInfntAplyTme(infntAplyVo);
        
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
    * 교육신청 회차 리스트 등록 기능
    **
    @Title : insertInfntAplyTme
    * @Description : 교육신청 회차 리스트 등록 기능
    * @param user
    * @param infntAplyVoParam
    * @return
    * @throws Exception
    * @return Map<String,String>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/insertInfntAplyTme.do")
    @ResponseBody
    public Map<String, String> insertInfntAplyTme(@UserInfo UserVo user, @RequestBody InfntAplyVo infntAplyVoParam) throws Exception {

        Map<String, String> response = new HashMap<String, String>();
        infntAplyVoParam.setUser(user);
        int retVal = 0;
        retVal = infntAplyService.insertInfntAplyTme(infntAplyVoParam);
        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        return response;
    }

    /**
    * 교육신청관리 회차 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 교육신청관리 회차 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntAply/selectInfntAplyTmeList.do")
    @ResponseBody
    public Map<String, Object> selectInfntAplyTmeList(InfntAplyVo infntAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<InfntAplyVo> result = null;
        result =  infntAplyService.selectInfntAplyTmeList(infntAplyVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("infntAplyTmeList", result);
        return resultMap;
    }
}
