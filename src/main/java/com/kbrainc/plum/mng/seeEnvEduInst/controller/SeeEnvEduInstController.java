package com.kbrainc.plum.mng.seeEnvEduInst.controller;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.inqry.model.InqryVo;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplyVO;
import com.kbrainc.plum.mng.seeEnvEduInst.model.SeeEnvEduInstVo;
import com.kbrainc.plum.mng.seeEnvEduInst.service.SeeEnvEduInstService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.excel.ExcelDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 교육기관/시설관리 > 사회환경교육기관 현황 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.seeEnvEduInst.controller
 * - SeeEnvEduInstController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstController
 * @Description : 교육기관/시설관리 > 사회환경교육기관 현황 컨트롤러
 * @date : 2023. 04. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SeeEnvEduInstController {

    @Autowired
    private SeeEnvEduInstService seeEnvEduInstService;

    /**
     * 사회환경교육기관 현황 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : seeEnvEduInstList
     * @Description : 사회환경교육기관 현황 목록 화면
     */
    @GetMapping("/mng/seeEnvEduInst/seeEnvEduInstList.html")
    public String seeEnvEduInstList() throws Exception {
        return "/mng/seeEnvEduInst/seeEnvEduInstList";
    }

    /**
     * 사회환경교육기관 목록 조회
     *
     * @param seeEnvEduInstVo
     * @return map
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 사회환경교육기관 목록 조회
     */
    @GetMapping("/mng/seeEnvEduInst/selectSeeEnvEduInstList.do")
    @ResponseBody
    public Map<String, Object> selectSeeEnvEduInstList(SeeEnvEduInstVo seeEnvEduInstVo) throws Exception {
        Map<String,Object> response = new HashMap<>();
        List<SeeEnvEduInstVo> list = seeEnvEduInstService.selectSeeEnvEduInstList(seeEnvEduInstVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @GetMapping("/mng/seeEnvEduInst/selectSeeEnvEduInstExcelDown.do")
    public void selectSeeEnvEduInstExcelDown(SeeEnvEduInstVo seeEnvEduInstVo, HttpServletResponse response) throws Exception {
        List<SeeEnvEduInstVo> result = seeEnvEduInstService.selectSeeEnvEduInstList(seeEnvEduInstVo);

        ExcelDownloadUtil<SeeEnvEduInstVo> excelDownUtil = new ExcelDownloadUtil<>(new String[] {"No.", "시도", "기관명", "기관유형", "대표자명", "전화번호", "지정일"}, result,
                (data, mapper, idx) -> {
                    mapper
                            .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                            .putData(1, data.getCtprvnCdNm())
                            .putData(2, data.getInstNm(), ExcelDownloadUtil.CELL_ALIGN.LEFT)
                            .putData(3, data.getInstTypeCdNm())
                            .putData(4, data.getRprsvNm())
                            .putData(5, data.getTelno())
                            .putData(6, data.getDsgnDe());
                    return true;
                }
        );
        excelDownUtil.excelDownload(response, "사회환경교육기관 목록");
    }
}
