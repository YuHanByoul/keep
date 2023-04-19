package com.kbrainc.plum.mng.spce.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 공간 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.spce.controller - SpceController.java
 * </pre>
 *
 * @ClassName : SpceController
 * @Description : 기관 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class SpceController {

    @Autowired
    private SpceService spceService;
    
    @Autowired
    private InstService instService;
    
    @Autowired
    private CommonService commonService;

    /**
     * 공간관리 화면 이동.
     *
     * @Title : spceList
     * @Description : 개인회원관리 리스트화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/spceList.html")
    public String spceList(Model model) throws Exception {
        model.addAttribute("sidoList", commonService.selectCtprvnList());      
        return "mng/spce/spceList";
    }

    /**
     * 공간 목록 조회.
     *
     * @Title : selectSpceList
     * @Description : 기관 목록 조회.
     * @param InstVo instVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/selectSpceList.do")
    @ResponseBody
    public Map<String, Object> selectSpceList(SpceVo spceVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<SpceVo> result = null;
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

    /**
     * 상세(탭) 화면
     *
     * @Title : instDetail
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/spceDetail.html")
    public String instDetail(SpceVo spceVo, Model model) throws Exception {
        model.addAttribute("param", spceVo);
        return "mng/spce/spceDetail";
    }

    /**
     * 등록 화면
     *
     * @Title : spceInsertForm
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/spceInsertForm.html")
    public String spceInsertForm(SpceVo spceVo,InstVo instVo ,Model model,@UserInfo UserVo user) throws Exception {
        
        model.addAttribute("user", user);
        model.addAttribute("param", spceVo);
        
        if(user.getRoleInfo().getTrgtInstCd().equals("S")) {
            instVo.setInstid(user.getInstid());
            instService.selectInstInfo(instVo);
            model.addAttribute("instVo", spceVo);
        }else {
            model.addAttribute("instVo", null);
        }
        
        return "mng/spce/spceForm";
    }
    
    /**
     * 기관 검색 모달 화면 
     *
     * @Title : instSearchPopup
     * @Description : 기관 검색 모달 화면 
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/instSearchPopup.html")
    public String instSearchPopup(InstVo instVo ,Model model) throws Exception {
        return "mng/spce/spceInstSearchPopup";
    }
    
    
    /**
     * 기관 검색 조회.
     *
     * @Title : selectMemberList
     * @Description : 기관 목록 조회.
     * @param InstVo instVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/selectInstList.do")
    @ResponseBody
    public Map<String, Object> selectInstList(InstVo instVo,SpceVo spceVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<InstVo> result = null;
        instVo.setUser(user);
        result = instService.selectInstList(instVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    
    /**
     * 시설 검색 조회.
     *
     * @Title : selectFcltList
     * @Description :설 검색 조회.
     * @param SpceVo SpceVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/selectFcltList.do")
    @ResponseBody
    public Map<String, Object> selectFcltList(SpceVo spceVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List< Map<String, Object>> result = null;
        spceVo.setUser(user);
        result = spceService.selectFcltList(spceVo);
        resultMap.put("fcltlist", result);
        return resultMap;
    }

    /**
     * 공간정보 업데이트 상세 화면
     *
     * @Title : spceUpdate
     * @Description : 등록화면 이동.
     * @param model 모델객체
    ) * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/spceUpdate.html")
    public String spceUpdate(SpceVo spceVo,InstVo instVo ,Model model,@UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("param", spceVo);
        
        if(user.getRoleInfo().getTrgtInstCd().equals("S")) {
            instVo.setInstid(user.getInstid());
            instService.selectInstInfo(instVo);
            model.addAttribute("instVo", spceVo);
        }else {
            model.addAttribute("instVo", null);
        }
        model.addAttribute("spceVo", spceService.selectSpceInfo(spceVo));
        return "mng/spce/spceUpdate";
    }

    /**
     * 공간 등록
     *
     * @Title : insertSpce
     * @Description : 공간 등록
     * @param SpceVo         SpceVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/insertSpce.do")
    @ResponseBody
    public Map<String, Object> insertSpce(@Valid SpceVo spceVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        spceVo.setUser(user);
        
        if(!StringUtil.isNull(spceVo.getChkinHr())) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
            spceVo.setChkinHour(dtFormat.parse("1970-01-01 "+spceVo.getChkinHr()));
        }else {
            spceVo.setChkinHour(null);
        }
        
        if(!StringUtil.isNull(spceVo.getChcktHr())) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
            spceVo.setChcktHour(dtFormat.parse("1970-01-01 "+spceVo.getChcktHr()));
        }else {
            spceVo.setChcktHour(null);
        }
        
        
        retVal = spceService.insertSpce(spceVo);
        
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
     * 공간 정보 수정
     *
     * @Title : insertMember
     * @Description :  공간 정보 수정
     * @param SpceVo         SpceVo객체
     * @param bindingResult1 memberVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/updateSpce.do")
    @ResponseBody
    public Map<String, Object> updateSpce(@Valid SpceVo spceVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        spceVo.setUser(user);
        
        if(!StringUtil.isNull(spceVo.getChkinHr())) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
            spceVo.setChkinHour(dtFormat.parse("1970-01-01 "+spceVo.getChkinHr()));
        }else {
            spceVo.setChkinHour(null);
        }
        
        if(!StringUtil.isNull(spceVo.getChcktHr())) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
            spceVo.setChcktHour(dtFormat.parse("1970-01-01 "+spceVo.getChcktHr()));
        }else {
            spceVo.setChcktHour(null);
        }
        
        
        retVal = spceService.updateSpce(spceVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 공간 정보 삭제 처리 
     *
     * @Title : deleteSpce
     * @Description :  공간 정보 삭제 처리
     * @param SpceVo spceVo
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/deleteSpce.do")
    @ResponseBody
    public Map<String, Object> deleteSpce(SpceVo spceVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        spceVo.setUser(user);
        
        if(spceService.isThereSpceRsvt(spceVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "예약 신청(내역)건이 존재하여 삭제할 수 없습니다.");
            return resultMap;
        }
        
        retVal = spceService.deleteSpce(spceVo);
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제 실패했습니다.");
        }
        
        return resultMap;
    }
    
    
    /*************************예약일자**********************************/
    /**
     * 공간 예약일자상세 화면
     *
     * @Title : spceRsvt
     * @Description : 공간 예약일자상세 화면
     * @param model 모델객체
    ) * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/spceRsvt.html")
    public String spceRsvt(SpceVo spceVo,InstVo instVo ,Model model,@UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("param", spceVo);
        model.addAttribute("spceVo", spceService.selectSpceInfo(spceVo));
        
        return "mng/spce/spceRsvt";
    }
    /**
     * 예약일자 리스트 호출  
     *
     * @Title       : selectSpceRsvtList 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return List<SpceRsvtdeVo> 기관정보 목록
     * @throws Exception 예외
     */
    @ResponseBody
    @RequestMapping(value = "/mng/spce/selectSpceRsvtdeList.do")
    public Map<String, Object> selectSpceRsvtdeList(SpceRsvtdeVo spceRsvtdeVo,InstVo instVo ,Model model,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List< SpceRsvtdeVo> result = null;
        spceRsvtdeVo.setUser(user);
        result = spceService.selectSpceRsvtdeList(spceRsvtdeVo);
        resultMap.put("rsvtdelist", result);
        return resultMap;
    }
    /**
     * 예약일자 팝업 상세(탭 영역 호출  )  
     *
     * @Title       : detailPopup 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return String 화면
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/detailPopup.html")
    public String detailPopup(SpceVo spceVo,Model model,@UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("mode", spceVo.getMode());
        model.addAttribute("singleChoiceDt", spceVo.getSingleChoiceDt());
        
        return "mng/spce/spceRsvtdetailPopup";
    }
    /**
     * 예약일자 팝업 상세 호출
     *
     * @Title       : packageStayInfoPopup 
     * @Description : 예약일자 팝업 상세 호출  
     * @param param SpceVo SpceVo 객체
     * @return String 화면
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/packageStayRsvtPopup.html")
    public String packageStayInfoPopup(SpceVo spceVo,SpceRsvtdeVo spceRsvtdeVo,Model model,@UserInfo UserVo user) throws Exception {
        
        String target = "";
        
        if(spceVo.getMode().equals("PS")){ //일괄 숙박
            target = "mng/spce/spcePackageStayRsvtPopup";
        }else if (spceVo.getMode().equals("PR")){ //일괄 대여
            target = "mng/spce/spcePackageRentRsvtPopup";
        }else if (spceVo.getMode().equals("S")){ //숙박
            target = "mng/spce/spceStayRsvtPopup";
        }else if (spceVo.getMode().equals("R")){ //대여 
            target = "mng/spce/spceRentRsvtPopup";
            
        }else if (spceVo.getMode().equals("MS")){ //숙박수정
            model.addAttribute("rsvtList",spceService.selectSpceRsvtdeList(spceRsvtdeVo));
            target = "mng/spce/spceStayRsvtPopup";
            
        }else if (spceVo.getMode().equals("MR")){ //대여수정
            model.addAttribute("rsvtList",spceService.selectSpceRsvtdeList(spceRsvtdeVo));
            target = "mng/spce/spceRentRsvtPopup";
        }
        
        return target;
    }
    
    /**
     * 예약일자 일괄 등록
     *
     * @Title : insertSpce
     * @Description : 공간 등록
     * @param SpceVo         SpceVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/InsertSpceTotalStayInfo.do")
    @ResponseBody
   
    public Map<String, Object> InsertSpceTotalStayInfo( @RequestBody SpceRsvtdeVo spceRsvtdeVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        List< SpceRsvtdeVo> result = null;
        spceRsvtdeVo.setUser(user);
        result = spceService.selectSpceRsvtdeList(spceRsvtdeVo);
        
        //기존 예약일자 여부 확인  
        if(result.size() > 0) {
            String deStr= "";
            List<String> deStrList= new ArrayList();
            for(SpceRsvtdeVo vo :result) {
                deStrList.add(vo.getDe());
            }
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg",String.join(", ",deStrList.stream().distinct().collect(Collectors.toList()))+ "일 이미 예약 운영일자가 존재합니다.");
            return resultMap; 
        }
        
        retVal = spceService.insertSpceRsvtde(spceRsvtdeVo);
        
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
     * 공간관리 예약상태 변경
     *
     * @Title : changeRsvtPsblty
     * @Description : 공간관리 예약상태 변경
     * @param SpceVo         SpceVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/changeRsvtPsblty.do")
    @ResponseBody
    public Map<String, Object> changeRsvtPsblty( @RequestBody SpceRsvtdeVo spceRsvtdeVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        List< SpceRsvtdeVo> result = null;
        spceRsvtdeVo.setUser(user);
        
        retVal = spceService.updateSpceRsvtde(spceRsvtdeVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "변경 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "변경에 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 공간관리 예약 운영일자 삭제 
     *
     * @Title : changeRsvtPsblty
     * @Description : 공간관리 예약상태 변경
     * @param SpceVo         SpceVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/deleteRsvt.do")
    @ResponseBody
    public Map<String, Object> deleteRsvt( @RequestBody SpceRsvtdeVo spceRsvtdeVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        List<Map<String, Object>> result = null;
        spceRsvtdeVo.setUser(user);
        result = spceService.selectFclRsvtdeList(spceRsvtdeVo);
        
        //예약 신청 여부 확인  
        if(result.size() > 0) {
            String deStr= "";
            List<String> deStrList= new ArrayList();
            for(Map<String, Object> vo :result) {
                deStrList.add(vo.get("DE").toString());
            }
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg",String.join(", ",deStrList.stream().distinct().collect(Collectors.toList()))+ "일 이미 예약 운영일자가 존재합니다.");
            return resultMap; 
        }
        
        retVal = spceService.deleteSpceRsvtdeByDate(spceRsvtdeVo);
        
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
     * 공간관리 예약 일자 수정  
     *
     * @Title : modifyRentRsvt
     * @Description : 공간관리 예약 일자 수정
     * @param SpceRsvtdeVo         spceRsvtdeVo객체
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/modifyRentRsvt.do")
    @ResponseBody
    public Map<String, Object> modifyRentRsvt( @RequestBody SpceRsvtdeVo spceRsvtdeVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        List<Map<String, Object>> result = null;
        spceRsvtdeVo.setUser(user);
        result = spceService.selectFclRsvtdeList(spceRsvtdeVo);
        
        //예약 신청 여부 확인  
        if(result.size() > 0) {
            String deStr= "";
            List<String> deStrList= new ArrayList();
            for(Map<String, Object> vo :result) {
                deStrList.add(vo.get("DE").toString());
            }
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg",String.join(", ",deStrList.stream().distinct().collect(Collectors.toList()))+"일 이미 예약 신청이 존재하여 수정 할 수 없습니다.");
            return resultMap; 
        }
        
        retVal = spceService.deleteSpceRsvtdeByDate(spceRsvtdeVo);
        
        retVal = spceService.insertSpceRsvtde(spceRsvtdeVo);
        
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
     * 공간관리 예약 숙박 수정 팝업  
     *
     * @Title       : modifyStayRsvtPopup 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return String 화면
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/modifyStayRsvtPopup.html")
    public String modifyStayRsvtPopup(SpceVo spceVo,Model model,@UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("mode", spceVo.getMode());
        model.addAttribute("singleChoiceDt", spceVo.getSingleChoiceDt());
        
        return "mng/spce/spceRsvtdetailPopup";
    }
    
    /**
     * 공간관리 예약 대여 수정 팝업  
     *
     * @Title       : modifyRentRsvtPopup 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return String 화면
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/spce/modifyRentRsvtPopup.html")
    public String modifyRentRsvtPopup(SpceVo spceVo,Model model,@UserInfo UserVo user) throws Exception {
        model.addAttribute("user", user);
        model.addAttribute("mode", spceVo.getMode());
        model.addAttribute("singleChoiceDt", spceVo.getSingleChoiceDt());
        
        return "mng/spce/spceRsvtdetailPopup";
    }
    
    /**
     * QR 코드 생성
     * @param commandMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mng/makeQRCode.do")
    public ModelAndView makeAtndncQRCode(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception{

        ModelAndView modelandview = new ModelAndView();

        String tmtblPrdAtndncId = StringUtil.nvl(commandMap.get("p_tmtbl_prd_atndnc_id"));
        if(StringUtil.isNotNull(tmtblPrdAtndncId)){
            modelandview.setViewName("qrcodeview");
        }
        
        modelandview.setViewName("QRCodeView");
        modelandview.addObject("qrText","aadadf-123");
        
        return modelandview;
    }
    
    
}