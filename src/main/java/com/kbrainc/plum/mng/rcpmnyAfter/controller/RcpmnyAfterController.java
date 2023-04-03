package com.kbrainc.plum.mng.rcpmnyAfter.controller;

import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.rcpmnyAfter.model.RcpmnyAfterVo;
import com.kbrainc.plum.mng.rcpmnyAfter.service.RcpmnyAfterService;
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
 * 입금 후관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.rcpmnyAfter.contoller
 * - BsnsCmmnController.java
 * </pre>
 *
 * @ClassName : RcpmnyAfterController
 * @Description : 입금 후 컨트롤러 클래스
 * @author : NTK
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class RcpmnyAfterController {

    @Autowired
    private RcpmnyAfterService rcpmnyAfterService;

    @Autowired
    private ResveReqstService resveReqstService;

    /**
     * 입금 후 리스트화면으로 이동
     *
     * @Title : rcpmnyAfterList
     * @Description : 예약신청 리스트화면으로 이동
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/rcpmnyAfterList.html")
    public String rcpmnyAfterList() throws Exception {
        return "mng/rcpmnyAfter/rcpmnyAfterList";
    }

    /**
     * 입금 후 상세화면으로 이동
     *
     * @Title : rcpmnyAfterView
     * @Description : 입금 후 상세화면으로 이동
     * @param rcpmnyAfterVo 입금 후 객체
     * @param model model 객체
     * @throws Exception 예외
     * @return String
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/rcpmnyAfterView.html")
    public String rcpmnyAfterView(RcpmnyAfterVo rcpmnyAfterVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {

        model.addAttribute("param", rcpmnyAfterVo);

        rcpmnyAfterVo.setUser(user);
        RcpmnyAfterVo resultVo = rcpmnyAfterService.selectRcpmnyAfterInfo(rcpmnyAfterVo);

        model.addAttribute("rcpmnyAfter", resultVo);

        return "mng/rcpmnyAfter/rcpmnyAfterView";
    }

    /**
     * 입금 후 리스트 기능
     *
     * @Title : selectRcpmnyAfterList
     * @Description : 입금 후 리스트 기능
     * @param rcpmnyAfterVo 입금 후 객체
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/selectRcpmnyAfterList.do")
    @ResponseBody
    public Map<String, Object> selectRcpmnyAfterList(RcpmnyAfterVo rcpmnyAfterVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<RcpmnyAfterVo> result = null;

        result =  rcpmnyAfterService.selectRcpmnyAfterList(rcpmnyAfterVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * @Title : refndPopup
     * @Description : 환불 요청 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/refndPopup.html")
    public String refndPopup(RcpmnyAfterVo rcpmnyAfterVo, Model model) throws Exception {
        model.addAttribute("param", rcpmnyAfterVo);

        RcpmnyAfterVo resultVo = rcpmnyAfterService.selectDsptCheckInfo(rcpmnyAfterVo);

        model.addAttribute("rcpmnyAfter", resultVo);
        return "mng/rcpmnyAfter/refndPopup";
    }

    /**
     * 입금 확인 취소 처리
     *
     * @Title : updateFcltMng
     * @Description : 입금 확인 취소 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @param bindingResult 입금 후 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/updateDsptCheckCancel.do")
    @ResponseBody
    public Map<String, Object> updateDsptCheckCancel(@Valid RcpmnyAfterVo rcpmnyAfterVo, BindingResult bindingResult, @UserInfo UserVo user, ResveReqstVo resveReqstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        rcpmnyAfterVo.setUser(user);

        int retVal = 0;

        retVal = rcpmnyAfterService.updateDsptCheckCancel(rcpmnyAfterVo);

        // 상태변경 데이터 세팅
        resveReqstVo.setAplyid(rcpmnyAfterVo.getAplyid());
        resveReqstVo.setUser(user);
        // 상태변경이력 추가
        resveReqstService.insertHstry(resveReqstVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "입금 확인취소처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "입금 확인취소처리에 실패했습니다.");
        }

        return resultMap;
    }

    /**
     * 환불요청 처리
     *
     * @Title : updateFcltMng
     * @Description : 환불요청 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @param bindingResult 입금 후 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/rcpmnyAfter/updateRefnd.do")
    @ResponseBody
    public Map<String, Object> updateRefnd(@Valid RcpmnyAfterVo rcpmnyAfterVo, BindingResult bindingResult, @UserInfo UserVo user, ResveReqstVo resveReqstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        rcpmnyAfterVo.setUser(user);

        int retVal = 0;

        retVal = rcpmnyAfterService.updateRefnd(rcpmnyAfterVo);

        // 상태변경 데이터 세팅
        resveReqstVo.setAplyid(rcpmnyAfterVo.getAplyid());
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
