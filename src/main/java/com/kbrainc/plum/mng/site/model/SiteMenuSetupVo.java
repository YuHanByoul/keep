package com.kbrainc.plum.mng.site.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 사이트 메뉴 구성 Vo클래스.
*
* <pre>
* com.kbrainc.plum.mng.site.model
* - SiteMenuSetupVo.java
* </pre>
*
* @ClassName : SiteMenuSetupVo
* @Description : 사이트 메뉴 구성 Vo클래스.
* @author : KBRAINC
* @date : 2023. 1. 2.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SiteMenuSetupVo {
	
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 메뉴구성 코드_이름 */
	private String cdNm;
	
	/** 프로그램 설명 */
	private String dc;
	
	/** 프로그램아이디 */
	private Integer prgrmid;
	
	/** 메뉴구성에 해당프로그램이 사용되었는지 여부(YN) */
	private String useYn;
	
	/** 옵션값("required" 필수, "option" 옵션) */
	private String optn2;
	
	/** 옵션값("show" 노출, "hidden" 미노출) */
	private String optn3;
	
	/** 사이트 아이디 */
	@NotNull(message = "사이트아이디를 입력해주십시오.")
    private Integer siteid;
    
	/** 메뉴구성에 사용될 프로그램아이디 배열 */
    @Valid
    @NotNull
    @Size(min = 1, message = "메뉴를 구성할 프로그램을 입력해주십시오.")
	private String[] prgrmids;
    
    /** 메뉴구성시 hidden처리할 프로그램아이디 배열 */
    private List<String> hidePrgrmids;
}
