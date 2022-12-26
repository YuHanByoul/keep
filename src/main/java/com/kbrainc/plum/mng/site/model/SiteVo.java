package com.kbrainc.plum.mng.site.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private Integer siteid;	//사이트 아이디
	private String siteNm; //사이트 이름 
	private String sysSeCd; //시스템 구분 코드
	private String sysSeNm; //시스템 구분 이름
	private String useYn; //사용유무
	private Integer mdfrid; //수정자 아이디
	
    /** 등록일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regDt;
	private Integer rgtrid; //등록자 아이디
	
	private String domains; //등록된 도메인들의 목록 문자열
	private List<SiteDomainVo> siteDomainList = new ArrayList(); //등록된 도메인 list
	
	/** 기관아이디 */
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
