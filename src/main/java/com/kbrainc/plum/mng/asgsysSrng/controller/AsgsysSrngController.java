package com.kbrainc.plum.mng.asgsysSrng.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeServiceImpl;
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
    private CodeServiceImpl codeServiceImpl;


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

    	model.addAttribute("sttsCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));
    	model.addAttribute("sttsCdList", codeServiceImpl.selectCodeList(codeVo));

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

        return "mng/asgsysSrng/aplyInfo";
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

        //심사위원심사 목록조회
  	   result = asgsysSrngService.selectPicList(asgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("picList", result);

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

    	return "mng/asgsysSrng/rsltRpt";
    }


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

    	//프로그램 우수성 상세 조회 tb_ass_prgrm_dstnctn
    	model.addAttribute("prgrmDstnctnInfo", asgsysSrngService.selectPrgrmDstnctn(asgsysSrngVo));

    	//프로그램 운영일정 목록 조회 tb_ass_prgrm_schdl
    	//대처계획 목록 조회    ASS_비상_조치_계획	TB_ASS_EMRGCY_ACTN_PLAN

    	//관련 교육과정 목록 조회 ASS_프로그램_교육_주제	tb_ass_prgrm_edu_sbjct	TB_ASS_PRGRM_EDU_SBJCT

    	return "mng/asgsysSrng/prgrmDstnctn";
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

    	//프로그램 운영관리 조회
    	model.addAttribute("prgrmOperMngInfo", asgsysSrngService.selectPrgrmOperMng(asgsysSrngVo));
    	//교구 및 시설 조회
    	model.addAttribute("tchaidFcltList", asgsysSrngService.selectTchaidFcltList(asgsysSrngVo));


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
    * @return Map<String,Object>
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
    * @Title : prgrmEvl
    * @Description : 지정신청-프로그램평가 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmEvl.html")
    public String prgrmEvlForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
    	model.addAttribute("prgrmEvlInfo", asgsysSrngService.selectPrgrmEvl(asgsysSrngVo));

    	return "mng/asgsysSrng/prgrmEvl";
    }

    /**
    * @Title : prgrmLdr
    * @Description : 심사위원/지원단-지도자의자격및배치 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmLdr.html")
    public String prgrmLdrForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	return "mng/asgsysSrng/prgrmLdr";
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
    	model.addAttribute("sftyMngInfo", asgsysSrngService.selectSftyMng(asgsysSrngVo));
    	return "mng/asgsysSrng/sftyMngForm";
    }


    /**
     * @Title : 안전관리 수정
     * @Description : user insert
     * @param UserTempVo
     * @throws Exception
     * @return String Y or N
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
    @RequestMapping(value = "/mng/asgsysSrng/assChklst.html")
    public String assChklstForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	return "mng/asgsysSrng/assChklst";
    }

    /**
    * 지원단 캘린더 팝업 오픈
    *
    * @Title : aplyExcelDownList
    * @Description : 지원단 캘린더 팝업 오픈
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @throws Exception
    * @return void
    */
    @RequestMapping(value = "/mng/asgsysSrng/sprtgrpCalenderPopup.html")
    public String sprtgrpCalenderPopup(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {

    	//todo 캘린더 조회
    	return "mng/asgsysSrng/sprtgrpCalenderPopup";
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
    public String splmntDmndPopup(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {


    	return "mng/asgsysSrng/splmntDmndPopup";
    }

    /**
    * @Title : prgrmDstnctn
    * @Description : 지정신청목록 엑셀 다운로드
    * @param AsgsysSrngVo객체
    * @throws Exception 예외
    */
	@RequestMapping(value = "/mng/asgsysSrng/aplyExcelDownList.do")
	public void aplyExcelDownList(HttpServletRequest request, HttpServletResponse response, AsgsysSrngVo asgsysSrngVo) throws Exception {
		asgsysSrngService.aplyExcelDownList(asgsysSrngVo, response, request);
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
   public String jdgsSrngMainForm() throws Exception {
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

	///mng/asgsysSrng/jdgsSrngForm.html
	/**
     * @Title : dsgnSrngDetailForm
     * @Description : 심사위원심사 상세 화면이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
	//심사신청의 심사위언심사 탭과 name이 중복되어 Detail추가
    @RequestMapping(value = "/mng/asgsysSrng/jdgsSrngDetailForm.html")
    public String jdgsSrngDetailForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	model.addAttribute("jdgsSrngInfo", asgsysSrngService.selectJdgsSrngDetail(asgsysSrngVo));
    	return "mng/asgsysSrng/jdgsSrngForm";
    }

    /**
     *
     * @Title       : updateJdgsSrngDetail
     * @Description : asgsysSrng
     * @param memberVo MemberVo , TeacherVo TeacherVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    //심사신청의 심사위언심사 탭과 name이 중복되어 Detail추가
    @RequestMapping(value = "/mng/asgsysSrng/updateJdgsSrngDetail.do")
    @ResponseBody
    public Map<String, Object> updateJdgsSrngDetail(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<>();
    	List<AsgsysSrngVo> result = null;

    	int retVal = 0 ;

    	asgsysSrngVo.setUser(user);


    	if(null == asgsysSrngVo.getRsltid()) {
    		retVal = asgsysSrngService.insertJdgsSrngDetail(asgsysSrngVo);
    		logger.info("insert 심사 #####################"  );
    	}else {
    		logger.info("asgsysSrngVo.getRgtrid : " + asgsysSrngVo.getRgtrid() );
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
    public String sprtgrpSrngListForm() throws Exception {
        return "mng/asgsysSrng/sprtgrpSrngList";
    }

    /**
    * 지원단심사 목록 조회
    *
    * @Title       : selectMemberList
    * @Description : 지원단심사 목록 조회
    * @param AsgsysSrngVo
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

    	model.addAttribute("sprtgrpSrngInfo", asgsysSrngService.selectSprtgrpSrng(asgsysSrngVo));
        return "mng/asgsysSrng/sprtgrpSrngInsertForm";
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
    public Map<String, Object> insertSprtgrpSrng(@Valid AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	asgsysSrngVo.setUser(user);

    	int retVal = 0;

    	if(null == asgsysSrngVo.getRsltid()) {
    		retVal = asgsysSrngService.insertSprtgrpSrng(asgsysSrngVo);
    		logger.info("insert 심사 #####################"  );
    	}else {
    		logger.info("asgsysSrngVo.getRgtrid : " + asgsysSrngVo.getRgtrid() );
    		retVal = asgsysSrngService.updateSprtgrpSrng(asgsysSrngVo);
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


}
