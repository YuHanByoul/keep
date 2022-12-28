package com.kbrainc.plum.mng.site.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
public class SiteDomainVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 도메인(저장용) */
    @Valid
    @NotNull
    @Size(min = 1, message = "도메인을 입력해주십시오.")
	private String[] dmns;
    
    /** 도메인(조회용) */
    private String dmn;
	
    /** 중복도메인(구분자:콤마(,)) */
    private String domains;
	
    /** 사이트아이디 */
    private Integer siteid;
}
