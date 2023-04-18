package com.kbrainc.plum.front.eduInst.controller;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.front.eduInst.model.EduInstVo;
import com.kbrainc.plum.front.eduInst.service.EduInstService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

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
		eduInstVo.setAplcntid(user.getUserid());
		model.addAttribute("aplyInfo", eduInstService.selectAplyInfoForm(eduInstVo));
		model.addAttribute("sidoList", commonService.selectCtprvnList());
		return "front/eduInst/aplyInfoForm";
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
	public String operPlanOutl(Model model, @UserInfo UserVo user) throws Exception {
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
