package com.kbrainc.plum.cmm.idntyVrfctn.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
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
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.member.service.MemberService;

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
    
    @Resource(name = "front.memberServiceImpl")
    private MemberService memberService;
    
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
        boolean isMembership = false; // 회원가입을 위한 본인인증 인지
        String type = ""; // 회원가입유형(P:개인회원, C:어린이회원, I:기관회원)
        String kind = ""; // 본인인증종류(auth:회원가입_본인인증, info:회원가입_회원정보입력)
        IdntyVrfctnSuccessVo result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(session, encodeData);
        
        String session_sRequestNumber =  (String)session.getAttribute("REQ_SEQ");
        if (session_sRequestNumber != null) {
            if (session_sRequestNumber.startsWith("P_") || session_sRequestNumber.startsWith("C_") || session_sRequestNumber.startsWith("I_")) {
                type = session_sRequestNumber.substring(0, 1);
                isMembership = true;
            }
            if (session_sRequestNumber.endsWith("_auth")) {
                kind = "auth";
            } else if (session_sRequestNumber.endsWith("_info")) {
                kind = "info";
            }
        }
        if (isMembership && "auth".equals(kind)) { // 회원가입 본인인증단계에서 본인인증시
            String birth = result.getSBirthDate();
            
            // 만나이 계산(어린이회원인지 확인하기위해)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = formatter.parse(birth);
            Calendar currentCal = Calendar.getInstance();
            Calendar brithCal = Calendar.getInstance();
            
            brithCal.setTime(birthDate);
            int currentYear = currentCal.get(Calendar.YEAR);
            int currentMonth = currentCal.get(Calendar.MONTH);
            int currentDay = currentCal.get(Calendar.DATE);
            int birthYear = brithCal.get(Calendar.YEAR);
            int birthMonth = brithCal.get(Calendar.MONTH);
            int birthDay = brithCal.get(Calendar.DATE);

            int manAge = currentYear - birthYear;
            
            if (currentMonth < birthMonth) {
                manAge--;
            } else if (currentMonth == birthMonth && currentDay < birthDay) {
                manAge--;
            }
            
            if (manAge < 14) {
                if ("C".equals(type)) { // 어린이회원 가입인데 14세 미만의 휴대폰으로 본인인증 시
                    result.setSMessage("법정대리인의 휴대폰 인증을 진행해주십시오.");
                } else { // 개인/기관회원 가입인데 
                    result.setSMessage("어린이회원으로 가입해주세요.");
                }
            }
        }
        
        if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print("<script>alert('" + result.getSMessage() + "');window.close();</script>");
            return null;
        } else {
            if (isMembership && ((!"C".equals(type) && "auth".equals(kind)) || "info".equals(kind))) {
                // CI값이 동일한 회원이 있는지 확인한다.
                MemberVo memberVo = new MemberVo();
                memberVo.setCi(result.getSConnInfo());
                String userid = memberService.selectUseridByCI(memberVo);
                if (userid != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print("<script>alert('회원정보가 존재합니다.\\n아이디 찾기로 확인해주시기 바랍니다.');window.close();</script>");
                    return null;
                }
            }
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