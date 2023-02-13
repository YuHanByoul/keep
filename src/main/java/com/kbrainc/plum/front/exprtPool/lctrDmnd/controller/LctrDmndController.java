package com.kbrainc.plum.front.exprtPool.lctrDmnd.controller;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.LctrDmndVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.service.LctrDmndService;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.controller
 * - LctrDmndController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndController
 * @Description : 환경교육 전문가 풀 > 섭외 요청 컨트롤러 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.lctrDmndController")
@Controller("front.lctrDmndController")
@RequestMapping("/front/exprtPool")
public class LctrDmndController {
    private static final String VIEW_PATH = "/front/exprtPool";

    @Resource(name="front.lctrDmndService")
    private LctrDmndService lctrDmndService;

    /**
     * 섭외요청 목록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndList
     * @Description : 섭외요청 목록 화면
     */
    @GetMapping("/lctrDmndList.html")
    public String lctrDmndList(ExprtVo searchVo) throws Exception {
        return VIEW_PATH + "/lctrDmndList";
    }

    /**
     * 섭외요청 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndDetail
     * @Description : 섭외요청 상세 화면
     */
    @GetMapping("/lctrDmndDetail.html")
    public String lctrDmndDetail(ExprtVo searchVo) throws Exception {
        return VIEW_PATH + "/lctrDmndDetail";
    }

    /**
     * 섭외 요청 등록 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : lctrDmndForm
     * @Description : 섭외 요청 등록 화면
     */
    @GetMapping("/lctrDmndForm.html")
    public String lctrDmndForm(ExprtVo searchVo) throws Exception {
        return VIEW_PATH + "/lctrDmndForm";
    }

    /**
     * 전문가 목록 조회
     *
     * @param searchVO
     * @return map
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    @PostMapping("/selectExprtList.do")
    @ResponseBody
    public Map<String,Object> selectExprtList(ExprtVo searchVo) throws Exception{
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    /**
     * 전문가 섭외 요청 등록
     *
     * @param lctrDmndVo
     * @return map
     * @throws Exception
     * @Title : insertLctrDmnd
     * @Description : 전문가 섭외 요청 등록
     */
    @PostMapping("/insertLctrDmnd.do")
    @ResponseBody
    public Map<String,Object> insertLctrDmnd(LctrDmndVo lctrDmndVo) throws Exception {
        Map<String,Object> response = new HashMap<>();
        return response;
    }


}
