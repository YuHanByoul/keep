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
 * 입금 전관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.rcpmnyBfe.contoller
 * - BsnsCmmnController.java
 * </pre>
 *
 * @ClassName : RcpmnyBfeController
 * @Description : 입금 전 컨트롤러 클래스
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
    public String rcpmnyBfeList(RcpmnyBfeVo rcpmnyBfeVo, Model model) throws Exception {
        model.addAttribute("param", rcpmnyBfeVo);
        return "mng/rcpmnyBfe/rcpmnyBfeList";
    }

    /**
     * 입금 전 상세화면으로 이동
     *
     * @Title : rcpmnyBfeView
     * @Description : 입금 전 상세화면으로 이동
     * @param rcpmnyBfeVo 입금 전 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/rcpmnyBfeView.html")
    public String rcpmnyBfeView(RcpmnyBfeVo rcpmnyBfeVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {

        model.addAttribute("param", rcpmnyBfeVo);

        rcpmnyBfeVo.setUser(user);
        RcpmnyBfeVo resultVo = rcpmnyBfeService.selectRcpmnyBfeInfo(rcpmnyBfeVo);

        model.addAttribute("rcpmnyBfe", resultVo);

        return "mng/rcpmnyBfe/rcpmnyBfeView";
    }

    /**
     * 입금 전 리스트 기능
     *
     * @Title : selectRcpmnyBfeList
     * @Description : 입금 전 리스트 기능
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/selectRcpmnyBfeList.do")
    @ResponseBody
    public Map<String, Object> selectRcpmnyBfeList(RcpmnyBfeVo rcpmnyBfeVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<RcpmnyBfeVo> result = null;
        rcpmnyBfeVo.setUser(user);
        result =  rcpmnyBfeService.selectRcpmnyBfeList(rcpmnyBfeVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * @Title : dsptCheckPopup
     * @Description : 입금 확인 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/dsptCheckPopup.html")
    public String dsptCheckPopup(RcpmnyBfeVo rcpmnyBfeVo, Model model) throws Exception {
        model.addAttribute("param", rcpmnyBfeVo);

        RcpmnyBfeVo resultVo = rcpmnyBfeService.selectDsptCheckInfo(rcpmnyBfeVo);

        model.addAttribute("rcpmnyBfe", resultVo);
        return "mng/rcpmnyBfe/dsptCheckPopup";
    }

    /**
     * @Title : dsptCheckPopup
     * @Description : 입금 확인 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rcpmnyBfe/resveCancelPopup.html")
    public String resveCancelPopup(RcpmnyBfeVo rcpmnyBfeVo, Model model) throws Exception {
        model.addAttribute("param", rcpmnyBfeVo);

        RcpmnyBfeVo resultVo = rcpmnyBfeService.selectDsptCheckInfo(rcpmnyBfeVo);

        model.addAttribute("rcpmnyBfe", resultVo);
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
    public Map<String, Object> updateDsptCheck(@Valid RcpmnyBfeVo rcpmnyBfeVo, BindingResult bindingResult, @UserInfo UserVo user, ResveReqstVo resveReqstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        rcpmnyBfeVo.setUser(user);

        int retVal = 0;

        retVal = rcpmnyBfeService.updateDsptCheck(rcpmnyBfeVo);

        // 상태변경 데이터 세팅
        resveReqstVo.setAplyid(rcpmnyBfeVo.getAplyid());
        resveReqstVo.setUser(user);
        // 상태변경이력 추가
        resveReqstService.insertHstry(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "입금 확인처리에 성공하였습니다.");
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
    public Map<String, Object> updateResveCancel(@Valid RcpmnyBfeVo rcpmnyBfeVo, BindingResult bindingResult, @UserInfo UserVo user, ResveReqstVo resveReqstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        rcpmnyBfeVo.setUser(user);

        int retVal = 0;

        retVal = rcpmnyBfeService.updateResveCancel(rcpmnyBfeVo);

        // 상태변경 데이터 세팅
        resveReqstVo.setAplyid(rcpmnyBfeVo.getAplyid());
        resveReqstVo.setUser(user);
        // 상태변경이력 추가
        resveReqstService.insertHstry(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "예약신청 취소에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "예약신청 취소에 실패했습니다.");
        }

        return resultMap;
    }
}
