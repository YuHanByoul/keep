package com.kbrainc.plum.mng.mobileAsgsysSrng.controller;

import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;
import com.kbrainc.plum.mng.mobileAsgsysSrng.service.MobileAsgsysSrngService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
* 시설관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : MobileAsgsysSrngController
* @Description : 시설 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class MobileAsgsysSrngController {

    @Autowired
    private MobileAsgsysSrngService mobileAsgsysSrngService;

    /**
    * 모바일 우수프로그램 지정제사업 지원단심사 리스트화면
    *
    * @Title : mobileAsgsysSrngList
    * @Description : 모바일 우수프로그램 지정제사업 지원단심사 리스트화면
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngList.html")
    public String asgsysSrngList() throws Exception {
        return "mng/mobileAsgsysSrng/asgsysSrngList";
    }

    /**
    * 모바일 우수프로그램 지정제사업 지원단심사 리스트 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 모바일 우수프로그램 지정제사업 지원단심사 리스트 조회
    * @param mobileAsgsysSrngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/selectAsgsysSrngList.do")
    @ResponseBody
    public Map<String, Object> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<MobileAsgsysSrngVo> result = null;
        mobileAsgsysSrngVo.setUser(user);
        result =  mobileAsgsysSrngService.selectAsgsysSrngList(mobileAsgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * 지원단심사 등록 화면 이동
     *
     * @Title       : asgsysSrngUpdate
     * @Description : 지원단심사 등록 화면 이동
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngUpdate.html")
    public String asgsysSrngUpdate(MobileAsgsysSrngVo mobileAsgsysSrngVo, Model model) throws Exception {
        MobileAsgsysSrngVo mobileAsgsysSrngInfo = mobileAsgsysSrngService.selectAsgsysSrngInfo(mobileAsgsysSrngVo);
        model.addAttribute("mobileAsgsysSrngInfo", mobileAsgsysSrngInfo);
        
        return "mng/mobileAsgsysSrng/asgsysSrngUpdate";
    }
    
    /**
    * 프로그램 방문일시 저장
    *
    * @Title : updateAsgsysSrng
    * @Description : 프로그램 방문일시 저장
    * @param mobileAsgsysSrngVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/updateAsgsysSrng.do")
    @ResponseBody
    public Map<String, Object> updateAsgsysSrng(@Valid MobileAsgsysSrngVo mobileAsgsysSrngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        mobileAsgsysSrngVo.setUser(user);

        int retVal = 0;
                
        retVal = mobileAsgsysSrngService.updateAsgsysSrng(mobileAsgsysSrngVo);
        
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
     * 지원단심사 체크리스트 화면 이동
     *
     * @Title       : asgsysSrngCheckList
     * @Description : 지원단심사 체크리스트 화면 이동
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngCheckList.html")
    public String asgsysSrngCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo, Model model) throws Exception {
        model.addAttribute("checkList", mobileAsgsysSrngService.selectCheckList(mobileAsgsysSrngVo));
        MobileAsgsysSrngVo mobileAsgsysSrngInfo = mobileAsgsysSrngService.selectAsgsysSrngInfo(mobileAsgsysSrngVo);
        model.addAttribute("mobileAsgsysSrngInfo", mobileAsgsysSrngInfo);
        
        return "mng/mobileAsgsysSrng/asgsysSrngCheckList";
    }

    /**
     * 지원단심사 등록
     *
     * @Title : insertSprtgrpSrng
     * @Description : 지원단심사 등록
     * @param mobileAsgsysSrngVo
     * @param user
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/insertSprtgrpSrng.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertSprtgrpSrng(@RequestBody MobileAsgsysSrngVo mobileAsgsysSrngVo, @UserInfo UserVo user, HttpServletRequest request) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        mobileAsgsysSrngVo.setUser(user);
        mobileAsgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));
        
        int retVal = 0;
        retVal = mobileAsgsysSrngService.insertSprtgrpSrng(mobileAsgsysSrngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "심사가 완료되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }

        return resultMap;
    }
}
