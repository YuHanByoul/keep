package com.kbrainc.plum.front.dsgnPrgrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.front.dsgnPrgrm.service.DsgnPrgrmService;
import com.kbrainc.plum.mng.bizAply.bizRpt.controller.BizRptController;
import com.kbrainc.plum.mng.inst.service.InstServiceImpl;
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
	public String dsgnAplyForm(Model model) throws Exception {
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
	public String evdncDataForm(Model model, @UserInfo UserVo user) throws Exception {
		//기관정보 조회
		DsgnPrgrmVo dsgnPrgrmVo = new DsgnPrgrmVo();
		//dsgnPrgrmVo.setAplcntid(user.getUserId); todo
		dsgnPrgrmVo.setAplcntid(10000073);
		model.addAttribute("instInfo", dsgnPrgrmService.selectInstInfo(dsgnPrgrmVo));

		return "front/dsgnPrgrm/evdncDataForm";
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
	public String prgrmDstnctnForm(Model model) throws Exception {
		return "front/dsgnPrgrm/prgrmDstnctnForm";
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


	@RequestMapping(value = "/front/dsgnPrgrm/prgrmEvlForm.html")
	public String prgrmEvlForm(Model model) throws Exception {
		return "front/dsgnPrgrm/prgrmEvlForm";
	}

	@RequestMapping(value = "/front/dsgnPrgrm/ldrQlfcForm.html")
	public String ldrQlfcForm(Model model) throws Exception {
		return "front/dsgnPrgrm/ldrQlfcForm";
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