package com.kbrainc.plum.mng.bbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.bbs.model.BbsClVo;
import com.kbrainc.plum.mng.bbs.model.BbsVo;
import com.kbrainc.plum.mng.bbs.model.CmntVo;
import com.kbrainc.plum.mng.bbs.model.PstVo;
import com.kbrainc.plum.mng.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * BBS Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.controller
 * - BbsController.java
 * </pre> 
 *
 * @ClassName : BbsController
 * @Description : BBS Controller
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class BbsController {

    @Autowired
    private BbsServiceImpl bbsService;

    /**
     * @Title : userTempListForm
     * @Description : user관리 리스트화면 이동
     * @throws Exception :
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/bbsForm.html")
    public String userTempListForm() throws Exception {
        return "mng/bbs/bbsForm";
    }

    /**
     * @Title : userTempInsertForm
     * @Description : user 관리 인서트 및 업데이트 트화면 이동
     * @throws Exception :
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/bbsInsertForm.html")
    public String userTempInsertForm() throws Exception {
        return "mng/bbs/bbsInsert";
    }

    /**
     * @Title : insertUserTemp
     * @Description : user insert
     * @param bbsVO :
     * @param user :
     * @throws Exception :
     * @return String Y or N
     */
    @RequestMapping(value = "/bbs/insertBbs.do")
    public @ResponseBody Map<String, String> insertBbs(@Valid BbsVo bbsVO, @UserInfo UserVo user) throws Exception {

        String resultMsg = Constant.REST_API_RESULT_FAIL;
        bbsVO.setUser(user);
        bbsVO.setUser(user);
        try {
            if (bbsService.insertBbs(bbsVO) == 1) {
                resultMsg = Constant.REST_API_RESULT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * 게시판 가져오기.
     * 
     * @Title : selectUserTmepList
     * @Description : 게시판 가져오기
     * @param paramVO :
     * @throws Exception :
     * @return Map String, Object
     */
    @RequestMapping(value = "/bbs/getBbsList.do")
    public @ResponseBody Map<String, Object> getBbsList(BbsVo paramVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<BbsVo> result = new ArrayList<BbsVo>();
        try {
            result = bbsService.getBbsList(paramVO);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
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
     * user 관리 업데이트 트화면 이동.
     *
     * @Title       : userTempmodifyForm 
     * @Description : TODO
     * @param bbsid :
     * @param model :
     * @param paramVO :
     * @return String :
     * @throws Exception :
     */
    @RequestMapping(value = "/bbs/bbsUpdateForm.html")
    public String userTempmodifyForm(@RequestParam(value = "bbsid", required = true) int bbsid, Model model,
            BbsVo paramVO) throws Exception {

        paramVO.setBbsid(bbsid);
        model.addAttribute("paramMap", bbsService.selectOneBbs(paramVO));

        return "mng/bbs/bbsModify";
    }

    /**
     * @Title : bbsClPop
     * @Description : 분류 관리 팝업창 화면 이동
     * @param UserTempVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/bbsClPop.html")
    public String bbsClPop(@RequestParam(value = "bbsid", required = true) int bbsid, Model model, BbsVo paramVO)
            throws Exception {
        paramVO.setBbsid(bbsid);
        model.addAttribute("codeMap", bbsService.selectBbsbyClUseYn(paramVO));
        model.addAttribute("bbsid", bbsid);
        return "mng/bbs/bbsPop";
    }

    /**
     * @Title : selectBbsCl
     * @Description : 분류관리 목록 select
     * @param String 이동화면경로
     * @throws Exception
     * @return list Data
     */
    @RequestMapping(value = "/bbs/selectBbsCl.do")
    public @ResponseBody Map<String, Object> selectBbsCl(BbsClVo clVo) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        List<BbsClVo> result = new ArrayList<BbsClVo>();
        try {
            result = bbsService.selectBbsCl(clVo);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
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
     * @Title : updateBbs
     * @Description : user insert
     * @param UserTempVo
     * @throws Exception
     * @return String Y or N
     */
    @RequestMapping(value = "/bbs/updateBbs.do")
    public @ResponseBody Map<String, String> updateBbs(BbsVo paramVO, @UserInfo UserVo user) throws Exception {

        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.updateBbs(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("result", resultMsg);
        return resultMap;
    }

    /**
     * @Title : insertBbsCl
     * @Description : user insert
     * @param UserTempVo
     * @throws Exception
     * @return String Y or N
     */
    @RequestMapping(value = "/bbs/insertBbsCl.do")
    public @ResponseBody Map<String, String> insertBbsCl(@RequestParam(value = "mode", required = false) String mode,
            BbsClVo clVo, @UserInfo UserVo user) throws Exception {

        String resultMsg = Constant.REST_API_RESULT_FAIL;
        clVo.setUser(user);
        try {
            if (mode.equals("C")) { // 인서트시
                bbsService.insertBbsCl(clVo);
            } else if (mode.equals("U")) { // 업데이트시
                bbsService.updateBbsCl(clVo);
            } else if (mode.equals("OU")) { // ord up
                bbsService.modifybbsClOrdUp(clVo);
            } else if (mode.equals("OD")) { // ord down
                bbsService.modifybbsClOrdDown(clVo);
            }
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /*************** 게시물 관리 *********************/
    /*************** 게시물 관리 *********************/

    /**
     * @Title : bbsPstForm
     * @Description : 게시물 관리창 화면 이동
     * @param UserTempVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/bbsPstForm.html")
    public String bbsPstForm(@RequestParam(name = "bbsid", required = false) String bbsid, Model model, BbsVo paramVO)
            throws Exception {

        paramVO.setSearchKeyword("All");
        model.addAttribute("codeMap", bbsService.selectBbsbyClUseYn(paramVO));
        model.addAttribute("bbsid", bbsid);
        return "mng/bbs/pstForm";
    }

    /**
     * @Title : bbsPstInsertForm
     * @Description : 게시물 관리창 화면 이동
     * @param UserTempVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/bbsPstInsertForm.html")
    public String bbsPstInsertForm(@RequestParam(name = "bbsid", required = false) String bbsid, Model model,
            BbsVo paramVO ,
            BbsClVo bbsClVo ) throws Exception {

    	bbsClVo.setBbsid(Integer.parseInt(bbsid));
    	model.addAttribute("clList", bbsService.selectBbsCl(bbsClVo));
        paramVO.setSearchKeyword("All");
        model.addAttribute("paramMap", bbsService.selectOneBbs(paramVO));
        return "mng/bbs/pstInsert";
    }

    /**
     * @Title : insertPst
     * @Description : insertPst
     * @param UserTempVo
     * @throws Exception
     * @return String Y or N
     */
    @RequestMapping(value = "/bbs/insertPst.do")
    public @ResponseBody Map<String, String> insertPst(@Valid PstVo paramVO, @UserInfo UserVo user) throws Exception {

        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);

        try {
            if (bbsService.insertPst(paramVO) == 1) {
                resultMsg = Constant.REST_API_RESULT_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("result", resultMsg);

        return resultMap;
    }

    /**
     * @Title : getPstList
     * @Description : 게시글 목록 가져오기
     * @param BbsVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/bbs/getPstList.do")
    public @ResponseBody Map<String, Object> getPstList(PstVo paramVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PstVo> result = new ArrayList<PstVo>();
        try {
            result = bbsService.selectPstList(paramVO);
            if (result.size() > 0) {
                resultMap.put("totalCount", (result.get(0).getTotalCount()));
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
     * @Title : pstUpdateForm
     * @Description : 게시물 업데이트 트화면 이동
     * @param PstVo
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/bbs/pstUpdateForm.html")
    public String pstUpdateForm(@RequestParam(value = "pstid", required = true) int pstid, Model model, PstVo paramVO,CmntVo cmntVO,
        Map<String, Object> resultMap, BbsClVo bbsClVo) throws Exception {
    	
        paramVO.setPstid(pstid);
        cmntVO.setPstid(pstid);
        //cmntVO.setOrderField(" PSTID, CMNT_GRP,ORD ");
        cmntVO.setOrderField("CMNT_GRP DESC,ORD ");
        
        cmntVO.setOrderDirection(CmntVo.ORDER_DIRECTION_ASC);
        resultMap = bbsService.selectPst(paramVO);
        
        paramVO = (PstVo)resultMap.get("paramMap");
        //bbsClVo.setBbsid(Integer.parseInt(paramVO.getBbsid()));
        if(!StringUtil.nvl(paramVO.getBbsid()).equals("")) {
        	bbsClVo.setBbsid(paramVO.getBbsid());
        }
        
        BbsVo bbsVo = new BbsVo();
        bbsVo.setBbsid(paramVO.getBbsid());
        model.addAttribute("bbsInfo", bbsService.selectOneBbs(bbsVo));
        
        paramVO.setPrntsPstid(pstid);
        model.addAttribute("result", bbsService.selectPst(paramVO));
        model.addAttribute("replyList", bbsService.selectReplyPstList(paramVO));
        model.addAttribute("cmntList", bbsService.selectCmntList(cmntVO));
        model.addAttribute("clList", bbsService.selectBbsClList(bbsClVo));

        return "mng/bbs/pstModify";
    }

    /**
     * @Title : updatePst
     * @Description : updatePst
     * @param paramVO
     * @throws Exception
     * @return String Y or N
     */
    @RequestMapping(value = "/bbs/updatePst.do")
    public @ResponseBody Map<String, String> updatePst(PstVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.updatePst(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * @Title : deleteFileByFileId
     * @Description : deleteFile DB
     * @param paramVO
     * @throws Exception
     * @return String Y or N
     */
    @RequestMapping(value = "/deleteFileByFileId.do")
    public @ResponseBody Map<String, String> deleteFileByFileId(
            @RequestParam(value = "fileId", required = false) int fileId, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        try {
            bbsService.deleteFileByfileId(fileId);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 
     * insertCmnt.
     *
     * @Title       : insertCmnt 
     * @Description : TODO
     * @param paramVO :
     * @param user :
     * @return Map String,String : 
     * @throws Exception :
     */
    @RequestMapping(value = "/bbs/insertCmnt.do")
    public @ResponseBody Map<String, String> insertCmnt(CmntVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.insertCmnt(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    
    /**
     * 
     * insertCmntReply.
     *
     * @Title       : insertCmnt 
     * @Description : TODO
     * @param paramVO :
     * @param user :
     * @return Map String,String : 
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bbs/insertCmntReply.do")
    public @ResponseBody Map<String, String> insertCmntReply(CmntVo paramVO, @UserInfo UserVo user) throws Exception {
    	Map<String, String> resultMap = new HashMap<>();
    	String resultMsg = Constant.REST_API_RESULT_FAIL;
    	paramVO.setUser(user);
    	try {
    		bbsService.insertReply(paramVO);
    		resultMsg = Constant.REST_API_RESULT_SUCCESS;
    		resultMap.put("result", resultMsg);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultMap;
    }

    /**
     * 
     * updateCmnt
     *
     * @Title       : updateCmnt 
     * @Description : TODO
     * @param paramVO :
     * @param user :
     * @return Map String,String 
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bbs/updateCmnt.do")
    public @ResponseBody Map<String, String> updateCmnt(CmntVo paramVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        paramVO.setUser(user);
        try {
            bbsService.updateCmnt(paramVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    
    /**
     * 
     * updateCmntReplyDelYn
     *
     * @Title       : updateCmntReplyDelYn 
     * @Description : TODO
     * @param paramVO :
     * @param user :
     * @return Map String,String 
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bbs/updateCmntReplyDelYn.do")
    public @ResponseBody Map<String, String> updateCmntReplyDelYn(CmntVo paramVO, @UserInfo UserVo user) throws Exception {
    	Map<String, String> resultMap = new HashMap<>();
    	String resultMsg = Constant.REST_API_RESULT_FAIL;
    	paramVO.setUser(user);
    	try {
    		bbsService.updateCmntReplyDelYn(paramVO);
    		resultMsg = Constant.REST_API_RESULT_SUCCESS;
    		resultMap.put("result", resultMsg);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultMap;
    }

    
   /**
     * 
     * deletePst
     *
     * @Title       : deletePst 
     * @Description : TODO
     * @param paramVO :
     * @param user :
     * @return Map String,String 
     * @throws Exception :
     */
    @RequestMapping(value = "/bbs/deletePst.do")
    public @ResponseBody Map<String, String> deletePst(PstVo pstVO, @UserInfo UserVo user) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String resultMsg = Constant.REST_API_RESULT_FAIL;
        pstVO.setUser(user);
        try {
            bbsService.deletePst(pstVO);
            resultMsg = Constant.REST_API_RESULT_SUCCESS;
            resultMap.put("result", resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    
}
