package com.kbrainc.plum.mng.role.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Getter;
import lombok.Setter;

/**
* 사용자에게 역할을 부여하기 위한 Vo클래스.
*
* <pre>
* com.kbrainc.plum.mng.role.model
* - RoleUserVo.java
* </pre>
*
* @ClassName : RoleUserVo
* @Description : 사용자에게 역할을 부여하기 위한 Vo클래스.
* @author : KBRINC
* @date : 2022. 12. 8.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Getter
@Setter
public class RoleUserVo extends ParentRequestVo {
    
    /** 역할아이디 */
    private String roleid;
    
    /** 사용자아이디 */
    private String userid;
    
    /** 역할_시작_일시 */
    @NotEmpty(message = "권한 시작일을 입력해주십시오.")
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "날짜 포맷이 맞지않습니다.")
    private String roleStrtDd;
    
    /** 역할_종료_일시 */
    @NotEmpty(message = "권한 종료일을 입력해주십시오.")
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", message = "날짜 포맷이 맞지않습니다.")
    private String roleEndDd;
    
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
    
    /** 기관_이름 */
    private String instNm;
    
    /** 이름 */
    private String nm;
    
    /** 휴대폰번호 */
    private String moblphon;
    
    /** 이메일 */
    private String eml;
}
