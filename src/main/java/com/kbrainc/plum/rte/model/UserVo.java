package com.kbrainc.plum.rte.model;

import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@Data
public class UserVo extends ParentVo implements AuthenticatedPrincipal, Serializable {

    private static final long serialVersionUID = 6779696145911699932L;
    
    /** 사용자아이디 */
    private String userid;
    
    /** 계정 */
    private String acnt;
    
    /** 계정 */
    private String name;
    
    /** 사용자이름 */
    private String nm;
    
    /** 사용자정보조회시 조회된 데이터 ROW Map */
    private Map data;
    
    /** 사용자에게 부여된 역할목록 */
    private ArrayList<Map<String, String>> authorities;
}