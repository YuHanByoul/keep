package com.kbrainc.plum.mng.site.model;

import lombok.Data;

@Data
public class SiteDomainVo {
	private String dmn; //도메인
	private String dmn_org; //도메인
	private Integer siteid; //사이트 아이디
	private String dmn_dc; //도메인 설명
	private String updt_dt; //수정일시
	private Integer updtuserid; //수정자아이디
	private String reg_dt; //등록일시
	private Integer reguserid; //등록자아이디
}
