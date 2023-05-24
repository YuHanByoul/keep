package com.kbrainc.plum.mng.dsgnPrgrm.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmObjcVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.dsgnPrgrm.service.DsgnPrgrmServiceImpl;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstServiceImpl;
import com.kbrainc.plum.mng.pop.model.PopUpNtcVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 지정프로그램 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.controller
 * - DsgnPrgrmController.java
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
    private CommonService commonService;

	@Autowired
	private InstServiceImpl instService;

	@Autowired
	private FileServiceImpl fileService;

    /**********************************************************************************
     * 지정프로그램
     **********************************************************************************/
	/**
	* 지정번호 중복 조회
	*
	* @Title : selectDsgnNo
	* @Description : 지정번호 중복 조회
	* @param dsgnPrgrmVo
	* @return Map<String,Object>
	* @throws Exception
	*/
	@RequestMapping(value = "/mng/dsgmPrgrm/selectDsgnNoDupChk.do")
	@ResponseBody
    public Map<String, Object> selectDsgnNo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		int retVal = dsgnPrgrmServiceImpl.selectDsgnNoDupChk(dsgnPrgrmVo);

		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
		if (retVal > 0) {
            resultMap.put("msg", "중복되는 지정번호가 존재합니다. 지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.");
        }else {
        	resultMap.put("msg", "");
        }
        return resultMap;
    }

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
    * 지정프로그램 목록 엑셀 다운
    *
    * @Title : selectDsgnPrgrmExcelDownList
    * @Description : 지정프로그램 목록 엑셀 다운
    * @param dsgnPrgrmVo
    * @param request
    * @param response
    * @throws Exception
    * @return void
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectDsgnPrgrmExcelDownList.do")
    public void selectDsgnPrgrmExcelDownList(DsgnPrgrmVo dsgnPrgrmVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	dsgnPrgrmServiceImpl.selectDsgnPrgrmExcelDownList(dsgnPrgrmVo, response, request);
    }

    /**
    * 심사일정 캘린더 팝업
    *
    * @Title : sprtgrpCalenderPopup
    * @Description : 심사일정 캘린더 팝업
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/sprtgrpCalenderPopup.html")
    public String sprtgrpCalenderPopup() throws Exception {
        return "mng/dsgnPrgrm/sprtgrpCalenderPopup";
    }

    /**
     * 지원단 캘린더 목록 조회
     *
     * @Title : selectSprtgrpClndrList
     * @Description : 지원단 캘린더 목록 조회
     * @param asgsysSrngVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/dsgnPrgrm/selectSprtgrpClndrList.do")
     @ResponseBody
     public Map<String, Object> selectSprtgrpClndrList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

     	Map<String, Object> resultMap = new HashMap<String, Object>();
     	List<DsgnPrgrmVo> result = null;

     	result = dsgnPrgrmServiceImpl.selectSprtgrpClndrList(dsgnPrgrmVo);
     	//todo 캘린더 조회
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
    public String dsgnPrgrmDetailForm(Model model) throws Exception {
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
    	InstVo instVo = new InstVo();
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
    	model.addAttribute("dsgnInfo",dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo));
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
     * @Title : mbrSrchPopup
     * @Description : 담당자 변경 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/mbrSrchPopup.html")
    public String mbrSrchPopup(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	model.addAttribute("instid", asgsysSrngVo.getInstid());
    	model.addAttribute("prgrmid", asgsysSrngVo.getPrgrmid());
    	return "mng/dsgnPrgrm/mbrSrchPopup";
    }

    /**
    * 담당자 변경
    *
    * @Title : updateMbr
    * @Description : 담당자 변경
    * @param dsgnPrgrmVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateMbr.do")
    @ResponseBody
    public Map<String, Object> updateMbr(AsgsysSrngVo asgsSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal = 0;

    	asgsSrngVo.setUser(user);

    	retVal = asgsysSrngServiceImpl.updateMbr(asgsSrngVo);

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
    * 변경신청 화면 이동
    *
    * @Title : chgAplyForm
    * @Description : 변경신청 화면 이동
    * @param asgsysSrngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
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

    	DsgnPrgrmVo chgAplyInfo = null;
    	chgAplyInfo = dsgnPrgrmServiceImpl.selectChgAplyDtl(dsgnPrgrmVo);

    	if(chgAplyInfo!=null) {
    		if (!StringUtil.nvl(chgAplyInfo.getFilegrpid()).equals("") && !StringUtil.nvl(chgAplyInfo.getFilegrpid()).equals(0)) {
    			FileVo fileVo = new FileVo();
    			fileVo.setFilegrpid(chgAplyInfo.getFilegrpid());

    			model.addAttribute("fileList", asgsysSrngServiceImpl.selectEvdncDcmntFileList(fileVo));

    		} else {
    			model.addAttribute("fileList", Collections.emptyList());
    		}

    		model.addAttribute("chgAplyInfo", chgAplyInfo);
    	}else {
    		model.addAttribute("chgAplyInfo", dsgnPrgrmVo);
    	}

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
    	model.addAttribute("popupInfo", dsgnPrgrmVo);
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
    		resultMap.put("msg", "저장에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "저장에 실패했습니다.");
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

    	model.addAttribute("sidoList", commonService.selectCtprvnList());
    	model.addAttribute("dsgnInfo",dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo));

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
    * 운영결과 등록 화면
    *
    * @Title : operRsltInsertForm
    * @Description : 운영결과 등록 화면
    * @param dsgnPrgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/operRsltInsertForm.html")
    public String operRsltInsertForm(DsgnPrgrmVo dsgnPrgrmVo ,Model model) throws Exception {

    	//등록시 초기 정보로 우수성 조회
    	AsgsysSrngVo srngVo = new AsgsysSrngVo();
    	srngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());
    	model.addAttribute("dstnctnInfo", asgsysSrngServiceImpl.selectPrgrmDstnctn(srngVo));

    	//운영결과 등록시 지정차수
    	model.addAttribute("cyclid", dsgnPrgrmVo.getCyclid());
/*
    	DsgnPrgrmVo operRsltInfo = null;

    	model.addAttribute("operRsltInfo", operRsltInfo);
    	model.addAttribute("prfmncList",   dsgnPrgrmServiceImpl.selectOperRsltPrfmncList(dsgnPrgrmVo));

 */
    	InstVo instVo = new InstVo();
    	model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
    	model.addAttribute("sidoList"  , commonService.selectCtprvnList());

    	return "mng/dsgnPrgrm/operRsltInsertForm";
    }


    /**
    * 운영결과 등록
    *
    * @Title : insertOperRslt
    * @Description : 운영결과 등록
    * @param dsgnPrgrmVo
    * @param bindingResult1
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/insertOperRslt.do")
    @ResponseBody
    public Map<String, Object> insertOperRslt(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

    	retVal = dsgnPrgrmServiceImpl.insertOperRslt(dsgnPrgrmVo);

    	if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

    	return resultMap;
    }

    /**
    * 운영결과 수정 화면
    *
    * @Title : operRsltUpdateForm
    * @Description : 운영결과 수정 화면
    * @param dsgnPrgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/operRsltUpdateForm.html")
    public String operRsltUpdateForm(DsgnPrgrmVo dsgnPrgrmVo ,Model model) throws Exception {

    	DsgnPrgrmVo operRsltInfo = null;
    	operRsltInfo = dsgnPrgrmServiceImpl.selectOperRsltDetail(dsgnPrgrmVo);

    	model.addAttribute("operRsltInfo", operRsltInfo);
    	model.addAttribute("prfmncList",   dsgnPrgrmServiceImpl.selectOperRsltPrfmncList(dsgnPrgrmVo));

    	InstVo instVo = new InstVo();
    	model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
    	model.addAttribute("sidoList"  , commonService.selectCtprvnList());

    	if (!StringUtil.nvl(operRsltInfo.getFilegrpid()).equals("") && !StringUtil.nvl(operRsltInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(operRsltInfo.getFilegrpid());

            model.addAttribute("fileList", fileService.getFileList(fileVo));

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        return "mng/dsgnPrgrm/operRsltUpdateForm";
    }

    /**
     * @Title : sbmsnPrdChgPopup
     * @Description : (운영결과)제출상태 변경 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/sbmsnSttsChgPopup.html")
    public String sbmsnSttsChgPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	model.addAttribute("popInfo", dsgnPrgrmVo);
    	//model.addAttribute("sbmsnPrdChgInfo", dsgnPrgrmServiceImpl.selectOperRsltCycl(dsgnPrgrmVo));
    	return "mng/dsgnPrgrm/sbmsnSttsChgPopup";
    }

    /**
     * @Title : sbmsnPrdChgPopup
     * @Description : (운영결과)제출상태 변경 담당자검색 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/searchPicPopup.html")
    public String searchPicPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	model.addAttribute("sidoList", commonService.selectCtprvnList());
    	model.addAttribute("picPopInfo", dsgnPrgrmVo);
    	return "mng/dsgnPrgrm/searchPicPopup";
    }

    /**
    * 담당자 목록 조회
    *
    * @Title : selectPicList
    * @Description : 담당자 목록 조회
    * @param dsgnPrgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectPicList.do")
    @ResponseBody
    public Map<String, Object> selectPicList(DsgnPrgrmVo dsgnPrgrmVo ,Model model) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DsgnPrgrmVo> result = null;

        //담당자 목록 조회
        result = dsgnPrgrmServiceImpl.selectPicList(dsgnPrgrmVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("list", result);

        return resultMap;
    }


    /**
    * 운영결과 제출상태 상태변경
    *
    * @Title : updateSbmsnStts
    * @Description : 운영결과 제출상태 상태변경
    * @param dsgnPrgrmVo
    * @param bindingResult1
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateSbmsnStts.do")
    @ResponseBody
    public Map<String, Object> updateSbmsnStts(DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);
    	retVal = dsgnPrgrmServiceImpl.updateSbmsnStts(dsgnPrgrmVo);

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
     * 운영결과 수정
     *
     * @Title       : updateOperRslt
     * @Description : 운영결과 수정
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateOperRslt.do")
    @ResponseBody
    public Map<String, Object> updateOperRslt(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

    	retVal = dsgnPrgrmServiceImpl.updateOperRslt(dsgnPrgrmVo);

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
     * 운영결과 삭제
     *
     * @Title       : deleteOperRslt
     * @Description : 운영결과 삭제
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/deleteOperRslt.do")
    @ResponseBody
    public Map<String, Object> deleteOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal = 0;

    	retVal = dsgnPrgrmServiceImpl.deleteOperRslt(dsgnPrgrmVo);

    	if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

    	return resultMap;
    }

    /**
     * @Title : sbmsnPrdChgPopup
     * @Description : (운영결과)제출기간 변경 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/sbmsnPrdChgPopup.html")
    public String sbmsnPrdChgPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	model.addAttribute("sbmsnPrdChgInfo", dsgnPrgrmServiceImpl.selectOperRsltCycl(dsgnPrgrmVo));
    	return "mng/dsgnPrgrm/sbmsnPrdChgPopup";
    }

    /**
     * (운영결과서)제출기간 수정
     *
     * @Title       : updateSbmsnPrd
     * @Description : (운영결과서)제출기간 수정
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateSbmsnPrd.do")
    @ResponseBody
    public Map<String, Object> updateSbmsnPrd(DsgnPrgrmVo dsgnPrgrmVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);

    	retVal = dsgnPrgrmServiceImpl.updateSbmsnPrd(dsgnPrgrmVo);

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
     * @Title : implmntIdntySrngForm
     * @Description : 이행확인심사(탭) 화면이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/implmntIdntySrngForm.html")
    public String implmntIdntySrngForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	model.addAttribute("prgrmBscInfo", dsgnPrgrmServiceImpl.selectImplmntIdntySrng(dsgnPrgrmVo));    //프로그램 기본 정보

    	return "mng/dsgnPrgrm/implmntIdntySrngForm";
    }

    /**
     * @Title : objcInfoForm
     * @Description : 이의신청(탭) 화면이동
     * @throws Exception :
     * @return String
     * @throws Exception 예외
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
    	if(dsgnPrgrmObjcVo.getAplyid()!=null&&dsgnPrgrmObjcVo.getAplyid()!=0) {
    		model.addAttribute("objcInfo", dsgnPrgrmServiceImpl.selectObjcInfo(dsgnPrgrmObjcVo));
    	}else {
    		model.addAttribute("objcInfo", dsgnPrgrmObjcVo);
    	}
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
    public String dsgnPrgrmOutlPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	//지정프로그램 개요 조회
    	model.addAttribute("dsgnPrgrmOutl", dsgnPrgrmServiceImpl.selectDsgnOutl(dsgnPrgrmVo));
    	model.addAttribute("chkList", dsgnPrgrmServiceImpl.selectDsgnOutlChkList(dsgnPrgrmVo));
    	return "mng/dsgnPrgrm/dsgnPrgrmOutlPopup";
    }


    /**
     * @Title : dsgnPrgrmDtlPopup
     * @Description : 지정신청 상세 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/dsgnPrgrmDtlPopup.html")
    public String dsgnPrgrmDtlPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	PopUpNtcVo paramVO = new PopUpNtcVo();
    	paramVO.setTitle("지정프로그램 상세 보기");
    	model.addAttribute("item", paramVO);
    	model.addAttribute("dsgnPrgrmInfo", dsgnPrgrmVo);

    	return "mng/dsgnPrgrm/dsgnPrgrmDtlPopup";
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
    	DsgnPrgrmVo dsgnPrgrmInfo = new DsgnPrgrmVo();

        model.addAttribute("callSe", dsgnPrgrmVo.getCallSe());

        //지정내역 조회
    	List<DsgnPrgrmVo> dsgnHstryList =  dsgnPrgrmServiceImpl.selectDsgnHstry(dsgnPrgrmVo);

    	//최초 지정승인
    	if(dsgnHstryList.size() == 0) {
    		//지정번호 조회

    		dsgnPrgrmInfo = dsgnPrgrmServiceImpl.selectDsgnNo(dsgnPrgrmVo);

    	}else {
    		//결과보고 지정승인 클릭시 Hstryid 세팅
    		if(dsgnPrgrmVo.getHstryid() ==0) {
    			dsgnPrgrmVo.setHstryid(dsgnHstryList.get(dsgnHstryList.size() -1).getHstryid());
    		}

    		dsgnPrgrmInfo = dsgnPrgrmServiceImpl.selectDsgnPrgrm(dsgnPrgrmVo);

    	}

    	model.addAttribute("dsgnPrgrmInfo", dsgnPrgrmInfo);
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
    public Map<String, Object> insertDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	int retVal = 0;

    	dsgnPrgrmVo.setUser(user);
    	retVal = 0;
    	if("132101".equals(dsgnPrgrmVo.getSttsCd()) && !"".equals(dsgnPrgrmVo.getChkVal()) ){

    		retVal = dsgnPrgrmServiceImpl.selectDsgnNoDupChk(dsgnPrgrmVo);

    		if(retVal > 0) {

    			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    			resultMap.put("msg", "중복되는 지정번호가 존재합니다. 지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.");
    			return resultMap;
    		}
		}

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
    	int retVal = 0;

    	if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", "errorMsg: " + fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        retVal = 0;
    	if("132101".equals(dsgnPrgrmVo.getSttsCd()) && !"".equals(dsgnPrgrmVo.getChkVal()) ){

    		retVal = dsgnPrgrmServiceImpl.selectDsgnNoDupChk(dsgnPrgrmVo);

    		if(retVal > 0) {
    			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    			resultMap.put("msg", "중복되는 지정번호가 존재합니다. 지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.");
    			return resultMap;
    		}
    	}

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

    /**
     * 이행확인심사 목록 조회
     *
     * @param dsgnPrgrmVo
     * @return map
     * @Title : selectimplmntIdntySrngList
     * @Description : 이행확인심사 목록 조회
     * @throws Exception
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/selectImplmntIdntySrngList.do")
    @ResponseBody
    public Map<String, Object> selectImplmntIdntySrngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<DsgnPrgrmVo> implmntIdntySrngList = dsgnPrgrmServiceImpl.selectImplmntIdntySrngList(dsgnPrgrmVo);

        if (implmntIdntySrngList.size() > 0) {
            result.put("totalCount", (implmntIdntySrngList.get(0).getTotalCount()));
        } else {
            result.put("totalCount", 0);
        }

        result.put("implmntIdntySrngList", implmntIdntySrngList);

        return result;
    }

    /**
     * @Title : implmntIdntySrngDetailForm
     * @Description : 이행확인심사 상세 화면 이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/implmntIdntySrngDetailForm.html")
    public String implmntIdntySrngDetailForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	DsgnPrgrmVo dtlInfo = new DsgnPrgrmVo();

    	if(null != dsgnPrgrmVo.getDmndid()) {
    		dtlInfo = dsgnPrgrmServiceImpl.selectImplmntIdntySrngDtl(dsgnPrgrmVo);
    	}else {
    		dtlInfo = dsgnPrgrmVo;
    	}

    	model.addAttribute("cidx", dsgnPrgrmVo.getCidx());
    	model.addAttribute("dtlInfo", dtlInfo);

    	return "mng/dsgnPrgrm/implmntIdntySrngDetail";
    }

    /**
     * @Title : splmntImprvForm
     * @Description : 보안개선요청(탭) 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/splmntImprvForm.html")
    public String splmntImprvForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

    	DsgnPrgrmVo  splmntDmndInfo = dsgnPrgrmServiceImpl.selectSplmntDmnd(dsgnPrgrmVo);
    	splmntDmndInfo.setSrngid(dsgnPrgrmVo.getSrngid());
    	model.addAttribute("splmntDmndInfo", splmntDmndInfo);

    	if (!StringUtil.nvl(splmntDmndInfo.getFilegrpid()).equals("") && !StringUtil.nvl(splmntDmndInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(splmntDmndInfo.getFilegrpid());

            model.addAttribute("fileList", fileService.getFileList(fileVo));

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

    	return "mng/dsgnPrgrm/splmntImprvForm";
    }

    /**
     * 보완요청 수정
     *
     * @Title : updateSplmntImprv
     * @Description : 보완요청 수정
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/updateSplmntImprv.do")
    @ResponseBody
    public Map<String, Object> updateSplmntImprv(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

    	retVal = dsgnPrgrmServiceImpl.updateSplmntImprv(dsgnPrgrmVo);

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
     * @Title : scrtyImprvPlanlnForm
     * @Description : 보안계획서(탭)화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/scrtyImprvPlanlnForm.html")
    public String scrtyImprvPlanlnForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	DsgnPrgrmVo scrtyImprvPlanln = new DsgnPrgrmVo();

    	if(null != dsgnPrgrmVo.getDmndid()) {
    		scrtyImprvPlanln = dsgnPrgrmServiceImpl.selectSplmntPlan(dsgnPrgrmVo);

    	}else {
    		scrtyImprvPlanln = dsgnPrgrmVo;
    	}
    	model.addAttribute("scrtyImprvPlanln", scrtyImprvPlanln);

    	if (!StringUtil.nvl(scrtyImprvPlanln.getFilegrpid()).equals("") && !StringUtil.nvl(scrtyImprvPlanln.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(scrtyImprvPlanln.getFilegrpid());

            model.addAttribute("fileList", fileService.getFileList(fileVo));

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

    	return "mng/dsgnPrgrm/scrtyImprvPlanlnForm";
    }

    /**
     * 보완개선계획 등록
     *
     * @Title : insertSplmntImprv
     * @Description : 보완요청 등록
     * @param DsgnPrgrmVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/insertScrtyImprvPlanln.do")
    @ResponseBody
    public Map<String, Object> insertScrtyImprvPlanln(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

    	retVal = dsgnPrgrmServiceImpl.insertScrtyImprvPlanln(dsgnPrgrmVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "등록에 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "등록에 실패했습니다.");
    	}

    	return resultMap;
    }

    /**
	 * 보완계획서 수정
	 *
	 * @Title : updateScrtyImprvPlanln
	 * @Description : 보완계획서 수정
	 * @param DsgnPrgrmVo 객체
	 * @param bindingResult 유효성검증결과
	 * @param user 사용자세션정보
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception 예외
	 */
	@RequestMapping(value = "/mng/dsgnPrgrm/updateScrtyImprvPlanln.do")
	@ResponseBody
	public Map<String, Object> updateScrtyImprvPlanln(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

		retVal = dsgnPrgrmServiceImpl.updateScrtyImprvPlanln(dsgnPrgrmVo);

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
     * @Title : rsltRptlnForm
     * @Description : 결과보고서(탭)화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/dsgnPrgrm/rsltRptlnForm.html")
    public String rsltRptlnForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
    	DsgnPrgrmVo rsltRptln = new DsgnPrgrmVo();

    	if(null != dsgnPrgrmVo.getDmndid()) {
    		rsltRptln = dsgnPrgrmServiceImpl.selectImplmntIdntySrngDtl(dsgnPrgrmVo);

    	}else {
    		rsltRptln = dsgnPrgrmVo;
    	}
    	model.addAttribute("rsltRptln", rsltRptln);

    	if (!StringUtil.nvl(rsltRptln.getFilegrpid()).equals("") && !StringUtil.nvl(rsltRptln.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(rsltRptln.getFilegrpid());

            model.addAttribute("fileList", fileService.getFileList(fileVo));

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

    	return "mng/dsgnPrgrm/rsltRptlnForm";
    }

	/**
	 * 결과보고서 수정
	 *
	 * @Title : updateRsltRptln
	 * @Description : 결과보고서 수정
	 * @param DsgnPrgrmVo 객체
	 * @param bindingResult 유효성검증결과
	 * @param user 사용자세션정보
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception 예외
	 */
	@RequestMapping(value = "/mng/dsgnPrgrm/updateRsltRptln.do")
	@ResponseBody
	public Map<String, Object> updateRsltRptln(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

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

		retVal = dsgnPrgrmServiceImpl.updateRsltRptln(dsgnPrgrmVo);

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