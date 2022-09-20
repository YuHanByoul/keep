package com.kbrainc.plum.cmm.bbs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.bbs.model.BbsClVo;
import com.kbrainc.plum.mng.bbs.model.BbsVo;
import com.kbrainc.plum.mng.bbs.model.CmntVo;
import com.kbrainc.plum.mng.bbs.model.PstVo;
import com.kbrainc.plum.mng.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * 생성된 게시판에 대한 사용 예시.
 *
 * <pre>
 * com.kbrainc.plum.cmm.bbs.controller
 * - CmmBbsController.java
 * </pre> 
 *
 * @ClassName : CmmBbsController
 * @Description : 
 * @author : KBRAINC
 * @date : 2021. 3. 25.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller
public class CmmBbsController {

    @Resource
    private BbsServiceImpl bbsService;

    /**
     * 
     * bbsid에 따른 게시물 메인리스트. 
     *
     * @Title : bbsPstForm
     * @Description : 
     * @param bbsid 게시판 ID
     * @param model
     * @param paramVO
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/cmm/bbs/{bbsid}/main.html")
    public String bbsPstForm(@PathVariable Integer bbsid, Model model, BbsVo paramVo) throws Exception {

        paramVo.setBbsid(bbsid);
        BbsVo bbsInfo = bbsService.selectOneBbs(paramVo);
        model.addAttribute("bbsid", bbsid);
        model.addAttribute("bbs_nm", bbsInfo.getNm());
        
        return "cmm/bbs/bbsMain";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/write.html")
    public String bbsPstInsertForm(@PathVariable Integer bbsid, Model model) throws Exception {

        BbsVo paramVo = new BbsVo(bbsid); 
        BbsClVo bbsClVo = new BbsClVo(bbsid);
        
        model.addAttribute("clList", bbsService.selectBbsCl(bbsClVo));
        model.addAttribute("paramMap", bbsService.selectOneBbs(paramVo));
        
        return "cmm/bbs/bbsWrite";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/read.html")
    public String bbsRead(@PathVariable Integer bbsid, Model model, PstVo paramVo) throws Exception {
        CmntVo cmntVo = new CmntVo();
        BbsClVo bbsClVo = new BbsClVo();
        Map<String, Object> resultMap = new HashMap<>();
        
        //paramVo.setPstid(bbsid);
        cmntVo.setPstid(paramVo.getPstid());
        cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD "); 
        cmntVo.setOrderDirection(cmntVo.getOrderDirection().asc);
        
        resultMap = bbsService.selectPst(paramVo);
        
        paramVo = (PstVo)resultMap.get("paramMap");
        
        if(!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(paramVo.getBbsid());
        }
        model.addAttribute("result", bbsService.selectPst(paramVo));
        model.addAttribute("cmntList", bbsService.selectCmntList(cmntVo));
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));
        
        return "cmm/bbs/bbsRead";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/modify.html")
    public String bbsModify(@PathVariable Integer bbsid, Model model, PstVo paramVo) throws Exception {
        CmntVo cmntVo = new CmntVo();
        BbsClVo bbsClVo = new BbsClVo();
        Map<String, Object> resultMap = new HashMap<>();
        
        cmntVo.setPstid(paramVo.getPstid());
        cmntVo.setOrderField(" PSTID, CMNT_GRP,ORD "); 
        cmntVo.setOrderDirection(CmntVo.ORDER_DIRECTION_ASC);
        
        resultMap = bbsService.selectPst(paramVo);
        
        paramVo = (PstVo)resultMap.get("paramMap");
        
        if(!StringUtil.nvl(paramVo.getBbsid()).equals("")) {
            bbsClVo.setBbsid(paramVo.getBbsid());
        }
        model.addAttribute("result", bbsService.selectPst(paramVo));
        model.addAttribute("cmntList", bbsService.selectCmntList(cmntVo));
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));
        
        return "cmm/bbs/bbsModify";
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/insert.do")
    public @ResponseBody Map<String, String> insertPst(@PathVariable Integer bbsid, @Valid PstVo paramVo, @UserInfo UserVo user) throws Exception {

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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/update.do")
    public @ResponseBody Map<String, String> updatePst(@PathVariable Integer bbsid, PstVo paramVo, @UserInfo UserVo user) throws Exception {
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/insertCmnt.do")
    public @ResponseBody Map<String, String> insertCmnt(@PathVariable Integer bbsid, CmntVo paramVo, @UserInfo UserVo user) throws Exception {
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/insertCmntReply.do")
    public @ResponseBody Map<String, String> insertCmntReply(@PathVariable Integer bbsid, CmntVo paramVo, @UserInfo UserVo user) throws Exception {
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/updateCmnt.do")
    public @ResponseBody Map<String, String> updateCmnt(@PathVariable Integer bbsid, CmntVo paramVo, @UserInfo UserVo user) throws Exception {
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
    @RequestMapping(value = "/cmm/bbs/{bbsid}/updateCmntReplyDelYn.do")
    public @ResponseBody Map<String, String> updateCmntReplyDelYn(@PathVariable Integer bbsid, CmntVo paramVo, @UserInfo UserVo user) throws Exception {
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
     * bbsid에 따른 게시물 삭제 처리.
     *
     * @Title : deletePst
     * @Description : 
     * @param bbsid
     * @param pstVo
     * @param user
     * @throws Exception
     * @return Map<String,String>
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultMap.put("result", resultMsg);

        return resultMap;
    }
}
