package com.kbrainc.plum.mng.bizAply.bizRpt.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptVo;
import com.kbrainc.plum.mng.bizAply.bizRpt.service.BizRptServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
* 사업보고관리 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.controller
* - BizRptController.java
* </pre>
*
* @ClassName : BizRptController
* @Description : 사업보고관리 컨트롤러 클래스.
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
@Slf4j
public class BizRptController {

	@Autowired
    private BizRptServiceImpl bizRptService;

	@Autowired
	private FileServiceImpl fileService;

	/*******************************************************************************
	 중간보고관리
	********************************************************************************/
	/**
     * @Title : mdlRptMngList.html
     * @Description : 중간보고관리 메뉴 이동
     * @return String 이동화면경로
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bizAply/bizRpt/mdlRptMngListForm.html")
    public String userTempListForm() throws Exception {
        return "mng/bizAply/bizRpt/mdlRptMngList";
    }

    /**
    * 중간보고관리 목록조회
    *
    * @Title : selectMdlRptMngList
    * @Description : 중간보고관리 목록조회
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectMdlRptMngList.do")
	@ResponseBody
	public Map<String, Object> selectMdlRptMngList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectMdlRptMngList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

	/**
    * 중간보고관리 목록 엑셀다운로드
    *
    * @Title : aplyExcelDownList
    * @Description : 중간보고관리 목록 엑셀다운로드
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return void
    * @throws Exception
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/selectMdlRptMngListExcel.do")
    public void selectMdlRptMngListExcel(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo) throws Exception {
    	bizRptService.selectMdlRptMngListExcel(bizRptVo, response, request);
    }

	/**
	 * 중간보고관리 상세이동
	 *
	 * @Title : mdlRptMngDetailForm
	 * @Description : 중간보고관리 상세이동
	 * @param bizRptVo
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/mdlRptMngDetailForm.html")
	public String mdlRptMngDetailForm(BizRptVo bizRptVo, Model model) throws Exception {
		model.addAttribute("bizMngInfo", bizRptService.selectMdlRptMng(bizRptVo));
		return "mng/bizAply/bizRpt/mdlRptMngDetail";
	}

	/**
	 * 중간보고제출 목록조회
	 *
	 * @Title : selectMdlRptSbmsnList
	 * @Description : 중간보고제출 목록조회
	 * @param bizRptVo
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/selectMdlRptSbmsnList.do")
    @ResponseBody
	public Map<String, Object> selectMdlRptSbmsnList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectMdlRptSbmsnList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

    /**
    * 신청상태코드 수정
    *
    * @Title : updateRptSttsCd
    * @Description : 신청상태코드 수정
    * @param bizRptVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/updateRptSttsCd.do")
    @ResponseBody
    public Map<String, Object> updateRptSttsCd(HttpServletRequest request, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
        int ret = 0;
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 1 ");
        String[] reportids = request.getParameterValues("reportids");

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 2 : " + reportids[0].toString());

        ret = bizRptService.updateRptSttsCd(user, reportids);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 3 ");

        if (ret > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "제출 완료처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "제출 완료처리에 실패했습니다.");
        }

        return resultMap;
    }

	/**
	 * 중간보고제출 목록 엑셀다운로드
	 *
	 * @Title : aplyExcelDownList
	 * @Description : 중간보고제출 목록 엑셀다운로드
	 * @param request
	 * @param response
	 * @param asgsysSrngVo
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectMdlRptSbmsnListExcel.do")
	public void selectMdlRptSbmsnListExcel(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo) throws Exception {
		bizRptService.selectMdlRptSbmsnListExcel(bizRptVo, response, request);
	}

	/**
	 * 중간보고제출 상세이동
	 *
	 * @Title : mdlRptMngDetailForm
	 * @Description : 중간보고제출 상세이동
	 * @param bizRptVo
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/mdlRptSbmsnDetailForm.html")

	public String mdlRptSbmsnDetailForm(BizRptVo bizRptVo, Model model) throws Exception {

		BizRptVo mdlRptSbmsnInfo = new BizRptVo();
		mdlRptSbmsnInfo = bizRptService.selectMdlRptSbmsnDetail(bizRptVo);

		if (!StringUtil.nvl(mdlRptSbmsnInfo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(mdlRptSbmsnInfo.getAtchFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(mdlRptSbmsnInfo.getAtchFilegrpid());
            model.addAttribute("fileList", fileService.getFileList(fileVo));    // 증빙서류
        } else {
        	model.addAttribute("fileList", Collections.emptyList());
        }

		model.addAttribute("mdlRptSbmsnInfo", mdlRptSbmsnInfo);

		return "mng/bizAply/bizRpt/mdlRptSbmsnDetail";
    }

	/**
	 * 보완요청 목록조회
	 *
	 * @Title : selectSplmntDmndList
	 * @Description : 보완요청 목록조회
	 * @param bizRptVo
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/selectSplmntDmndList.do")
    @ResponseBody
	public Map<String, Object> selectSplmntDmndList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		// result = bizRptService.selectSplmntDmndList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

 	/**
 	* 컨설팅 대상 팝업
 	*
 	* @Title : cnsltngTrgtPopup
 	* @Description : 컨설팅 대상 팝업
 	* @param bizRptVo
 	* @param model
 	* @param user
 	* @return
 	* @throws Exception
 	* @return String
 	*/
 	@RequestMapping(value = "/mng/bizAply/bizRpt/cnsltngTrgtPopup.html")
    public String cnsltngTrgtPopup(BizRptVo bizRptVo, Model model, @UserInfo UserVo user) throws Exception {
        //model.addAttribute("user",user);
        return "mng/bizAply/bizRpt/cnsltngTrgtPopup";
    }

}
