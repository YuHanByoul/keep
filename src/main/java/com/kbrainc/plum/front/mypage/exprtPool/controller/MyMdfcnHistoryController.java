package com.kbrainc.plum.front.mypage.exprtPool.controller;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyMdfcnHistoryVo;
import com.kbrainc.plum.front.mypage.exprtPool.service.MyMdfcnHistoryService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.controller
 * - MyMdfcnHistoryController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyMdfcnHistoryController
 * @Description : 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 컨트롤러 클래스
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/exprtPool")
public class MyMdfcnHistoryController {
    private static final String VIEW_PATH = "/front/mypage/exprtPool";

    @Autowired
    private MyMdfcnHistoryService myMdfcnHistoryService;

    @GetMapping("/mdfcnHistoryList.html")
    public String mdfcnHistory() throws Exception {
        return VIEW_PATH + "/mdfcnHistoryList";
    }

    @GetMapping("/selectMdfcnHistoryList.do")
    @ResponseBody
    public Map<String, Object> selectMdfcnHistoryList(MyMdfcnHistoryVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        searchVo.setUser(user);

        List<MyMdfcnHistoryVo> result = myMdfcnHistoryService.selectMdfcnHistoryList(searchVo);

        if (result.size() > 0) {
            response.put("totalCount", (result.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", result);

        return response;
    }

    @GetMapping("/selectMdfcnRsnByDmndId.do")
    @ResponseBody
    public String selectMdfcnRsnByDmndId(@RequestParam Integer mdfcnDmndId) throws Exception {
        return myMdfcnHistoryService.selectMdfcnRsnByDmndId(mdfcnDmndId);
    }

    @GetMapping("/selectLogRsnByDmndId.do")
    @ResponseBody
    public String selectLogRsnByDmndId(@RequestParam Integer mdfcnDmndId) throws Exception {
        return myMdfcnHistoryService.selectLogRsnByDmndId(mdfcnDmndId);
    }

}
