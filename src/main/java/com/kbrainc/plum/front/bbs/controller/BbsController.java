package com.kbrainc.plum.front.bbs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.bbs.model.BbsClVo;
import com.kbrainc.plum.front.bbs.model.BbsVo;
import com.kbrainc.plum.front.bbs.model.CmntVo;
import com.kbrainc.plum.front.bbs.model.PstVo;
import com.kbrainc.plum.front.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * BBS Controller
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.controller - BbsController.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : BbsController
 * @Description : BBS Controller
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.bbsController")
@Alias("front.bbsController")
@Slf4j
public class BbsController {

    @Resource(name = "front.bbsServiceImpl")
    private BbsServiceImpl bbsService;

    /**
     * bbsid에 따른 게시물 메인리스트.
     *
     * @param bbsid   게시판 ID
     * @param model
     * @param paramVO
     * @return String
     * @throws Exception
     * @Title : bbsPstForm
     * @Description :
     */
    @RequestMapping(value = "/front/bbs/{bbsid}/main.html")
    public String bbsPstForm(@PathVariable Integer bbsid, Model model, BbsVo paramVo, @UserInfo UserVo user, @SiteInfo SiteInfoVo site) throws Exception {

        paramVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);
        String returnUrl = "front/bbs/bbsMain";

        BbsClVo bbsClVo = new BbsClVo();
        if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(paramVo.getBbsid());
        }
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

        if (bbsInfo.getBbsid().equals(1)) {
            /* 공지사항인 경우 submit 페이징 방식으로 처리 */
            paramVo.setUser(user);
            paramVo.setSite(site);
            paramVo.setHotUseStdrHits(bbsInfo.getHotUseStdrHits());
            paramVo.setHotUseYn(bbsInfo.getHotUseYn());
            paramVo.setNewUseYn(bbsInfo.getNewUseYn());
            paramVo.setNewIndictDaycnt(bbsInfo.getNewIndictDaycnt());

            if (paramVo.getTabType().equals(2)) {
                paramVo.setOrderField("GRP DESC,ORD");
            } else {
                paramVo.setOrderField("FIXORDER ASC,GRP DESC,ORD");
            }

            List<PstVo> list = bbsService.selectAllPstList(paramVo);
            model.addAttribute("list", list);
            model.addAttribute("totalCount", list.size() > 0 ? list.get(0).getTotalCount() : 0);
            returnUrl = "front/bbs/noticeView";
        }

        model.addAttribute("bbsid", bbsid);
        model.addAttribute("BbsVo", paramVo);
        model.addAttribute("bbsInfo", bbsInfo);

        return returnUrl;
    }


    /**
     * bbsid에 따른 게시물 읽기 처리.
     *
     * @param bbsid
     * @param model
     * @param paramVo
     * @return String
     * @throws Exception
     * @Title : bbsRead
     * @Description :
     */
    @RequestMapping(value = "/front/bbs/{bbsid}/view.html")
    public String bbsView(@PathVariable Integer bbsid, Model model, PstVo paramVo, @UserInfo UserVo user, @SiteInfo SiteInfoVo site) throws Exception {
        CmntVo cmntVo = new CmntVo();
        BbsClVo bbsClVo = new BbsClVo();
        Map<String, Object> resultMap = new HashMap<>();

        paramVo.setUser(user);
        paramVo.setSite(site);
        paramVo.setOrderField("FIXORDER ASC,GRP DESC,ORD");

        model.addAttribute("PstVo", paramVo);

        //조회수 증가 
        bbsService.updatePstHitsCount(paramVo);

        BbsVo bbsVo = new BbsVo();
        bbsVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(bbsVo);

        resultMap = bbsService.selectPst(paramVo);

//        paramVo = (PstVo) resultMap.get("paramMap");
//        Map result = bbsService.selectPst(paramVo);
//		  paramVo.setPrntsPstid(paramVo.getPstid());
//        model.addAttribute("replyList", bbsService.selectReplyPstList(paramVo));

        model.addAttribute("bbsInfo", bbsInfo);
        model.addAttribute("pstInfo", resultMap.get("paramMap"));
        model.addAttribute("fileMap", resultMap.get("fileMap"));
        model.addAttribute("currentFileCnt", resultMap.get("currentFileCnt"));

        return "front/bbs/bbsView";
    }

    /**
     * 게시물 상세 댓글 리스트 호출
     *
     * @param bbsid
     * @param model
     * @param paramVo
     * @return String
     * @throws Exception
     * @Title : bbsRead
     * @Description :
     */
    @ResponseBody
    @RequestMapping(value = "/front/bbs/{bbsid}/selectCmntList.do")
    public Map<String, Object> selectCmntList(@PathVariable Integer bbsid, Model model, PstVo paramVo, @UserInfo UserVo user) throws Exception {

        CmntVo cmntVo = new CmntVo();
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntVo> result = new ArrayList<CmntVo>();

        cmntVo.setUser(user);
        cmntVo.setPstid(paramVo.getPstid());
        cmntVo.setPageNumber(paramVo.getPageNumber());
        cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD ");
        cmntVo.setOrderField("CMNT_GRP DESC, ORD ");
        cmntVo.setOrderDirection(cmntVo.getOrderDirection().asc);

        result = bbsService.selectCmntList(cmntVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            //PaginationUtil.getPagingHtml(총페이지 갯수,현재페이지번호,페이지번호 표출 갯수)
            resultMap.put("pagination", PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);


        return resultMap;
    }

    /**
     * bbsid에 따른 게시물 등록 화면.
     *
     * @param bbsid
     * @param model
     * @return String
     * @throws Exception
     * @Title : bbsPstInsertForm
     * @Description :
     */
    @RequestMapping(value = "/front/bbs/{bbsid}/write.html")
    public String bbsPstInsertForm(@PathVariable Integer bbsid, Model model) throws Exception {

        BbsVo paramVo = new BbsVo(bbsid);

        paramVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);
        model.addAttribute("bbsid", bbsid);
        model.addAttribute("BbsVo", paramVo);
        model.addAttribute("bbsInfo", bbsInfo);

        BbsClVo bbsClVo = new BbsClVo();
        if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(paramVo.getBbsid());
        }
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

        return "front/bbs/bbsWrite";
    }

    /**
     * bbsid에 따른 게시물 등록 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : insertPst
     * @Description :
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
        } catch (SQLException e) {
            log.error("insertPst.SQLException.233L");
        } catch (Exception e) {
            log.error("insertPst.Exception.233L");
        }
        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 게시물 수정 화면 이동.
     *
     * @param bbsid
     * @param model
     * @param paramVo
     * @return String
     * @throws Exception
     * @Title : bbsRead
     * @Description :
     */
    @RequestMapping(value = "/front/bbs/{bbsid}/modify.html")
    public String bbsRead(@PathVariable Integer bbsid, Model model, PstVo paramVo, @UserInfo UserVo user) throws Exception {

        CmntVo cmntVo = new CmntVo();
        BbsClVo bbsClVo = new BbsClVo();
        Map<String, Object> resultMap = new HashMap<>();

        paramVo.setUser(user);
        model.addAttribute("PstVo", paramVo);

        if (!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(paramVo.getBbsid());
        }
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

        BbsVo bbsVo = new BbsVo();
        bbsVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(bbsVo);

        resultMap = bbsService.selectPst(paramVo);

        model.addAttribute("bbsInfo", bbsInfo);
        model.addAttribute("pstInfo", resultMap.get("paramMap"));
        model.addAttribute("fileMap", resultMap.get("fileMap"));
        model.addAttribute("currentFileCnt", resultMap.get("currentFileCnt"));

        return "front/bbs/bbsModify";
    }

    /**
     * bbsid에 따른 게시물 수정 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : updatePst
     * @Description :
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
        } catch (SQLException e) {
            log.error("updatePst.SQLException.303L");
        } catch (Exception e) {
            log.error("updatePst.Exception.303L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 댓글 추가 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : insertCmnt
     * @Description :
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
        } catch (SQLException e) {
            log.error("insertCmnt.SQLException.330L");
        } catch (Exception e) {
            log.error("insertCmnt.Exception.330L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 대댓글 추가 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : insertCmntReply
     * @Description :
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
        } catch (SQLException e) {
            log.error("insertCmntReply.SQLException.363L");
        } catch (Exception e) {
            log.error("insertCmntReply.Exception.363L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 댓글 수정 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : updateCmnt
     * @Description :
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
        } catch (SQLException e) {
            log.error("updateCmnt.SQLException.393L");
        } catch (Exception e) {
            log.error("updateCmnt.Exception.393L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 댓글 삭제 처리.
     *
     * @param bbsid
     * @param paramVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : updateCmntReplyDelYn
     * @Description :
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
        } catch (SQLException e) {
            log.error("updateCmntReplyDelYn.SQLException.423L");
        } catch (Exception e) {
            log.error("updateCmntReplyDelYn.Exception.423L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 게시물 삭제 처리
     *
     * @param bbsid
     * @param pstVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : deletePst
     * @Description :
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
        } catch (SQLException e) {
            log.error("deletePst.SQLException.455L");
        } catch (Exception e) {
            log.error("deletePst.Exception.455L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }


    /***************************************************************************************************/
    /***************************************************************************************************/
    /***************************************************************************************************/
    /**
     * @param BbsVo
     * @return
     * @throws Exception
     * @Title : getPstList
     * @Description : 게시글 목록 가져오기
     */
    @RequestMapping(value = "/front/bbs/{bbsid}/selectPstList.do")
    public @ResponseBody Map<String, Object> getPstList(@PathVariable Integer bbsid, PstVo paramVO, @UserInfo UserVo user, @SiteInfo SiteInfoVo site) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PstVo> result = new ArrayList<PstVo>();
        try {

            BbsVo paramVo = new BbsVo();
            paramVo.setBbsid(bbsid);
            BbsVo bbsVo = bbsService.selectOneBbs(paramVo);

            bbsVo.setBbsClid(paramVO.getBbsClid());
            bbsVo.setSearchKeyword(paramVO.getSearchKeyword());
            bbsVo.setSearchType(paramVO.getSearchType());
            bbsVo.setRowPerPage(paramVO.getRowPerPage());
            bbsVo.setPageNumber(paramVO.getPageNumber());
            bbsVo.setUser(user);
            bbsVo.setSite(site);
            bbsVo.setOrderDirection(paramVO.getOrderDirection());
            bbsVo.setOrderField("FIXORDER ASC,GRP DESC,ORD");
            result = bbsService.selectAllPstList(bbsVo);

            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
                resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
            } else {
                resultMap.put("totalCount", 0);
            }
            resultMap.put("list", result);

        } catch (SQLException e) {
            log.error("getPstList.SQLException.507L");
        } catch (Exception e) {
            log.error("getPstList.Exception.507L");
        }
        return resultMap;
    }

}
