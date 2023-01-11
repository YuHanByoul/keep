package com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 공모전 접수 내역 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller
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

    /**
     * 공모전 등록
     * Title : cntstListForm
     * Description : 공모전 등록
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstListForm.html")
    public String cntstListForm(Model model, HttpServletRequest request) throws Exception {
        return "mng/prtpn/cntst/cntstList";
    }

    /**
     * 공모전 목록 조회
     * Title : selectCntstList
     * Description : 공모전 목록 조회
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/selectCntstList.do")
    @ResponseBody
    public String selectCntstList(Model model, HttpServletRequest request) throws Exception {
        return "";
    }

    /**
     * 공모전 등록 화면
     * Title : cntstInsertForm
     * Description : 공모전 등록 화면
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstInsertForm.html")
    public String cntstInsertForm(Model model, HttpServletRequest request) throws Exception {
        return "mng/prtpn/cntst/cntstForm";
    }


    /**
     * 공모전 수정 화면
     * Title : cntstUpdateForm
     * Description : 공모전 수정 화면
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstUpdateForm.html")
    public String cntstUpdateForm(Model model, HttpServletRequest request) throws Exception {
        return "mng/prtpn/cntst/cntstUpdate";
    }

    /**
     * 공모전 등록 기능
     * Title : insertCntst
     * Description : 공모전 등록 기능
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/insertCntst.do")
    @ResponseBody
    public String insertCntst(Model model, HttpServletRequest request) throws Exception {
        return "";
    }

    /**
     * 공모전 수정 기능
     * Title : updateCntst
     * Description : 공모전 수정 기능
     *
     * @param model
     * @param request
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/prtpn/cntst/updateCntst.do")
    @ResponseBody
    public String updateCntst(Model model, HttpServletRequest request) throws Exception {
        return "";
    }
}
