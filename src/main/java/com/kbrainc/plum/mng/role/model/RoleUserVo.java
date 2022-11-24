package com.kbrainc.plum.mng.role.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Getter;
import lombok.Setter;

/**
 * ROLE_USER VO.
 *
 * @FileName : RoleUserVo.java
 * @Project : airecruit
 * @Date : 2019. 5. 10.
 * @author : KBRAINC
 * @Version : 1.0
 * @Modify :
 * @Description :
 *
 */
@Getter
@Setter
public class RoleUserVo extends ParentRequestVo {
    
    /** 역할아이디 */
    private String roleid;
    
    /** 사용자아이디 */
    private String userid;
    
    /** 역할_시작_일시 */
    private String roleStrtDt;
    
    /** 역할_종료_일시 */
    private String roleEndDt;
    
    /** 수정_일시 */
    private String mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    private String regDt;
    
    /** 등록자아이디 */
    private String rgtrid;

    /* 역할_이름 */
    private String roleNm;

    /** 계정 */
    private String acnt;
    
    /** 이름 */
    private String nm;
    
    /** 사용자_구분_코드 */
    private String userSeCd;
}
