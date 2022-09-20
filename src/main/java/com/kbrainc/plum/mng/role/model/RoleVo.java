package com.kbrainc.plum.mng.role.model;

import lombok.Data;

@Data
public class RoleVo {

    /* 역할ID */
    private String roleid;
    
    /* 역할명 */
    private String nm;
    
    /* 역할구분코드(A: admin, U: user) */
    private String se_cd;
    
    /* 설명 */
    private String dc;
    
    /* 상위 역할 ID */
    private int uppr_roleid;
    
    /* 순서 */
    private int ord;
    
    /* 사용 여부 */
    private String use_yn;
    
    /* 수정일자 */
    private String updt_dt;
    
    /* 수정자 ID */
    private int updtuserid;
    
    /* 등록일자 */
    private String reg_dt;
    
    /* 등록자 ID */
    private int reguserid;

    /* action mode 값(W: 쓰기, E: 수정) */
    private String mode;
}
