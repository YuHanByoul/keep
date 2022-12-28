package com.kbrainc.plum.mng.ass.jdgGrpMng.controller;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.service.JdgGrpMngService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 심사위원 그룹 관리 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.controller
 * - JdgGrpMngController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpMngController
 * @Description : 심사위원 그룹 관리 컨트롤러
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class JdgGrpMngController {

    @Autowired
    private JdgGrpMngService jdgGrpMngService;


    /**
     * 심사위원 그룹 리스트 화면 이동
     *
     * @return string
     * @throws Exception
     * @Title : jdgGrpMngList
     * @Description : 심사위원 그룹 리스트 화면 이동
     */
    @RequestMapping("/mng/ass/jdgGrpMngList.html")
    public String jdgGrpMngList() throws Exception {
        return "/mng/ass/jdgGrpMngList";
    }

    /**
     * 심사위원 그룹 상세화면 이동
     *
     * @param jdgGrpVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : jdgGrpMngDetail
     * @Description : 심사위원 그룹 상세화면 이동
     */
    @RequestMapping("/mng/ass/jdgGrpDetail.html")
    public String jdgGrpMngDetail(JdgGrpVo jdgGrpVo, Model model) throws Exception {
        JdgGrpVo jdgGrpInfo = jdgGrpMngService.selectJdgGrpInfo(jdgGrpVo);
        if (jdgGrpInfo == null) jdgGrpInfo = new JdgGrpVo();
        model.addAttribute("jdgGrpInfo", jdgGrpInfo);
        return "mng/ass/jdgGrpDetail";
    }


    /**
     * 심사위원 그룹 목록 조회
     *
     * @param jdgGrpVo
     * @return map
     * @throws Exception
     * @Title : selectJdgGrpList
     * @Description : 심사위원 그룹 목록 조회
     */
    @RequestMapping("/mng/ass/selectJdgGrpList.do")
    @ResponseBody
    public Map<String, Object> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        List<JdgGrpVo> list = jdgGrpMngService.selectJdgGrpList(jdgGrpVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }


    /**
     * 심사위원 그룹에 매핑된 전문가 조회
     *
     * @param jdgGrpVo
     * @return map
     * @throws Exception
     * @Title : selectExpertList
     * @Description : 심사위원 그룹에 매핑된 전문가 조회
     */
    @RequestMapping("/mng/ass/selectExpertList.do")
    @ResponseBody
    public Map<String, Object> selectExpertList(JdgGrpVo jdgGrpVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        List<JdgGrpExpertVo> list = jdgGrpMngService.selectJdgGrpExpertList(jdgGrpVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }


    /**
     * 전문가 모달 화면 이동
     *
     * @return string
     * @throws Exception
     * @Title : jdgGrpMngExpertModal
     * @Description : 전문가 모달 화면 이동
     */
    @RequestMapping("/mng/ass/jdgGrpMngExpertModal.html")
    public String jdgGrpMngExpertModal() throws Exception {
        return "mng/ass/jdgGrpMngExpertModal";
    }

    /**
     * 전문가 모달 > 전문가 목록 조회
     *
     * @param jdgGrpExpertVo
     * @return map
     * @throws Exception
     * @Title : selectJdgGrpMngExpertSearchList
     * @Description : 전문가 모달 > 전문가 목록 조회
     */
    @RequestMapping("/mng/ass/selectJdgGrpMngExpertSearchList.do")
    @ResponseBody
    public Map<String, Object> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<JdgGrpExpertVo> list = jdgGrpMngService.selectJdgGrpMngExpertSearchList(jdgGrpExpertVo);
        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
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
    @RequestMapping("/mng/ass/insertJdgGrpInfo.do")
    @ResponseBody
    public Map<String, Object> insertJdgGrpInfo(@Valid JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);

        int retVal = 0;

        retVal = jdgGrpMngService.insertJdgGrpInfo(jdgGrpVo);

        if (retVal > 0) {
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
    @RequestMapping("/mng/ass/updateJdgGrpInfo.do")
    @ResponseBody
    public Map<String, Object> updateJdgGrpInfo(@Valid JdgGrpVo jdgGrpVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        jdgGrpVo.setUser(user);

        int retVal = 0;

        retVal = jdgGrpMngService.updateJdgGrpInfo(jdgGrpVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }


    /**
     * 전문가 추가
     *
     * @param jdgGrpExpertVo
     * @return map
     * @throws Exception
     * @Title : insertJdgGrpExpert
     * @Description : 전문가 추가
     */
    @RequestMapping("/mng/ass/insertJdgGrpExpert.do")
    @ResponseBody
    public Map<String, Object> insertJdgGrpExpert(JdgGrpExpertVo jdgGrpExpertVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        jdgGrpExpertVo.setUser(user);

        boolean success = jdgGrpMngService.insertJdgGrpExpert(jdgGrpExpertVo);
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
    @RequestMapping("/mng/ass/deleteJdgGrpExpert.do")
    @ResponseBody
    public Map<String, Object> deleteJdgGrpExpert(@RequestBody JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        boolean success = jdgGrpMngService.deleteJdgGrpExpert(jdgGrpExpertVo);
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
