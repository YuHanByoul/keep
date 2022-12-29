package com.kbrainc.plum.mng.site.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
 * 
 * Site VO
 *
 * <pre>
 * com.kbrainc.plum.mng.site.model
 * - SiteVo.java
 * </pre> 
 *
 * @ClassName : SiteVo
 * @Description : Site VO
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class SiteVo extends ParentRequestVo {
	private static final String SYSTEM_SE_NAME_ADMIN = "관리자";
	private static final String SYSTEM_SE_NAME_USER = "사용자";

	/** 사이트 아이디 */
	private Integer siteid;
	
	/** 사이트 이름 */
	@NotEmpty(message = "사이트 이름을 입력해주십시오.")
    @Size(max = 20, message = "사이트 이름은 20자를 넘을 수 없습니다.")
	private String siteNm; 
	
	/** 시스템 구분 코드 */
	@NotEmpty(message = "시스템 구분 코드를 입력해주십시오.")
	@Pattern(regexp="[AU]")
	private String sysSeCd;
	
	/** 시스템 구분 이름 */
	private String sysSeNm;
	
	/** 시스템 종류 코드 */
	@NotEmpty(message = "시스템 종류 코드를 입력해주십시오.")
	@Pattern(regexp="[APT]")
	private String sysKndCd;
	
	/** 로고 파일아이디 */
	private Integer logoFileid;
	
	/** 카피라이트 */
	@NotEmpty(message = "카피라이트를 입력해주십시오.")
    @Size(max = 2000, message = "카피라이트는 2000자를 넘을 수 없습니다.")
	private String cpyrht;
	
	/** 사용 여부 */
	@Pattern(regexp="[YN]")
	private String useYn = "N";
	
	/** 수정자아이디 */
	private Integer mdfrid;
	
    /** 등록일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regDt;
	private Integer rgtrid; //등록자 아이디
	
	private String domains; //등록된 도메인들의 목록 문자열
	private List<SiteDomainVo> siteDomainList = new ArrayList(); //등록된 도메인 list
	
	/** 기관아이디 */
	@NotEmpty(message = "기관아이디를 입력해주십시오.")
	private String instid;
	
	/** 기관_이름 */
	private String instNm;
	
	/** 기관_코드 */
	private String instCd;
	
	/**
	* 
	* 시스템 코드 입력시 관리되지 않는 코드로 이름을 자동 입력한다.
	*
	* @Title : setSysSeCd
	* @Description : 시스템 코드 입력시 관리되지 않는 코드로 이름을 자동 입력한다.
	* @param sysSeCd 시스템 구분 코드
	* @return void
	*/
	public void setSysSeCd(String sysSeCd) {
		this.sysSeCd = sysSeCd;
		if( "A".equals(sysSeCd) ) {
			this.setSysSeNm(SYSTEM_SE_NAME_ADMIN);
		} else if( "U".equals(sysSeCd) ) {
			this.setSysSeNm(SYSTEM_SE_NAME_USER);
		}
	}
    public void setSiteDomainList(List<SiteDomainVo> siteDomainList) {
        this.siteDomainList.addAll(siteDomainList);
    }
}
