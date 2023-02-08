package com.kbrainc.plum.mng.bizAply.bizRpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.mng.bizAply.bizRpt.service.BizRptServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
* 사업보고관리 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.controller
* - BizRptController.java
* </pre>
*
* @ClassName : BizRptController
* @Description : 사업보고관리 컨트롤러 클래스.
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
@Slf4j
public class BizRptController {

	@Autowired
    private BizRptServiceImpl bizRptService;

	/*******************************************************************************
	 중간보고관리
	********************************************************************************/
	/**
     * @Title : mdlRptMngList.html
     * @Description : 중간보고관리 목록 조회
     * @return String 이동화면경로
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/bizAply/bizRpt/mdlRptMngListForm.html")
    public String userTempListForm() throws Exception {
        return "mng/bizAply/bizRpt/mdlRptMngList.html";
    }
}
