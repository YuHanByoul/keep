package com.kbrainc.plum.front.mypage.ntcnHist.controller;

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

import com.kbrainc.plum.front.mypage.ntcnHist.model.NtcnHistVo;
import com.kbrainc.plum.front.mypage.ntcnHist.service.NtcnHistService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 마이페이지 > 알림 내역
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.ntcnHist
 * - MyNtcnHistController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : MyNtcnHistController
 * @Description : 마이페이지 > 알림 내역
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/ntcnHist")
public class NtcnHistController {
    private static final String VIEW_PATH = "/front/mypage/ntcnHist";

    @Resource(name = "front.ntcnHistServiceImpl")
    private NtcnHistService ntcnHistService;

    @GetMapping("/ntcnHistListForm.html")
    public String ntcnHistList(Model model) throws Exception {
        return VIEW_PATH + "/ntcnHistList";
    }

    @GetMapping(value = "/selectNtcnHistList.do")
    @ResponseBody
    public Map<String, Object> selectInqryList(NtcnHistVo ntcnHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ntcnHistVo.setUser(user);
        
        //boolean success = false;
        //if (ntcnHistService.updateInqMsg(ntcnHistVo) > 0) { success = true; }
        
        //result.put("success", success);        
        List<NtcnHistVo> list = ntcnHistService.selectNtcnHistList(ntcnHistVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }
 
    @PostMapping("/updateDeleteNtcn.do")
    @ResponseBody
    public Map<String, Object> updateDeleteNtcn(NtcnHistVo ntcnHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        ntcnHistVo.setUser(userVo);

        if (ntcnHistService.updateDeleteNtcn(ntcnHistVo) > 0) {
            success = true;
            response.put("msg", "삭제가 완료되었습니다.");
        } else
            response.put("msg", "삭제가 실패하였습니다.");

        response.put("success", success);
        return response;
    }    
}
