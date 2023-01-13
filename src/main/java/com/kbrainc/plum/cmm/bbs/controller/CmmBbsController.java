package com.kbrainc.plum.cmm.bbs.controller;

import com.kbrainc.plum.mng.bbs.model.BbsClVo;
import com.kbrainc.plum.mng.bbs.model.BbsVo;
import com.kbrainc.plum.mng.bbs.model.CmntVo;
import com.kbrainc.plum.mng.bbs.model.PstVo;
import com.kbrainc.plum.mng.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 생성된 게시판에 대한 사용 예시.
 *
 * <pre>
 * com.kbrainc.plum.cmm.bbs.controller
 * - CmmBbsController.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : CmmBbsController
 * @Description :
 * @date : 2021. 3. 25.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class CmmBbsController {

    @Autowired
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/main.html")
    public String bbsPstForm(@PathVariable Integer bbsid, Model model, BbsVo paramVo) throws Exception {

        paramVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);
        model.addAttribute("bbsid", bbsid);
        model.addAttribute("bbsNm", bbsInfo.getNm());

        return "cmm/bbs/bbsMain";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/write.html")
    public String bbsPstInsertForm(@PathVariable Integer bbsid, Model model) throws Exception {

        BbsVo paramVo = new BbsVo(bbsid);
        BbsClVo bbsClVo = new BbsClVo(bbsid);
        bbsClVo.setUseYn("Y");


        model.addAttribute("clList", bbsService.selectBbsCl(bbsClVo));
        paramVo.setSearchKeyword("All");
        model.addAttribute("paramMap", bbsService.selectOneBbs(paramVo));

        return "cmm/bbs/bbsWrite";
    }

    /**
     * bbsid에 따른 게시물 수정 화면.
     *
     * @param bbsid
     * @param model
     * @param paramVo
     * @return String
     * @throws Exception
     * @Title : bbsModify
     * @Description :
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/modify.html")
    public String bbsModify(@PathVariable Integer bbsid, Model model, PstVo paramVo, @UserInfo UserVo user) throws Exception {
        BbsClVo bbsClVo = new BbsClVo();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap = bbsService.selectPst(paramVo);

        bbsService.updatePstHitsCount(paramVo);

        PstVo tmpParamVo = (PstVo) resultMap.get("paramMap");

        if (!StringUtil.nvl(tmpParamVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(tmpParamVo.getBbsid());
            bbsClVo.setUseYn("Y");
        }

        BbsVo bbsVo = new BbsVo();
        bbsVo.setUser(user);
        bbsVo.setBbsid(tmpParamVo.getBbsid());
        model.addAttribute("bbsInfo", bbsService.selectOneBbs(bbsVo));

        tmpParamVo.setPrntsPstid(paramVo.getPstid());

        model.addAttribute("result", bbsService.selectPst(tmpParamVo));
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

        return "cmm/bbs/bbsModify";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/insert.do")
    public @ResponseBody Map<String, String> insertPst(@PathVariable Integer bbsid, @Valid PstVo paramVo, @UserInfo UserVo user) throws Exception {

        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        Integer resVal = 0;

        paramVo.setUser(user);

        try {
            if (!CommonUtil.isEmpty(paramVo.getRplyYn()) && paramVo.getRplyYn().equals("Y")) {
                resVal = bbsService.insertReplyPst(paramVo);
            } else {
                resVal = bbsService.insertPst(paramVo);
            }
            if (resVal > 0) {
                resultMsg = Constant.REST_API_RESULT_SUCCESS;
            }
        } catch (SQLException e) {
            log.error("insertPst.SQLException.196L");
        } catch (Exception e) {
            log.error("insertPst.Exception.196L");
        }
        resultMap.put("result", resultMsg);

        return resultMap;
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/update.do")
    public @ResponseBody Map<String, String> updatePst(@PathVariable Integer bbsid, PstVo paramVo, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVo.setUser(user);
        try {
            bbsService.updatePst(paramVo);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
        } catch (SQLException e) {
            log.error("updatePst.SQLException.224L");
        } catch (Exception e) {
            log.error("updatePst.Exception.224L");
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/updateCmntReplyDelYn.do")
    public @ResponseBody Map<String, String> updateCmntReplyDelYn(@PathVariable Integer bbsid, CmntVo paramVo, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVo.setUser(user);
        try {
            bbsService.updateCmntReplyDelYn(paramVo);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
        } catch (SQLException e) {
            log.error("updateCmntReplyDelYn.SQLException.340L");
        } catch (Exception e) {
            log.error("updateCmntReplyDelYn.Exception.340L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 게시물 삭제 처리.
     *
     * @param bbsid
     * @param pstVo
     * @param user
     * @return Map<String, String>
     * @throws Exception
     * @Title : deletePst
     * @Description :
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/delete.do")
    public @ResponseBody Map<String, String> deletePst(@PathVariable Integer bbsid, PstVo pstVo, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;

        pstVo.setBbsid(bbsid);
        pstVo.setUser(user);
        try {
            bbsService.deletePst(pstVo);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
        } catch (SQLException e) {
            log.error("deletePst.SQLException.371L");
        } catch (Exception e) {
            log.error("deletePst.Exception.371L");
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * bbsid에 따른 답글 등록창 화면 이동
     *
     * @param PstVo
     * @return String 이동화면경로
     * @throws Exception
     * @Title : bbsPstRplyInsertForm
     * @Description : 게시물 답글 등록창 화면 이동
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/bbsReply.html")
    public String bbsReply(@PathVariable Integer bbsid, BbsVo paramVO, PstVo pstVO, Model model) throws Exception {
        model.addAttribute("paramMap", bbsService.selectOneBbs(paramVO));
        model.addAttribute("pstVo", bbsService.selectPst(pstVO));
        return "cmm/mng/bbsReply";
    }

    /**
     * insertCmnt.
     *
     * @param paramVO :
     * @param user    :
     * @return Map<String, Object> :
     * @throws Exception :
     * @Title : insertCmnt
     * @Description : TODO
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/insertCmnt.do")
    public @ResponseBody Map<String, String> insertCmnt(@PathVariable Integer bbsid, CmntVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.insertCmnt(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (SQLException e) {
            log.error("insertCmnt.SQLException.443L");
        } catch (Exception e) {
            log.error("insertCmnt.Exception.443L");
        }
        return resultMap;
    }

    /**
     * insertCmntReply.
     *
     * @param paramVO
     * @return Map<String, Object>
     * @throws Exception :
     * @Title : insertCmnt
     * @Description : TODO
     */
    @RequestMapping(value = "/cmm//bbs/{bbsid}/insertCmntReply.do")
    public @ResponseBody Map<String, String> insertCmntReply(@PathVariable Integer bbsid, CmntVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.insertReply(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (SQLException e) {
            log.error("insertCmntReply.SQLException.469L");
        } catch (Exception e) {
            log.error("insertCmntReply.Exception.469L");
        }
        return resultMap;
    }

    /**
     * updateCmnt
     *
     * @param paramVO :
     * @param user    :
     * @return Map<String, Object>
     * @throws Exception :
     * @Title : updateCmnt
     * @Description : TODO
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/updateCmnt.do")
    public @ResponseBody Map<String, String> updateCmnt(@PathVariable Integer bbsid, CmntVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.updateCmnt(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (SQLException e) {
            log.error("updateCmnt.SQLException.495L");
        } catch (Exception e) {
            log.error("updateCmnt.Exception.495L");
        }
        return resultMap;
    }

    /**
     * selectNtcPstCnt
     *
     * @param paramVO :
     * @param user    :
     * @return Map<String, Object>
     * @throws Exception :
     * @Title : selectNtcPstCnt
     * @Description : TODO
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/selectNtcPstCnt.do")
    public @ResponseBody Map<String, Object> selectNtcPstCnt(@PathVariable Integer bbsid, PstVo pstVO, @UserInfo UserVo user) throws Exception {

        PstVo resVo = new PstVo();
        Map<String, Object> resultMap = new HashMap<>();
        pstVO.setUser(user);
        try {
            resVo = bbsService.selectNtcPstCnt(pstVO);
            resultMap.put("cntVo", resVo);
        } catch (SQLException e) {
            log.error("deletePst.SQLException.548L");
        } catch (Exception e) {
            log.error("deletePst.Exception.548L");
        }
        return resultMap;
    }

    /**
     * @param PstVo
     * @return Map<String, Object>
     * @throws Exception
     * @Title : selectCmntList
     * @Description : 댓글 리스트 호출
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/selectCmntList.do")
    public @ResponseBody Map<String, Object> selectCmntList(@PathVariable Integer bbsid, CmntVo cmntVO) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<CmntVo> result = new ArrayList<CmntVo>();
        cmntVO.setOrderField("CMNT_GRP DESC,ORD ");
        cmntVO.setOrderDirection(CmntVo.ORDER_DIRECTION_ASC);

        try {
            result = bbsService.selectCmntList(cmntVO);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
                resultMap.put("pagination", PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
            } else {
                resultMap.put("totalCount", 0);
            }
            resultMap.put("list", result);
        } catch (SQLException e) {
            log.error("selectBbsCl.SQLException.188L");
        } catch (Exception e) {
            log.error("selectBbsCl.Exception.188L");
        }
        return resultMap;
    }

}
