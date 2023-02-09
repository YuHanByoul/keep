package com.kbrainc.plum.cmm.idntyVrfctn.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnFailVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnStartVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.cmm.idntyVrfctn.service.IdntyVrfctnService;

/**
 * 
 * 본인인증 관련 요청을 처리하는 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.cmm.idntyVrfctn.controller - IdntyVrfctnController.java
 * </pre>
 *
 * @ClassName : IdntyVrfctnController
 * @Description : 본인인증 관련 요청을 처리하는 컨트롤러.
 * @author : KBRAINC
 * @date : 2023. 2. 7.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class IdntyVrfctnController {

    @Autowired
    private IdntyVrfctnService idntyVrfctnService;
    
    
    
    /**
    * 휴대폰 본인인증 모달을 띄운다.
    *
    * @Title : checkplus
    * @Description : 휴대폰 본인인증 모달을 띄운다
    * @param request 요청객체
    * @param response 응답객체
    * @param session 세션객체
    * @return ModelAndView 모델뷰객체
    * @throws Exception 예외
    */
    @RequestMapping("/CheckPlusSafeModel/checkplus.html")
    public ModelAndView checkplus(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        // 본인인증 업체정보 검증
        String returnUrlPath = "/checkplus/success.html";      // 성공시 이동될 URL path
        String errorUrlPath = "/checkplus/fail.html";          // 실패시 이동될 URL path
        
        IdntyVrfctnStartVo result = idntyVrfctnService.siteInfoVerification(request, returnUrlPath, errorUrlPath);
        
        if (!"".equals(result.getSMessage())) { // 본인인증모듈 업체정보 검증 실패
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + result.getSMessage() + "');window.close();</script>");
            return null;
        }
        
        StringBuffer sb = new StringBuffer();
        sb.append("<form name='form_chk' action='https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb' method='post'>");
        sb.append("<input type='hidden' name='m' value='checkplusService'>");
        sb.append("<input type='hidden' name='EncodeData' value='");
        sb.append(result.getSEncData());
        sb.append("'></form>");
        sb.append("<script>");
        sb.append("document.form_chk.submit();");
        sb.append("</script>");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(sb.toString());
        return null;
    }
    
    /**
    * 휴대폰 본인인증 후 URL 후처리.
    *
    * @Title : checkplusSuccess
    * @Description : 휴대폰 본인인증 후 URL 후처리.
    * @param request 요청객체
    * @param response 응답객체
    * @param session 세션객체
    * @return ModelAndView 모델뷰객체
    * @throws Exception 예외
    */
    @RequestMapping("/checkplus/success.html")
    public ModelAndView checkplusSuccess(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String encodeData = request.getParameter("EncodeData");
        IdntyVrfctnSuccessVo result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(session, encodeData);
        
        if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + result.getSMessage() + "');window.close();</script>");
            return null;
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('본인인증에 성공하였습니다.');try{opener.setEncodeData('" + encodeData + "');}catch(e){}window.close();</script>");
            return null;
        }
    }
    
    /**
    * 휴대폰 본인인증 후 error URL 후처리.
    *
    * @Title : checkplusFail
    * @Description : 휴대폰 본인인증 후 error URL 후처리.
    * @param request 요청객체
    * @param response 응답객체
    * @return ModelAndView 모델뷰객체
    * @throws Exception 예외
    */
    @RequestMapping("/checkplus/fail.html")
    public ModelAndView checkplusFail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String encodeData = request.getParameter("EncodeData");
        IdntyVrfctnFailVo result = idntyVrfctnService.decodeIdntyVrfctnFailData(encodeData);
        
        if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + result.getSMessage() + "');window.close();</script>");
            return null;
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('본인인증에 실패하였습니다. 에러코드(" + result.getSErrorCode() + ")');window.close();</script>");
            return null;
        }
    }
}