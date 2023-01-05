package com.kbrainc.plum.mng.dsgnPrgrm.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.code.service.CodeServiceImpl;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.dsgnPrgrm.service.DsgnPrgrmServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

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
     * @param DsgnPrgrmVo객체
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
    public String dsgnInfoForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	model.addAttribute("dsgnAplyInfo",dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo));
    	return "mng/dsgnPrgrm/dsgnInfoForm";
    }

    /**
     * @Title : selectDsgnPrgrmList
     * @Description : 지정프로그램 목록 조회
     * @param DsgnPrgrmVo객체
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
    public String chgAplyForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	model.addAttribute("chgAplyPrgrmInfo", asgsysSrngServiceImpl.selectDsgnAplyDtlInfo(asgsysSrngVo));
    	return "mng/dsgnPrgrm/chgAplyForm";
    }

    /**
     * @Title : selectChgAplyList
     * @Description : 변경신청 목록조회
     * @param DsgnPrgrmVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectChgAplyList.do")
    @ResponseBody
    public Map<String, Object> selectChgAplyList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<DsgnPrgrmVo> result = null;

        result = dsgnPrgrmServiceImpl.selectChgAplyList(dsgnPrgrmVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("chgAplyList", result);

        return resultMap;
    }

    /**
     * @Title : dsgnRtrcnPopup
     * @Description : 변경신청팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/chgAplyDtlPopup.html")
    public String chgAplyDtlPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	DsgnPrgrmVo chgAplyInfo = dsgnPrgrmServiceImpl.selectChgAplyDtl(dsgnPrgrmVo);

    	logger.info("@@@@@@@@@@@@@@@@@@@ filegrpid : " + chgAplyInfo.toString());
    	logger.info("@@@@@@@@@@@@@@@@@@@ filegrpid : " + chgAplyInfo.getFilegrpid());

    	if (!StringUtil.nvl(chgAplyInfo.getFilegrpid()).equals("") && !StringUtil.nvl(chgAplyInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(chgAplyInfo.getFilegrpid());

            model.addAttribute("fileList", asgsysSrngServiceImpl.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

    	model.addAttribute("chgAplyInfo", chgAplyInfo);

    	return "mng/dsgnPrgrm/chgAplyDtlPopup";
    }

    /**
     * @Title : chgAplySttsChgPopup
     * @Description : 변경신청 상태변경 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/chgAplySttsChgPopup.html")
    public String chgAplySttsChgPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	model.addAttribute("aplyid", dsgnPrgrmVo.getAplyid());
    	return "mng/dsgnPrgrm/chgAplySttsChgPopup";
    }

    /**
     * 변경신청상태 수정
     *
     * @Title       : updateChgAplyStts
     * @Description : 변경신청상태 수정
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateChgAplyStts.do")
    @ResponseBody
    public Map<String, Object> updateChgAplyStts(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);

    	retVal = dsgnPrgrmServiceImpl.updateChgAplyStts(dsgnPrgrmVo);

    	if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

    	return resultMap;
    }

    /**
     *
     * 변경신청상세 수정
     *
     * @Title        : updateChgAply
     * @Description : 변경신청상세 수정
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateChgAply.do")
    @ResponseBody
    public Map<String, Object> updateChgAply(DsgnPrgrmVo dsgnPrgrmVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);

    	retVal = dsgnPrgrmServiceImpl.updateChgAply(dsgnPrgrmVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "수정에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "수정에 실패했습니다.");
    	}

    	return resultMap;
    }


    /**
     * @Title : operRsltForm
     * @Description : 운영결과(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/operRsltForm.html")
    public String operRsltForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

    	asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());

    	model.addAttribute("prgrmBscInfo", asgsysSrngServiceImpl.selectDsgnAplyDtlInfo(asgsysSrngVo));    //프로그램 기본 정보

    	return "mng/dsgnPrgrm/operRsltForm";
    }

    /**
     * @Title : selectOperRsltList
     * @Description : 운영결과 목록 조회
     * @param DsgnPrgrmVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectOperRsltList.do")
    @ResponseBody
    public Map<String, Object> selectOperRsltList(DsgnPrgrmVo dsgnPrgrmVo ,Model model) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DsgnPrgrmVo> result = null;

        //운영결과 목록 조회
        result = dsgnPrgrmServiceImpl.selectOperRsltList(dsgnPrgrmVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("operRsltList", result);

        return resultMap;
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
     * @Title : implmntIdntySrngForm
     * @Description : 이의신청(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/objcInfoForm.html")
    public String objcInfoForm() throws Exception {
    	return "mng/dsgnPrgrm/objcInfoForm";
    }

    /**
     * @Title : dsgnRtrcnPopup
     * @Description : 지정취소팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnRtrcnPopup.html")
    public String dsgnRtrcnPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	model.addAttribute("dsgnRtrcnInfo", dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo));

    	return "mng/dsgnPrgrm/dsgnRtrcnPopup";
    }

    /**
     * @Title : dsgnPrgrmOutlPopup
     * @Description : 지정프로그램 개요 보기
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnPrgrmOutlPopup.html")
    public String dsgnPrgrmOutlPopup() throws Exception {
    	return "mng/dsgnPrgrm/dsgnPrgrmOutlPopup";
    }

    /**
     * @Title : dsgnInfoChgPopup
     * @Description : 지정정보변경팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnInfoChgPopup.html")
    public String dsgnInfoChgPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	model.addAttribute("callSe", dsgnPrgrmVo.getCallSe());
    	model.addAttribute("dsgnPrgrmInfo", dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo));
    	return "mng/dsgnPrgrm/dsgnInfoChgPopup";
    }

    /**
     * 지정내역 저장
     *
     * @Title       : insertDsgnHstry
     * @Description : 지정내역 저장
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/insertDsgnHstry.do")
    @ResponseBody
    public Map<String, Object> insertDsgnHstry(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);

    	retVal = dsgnPrgrmServiceImpl.insertDsgnHstry(dsgnPrgrmVo);

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
     * 지정내역 변경
     *
     * @Title       : updateDsgnHstry
     * @Description : 지정내역 변경
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateDsgnHstry.do")
    @ResponseBody
    public Map<String, Object> updateDsgnHstry(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);

    	retVal = dsgnPrgrmServiceImpl.updateDsgnHstry(dsgnPrgrmVo);

    	if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

    	return resultMap;
    }



}


