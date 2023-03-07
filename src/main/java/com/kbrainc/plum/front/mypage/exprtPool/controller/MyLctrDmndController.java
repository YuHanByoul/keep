package com.kbrainc.plum.front.mypage.exprtPool.controller;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndVo;
import com.kbrainc.plum.front.mypage.exprtPool.service.MyLctrDmndService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 전문가 요청 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.controller
 * - MyExprtLctrDmndController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtLctrDmndController
 * @Description : 마이페이지 > 전문가 요청 관리 컨트롤러 클래스
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Controller
@RequestMapping("/front/mypage/exprtPool")
public class MyLctrDmndController {
    private static final String VIEW_PATH = "/front/mypage/exprtPool";

    @Autowired
    private MyLctrDmndService myLctrDmndService;

    /**
     * 전문가 요청 관리 목록 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : lctrDmndList
     * @Description : 전문가 요청 관리 목록 화면
     */
    @GetMapping("/lctrDmndList.html")
    public String lctrDmndList(MyLctrDmndVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/lctrDmndList";
    }

    /**
     * 전문가 요청 관리 상세 화면
     *
     * @param searchVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : lctrDmndDetail
     * @Description : 전문가 요청 관리 상세 화면
     */
    @GetMapping("/lctrDmndDetail.html")
    public String lctrDmndDetail(MyLctrDmndVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        model.addAttribute("lctrDmnd", myLctrDmndService.selectLctrDmnd(searchVo));
        return VIEW_PATH + "/lctrDmndDetail";
    }

    /**
     * 전문가 요청 관리 목록 조회
     *
     * @param searchVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : selectLctrDmndList
     * @Description : 전문가 요청 관리 목록 조회
     */
    @GetMapping("/selectLctrDmndList.do")
    @ResponseBody
    public Map<String, Object> selectLctrDmndList(MyLctrDmndVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        searchVo.setUser(user);
        List<MyLctrDmndVo> result = myLctrDmndService.selectLctrDmndList(searchVo);

        if (result.size() > 0) {
            response.put("totalCount", (result.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", result);

        return response;
    }

    /**
     * 강의 요청서 상태 변경
     *
     * @param myLctrDmndVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : updateStatus
     * @Description : 강의 요청서 상태 변경
     */
    @PostMapping("/updateStatus.do")
    @ResponseBody
    public Map<String, Object> updateStatus(MyLctrDmndVo myLctrDmndVo, @UserInfo UserVo user) throws Exception {
        myLctrDmndVo.setUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);

        if (myLctrDmndService.updateStatus(myLctrDmndVo) > 0) {
            response.put("success", true);
            response.put("msg", "변경되었습니다.");
        } else {
            response.put("msg", "변경이 실패하였습니다.");
        }

        return response;
    }


}
