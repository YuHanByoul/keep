package com.kbrainc.plum.mng.eduInst.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("SeePrgrmVo")
public class SeePrgrmVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

    private Integer aplyid;	     /** 신청아이디 */
    private Integer prgrmid;        /** 프로그램아이디 */
    private String  prgrmNm;        /** 프로그램명 */
    private String  dsgnNo;         /** 지정번호 */
    private Integer rfrncPrgrmid;   /** 참조 프로그램아이디 */
    private String  rfrncPrgrmNm;   /** 참조 프로그램명 */
    private String  eduSbjct;       /** 교육 주제 */
    private String  eduTrgt;        /** 교육 대상 */
    private Integer eduNope;        /** 교육 인원수 */
    private String  eduMthd;        /** 교육 방법 */
    private String  eduRnd;         /** 교육 차시 */
    private String  eduPlc;         /** 교육 장소 */
    private Integer etrfee;         /** 참가비 */
    private String  prgrmSttsCd;    /** 프로그램 상태 코드 */
    private Date    mdfcnDt;        /** 수정 일시 */
    private Integer mdfrid;         /** 수정자아이디 */
    private Date    regDt;          /** 등록 일시 */
    private Integer rgtrid;         /** 등록자아이디 */
}
