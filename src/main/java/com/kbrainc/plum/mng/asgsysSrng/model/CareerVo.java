package com.kbrainc.plum.mng.asgsysSrng.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 책임 개발자 경력 vo
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - CareeRVo.java
* </pre>
*
* @ClassName : CareeRVo
* @Description : 책임 개발자 경력 vo
* @author : kbrain
* @date : 2023. 3. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CareerVo extends ParentRequestVo {

    /** 로그인사용자정보 */
	private UserVo user;

	/** 경력아이디 */
	private Integer careerid;

	/** 프로그램아이디 */
	private Integer prgrmid;

	/** 시작 일자 */
	private String bgngDe;

	/** 종료 일자 */
	private String endDe;

	/** 활동 이름 */
	private String actvtNm;

	/** 활동 유형 */
	private String actvtType;

	/** 수정 일시 */
	private String mdfcnDt;

	/** 수정자아이디 */
	private String mdfrid;

	/** 등록 일시 */
	private String regDt;

}
