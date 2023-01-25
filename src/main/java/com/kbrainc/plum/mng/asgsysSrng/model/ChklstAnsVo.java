package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 *
 * 체크리스트응답Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.asgsysSrng.model
 * - ChklstAnsVo.java
 * </pre>
 *
 * @ClassName : ChklstQitemVo
 * @Description : 체크리스트응답Vo 클래스
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Data
public class ChklstAnsVo extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

	/** 제출 ID*/
	private Integer sbmsnid;

	/** 항목 ID*/
	private Integer qitemid;

	/** 구분코드 */
	private String seCd;

	/** 내용 */
	private String cn;

	/** 확인사항 */
	private String idntyMttr;

	/** 점수 */
	private Integer scr;

	/** 순서 */
	private Integer ordr;

	/** 수정일시 */
	private Date mdfcnDt;

	/** 수정자ID */
	private Integer mdfrid;

	/** 등록일시 */
	private Date regDt;

	/** 등록자 아이디 */
	private Integer rgtrid;

	/** 수정일자 정보 */
    public Date getMdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }

    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }

    /** 등록일자 정보 */
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }

    /** 로그인 사용자 정보 */
    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }

    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }

}
