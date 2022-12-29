package com.kbrainc.plum.mng.dsgnPrgrm.controller;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.code.service.CodeServiceImpl;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmObjcVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.dsgnPrgrm.service.DsgnPrgrmServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 지정제심사관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.asgsys.controller
 * - BbsController.java
 * </pre>
 *
 * @ClassName : DsgnPrgrmController
 * @Description : 지정프로그램 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class DsgnPrgrmController {

	protected Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

	@Autowired
    private DsgnPrgrmServiceImpl dsgnPrgrmServiceImpl;

	@Autowired
    private AsgsysSrngServiceImpl asgsysSrngServiceImpl;

	@Autowired
    private CodeServiceImpl codeServiceImpl;


    /**********************************************************************************
     * 지정프로그램
     **********************************************************************************/
	/**
     * @Title : dsgnPrgrmListForm
     * @Description : 지정프로그램목록 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnPrgrmListForm.html")
    public String dsgnPrgrmListForm() throws Exception {
        return "mng/dsgnPrgrm/dsgnPrgrmList";
    }

    /**
     * @Title : selectDsgnPrgrmList
     * @Description : 지정프로그램 목록 조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectDsgnPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DsgnPrgrmVo> result = null;

        result = dsgnPrgrmServiceImpl.selectDsgnPrgrmList(dsgnPrgrmVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * @Title : dsgnPrgrmDetailForm
     * @Description : 지정프로그램 상세 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnPrgrmDetailForm.html")
    public String dsgnPrgrmDetailForm() throws Exception {
    	return "mng/dsgnPrgrm/dsgnPrgrmDetail";
    }

    /**
     * @Title : dsgnInfoForm
     * @Description : 지정정보(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnInfoForm.html")
    public String dsgnInfoForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	model.addAttribute("dsgnAplyInfo",asgsysSrngServiceImpl.selectDsgnAplyDtlInfo(asgsysSrngVo));
    	return "mng/dsgnPrgrm/dsgnInfoForm";
    }

    /**
     * @Title : selectDsgnPrgrmList
     * @Description : 지정프로그램 목록 조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectDsgnDsctnList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<DsgnPrgrmVo> result = null;

        result = dsgnPrgrmServiceImpl.selectDsgnDsctnList(dsgnPrgrmVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("dsgnDsctnList", result);

        return resultMap;
    }

    /**
     * @Title : chgAplyForm
     * @Description : 변경신청(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/chgAplyForm.html")
    public String chgAplyForm() throws Exception {
    	return "mng/dsgnPrgrm/chgAplyForm";
    }

    /**
     * @Title : operRsltForm
     * @Description : 운영결과(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/operRsltForm.html")
    public String operRsltForm() throws Exception {
    	return "mng/dsgnPrgrm/operRsltForm";
    }

    /**
     * @Title : implmntIdntySrngForm
     * @Description : 이행확인심사(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/implmntIdntySrngForm.html")
    public String implmntIdntySrngForm() throws Exception {
    	return "mng/dsgnPrgrm/implmntIdntySrngForm";
    }

    /**
     * @return String 이동화면경로
     * @throws Exception :
     * @throws Exception 예외
     * @Title : implmntIdntySrngForm
     * @Description : 이의신청(탭) 화면이동
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/objcInfoForm.html")
    public String objcInfoForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
        model.addAttribute("dsgnAplyInfo", asgsysSrngServiceImpl.selectDsgnAplyDtlInfo(asgsysSrngVo));
        return "mng/dsgnPrgrm/objcInfoForm";
    }

    /**
     * 이의신청 목록 조회
     *
     * @param dsgnPrgrmVo
     * @return map
     * @throws Exception
     * @Title : selectObjcList
     * @Description : 이의신청 목록 조회
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectObjcList.do")
    @ResponseBody
    public Map<String, Object> selectObjcList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<DsgnPrgrmObjcVo> list = dsgnPrgrmServiceImpl.selectObjcList(dsgnPrgrmVo);

        if (list.size() > 0) {
            result.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    /**
     * 이의신청 팝업 화면이동
     *
     * @param dsgnPrgrmObjcVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : objcInfoPopup
     * @Description : 이의신청 팝업 화면이동
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/objcInfoPopup.html")
    public String objcInfoPopup(DsgnPrgrmObjcVo dsgnPrgrmObjcVo, Model model) throws Exception {
        model.addAttribute("objcInfo", dsgnPrgrmServiceImpl.selectObjcInfo(dsgnPrgrmObjcVo));
        return "mng/dsgnPrgrm/objcInfoPopup.html";
    }

    /**
     * 이의신청 답변 등록
     *
     * @param dsgnPrgrmObjcVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertObjcAns
     * @Description : 이의신청 답변 등록
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/insertObjcAns.do")
    @ResponseBody
    public Map<String, Object> insertObjcAns(@Valid DsgnPrgrmObjcVo dsgnPrgrmObjcVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        int retVal = 0;
        dsgnPrgrmObjcVo.setUser(user);
        retVal = dsgnPrgrmServiceImpl.insertObjcAns(dsgnPrgrmObjcVo);

        if (retVal > 0) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "저장에 성공하였습니다.");
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "저장에 실패했습니다.");
        }

        return result;
    }
}


