package com.kbrainc.plum.mng.prtpn.mvmnAply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeService;
import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.service.EduSareaService;
import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.mng.prtpn.mvmnAply.service.MvmnAplyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;

/**
* 유아환경교육 -> 교육신청관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnAply.controller
* - MvmnAplyController.java
* </pre>
**
@ClassName : MvmnAplyController
* @Description : TODO
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class MvmnAplyController {
    
    @Autowired
    private MvmnAplyService mvmnAplyService;
    
    @Autowired
    private EduSareaService eduSareaService; 

    @Autowired
    private CodeService codeService; 
    
    /**
    * 교육신청관리 리스트화면으로 이동
    *
    * @Title : mvmnAplyListForm
    * @Description : 교육신청관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyListForm.html")
    public String mvmnAplyListForm(Model model, HttpServletRequest request) throws Exception {
        
        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));
        
        List<MvmnAplyVo> years = mvmnAplyService.selectEduYear();
        model.addAttribute("years", years);
        return "mng/prtpn/mvmnAply/mvmnAplyList";
    }

    /**
     * 교육신청관리 상세화면으로 이동
     *
     * @Title : mvmnAplyDetailForm
     * @Description : 교육신청관리 상세 화면으로 이동
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyDetailForm.html")
    public String mvmnAplyDetailForm(MvmnAplyVo mvmnAplyVo, Model model) throws Exception {
        model.addAttribute("mvmnAplyVo", mvmnAplyVo);
        CodeVo codeVo = new CodeVo();
        codeVo.setCdgrpid("180");
        model.addAttribute("sttCodeList", codeService.selectCodeList(codeVo));
        model.addAttribute("mvmnAplyParamVo", mvmnAplyVo);
        
        return "mng/prtpn/mvmnAply/mvmnAplyDetailForm";
    }
    
    /**
    * 교육신청관리 등록화면으로 이동
    *
    * @Title : mvmnAplyInsertForm
    * @Description : 교육신청관리 등록화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyInsertForm.html")
    public String mvmnAplyInsertForm(MvmnAplyVo mvmnAplyVo, Model model) throws Exception {
        Integer[] years = new Integer[21];
        
        for(int i = 2020, j = 0; i < 2041; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);
        
        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("mvmnAplyVo", mvmnAplyService.selectMvmnAplyInsertInfo(mvmnAplyVo));
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));
        
        model.addAttribute("tmeSchdlList", mvmnAplyService.selectTmeSchdlList(mvmnAplyVo));
        
        return "mng/prtpn/mvmnAply/mvmnAplyInsertForm";
    }
    
    /**
    * 교육신청관리 수정화면으로 이동
    *
    * @Title : mvmnAplyUpdateForm
    * @Description : 교육신청관리 수정화면으로 이동
    * @param mvmnAplyVo 교육신청관리 객체
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyUpdateForm.html")
    public String mvmnAplyUpdateForm(MvmnAplyVo mvmnAplyVo, Model model) throws Exception {
        Integer[] years = new Integer[21];
        
        for(int i = 2020, j = 0; i < 2041; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        EduSareaVo eduSareaVo = new EduSareaVo(); 
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));
        model.addAttribute("tmeSchdlList", mvmnAplyService.selectTmeSchdlList(mvmnAplyVo));        
        MvmnAplyVo result = null;
        result = mvmnAplyService.selectMvmnAplyInfo(mvmnAplyVo);

        model.addAttribute("mvmnAply", result);
        return "mng/prtpn/mvmnAply/mvmnAplyUpdate";
    }
   
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        result =  mvmnAplyService.selectMvmnAplyList(mvmnAplyVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }

    /**
    * 교육신청관리 게시글 상세 목록 조회
    *
    * @Title : selectMvmnAplyDetailList
    * @Description : 교육신청관리 게시글 상세 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyDetailList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyDetailList(MvmnAplyVo mvmnAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        result =  mvmnAplyService.selectMvmnAplyDetailList(mvmnAplyVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }

    /**
     * 신청마감 레이어팝업화면.
     *
     * @Title       : infntAplyUserSearchPopup 
     * @Description : 신청인 회원검색 레이어팝업화면.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyClosePopup.html")
    public String mvmnAplyClosePopup(MvmnAplyVo mvmnAplyVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("user",user);
        model.addAttribute("mvmnAplyParamVo",mvmnAplyVo);
        return "mng/prtpn/mvmnAply/mvmnAplyClosePopup";
    }

    /**
     * 교육신청관리 게시글 신청마감 리스트 조회
     *
     * @Title : selectMvmnAplyCloseList
     * @Description : 교육신청관리 게시글 신청마감 리스트 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyCloseList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyCloseList(MvmnAplyVo mvmnAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        result =  mvmnAplyService.selectMvmnAplyCloseList(mvmnAplyVo);
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
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 수정 기능
    * @param mvmnAplyVo 교육신청관리 객체
    * @param bindingResult 교육신청관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/insertMvmnAply.do")
    @ResponseBody
    public Map<String, Object> insertMvmnAply(@Valid MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnAplyVo.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.insertMvmnAply(mvmnAplyVo);
        
        //retVal = mvmnAplyService.insertMvmnAplyTme(mvmnAplyVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
            resultMap.put("aplyId", mvmnAplyVo.getAplyId());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
            resultMap.put("aplyId", 0);
        }
        return resultMap;
    }
    
    /**
    * 교육신청관리 게시글 수정 기능
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 게시글 수정 기능
    * @param mvmnAplyVo 교육신청관리 객체
    * @param bindingResult 교육신청관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/updateMvmnAply.do")
    @ResponseBody
    public Map<String, Object> updateMvmnAply(@Valid MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnAplyVo.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.updateMvmnAply(mvmnAplyVo);
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
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdMvmnAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @param bindingResult 교육신청관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/updateSttsCdMvmnAply.do")
    @ResponseBody
    public Map<String, Object> updateSttsCdMvmnAply(@Valid MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        /*if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }*/
        mvmnAplyVo.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.updateSttsCdMvmnAply(mvmnAplyVo);
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
     * 교육신청관리 신청마감 수정 기능
     *
     * @Title : updateMvmnAplyClose
     * @Description : 교육신청관리 신청마감 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @param bindingResult 교육신청관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/updateMvmnAplyClose.do")
    @ResponseBody
    public Map<String, Object> updateMvmnAplyClose(@Valid MvmnAplyVo mvmnAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        mvmnAplyVo.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.updateMvmnAplyClose(mvmnAplyVo);
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
    @Title : insertMvmnAplyTmeList
    * @Description : 교육신청 회차 리스트 등록 기능
    * @param mvmnAplyVoList
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return List<Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/insertMvmnAplyTmeList.do")
    @ResponseBody
    public Map<String, Object> insertMvmnAplyTmeList(@RequestBody MvmnAplyVo mvmnAplyVo, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        mvmnAplyVo.setUser(user);

        int retVal = 0;
/*        List<Object> response = new ArrayList<Object>();
        response.add(Constant.REST_API_RESULT_SUCCESS);
        if (bindingResult.hasErrors()) {
            return response;
        }
        for (MvmnAplyVo mvmnAplyVo : mvmnAplyVoList.getMvmnAplyVoList()) {
            response.add(insertMvmnAplyTme(user, mvmnAplyVo));
        }
        return response; */

        retVal = mvmnAplyService.insertMvmnAplyTme(mvmnAplyVo);
        
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
    @Title : insertMvmnAplyTme
    * @Description : 교육신청 회차 리스트 등록 기능
    * @param user
    * @param mvmnAplyVoParam
    * @return
    * @throws Exception
    * @return Map<String,String>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/insertMvmnAplyTme.do")
    @ResponseBody
    public Map<String, String> insertMvmnAplyTme(@UserInfo UserVo user, @RequestBody MvmnAplyVo mvmnAplyVoParam) throws Exception {

        Map<String, String> response = new HashMap<String, String>();
        mvmnAplyVoParam.setUser(user);
        int retVal = 0;
        retVal = mvmnAplyService.insertMvmnAplyTme(mvmnAplyVoParam);
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
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 회차 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyTmeList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        result =  mvmnAplyService.selectMvmnAplyTmeList(mvmnAplyVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("mvmnAplyTmeList", result);
        return resultMap;
    }
    
    /**
     * 신청인 회원검색 레이어팝업화면.
     *
     * @Title       : mvmnAplyUserSearchPopup 
     * @Description : 신청인 회원검색 레이어팝업화면.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyUserSearchPopup.html")
    public String mvmnAplyUserSearchPopup(MvmnAplyVo mvmnAplyVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("user",user);
        return "mng/prtpn/mvmnAply/mvmnAplyUserSearchPopup";
    }
    
    /**
    * 신청인 회원 검색.
    *
    * @Title       : selectInstUserList 
    * @Description :  신청인 회원 검색.
    * @param InstVo instVo 객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyUser.do")
    @ResponseBody
    public Map<String, Object> selectInstUserList(MvmnAplyVo mvmnAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnAplyVo> result = null;
        mvmnAplyVo.setUser(user);
        result = mvmnAplyService.selectMemberList(mvmnAplyVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }    
    
    /**
     * 교육신청관리 게시글 지역 리스트 조회
     *
     * @Title : selectMvmnAplyCtprvnCd
     * @Description : 교육신청관리 게시글 지역 리스트 조회
     * @param sareaid
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplyCtprvnCd.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplyCtprvnCd(@RequestParam(value ="sareaId", required = true) String sareaId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EduSareaVo> ctprvnCdList = null;
        ctprvnCdList = eduSareaService.selectCtprvnCdList(Integer.parseInt(sareaId));
        resultMap.put("ctprvnCdList", ctprvnCdList);
        
        return resultMap;
    }  
    
    /**
     * 교육신청관리 게시글 시군구 리스트 조회
     *
     * @Title : selectMvmnAplySignguCd
     * @Description : 교육신청관리 게시글 시군구 리스트 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/selectMvmnAplySignguCd.do")
    @ResponseBody
    public Map<String, Object> selectMvmnAplySignguCd(@RequestParam(value ="sareaId", required = true) String sareaId, @RequestParam(value ="ctprvnCd", required = true) String ctprvnCd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EduSareaVo> signguCdList = null;
        EduSareaVo eduSareaParamVo = new EduSareaVo();
        eduSareaParamVo.setSareaId(Integer.parseInt(sareaId));
        eduSareaParamVo.setCtprvnCd(ctprvnCd);
        
        signguCdList = eduSareaService.selectAddrSignguList(eduSareaParamVo);
        
        resultMap.put("signguCdList", signguCdList);
        
        return resultMap;
    }    
    /**
    * 교육일정관리 교육신청자 검색결과 엑셀 다운로드
    **
      @Title : mvmnAplyExcelDownList
    * @Description : 교육일정관리 교육신청자 검색결과 엑셀 다운로드
    * @param request
    * @param response
    * @param mvmnAplyVo
    * @throws Exception
    * @return void
    */
    @RequestMapping(value = "/mng/prtpn/mvmnAply/mvmnAplyExcelDownList.do")
    public void mvmnAplyExcelDownList(HttpServletRequest request, HttpServletResponse response, MvmnAplyVo mvmnAplyVo) throws Exception {
        mvmnAplyService.mvmnAplyExcelDownList(mvmnAplyVo, response, request);
    }     
}
