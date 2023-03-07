package com.kbrainc.plum.front.mypage.exprtPool.controller;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyRelationVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.ReviewVo;
import com.kbrainc.plum.front.mypage.exprtPool.service.MyRelationService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 환경교육 전문가 풀 관리 > 전문가 섭외 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.controller
 * - MyExprtRelationController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtRelationController
 * @Description : 마이페이지 > 환경교육 전문가 풀 관리 > 전문가 섭외 관리 컨트롤러 클래스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Controller
@RequestMapping("/front/mypage/exprtPool")
public class MyRelationController {
    private static final String VIEW_PATH = "/front/mypage/exprtPool";

    @Autowired
    private MyRelationService myRelationService;

    @GetMapping("/relationList.html")
    public String relationList(MyRelationVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/relationList";
    }

    @GetMapping("/relationDetail.html")
    public String relationDetail(MyRelationVo searchVo, Model model) throws Exception {
        Map<String, Object> result = myRelationService.selectMyRelation(searchVo);

        model.addAttribute("myRelation", result.get("myRelation"));
        model.addAttribute("exprt", result.get("exprt"));
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/relationDetail";
    }

    @PostMapping("/insertReview.do")
    @ResponseBody
    public Map<String, Object> insertReview(ReviewVo reviewVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        reviewVo.setUser(user);
        response.put("success", false);

        int result = myRelationService.insertReview(reviewVo);
        if (result > 0) {
            response.put("msg", "등록이 완료되었습니다.");
            response.put("success", true);
        } else {
            response.put("msg", "등록이 실패하였습니다.");
        }
        return response;
    }


    @PostMapping("/deleteReview.do")
    @ResponseBody
    public Map<String, Object> deleteReview(ReviewVo reviewVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        reviewVo.setUser(user);
        response.put("success", false);

        int result = myRelationService.deleteReview(reviewVo);
        if (result > 0) {
            response.put("msg", "삭제가 완료되었습니다.");
            response.put("success", true);
        } else {
            response.put("msg", "삭제가 실패하였습니다.");
        }

        return response;
    }

    @PostMapping("/cancelRelation.do")
    @ResponseBody
    public Map<String, Object> cancelRelation(MyRelationVo myRelationVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        myRelationVo.setUser(user);
        response.put("success", false);

        int result = myRelationService.cancelRelation(myRelationVo);
        if (result > 0) {
            response.put("msg", "요청취소가 성공하였습니다.");
            response.put("success", true);
        } else {
            response.put("msg", "요청취소가 실패하였습니다.");
        }

        return response;
    }

    @GetMapping("/selectRelationList.do")
    @ResponseBody
    public Map<String, Object> selectRelationList(MyRelationVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        searchVo.setUser(user);
        List<MyRelationVo> result = myRelationService.selectMyRelationList(searchVo);

        if (result.size() > 0) {
            response.put("totalCount", (result.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", result);

        return response;
    }

    @GetMapping("/selectReviewByDmndid.do")
    @ResponseBody
    public ReviewVo selectReviewByDmndid(@RequestParam Integer dmndid) throws Exception {
        return myRelationService.selectReviewByDmndid(dmndid);
    }

    @GetMapping("/selectAddrByDmndid.do")
    @ResponseBody
    public ReviewVo selectAddrByDmndid(@RequestParam Integer dmndid) throws Exception {

        return myRelationService.selectAddrByDmndid(dmndid);
    }

}
