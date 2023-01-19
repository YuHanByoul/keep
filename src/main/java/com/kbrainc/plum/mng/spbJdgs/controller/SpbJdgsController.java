package com.kbrainc.plum.mng.spbJdgs.controller;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.mng.spbJdgs.service.SpbJdgsService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 체험환경교육 지원사업 > 심사위원 그룹 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spbJdgs.controller
 * - SpbJdgsController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpbJdgsController
 * @Description : 체험환경교육 지원사업 > 심사위원 그룹 관리 컨트롤러 클래스
 * @date : 2023. 01. 18.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/spbJdgs")
@RequiredArgsConstructor
public class SpbJdgsController {

    private final SpbJdgsService spbJdgsService;

    /**
     * 심사위원 그룹 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : spbJdgsList
     * @Description : 심사위원 그룹 목록 화면
     */
    @GetMapping("/spbJdgsList.html")
    public String spbJdgsList() throws Exception {
        return "/mng/spbJdgs/spbJdgsList.html";
    }

    /**
     * 심사위원 그룹 조회
     *
     * @return map
     * @throws Exception
     * @Title : selectSpbJdgsList
     * @Description : 심사위원 그룹 조회
     */
    @GetMapping("/selectSpbJdgsList.do")
    @ResponseBody
    public Map<String, Object> selectSpbJdgsList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<JdgGrpVo> list = spbJdgsService.selectSpbJdgsList(jdgGrpVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);
        return result;
    }

    /**
     * 심사위원 그룹 상세 화면
     *
     * @param grpId
     * @return string
     * @throws Exception
     * @Title : spbJdgsForm
     * @Description : 심사위원 그룹 상세 화면
     */
    @GetMapping("/spbJdgsForm.html")
    public String spbJdgsForm(@RequestParam(required = false) Integer grpId, Model model) throws Exception {
        JdgGrpVo jdgGrpInfo = spbJdgsService.selectSpbJdgsInfo(grpId);
        if (jdgGrpInfo == null) jdgGrpInfo = new JdgGrpVo();
        model.addAttribute("jdgGrpInfo", jdgGrpInfo);
        return "/mng/spbJdgs/spbJdgsForm.html";
    }

    /**
     * 전문가 검색 팝업
     *
     * @return string
     * @throws Exception
     * @Title : exprtSrchPopup
     * @Description : 전문가 검색 팝업
     */
    @GetMapping("/exprtSrchPopup.html")
    public String exprtSrchPopup() throws Exception {
        return "/mng/spbJdgs/exprtSrchPopup.html";
    }

    /**
     * 그룹에 매핑된 전문가 목록 조회
     *
     * @param grpId
     * @return map
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 그룹에 매핑된 전문가 목록 조회
     */
    @GetMapping("/selectExprtList.do")
    @ResponseBody
    public Map<String, Object> selectExprtList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<JdgGrpExpertVo> list = spbJdgsService.selectExprtList(jdgGrpVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);
        return result;
    }


    /**
     * 심사위원 그룹 등록
     *
     * @param jdgGrpVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertJdgGrpInfo
     * @Description : 심사위원 그룹 등록
     */
    @PostMapping("/insertSpbJdgs.do")
    @ResponseBody
    public Map<String, Object> insertSpbJdgs(@Valid JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);

        boolean retVal = spbJdgsService.insertSpbJdgs(jdgGrpVo);

        if (retVal) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }


    /**
     * 심사위원 그룹 수정
     *
     * @param jdgGrpVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : updateJdgGrpInfo
     * @Description : 심사위원 그룹 수정
     */
    @PostMapping("/updateSpbJdgs.do")
    @ResponseBody
    public Map<String, Object> updateSpbJdgs(@Valid JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);

        boolean retVal = spbJdgsService.updateSpbJdgs(jdgGrpVo);

        if (retVal) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }
        return map;
    }

    @PostMapping("/insertExprt.do")
    @ResponseBody
    public Map<String, Object> insertExprt(JdgGrpExpertVo jdgGrpExpertVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        jdgGrpExpertVo.setUser(user);

        boolean success = spbJdgsService.insertExprt(jdgGrpExpertVo);

        map.put("success", success);

        if (success) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패하였습니다.");
        }

        return map;
    }

    /**
     * 전문가 삭제
     *
     * @param jdgGrpExpertVo
     * @return map
     * @throws Exception
     * @Title : deleteJdgGrpExpert
     * @Description : 전문가 삭제
     */
    @PostMapping("/deleteExprt.do")
    @ResponseBody
    public Map<String, Object> deleteExprt(@RequestBody JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        boolean success = spbJdgsService.deleteExprt(jdgGrpExpertVo);

        map.put("success", success);

        if (success) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "삭제에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "삭제에 실패하였습니다.");
        }
        return map;
    }
}
