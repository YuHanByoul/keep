package com.kbrainc.plum.mng.prtpn.infntSchdl.controller;

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
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.service.EduClssRmService;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.prtpn.infntSchdl.model.InfntSchdlVo;
import com.kbrainc.plum.mng.prtpn.infntSchdl.service.InfntSchdlService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 유아환경교육 -> 교육일정관리 컨트롤러 클래스
 **
 * <pre>
 * com.kbrainc.plum.mng.prtpn.infntSchdl.controller - InfntSchdlController.java
 * </pre>
 **
 * @ClassName : InfntSchdlController
 * @Description : TODO
 * @author : Notebiz001
 * @date : 2023. 1. 9.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class InfntSchdlController {

    @Autowired
    private InfntSchdlService infntSchdlService;

    @Autowired
    private EduClssRmService eduClssRmService;

    /**
     * 교육일정관리 리스트화면으로 이동
     *
     * @Title : infntSchdlListForm
     * @Description : 교육일정관리 리스트 화면으로 이동
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/infntSchdlListForm.html")
    public String infntSchdlListForm(Model model, HttpServletRequest request) throws Exception {

        EduClssRmVo eduClssRmVo = new EduClssRmVo();
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));

        int curYear = 2022;
        Integer[] years = new Integer[4];

        for (int i = curYear, j = 0; i <= i + 3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        return "mng/prtpn/infntSchdl/infntSchdlList";
    }

    /**
     * 교육일정관리 입력 팝업화면으로 이동
     *
     * @Title : infntSchdlInsertPopup
     * @Description : 교육일정관리 입력 팝업화면으로 이동
     * @param clssrmId
     * @param clssrmNm 
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/infntSchdlInsertPopup.html")
    public String infntSchdlInsertPopup(@RequestParam(value ="clssrmId",required = false) int clssrmId, @RequestParam(value ="ym",required = false) String ym, Model model, HttpServletRequest request) throws Exception {
        
        //model.addAttribute("clssrmId", infntSchdlVo.getClssrmId());
        //model.addAttribute("clssrmNm", infntSchdlVo.getClssrmNm());
        EduClssRmVo eduClssRmVo = new EduClssRmVo();
        eduClssRmVo.setClssrmId(clssrmId);
        model.addAttribute("eduClssRm", eduClssRmService.selectEduClssRmInfo(eduClssRmVo));
        model.addAttribute("ym", ym);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        Date date = dateFormatter.parse(ym);

        SimpleDateFormat strFormatter = new SimpleDateFormat("yyyy년 MM월");
        String ymDesc = strFormatter.format(date);
        model.addAttribute("ymDesc", ymDesc);
        
        return "mng/prtpn/infntSchdl/infntSchdlInsertPopup";
    }

    /**
     * 교육일정관리 수정 팝업화면으로 이동
     *
     * @Title : infntSchdlInsertPopup
     * @Description : 교육일정관리 수정 팝업화면으로 이동
     * @param clssrmId
     * @param clssrmNm 
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/infntSchdlUpdatePopup.html")
    public String infntSchdlUpdatePopup(InfntSchdlVo infntSchdlVo, Model model, HttpServletRequest request) throws Exception {
        
        InfntSchdlVo result = null;
        result = infntSchdlService.selectInfntSchdlInfo(infntSchdlVo);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        Date date = dateFormatter.parse(result.getYm());
        
        SimpleDateFormat strFormatter = new SimpleDateFormat("yyyy년 MM월");
        String ymDesc = strFormatter.format(date);

        model.addAttribute("infntSchdl", result);
        model.addAttribute("ymDesc", ymDesc);

        return "mng/prtpn/infntSchdl/infntSchdlUpdatePopup";
    }



    /**
     * 교육일정관리 게시글 목록 조회
     *
     * @Title : selectInfntSchdlList
     * @Description : 교육일정관리 게시글 목록 조회
     * @param infntSchdlVo 교육일정관리 객체
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/selectInfntSchdlList.do")
    @ResponseBody
    public List<InfntSchdlVo> selectInfntSchdlList(InfntSchdlVo infntSchdlVo) throws Exception {
        //Map<String, Object> resultMap = new HashMap<>();
        List<InfntSchdlVo> result = null;
        result = infntSchdlService.selectInfntSchdlList(infntSchdlVo);
//        if (result.size() > 0) {
//            resultMap.put("totalCount", (result.get(0).getTotalCount()));
//        } else {
//            resultMap.put("totalCount", 0);
//        }
//        resultMap.put("list", result);

        return result;
    }

    /**
     * 교육일정관리 등록화면으로 이동
     *
     * @Title : infntSchdlInsertForm
     * @Description : 교육일정관리 등록화면으로 이동
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/infntSchdlInsertForm.html")
    public String infntSchdlInsertForm(Model model) throws Exception {

        EduClssRmVo eduClssRmVo = new EduClssRmVo();
        model.addAttribute("clssList", eduClssRmService.selectEduClssRmList(eduClssRmVo));

        int curYear = 2022;
        Integer[] years = new Integer[4];

        for (int i = curYear, j = 0; i <= i + 3 && j <= 3; i++, j++) {
            years[j] = i;
        }
        model.addAttribute("years", years);

        return "mng/prtpn/infntSchdl/infntSchdlInsertForm";
    }

    /**
     * 교육일정관리 게시글 등록 기능
     *
     * @Title : insertInfntSchdl
     * @Description : 교육일정관리 게시글 등록 기능
     * @param infntSchdlVo  교육일정관리 객체
     * @param bindingResult 교육일정관리 유효성 검증결과
     * @param user          사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/insertInfntSchdl.do")
    @ResponseBody
    public Map<String, Object> insertInfntSchdl(@Valid InfntSchdlVo infntSchdlVo, BindingResult bindingResult,
            @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntSchdlVo.setUser(user);
        int retVal = 0;
        retVal = infntSchdlService.insertInfntSchdl(infntSchdlVo);

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
    * @Title : updateInfntSchdl
    * @Description : 교육일정관리 게시글 수정 기능
    * @param infntSchdlVo 교육일정관리 객체
    * @param bindingResult 교육일정관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/updateInfntSchdl.do")
    @ResponseBody
    public Map<String, Object> updateInfntSchdl(@Valid InfntSchdlVo infntSchdlVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntSchdlVo.setUser(user);
        int retVal = 0;
        retVal = infntSchdlService.updateInfntSchdl(infntSchdlVo);
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
     * @Title : deleteInfntSchdl
     * @Description : 교육일정관리 게시글 삭제 기능
     * @param infntSchdlVo 교육일정관리 객체
     * @param bindingResult 교육일정관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/deleteInfntSchdl.do")
    @ResponseBody
    public Map<String, Object> deleteInfntSchdl(@Valid InfntSchdlVo infntSchdlVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        infntSchdlVo.setUser(user);
        int retVal = 0;
        retVal = infntSchdlService.deleteInfntSchdl(infntSchdlVo);
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
    * @Title : selectInfntPrgrmList
    * @Description : 교육일정관리 교육일정 리스트 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/prtpn/infntSchdl/selectInfntSchdlIdList.do")
    @ResponseBody
    public Map<String, Object> selectInfntSchdlIdList(String clssrmId) throws Exception {
        List<InfntSchdlVo> result = null;
        result =  infntSchdlService.selectInfntSchdlIdList(clssrmId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("schdlIdList", result);
        return resultMap;
            
    }    
}
