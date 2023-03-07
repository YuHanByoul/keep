package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 책임개발자 학력 vo
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - AcbgVo.java
* </pre>
*
* @ClassName : AcbgVo
* @Description : 책임 개발자 학력 vo
* @author : kbrain
* @date : 2023. 3. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class AcbgVo extends ParentRequestVo {

    /** 로그인사용자정보 */
	private UserVo user;

	/** 학력아이디 */
	private Integer acbgid;

	/** 프로그램아이디 */
	private Integer prgrmid;

	/** 시작 일자 */
	private String bgngDe;

	/** 종료 일자 */
	private String endDe;

	/** 학교 이름 */
	private String schlNm;

	/** 전공 */
	private String mjr;

	/** 학위 */
	private String dgr;

	/** 수정 일시 */
	private Date mdfcnDt;

	/** 수정자아이디 */
	private Integer mdfrid;

	/** 등록 일시 */
	private Date regDt;

	/** 등록자아이디 */
	private Integer rgtrid;


}
