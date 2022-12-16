package com.kbrainc.plum.mng.asgsysSrng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.constant.Constant;

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

    	//String sttsCd = asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo);
    	model.addAttribute("sttsCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));

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

    	//model.addAttribute("sttsCd", asgsysSrngService.selectPrgrmSttsCd(asgsysSrngVo));

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

    	model.addAttribute("dsgnAplyInfo", asgsysSrngService.selectDsgnAplyDtlInfo(asgsysSrngVo));
        return "mng/asgsysSrng/aplyInfo";
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
    * @Description : 심사위원/지원단-프로그램우수성 탭
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
    * @Title : prgrmOperMng
    * @Description : 심사위원/지원단-프로그램운영관리 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/prgrmOperMng.html")
    public String prgrmOperMngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	return "mng/asgsysSrng/prgrmOperMng";
    }

    /**
    * @Title : prgrmEvl
    * @Description : 심사위원/지원단-프로그램평가 탭
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
    * @Title : sftyMng
    * @Description : 심사위원/지원단-프로그램 안전관리 탭
    * @param AsgsysSrngVo객체
    * @param model 모델객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/asgsysSrng/sftyMng.html")
    public String sftyMngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

    	return "mng/asgsysSrng/sftyMng";
    }

    /**
    * @Title : prgrmDstnctn
    * @Description : 심사위원/지원단-체크리스트 탭
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

}
