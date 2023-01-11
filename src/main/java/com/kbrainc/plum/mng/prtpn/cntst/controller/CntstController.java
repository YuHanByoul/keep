package com.kbrainc.plum.mng.prtpn.cntst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공모전 등록 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.controller
 * - CntstController.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstController
 * @Description : 공모전 등록 컨트롤러
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller
public class CntstController {

    /**
     * 공모전 접수내역
     공모전 접수내역
     * Description : TODO [메소드설명]
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
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/selectCntstRcptHistList.do")
    public String selectCntstRcptHistList() {
        return "";
    }

    /**
     * 공모전 접수내역 목록 엑셀 다운
     * Title : cntstRcptHistListExcelDown
     * Description : 공모전 접수내역 목록 엑셀 다운
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistListExcelDown.do")
    public String cntstRcptHistListExcelDown() {
        return "";
    }

    /**
     * 공모전 접수내역 조회
     * Title : cntstRcptHistView
     * Description : 공모전 접수내역 조회
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntstRcptHist/cntstRcptHistView.html")
    public String cntstRcptHistView() {
        return "mng/prtpn/cntstRcptHist/cntstRcptHistDetail";
    }


}
