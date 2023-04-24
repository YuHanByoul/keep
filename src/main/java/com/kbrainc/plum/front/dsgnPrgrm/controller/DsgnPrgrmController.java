package com.kbrainc.plum.front.dsgnPrgrm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.front.dsgnPrgrm.service.DsgnPrgrmService;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
import com.kbrainc.plum.mng.asgsysSrng.model.TchaidFcltVo;
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.cnsltng.service.CnsltngServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 지정프로그램 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.front.dsgnPrgrm.controller
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
@Controller("front.dsgnPrgrmController")
@Alias("front.dsgnPrgrmController")
@Slf4j
public class DsgnPrgrmController {

	@Resource(name = "front.dsgnPrgrmServiceImpl")
    private DsgnPrgrmService dsgnPrgrmService;

    @Autowired
    private AsgsysSrngServiceImpl asgsysSrngService;

    @Autowired
    private CnsltngServiceImpl cnsltngService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private FileServiceImpl fileService;

    /**
    * 지정현황 목록 화면이동
    *
    * @Title : dsgnSttusList
    * @Description : 지정현황 목록 화면이동
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnPrgrm/dsgnSttusList.html")
    public String dsgnSttusList(Model model) throws Exception {
    	model.addAttribute("sidoList", commonService.selectCtprvnList());
        return "front/dsgnPrgrm/dsgnSttusList";
    }

    /**
    * 지정현황 목록 조회
    *
    * @Title : selectDsgnSttusList
    * @Description : 지정현황 목록 조회
    * @param dsgnPrgrmVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnPrgrm/selectDsgnSttusList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnSttusList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	List<DsgnPrgrmVo> list = dsgnPrgrmService.selectDsgnSttusList(dsgnPrgrmVo);
    	if (list.size() > 0) {
    		response.put("totalCount", (list.get(0).getTotalCount()));
    		response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
    	} else {
    		response.put("totalCount", 0);
    	}
    	response.put("list", list);
    	return response;
    }

    /**
    * 지정현황 상세 화면이동
    *
    * @Title : prgrmDetailForm
    * @Description : 지정현황 상세 화면이동
    * @param dsgnPrgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/dsgnPrgrm/dsgnSttusDetailForm.html")
    public String prgrmDetailForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
        DsgnPrgrmVo dsgnSttus = null;
        dsgnSttus = dsgnPrgrmService.selectDsgnSttus(dsgnPrgrmVo);
        model.addAttribute("dsgnSttus", dsgnSttus);
        dsgnPrgrmVo.setInstid(dsgnSttus.getInstid());
        dsgnPrgrmVo.setPrgrmid(dsgnSttus.getPrgrmid());

        List<DsgnPrgrmVo> eduPhotoFileList = null;
        eduPhotoFileList = dsgnPrgrmService.selectEduPhotoFileList(dsgnPrgrmVo);
        model.addAttribute("eduPhotoFileList", eduPhotoFileList);

        List<DsgnPrgrmVo> prgrmList = null;

        prgrmList = dsgnPrgrmService.selectInstPrgrmList(dsgnPrgrmVo);
        if(prgrmList.size() <= 0) {
        	model.addAttribute("prgrmList", "null");
        }else {
        	model.addAttribute("prgrmList", prgrmList);
        }

        List<DsgnPrgrmVo> prgrmSchdlList = null;
        prgrmSchdlList = dsgnPrgrmService.selectPrgrmSchdlList(dsgnPrgrmVo);    //프로그램 운영일정

        if(prgrmSchdlList.size() <= 0) {
            model.addAttribute("prgrmSchdlList", "null");
        }else {
            model.addAttribute("prgrmSchdlList", prgrmSchdlList);
        }

        return "front/dsgnPrgrm/dsgnSttusDetail";
    }

	/**
	* 지정신청 메뉴 이동
	*
	* @Title : dsgnAplyForm
	* @Description : 지정신청 메뉴 이동
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/dsgnAplyForm.html")
	public String dsgnAplyForm(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {
		List<DsgnPrgrmVo> prgrmLst = null;

		dsgnPrgrmVo.setAplcntid(Integer.parseInt(user.getUserid()));
		dsgnPrgrmVo.setSearchSttsCd("111101");    //작성중
		dsgnPrgrmVo.setOpner("aply");
		prgrmLst = dsgnPrgrmService.selectAplyDsctnList(dsgnPrgrmVo);

		model.addAttribute("user", user);
		model.addAttribute("prgrmLst", prgrmLst);
		return "front/dsgnPrgrm/dsgnAplyForm";
	}

	/**
	* 약관동의 화면 이동
	*
	* @Title : trmsAgreForm
	* @Description : 약관동의 화면 이동
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/trmsAgreForm.html")
	public String trmsAgreForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/dsgnPrgrm/trmsAgreForm";
	}

	/**
	* 신청정보 확인 및 증빙자료 화면 이동
	*
	* @Title : evdncDataForm
	* @Description : 신청정보 확인 및 증빙자료 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/evdncDataForm.html")
	public String evdncDataForm(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {

		DsgnPrgrmVo instInfo = new DsgnPrgrmVo();

		//로그인 사용자 정보
		dsgnPrgrmVo.setAplcntid(Integer.parseInt(user.getUserid()));

		instInfo = dsgnPrgrmService.selectInstInfo(dsgnPrgrmVo);

		model.addAttribute("instInfo", instInfo);

		if(instInfo.getFilegrpid() !=null && instInfo.getFilegrpid() !=  "") {
			FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(instInfo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileList", fileList);
            model.addAttribute("fileListCnt", fileList.size());
		} else {
        	model.addAttribute("fileList", null);
        	model.addAttribute("fileListCnt", 0);
        }


		model.addAttribute("fileMap", null);
		model.addAttribute("fileListCnt", 0);

		return "front/dsgnPrgrm/evdncDataForm";
	}


    /**
    * 증빙자료 등록
    *
    * @Title : insertEvdncDataForm
    * @Description : 증빙자료 등록
    * @param dsgnPrgrmVo
    * @param bindingResult1
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnPrgrm/insertEvdncDataForm.do")
    @ResponseBody
    public Map<String, Object> insertEvdncDataForm(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        dsgnPrgrmVo.setUser(user);
        dsgnPrgrmVo.setAplcntid(Integer.parseInt(user.getUserid()));

        int retVal = 0;

        //지정프로그램 저장
        retVal = dsgnPrgrmService.insertPrgrmAssPrgrm(dsgnPrgrmVo);

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
     * 증빙자료 수정
     *
     * @Title : updateEvdncDataForm
     * @Description : 증빙자료 수정
     * @param dsgnPrgrmVo
     * @param bindingResult1
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/dsgnPrgrm/updateEvdncDataForm.do")
    @ResponseBody
    public Map<String, Object> updateEvdncDataForm(@Valid DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	if (bindingResult1.hasErrors()) {
    		FieldError fieldError = bindingResult1.getFieldError();
    		if (fieldError != null) {
    			resultMap.put("msg", fieldError.getDefaultMessage());
    		}
    		return resultMap;
    	}

    	dsgnPrgrmVo.setUser(user);

    	int retVal = 0;

    	//지정프로그램 수정
    	retVal = dsgnPrgrmService.updateAssPrgrm(dsgnPrgrmVo);

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
	* 프로그램우수성 화면 이동
	*
	* @Title : prgrmDstnctnForm
	* @Description : 프로그램우수성 화면 이동
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/prgrmDstnctnForm.html")
	public String prgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {

		dsgnPrgrmVo.setUser(user);
		FileVo fileVo = new FileVo();
		List<FileVo> eduPhotoFileList =null;

		DsgnPrgrmVo aplyInfo         = dsgnPrgrmService.selectAplyInfo(dsgnPrgrmVo);
		List<DsgnPrgrmVo> schdlList  = dsgnPrgrmService.selectPrgrmSchdlList(dsgnPrgrmVo);   //프로그램 일정 목록
    	List<DsgnPrgrmVo> planList   = dsgnPrgrmService.selectPlanList(dsgnPrgrmVo);         //프로그램 대처 계획
    	List<DsgnPrgrmVo> csltngList = dsgnPrgrmService.selectCsltngList(dsgnPrgrmVo);       //컨설팅목록
    	List<DsgnPrgrmVo> sbjctList  = dsgnPrgrmService.selectEduSbjctList(dsgnPrgrmVo);     //교육주제

    	//교육사진 그룹 조회
    	if (aplyInfo.getEduPhotoFilegrpid() != null && !aplyInfo.getEduPhotoFilegrpid().equals(0)) {
    		fileVo.setFilegrpid(aplyInfo.getEduPhotoFilegrpid());
    		eduPhotoFileList = asgsysSrngService.selectEvdncDcmntFileList(fileVo);
    	}

    	model.addAttribute("aplyInfo", aplyInfo);        //신청정보
		model.addAttribute("schdlList", schdlList);
		model.addAttribute("planList", planList );
		model.addAttribute("csltngList", csltngList );
		model.addAttribute("sbjctList", sbjctList );
		model.addAttribute("opner", dsgnPrgrmVo.getOpner());

    	model.addAttribute("eduPhotoFileList", eduPhotoFileList);

		return "front/dsgnPrgrm/prgrmDstnctnForm";
	}

	/**
	* 프로그램 우수성 등록
	*
	* @Title : insertPrgrmDstnctnForm
	* @Description : 프로그램 우수성 등록
	* @param dsgnPrgrmVo
	* @param bindingResult1
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmDstnctnForm.do")
    @ResponseBody
    public Map<String, Object> insertPrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo, @UserInfo UserVo user) throws Exception {

    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	int retVal=0;

    	dsgnPrgrmVo.setUser(user);

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();
		BeanUtils.copyProperties(dsgnPrgrmVo, asgsysSrngVo);
		asgsysSrngVo.setEduSbjctCdLst(dsgnPrgrmVo.getEduSbjctCdLst());

		retVal+=asgsysSrngService.insertPrgrmDstnctn(asgsysSrngVo);

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
	 * 프로그램 우수성 수정
	 *
	 * @Title : updatePrgrmDstnctnForm
	 * @Description : 프로그램 우수성 수정
	 * @param dsgnPrgrmVo
	 * @param user
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/front/dsgnPrgrm/updatePrgrmDstnctnForm.do")
	@ResponseBody
	public Map<String, Object> updatePrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo, @UserInfo UserVo user) throws Exception {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		int retVal=0;

		dsgnPrgrmVo.setUser(user);

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();
		BeanUtils.copyProperties(dsgnPrgrmVo, asgsysSrngVo);

		asgsysSrngVo.setPrgrmSchdlLst(dsgnPrgrmVo.getPrgrmSchdlLst());
		asgsysSrngVo.setEmrgcyActnPlanLst(dsgnPrgrmVo.getEmrgcyActnPlanLst());

		//retVal+=dsgnPrgrmService.updateAssPrgrm(dsgnPrgrmVo);
		retVal+=asgsysSrngService.updatePrgrmDstnctn(asgsysSrngVo);

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
	* 프로그램 평가 체계 화면 이동
	*
	* @Title : prgrmEvlForm
	* @Description : 프로그램 평가 체계 화면 이동
	* @param DsgnPrgrmVo
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/prgrmEvlForm.html")
	public String prgrmEvlForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {
		model.addAttribute("aplyInfo", dsgnPrgrmService.selectPrgrmEvlForm(dsgnPrgrmVo));
		model.addAttribute("opner", dsgnPrgrmVo.getOpner());

		return "front/dsgnPrgrm/prgrmEvlForm";
	}

	/**
	* 프로그램평가 등록
	*
	* @Title : insertPrgrmEvlForm
	* @Description : 프로그램평가 등록
	* @param dsgnPrgrmVo
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmEvlForm.do")
	@ResponseBody
	public Map<String, Object> insertPrgrmEvlForm(DsgnPrgrmVo dsgnPrgrmVo, @UserInfo UserVo user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int retVal=0;

		dsgnPrgrmVo.setUser(user);

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();
		BeanUtils.copyProperties(dsgnPrgrmVo, asgsysSrngVo);

		retVal+=asgsysSrngService.updatePrgrmEvl(asgsysSrngVo);

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
	 * 프로그램 운영관리 화면이동
	 *
	 * @Title : prgrmOperMngForm
	 * @Description : 프로그램 운영관리 화면이동
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/dsgnPrgrm/prgrmOperMngForm.html")
	public String prgrmOperMngForm(AsgsysSrngVo asgsysSrngVo, Model model) throws Exception {

		model.addAttribute("aplyInfo", asgsysSrngService.selectPrgrmOperMng(asgsysSrngVo));


		ExpndArtclVo expndArtclVo = new ExpndArtclVo();
    	TchaidFcltVo tchaidFcltVo = new TchaidFcltVo();

    	BeanUtils.copyProperties(asgsysSrngVo, expndArtclVo);
    	BeanUtils.copyProperties(asgsysSrngVo, tchaidFcltVo);

    	//프로그램 운영관리 조회
    	model.addAttribute("prgrmOperMngInfo", asgsysSrngService.selectPrgrmOperMng(asgsysSrngVo));

    	//지출항목 목록 조회
    	List<ExpndArtclVo> expndArtclList = asgsysSrngService.selectExpndArtclList(expndArtclVo);
    	model.addAttribute("artclList", expndArtclList);

    	//교구 및 시설 목록 조회
    	List<TchaidFcltVo> tchaidFcltList = asgsysSrngService.selectTchaidFcltList(tchaidFcltVo);
    	model.addAttribute("fcltList", tchaidFcltList);

    	model.addAttribute("opner", asgsysSrngVo.getOpner());

		return "front/dsgnPrgrm/prgrmOperMngForm";
	}

	/**
	 * 프로그램 운영 관리 등록
	 *
	 * @Title : insertPrgrmOperMngForm
	 * @Description : 프로그램 운영 관리 등록
	 * @param dsgnPrgrmVo
	 * @param user
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmOperMngForm.do")
	@ResponseBody
	public Map<String, Object> insertPrgrmOperMngForm(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int retVal=0;

		asgsysSrngVo.setUser(user);

		//프로그램 운영관리
		retVal+=asgsysSrngService.insertPrgrmOperMng(asgsysSrngVo);

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
	* 지도자 자격 및 배치 화면 이동
	*
	* @Title : ldrQlfcForm
	* @Description : 지도자 자격 및 배치 화면 이동
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/ldrQlfcForm.html")
	public String ldrQlfcForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

		BeanUtils.copyProperties(dsgnPrgrmVo, asgsysSrngVo);

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

		model.addAttribute("opner", dsgnPrgrmVo.getOpner());

		return "front/dsgnPrgrm/ldrQlfcForm";
	}

	/**
	* 지도자의 자격및 배치 등록
	*
	* @Title : insertLdrQlfcForm
	* @Description : 지도자의 자격및 배치 등록
	* @param dsgnPrgrmVo
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmLdrQlfcForm.do")
	@ResponseBody
	public Map<String, Object> insertLdrQlfcForm(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int retVal=0;

		asgsysSrngVo.setUser(user);


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
	* 안전관리 화면 이동
	*
	* @Title : sftyMngForm
	* @Description : 안전관리 화면 이동
	* @param asgsysSrngVo
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/sftyMngForm.html")
	public String sftyMngForm(AsgsysSrngVo asgsysSrngVo, Model model,@UserInfo UserVo user) throws Exception {
	    //신청정보
		AsgsysSrngVo aplyInfo = null;
		aplyInfo = asgsysSrngService.selectSftyMng(asgsysSrngVo);
		model.addAttribute("aplyInfo", aplyInfo);
		model.addAttribute("loginUserid", user.getUserid());

    	//안전관리 메뉴얼 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(aplyInfo.getFilegrpid());

            model.addAttribute("mnlFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

        } else {
            model.addAttribute("mnlFileList", Collections.emptyList());
        }

    	//사전인증 첨부파일
    	if (!StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals("") && !StringUtil.nvl(aplyInfo.getBfrCertFilegrpid()).equals(0)) {
    		FileVo fileVo = new FileVo();
    		fileVo.setFilegrpid(aplyInfo.getBfrCertFilegrpid());

    		model.addAttribute("bfrCertFileList", asgsysSrngService.selectEvdncDcmntFileList(fileVo));

    	} else {
    		model.addAttribute("bfrCertFileList", Collections.emptyList());
    	}

    	model.addAttribute("opner", asgsysSrngVo.getOpner());

		return "front/dsgnPrgrm/sftyMngForm";
	}

	/**
	* 안전관리 등록
	*
	* @Title : insertPrgrmSftyMngForm
	* @Description : 안전관리 등록
	* @param asgsysSrngVo
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmSftyMngForm.do"	)
	@ResponseBody
	public Map<String, Object> insertPrgrmSftyMngForm(AsgsysSrngVo asgsysSrngVo, @UserInfo UserVo user,HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    asgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));
	    asgsysSrngVo.setUser(user);

		int retVal=0;

		retVal+=asgsysSrngService.insertSftyMng(asgsysSrngVo);

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
	* 체크리스트 화면 이동
	*
	* @Title : chkListForm
	* @Description : 체크리스트 화면 이동
	* @param model
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/chkListForm.html")
	public String chkListForm(DsgnPrgrmVo dsgnPrgrmVo, Model model,@UserInfo UserVo user) throws Exception {

		DsgnPrgrmVo chkListInfo = null;
		dsgnPrgrmVo.setSbmsnSeCd("242101");    /*신청자 자가진단*/


		chkListInfo = dsgnPrgrmService.selectChkListInfo(dsgnPrgrmVo);
		dsgnPrgrmVo.setChklstid(chkListInfo.getChklstid());
		dsgnPrgrmVo.setSbmsnid(chkListInfo.getSbmsnid());

		model.addAttribute("loginUserid", user.getUserid());
		model.addAttribute("chkListInfo", chkListInfo);
		model.addAttribute("qitemList", dsgnPrgrmService.selectQitemList(dsgnPrgrmVo));
		model.addAttribute("ansList", dsgnPrgrmService.selectChkAnsList(dsgnPrgrmVo));

		model.addAttribute("opner", dsgnPrgrmVo.getOpner());

		return "front/dsgnPrgrm/chkListForm";
	}

	/**
	* 체크리스트 등록
	*
	* @Title : insertChkList
	* @Description : 체크리스트 등록
	* @param asgsysSrngVo
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/insertChkList.do")
    @ResponseBody
    public Map<String, Object> insertChkList(AsgsysSrngVo asgsysSrngVo,  @UserInfo UserVo user, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

     	asgsysSrngVo.setUserIp(CommonUtil.getClientIp(request));
        asgsysSrngVo.setUser(user);

        int retVal = 0;

        //체크리스트 등록
        retVal = asgsysSrngService.updateAssChklst(asgsysSrngVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }

        return resultMap;
    }

	@RequestMapping(value = "/front/dsgnPrgrm/aplyCmptnForm.html")
	public String aplyCmptnForm(Model model) throws Exception {
		return "front/dsgnPrgrm/aplyCmptnForm";
	}

	/**
	* 컨설팅 신청 상세 화면이동
	*
	* @Title : cnsltngAplyDetailForm
	* @Description : 컨설팅 신청 상세 화면이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/cnsltngAplyDetailForm.html")
	public String cnsltngAplyDetailForm(Model model, @UserInfo UserVo user) throws Exception {
		model.addAttribute("user", user);
		return "front/dsgnPrgrm/cnsltngAplyDetailForm";
	}

	/**
	 * 컨설팅 신청 약관동의 화면이동
	 *
	 * @Title : cnsltngAplyTermsForm
	 * @Description : 컨설팅 신청 약관동의화면 이동
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/dsgnPrgrm/cnsltngAplyTermsForm.html")
	public String cnsltngAplyTermsForm(Model model, @UserInfo UserVo user) throws Exception {
		return "front/dsgnPrgrm/cnsltngAplyTermsForm";
	}

    /**
    * 컨설팅 목록 조회
    *
    * @Title : selectCsltngList
    * @Description : 컨설팅 목록 조회
    * @param dsgnPrgrmVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnPrgrm/selectCsltngList.do")
    @ResponseBody
    public Map<String, Object> selectCsltngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
    	Map<String, Object> response = new HashMap<>();

    	List<DsgnPrgrmVo> list = dsgnPrgrmService.selectCsltngList(dsgnPrgrmVo);

    	response.put("list", list);
    	return response;
    }

	/**
	* 컨설팅 신청 화면이동
	*
	* @Title : cnsltngAplyForm
	* @Description : 컨설팅 신청 화면이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/cnsltngAplyForm.html")
	public String cnsltngAplyForm(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {
		DsgnPrgrmVo instInfo = new DsgnPrgrmVo();
		CnsltngVo cnsltngAply = new CnsltngVo();

		//로그인 사용자 정보
		dsgnPrgrmVo.setAplcntid(Integer.parseInt(user.getUserid()));

		//기관정보 조회
		instInfo = dsgnPrgrmService.selectInstInfo(dsgnPrgrmVo);

		//컨설팅 조회
		if(!CommonUtil.isEmpty(instInfo.getCnsltngid())) {
			cnsltngAply.setCnsltngid(instInfo.getCnsltngid());
			cnsltngAply = cnsltngService.selectCnsltngtInfo(cnsltngAply);
		}

        if(cnsltngAply.getFilegrpid() !=null && cnsltngAply.getFilegrpid() > 0) {
        	FileVo fileVo = new FileVo();
        	fileVo.setFilegrpid(Integer.parseInt(cnsltngAply.getFilegrpid().toString()));
        	ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
        	model.addAttribute("fileList", fileList);
        	model.addAttribute("fileListCnt", fileList.size());
        } else {
        	model.addAttribute("fileMap", null);
        	model.addAttribute("fileListCnt", 0);
        }
		model.addAttribute("instInfo", instInfo);
		model.addAttribute("cnsltngAply", cnsltngAply);

		return "front/dsgnPrgrm/cnsltngAplyForm";
	}

    /**
    * 컨설팅신청 등록
    *
    * @Title : insertCnsltngAplyForm
    * @Description : 컨설팅신청 등록
    * @param cnsltngVo
    * @param bindingResult1
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/dsgnPrgrm/insertCnsltngAplyForm.do")
    @ResponseBody
    public Map<String, Object> insertCnsltngAplyForm(DsgnPrgrmVo dsgnPrgrmVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        dsgnPrgrmVo.setUser(user);

        int retVal = 0;

        //컨설팅 등록
        retVal = dsgnPrgrmService.insertCsltng(dsgnPrgrmVo);

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
	*컨설팅 신청 완료
	*
	* @Title : cnsltngAplyComp
	* @Description : 컨설팅 신청 완료
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/cnsltngAplyComp.html")
	public String cnsltngAplyComp(Model model, @UserInfo UserVo user) throws Exception {
		return "front/dsgnPrgrm/cnsltngAplyComp";
	}

	/**
	* 신청내역 화면 이동
	*
	* @Title : aplyDsctnList
	* @Description : 신청내역 화면 이동
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return String
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/aplyDsctnList.html")
	public String aplyDsctnList(Model model, @UserInfo UserVo user) throws Exception {
		model.addAttribute("aplcntid", user.getUserid());
		return "front/dsgnPrgrm/aplyDsctnList";
	}

	/**
	* 신청내역 조회
	*
	* @Title : selectAplyDsctnList
	* @Description : 신청내역 조회
	* @Description : TODO
	* @param dsgnPrgrmVo
	* @param model
	* @param user
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/front/dsgnPrgrm/selectAplyDsctnList.do")
	@ResponseBody
	public Map<String, Object> selectAplyDsctnList(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {
		Map<String, Object> result = new HashMap<>();

        List<DsgnPrgrmVo> list = dsgnPrgrmService.selectAplyDsctnList(dsgnPrgrmVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());

            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);
		return result;
	}

    /**
    * 보완개선요청 팝업
    *
    * @Title : splmntDmndPopup
    * @Description : 보완개선요청 팝업
    * @param dsgnMngVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnPrgrm/splmntDmndPopup.html")
     public String splmntDmndPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {
    	 model.addAttribute("aplcntid", user.getUserid());
    	 model.addAttribute("splmntDmnd" , dsgnPrgrmService.selectSplmntDmnd(dsgnPrgrmVo));
    	 return "front/dsgnPrgrm/splmntDmndPopup";
     }

    /**
    * 보완개선계획 팝업
    *
    * @Title : imprvPlanPopup
    * @Description : 보완개선계획 팝업
    * @param dsgnPrgrmVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/dsgnPrgrm/imprvPlanPopup.html")
    public String imprvPlanPopup(DsgnPrgrmVo dsgnPrgrmVo, Model model, @UserInfo UserVo user) throws Exception {
    	model.addAttribute("aplcntid", user.getUserid());
    	//model.addAttribute("imprvPlan" , dsgnPrgrmService.selectImprvPlan(dsgnPrgrmVo));
    	return "front/dsgnPrgrm/imprvPlanPopup";
    }

}