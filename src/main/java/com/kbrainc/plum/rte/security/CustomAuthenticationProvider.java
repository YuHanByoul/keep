package com.kbrainc.plum.rte.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.session.FindByIndexNameSessionRepository; // REDIS_SESSION
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.RoleInfoVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;

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
    
    @Value("${system.person.roleid}")
    private String sysPersonRoleid;
    
    @Value("${system.company.roleid}")
    private String sysCompanyRoleid;
    
    private Map<String,Integer> usernameNotFoundMap = new HashMap<String,Integer>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginid = authentication.getName();
        
        String password = (String) authentication.getCredentials();

        // 비밀번호 암호화
        try {
             password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(password.getBytes("UTF-8")));
             // sha3-512 비밀번호 암호화
        } catch (NoSuchAlgorithmException e) {
            throw new InternalAuthenticationServiceException("Login Error !!");
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Login Error !!");
        }

        Map resultMap = null;
        List<Map<String, Object>> resultList = null;
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpSession session = request.getSession();
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        String sysSeCd = siteInfo.getSysSeCd();
        request.setAttribute("loginid", loginid);
        
        if ("A".equals(sysSeCd)) { // 관리자 사이트
            try {
                resultMap = securedObjectService.selectUserLoginInfo(loginid); // 사용자 로그인 정보 조회
                if ("Y".equals((String) resultMap.get("ACNT_LOCK_YN"))) { // 계정이 잠겨있으면
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                    throw new LockedException("Login Error !!");
                }
                if (!password.equals((String) resultMap.get("PSWD"))) {
                    commonService.insertLoginFail(request, String.valueOf(resultMap.get("USERID")));
                    Integer loginFailCnt = (Integer)resultMap.get("LGN_FAIL_CNT");
                    getLoginFailMessage(loginFailCnt);
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    throw new BadCredentialsException("Login Error !!");
                }
                
                resultList = securedObjectService.selectGrantedAuthority(loginid); // 사용자에게 부여된 역할 목록 조회
                // 사용자 역할까지 부여
                String instid = (String) resultMap.get("INSTID");
                Map<String, Object> roleMap = null;
                if (instid == null) {
                    roleMap = new HashMap<String, Object>();
                    roleMap.put("ROLEID", sysPersonRoleid);
                    roleMap.put("NM", "개인회원");
                    roleMap.put("SE_CD", "U");
                    resultList.add(roleMap);
                } else {
                    roleMap = new HashMap<String, Object>();
                    roleMap.put("ROLEID", sysCompanyRoleid);
                    roleMap.put("NM", "기관회원");
                    roleMap.put("SE_CD", "U");
                    resultList.add(roleMap);
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
            } catch (LockedException e) { // 계정이 잠긴 사용자일때
                throw new LockedException("Login Error !!");
            } catch (Exception e) { // 예외발생시
                throw new InternalAuthenticationServiceException("Login Error !!");
            }
        } else { // 사용자 사이트
            try {
                resultMap = securedObjectService.selectUserLoginInfo(loginid); // 사용자 로그인 정보 조회
                if ("Y".equals((String) resultMap.get("ACNT_LOCK_YN"))) { // 계정이 잠겨있으면
                    request.setAttribute("message", "서비스 이용이 차단되었습니다. 고객센터에 문의 해주십시오.");
                    throw new LockedException("Login Error !!");
                }
                if (!password.equals((String) resultMap.get("PSWD"))) {
                    commonService.insertLoginFail(request, String.valueOf(resultMap.get("USERID")));
                    Integer loginFailCnt = (Integer)resultMap.get("LGN_FAIL_CNT");
                    getLoginFailMessage(loginFailCnt);
                    Map<String, Object> data = getLoginFailMessage(loginFailCnt);
                    request.setAttribute("message", data.get("message"));
                    throw new BadCredentialsException("Login Error !!");
                }
                
                resultList = securedObjectService.selectGrantedAuthority(loginid); // 사용자에게 부여된 역할 목록 조회
                // 사용자 역할까지 부여
                String instid = (String) resultMap.get("INSTID");
                Map<String, Object> roleMap = null;
                if (resultList == null) {
                    resultList = new ArrayList<Map<String, Object>>();
                }
                
                if (instid == null) {
                    roleMap = new HashMap<String, Object>();
                    roleMap.put("ROLEID", sysPersonRoleid);
                    roleMap.put("NM", "개인회원");
                    roleMap.put("SE_CD", "U");
                    resultList.add(roleMap);
                } else {
                    roleMap = new HashMap<String, Object>();
                    roleMap.put("ROLEID", sysCompanyRoleid);
                    roleMap.put("NM", "기관회원");
                    roleMap.put("SE_CD", "U");
                    resultList.add(roleMap);
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
        user.setAcnt(loginid);
        user.setName(loginid);
        user.setNm((String) resultMap.get("NM"));
        user.setInstid((Integer)resultMap.get("INSTID"));
        user.setInstpicRoleCd((String)resultMap.get("INSTPIC_ROLE_CD"));
        user.setData(resultMap);

        ArrayList<Map<String, String>> sessionAuthorities = new ArrayList<>();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, String> authority = null;
        boolean grantedRole = false;
        authorities.add(new SimpleGrantedAuthority("0")); // anonymous권한 기본 부여
        
        for (int i = 0; i < resultList.size(); i++) {
            if (!grantedRole) { // 허용된 역할중 1개만 적용한다.
                if ("A".equals(sysSeCd) && "A".equals(resultList.get(i).get("SE_CD"))) { // 관리자사이트 & 관리자역할
                    authorities.add(new SimpleGrantedAuthority(String.valueOf(resultList.get(i).get("ROLEID"))));
                    RoleInfoVo roleInfo = new RoleInfoVo(String.valueOf(resultList.get(i).get("ROLEID")), (String)resultList.get(i).get("NM"), (String)resultList.get(i).get("SE_CD"), (String)resultList.get(i).get("TRGT_INST_CD"), (String)resultList.get(i).get("TRGT_RGN_CD"));
                    user.setRoleInfo(roleInfo);
                    grantedRole = true;
                } else if ("U".equals(sysSeCd) && "U".equals(resultList.get(i).get("SE_CD"))) { // 사용자사이트 & 사용자역할
                    authorities.add(new SimpleGrantedAuthority(String.valueOf(resultList.get(i).get("ROLEID"))));
                    RoleInfoVo roleInfo = new RoleInfoVo(String.valueOf(resultList.get(i).get("ROLEID")), (String)resultList.get(i).get("NM"), (String)resultList.get(i).get("SE_CD"), (String)resultList.get(i).get("TRGT_INST_CD"), (String)resultList.get(i).get("TRGT_RGN_CD"));
                    user.setRoleInfo(roleInfo);
                    grantedRole = true;
                }
            }
            authority = new HashMap<String, String>();
            authority.put("roleid", (String.valueOf(resultList.get(i).get("ROLEID")))); // 역할ID
            authority.put("nm", (String.valueOf(resultList.get(i).get("NM")))); // 역할명
            authority.put("se_cd", (String.valueOf(resultList.get(i).get("SE_CD")))); // 구분_코드
            authority.put("trgt_inst_cd", (String.valueOf(resultList.get(i).get("TRGT_INST_CD")))); // 대상_기관_코드
            authority.put("trgt_rgn_cd", (String.valueOf(resultList.get(i).get("TRGT_RGN_CD")))); // 대상_지역_코드
            sessionAuthorities.add(authority);
        }
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
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private Map<String, Object> getLoginFailMessage(Integer loginFailCnt) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        
        if (loginFailCnt == null) {
            loginFailCnt = 1;
        } else {
            loginFailCnt += 1;
            if (loginFailCnt > 5) {
                loginFailCnt = 5;
            }
        }
        
        sb.append("등록되지 않은 아이디이거나 또는 비밀번호를 잘못 입력하셨습니다. 로그인 5회 이상 실패시 정보 보호를 위해 서비스 이용이 차단 됩니다. (로그인 실패 ").append(loginFailCnt).append("회)");
        
        resultMap.put("message", sb.toString());
        resultMap.put("loginFailCnt", loginFailCnt);
        
        return  resultMap;
    }
}