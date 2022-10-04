package com.kbrainc.plum.front.bbs.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.bbs.model.BbsClVo;
import com.kbrainc.plum.front.bbs.model.BbsVo;
import com.kbrainc.plum.front.bbs.model.CmntVo;
import com.kbrainc.plum.front.bbs.model.PstVo;
import com.kbrainc.plum.front.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import com.kbrainc.plum.sample.model.PagingVo.ORDER_DIRECTION;

/**
 * 
 * BBS Controller
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.controller - BbsController.java
 * </pre>
 *
 * @ClassName : BbsController
 * @Description : BBS Controller
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.bbsController")
@Alias("front.bbsController")
public class BbsController {

	@Resource(name = "front.bbsServiceImpl")
	private BbsServiceImpl bbsService;

	/**
	 * 
	 * bbsid에 따른 게시물 메인리스트.
	 *
	 * @Title : bbsPstForm
	 * @Description :
	 * @param bbsid   게시판 ID
	 * @param model
	 * @param paramVO
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/main.html")
	public String bbsPstForm(@PathVariable Integer bbsid, Model model, BbsVo paramVo) throws Exception {

		paramVo.setBbsid(bbsid);
		BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);
		model.addAttribute("bbsid", bbsid);
		model.addAttribute("bbsInfo", bbsInfo);

		return "front/bbs/bbsMain";
	}

	/**
	 * 
	 * bbsid에 따른 게시물 등록 화면.
	 *
	 * @Title : bbsPstInsertForm
	 * @Description :
	 * @param bbsid
	 * @param model
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/write.html")
	public String bbsPstInsertForm(@PathVariable Integer bbsid, Model model) throws Exception {

		BbsVo paramVo = new BbsVo(bbsid);
		BbsClVo bbsClVo = new BbsClVo(bbsid);

		model.addAttribute("clList", bbsService.selectBbsCl(bbsClVo));
		model.addAttribute("paramMap", bbsService.selectOneBbs(paramVo));

		return "front/bbs/bbsWrite";
	}

	/**
	 * 
	 * bbsid에 따른 게시물 읽기 처리.
	 *
	 * @Title : bbsRead
	 * @Description :
	 * @param bbsid
	 * @param model
	 * @param paramVo
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/modify.html")
	public String bbsRead(Model model, PstVo paramVo) throws Exception {
		CmntVo cmntVo = new CmntVo();
		BbsClVo bbsClVo = new BbsClVo();
		Map<String, Object> resultMap = new HashMap<>();

		// paramVo.setPstid(bbsid);
		cmntVo.setPstid(paramVo.getPstid());
		cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD ");
		cmntVo.setOrderDirection(cmntVo.getOrderDirection().asc);

		resultMap = bbsService.selectPst(paramVo);

		paramVo = (PstVo) resultMap.get("paramMap");

		if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
			bbsClVo.setBbsid(paramVo.getBbsid());
		}
		model.addAttribute("result", bbsService.selectPst(paramVo));
		model.addAttribute("cmntList", bbsService.selectCmntList(cmntVo));
		model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

		return "front/bbs/bbsRead";
	}

	/**
	 * 
	 * bbsid에 따른 게시물 수정 화면.
	 *
	 * @Title : bbsModify
	 * @Description :
	 * @param bbsid
	 * @param model
	 * @param paramVo
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/modify1.html")
	public String bbsModify(@PathVariable Integer bbsid, Model model, PstVo paramVo) throws Exception {
		CmntVo cmntVo = new CmntVo();
		BbsClVo bbsClVo = new BbsClVo();
		Map<String, Object> resultMap = new HashMap<>();

		cmntVo.setPstid(paramVo.getPstid());
		cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD ");
		cmntVo.setOrderDirection(CmntVo.ORDER_DIRECTION_ASC);

		resultMap = bbsService.selectPst(paramVo);

		paramVo = (PstVo) resultMap.get("paramMap");

		if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
			bbsClVo.setBbsid(paramVo.getBbsid());
		}
		model.addAttribute("result", bbsService.selectPst(paramVo));
		model.addAttribute("cmntList", bbsService.selectCmntList(cmntVo));
		model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

		return "front/bbs/bbsModify";
	}

	/**
	 * 
	 * bbsid에 따른 게시물 등록 처리.
	 *
	 * @Title : insertPst
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/insert.do")
	public @ResponseBody Map<String, String> insertPst(@PathVariable Integer bbsid, @Valid PstVo paramVo,
			@UserInfo UserVo user) throws Exception {

		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);

		try {
			if (bbsService.insertPst(paramVo) == 1) {
				resultMsg = Constant.REST_API_RESULT_SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 게시물 수정 처리.
	 *
	 * @Title : updatePst
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/update.do")
	public @ResponseBody Map<String, String> updatePst(@PathVariable Integer bbsid, PstVo paramVo,
			@UserInfo UserVo user) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);
		try {
			bbsService.updatePst(paramVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 댓글 추가 처리.
	 *
	 * @Title : insertCmnt
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/insertCmnt.do")
	public @ResponseBody Map<String, String> insertCmnt(@PathVariable Integer bbsid, CmntVo paramVo,
			@UserInfo UserVo user) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);
		try {
			bbsService.insertCmnt(paramVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 대댓글 추가 처리.
	 *
	 * @Title : insertCmntReply
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/insertCmntReply.do")
	public @ResponseBody Map<String, String> insertCmntReply(@PathVariable Integer bbsid, CmntVo paramVo,
			@UserInfo UserVo user) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);
		try {
			bbsService.insertReply(paramVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 댓글 수정 처리.
	 *
	 * @Title : updateCmnt
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/updateCmnt.do")
	public @ResponseBody Map<String, String> updateCmnt(@PathVariable Integer bbsid, CmntVo paramVo,
			@UserInfo UserVo user) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);
		try {
			bbsService.updateCmnt(paramVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 댓글 삭제 처리.
	 *
	 * @Title : updateCmntReplyDelYn
	 * @Description :
	 * @param bbsid
	 * @param paramVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/updateCmntReplyDelYn.do")
	public @ResponseBody Map<String, String> updateCmntReplyDelYn(@PathVariable Integer bbsid, CmntVo paramVo,
			@UserInfo UserVo user) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;
		paramVo.setUser(user);
		try {
			bbsService.updateCmntReplyDelYn(paramVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	/**
	 * 
	 * bbsid에 따른 게시물 삭제 처리
	 *
	 * @Title : deletePst
	 * @Description :
	 * @param bbsid
	 * @param pstVo
	 * @param user
	 * @throws Exception
	 * @return Map<String,String>
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/delete.do")
	public @ResponseBody Map<String, String> deletePst(@PathVariable Integer bbsid, PstVo pstVo, @UserInfo UserVo user)
			throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String resultMsg = Constant.REST_API_RESULT_FAIL;

		pstVo.setBbsid(bbsid);
		pstVo.setUser(user);
		try {
			bbsService.deletePst(pstVo);
			resultMsg = Constant.REST_API_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		resultMap.put("result", resultMsg);

		return resultMap;
	}

	
	/***************************************************************************************************/
	/***************************************************************************************************/
	/***************************************************************************************************/
	/**
	 * @Title : getPstList
	 * @Description : 게시글 목록 가져오기
	 * @param BbsVo
	 * @throws Exception
	 * @return
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/selectPstList.do")
	public @ResponseBody Map<String, Object> getPstList(@PathVariable Integer bbsid, PstVo paramVO, @UserInfo UserVo user) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<PstVo> result = new ArrayList<PstVo>();
		try {

			BbsVo paramVo = new BbsVo();
			paramVo.setBbsid(bbsid);
			BbsVo bbsVo = bbsService.selectOneBbs(paramVo);

			bbsVo.setSearchKeyword(paramVO.getSearchKeyword());
			bbsVo.setSearchType(paramVO.getSearchType());
			bbsVo.setRowPerPage(bbsVo.getPage_pst_cnt());
			bbsVo.setPageNumber(paramVO.getPageNumber());
			bbsVo.setUser(user);

			bbsVo.setOrderDirection(paramVO.getOrderDirection());
			bbsVo.setOrderField(paramVO.getOrderField());

			result = bbsService.selectTotalPstList(bbsVo);

			if (result.size() > 0) {
				resultMap.put("totalCount", (result.get(0).getTotalCount()));
				
				//PaginationUtil.getPagingHtml(총페이지 갯수,현재페이지번호,페이지번호 표출 갯수)
				resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
			} else {
				resultMap.put("totalCount", 0);
			}
			resultMap.put("list", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	/**
	 * 
	 * bbsid에 따른 게시물 읽기 처리.
	 *
	 * @Title : bbsRead
	 * @Description :
	 * @param bbsid
	 * @param model
	 * @param paramVo
	 * @throws Exception
	 * @return String
	 */
	@RequestMapping(value = "/front/bbs/{bbsid}/view.html")
	public String bbsView(@PathVariable Integer bbsid, Model model, PstVo paramVo) throws Exception {
		CmntVo cmntVo = new CmntVo();
		BbsClVo bbsClVo = new BbsClVo();
		Map<String, Object> resultMap = new HashMap<>();

		// paramVo.setPstid(bbsid);
		cmntVo.setPstid(paramVo.getPstid());
		cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD ");
		cmntVo.setOrderDirection(cmntVo.getOrderDirection().asc);

		resultMap = bbsService.selectPst(paramVo);

		paramVo = (PstVo) resultMap.get("paramMap");

		if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
			bbsClVo.setBbsid(paramVo.getBbsid());
		}
		model.addAttribute("result", bbsService.selectPst(paramVo));
		model.addAttribute("cmntList", bbsService.selectCmntList(cmntVo));
		model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

		return "front/bbs/bbsRead";
	}

}
