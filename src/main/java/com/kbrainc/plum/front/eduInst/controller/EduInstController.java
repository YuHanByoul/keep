package com.kbrainc.plum.front.eduInst.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.front.eduInst.model.EduInstVo;
import com.kbrainc.plum.front.eduInst.service.EduInstService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
* 사회환경교육기관지정 Controller
*
* <pre>
* com.kbrainc.plum.front.eduInst.controller
* - EduInstController.java
* </pre>
*
* @ClassName : EduInstController
* @Description : 사회환경교육기관지정 Controller
* @author : kbrain
* @date : 2023. 4. 17.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.eduInstController")
@Alias("frontEduInstController")
public class EduInstController {

	@Resource(name = "front.eduInstServiceImpl")
    private EduInstService eduInstService;

	@Autowired
    private CommonService commonService;

	@Autowired
    private FileService fileService;


	/**
	* 사회환경교육기관지정 화면 이동
	*
	* @Title : prgrmListForm
	* @Description : 사회환경교육기관지정 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/instDsgnForm.html")
    public String prgrmListForm(Model model, @UserInfo UserVo user) throws Exception {
		model.addAttribute("loginUserType", user.getLoginUserType());
		model.addAttribute("loginUserid", user.getUserid());
        return "front/eduInst/instDsgnForm";
    }

	/**
	* 약관동의 화면이동
	*
	* @Title : trmsAgreForm
	* @Description : 약관동의 화면이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/trmsAgreForm.html")
	public String trmsAgreForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/trmsAgreForm";
	}

	/**
	* 신청정보 화면 이동
	*
	* @Title : aplyInfoForm
	* @Description : 신청정보 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/aplyInfoForm.html")
	public String aplyInfoForm(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
		EduInstVo aplyInfo = null;


		eduInstVo.setAplcntid(user.getUserid());

		if(null != eduInstVo.getAplyid() && 0 != eduInstVo.getAplyid()) {
			aplyInfo = eduInstService.selectEnvEduInst(eduInstVo);

			//첨부파일
			if (!StringUtil.nvl(aplyInfo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getAtchFilegrpid()).equals(0)) {
	            FileVo fileVo = new FileVo();
	            fileVo.setFilegrpid(aplyInfo.getAtchFilegrpid());
	            model.addAttribute("fileList", fileService.getFileList(fileVo));
			} else {
	            model.addAttribute("fileList", Collections.emptyList());
	        }

		}else {
			aplyInfo  = eduInstService.selectAplyInfoForm(eduInstVo);
			model.addAttribute("fileList", Collections.emptyList());
		}

		model.addAttribute("sidoList", commonService.selectCtprvnList());
		model.addAttribute("aplyInfo", aplyInfo);

		return "front/eduInst/aplyInfoForm";
	}

    /**
    * 신청정보 등록
    *
    * @Title : insertChklstQitem
    * @Description : 신청정보 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/eduInst/insertAplyInfo.do")
    @ResponseBody
    public Map<String, Object> insertChklstQitem(@Valid EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertAplyInfo(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }

        return resultMap;
    }

    /**
     * 신청정보 수정
     *
     * @Title : insertChklstQitem
     * @Description : 신청정보 수정
     * @param eduInstVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/eduInst/updateAplyInfo.do")
    @ResponseBody
    public Map<String, Object> updateChklstQitem(@Valid EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if(bindingResult.hasErrors()) {
    		FieldError fieldError = bindingResult.getFieldError();
    		if(fieldError != null) {
    			resultMap.put("msg", fieldError.getDefaultMessage());
    		}
    		return resultMap;
    	}

    	int retVal = 0;
    	eduInstVo.setUser(user);
    	retVal = eduInstService.updateAplyInfo(eduInstVo);

    	if(retVal > 0) {
    		resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
    		resultMap.put("msg", "수정에 성공하였습니다");
    	} else {
    		resultMap.put("result", Constant.REST_API_RESULT_FAIL);
    		resultMap.put("msg", "수정에 실패하였습니다");
    	}

    	return resultMap;
    }

	/**
	* 운영계획 개요 화면 이동
	*
	* @Title : operPlanOutl
	* @Description : 운영계획 개요 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/operPlanOutlForm.html")
	public String operPlanOutl(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {

		EduInstVo planOutlInfo = null;

		model.addAttribute("loginUserid", user.getUserid());
		if(null != eduInstVo.getAplyid() && 0 != eduInstVo.getAplyid()) {
			planOutlInfo = eduInstService.selectOperPlan(eduInstVo);

			//첨부파일
			if (!StringUtil.nvl(planOutlInfo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(planOutlInfo.getAtchFilegrpid()).equals(0)) {
	            FileVo fileVo = new FileVo();
	            fileVo.setFilegrpid(planOutlInfo.getAtchFilegrpid());
	            model.addAttribute("fileList", fileService.getFileList(fileVo));
			} else {
	            model.addAttribute("fileList", Collections.emptyList());
	        }

		}else {
			//planOutlInfo  = eduInstService.selectplanOutlInfoForm(eduInstVo);
			model.addAttribute("fileList", Collections.emptyList());
		}

		model.addAttribute("sidoList", commonService.selectCtprvnList());
		model.addAttribute("planOutlInfo", planOutlInfo);

		model.addAttribute("planOutlInfo", eduInstService.selectOperPlan(eduInstVo));

		return "front/eduInst/operPlanOutlForm";
	}

	/**
	* 추진일정 화면 이동
	*
	* @Title : propSchdlForm
	* @Description : 추진일정 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/propSchdlForm.html")
	public String propSchdlForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/propSchdlForm";
	}

	/**
	* 교육전문인력현황 화면 이동
	*
	* @Title : mnPwrSttusForm
	* @Description : 교육전문인력현황 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/mnPwrSttusForm.html")
	public String mnPwrSttusForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/mnPwrSttusForm";
	}

	/**
	* 교육프로그램 보유현황 화면 이동
	*
	* @Title : hldngSttsForm
	* @Description : 교육프로그램 보유현황 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/hldngSttsForm.html")
	public String hldngSttsForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/hldngSttsForm";
	}

	/**
	* 교육시설 및 설비현황 화면 이동
	*
	* @Title : fcltSttsForm
	* @Description : 교육시설 및 설비현황 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/fcltSttsForm.html")
	public String fcltSttsForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/fcltSttsForm";
	}

	/**
	* 신청완료 화면 이동
	*
	* @Title : aplyCmptnForm
	* @Description : 신청완료 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value="/front/eduInst/aplyCmptnForm.html")
	public String aplyCmptnForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/eduInst/aplyCmptnForm";
	}

}
