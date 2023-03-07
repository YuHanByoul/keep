package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
public class LdrVo extends ParentRequestVo {

	/** 로그인사용자정보 */
	private UserVo user;

	/** 프로그램아이디 */
	private Integer prgrmid;

	/** 구분 */
	private String se;

	/** 이름 */
	private String nm;

	/** 업무 내용 */
	private String taskCn;

	/** 책임개발자 여부 */
	private String snrstfdvlprYn;

	/** 수정 일시 */
	private Date mdfcnDt;

	/** 수정자아이디 */
	private Integer mdfrid;

	/** 등록 일시 */
	private Date regDt;

	/** 등록자아이디 */
	private Integer rgtrid;

}
