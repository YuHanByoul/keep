package com.kbrainc.plum.front.mypage.mymsg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.mypage.mymsg.model.MymsgVo;
import com.kbrainc.plum.front.mypage.mymsg.service.MymsgService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 마이페이지 > 쪽지함
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.mymsg
 * - MymsgController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : MymsgController
 * @Description : 마이페이지 > 쪽지함
 * @date : 2023. 3. 7.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/mymsg")
public class MymsgController {
    private static final String VIEW_PATH = "/front/mypage/mymsg";

    @Resource(name = "front.mymsgServiceImpl")
    private MymsgService mymsgService;

    /**
    * 받은쪽지함 목록 화면 이동
    **
    @Title : mymsgRecvList
    * @Description : 받은쪽지함 목록 화면 이동
    * @param searchVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @GetMapping("/mymsgRecvListForm.html")
    public String mymsgRecvList(MymsgVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/mymsgRecvList";
    }

    /**
    * 받은쪽지함 목록 조회
    **
    @Title : selectRecvMsgList
    * @Description : 받은쪽지함 목록 조회
    * @param mymsgVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @GetMapping(value = "/selectRecvMsgList.do")
    @ResponseBody
    public Map<String, Object> selectRecvMsgList(MymsgVo mymsgVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        mymsgVo.setUser(user);
        
        boolean success = false;
        /*
         * if (mymsgService.updateInqMsg(mymsgVo) > 0) { success = true; }
         */
        //result.put("success", success);
        List<MymsgVo> list = mymsgService.selectRecvMsgList(mymsgVo);
        //List<MvmnAplyVo> list = null;
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
    * 받은쪽지함 목록 상세화면 이동
    **
    @Title : mymsgRecvDetail
    * @Description : 받은쪽지함 목록 상세화면 이동
    * @param mymsgVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @GetMapping("/mymsgRecvDetail.html")
    public String mymsgRecvDetail(MymsgVo mymsgVo, Model model, @UserInfo UserVo user) throws Exception {
        MymsgVo mymsgInfoVo = mymsgService.selectRecvMsgInfo(mymsgVo);
        model.addAttribute("mymsgInfoVo", mymsgInfoVo);
        return VIEW_PATH + "/mymsgRecvDetail";
    }

    /**
    * 받은쪽지함 답장 팝업화면 이동
    **
    @Title : mymsgSendPopup
    * @Description : 받은쪽지함 답장 팝업화면 이동
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @GetMapping("/mymsgSendPopup.html")
    public String mymsgSendPopup(Model model) throws Exception {
        return VIEW_PATH + "/mymsgSendPopup";
    }    
    
    /**
    * 받은쪽지함 답장 등록
    **
    @Title : insertSendMsg
    * @Description : 받은쪽지함 답장 등록
    * @param mymsgVo
    * @param userVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @PostMapping("/insertSendMsg.do")
    @ResponseBody
    public Map<String, Object> insertSendMsg(MymsgVo mymsgVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        mymsgVo.setUser(userVo);

        if (mymsgService.insertSendMsg(mymsgVo) > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
            response.put("msg", "쪽지를 성공적으로 발송 했습니다.");
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);            
            response.put("msg", "쪽지 발송이 실패 했습니다.");
        }    
        return response;
    }
    
    /**
    * 받은쪽지함 삭제
    **
    @Title : updateUserDelMsg
    * @Description : 받은쪽지함 삭제
    * @param mymsgVo
    * @param userVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @PostMapping("/updateUserDelMsg.do")
    @ResponseBody
    public Map<String, Object> updateUserDelMsg(MymsgVo mymsgVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        mymsgVo.setUser(userVo);
        
        if (mymsgService.updateUserDelMsg(mymsgVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");
        
        response.put("success", success);
        return response;
    }    
    
    /**
     * 보낸쪽지함 목록 화면 이동
     **
        @Title : mymsgSendList
     * @Description : 보낸쪽지함 목록 화면 이동
     * @param searchVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @GetMapping("/mymsgSendListForm.html")
    public String mymsgSendList(MymsgVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/mymsgSendList";
    }
    
    /**
     * 보낸쪽지함 목록 조회
     **
        @Title : selectSendMsgList
     * @Description : 보낸쪽지함 목록 조회
     * @param mymsgVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @GetMapping(value = "/selectSendMsgList.do")
    @ResponseBody
    public Map<String, Object> selectSendMsgList(MymsgVo mymsgVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        mymsgVo.setUser(user);
        List<MymsgVo> list = mymsgService.selectSendMsgList(mymsgVo);
        //List<MvmnAplyVo> list = null;
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
     * 보낸쪽지함 목록 상세화면 이동
     **
        @Title : mymsgSendDetail
     * @Description : 보낸쪽지함 목록 상세화면 이동
     * @param mymsgVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
     */
    @GetMapping("/mymsgSendDetail.html")
    public String mymsgSendDetail(MymsgVo mymsgVo, Model model, @UserInfo UserVo user) throws Exception {
        MymsgVo mymsgInfoVo = mymsgService.selectSendMsgInfo(mymsgVo);
        model.addAttribute("mymsgInfoVo", mymsgInfoVo);
        return VIEW_PATH + "/mymsgSendDetail";
    }
    
    /**
     * 보낸쪽지함 삭제
     **
        @Title : updateUserDelMsg
     * @Description : 보낸쪽지함 삭제
     * @param mymsgVo
     * @param userVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @PostMapping("/updateTrgtDelMsg.do")
    @ResponseBody
    public Map<String, Object> updateTrgtDelMsg(MymsgVo mymsgVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        mymsgVo.setUser(userVo);
        
        if (mymsgService.updateTrgtDelMsg(mymsgVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");
        
        response.put("success", success);
        return response;
    }    
}
