package com.kbrainc.plum.mng.srng.controller;

import com.kbrainc.plum.mng.srng.model.SrngQitemVO;
import com.kbrainc.plum.mng.srng.service.SrngSerivceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import lombok.extern.slf4j.Slf4j;
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
 * 
 * 심사양식관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.controller
 * - SrngController.java
 * </pre>
 *
 * @ClassName : SrngController
 * @Description : 심사양식관리 Controller
 * @author : KBRAINC
 * @date : 2023. 01. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class SrngController {

    @Autowired
    private SrngSerivceImpl srngSerivce;

    /**
     * 심사 문항 목록 화면
     * Title : srngListForm
     * Description : 심사 문항 목록 화면
     *
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/srngListForm.html")
    public String srngListForm() throws Exception {
        return "mng/srng/srngList";
    }

    /**
     * 심사 문항 등록 화면
     * Title : srngInsertForm
     * Description : 심사 문항 등록 화면
     *
     * @param srngQitemVO
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/srngInsertForm.html")
    public String srngInsertForm(SrngQitemVO srngQitemVO) throws Exception {
        return "mng/srng/srngInsert";
    }

    /**
     * 심사 문항 수정 화면
     * Title : srngUpdateForm
     * Description : 심사 문항 수정 화면
     *
     * @param srngQitemVO
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/srngUpdateForm.html")
    public String srngUpdateForm(SrngQitemVO srngQitemVO, Model model) throws Exception {
        model.addAttribute("srngInfo", srngSerivce.selectSrng(srngQitemVO));
        return "mng/srng/srngUpdate";
    }

    /**
     * 심사 문항 목록 조회
     * Title : selectSrngList
     * Description : 심사 문항 목록 조회
     *
     * @param srngQitemVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/selectSrngList.do")
    @ResponseBody
    public Map<String, Object> selectSrngList(SrngQitemVO srngQitemVO) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        List<SrngQitemVO> result = srngSerivce.selectSrngList(srngQitemVO);

        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * 심사 문항 등록
     * Title : insertSrng
     * Description : 심사 문항 등록
     *
     * @param srngQitemVO
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/insertSrng.do")
    @ResponseBody
    public Map<String, Object> insertSrng(@Valid SrngQitemVO srngQitemVO, BindingResult bindingResult,  @UserInfo UserVo user) throws Exception{
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        srngQitemVO.setUser(user);
        retVal = srngSerivce.insertSrng(srngQitemVO);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }

        return resultMap;
    }

    /**
     * 심사 문항 수정
     * Title : insertSrng
     * Description : 심사 문항 수정
     *
     * @param srngQitemVO
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/srng/updateSrng.do")
    @ResponseBody
    public Map<String, Object> updateSrng(@Valid SrngQitemVO srngQitemVO, BindingResult bindingResult, @UserInfo UserVo user) throws Exception{
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        srngQitemVO.setUser(user);
        retVal = srngSerivce.updateSrng(srngQitemVO);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }

        return resultMap;
    }
}
