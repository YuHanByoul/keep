package com.kbrainc.plum.cmm.esylgn.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.cmm.esylgn.service.EsylgnService;
import com.kbrainc.plum.front.member.model.MemberAgreVo;
import com.kbrainc.plum.front.member.model.MemberParamVo;
import com.kbrainc.plum.front.member.model.MemberTypeVo;
import com.kbrainc.plum.front.member.service.MemberService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.security.OnepassUsernameUserkeyAuthenticationToken;
import com.kbrainc.plum.rte.util.CommonUtil;

import kr.go.onepass.client.dto.api.send.OnepassUserResponse;
import kr.go.onepass.client.dto.api.send.OnepassUserResponse.USER_STATUS;
import kr.go.onepass.client.dto.saml.OnepassResponse;
import kr.go.onepass.client.handler.api.ApiSendHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.RESULT_CODE;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.STATUS;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.TYPE;
import kr.go.onepass.client.utils.AESHandler;

/**
 * 
 * 간편로그인 관련 요청을 처리하는 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.cmm.esylgn.controller - EsylgnController.java
 * </pre>
 *
 * @ClassName : EsylgnController
 * @Description : 간편로그인 관련 요청을 처리하는 컨트롤러.
 * @author : KBRAINC
 * @date : 2023. 1. 31.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class EsylgnController {

    @Autowired
    private EsylgnService esylgnService;
    
    @Resource(name = "front.memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name="ariaCryptoService")
    EgovCryptoService cryptoService;
    
    @Value("${crypto.key}")
    private String cryptoKey;

    /**
    * 디지털원패스 연동/연동해제 화면.
    *
    * @Title       : onepassLinkUnLink 
    * @Description : 디지털원패스 연동/연동해제 화면.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/esylgn/onepassLinkUnLink.html")
    public String onepassLinkUnLink() throws Exception {
        return "front/esylgn/onepassLinkUnLink";
    }
    
    /**
     * 디지털원패스 로그인 팝업 화면(연동/연동해제를 위한 로그인).
     *
     * @Title       : onepassLoginPopup 
     * @Description : 디지털원패스 연동/연동해제 화면(연동/연동해제를 위한 로그인).
     * @return String 이동화면경로
     * @throws Exception 예외
     */
     @RequestMapping(value = {"/front/esylgn/onepassLinkPopup.html", "/front/esylgn/onepassUnlinkPopup.html"})
     public String onepassLoginPopup() throws Exception {
         return "front/esylgn/onepassLoginPopup";
     }
    
    /**
    * 디지털원패스 로그인후 응답URL 후처리.
    *
    * @Title : onepassAcs
    * @Description : 디지털원패스 로그인후 응답URL 후처리.
    * @param request 요청객체
    * @param response 응답객체
    * @param session 세션객체
    * @param user 사용자세션정보
    * @param redirect 리다이렉트속성객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping("/onepass/acs.html")
    public String onepassAcs(HttpServletRequest request, HttpServletResponse response, HttpSession session, @UserInfo UserVo user, RedirectAttributes redirect) throws Exception {
        ModelAndView mav = new ModelAndView();
        OnepassResponse onepassResponse = OnepassResponseHandler.check(request);
        String returnUrl = request.getParameter("returnUrl");
        PrintWriter writer = null;
        
        if (onepassResponse.getStatus() == STATUS.SUCCESS  && onepassResponse.getResultCode() == RESULT_CODE.SUCCESS) {
            if (onepassResponse.getType() == TYPE.LOGIN) {
                String userKey = onepassResponse.getUserKey();
                String intfToken = onepassResponse.getIntfToken();
                
                if ("/front/esylgn/onepassUnlinkPopup.html".equals(returnUrl)) { // 연동해제
                    if (user == null) {
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('세션이 유효하지 않습니다.\\n로그인 화면으로 이동합니다.');try{opener.location.href='/login.html';}catch(e){}window.close();</script>");
                        return null;
                    }
                    
                    EsylgnVo esylgnVo = null;
                    
                    // 내 계정과 연결된 userKey 조회
                    esylgnVo = new EsylgnVo();
                    esylgnVo.setUserid(user.getUserid());
                    esylgnVo.setEsylgnCd("106101");
                    String currentUserKey = esylgnService.selectEsylgnUserkeyByUserid(esylgnVo);
                    
                    if (currentUserKey == null) { // 내 계정과 연결된 userKey가 없으면
                       response.setContentType("text/html;charset=UTF-8");
                       writer = response.getWriter();
                       writer.print("<script>alert('연동된 디지털원패스 계정이 존재하지 않습니다.');try{opener.location.reload();}catch(e){}window.close();</script>");
                       user.setOnepassLinkYn("N");
                       return null;
                    }
                    
                    if (!currentUserKey.equals(userKey)) { // 로그인한 디지털원패스 계정의 userKey가 DB에 저장된 나의 userkey와 다르면 
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('현재 연동된 디지털원패스 계정이 아닙니다.');window.close();</script>");
                        return null;
                    }
                    
                    // 간편로그인 사용자 인지, 다른 간편로그인을 사용중인지 확인(user의 acnt가 존재하는지로 체크)
                    esylgnVo = new EsylgnVo();
                    esylgnVo.setUserid(user.getUserid());
                    esylgnVo.setEsylgnCd("106101");
                    EsylgnVo esylgnAddInfo = esylgnService.selectEsylgnAdditionalUserInfo(esylgnVo);
                    String acnt = esylgnAddInfo.getAcnt();
                    String onepassLinkYn = esylgnAddInfo.getOnepassLinkYn();
                    int esylgnLinkCnt = esylgnAddInfo.getEsylgnLinkCnt();
                    
                    if (acnt != null || "Y".equals(onepassLinkYn) && esylgnLinkCnt > 1) {
                        // 디지털 원패스 간편로그인 정보 DB 삭제 처리
                        esylgnVo = new EsylgnVo();
                        esylgnVo.setUserid(user.getUserid());
                        esylgnVo.setEsylgnCd("106101");
                        esylgnService.deleteEsylgnByUseridAndEsylgnCd(esylgnVo);
                        ApiSendHandler apiSendHandler = new ApiSendHandler();
                        OnepassUserResponse onepassUser = apiSendHandler.InterLockRelease(userKey, intfToken);
                        if (onepassUser == null) {
                            onepassUser = new OnepassUserResponse();
                        }
                        onepassUser.getProcess_result();
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('디지털원패스 연동이 해지되었습니다.');try{opener.location.reload();}catch(e){}window.close();</script>");
                        user.setOnepassLinkYn("N");
                        return null;
                    }
                    
                    if ("Y".equals(onepassLinkYn) && esylgnLinkCnt == 1) {
                        // 회원탈퇴처리(모든 간편로그인 정보도 함께 삭제, 세션무효화)
                        memberService.withdrawalMember(user, session);
                        ApiSendHandler apiSendHandler = new ApiSendHandler();
                        OnepassUserResponse onepassUser = apiSendHandler.InterLockRelease(userKey, intfToken);
                        if (onepassUser == null) {
                            onepassUser = new OnepassUserResponse();
                        }
                        onepassUser.getProcess_result();
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('디지털원패스 연동이 해지되고 회원탈퇴처리 되셨습니다.');try{opener.location.href='/';}catch(e){}window.close();</script>");
                        return null;
                    }
                    
                } else if ("/front/esylgn/onepassLinkPopup.html".equals(returnUrl)) { // 연동
                    if (user == null) {
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('세션이 유효하지 않습니다.\\n로그인 화면으로 이동합니다.');try{opener.location.href='/login.html';}catch(e){}window.close();</script>");
                        return null;
                    }
                    
                    EsylgnVo esylgnVo = null;
                    
                    // 내 계정과 연결된 userKey 조회
                    esylgnVo = new EsylgnVo();
                    esylgnVo.setUserid(user.getUserid());
                    esylgnVo.setEsylgnCd("106101");
                    String currentUserKey = esylgnService.selectEsylgnUserkeyByUserid(esylgnVo);
                    
                    if (currentUserKey != null) { // 이미 내 계정과 연결된 userKey가 있으면
                       response.setContentType("text/html;charset=UTF-8");
                       writer = response.getWriter();
                       writer.print("<script>alert('이미 연동된 디지털원패스 계정이 존재합니다.');try{opener.location.reload();}catch(e){}window.close();</script>");
                       user.setOnepassLinkYn("Y");
                       return null;
                    }
                    
                    // userKey를 사용하는 userid 조회
                    esylgnVo = new EsylgnVo();
                    esylgnVo.setEsylgnCd("106101");
                    esylgnVo.setUserkey(userKey);
                    String userid = esylgnService.selectUseridByEsylgnUserkey(esylgnVo);
                    if (userid != null) {
                        if (!userid.equals(user.getUserid())) { // 다른사용자에 userKey가 연결되어있는 경우
                            response.setContentType("text/html;charset=UTF-8");
                            writer = response.getWriter();
                            writer.print("<script>alert('이미 다른 사용자계정과 연동된 디지털원패스 계정입니다.');window.close();</script>");
                            return null;
                        }
                    } else { // 등록되지않은 userKey
                        ApiSendHandler apiSendHandler = new ApiSendHandler();
                        
                        OnepassUserResponse onepassUser = apiSendHandler.findUser(userKey, intfToken);
                        if (onepassUser != null && onepassUser.getStatus() == USER_STATUS.USE) {
                            esylgnVo = new EsylgnVo();
                            esylgnVo.setCi(onepassUser.getCi());
                            EsylgnVo userInfo = esylgnService.selectUserInfoByCi(esylgnVo);
                            if (userInfo == null || user.getUserid().equals(userInfo.getUserid())) { // 회원테이블에서 미사용인 ci이거나 나의 ci일때만 연동 가능
                                esylgnVo = new EsylgnVo();
                                esylgnVo.setUserid(user.getUserid());
                                esylgnVo.setEsylgnCd("106101");
                                esylgnVo.setUserkey(userKey);                                
                                esylgnService.insertEsylgnUserkey(esylgnVo);
                                response.setContentType("text/html;charset=UTF-8");
                                writer = response.getWriter();
                                writer.print("<script>alert('디지털원패스 계정과 연동에 성공하였습니다.');try{opener.location.reload();}catch(e){}window.close();</script>");
                                user.setOnepassLinkYn("Y");
                                return null;
                            } else {
                                response.setContentType("text/html;charset=UTF-8");
                                writer = response.getWriter();
                                writer.print("<script>alert('다른 회원의 디지털원패스 계정입니다.');window.close();</script>");
                                return null;
                            }
                        } else {
                            response.setContentType("text/html;charset=UTF-8");
                            writer = response.getWriter();
                            writer.print("<script>alert('디지털원패스 정보조회에 실패하였습니다.\\n잠시후 다시 시도해주십시오.');window.close();</script>");
                            return null;
                        }
                    }
                } else { // 로그인
                    ApiSendHandler apiSendHandler = new ApiSendHandler();
                    
                    OnepassUserResponse onepassUser = apiSendHandler.findUser(userKey, intfToken);
                    if (onepassUser != null && onepassUser.getStatus() == USER_STATUS.USE) {
                        String ci = onepassUser.getCi();
                        String email = onepassUser.getEmail();
                        String name = onepassUser.getName();
                        String birth = onepassUser.getBirth();
                        
                        EsylgnVo esylgnVo = null;

                        // 1. userKey일치하면 로그인 처리
                        esylgnVo = new EsylgnVo();
                        esylgnVo.setEsylgnCd("106101");
                        esylgnVo.setUserkey(userKey);
                        String userid = esylgnService.selectUseridByEsylgnUserkey(esylgnVo);
                        if (userid != null) {
                            onepassLogin(request, response, onepassUser.getId(), userKey);
                            return null;
                        }
                        
                        // 2. ci만 일치하면 userKey연결후 로그인 처리
                        esylgnVo = new EsylgnVo();
                        esylgnVo.setCi(ci);
                        EsylgnVo userInfo = esylgnService.selectUserInfoByCi(esylgnVo);
                        if (userInfo != null) {
                            esylgnVo = new EsylgnVo();
                            esylgnVo.setUserid(userInfo.getUserid());
                            esylgnVo.setEsylgnCd("106101");
                            esylgnVo.setUserkey(userKey);
                            esylgnService.mergeEsylgnUserkey(esylgnVo);
                            request.setAttribute("alertMessage", "아이디 " + userInfo.getAcnt() + " 계정과 디지털원패스의 계정이 연동되었습니다.");
                            onepassLogin(request, response, onepassUser.getId(), userKey);
                            return null;
                        }
            
                        // 3. 모두 일치하지 않을때(신규회원연동)
                        // 만나이 계산(어린이회원인지 확인하기위해)
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
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
                        
                        Map<String,Object> resultMap = new HashMap<>();
                        resultMap.put("ci", ci);
                        resultMap.put("email", email);
                        resultMap.put("name", name);
                        resultMap.put("userKey", userKey);
                        
                        if (manAge < 14) {
                            resultMap.put("type", "C"); // 어린이
                        }
                        
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonRes = gson.toJson(resultMap);
                        
                        // onepassUser(ci, email, name, userKey)/어린이회원여부 정보를 자체암호화해서 넣고 회원연동(회원가입)진행(회원가입 유형 선택(만14세 미만 자동으로 어린이 회원) -> 약관동의부터, 일반/기관회원은 본인인증은 건너뜀(어린이회원은 부모본인인증진행))
                        byte[] encrypted = cryptoService.encrypt(jsonRes.getBytes("UTF-8"), cryptoKey);
                        
                        CsrfTokenRepository csrfTokenRepository = ((CsrfTokenRepository)CommonUtil.getBean("csrfTokenRepository"));
                        CsrfToken token = csrfTokenRepository.generateToken(request);
                        csrfTokenRepository.saveToken(token, request, response);
                        //CsrfToken token = csrfTokenRepository.loadToken(request);
                        
                        // 회원가입 리다이렉션(post)
                        if (manAge < 14) { // 어린이 회원 -> 약관동의부터진행
                            StringBuffer sb = new StringBuffer();
                            sb.append("<form name='typeForm' action='/front/membership/step2.html' method='post'>");
                            sb.append("<input type='hidden' name='_csrf' value='").append(token.getToken()).append("'>");
                            sb.append("<input type='hidden' name='type' value='").append("C").append("'>");
                            sb.append("<input type='hidden' name='onepassEncodeData' value='").append(Base64.getEncoder().encodeToString(encrypted)).append("'>");
                            sb.append("<input type='hidden' name='returnUrl' value='").append(request.getParameter("returnUrl").split("::")[0]).append("'>");
                            sb.append("</form>");
                            sb.append("<script>");
                            sb.append("document.typeForm.submit();");
                            sb.append("</script>");
                            response.setContentType("text/html;charset=UTF-8");
                            writer = response.getWriter();
                            writer.print(sb.toString());
                            return null;
                        } else { // 회원유형 선택부터
                            StringBuffer sb = new StringBuffer();
                            sb.append("<form name='typeForm' action='/front/membership/step1.html' method='post'>");
                            sb.append("<input type='hidden' name='_csrf' value='").append(token.getToken()).append("'>");
                            sb.append("<input type='hidden' name='onepassEncodeData' value='").append(Base64.getEncoder().encodeToString(encrypted)).append("'>");
                            sb.append("<input type='hidden' name='returnUrl' value='").append(request.getParameter("returnUrl").split("::")[0]).append("'>");
                            sb.append("</form>");
                            sb.append("<script>");
                            sb.append("document.typeForm.submit();");
                            sb.append("</script>");
                            response.setContentType("text/html;charset=UTF-8");
                            writer = response.getWriter();
                            writer.print(sb.toString());
                            return null;
                        }                        
                    } else { // 정보조회 오류, 로그인 불가
                        response.setContentType("text/html;charset=UTF-8");
                        writer = response.getWriter();
                        writer.print("<script>alert('디지털원패스 정보조회에 실패하였습니다.\\n잠시후 다시 시도해주십시오.');location.href='/';</script>");
                        return null;
                    }
                }     
            } else if(onepassResponse.getType() == TYPE.LOGOUT) {
                OnepassResponseHandler.onepassLogout(request, response);
            }
        }

        return null;
    }
    
    /**
    * 디지털원패스 로그인으로 들어왔을때 로그인 처리.
    *
    * @Title : onepassLogin
    * @Description : 디지털원패스 로그인으로 들어왔을때 로그인 처리.
    * @param request 요청객체
    * @param response 응답객체
    * @param loginid 디지털원패스 로그인계정
    * @param userKey 디지털원패스 사용자키
    * @return void 리턴값 없음
    * @throws Exception 예외
    */
    public void onepassLogin(HttpServletRequest request, HttpServletResponse response, String loginid, String userKey) throws Exception {
        OnepassUsernameUserkeyAuthenticationToken authReq = new OnepassUsernameUserkeyAuthenticationToken(loginid, userKey);
        try {
            AuthenticationManager authenticaltionManager = ((AuthenticationManager)CommonUtil.getBean("authenticationManagerBean"));
            Authentication auth = authenticaltionManager.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            SavedRequestAwareAuthenticationSuccessHandler successHandler = ((SavedRequestAwareAuthenticationSuccessHandler)CommonUtil.getBean("httpsLoginSuccessHandler"));
            successHandler.onAuthenticationSuccess(request, response, auth);
        } catch(AuthenticationException e) {
            SimpleUrlAuthenticationFailureHandler failureHandler = ((SimpleUrlAuthenticationFailureHandler)CommonUtil.getBean("customAuthFailureHandler"));
            failureHandler.onAuthenticationFailure(request, response, e);
        }
    }
    
    /**
    * 디지털원패스 사이트에서 서비스해지시 callbackURL/후처리.
    *
    * @Title : onepassAcs
    * @Description : 디지털원패스 사이트에서 서비스해지시 callbackURL/후처리.
    * @param request 요청객체
    * @return ModelAndView 모델뷰객체
    * @throws Exception 예외
    */
    @RequestMapping("/onepass/rcv.do")
    public ModelAndView onepassRcv(HttpServletRequest request) throws Exception {
        // 원패스로 부터 전달받은 USER_KEY
        String userKey = AESHandler.decrypt(request.getParameter("userKey"));
        EsylgnVo esylgnVo = null;
        
        if (!"".equals(userKey)) {
            esylgnVo = new EsylgnVo();
            esylgnVo.setUserkey(userKey);
            esylgnVo.setEsylgnCd("106101");
            EsylgnVo esylgnAddInfo = esylgnService.selectEsylgnAdditionalUserInfoByUserkey(esylgnVo);
            
            if (esylgnAddInfo != null) { 
                String acnt = esylgnAddInfo.getAcnt();
                int esylgnLinkCnt = esylgnAddInfo.getEsylgnLinkCnt();
                
                if (acnt != null || esylgnLinkCnt > 1) {
                    // 디지털 원패스 간편로그인 정보 DB 삭제 처리
                    esylgnVo = new EsylgnVo();
                    esylgnVo.setUserkey(userKey);
                    esylgnVo.setEsylgnCd("106101");
                    esylgnService.deleteEsylgnByUserkeyAndEsylgnCd(esylgnVo);
                    return null;
                }
                
                if (esylgnLinkCnt == 1) {
                    // 회원탈퇴처리(모든 간편로그인 정보도 함께 삭제)
                    UserVo user = new UserVo();
                    user.setUserid(esylgnAddInfo.getUserid());
                    memberService.withdrawalMember(user, null);
                    return null;
                }
            }
        }
        
        return null;
    }
}