package com.kbrainc.plum.mng.role.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RoleVo {

    /** 역할ID */
    private String roleid;
    
    /** 역할명 */
    @NotEmpty(message = "역할명을 입력해주십시오.")
    @Size(max = 60, message = "역할명은 60자를 넘을 수 없습니다.")
    private String nm;
    
    /** 종류_코드(A: 총괄관리자, G: 일반관리자, M: 기관관리자)  */
    @Pattern(regexp="[AGM]")
    private String kndCd;
    
    /** 역할구분코드(A: admin, U: user) */
    @Pattern(regexp="[AU]")
    private String seCd;
    
    /** 역할설명 */
    @Size(max = 400, message = "역할설명은 400자를 넘을 수 없습니다.")
    private String dc;
    
    /** 상위 역할 ID */
    private int upprRoleid;
    
    /** 순서 */
    private int ord;
    
    /** 대상_기관_코드(A:전체, S:소속, C:선택) */
    @Pattern(regexp="[ASC]")
    private String trgtInstCd;
    
    /** 대상_지역_코드(A:사용안함 , S:선택지역) */
    @Pattern(regexp="[AS]")
    private String trgtRgnCd;
    
    /** 사용 여부 */
    @Pattern(regexp="[YN]")
    private String useYn = "N";
    
    /** 수정일자 */
    private String mdfcnDt;
    
    /** 수정자 ID */
    private int mdfrid;
    
    /** 등록일자 */
    private String regDt;
    
    /** 등록자 ID */
    private int rgtrid;

    /** action mode 값(W: 쓰기, E: 수정) */
    private String mode;
    
}
