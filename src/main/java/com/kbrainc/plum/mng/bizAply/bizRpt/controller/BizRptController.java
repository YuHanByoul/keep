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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptVo;
import com.kbrainc.plum.mng.bizAply.bizRpt.service.BizRptServiceImpl;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestMngGrpVo;
import com.kbrainc.plum.mng.bizAply.srng.model.BizAplySrngVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

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
	 ******************************************************************************/
	/**
     * @Title : mdlRptMngList.html
     * @Description : 중간보고관리 메뉴 이동
     * @return String 이동화면경로
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bizAply/bizRpt/mdlRptMngListForm.html")
    public String mdlRptMngListForm(HttpServletRequest request) throws Exception {
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

		result = bizRptService.selectRptSbmsnList(bizRptVo);

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

        String[] reportids = request.getParameterValues("reportids");

        ret = bizRptService.updateRptSttsCd(user, reportids);

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
		//중간보고제출 상세 조회
		mdlRptSbmsnInfo = bizRptService.selectMdlRptSbmsnDetail(bizRptVo);

		//보고운영목록 조회
		model.addAttribute("reportOperList", bizRptService.selectReportOperList(bizRptVo));

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

		result = bizRptService.selectSplmntDmndList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

 	/**
 	 * 보완요청팝업
 	 *
 	 * @Title : rptSplmntDmndPopup
 	 * @Description : 보완요청팝업
 	 * @param bizRptVo
 	 * @param model
 	 * @param user
 	 * @return
 	 * @throws Exception
 	 * @return String
 	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/rptSplmntDmndPopup.html")
 	public String rptSplmntDmndPopup(BizRptVo bizRptVo, Model model) throws Exception {
 		model.addAttribute("mode", bizRptVo.getMode());
 		model.addAttribute("reportid", bizRptVo.getReportid());

 		//BizRptVo rptSplmntDmndDtlInfo = bizRptService.selectRptSplmntDmnd(bizRptVo);
 		//bizRptService.selectRptSplmntDmnd(bizRptVo);

 		//model.addAttribute("rptSplmntDmndDtlInfo", rptSplmntDmndDtlInfo);
 		return "mng/bizAply/bizRpt/rptSplmntDmndPopup";
 	}

 	/**
 	 * 보고보완 등록
 	 *
 	 * @Title : insertSplmnt
 	 * @Description : 보고보완 등록
 	 * @param bizRptVo
 	 * @param model
 	 * @param user
 	 * @return
 	 * @throws Exception
 	 * @return String
 	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/insertRptSplmnt.do")
    @ResponseBody
 	public Map<String, Object> insertSplmnt(BizRptVo bizRptVo, Model model,@UserInfo UserVo user) throws Exception {
 		Map<String, Object> resultMap = new HashMap<>();

 		int ret=0;
 		bizRptVo.setUser(user);
 		ret = bizRptService.insertRptSplmnt(bizRptVo);

 		if (ret > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "보완요청 처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "보완요청 처리에 실패했습니다.");
        }

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
        model.addAttribute("aplyid",bizRptVo.getAplyid());
        model.addAttribute("cnsltngTrgtCn",bizRptVo.getCnsltngTrgtCn());
        model.addAttribute("cnstntList", bizRptService.selectCnstntList(bizRptVo));
        return "mng/bizAply/bizRpt/cnsltngTrgtPopup";
    }

 	/**
 	 * 컨설팅 대상 저장
 	 *
 	 * @Title : insertCnsltngTrgt
 	 * @Description : 컨설팅 대상 저장
 	 * @param bizRptVo
 	 * @param model
 	 * @param user
 	 * @return
 	 * @throws Exception
 	 * @return String
 	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/insertCnsltngTrgt.do")
    @ResponseBody
 	public Map<String, Object> insertCnsltngTrgt(BizRptVo bizRptVo, @UserInfo UserVo user) throws Exception {
 		Map<String, Object> resultMap = new HashMap<>();

 		int ret=0;
 		bizRptVo.setUser(user);
 		ret = bizRptService.insertCnsltngTrgt(bizRptVo);

 		if (ret > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "컨설팅 대상 처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "컨설팅 대상 처리에 실패했습니다.");
        }

 		return resultMap;
 	}

 	/**
 	 * 컨설팅 대상 삭제
 	 *
 	 * @Title : deleteCnsltngTrgt
 	 * @Description : 컨설팅 대상 삭제
 	 * @param bizRptVo
 	 * @param model
 	 * @param user
 	 * @return
 	 * @throws Exception
 	 * @return String
 	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/deleteCnsltngTrgt.do")
 	@ResponseBody
 	public Map<String, Object> deleteCnsltngTrgt(BizRptVo bizRptVo, Model model,@UserInfo UserVo user) throws Exception {
 		Map<String, Object> resultMap = new HashMap<>();

 		int ret=0;
 		bizRptVo.setUser(user);
 		//ret = bizRptService.deleteCnsltngTrgt(bizRptVo);

 		if (ret > 0) {
 			resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
 			resultMap.put("msg", "컨설팅 대상 취소 처리에 성공하였습니다.");
 		} else {
 			resultMap.put("result", Constant.REST_API_RESULT_FAIL);
 			resultMap.put("msg", "컨설팅 대상 취소 처리에 실패했습니다.");
 		}

 		return resultMap;
 	}

 	/**
 	 * 컨설팅 담당자 팝업
 	 *
 	 * @Title : cnsltngPicPopup
 	 * @Description : 컨설팅 담당자 팝업
 	 * @param bizRptVo
 	 * @param model
 	 * @param user
 	 * @return
 	 * @throws Exception
 	 * @return String
 	 */
 	@RequestMapping(value = "/mng/bizAply/bizRpt/cnsltngPicPopup.html")
 	public String cnsltngPicPopup(BizRptVo bizRptVo, Model model, @UserInfo UserVo user) throws Exception {
 		return "mng/bizAply/bizRpt/cnsltngPicPopup";
 	}



    /**
    * 컨설팅 담당자 목록 조회
    *
    * @Title : cnsltngPicList
    * @Description : 컨설팅 담당자 목록 조회
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
	@RequestMapping(value = "/mng/bizAply/bizRpt/cnsltngPicList.do")
	@ResponseBody
	public Map<String, Object> cnsltngPicList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
        List<BizRptVo> result = bizRptService.selectMngGrpList(bizRptVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
	}

	/*******************************************************************************
	 * 결과보고관리
	 ******************************************************************************/
	/**
	 * @Title : rsltRptMngList.html
	 * @Description : 결과보고관리 메뉴 이동
	 * @return String 이동화면경로
	 * @throws Exception :
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/rsltRptMngListForm.html")
	public String rsltRptMngListForm(HttpServletRequest request) throws Exception {
		return "mng/bizAply/bizRpt/rsltRptMngList";
	}

	/**
	 * 결과보고관리 목록조회
	 *
	 * @Title : selectRsltRptMngList
	 * @Description : 결과보고관리 목록조회
	 * @param bizRptVo
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectRsltRptMngList.do")
	@ResponseBody
	public Map<String, Object> selectRsltRptMngList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectRsltRptMngList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

	/**
	 * 결과보고관리 목록 엑셀다운로드
	 *
	 * @Title : aplyExcelDownList
	 * @Description : 결과보고관리 목록 엑셀다운로드
	 * @param request
	 * @param response
	 * @param asgsysSrngVo
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectRsltRptMngListExcel.do")
	public void selectRsltRptMngListExcel(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo)
			throws Exception {
//		bizRptService.selectRsltRptMngListExcel(bizRptVo, response, request);
	}

	/**
	 * 결과보고관리 상세이동
	 *
	 * @Title : rsltRptMngDetailForm
	 * @Description : 결과보고관리 상세이동
	 * @param bizRptVo
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/rsltRptMngDetailForm.html")
	public String rsltRptMngDetailForm(BizRptVo bizRptVo, Model model) throws Exception {
		model.addAttribute("bizMngInfo", bizRptService.selectRsltRptMng(bizRptVo));
		return "mng/bizAply/bizRpt/rsltRptMngDetail";
	}

	/**
	 * 결과보고제출 목록조회
	 *
	 * @Title : selectRsltRptSbmsnList
	 * @Description : 결과보고제출 목록조회
	 * @param bizRptVo
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectRsltRptSbmsnList.do")
	@ResponseBody
	public Map<String, Object> selectRsltRptSbmsnList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectRptSbmsnList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

	/**
	 * 결과보고제출 목록 엑셀다운로드
	 *
	 * @Title : aplyExcelDownList
	 * @Description : 결과보고제출 목록 엑셀다운로드
	 * @param request
	 * @param response
	 * @param asgsysSrngVo
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectRsltRptSbmsnListExcel.do")
	public void selectRsltRptSbmsnListExcel(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo)
			throws Exception {
		// bizRptService.selectRsltRptSbmsnListExcel(bizRptVo, response, request);
	}

	/**
	 * 결과보고제출 상세이동
	 *
	 * @Title : rsltRptMngDetailForm
	 * @Description : 결과보고제출 상세이동
	 * @param bizRptVo
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/mng/bizAply/bizRpt/rsltRptSbmsnDetailForm.html")

	public String rsltRptSbmsnDetailForm(BizRptVo bizRptVo, Model model) throws Exception {

		BizRptVo rsltRptSbmsnInfo = new BizRptVo();
		// 결과보고제출 상세 조회
		 rsltRptSbmsnInfo = bizRptService.selectMdlRptSbmsnDetail(bizRptVo);

		// 보고운영목록 조회
		model.addAttribute("reportOperList", bizRptService.selectReportOperList(bizRptVo));

		if (!StringUtil.nvl(rsltRptSbmsnInfo.getAtchFilegrpid()).equals("")
				&& !StringUtil.nvl(rsltRptSbmsnInfo.getAtchFilegrpid()).equals(0)) {
			FileVo fileVo = new FileVo();
			fileVo.setFilegrpid(rsltRptSbmsnInfo.getAtchFilegrpid());
			model.addAttribute("fileList", fileService.getFileList(fileVo)); // 증빙서류
		} else {
			model.addAttribute("fileList", Collections.emptyList());
		}

		model.addAttribute("rsltRptSbmsnInfo", rsltRptSbmsnInfo);

		return "mng/bizAply/bizRpt/rsltRptSbmsnDetail";
	}

	/*******************************************************************************
	 * 컨설팅 관리
	 ******************************************************************************/

    /**
    * 컨설팅관리 메뉴 이동
    *
    * @Title : cnsltngMngList
    * @Description : 컨설팅관리 메뉴 이동
    * @param request
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/cnsltngMngList.html")
    public String cnsltngMngList(Model model, @UserInfo UserVo user) throws Exception {
    	List<BizRptVo> result = bizRptService.selectCnsltngExprtList();
    	model.addAttribute("list", result == null ? new BizRptVo() : result);
    	model.addAttribute("searchUserid", user.getUserid());

        return "mng/bizAply/bizRpt/cnsltngMngList";
    }

	/**
	* 컨설팅관리 목록조회
	*
	* @Title : selectCnsltngMngList
	* @Description : 컨설팅관리 목록조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectCnsltngMngList.do")
	@ResponseBody
	public Map<String, Object> selectCnsltngMngList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectCnsltngMngList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

	/**
    * 컨설팅관리 목록 엑셀다운로드
    *
    * @Title : selectCnsltngMngExcelList
    * @Description : 컨설팅관리 목록 엑셀다운로드
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return void
    * @throws Exception
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/selectCnsltngMngExcelList.do")
    public void selectCnsltngMngExcelList(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo) throws Exception {
    	bizRptService.selectCnsltngMngExcelList(bizRptVo, response, request);
    }

    /**
     * 컨설팅관리 상세이동
     *
     * @Title : cnsltngMngDetailForm
     * @Description : 컨설팅관리 상세이동
     * @param request
     * @return
     * @throws Exception
     * @return String
     */
     @RequestMapping(value = "/mng/bizAply/bizRpt/cnsltngMngDetailForm.html")
     public String cnsltngMngDetailForm(BizRptVo bizRptVo, Model model) throws Exception {
    	 BizRptVo cnsltngDtlInfo = new BizRptVo();
    	 cnsltngDtlInfo = bizRptService.selectCnsltngMng(bizRptVo);

    	 if (!StringUtil.nvl(cnsltngDtlInfo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(cnsltngDtlInfo.getAtchFilegrpid()).equals(0)) {
             FileVo fileVo = new FileVo();
             fileVo.setFilegrpid(cnsltngDtlInfo.getAtchFilegrpid());
             model.addAttribute("fileList", fileService.getFileList(fileVo));    // 증빙서류
         } else {
         	model.addAttribute("fileList", Collections.emptyList());
         }

    	 model.addAttribute("cnsltngDtlInfo", bizRptService.selectCnsltngMng(bizRptVo));

         return "mng/bizAply/bizRpt/cnsltngMngDetail";
     }

  	/**
  	* 컨설팅관리 저장
  	*
  	* @Title : insertCnsltngMng
  	* @Description : 컨설팅관리 저장
  	* @param bizRptVo
  	* @param model
  	* @param user
  	* @return
  	* @throws Exception
  	* @return Map<String,Object>
  	*/
  	@RequestMapping(value = "/mng/bizAply/bizRpt/insertCnsltngMng.do")
    @ResponseBody
 	public Map<String, Object> insertCnsltngMng(BizRptVo bizRptVo, Model model,@UserInfo UserVo user) throws Exception {
 		Map<String, Object> resultMap = new HashMap<>();

 		int ret = 0;
 		bizRptVo.setUser(user);
 		ret = bizRptService.insertCnsltngMng(bizRptVo);

 		if (ret > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "컨설팅관리 저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "컨설팅관리 저장에 실패했습니다.");
        }

 		return resultMap;
 	}

  	/*******************************************************************************
	 사업포기관리
	 ******************************************************************************/
  	/**
  	* 사업포기관리 메뉴 이동
  	*
  	* @Title : bizAbndMngList
  	* @Description : 사업포기관리 메뉴 이동
  	* @param request
  	* @return
  	* @throws Exception
  	* @return String
  	*/
  	@RequestMapping(value = "/mng/bizAply/bizRpt/bizAbndMngList.html")
  	public String bizAbndMngList() throws Exception {
        return "mng/bizAply/bizRpt/bizAbndMngList";
    }

	/**
	* 사업포기관리 목록 조회
	*
	* @Title : selectBizAbndMngList
	* @Description : 사업포기관리 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return Map<String,Object>
	*/
	@RequestMapping(value = "/mng/bizAply/bizRpt/selectBizAbndMngList.do")
	@ResponseBody
	public Map<String, Object> selectBizAbndMngList(BizRptVo bizRptVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		List<BizRptVo> result = null;

		result = bizRptService.selectBizAbndMngList(bizRptVo);

		if (result.size() > 0) {
			resultMap.put("totalCount", (result.get(0).getTotalCount()));
		} else {
			resultMap.put("totalCount", 0);
		}
		resultMap.put("list", result);

		return resultMap;
	}

	/**
    * 컨설팅관리 목록 엑셀다운로드
    *
    * @Title : selectBizAbndMngExcelList
    * @Description : 컨설팅관리 목록 엑셀다운로드
    * @param request
    * @param response
    * @param asgsysSrngVo
    * @return void
    * @throws Exception
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/selectBizAbndMngExcelList.do")
    public void selectBizAbndMngExcelList(HttpServletRequest request, HttpServletResponse response, BizRptVo bizRptVo) throws Exception {
    	bizRptService.selectBizAbndMngExcelList(bizRptVo, response, request);
    }

	/**
  	* 사업포기관리 상세
  	*
  	* @Title : bizAbndMngForm
  	* @Description : 사업포기관리 상세 이동
  	* @param request
  	* @return
  	* @throws Exception
  	* @return String
  	*/
  	@RequestMapping(value = "/mng/bizAply/bizRpt/bizAbndMngDetailForm.html")
  	public String bizAbndMngDetailForm(BizRptVo bizRptVo, Model model) throws Exception {
  		model.addAttribute("bizAbndMngInfo", bizRptService.selectBizAbndMng(bizRptVo));
        return "mng/bizAply/bizRpt/bizAbndMngDetail";
    }



    /**
    * 사업포기 수정
    *
    * @Title : updateBizAbnd
    * @Description : 사업포기 수정
    * @param bizRptVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/bizAply/bizRpt/updateBizAbnd.do")
    @ResponseBody
    public Map<String, Object> updateBizAbnd(BizRptVo bizRptVo, @UserInfo UserVo user) throws Exception {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
        int ret = 0;

        bizRptVo.setUser(user);
        ret = bizRptService.updateBizAbnd(bizRptVo);

        if (ret > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "포기승인 처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "포기승인 처리에 실패했습니다.");
        }

        return resultMap;
    }

}