package com.kbrainc.plum.mng.eduInst.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("LctrumVo")
public class LctrumVo extends ParentRequestVo {

	/** 로그인사용자정보 */
    private UserVo user;

	private Integer lctrumid;    /** 강의실아이디 */
	private Integer aplyid;      /** 신청아이디   */
	private String  seCd;        /** 구분_코드    */
	private String  nm;          /** 이름         */
	private String  ar;          /** 면적         */
	private Integer nope;        /** 인원수       */
	private Date    mdfcnDt;     /** 수정_일시    */
	private String  mdfrid;      /** 수정자아이디 */
	private Date    regDt;       /** 등록_일시    */
	private String  rgtrid;      /** 등록자아이디 */

}
