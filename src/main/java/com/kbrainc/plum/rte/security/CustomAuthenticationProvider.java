package com.kbrainc.plum.rte.security;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.session.FindByIndexNameSessionRepository; // REDIS_SESSION
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecuredObjectService securedObjectService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginid = authentication.getName();
        
        String password = (String) authentication.getCredentials();

        // 비밀번호 암호화
        try {
             password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(password.getBytes("UTF-8")));
             // sha3-512 비밀번호 암호화
        } catch (Exception e) {
            throw new BadCredentialsException("Login Error !!");
        }

        Map resultMap = null;
        List<Map<String, Object>> resultList = null;
        
        try {
            resultMap = securedObjectService.selectUserLoginInfo(loginid); // 사용자 로그인 정보 조회
            if (!password.equals((String) resultMap.get("PWD"))) {
                throw new BadCredentialsException("Login Error !!");
            }
            
            resultList = securedObjectService.selectGrantedAuthority(loginid); // 사용자에게 부여된 역할 목록 조회
        } catch (EmptyResultDataAccessException e) { // 존재하지않는 사용자일때
            throw new BadCredentialsException("Login Error !!");
        } catch (Exception e) { // 예외발생시
            throw new BadCredentialsException("Login Error !!");
        }

        // 추가
        /*
        String userSeCd = (String) resultMap.get("USER_SE_CD"); // 사용자_구분_코드(T:교사, G: 일반)
        Map<String, Object> roleMap = null;
        resultList = new ArrayList<Map<String, Object>>();
        if ("T".equals(userSeCd)) {
            roleMap = new HashMap<String, Object>();
            roleMap.put("ROLEID", sysTeacherRoleid);
            roleMap.put("NM", "교사");
            resultList.add(roleMap);
        } else if ("G".equals(userSeCd)) {
            roleMap = new HashMap<String, Object>();
            roleMap.put("ROLEID", sysGeneralRoleid);
            roleMap.put("NM", "일반");
            resultList.add(roleMap);
        }
        */
        
        if (resultList.size() == 0) { // 사용자에게 부여된 역할이 없으면
            throw new BadCredentialsException("Login Error !!");
        }

        UserVo user = new UserVo();
        user.setUserid(String.valueOf(resultMap.get("USERID")));
        user.setAcnt(loginid);
        user.setName(loginid);
        user.setNm((String) resultMap.get("NM"));
        user.setData(resultMap);

        ArrayList<Map<String, String>> sessionAuthorities = new ArrayList<>();
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, String> authority = null;
        for (int i = 0; i < resultList.size(); i++) {
            if (i == 0) { // 사용자에게 부여된 역할중 1개만 적용한다.
                authorities.add(new SimpleGrantedAuthority(String.valueOf(resultList.get(i).get("ROLEID"))));
            }
            authority = new HashMap<String, String>();
            authority.put("roleid", (String.valueOf(resultList.get(i).get("ROLEID")))); // 역할ID
            authority.put("nm", (String.valueOf(resultList.get(i).get("NM")))); // 역할명
            sessionAuthorities.add(authority);
        }
        user.setAuthorities(sessionAuthorities);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("user", user); // 세션에 사용자정보 객체 추가
        //session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, loginid); // REDIS_SESSION

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}