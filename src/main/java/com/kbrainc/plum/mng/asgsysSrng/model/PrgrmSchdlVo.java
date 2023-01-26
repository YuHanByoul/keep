package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 *
 * 프로그램운영일정Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.asgsysSrng.model
 * - PrgrmSchdlVo.java
 * </pre>
 *
 * @ClassName : PrgrmSchdlVo
 * @Description : 프로그램운영일정Vo 클래스
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Data
public class PrgrmSchdlVo extends ParentRequestVo {

	/** 로그인 사용자 정보 */
    private UserVo user;

	/** 차시*/
	private String  rnd;

    /** 프로그램_이름*/;
	private String  prgrmNm;

    /** 등록자아이디*/;
	private Integer rgtrid;

    /** 프로그램아이디*/;
	private Integer prgrmid;

    /** 종료_분*/
	private Integer endMnt;

    /** 등록_일시*/
	private Date    regDt;

    /** 일정아이디*/
	private Integer schdlid;

    /** 종료_시간*/
	private Integer endHr;

    /** 시작_분*/
	private Integer bgngMnt;

    /** 시작_시간*/
	private Integer bgngHr;

    /** 과정*/
	private String  crs;

	/** 수정일시 */
	private Date mdfcnDt;

	/** 수정자ID */
	private Integer mdfrid;


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
