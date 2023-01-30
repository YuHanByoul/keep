package com.kbrainc.plum.mng.evnt.controller;

import com.kbrainc.plum.mng.evnt.model.EvntVo;
import com.kbrainc.plum.mng.evnt.service.EvntService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 참여신청관리 > 이벤트관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.evnt.controller
 * - EvntController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntController
 * @Description : 참여신청관리 > 이벤트관리 컨트롤러 클래스
 * @date : 2023. 01. 25.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/evnt")
@RequiredArgsConstructor
public class EvntController {

    private final EvntService evntService;

    /**
     * 이벤트 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : evntList
     * @Description : 이벤트 목록 화면
     */
    @GetMapping("/evntList.html")
    public String evntList() throws Exception {
        return "mng/evnt/evntList";
    }

    /**
     * 이벤트 목록 조회
     *
     * @param evntVo
     * @return map
     * @throws Exception
     * @Title : selectEvntList
     * @Description : 이벤트 목록 조회
     */
    @GetMapping("/selectEvntList.do")
    @ResponseBody
    public Map<String, Object> selectEvntList(EvntVo evntVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<EvntVo> list = evntService.selectEvntList(evntVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 이벤트 상세화면 이동
     *
     * @param evntVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : evtForm
     * @Description : 이벤트 상세화면 이동
     */
    @GetMapping("/evntForm.html")
    public String evtForm(EvntVo evntVo, Model model) throws Exception {
        EvntVo evntInfo = evntService.selectEvntInfo(evntVo);
        if (evntInfo == null) evntInfo = new EvntVo();
        model.addAttribute("evntInfo", evntInfo);
        return "mng/evnt/evntForm";
    }

    /**
     * 이벤트 등록
     *
     * @param evntVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertEvnt
     * @Description : 이벤트 등록
     */
    @PostMapping("insertEvnt.do")
    @ResponseBody
    public Map<String, Object> insertEvnt(@Valid EvntVo evntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        evntVo.setUser(user);

        int retVal = 0;

        retVal = evntService.insertEvnt(evntVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "등록에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "등록에 실패했습니다.");
        }

        return result;
    }

    /**
     * 이벤트 수정
     *
     * @param evntVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : updateEvnt
     * @Description : 이벤트 수정
     */
    @PostMapping("updateEvnt.do")
    @ResponseBody
    public Map<String, Object> updateEvnt(@Valid EvntVo evntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        evntVo.setUser(user);

        int retVal = 0;

        retVal = evntService.updateEvnt(evntVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "수정에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "수정에 실패했습니다.");
        }

        return result;
    }


}
