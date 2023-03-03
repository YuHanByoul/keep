package com.kbrainc.plum.front.dsgnPrgrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.kbrainc.plum.mng.asgsysSrng.service.AsgsysSrngServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
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
	public String dsgnAplyForm(Model model, @UserInfo UserVo user) throws Exception {
		model.addAttribute("user", user);
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
	public String trmsAgreForm(Model model) throws Exception {
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
        	model.addAttribute("fileMap", null);
        	model.addAttribute("fileListCnt", 0);
        }

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
    	retVal = dsgnPrgrmService.updatePrgrmAssPrgrm(dsgnPrgrmVo);

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
	public String prgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo, Model model) throws Exception {

		//신청정보
		model.addAttribute("aplyInfo", dsgnPrgrmService.selectAplyInfo(dsgnPrgrmVo));

		//프로그램 일정 목록
		List<DsgnPrgrmVo> schdlList = dsgnPrgrmService.selectPrgrmSchdlList(dsgnPrgrmVo);

		//프로그램 대처 계획
    	List<DsgnPrgrmVo> planList  = dsgnPrgrmService.selectPlanList(dsgnPrgrmVo);

		model.addAttribute("schdlList", schdlList);
		model.addAttribute("planList", planList );

		return "front/dsgnPrgrm/prgrmDstnctnForm";
	}


	/**
	* 프로그램 우수성 등록
	*
	* @Title : insertPrgrmDstnctnForm
	* @Description : TODO
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

		asgsysSrngService.insertPrgrmDstnctn(asgsysSrngVo);

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

		retVal=+asgsysSrngService.updatePrgrmDstnctn(asgsysSrngVo);

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

		retVal=+asgsysSrngService.updatePrgrmEvl(asgsysSrngVo);

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
	public String prgrmOperMngForm(Model model) throws Exception {
		return "front/dsgnPrgrm/prgrmOperMngForm";
	}

//	@RequestMapping(value = "/front/dsgnPrgrm/insertPrgrmDstnctn.do")
//	public iprgrmDstnctn(Model model) throws Exception {
//		return "front/dsgnPrgrm/prgrmDstnctn";
//	}

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


		retVal=+asgsysSrngService.insertLdrQlfcForm(asgsysSrngVo);

		if (retVal > 0) {
			resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
			resultMap.put("msg", "저장에 성공하였습니다.");
		} else {
			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
			resultMap.put("msg", "저장에 실패했습니다.");
		}
		return resultMap;
	}

	@RequestMapping(value = "/front/dsgnPrgrm/sftyMngForm.html")
	public String sftyMngForm(Model model) throws Exception {
		return "front/dsgnPrgrm/sftyMngForm";
	}

	@RequestMapping(value = "/front/dsgnPrgrm/chkListForm.html")
	public String chkListForm(Model model) throws Exception {
		return "front/dsgnPrgrm/chkListForm";
	}

	@RequestMapping(value = "/front/dsgnPrgrm/aplyCmptnForm.html")
	public String aplyCmptnForm(Model model) throws Exception {
		return "front/dsgnPrgrm/aplyCmptnForm";
	}

}