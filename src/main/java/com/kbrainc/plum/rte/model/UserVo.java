package com.kbrainc.plum.rte.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticatedPrincipal;

import lombok.Data;

@Data
public class UserVo extends ParentVo implements AuthenticatedPrincipal, Serializable ,Cloneable {

    private static final long serialVersionUID = 6779696145911699932L;
    
    /** 사용자아이디 */
    private String userid;
    
    /** 계정 */
    private String acnt;
    
    /** 계정 */
    private String name;
    
    /** 사용자이름 */
    private String nm;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 기관담당자_역할_코드 */
    private String instpicRoleCd;
    
    /** 포털로그인사용자타입(개인회원:P, 기관회원:I) */
    private String loginUserType;
    
    /** 사용자타입(전문가:E, 어린이:C), null일수 있음. */
    private String userType;
    
    /** 현재적용된 역할정보 */
    private RoleInfoVo roleInfo;
    
    /** 간편로그인_코드(현재로그인한) */
    private String esylgnCd;
    
    /** 디지털원패스 연동여부 */
    private String onepassLinkYn;
    
    /** 삭제 사유  */
    private String secsnRsn;
    
    /** 간편로그인 연결갯수 */
    private int esylgnLinkCnt;
    
    /** 사용자정보조회시 조회된 데이터 ROW Map */
    private Map data = new HashMap();
    
    /** 사용자에게 부여된 역할목록 */
    private ArrayList<Map<String, String>> authorities = new ArrayList();
    
    public void setData(Map data) {
        this.data.putAll(data);
    }
    
    public void setAuthorities(ArrayList<Map<String, String>> authorities) {
        this.authorities.addAll(authorities);
    }
    
    public UserVo() {}
    
    public UserVo(UserVo user)
    {
        this.acnt = user.getAcnt();
        this.authorities = user.getAuthorities();
        this.data = user.getData();
        this.name = user.getName();
        this.nm = user.getNm();
        this.userid = user.getUserid();
    }
    
}