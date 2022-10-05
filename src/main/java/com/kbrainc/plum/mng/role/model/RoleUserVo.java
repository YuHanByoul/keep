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
    private String role_strt_dt;
    
    /** 역할_종료_일시 */
    private String role_end_dt;
    
    /** 수정_일시 */
    private String updt_dt;
    
    /** 수정자아이디 */
    private String updtuserid;
    
    /** 등록_일시 */
    private String reg_dt;
    
    /** 등록자아이디 */
    private String reguserid;

    /* 역할_이름 */
    private String role_nm;

    /** 계정 */
    private String acnt;
    
    /** 이름 */
    private String nm;
    
    /** 사용자_구분_코드 */
    private String user_se_cd;
}
