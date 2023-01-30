package com.kbrainc.plum.cmm.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.config.security.properties.SecurityProperties;
import com.kbrainc.plum.front.bbs.model.PstVo;
import com.kbrainc.plum.front.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.front.member.model.EsylgnVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.DrmncyInfoVo;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.security.OnepassUsernameUserkeyAuthenticationToken;
import com.kbrainc.plum.rte.util.CommonUtil;

import kr.go.onepass.client.dto.api.send.OnepassUserResponse;
import kr.go.onepass.client.dto.saml.OnepassResponse;
import kr.go.onepass.client.handler.api.ApiSendHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.RESULT_CODE;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.STATUS;
import kr.go.onepass.client.handler.saml.OnepassResponseHandler.TYPE;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하는 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.cmm.controller - CommonController.java
 * </pre>
 *
 * @ClassName : CommonController
 * @Description : 어플리케이션 전체의 공통 요청을 처리하는 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Value("${server.port}")
    private String serverHttpsPort;

    @Autowired
    private SecurityProperties securityProperties;
    
    @Autowired
    private BbsServiceImpl bbsService;
    
    /**
    * 인덱스.
    *
    * @Title : index
    * @Description : 인덱스
    * @param request 요청객체
    * @param session 세션객체
    * @param user 사용자세션정보
    * @param site 사이트세션정보
    * @param error error요청파라미터 존재여부
    * @param timeout timeout요청파라미터 존재여부
    * @param pwd pwd요청파라미터 존재여부
    * @return ModelAndView 모델뷰객체
    */
    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpSession session, @UserInfo UserVo user, @SiteInfo SiteInfoVo site, @RequestParam(required=false) boolean error, @RequestParam(required=false) boolean timeout, @RequestParam(required=false) String pwd) {
        ModelAndView mav = new ModelAndView();

        DrmncyInfoVo drmncyInfo = (DrmncyInfoVo) session.getAttribute("drmncy");
        if (drmncyInfo != null) {
            if(drmncyInfo.isUsed()) {
                session.removeAttribute("drmncy"); // 휴면회원전환 레이어를 한번만 띄우기 위해서
            }
            drmncyInfo.setUsed(true);
        }
        
        if (user != null) {
            /*String serverHttpPortStr = "";
            if (!"443".equals(serverHttpsPort)) {
                serverHttpPortStr = ":" + serverHttpsPort;
            }*/
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(request.getContextPath() + securityProperties.getDEFAULT_TARGET_URL() + (pwd != null ? "?pwd" : ""));
            redirectView.setExposeModelAttributes(false); // 화면으로 이동시 model데이터가 query string으로 붙는것을 막는다.
            mav.setView(redirectView);
            return mav;
        }   
        
        String sysSeCd = site.getSysSeCd();
        
        if ("A".equals(sysSeCd)) { // 관리자 사이트
            mav.setViewName("mng/login");
        } else { // 사용자 사이트
            if (timeout) {
                if (CommonUtil.portalUrl.equals(request.getRequestURL().toString())) {
                    mav.addObject("timeout", "true");
                    mav.setViewName("front/login");  
                } else {
                    RedirectView redirectView = new RedirectView();
                    redirectView.setUrl(CommonUtil.portalUrl + "?timeout=true");
                    redirectView.setExposeModelAttributes(false); // 화면으로 이동시 model데이터가 query string으로 붙는것을 막는다.
                    mav.setView(redirectView);
                    return mav;
                }
            } else if (error) {
                mav.addObject("error", "true");
                mav.setViewName("front/login");
            } else {
                PstVo pstVo =new PstVo();
                
                pstVo.setUser(user);
                pstVo.setRowPerPage(5);
                pstVo.setBbsid(10);
                try {
                    List<PstVo> list= bbsService.selectPstList(pstVo);
                    mav.addObject("list", list);
                }catch(SQLException e){
                    mav.addObject("list", null);
                }catch(Exception e){
                    mav.addObject("list", null);
                }
                
                mav.setViewName("front/main");
            }
        }
        
        return mav;
    }
    
    /**
    * 중복로그인시 호출되는 핸들러.
    *
    * @Title       : dupLogout 
    * @Description : 화면 이동후 중복로그인 alert노출 후 로그아웃 처리를 한다.
    * @return String 이동화면경로
    */
    @GetMapping("/dupLogout")
    public String dupLogout() {
        return "dupLogout";
    }
    
    /**
    * 메인 화면으로 이동한다.
    *
    * @Title       : main 
    * @Description : 메인 화면으로 이동한다.
    * @return String 이동화면경로
    */
    @GetMapping("/main.html")
    public String mainPage(Model model, PstVo pstVo, @UserInfo UserVo user, @SiteInfo SiteInfoVo site, HttpSession session) {
        String sysSeCd = site.getSysSeCd();
        
        if ("A".equals(sysSeCd)) { // 관리자 사이트
            return "mng/main";
        } else { // 사용자 사이트
            pstVo.setUser(user);
            pstVo.setRowPerPage(5);
            pstVo.setBbsid(10);
            try {
                List<PstVo> list= bbsService.selectPstList(pstVo);
                model.addAttribute("list", list);
            }catch(SQLException e){
                model.addAttribute("list", null);
            }catch(Exception e){
                model.addAttribute("list", null);
            }
            
            return "front/main";
        }
    }
    
    /**
    * 로그인 화면으로 이동한다.
    *
    * @Title : login
    * @Description : 로그인 화면으로 이동한다.
    * @param request 요청객체
    * @param session 세션객체
    * @param user 사용자세션정보
    * @return ModelAndView 모델객체
    */
    @GetMapping("/login.html")
    public ModelAndView login(HttpServletRequest request, HttpSession session, @UserInfo UserVo user) {
        ModelAndView mav = new ModelAndView();
        
        if (user != null) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(request.getContextPath() + securityProperties.getDEFAULT_TARGET_URL());
            redirectView.setExposeModelAttributes(false); // 화면으로 이동시 model데이터가 query string으로 붙는것을 막는다.
            mav.setView(redirectView);
            return mav;
        }
        
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        String sysSeCd = siteInfo.getSysSeCd();
        
        if ("A".equals(sysSeCd)) { // 관리자 사이트
            mav.setViewName("mng/login");
        } else { // 사용자 사이트
            mav.setViewName("front/login");
        }
        return mav;
    }

    /**
    * 로그인한 사용자의 다른 역할로 변경한다.
    *
    * @Title       : changeRole 
    * @Description : 로그인한 사용자의 다른 역할로 변경한다.
    * @param afterRoleid 변경할 roleid
    * @param request 요청객체
    * @param session 세션객체
    * @return ModelAndView 모델뷰객체
    */
    @GetMapping("/cmm/changeRole.do")
    public ModelAndView changeRole(@RequestParam String afterRoleid, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        UserVo user = (UserVo) session.getAttribute("user");

        if (user != null) {
            // 역할을 변경한다.
            for (Map<String, String> authority : user.getAuthorities()) {
                if (afterRoleid.equals(authority.get("roleid"))) {
                    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(afterRoleid));
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
                    RoleInfoVo roleInfo = new RoleInfoVo((String)authority.get("roleid"), (String)authority.get("nm"), (String)authority.get("knd_cd"), (String)authority.get("se_cd"), (String)authority.get("trgt_inst_cd"), (String)authority.get("trgt_rgn_cd"));
                    user.setRoleInfo(roleInfo);
                    break;
                }
            }

            // 메인화면으로 이동한다.
            String serverHttpPortStr = "";
            if (!"443".equals(serverHttpsPort)) {
                serverHttpPortStr = ":" + serverHttpsPort;
            }
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(securityProperties.getDEFAULT_TARGET_URL());
            redirectView.setExposeModelAttributes(false); // 화면으로 이동시 model데이터가 query string으로 붙는것을 막는다.
            mav.setView(redirectView);
            return mav;
        }
        mav.setViewName("login");
        return mav;
    }

    /**
    * 사이트 리스트를 반환한다.
    *
    * @Title       : getSiteList 
    * @Description : 사이트 리스트를 반환한다.
    * @param site SiteVo객체
    * @return List<SiteVo> 사이트정보 목록
    * @throws Exception 예외
    */
    @RequestMapping(value = "/cmm/getSiteList.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<SiteVo> getSiteList(SiteVo site) throws Exception {
        return commonService.selectSiteList(site);
    }

    /**
    * 디지털원패스 로그인후 응답URL 후처리.
    *
    * @Title : onepassAcs
    * @Description : 디지털원패스 로그인후 응답URL 후처리.
    * @param request 요청객체
    * @param response 응답객체
    * @return ModelAndView 모델뷰객체
    * @throws Exception 예외
    */
    @RequestMapping("/onepass/acs.html")
    public ModelAndView onepassAcs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        
        OnepassResponse onepassResponse = OnepassResponseHandler.check(request);
        
        if (onepassResponse.getStatus() == STATUS.SUCCESS  && onepassResponse.getResultCode() == RESULT_CODE.SUCCESS) {
            if (onepassResponse.getType() == TYPE.LOGIN) {
                String userKey = onepassResponse.getUserKey();
                String intfToken = onepassResponse.getIntfToken();

                OnepassResponseHandler.onepassLogin(response, userKey, intfToken);
                ApiSendHandler apiSendHandler = new ApiSendHandler();
                
                OnepassUserResponse onepassUser = apiSendHandler.findUser(userKey, intfToken);
                if (onepassUser == null) {
                  onepassUser = new OnepassUserResponse();
                }
                
                String ci = onepassUser.getCi();
                String email = onepassUser.getEmail();
                String name = onepassUser.getName();
                String birth = onepassUser.getBirth();
                
                EsylgnVo esylgnVo = new EsylgnVo();
                esylgnVo.setEsylgnCd("106101");
                esylgnVo.setUserkey(userKey);
                esylgnVo.setCi(ci);
                
                String userid = null;
                // 1. userKey일치하면 로그인 처리
                userid = commonService.selectUseridByEsylgnUserkey(esylgnVo);
                if (userid != null) {
                    onepassLogin(request, response, onepassUser.getId(), userKey);
                    return null;
                }
                
                // 2. ci만 일치하면 userKey연결후 로그인 처리
                userid = commonService.selectUseridByCi(esylgnVo);
                if (userid != null) {
                    esylgnVo.setUserid(userid);
                    commonService.updateEsylgnUserkey(esylgnVo);
                    onepassLogin(request, response, onepassUser.getId(), userKey);
                    return null;
                }

                // 3. 모두 일치하지 않을때 onepassUser(ci, email, name, userKey, 어린이회원여부)정보를 세션에 넣고 회원연동(회원가입)진행(회원가입 유형 선택(만14세 미만 자동으로 어린이 회원) -> 약관동의부터, 일반/기관회원은 본인인증은 건너뜀(어린이회원은 부모본인인증진행), 회원정보저장시 세션에 정보없으면 진행불가)
                // 만나이 계산
                /*SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
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
                    System.out.println("어린이 회원");
                }*/
                
                // 리다이렉션
                
            } else if(onepassResponse.getType() == TYPE.LOGOUT) {
                OnepassResponseHandler.onepassLogout(request, response);
            }
        }
        
        // returnUrl과 시큐리티세션에 저장된 url, defaultUrl 따져보아 리다이렉션 진행
        /*RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/login.html");
        redirectView.setExposeModelAttributes(false); // 화면으로 이동시 model데이터가 query string으로 붙는것을 막는다.
        mav.setView(redirectView);
        return mav;*/
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
}