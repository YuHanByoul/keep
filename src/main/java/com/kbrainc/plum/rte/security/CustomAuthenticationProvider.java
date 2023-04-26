package com.kbrainc.plum.rte.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.session.FindByIndexNameSessionRepository; // REDIS_SESSION
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;
import com.kbrainc.plum.cmm.idntyVrfctn.service.IdntyVrfctnService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.DrmncyInfoVo;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.penta.scpdb.ScpDbAgent;

/**
 * 
 * 사용자인증 및 역할목록/세션정보를 셋팅한다.
 *
 * <pre>
 * com.kbrainc.plum.rte.security
 * - CustomAuthenticationProvider.java
 * </pre> 
 *
 * @ClassName : CustomAuthenticationProvider
 * @Description : 사용자인증 및 역할목록/세션정보를 셋팅한다.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecuredObjectService securedObjectService;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private IdntyVrfctnService idntyVrfctnService;
    
    @Value("${system.person.roleid}")
    private String sysPersonRoleid;
    
    @Value("${system.company.roleid}")
    private String sysCompanyRoleid;
    
    public Map<String,Integer> usernameNotFoundMap = new HashMap<String,Integer>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginid = "";
        String loginType= "G"; // 일반(G), 디지털원패스(D), SSO(S)
        String password = "";
        String userKey = "";
        String userid = null;
        String returnUrl = null;
        String loginUserType = null; // 개인회원(P), 기관회원(I)
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        
        
        
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            loginid = authentication.getName();
            password = (String) authentication.getCredentials();
            returnUrl = request.getParameter("returnUrl");
            loginUserType = request.getParameter("loginUserType");
            if (loginUserType == null) {
                loginUserType = "P";
            }
        } else if (authentication instanceof OnepassUsernameUserkeyAuthenticationToken) {
            loginType = "D";
            loginid = authentication.getName();
            userKey = (String) authentication.getCredentials();
            returnUrl = request.getParameter("returnUrl").split("::")[0];
            loginUserType = request.getParameter("returnUrl").split("::")[1];
        } else if (authentication instanceof SSOUseridLoginUserTypeAuthenticationToken) {
            loginType = "S";
            userid = authentication.getName();
            loginUserType = (String) authentication.getCredentials();
        }
        
        if ("G".equals(loginType)) {
            // 비밀번호 암호화
            try {
                if (System.getenv("PC_KIND") != null) {
                    ScpDbAgent agt = new ScpDbAgent();
                    password = agt.ScpHashStr(CommonUtil.damoScpIniFilePath, 73, new String(password.getBytes(), "UTF-8")).toLowerCase();
                } else {
                    password = Hex.encodeHexString(MessageDigest.getInstance("SHA-512").digest(password.getBytes("UTF-8")));
                }
                 // sha3-512 비밀번호 암호화
            } catch (NoSuchAlgorithmException e) {
                throw new InternalAuthenticationServiceException("Login Error !!");
            } catch (Exception e) {
                throw new InternalAuthenticationServiceException("Login Error !!");
            }
        }

        Map resultMap = null;
        List<Map<String, Object>> resultList = null;
        
        HttpSession session = request.getSession();
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        String sysSeCd = siteInfo.getSysSeCd();
        String siteid = siteInfo.getSiteid();        
        Integer instid = null;
        String siteaplyUseYn = null;
        request.setAttribute("loginid", loginid);
        request.setAttribute("returnUrl", returnUrl);
        request.setAttribute("loginUserType", loginUserType);
        request.setAttribute("loginType", loginType);
        
        if ("A".equals(sysSeCd)) { // 관리자 사이트
            try {
                if ("G".equals(loginType)) { // 아이디 비밀번호 방식
                    
                    String ci = null;
                    String moblphon = null;
                    boolean isAllowedIp = true;
                    if (!commonService.selectCertIpList().contains(CommonUtil.getClientIp(request))) { // 허가되지않은 IP에서 접속
                        isAllowedIp = false;
                        IdntyVrfctnSuccessVo result = null;
                        try {
                            result = idntyVrfctnService.decodeIdntyVrfctnSuccessData(null, request.getParameter("encodeData"));
                        } catch (Exception e) {
                            request.setAttribute("message", "본인인증 인코딩 실패. 고객센터에 문의 해주십시오.");
                            throw new InternalAuthenticationServiceException("Login Error !!");
                        }
                                   
                        if (!"".equals(result.getSMessage())) { // 본인인증모듈 인코딩 실패
                            request.setAttribute("message", result.getSMessage());
                            throw new InternalAuthenticationServiceException("Login Error !!");
                        } else {                
                            ci = result.getSConnInfo();
                            moblphon = result.getSMobileNo();
                        }
                    }
                    
                    resultMap = securedObjectService.selectUserLoginInfo(loginid); // 사용자 로그인 정보 조회
                    
                    if (!isAllowedIp) { // 허가되지않은 IP에서 접속
                        if (resultMap.get("CI") != null) {
                            if (!ci.equals(resultMap.get("CI"))) { // CI가 다르면
                                request.setAttribute("message", "로그인 계정과 본인인증 결과가 일치하지 않습니다.");
                                throw new InternalAuthenticationServiceException("Login Error !!");
                            }
                        } else { // 관리자에서 회원등록시 CI가 없으므로 휴대폰번호롤 비교
                            if (!moblphon.equals(resultMap.get("MOBLPHON"))) { // 휴대폰번호가 다르면
                                request.setAttribute("message", "로그인 계정과 본인인증 결과가 일치하지 않습니다.");
                                throw new InternalAuthenticationServiceException("Login Error !!");
                            }
                        }
                    }
                } else if ("S".equals(loginType)) { // SSO
                    resultMap = securedObjectService.selectUserLoginInfoForSSO(userid); // SSO 사용자 로그인 정보 조회
                    loginid = (String) resultMap.get("ACNT");
                } 
                
                if ("Y".equals((String) resultMap.get("ACNT_LOCK_YN"))) { // 계정이 잠겨있으면
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                    throw new LockedException("Login Error !!");
                }
                if ("N".equals((String) resultMap.get("ACNT_LOCK_YN")) && "G".equals(loginType) && !password.equals((String) resultMap.get("PSWD"))) {
                    commonService.insertLoginFail(request, String.valueOf(resultMap.get("USERID")));
                    Integer loginFailCnt = (Integer)resultMap.get("LGN_FAIL_CNT");                    
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    throw new BadCredentialsException("Login Error !!");
                }
                
                resultList = securedObjectService.selectGrantedAuthority(loginid); // 사용자에게 부여된 역할 목록 조회
                // 사용자 역할까지 부여
                instid = (Integer) resultMap.get("INSTID");
                siteaplyUseYn = (String) resultMap.get("SITEAPLY_USE_YN"); // 분양사이트 사용여부
                Map<String, Object> roleMap = null;
                roleMap = new HashMap<String, Object>();
                roleMap.put("ROLEID", sysPersonRoleid);
                roleMap.put("NM", "개인회원");
                roleMap.put("KND_CD", "U");
                roleMap.put("SE_CD", "U");
                resultList.add(roleMap);
            } catch (EmptyResultDataAccessException e) { // 존재하지않는 사용자일때
                Integer loginFailCnt = usernameNotFoundMap.get(loginid);
                if (loginFailCnt != null && loginFailCnt >= 5) {
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                } else {
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    usernameNotFoundMap.put(loginid, (Integer) data.get("loginFailCnt"));
                }
                throw new UsernameNotFoundException("Login Error !!");
            } catch (LockedException e) { // 계정이 잠긴 사용자일때
                throw new LockedException("Login Error !!");
            } catch (Exception e) { // 예외발생시
                throw new InternalAuthenticationServiceException("Login Error !!");
            }
        } else { // 사용자 사이트
            try {
                
                if ("G".equals(loginType)) { // 아이디 비밀번호 방식
                    resultMap = securedObjectService.selectUserLoginInfo(loginid); // 사용자 로그인 정보 조회
                } else if ("D".equals(loginType)) { // 디지털 원패스
                    resultMap = securedObjectService.selectUserLoginInfoForOnepass(userKey); // 디지털원패스 사용자 로그인 정보 조회
                } else if ("S".equals(loginType)) { // SSO
                    resultMap = securedObjectService.selectUserLoginInfoForSSO(userid); // SSO 사용자 로그인 정보 조회
                } 
                
                if ("101105".equals((String) resultMap.get("ACNT_LOCK_CD"))) { // 휴면계정 회원
                    if ("G".equals(loginType) && !password.equals((String) resultMap.get("PSWD"))) {
                        throw new BadCredentialsException("Login Error !!");
                    } else if (!"S".equals(loginType)) {
                        DrmncyInfoVo drmncyInfo = new DrmncyInfoVo();
                        drmncyInfo.setUserid(String.valueOf(resultMap.get("USERID")));
                        drmncyInfo.setMdfrid(String.valueOf(resultMap.get("USERID")));
                        drmncyInfo.setPrvcVldty((Integer) resultMap.get("PRVC_VLDTY"));
                        drmncyInfo.setRegDt((String) resultMap.get("DRMNCY_REG_DT"));
                        drmncyInfo.setUsed(false);
                        session.setAttribute("drmncy", drmncyInfo);
                        throw new LockedException("Login Error !!");
                    }
                } else if ("Y".equals((String) resultMap.get("ACNT_LOCK_YN"))) { // 계정이 잠겨있으면
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                    throw new LockedException("Login Error !!");
                }
                if ("N".equals((String) resultMap.get("ACNT_LOCK_YN")) && "G".equals(loginType) && !password.equals((String) resultMap.get("PSWD"))) {
                    commonService.insertLoginFail(request, String.valueOf(resultMap.get("USERID")));
                    Integer loginFailCnt = (Integer)resultMap.get("LGN_FAIL_CNT");
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    throw new BadCredentialsException("Login Error !!");
                }
                
                //resultList = securedObjectService.selectGrantedAuthority(loginid); // 사용자에게 부여된 관리자 역할 목록 조회
                instid = (Integer) resultMap.get("INSTID");
                Map<String, Object> roleMap = null;
                String instUseYn = (String) resultMap.get("INST_USE_YN"); // 기관사용여부
                String instAprvSttsCd = (String) resultMap.get("INST_APRV_STTS_CD"); // 기관승인상태코드(2: 승인)
                
                
                if (resultList == null) {
                    resultList = new ArrayList<Map<String, Object>>();
                }
                
                if ("I".equals(loginUserType) && instid != null) {
                    if ("N".equals(instUseYn) || !"2".equals(instAprvSttsCd)) { // 기관이 미사용이거나 승인상태가 아닌경우
                        request.setAttribute("message", "기관정보가 승인되지 않았습니다. 개인회원으로는 로그인이 가능합니다.");
                        throw new InternalAuthenticationServiceException("Login Error !!");
                    } else {
                        roleMap = new HashMap<String, Object>();
                        roleMap.put("ROLEID", sysCompanyRoleid);
                        roleMap.put("NM", "기관회원");
                        roleMap.put("KND_CD", "O");
                        roleMap.put("SE_CD", "U");
                        resultList.add(roleMap);
                    }
                } else if ("I".equals(loginUserType) && instid == null) {
                    // 회원은 맞으나 기관회원이 아님
                    request.setAttribute("message", "기관회원이 아닙니다. 개인회원으로 로그인 해주십시오.");
                    throw new InternalAuthenticationServiceException("Login Error !!");
                }
                
                // 개인회원 역할은 무조건 부여
                roleMap = new HashMap<String, Object>();
                roleMap.put("ROLEID", sysPersonRoleid);
                roleMap.put("NM", "개인회원");
                roleMap.put("KND_CD", "U");
                roleMap.put("SE_CD", "U");
                resultList.add(roleMap);
                
                if (resultMap.get("ACNT") != null) {
                    if (CommonUtil.isEmpty(resultMap.get("PSWD_MDFCN_DT"))) {
                        request.setAttribute("pswdChangeLayer", true);
                    } else {
                        Timestamp pswdMdfcnTimestamp = (Timestamp) resultMap.get("PSWD_MDFCN_DT");
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTime(pswdMdfcnTimestamp);
                        cal1.add(Calendar.DATE, 90);
                        
                        if (cal1.compareTo(cal2) <= 0) {
                            request.setAttribute("pswdChangeLayer", true);
                        }
                    }
                }

            } catch (EmptyResultDataAccessException e) { // 존재하지않는 사용자일때
                Integer loginFailCnt = usernameNotFoundMap.get(loginid);
                if (loginFailCnt != null && loginFailCnt >= 5) {
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                } else {
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    usernameNotFoundMap.put(loginid, (Integer) data.get("loginFailCnt"));
                }
                throw new UsernameNotFoundException("Login Error !!");
            } catch (Exception e) { // 예외발생시
                throw new InternalAuthenticationServiceException("Login Error !!");
            }
        }
                
        if (resultList.size() == 0) { // 사용자에게 부여된 역할이 없으면
            throw new InternalAuthenticationServiceException("Login Error !!");
        }

        UserVo user = new UserVo();
        user.setUserid(String.valueOf(resultMap.get("USERID")));
        user.setAcnt((String) resultMap.get("ACNT"));
        user.setName((String) resultMap.get("ACNT"));
        user.setNm((String) resultMap.get("NM"));
        user.setInstid((Integer) resultMap.get("INSTID"));
        user.setInstpicRoleCd((String) resultMap.get("INSTPIC_ROLE_CD"));
        user.setLoginUserType(loginUserType);
        user.setOnepassLinkYn((String) resultMap.get("ONEPASS_LINK_YN"));
        user.setEsylgnLinkCnt(Integer.parseInt(String.valueOf(resultMap.get("ESYLGN_LINK_CNT"))));
        user.setData(resultMap);
        
        if (resultMap.get("EXPRT_USERID") != null) {
            user.setUserType("E"); // 전문가
        } else if(resultMap.get("CI_PARNTS") != null) {
            user.setUserType("C"); // 어린이
        }
        
        if ("D".equals(loginType)) {
            user.setEsylgnCd("106101"); // 디지털원패스
        }

        ArrayList<Map<String, String>> sessionAuthorities = new ArrayList<>();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, String> authority = null;
        boolean grantedRole = false;
        boolean availableRole = false;
        boolean availableSiteaply = true;
        
        String roleid = null; // 역할ID
        String nm = null; // 역할명
        String seCd = null; // 역할_구분_코드
        String trgtInstCd = null; // 대상_기관_코드
        String trgtRgnCd = null; // 대상_지역_코드
        String allowedSiteids = null; // 접근이 가능한 사이트아이디들
        String kndCd = null; // 역할_종류_코드
        
        for (int i = 0; i < resultList.size(); i++) {
            availableRole = false;
            roleid = String.valueOf(resultList.get(i).get("ROLEID"));
            nm = String.valueOf(resultList.get(i).get("NM"));
            seCd = String.valueOf(resultList.get(i).get("SE_CD"));
            trgtInstCd = String.valueOf(resultList.get(i).get("TRGT_INST_CD"));
            trgtRgnCd = String.valueOf(resultList.get(i).get("TRGT_RGN_CD"));
            allowedSiteids = String.valueOf(resultList.get(i).get("ALLOWED_SITEIDS"));
            kndCd = String.valueOf(resultList.get(i).get("KND_CD"));
            
            if (!grantedRole) { // 허용된 역할중 1개만 적용한다.
                if ("P".equals(siteInfo.getSysKndCd()) && "I".equals(user.getLoginUserType())) { // 사용자포털사이트 & 기관회원역할
                    authorities.add(new SimpleGrantedAuthority(sysCompanyRoleid));
                    grantedRole = true;
                } else if ("U".equals(sysSeCd) && "U".equals(seCd)) { // 사용자포털사이트 & 사용자역할
                    authorities.add(new SimpleGrantedAuthority(roleid));
                    grantedRole = true;
                } else if ("A".equals(sysSeCd) && "A".equals(seCd) && Arrays.asList(allowedSiteids.split(",")).contains(siteid)) { // 관리자사이트 & 관리자역할 & 접근가능한 사이트
                    if (!("N".equals(siteaplyUseYn) && "M".equals(kndCd))) { // 분양사이트 미사용이면서 역할종류가 기관관리자인 경우가 아닐때
                        authorities.add(new SimpleGrantedAuthority(roleid));
                        grantedRole = true;
                    }
                }
                
                if (grantedRole) {
                    RoleInfoVo roleInfo = new RoleInfoVo(roleid, nm, kndCd, seCd, trgtInstCd, trgtRgnCd);
                    user.setRoleInfo(roleInfo);
                }
            }
            
            if ("A".equals(sysSeCd) && "A".equals(seCd)) { // 관리자사이트 & 관리자역할
                if ("N".equals(siteaplyUseYn) && "M".equals(kndCd)) { // 분양사이트 미사용이면서 역할종류가 기관관리자인 경우
                    availableRole = false;
                    availableSiteaply = false;
                } else {
                    availableRole = true;
                }
            } else {
                availableRole = true;
            }
            
            if (availableRole) {
                authority = new HashMap<String, String>();
                authority.put("roleid", roleid); // 역할ID
                authority.put("nm", nm); // 역할명
                authority.put("knd_cd", kndCd); // 종류_코드
                authority.put("se_cd", seCd); // 구분_코드
                authority.put("trgt_inst_cd", trgtInstCd); // 대상_기관_코드
                authority.put("trgt_rgn_cd", trgtRgnCd); // 대상_지역_코드
                authority.put("allowed_siteids", allowedSiteids); // 접근이 가능한 사이트아이디들
                sessionAuthorities.add(authority);
            }
        }
        
        if (authorities.size() == 0 && !availableSiteaply) { // 부여받은 역할이 없으면서 분양사이트 미사용이면서 역할종류가 기관관리자인 경우일때
            request.setAttribute("message", "사이트분양 이후 로그인이 가능합니다.");
            throw new InternalAuthenticationServiceException("Login Error !!");
        }
        
        authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여
        user.setAuthorities(sessionAuthorities);

        session.setAttribute("user", user); // 세션에 사용자정보 객체 추가
        //session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, loginid); // REDIS_SESSION

        try {
            commonService.insertLoginSuccess(attr.getRequest(), user.getUserid());
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Login Error !!");
        }
        
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class) || authentication.equals(OnepassUsernameUserkeyAuthenticationToken.class) || authentication.equals(SSOUseridLoginUserTypeAuthenticationToken.class);
    }
    
    private Map<String, Object> getLoginFailMessage(Integer loginFailCnt) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        Integer failCnt = loginFailCnt;
        
        if (failCnt == null) {
            failCnt = 1;
        } else {
            failCnt += 1;
            if (failCnt > 5) {
                failCnt = 5;
            }
        }
        
        sb.append("등록되지 않은 아이디이거나 또는 비밀번호를 잘못 입력하셨습니다. 로그인 5회 이상 실패시 정보 보호를 위해 서비스 이용이 차단 됩니다. (로그인 실패 ").append(failCnt).append("회)");
        
        resultMap.put("message", sb.toString());
        resultMap.put("loginFailCnt", failCnt);
        
        return  resultMap;
    }
}