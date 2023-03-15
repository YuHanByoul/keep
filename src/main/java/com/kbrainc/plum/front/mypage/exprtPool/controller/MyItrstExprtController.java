package com.kbrainc.plum.front.mypage.exprtPool.controller;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.mypage.exprtPool.service.MyItrstExprtServcice;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 환경교육 전문가 풀 관리 > 관심전문가 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.controller
 * - MyItrstExprtController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyItrstExprtController
 * @Description : 마이페이지 > 환경교육 전문가 풀 관리 > 관심전문가 컨트롤러 클래스
 * @date : 2023. 03. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/exprtPool")
public class MyItrstExprtController {

    @Autowired
    private MyItrstExprtServcice myItrstExprtServcice;

    @GetMapping("/itrstExprtList.html")
    public String itrstExprtList(ExprtVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return "/front/mypage/exprtPool/myItrstExprtList";
    }

    @GetMapping("/selectItrstExprtList.do")
    @ResponseBody
    public Map<String, Object> selectItrstExprtList(ExprtVo searchVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        searchVo.setUser(user);
        List<ExprtVo> list = myItrstExprtServcice.selectItrstExprtList(searchVo);

        if (list.size() > 0) {
            response.put("totalCount", list.get(0).getTotalCount());
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }


}
