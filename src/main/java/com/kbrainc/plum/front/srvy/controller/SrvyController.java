package com.kbrainc.plum.front.srvy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnAnsVo;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.front.srvy.service.SrvyServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.DateTimeUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
 * 설문조사 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.controller
 * - SrvyController.java
 * </pre>
 *
 * @ClassName : SrvyController
 * @Description : 설문조사 Controller
 * @author : KBRAINC
 * @date : 2023. 2. 28.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.SrvyController")
@Alias("front.SrvyController")
public class SrvyController {

    @Resource(name = "front.SrvyServiceImpl")
    private SrvyServiceImpl srvyService;
    
    
    /**
     * 설문조사 목록 화면
     *
     * @Title : srvyMngForm
     * @Description : 설문조사 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = {"/front/srvy/srvyListForm.html"})
    public String srvyListForm() throws Exception {
        return "front/srvy/srvyList";
    }
    
    /**
     * 설문조사 참여이력 목록 화면
     *
     * @Title : srvyHstryListForm
     * @Description : 설문조사 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = {"/front/srvyHstry/srvyHstryListForm.html"})
    public String srvyHstryListForm() throws Exception {
        return "front/srvy/srvyHstryList";
    }
    
    /**
    * 설문 제출 팝업
    *
    * @Title : srvySbmsnInsertPopup
    * @Description : 설문 제출 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/srvy/srvySbmsnInsertPopup.html")
    public String srvySbmsnInsertPopup(SrvyVo srvyVo, @UserInfo UserVo user, Model model) throws Exception {
        srvyVo.setUser(user);
        model.addAttribute("qitemList", srvyService.selectQitemList(srvyVo));
        SrvyVo srvyInfo = srvyService.selectSrvyInfo(srvyVo);
        model.addAttribute("srvyInfo", srvyInfo);
        model.addAttribute("infntPrgrmAplyid", srvyVo.getInfntPrgrmAplyid());
        model.addAttribute("mvmnPrgrmAplyid", srvyVo.getMvmnPrgrmAplyid());
        
        return "front/srvy/srvySbmsnInsertPopup";
    }
    
    /**
     * 설문 확인 팝업
     *
     * @Title : srvySbmsnInfoPopup
     * @Description : 설문 확인 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
     @RequestMapping(value = "/front/srvy/srvySbmsnInfoPopup.html")
     public String srvySbmsnInfoPopup(SrvyVo srvyVo, @UserInfo UserVo user, Model model) throws Exception {
         srvyVo.setUser(user);
         SrvyVo srvyInfo = srvyService.selectSrvyInfo(srvyVo);
         model.addAttribute("srvyInfo", srvyInfo);
         srvyVo.setSbmsnid(srvyInfo.getSbmsnid());
         model.addAttribute("ansList", srvyService.selectAnsList(srvyVo));
         
         return "front/srvy/srvySbmsnInfoPopup";
     }
    
    /**
    * 설문 목록 조회
    *
    * @Title : selectSrvyList
    * @Description : 설문 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/srvy/selectSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectSrvyList(SrvyVo srvyVo, @UserInfo UserVo user, @SiteInfo SiteInfoVo site) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        srvyVo.setUser(user);
        srvyVo.setSiteid(Integer.valueOf(site.getSiteid()));
        List<SrvyVo> result = srvyService.selectSrvyList(srvyVo);
                     
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
             
        return resultMap;
    }
    
    /**
    * 설문 참여 목록 조회
    *
    * @Title : selectSrvyList
    * @Description : 설문 참여 목록 조회
    * @param srvyVo SrvyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/srvyHstry/selectSrvyHstryList.do")
    @ResponseBody
    public Map<String, Object> selectSrvyHstryList(SrvyVo srvyVo, @UserInfo UserVo user, @SiteInfo SiteInfoVo site) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        srvyVo.setUser(user);
        srvyVo.setSiteid(Integer.valueOf(site.getSiteid()));
        List<SrvyVo> result = srvyService.selectSrvyHstryList(srvyVo);
                     
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
             
        return resultMap;
    }
    
    /**
    * 설문 제출
    *
    * @Title : insertTrprSrvy
    * @Description : 대상자설문 등록
    * @param request HttpServletRequest 객체
    * @param srvySbmsnVo SrvySbmsnVo 객체
    * @param bindingResult srvySbmsnVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/srvy/insertSrvySbmsn.do")
    @ResponseBody
    public Map<String, Object> insertSrvySbmsn(HttpServletRequest request, @RequestParam("ansList") String ansList, @Valid SrvySbmsnVo srvySbmsnVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        ObjectMapper mapper = new ObjectMapper();
        List<SrvySbmsnAnsVo> srvySbmsnAnsList = mapper.readValue(ansList, new TypeReference<>(){});
        
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        srvySbmsnVo.setUser(user);
        retVal = srvyService.insertSrvySbmsn(request, srvySbmsnVo, srvySbmsnAnsList);
        
        if(retVal == 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "설문 제출이 이미 완료되어 제출할 수 없습니다");
        } else if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "설문 제출이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "설문 제출에 실패하였습니다");
        }
        return resultMap;
    }

    /**
     * 설문 완료 팝업
     *
     * @Title : srvySbmsnCmplPopup
     * @Description : 설문 완료 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
     @RequestMapping(value = "/front/srvy/srvySbmsnCmplPopup.html")
     public String srvySbmsnCmplPopup(String aplyType, Model model) throws Exception {
         int curYear = Integer.valueOf(DateTimeUtil.getYear());
         
         model.addAttribute("aplyType", aplyType);
         model.addAttribute("curYear", curYear);
         
         return "front/srvy/srvySbmsnCmplPopup";
     }
}
