package com.kbrainc.plum.front.eduInst.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.SchdlVo")
public class SchdlVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

    private Integer aplyid;	     /** 신청아이디 */
    private Integer schdlid;	 /** 일정아이디 */
    private String de;	         /** 일자 */
    private String hr;	         /** 시간 */
    private String eduCrs;      /** 교육 과정 */
    private String eduTrgt;	     /** 교육 대상 */
    private String dtlCn;	     /** 세부 내용*/
    private Date mdfcnDt;       /** 수정 일시 */
    private Integer mdfrid;     /** 수정자아이디 */
    private Date regDt;         /** 등록 일시 */
    private Integer rgtrid;     /** 등록자아이디 */

}
