package com.kbrainc.plum.front.intro.envEduPlcyEtcDta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model.EtcDtaVo;
import com.kbrainc.plum.front.intro.envEduPlcyEtcDta.service.EnvEduPlcyEtcDtaService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 환경교육 정책자료실 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyEtcDta.controller
 * - EnvEduPlcyEtcDtaController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyEtcDtaController
 * @Description : 환경교육 정책자료실 컨트롤러
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/intro/envEduPlcyEtcDta")
public class EnvEduPlcyEtcDtaController {

    private static final String VIEW_PATH = "/front/intro/envEduPlcyEtcDta";

    @Resource(name = "front.envEduPlcyEtcDtaService")
    private EnvEduPlcyEtcDtaService envEduPlcyEtcDtaService;

    /**
     * 사업운영 자료 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : etcDtaList
     * @Description : 사업운영 자료 목록 화면
     */
    @GetMapping("/etcDtaList.html")
    public String etcDtaList(EtcDtaVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/etcDtaList";
    }

    /**
     * 사업운영자료 상세 화면
     *
     * @param searchVo
     * @return string
     * @throws Exception
     * @Title : etcDtaDetail
     * @Description : 사업운영자료 상세 화면
     */
    @GetMapping("/etcDtaDetail.html")
    public String etcDtaDetail(EtcDtaVo searchVo, Model model) throws Exception {
        EtcDtaVo etcDta = envEduPlcyEtcDtaService.selectEtcDta(searchVo);

        model.addAttribute("searchVo", searchVo);
        model.addAttribute("etcDta", etcDta);
        return VIEW_PATH + "/etcDtaDetail";
    }

    /**
     * 사업운영자료 목록 조회
     *
     * @param etcDtaVo
     * @return map
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @GetMapping("/selectEtcDtaList.do")
    @ResponseBody
    public Map<String, Object> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception {
        Map<String, Object> response = new HashMap<>();

        List<EtcDtaVo> list = envEduPlcyEtcDtaService.selectEtcDtaList(etcDtaVo);

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
