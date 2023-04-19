package com.kbrainc.plum.mng.asgsysSrng.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.DsgnSrngFormVo;
import com.kbrainc.plum.mng.asgsysSrng.model.EmrgcyActnPlanVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
import com.kbrainc.plum.mng.asgsysSrng.model.TchaidFcltVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeServiceImpl;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstServiceImpl;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 지정제심사관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.asgsys.controller
 * - AsgsysSrngController.java
 * </pre>
 *
 * @ClassName : AsgsysSrngController
 * @Description : 지정제심사관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2022. 12. 06.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class AsgsysSrngController {

	protected Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

	@Autowired
    private AsgsysSrngServiceImpl asgsysSrngService;

	@Autowired
    private CodeServiceImpl codeService;

	@Autowired
	private InstServiceImpl instService;

	/**********************************************************************************
     * 지정신청
     **********************************************************************************/

	/**
     * @Title : dsgnSrngMainForm
     * @Description : 지정신청메인 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/dsgnAplyMainForm.html")
    public String dsgnAplyMainForm() throws Exception {
        return "mng/asgsysSrng/dsgnAplyMain";
    }

    /**
     * @Title : dsgnSrngForm
     * @Description : 지정신청 목록조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/selectDsgnAplyList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<AsgsysSrngVo> result = null;

        result = asgsysSrngService.selectDsgnAplyList(asgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * @Title : dsgnSrngDetailForm
     * @Description : 지정신청상세 화면이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/dsgnAplyDetailForm.html")
    public String dsgnAplyDetailForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	CodeVo codeVo = new CodeVo();

    	codeVo.setCdgrpid("111");    //신청진행상태코드
    	codeVo.setRowPerPage(30);


    	model.addAttribute("sttsCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));
    	model.addAttribute("sttsCdList", codeService.selectCodeList(codeVo));

    	return "mng/asgsysSrng/dsgnAplyDetail";
    }

    /**
    * 지정신청 진행상태코드 수정
    *
    * @Title : updateSttsCd
    * @Description : 지정신청 진행상태코드 수정
    * @param asgsysSrngVo
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/asgsysSrng/updateSttsCd.do")
    @ResponseBody
    public Map<String, Object> updateSttsCd(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        int retVal = 0 ;

        retVal = asgsysSrngService.updatePrgrSttsCd(asgsysSrngVo);
        if (retVal == 1) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "지정지원 진행상태를 변경하였습니다.");
        } else if(retVal < 1){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "해당 하는 지정신청 프로그램ID가 없습니다.");
        }else {
        	resultMap.put("result", Constant.REST_API_RESULT_FAIL);
        	resultMap.put("msg", "지정지원 진행상태 변경을 실패 했습니다.");
        }

    	return resultMap;
    }

    /**
     * @Title : aplyInfoForm
     * @Description : 신청정보 화면이동
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/aplyInfoForm.html")
    public String aplyInfoForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	AsgsysSrngVo dsgnAplyInfo = asgsysSrngService.selectDsgnAplyDtlInfo(asgsysSrngVo);

        if (dsgnAplyInfo == null) {
            dsgnAplyInfo = new AsgsysSrngVo();
        }

        model.addAttribute("dsgnAplyInfo", dsgnAplyInfo);

        if (!StringUtil.nvl(dsgnAplyInfo.getFilegrpid()).equals("") && !StringUtil.nvl(dsgnAplyInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(dsgnAplyInfo.getFilegrpid());

            model.addAttribute("fileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));    //증빙서류파일목록

        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        InstVo instVo = new InstVo();
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));

        return "mng/asgsysSrng/aplyInfo";
    }


    /**
     * @Title : mbrSrchPopup
     * @Description : 회원검색 팝업
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/mbrSrchPopup.html")
    public String mbrSrchPopup(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	model.addAttribute("instid", asgsysSrngVo.getInstid());
    	model.addAttribute("prgrmid", asgsysSrngVo.getPrgrmid());

    	return "mng/asgsysSrng/mbrSrchPopup";
    }

    /**
     * @Title : selectMbrList
     * @Description : 회원목록 조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/selectMbrList.do")
    @ResponseBody
    public Map<String, Object> selectMbrList(AsgsysSrngVo asgsysSrngVo) throws Exception {
  	   Map<String, Object> resultMap = new HashMap<>();
        List<MemberVo> result = null;

        result = asgsysSrngService.selectMbrList(asgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("mbrList", result);

        return resultMap;
    }

    /**
     * 담당자 변경
     *
     * @Title       : updateMbr
     * @Description : 담당자 변경
     * @param asgsysSrngVo AsgsysSrngVo객체
     * @param AsgsysSrngVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/updateMbr.do")
    @ResponseBody
    public Map<String, Object> updateMbr(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.updateMbr(asgsysSrngVo);

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
     * @Title : picAltmntPopup
     * @Description : 담당자 배정 팝업
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/picAltmntPopup.html")
    public String picAltmntPopup(@RequestParam(value ="mode",required = false) String mode, AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	model.addAttribute("mode",mode);

    	return "mng/asgsysSrng/picAltmntPopup";
    }

    /**
     * @Title : selectPicList
     * @Description : 담당자배정 목록조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/selectPicList.do")
    @ResponseBody
    public Map<String, Object> selectPicList(@RequestParam(value ="picSe" ,required = false) String picSe, AsgsysSrngVo asgsysSrngVo) throws Exception {
  	   Map<String, Object> resultMap = new HashMap<>();
        List<AsgsysSrngVo> result = null;

        String se = picSe;

        if("Y".equals(se)) {
        	// 지원단 목록조회
        	result = asgsysSrngService.selectSprtgrpPicList(asgsysSrngVo);

        }else {
        	// 심사위원심사 목록조회
        	result = asgsysSrngService.selectjdgsPicList(asgsysSrngVo);
        }


        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("picList", result);

        return resultMap;
    }

    /**
     * 담당자 배정 등록
     *
     * @Title : insertPicInfo
     * @Description : 담당자 배정 등록
     * @param asgsysSrngVo
     * @param user
     * @return Map<String,Object> 응답결과객체
     * @throws Exception
     */
    @RequestMapping(value = "/mng/asgsysSrng/insertPicInfo.do")
    @ResponseBody
    public Map<String, Object> insertPicInfo(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();

    	int retVal = 0 ;

    	asgsysSrngVo.setUser(user);
    	retVal = asgsysSrngService.insertPicInfo(asgsysSrngVo);

    	if (retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "등록 성공하였습니다.");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "등록 실패했습니다.");
    	}
    	return resultMap;
    }

    /**
	 * 담당자 배정 삭제
	 *
	 * @Title : deletePicInfo
	 * @Description : 담당자 배정 삭제
	 * @param asgsysSrngVo
	 * @param user
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception
	 */
    @RequestMapping(value = "/mng/asgsysSrng/deletePicInfo.do")
    @ResponseBody
    public Map<String, Object> deletePicInfo(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();

		int retVal = 0 ;

		retVal = asgsysSrngService.deletePicInfo(asgsysSrngVo);

		if (retVal > 0) {
			resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
			resultMap.put("msg", "삭제 성공하였습니다.");
		} else {
			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
			resultMap.put("msg", "삭제 실패했습니다.");
		}
		return resultMap;
    }


    /**
    * @Title : jdgsSprtgrpAltmntForm
    * @Description : 심사위원/지원단 배정 화면이동
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/jdgsSprtgrpAltmntForm.html")
    public String jdgsSprtgrpAltmntForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	return "mng/asgsysSrng/jdgsSprtgrpAltmnt";
    }

    /**
     * @Title : jdgsSrng
     * @Description : 심사위원심사 화면이동
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/jdgsSrngForm.html")
    public String jdgsSrngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	//신청정보조회
    	AsgsysSrngVo aplyInfo = asgsysSrngService.selectAplyInfo(asgsysSrngVo);

    	//심사양식 조회
    	aplyInfo.getOperFrmCd();

    	asgsysSrngVo.setFormid(aplyInfo.getFormid());

    	//운영형태코드 목록 조회
    	CodeVo codeVo = new CodeVo();
    	codeVo.setCdgrpid("120");    //신청진행상태코드

    	model.addAttribute("operFrmCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));
    	model.addAttribute("operFrmCdList", codeService.selectCodeList(codeVo));

    	//심사점수 목록 조회
    	List<AsgsysSrngVo> srngScrList = asgsysSrngService.selectSrngScrList(asgsysSrngVo);

    	asgsysSrngVo.setFormid(srngScrList.get(0).getFormid());
    	model.addAttribute("scrHeader", asgsysSrngService.selectSrngScrHeader(asgsysSrngVo));
    	model.addAttribute("srngScrList", srngScrList);

    	if(srngScrList.size() > 0) {
    	}

    	//심사점수 목록 헤더 조회
    	model.addAttribute("srngFormList", asgsysSrngService.selectSrngFormList(asgsysSrngVo));

    	//신청 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(aplyInfo.getAplyFilegrpid());

            model.addAttribute("aplyFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("aplyFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(aplyInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}


    	model.addAttribute("aplyInfo", aplyInfo);

    	return "mng/asgsysSrng/jdgsSrng";
    }
    /**
     * @Title : sprtgrpSrng
     * @Description : 지원단심사 화면이동
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/sprtgrpSrngForm.html")
    public String sprtgrpSrngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	//신청정보조회
    	AsgsysSrngVo aplyInfo = asgsysSrngService.selectAplyInfo(asgsysSrngVo);

    	model.addAttribute("sprtgrpCheckList", asgsysSrngService.selectCheckList(aplyInfo));

    	//운영형태코드 목록 조회
    	CodeVo codeVo = new CodeVo();
    	codeVo.setCdgrpid("120");    //신청진행상태코드

    	model.addAttribute("operFrmCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));
    	model.addAttribute("operFrmCdList", codeService.selectCodeList(codeVo));

    	//신청 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(aplyInfo.getAplyFilegrpid());

            model.addAttribute("aplyFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("aplyFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(aplyInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	model.addAttribute("aplyInfo", aplyInfo);
    	return "mng/asgsysSrng/sprtgrpSrng";
    }

    /**
     * @Title : rsltRpt
     * @Description : 결과보고 화면 이동
     * @param AsgsysSrngVo객체
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/rsltRptForm.html")
    public String rsltRptForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	//신청정보조회
    	AsgsysSrngVo aplyInfo = asgsysSrngService.selectAplyInfo(asgsysSrngVo);


    	//운영형태코드 목록 조회
    	CodeVo codeVo = new CodeVo();
    	codeVo.setCdgrpid("120");    //신청진행상태코드

    	model.addAttribute("operFrmCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));
    	model.addAttribute("operFrmCdList", codeService.selectCodeList(codeVo));
    	model.addAttribute("aplyInfo", aplyInfo);

    	//신청 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getAplyFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(aplyInfo.getAplyFilegrpid());

            model.addAttribute("aplyFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("aplyFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(aplyInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	//심사점수 목록 조회
    	List<AsgsysSrngVo> srngScrList = asgsysSrngService.selectSrngScrList(asgsysSrngVo);

    	if(srngScrList.size() > 0) {
    		model.addAttribute("srngScrList", srngScrList);

    		asgsysSrngVo.setFormid(srngScrList.get(0).getFormid());

    		//심사점수 목록 헤더 조회
    		model.addAttribute("scrHeader", asgsysSrngService.selectSrngScrHeader(asgsysSrngVo));
    	}

    	return "mng/asgsysSrng/rsltRpt";
    }

    //지정탈락

    /**
    * @Title : prgrmDstnctn
    * @Description : 지정신청-프로그램우수성 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmDstnctn.html")
    public String prgrmDstnctnForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	PrgrmSchdlVo prgrmSchdlVo = new PrgrmSchdlVo();
    	EmrgcyActnPlanVo emrgcyActnPlanVo = new EmrgcyActnPlanVo();

    	//프로그램 우수성 상세 조회 tb_ass_prgrm_dstnctn
    	AsgsysSrngVo prgrmDstnctnInfo = asgsysSrngService.selectPrgrmDstnctn(asgsysSrngVo);
    	model.addAttribute("prgrmDstnctnInfo", prgrmDstnctnInfo);

    	//컨설팅목록
    	List<DsgnPrgrmVo> csltngList = asgsysSrngService.selectCsltngList(prgrmDstnctnInfo);
    	model.addAttribute("csltngList", csltngList );

    	//교육주제코드목록
    	List<String> eduSbjctCdLst = new ArrayList<>();
    	String cdLst = prgrmDstnctnInfo.getEduSbjctCdLst();
    	if (cdLst != null && !cdLst.isEmpty()) {
    	    String[] cdArray = cdLst.split(",");
    	    eduSbjctCdLst = Arrays.asList(cdArray);
    	}
    	model.addAttribute("eduSbjctCdLst", eduSbjctCdLst);

    	BeanUtils.copyProperties(asgsysSrngVo, prgrmSchdlVo);
    	BeanUtils.copyProperties(asgsysSrngVo, emrgcyActnPlanVo);

    	List<PrgrmSchdlVo>     prgrmSchdlList    = asgsysSrngService.selectPrgrmSchdlList(prgrmSchdlVo);    //프로그램 운영일정
    	List<EmrgcyActnPlanVo> emrgcyActnPlanLst = asgsysSrngService.selectEmrgcyActnPlanList(emrgcyActnPlanVo);    //대처계획


    	//프로그램 운영일정 목록 조회 tb_ass_prgrm_schdl
    	if(0 == prgrmSchdlList.size()) {
    		prgrmSchdlVo.setRnd("1차시");
    		prgrmSchdlList.add(0, prgrmSchdlVo);
    	}
    	model.addAttribute("prgrmSchdlList", prgrmSchdlList);

    	//대처계획 목록 조회    ASS_비상_조치_계획	TB_ASS_EMRGCY_ACTN_PLAN
    	if(0 == emrgcyActnPlanLst.size()) {
    		emrgcyActnPlanLst.add(0, emrgcyActnPlanVo);
    	}

    	model.addAttribute("emrgcyActnPlanLst", emrgcyActnPlanLst);

    	FileVo fileVo = new FileVo();
    	List<FileVo> eduPhotoFileList = new ArrayList<FileVo>();

    	//교육사진 그룹 조회
    	if (prgrmDstnctnInfo.getEduPhotoFilegrpid() != null && !prgrmDstnctnInfo.getEduPhotoFilegrpid().equals(0)) {

    		fileVo.setFilegrpid(prgrmDstnctnInfo.getEduPhotoFilegrpid());

    		eduPhotoFileList = asgsysSrngService.selectEvdncDcmntFileList(fileVo);

    	}
    	for(int i=1; i < 4; i++) {    //교육사진 3개

    		if(eduPhotoFileList.size() == (i-1)) {

    			FileVo rowVo = new FileVo();
    			rowVo.setFileIdntfcKey("");
    			eduPhotoFileList.add((i-1), rowVo);
    		}
    	}
    	model.addAttribute("eduPhotoFileList", eduPhotoFileList);

    	return "mng/asgsysSrng/prgrmDstnctn";
    }

    /**
     * 교육사진파일그룹id 수정
     *
     * @Title       : updateEduPhotoFilegrpid
     * @Description : 교육사진파일그룹id 수정
     * @param asgsysSrngVo AsgsysSrngVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/updateEduPhotoFilegrpid.do")
    @ResponseBody
    public Map<String, Object> updateEduPhotoFilegrpid(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.updateEduPhotoFilegrpid(asgsysSrngVo);

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
    * 프로그램 우수성 등록
    *
    * @Title       : insertPrgrmDstnctn
    * @Description : 프로그램 우수성 등록
    * @param asgsysSrngVo AsgsysSrngVo객체
    * @param bindingResult1 asgsysSrngVo 유효성검증결과
    * @param memberDtlVo MemberDtlVo객체
    * @param bindingResult2 memberDtlVo 유효성검증결과
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/insertPrgrmDstnctn.do")
    @ResponseBody
    public Map<String, Object> insertPrgrmDstnctn(@Valid AsgsysSrngVo asgsysSrngVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        asgsysSrngVo.setUser(user);

        int retVal = 0;

        retVal = asgsysSrngService.insertPrgrmDstnctn(asgsysSrngVo);

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
     * 프로그램 우수성 수정
     *
     * @Title       : updatePrgrmDstnctn
     * @Description : 프로그램 우수성 수정
     * @param asgsysSrngVo AsgsysSrngVo객체
     * @param bindingResult1 asgsysSrngVo 유효성검증결과
     * @param memberDtlVo MemberDtlVo객체
     * @param bindingResult2 memberDtlVo 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/updatePrgrmDstnctn.do")
    @ResponseBody
    public Map<String, Object> updatePrgrmDstnctn(@Valid AsgsysSrngVo asgsysSrngVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if (bindingResult1.hasErrors()) {
    		FieldError fieldError = bindingResult1.getFieldError();
    		if (fieldError != null) {
    			resultMap.put("msg", fieldError.getDefaultMessage());
    		}
    		return resultMap;
    	}


    	asgsysSrngVo.setUser(user);
    	int retVal = 0;
    	retVal = asgsysSrngService.updatePrgrmDstnctn(asgsysSrngVo);

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
    * @Title : prgrmOperMngForm
    * @Description : 지정신청-프로그램운영관리 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmOperMngForm.html")
    public String prgrmOperMngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	ExpndArtclVo expndArtclVo = new ExpndArtclVo();
    	TchaidFcltVo tchaidFcltVo = new TchaidFcltVo();

    	BeanUtils.copyProperties(asgsysSrngVo, expndArtclVo);
    	BeanUtils.copyProperties(asgsysSrngVo, tchaidFcltVo);

    	//프로그램 운영관리 조회
    	model.addAttribute("prgrmOperMngInfo", asgsysSrngService.selectPrgrmOperMng(asgsysSrngVo));

    	//지출항목 목록 조회
    	List<ExpndArtclVo> expndArtclList = asgsysSrngService.selectExpndArtclList(expndArtclVo);
    	if(0 == expndArtclList.size()) {

    		expndArtclList.add(0, expndArtclVo);
    	}
    	model.addAttribute("expndArtclList", expndArtclList);

    	//교구 및 시설 목록 조회
    	List<TchaidFcltVo> tchaidFcltList = asgsysSrngService.selectTchaidFcltList(tchaidFcltVo);

    	if(0 == tchaidFcltList.size()) {
    		tchaidFcltList.add(0, tchaidFcltVo);
    	}
    	model.addAttribute("tchaidFcltList", tchaidFcltList);

    	return "mng/asgsysSrng/prgrmOperMngForm";
    }

    /**
    * 프로그램운영관리 수정
    *
    * @Title : updatePrgrmOperMng
    * @Description : 프로그램운영관리 수정
    * @param asgsysSrngVo
    * @param user
    * @throws Exception
    * @return Map<String,Object> 응답결과객체
    */
    @RequestMapping(value = "/mng/asgsysSrng/updatePrgrmOperMng.do")
    @ResponseBody
    public Map<String, Object> updatePrgrmOperMng(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.updatePrgrmOperMng(asgsysSrngVo);

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
     * 프로그램운영관리 등록
     *
     * @Title : insertPrgrmOperMng
     * @Description : 프로그램운영관리 등록
     * @param asgsysSrngVo
     * @param user
     * @return Map<String,Object> 응답결과객체
     * @throws Exception
     */
    @RequestMapping(value = "/mng/asgsysSrng/insertPrgrmOperMng.do")
    @ResponseBody
    public Map<String, Object> insertPrgrmOperMng(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.insertPrgrmOperMng(asgsysSrngVo);

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
    * @Title : prgrmEvl
    * @Description : 지정신청-프로그램평가 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmEvl.html")
    public String prgrmEvlForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	AsgsysSrngVo prgrmEvlInfo = asgsysSrngService.selectPrgrmEvl(asgsysSrngVo);
		if(CommonUtil.isEmpty(prgrmEvlInfo.getPrgrmid())) {
			prgrmEvlInfo = new AsgsysSrngVo();
			BeanUtils.copyProperties(asgsysSrngVo, prgrmEvlInfo);
		}

		model.addAttribute("prgrmEvlInfo", prgrmEvlInfo);

    	return "mng/asgsysSrng/prgrmEvl";
    }

    /**
     * @Title : updatePrgrmEvl
     * @Description : 프로그램 평가 수정
     * @param AsgsysSrngVo객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception
     */
    @RequestMapping(value = "/mng/asgsysSrng/updatePrgrmEvl.do")
    @ResponseBody
    public Map<String, Object> updatePrgrmEvl(@Valid AsgsysSrngVo asgsysSrngVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.updatePrgrmEvl(asgsysSrngVo);

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
    * @Title : ldrQlfcForm
    * @Description : 심사위원/지원단-지도자의자격및배치 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/ldrQlfcForm.html")
    public String prgrmLdrForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

		//책임개발자 목록 조회
		model.addAttribute("ldrList", asgsysSrngService.selectLdrList(asgsysSrngVo));
		//책임개발자 이력 조회
		model.addAttribute("snrstfdvlprHstry", asgsysSrngService.selectSnrstfdvlprHstry(asgsysSrngVo));
		//책임개발자 학력사항 목록 조회
		model.addAttribute("acbgList", asgsysSrngService.selectSnrstfdvlprAcbgList(asgsysSrngVo));
		//책임개발자 경력사항 목록 조회
		model.addAttribute("careerList", asgsysSrngService.selectSnrstfdvlprCareerList(asgsysSrngVo));
		//책임개발자 자격사항 목록 조회
		model.addAttribute("qlfcList", asgsysSrngService.selectSnrstfdvlprQlfcList(asgsysSrngVo));

    	return "mng/asgsysSrng/ldrQlfcForm";
    }

    /**
    * 지도자의자격및배치 등록
    *
    * @Title : insertPrgrmLdrQlfc
    * @Description : 지도자의자격및배치 등록
    * @param asgsysSrngVo
    * @param user
    * @param request
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/asgsysSrng/insertPrgrmLdrQlfc.do")
    @ResponseBody
    public Map<String, Object> insertPrgrmLdrQlfc(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal+=asgsysSrngService.insertLdrQlfcForm(asgsysSrngVo);

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
    * @Title : sftyMngForm
    * @Description : 지정신청-프로그램 안전관리 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/sftyMngForm.html")
    public String sftyMngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	//안전관리탭 상세 정보 조회
    	AsgsysSrngVo sftyMngInfo = asgsysSrngService.selectSftyMng(asgsysSrngVo);

    	if(null == sftyMngInfo) {
    		sftyMngInfo = new AsgsysSrngVo();
    		BeanUtils.copyProperties(asgsysSrngVo, sftyMngInfo);
    	}

    	model.addAttribute("sftyMngInfo", sftyMngInfo);

    	//안전관리 메뉴얼 첨부파일
    	if (!StringUtil.nvl(sftyMngInfo.getFilegrpid()).equals("") && !StringUtil.nvl(sftyMngInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(sftyMngInfo.getFilegrpid());

            model.addAttribute("mnlFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("mnlFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(sftyMngInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(sftyMngInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(sftyMngInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	return "mng/asgsysSrng/sftyMngForm";
    }


    /**
     * @Title : 안전관리 수정
     * @Description : user insert
     * @param UserTempVo
     * @throws Exception
     * @return Map<String,Object> 응답결과객체
     */
    @RequestMapping(value = "/mng/asgsysSrng/updateSftyMng.do")
    @ResponseBody
    public Map<String, Object> updateSftyMng(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	retVal = asgsysSrngService.updateSftyMng(asgsysSrngVo);

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
    * @Title : prgrmDstnctn
    * @Description : 지정신청-체크리스트 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/assChklstForm.html")
    public String assChklstForm(AsgsysSrngVo asgsysSrngVo, Model model,@UserInfo UserVo user) throws Exception {

    	//지정신청상세정보 조회
    	AsgsysSrngVo assChklstInfo = asgsysSrngService.selectAssChklstForm(asgsysSrngVo);
    	model.addAttribute("loginUserid", user.getUserid());
    	model.addAttribute("assChklstInfo", assChklstInfo);

    	model.addAttribute("sprtgrpCheckList", asgsysSrngService.selectCheckList(assChklstInfo));

    	return "mng/asgsysSrng/assChklstForm";
    }
    //updateAssChklst
    /**
     * 지원단심사 수정
     *
     * @Title : updateAssChklst
     * @Description : 지원단심사 수정
     * @param asgsysSrngVo
     * @param user
     * @throws Exception
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/asgsysSrng/updateAssChklst.do")
     @ResponseBody
     public Map<String, Object> updateAssChklst(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user, HttpServletRequest request) throws Exception {

     	Map<String, Object> resultMap = new HashMap<String, Object>();

     	asgsysSrngVo.setUser(user);
     	asgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));

     	int ret = 0;
     	ret = asgsysSrngService.updateAssChklst(asgsysSrngVo);

         if (ret > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "저장에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "저장에 실패했습니다.");
         }

         return resultMap;
     }

    /**
    * 지원단심사 등록
    *
    * @Title : insertSprtgrpSrng
    * @Description : 지원단심사 등록
    * @param asgsysSrngVo
    * @param user
    * @throws Exception
    * @return Map<String,Object>
    */
 	@RequestMapping(value = "/mng/asgsysSrng/insertSprtgrpSrng.do")
	@ResponseBody
	public Map<String, Object> insertSprtgrpSrng(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user, HttpServletRequest request) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		asgsysSrngVo.setUser(user);
		asgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));

		int retVal = 0;

		if (null != asgsysSrngVo.getSbmsnid() && 0 != asgsysSrngVo.getSbmsnid()) {
			retVal = asgsysSrngService.updateSprtgrpSrng(asgsysSrngVo);
		} else {
			retVal = asgsysSrngService.insertSprtgrpSrng(asgsysSrngVo);
		}

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
    * 지원단 캘린더 팝업 오픈
    *
    * @Title : sprtgrpCalenderPopup
    * @Description : 지원단 캘린더 팝업 오픈
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return String
    * @throws Exception
    */
    @RequestMapping(value = "/mng/asgsysSrng/sprtgrpCalenderPopup.html")
    public String sprtgrpCalenderPopup(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	//캘린더 조회
    	model.addAttribute("clndrList", asgsysSrngService.selectSprtgrpClndrList(asgsysSrngVo));

    	return "mng/asgsysSrng/sprtgrpCalenderPopup";
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
    @RequestMapping(value = "/mng/asgsSrng/selectSprtgrpClndrList.do")
    @ResponseBody
    public Map<String, Object> selectSprtgrpClndrList(AsgsysSrngVo asgsysSrngVo) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	List<AsgsysSrngVo> result = null;

    	result = asgsysSrngService.selectSprtgrpClndrList(asgsysSrngVo);
    	//todo 캘린더 조회
        resultMap.put("list", result);
        return resultMap;
    }


    /**
    * 총평 엑셀 다운로드
    *
    * @Title : gnrlrvwExcelDownList
    * @Description : 총평 엑셀 다운로드
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return void
    * @throws Exception
    */
    @RequestMapping(value = "/mng/asgsysSrng/gnrlrvwExcelDownList.do")
    public void gnrlrvwExcelDownList(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {
    	asgsysSrngService.gnrlrvwExcelDownList(asgsysSrngVo, response, request);
    }

    /**
    * 신청목록 엑셀 다운로드
    *
    * @Title : aplyExcelDownList
    * @Description : 신청목록 엑셀 다운로드
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return void
    * @throws Exception
    */
    @RequestMapping(value = "/mng/asgsysSrng/aplyExcelDownList.do")
    public void aplyExcelDownList(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {
    	asgsysSrngService.aplyExcelDownList(asgsysSrngVo, response, request);
    }

    /**
     * 보완요청 팝업 오픈
     *
     * @Title : splmntDmndPopup
     * @Description : 보완요청 팝업 오픈
     * @param request
     * @param response
     * @param asgsysSrngVo
     * @throws Exception
     * @return void
     */
    @RequestMapping(value = "/mng/asgsysSrng/splmntDmndPopup.html")
    public String splmntDmndPopup(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	model.addAttribute("mode", asgsysSrngVo.getMode());
    	model.addAttribute("sttsCd", asgsysSrngVo.getSttsCd());



    	if(null != asgsysSrngVo.getSplmntDmndid()) {
    		model.addAttribute("splmntDmndInfo", asgsysSrngService.selectSplmntDmnd(asgsysSrngVo));
    	}else {
    		AsgsysSrngVo splmntDmndInfo = new AsgsysSrngVo();

    		splmntDmndInfo.setPrgrmid(asgsysSrngVo.getPrgrmid());
    		model.addAttribute("splmntDmndInfo", splmntDmndInfo);
    	}

    	return "mng/asgsysSrng/splmntDmndPopup";
    }


	/**
	* 보완요청 등록
	*
	* @Title : insertSplmntDmnd
	* @Description : 보완요청 등록
	* @param asgsysSrngVo
	* @param bindingResult1
	* @param user
	* @return Map<String,Object> 응답결과객체
	* @throws Exception
	*/
	@RequestMapping(value = "/mng/asgsysSrng/insertSplmntDmnd.do")
	@ResponseBody
	public Map<String, Object> insertSplmntDmnd(@Valid AsgsysSrngVo asgsysSrngVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();

		int retVal = 0 ;

		if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

		asgsysSrngVo.setUser(user);
		retVal = asgsysSrngService.insertSplmntDmnd(asgsysSrngVo);

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
	 * 보완요청 수정
	 *
	 * @Title : updateSplmntDmnd
	 * @Description : 보완요청 수정
	 * @param asgsysSrngVo
	 * @param bindingResult1
	 * @param user
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/asgsysSrng/updateSplmntDmnd.do")
	@ResponseBody
	public Map<String, Object> updateSplmntDmnd(@Valid AsgsysSrngVo asgsysSrngVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();

		int retVal = 0 ;

		if (bindingResult1.hasErrors()) {
			FieldError fieldError = bindingResult1.getFieldError();
			if (fieldError != null) {
				resultMap.put("msg", fieldError.getDefaultMessage());
			}
			return resultMap;
		}

		asgsysSrngVo.setUser(user);
		retVal = asgsysSrngService.updateSplmntDmnd(asgsysSrngVo);

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
	 * 보완요청 삭제
	 *
	 * @Title : deleteSplmntDmnd
	 * @Description : 보완요청 삭제
	 * @param asgsysSrngVo
	 * @param user
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception
	 */
    @RequestMapping(value = "/mng/asgsysSrng/deleteSplmntDmnd.do")
    @ResponseBody
    public Map<String, Object> deleteSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();

		int retVal = 0 ;

		retVal = asgsysSrngService.deleteSplmntDmnd(asgsysSrngVo);

		if (retVal > 0) {
			resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
			resultMap.put("msg", "삭제 성공하였습니다.");
		} else {
			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
			resultMap.put("msg", "삭제 실패했습니다.");
		}
		return resultMap;
    }

    /**
     * @Title : dsgnSrngForm
     * @Description : 보완요청 목록조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/selectSplmntDmndList.do")
    @ResponseBody
    public Map<String, Object> selectSplmntDmndList(AsgsysSrngVo asgsysSrngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<AsgsysSrngVo> result = null;

        result = asgsysSrngService.selectSplmntDmndList(asgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("splmntDmndList", result);

        return resultMap;

    }

    /**********************************************************************************
     * 심사위원심사
     **********************************************************************************/
    /**
    * @Title : dsgnSrngMainForm
    * @Description : 심사위원심사메인 화면이동
    * @throws Exception :
    * @return String 이동화면경로
    * @throws Exception 예외
    */
   @RequestMapping(value = "/mng/asgsysSrng/jdgsSrngMainForm.html")
   public String jdgsSrngMainForm(AsgsysSrngVo asgsysSrngVo, Model model, @UserInfo UserVo user) throws Exception {

	   model.addAttribute("userid",user.getUserid());
	   model.addAttribute("rolid" ,user.getRoleInfo().getRoleid());

	   log.info("@@@@@@@@@@@@@@@ : "+ user.getRoleInfo().getRoleid());
	   log.info("@@@@@@@@@@@@@@@ : "+ user.toString());

	   //심사위원 목록 조회
	   model.addAttribute("list",asgsysSrngService.selectjdgsList(asgsysSrngVo));
       return "mng/asgsysSrng/jdgsSrngMain";
   }

   /**
   * @Title : dsgnSrngMainForm
   * @Description : 심사위원심사 목록조회
   * @param AsgsysSrngVo객체
   * @return Map<String,Object> 응답결과객체
   * @throws Exception 예외
   */
  @RequestMapping(value = "/mng/asgsysSrng/selectJdgsSrngList.do")
  @ResponseBody
  public Map<String, Object> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception {
	   Map<String, Object> resultMap = new HashMap<>();
      List<AsgsysSrngVo> result = null;

      //심사위원심사 목록조회
	   result = asgsysSrngService.selectJdgsSrngList(asgsysSrngVo);

      if (result.size() > 0) {
          resultMap.put("totalCount", (result.get(0).getTotalCount()));
      } else {
          resultMap.put("totalCount", 0);
      }

      resultMap.put("list", result);

      return resultMap;
  }

  /**
   * @Title : jdgsSrngMain
   * @Description : 심사위원심사 목록 엑셀 다운로드
   * @param AsgsysSrngVo객체
   * @throws Exception 예외
   */
	@RequestMapping(value = "/mng/asgsysSrng/jdgsSrngListExcelDown.do")
	public void jdgsSrngMainExcelDownList(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {
		asgsysSrngService.jdgsSrngListExcelDown(asgsysSrngVo, response, request);
	}

	/**
     * @Title : dsgnSrngDetailForm
     * @Description : 심사위원심사 상세 화면이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/jdgsSrngDetailForm.html")
    public String jdgsSrngDetailForm(AsgsysSrngVo asgsysSrngVo, Model model, @UserInfo UserVo user) throws Exception {

    	DsgnSrngFormVo dsgnSrngFormVo = new DsgnSrngFormVo();
    	AsgsysSrngVo jdgsSrngInfo = asgsysSrngService.selectJdgsSrngDetail(asgsysSrngVo);

    	model.addAttribute("dsgnSrgnFormList",asgsysSrngService.selectSrngQitemList(jdgsSrngInfo));

    	model.addAttribute("loginUserid", user.getUserid());
    	model.addAttribute("jdgsSrngInfo", jdgsSrngInfo);

    	//신청 첨부파일
    	if (!StringUtil.nvl(jdgsSrngInfo.getAplyFilegrpid()).equals("") && !StringUtil.nvl(jdgsSrngInfo.getAplyFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(jdgsSrngInfo.getAplyFilegrpid());

            model.addAttribute("aplyFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("aplyFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(jdgsSrngInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(jdgsSrngInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(jdgsSrngInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	BeanUtils.copyProperties(jdgsSrngInfo, dsgnSrngFormVo);
    	logger.info(dsgnSrngFormVo.toString());
//    	model.addAttribute("dsgnSrgnFormList", asgsysSrngService.selectDsgnSrgnFormList(dsgnSrngFormVo));

    	//심사양식 목록 조회
    	model.addAttribute("srngFormList",asgsysSrngService.selectSrngFormQitemList(dsgnSrngFormVo));

    	return "mng/asgsysSrng/jdgsSrngForm";
    }

    /**
     * @Title : selectDsgnSrgnFormList
     * @Description : 심사양식 목록조회
     * @param AsgsysSrngVo객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/selectDsgnSrgnFormList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnSrgnFormList(AsgsysSrngVo asgsysSrngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        DsgnSrngFormVo dsgnSrngFormVo = new DsgnSrngFormVo();

        BeanUtils.copyProperties(asgsysSrngVo, dsgnSrngFormVo);

        resultMap.put("srngFormList", asgsysSrngService.selectDsgnSrgnFormList(dsgnSrngFormVo));


        return resultMap;
    }

    /**
     *
     * @Title       : updateJdgsSrngDetail
     * @Description : 심사위원심사 수정
     * @param asgsysSrngVo AsgsysSrngVo , TeacherVo TeacherVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/updateJdgsSrngDetail.do")
    @ResponseBody
    public Map<String, Object> updateJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user, HttpServletRequest request) throws Exception {

    	Map<String, Object> resultMap = new HashMap<>();
    	List<AsgsysSrngVo> result = null;

    	int retVal = 0 ;

    	asgsysSrngVo.setUser(user);
    	asgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));

    	if(CommonUtil.isEmpty(asgsysSrngVo.getSbmsnid())) {
    		retVal = asgsysSrngService.insertJdgsSrngDetail(asgsysSrngVo);
    	}else {
    		retVal = asgsysSrngService.updateJdgsSrngDetail(asgsysSrngVo);
    	}

    	if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
    	return resultMap;
    }


    /**********************************************************************************
     * 지원단심사
     **********************************************************************************/

    /**
    * 지원단심사 목록 화면 이동
    *
    * @Title       : sprtgrpSrngListForm
    * @Description : 지원단심사 목록 화면 이동
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/sprtgrpSrngListForm.html")
    public String sprtgrpSrngListForm(AsgsysSrngVo asgsysSrngVo, Model model ,@UserInfo UserVo user) throws Exception {

    	model.addAttribute("userid", user.getUserid());
        return "mng/asgsysSrng/sprtgrpSrngList";
    }

    /**
    * 지원단심사 목록 조회
    *
    * @Title       : selectMemberList
    * @Description : 지원단심사 목록 조회
    * @param dsgnPrgrmVo
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/selectSprtgrpSrngList.do")
    @ResponseBody
    public Map<String, Object> selectSprtgrpSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<AsgsysSrngVo> result = null;

        result = asgsysSrngService.selectSprtgrpSrngList(asgsysSrngVo);

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
    * @Title       : sprtgrpSrngInsertForm
    * @Description : 지원단심사 등록 화면 이동
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/sprtgrpSrngInsertForm.html")
    public String sprtgrpSrngInsertForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {


    	//신청자와 심사위원의 체크리스 제출 ID 조회
    	AsgsysSrngVo asgsysSrngInfo = asgsysSrngService.selectSprtgrpSrng(asgsysSrngVo);

    	//신청 첨부파일
    	if (!StringUtil.nvl(asgsysSrngInfo.getAplyFilegrpid()).equals("") && !StringUtil.nvl(asgsysSrngInfo.getAplyFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(asgsysSrngInfo.getAplyFilegrpid());

            model.addAttribute("aplyFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("aplyFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(asgsysSrngInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(asgsysSrngInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(asgsysSrngInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	model.addAttribute("sprtgrpSrngInfo", asgsysSrngInfo);
    	model.addAttribute("sprtgrpCheckList", asgsysSrngService.selectCheckList(asgsysSrngInfo));

        return "mng/asgsysSrng/sprtgrpSrngInsertForm";
    }


}


