package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 책임개발자 자격 vo
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - QlfcVo.java
* </pre>
*
* @ClassName : QlfcVo
* @Description : 책임개발자 자격 vo
* @author : kbrain
* @date : 2023. 3. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class QlfcVo extends ParentRequestVo {

    /** 로그인사용자정보 */
	private UserVo user;

	/** 프로그램아이디 */
	private Integer prgrmid;

	/** 자격아이디 */
	private Integer qlfcid;

	/** 신청아이디 */
	private Integer aplyid;

	/** 자격 이름 */
	private String qlfcNm;

	/** 등급 */
	private String grd;

	/** 취득 일자 */
	private String acqsDe;

	/** 발령처 */
	private String wrkplc;

	/** 수정 일시 */
	private Date mdfcnDt;

	/** 수정자아이디 */
	private Integer mdfrid;

	/** 등록 일시 */
	private Date regDt;

	/** 등록자아이디 */
	private Integer rgtrid;

}
