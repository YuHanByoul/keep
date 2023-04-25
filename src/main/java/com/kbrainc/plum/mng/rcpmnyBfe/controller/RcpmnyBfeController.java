package com.kbrainc.plum.mng.rcpmnyBfe.controller;

import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;
import com.kbrainc.plum.mng.rcpmnyBfe.service.RcpmnyBfeService;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.mng.resveReqst.service.ResveReqstService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 교육시설 입금 전 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.rcpmnyBfe.contoller
 * - BsnsCmmnController.java
 * </pre>
 *
 * @ClassName : RcpmnyBfeController
 * @Description : 교육시설 입금 전 관리 컨트롤러 클래스
 * @author : NTK
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class RcpmnyBfeController {

    @Autowired
    private RcpmnyBfeService rcpmnyBfeService;

    @Autowired
    private ResveReqstService resveReqstService;

    /**
     * 입금 전 리스트화면으로 이동
     *
     * @Title : rcpmnyBfeList
     * @Description : 예약신청 리스트화면으로 이동
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/rcpmnyBfeList.html")
    public String rcpmnyBfeList(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("param", resveReqstVo);
        return "mng/rcpmnyBfe/rcpmnyBfeList";
    }

    /**
     * 입금 전 상세화면
     *
     * @Title : rcpmnyBfeView
     * @Description : 입금 전 상세화면으로 이동
     * @param rcpmnyBfeVo 입금 전 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/rcpmnyBfeView.html")
    public String rcpmnyBfeView(ResveReqstVo resveReqstVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        model.addAttribute("param", resveReqstVo);
        resveReqstVo.setUser(user);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("aplyVo", resultVo);
        return "mng/rcpmnyBfe/rcpmnyBfeView";
    }

    /**
     * 입금 전 리스트 호출
     *
     * @Title : selectRcpmnyBfeList
     * @Description : 입금 전 리스트 기능
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/selectRcpmnyBfeList.do")
    @ResponseBody
    public Map<String, Object> selectRcpmnyBfeList(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ResveReqstVo> result = null;
        resveReqstVo.setUser(user);
        result =  rcpmnyBfeService.selectRcpmnyBfeList(resveReqstVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * 입금 확인 팝업
     * 
     * @Title : dsptCheckPopup
     * @Description : 입금 확인 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/dsptCheckPopup.html")
    public String dsptCheckPopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("param", resveReqstVo);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("popupVo", resultVo);
        return "mng/rcpmnyBfe/dsptCheckPopup";
    }

    /**
     * @Title : 예약취소 팝업
     * @Description : 예약취소 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/resveCancelPopup.html")
    public String resveCancelPopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("param", resveReqstVo);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        model.addAttribute("popupVo", resultVo);
        return "mng/rcpmnyBfe/resveCancelPopup";
    }

    /**
     * 입금 확인 처리
     *
     * @Title : updateFcltMng
     * @Description : 입금 확인 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/updateDsptCheck.do")
    @ResponseBody
    public Map<String, Object> updateDsptCheck(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resveReqstVo.setUser(user);
        int retVal = 0;
        resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);
        
        if(resultVo==null) {
            resultMap.put("msg", "해당 신청건이 존재하지 않습니다. 다시 확인 후 처리해주십시오.");
            return resultMap; 
        }
        
        if(resultVo.getAplySttsCd().equals("160103")) {
            resultMap.put("msg", "해당 신청건은 취소된 상태입니다. 다시 한번 확인 해주십시오.");
            return resultMap; 
        }
        
        if(rcpmnyBfeService.isThereResveNow(resveReqstVo).equals("Y")) {
            resultMap.put("msg", "해당 일정중 이미 예약(신청/승인) 건이 존재 하여 예약승인/입금확인 처리 할 수 없습니다.");
            return resultMap; 
        }
        
        retVal = rcpmnyBfeService.updateDsptCheck(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "입금 완료(예약승인)처리 하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "입금 확인처리에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateFcltMng
     * @Description : 예약 신청 취소 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @param bindingResult 입금 전 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/updateResveCancel.do")
    @ResponseBody
    public Map<String, Object> updateResveCancel(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resveReqstVo.setUser(user);
        int retVal = 0;
        retVal = rcpmnyBfeService.updateResveCancel(resveReqstVo);
        
        if (retVal > 0 || resveReqstVo.getAplyids().length > 0 ) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "예약신청 취소에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "예약신청 취소에 실패했습니다.");
        }

        return resultMap;
    }
}
