package com.kbrainc.plum.mng.dsgnPrgrm.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 운영실적Vo
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - OperPrfmncVo.java
* </pre>
*
* @ClassName : OperPrfmncVo
* @Description : 운영실적Vo
* @author : kbrain
* @date : 2023. 3. 17.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class OperPrfmncVo extends ParentRequestVo {

	/** 로그인사용자정보 */
	private UserVo user;

    /** 실적아이디 */
	private Integer prfmncid;

	/** 차수아이디 */
	private Integer cyclid;

	/** 실시 일자 */
	private String oprtnDe;

	/** 대상 */
	private String trgt;

	/** 시간 */
	private Integer hr;

	/** 인원수 */
	private Integer nope;

	/** 횟수 */
	private Integer cnt;

	/** 전체 시간 */
	private Integer wholHr;

	/** 전체 인원수 */
	private Integer wholNope;

	/** 지도자 배치 비율 */
	private String ldrPostngRt;

	/** 수정 일시 */
	private Date mdfcnDt;

	/** 수정자아이디 */
	private Integer mdfrid;

	/** 등록 일시 */
	private Date regDt;

	/** 등록자아이디 */
	private Integer rgtrid;

}
