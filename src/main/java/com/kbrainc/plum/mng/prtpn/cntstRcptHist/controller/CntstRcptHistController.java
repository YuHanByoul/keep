package com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller;

import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstRcptHistVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.service.CntstRcptHistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 공모전 접수 내역 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.controller
 * - CntstRcptHistController.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistController
 * @Description : 공모전 접수 내역 컨트롤러
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class CntstRcptHistController {

    @Autowired
    CntstRcptHistService cntstRcptHistService;


    /**
     * 공모전 접수내역
     * Description : 공모전 접수내역
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistListForm.html")
    public String cntstRcptHistListForm() {
        return "mng/prtpn/cntstRcptHist/cntstRcptHistList";
    }

    /**
     * 공모전 접수내역 목록 조회
     * Title : selectCntstRcptHistList
     * Description : 공모전 접수내역 목록 조회
     *
     * @param cntstRcptHistVO
     * @return map
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/selectCntstRcptHistList.do")
    @ResponseBody
    public Map<String, Object> selectCntstRcptHistList(CntstRcptHistVO cntstRcptHistVO) {
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstRcptHistVO> result = null;
        result =  cntstRcptHistService.selectCntstRcptHistList(cntstRcptHistVO);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }

    /**
     * 공모전 접수내역 목록 엑셀 다운
     * Title : cntstRcptHistListExcelDown
     * Description : 공모전 접수내역 목록 엑셀 다운
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistListExcelDown.do")
    @ResponseBody
    public Map<String, Object> cntstRcptHistListExcelDown() {
        return null;
    }

    /**
     * 공모전 접수내역 조회
     * Title : cntstRcptHistView
     * Description : 공모전 접수내역 조회
     *
     * @param aplyid
     * @param model
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistView.html")
    public String cntstRcptHistView(Integer aplyid, ModelMap model) {
        CntstRcptHistVO cntstRcptHistVO = cntstRcptHistService.selectCntstRcptHistInfo(aplyid);
        model.addAttribute("cntstRcptHistVO", cntstRcptHistVO);
        return "mng/prtpn/cntstRcptHist/cntstRcptHistDetail";
    }


}
