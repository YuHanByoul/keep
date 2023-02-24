package com.kbrainc.plum.cmm.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
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
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.model.DrmncyInfoVo;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.SiteInfo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;

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
    
    @Value("${system.admin.tha.roleid}")
    private String sysAdminThaRoleid;
    
    @Value("${system.admin.ass.roleid}")
    private String sysAdminAssRoleid;

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
    public ModelAndView index(HttpServletRequest request, HttpSession session, @UserInfo UserVo user, @SiteInfo SiteInfoVo site, @RequestParam(required=false) boolean error, @RequestParam(required=false) boolean timeout, @RequestParam(required=false) String pwd, @RequestParam(required=false) String alertMsg) {
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
            
            StringBuffer alertMessageBuffer = new StringBuffer("");
            
            if (alertMsg != null) {
                if (pwd != null) {
                    alertMessageBuffer.append("&");
                } else {
                    alertMessageBuffer.append("?");
                }
                alertMessageBuffer.append("alertMsg=").append(alertMsg);
            }
            
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(request.getContextPath() + securityProperties.getDEFAULT_TARGET_URL() + (pwd != null ? "?pwd" : "") + alertMessageBuffer.toString());
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
            if (sysAdminThaRoleid.equals(user.getRoleInfo().getRoleid())) {
                return "mng/mainTha";
            } else if (sysAdminAssRoleid.equals(user.getRoleInfo().getRoleid())) {
                return "mng/mainAss";
            } else {
                return "mng/main";
            }
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

    
}