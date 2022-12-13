package com.kbrainc.plum.mng.asgsysSrng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	/**
     * @Title : dsgnSrngForm
     * @Description : 지정신청 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/dsgnAplyForm.html")
    public String dsgnAplyForm() throws Exception {
        return "mng/asgsysSrng/dsgnAplyForm";
    }

	/**
     * @Title : dsgnSrngForm
     * @Description : 지정심사 화면이동
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/asgsysSrng/dsgnSrngForm.html")
    public String dsgnSrngForm() throws Exception {
        return "mng/asgsysSrng/dsgnSrngForm";
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

    	return "mng/asgsysSrng/dsgnAplyDetailForm";
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
    public Map<String, Object> updateSttsCd(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> resMap = new HashMap<String, Object>();

        int retVal = 0 ;
        logger.info("########### prgrmid ############################ 1");
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
        logger.info("########### prgrmid : " + asgsysSrngVo.getPrgrmid());
        logger.info("########### sttsCd : " + asgsysSrngVo.getSttsCd());
        logger.info("########### prgrmid ############################ 2");
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
        return "mng/asgsysSrng/aplyInfoForm";
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

    	//model.addAttribute("dsgnAplyInfo", asgsysSrngService.selectDsgnAplyDtlInfo(asgsysSrngVo));
    	return "mng/asgsysSrng/jdgsSprtgrpAltmntForm";
    }



}
