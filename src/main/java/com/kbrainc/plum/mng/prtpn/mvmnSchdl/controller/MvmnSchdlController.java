package com.kbrainc.plum.mng.prtpn.mvmnSchdl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.model.EduSareaVo;
import com.kbrainc.plum.mng.prtpn.eduSarea.service.EduSareaService;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.mng.prtpn.mvmnSchdl.model.MvmnSchdlVo;
import com.kbrainc.plum.mng.prtpn.mvmnSchdl.service.MvmnSchdlService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 유아환경교육 -> 교육일정관리 컨트롤러 클래스
 **
 * <pre>
 * com.kbrainc.plum.mng.prtpn.mvmnSchdl.controller - MvmnSchdlController.java
 * </pre>
 **
 * @ClassName : MvmnSchdlController
 * @Description : TODO
 * @author : Notebiz001
 * @date : 2023. 1. 9.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class MvmnSchdlController {

    @Autowired
    private MvmnSchdlService mvmnSchdlService;

    @Autowired
    private EduSareaService eduSareaService;

    /**
     * 교육일정관리 리스트화면으로 이동
     *
     * @Title : mvmnSchdlListForm
     * @Description : 교육일정관리 리스트 화면으로 이동
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/mvmnSchdlListForm.html")
    public String mvmnSchdlListForm(Model model, HttpServletRequest request) throws Exception {

        EduSareaVo eduSareaVo = new EduSareaVo();
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));

        int curYear = 2022;
        Integer[] years = new Integer[4];

        for (int i = curYear, j = 0; i <= i + 3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        return "mng/prtpn/mvmnSchdl/mvmnSchdlList";
    }

    /**
     * 교육일정관리 입력 팝업화면으로 이동
     *
     * @Title : mvmnSchdlInsertPopup
     * @Description : 교육일정관리 입력 팝업화면으로 이동
     * @param sareaId
     * @param sareaNm 
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/mvmnSchdlInsertPopup.html")
    public String mvmnSchdlInsertPopup(@RequestParam(value ="sareaId",required = false) int sareaId, @RequestParam(value ="ym",required = false) String ym, Model model, HttpServletRequest request) throws Exception {
        
        //model.addAttribute("sareaId", mvmnSchdlVo.getSareaId());
        //model.addAttribute("sareaNm", mvmnSchdlVo.getSareaNm());
        EduSareaVo eduSareaVo = new EduSareaVo();
        eduSareaVo.setSareaId(sareaId);
        model.addAttribute("eduSarea", eduSareaService.selectEduSareaInfo(eduSareaVo));
        model.addAttribute("ym", ym);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        Date date = dateFormatter.parse(ym);

        SimpleDateFormat strFormatter = new SimpleDateFormat("yyyy년 MM월");
        String ymDesc = strFormatter.format(date);
        model.addAttribute("ymDesc", ymDesc);
        
        return "mng/prtpn/mvmnSchdl/mvmnSchdlInsertPopup";
    }

    /**
     * 교육일정관리 수정 팝업화면으로 이동
     *
     * @Title : mvmnSchdlInsertPopup
     * @Description : 교육일정관리 수정 팝업화면으로 이동
     * @param sareaId
     * @param sareaNm 
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/mvmnSchdlUpdatePopup.html")
    public String mvmnSchdlUpdatePopup(MvmnSchdlVo mvmnSchdlVo, Model model, HttpServletRequest request) throws Exception {
        
        MvmnSchdlVo result = null;
        result = mvmnSchdlService.selectMvmnSchdlInfo(mvmnSchdlVo);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        Date date = dateFormatter.parse(result.getYm());
        
        SimpleDateFormat strFormatter = new SimpleDateFormat("yyyy년 MM월");
        String ymDesc = strFormatter.format(date);

        model.addAttribute("mvmnSchdl", result);
        model.addAttribute("ymDesc", ymDesc);

        return "mng/prtpn/mvmnSchdl/mvmnSchdlUpdatePopup";
    }



    /**
     * 교육일정관리 게시글 목록 조회
     *
     * @Title : selectMvmnSchdlList
     * @Description : 교육일정관리 게시글 목록 조회
     * @param mvmnSchdlVo 교육일정관리 객체
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/selectMvmnSchdlList.do")
    @ResponseBody
    public List<MvmnSchdlVo> selectMvmnSchdlList(MvmnSchdlVo mvmnSchdlVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MvmnSchdlVo> result = null;
        result = mvmnSchdlService.selectMvmnSchdlList(mvmnSchdlVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return result;
    }

    /**
     * 교육일정관리 등록화면으로 이동
     *
     * @Title : mvmnSchdlInsertForm
     * @Description : 교육일정관리 등록화면으로 이동
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/mvmnSchdlInsertForm.html")
    public String mvmnSchdlInsertForm(Model model) throws Exception {

        EduSareaVo eduSareaVo = new EduSareaVo();
        model.addAttribute("sareaList", eduSareaService.selectEduSareaList(eduSareaVo));

        int curYear = 2022;
        Integer[] years = new Integer[4];

        for (int i = curYear, j = 0; i <= i + 3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        return "mng/prtpn/mvmnSchdl/mvmnSchdlInsertForm";
    }

    /**
     * 교육일정관리 게시글 등록 기능
     *
     * @Title : insertMvmnSchdl
     * @Description : 교육일정관리 게시글 등록 기능
     * @param mvmnSchdlVo  교육일정관리 객체
     * @param bindingResult 교육일정관리 유효성 검증결과
     * @param user          사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/insertMvmnSchdl.do")
    @ResponseBody
    public Map<String, Object> insertMvmnSchdl(@Valid MvmnSchdlVo mvmnSchdlVo, BindingResult bindingResult,
            @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnSchdlVo.setUser(user);
        int retVal = 0;
        retVal = mvmnSchdlService.insertMvmnSchdl(mvmnSchdlVo);

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
    * 교육일정관리 게시글 수정 기능
    *
    * @Title : updateMvmnSchdl
    * @Description : 교육일정관리 게시글 수정 기능
    * @param mvmnSchdlVo 교육일정관리 객체
    * @param bindingResult 교육일정관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/updateMvmnSchdl.do")
    @ResponseBody
    public Map<String, Object> updateMvmnSchdl(@Valid MvmnSchdlVo mvmnSchdlVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnSchdlVo.setUser(user);
        int retVal = 0;
        retVal = mvmnSchdlService.updateMvmnSchdl(mvmnSchdlVo);
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
     * 교육일정관리 게시글 삭제 기능
     *
     * @Title : deleteMvmnSchdl
     * @Description : 교육일정관리 게시글 삭제 기능
     * @param mvmnSchdlVo 교육일정관리 객체
     * @param bindingResult 교육일정관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/deleteMvmnSchdl.do")
    @ResponseBody
    public Map<String, Object> deleteMvmnSchdl(@Valid MvmnSchdlVo mvmnSchdlVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        mvmnSchdlVo.setUser(user);
        int retVal = 0;
        retVal = mvmnSchdlService.deleteMvmnSchdl(mvmnSchdlVo);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }
        return resultMap;
    }    
    
    /**
    * 교육일정관리 교육일정 리스트 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육일정관리 교육일정 리스트 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/mvmnSchdl/selectMvmnSchdlIdList.do")
    @ResponseBody
    public Map<String, Object> selectMvmnSchdlIdList(String sareaId) throws Exception {
        List<MvmnSchdlVo> result = null;
        result =  mvmnSchdlService.selectMvmnSchdlIdList(sareaId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("schdlIdList", result);
        return resultMap;
            
    }    
}
