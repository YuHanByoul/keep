package com.kbrainc.plum.front.eduInst.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EduInstVo")
public class EduInstVo extends ParentRequestVo {

	/** 로그인사용자정보 */
    private UserVo user;

    private Integer aplyid;           /** 신청아이디 */
    private String rcptno;            /** 접수번호 */
    private Integer instid;           /** 기관아이디 */
    private String aplcntid;          /** 신청자아이디 */
    private String aplcntNm;          /** 신청자 이름 */
    private String aplcntEml;         /** 신청자 이메일 */
    private String aplyTelno;         /** 신청자 전화번호 */
    private String aplcntMoblphon;    /** 신청자 휴대폰 */
    private String instNm;            /** 기관 이름 */
    private String instEml;           /** 기관 이메일 */
    private String instCntct;         /** 기관 연락처 */
    private String addr;              /** 기관 주소*/
    private String addrDtl;          /** 기관 상세 주소*/
    private String ctprvnCd;          /** 시도 코드 */
    private String rprsvBrdt;         /** 대표자 생년월일 */
    private String rprsvNm;           /** 대표자 명 */
    private Integer atchFilegrpid;    /** 첨부 파일그룹아이디 */
    private String dsgnno;            /** 지정 번호 */
    private String dsgnDe;            /** 지정 일자 */
    private String brno;              /** 사업자등록번호 */
    private Integer autzrid;          /** 승인자아이디 */
    private String issuDe;            /** 발급일자*/
    private String IsgnDe;            /** 재발급일자*/
    private String sttsCd;            /** 상태 코드 */
    private Date mdfcnDt;             /** 수정 일시 */
    private Integer mdfrid;           /** 수정자아이디 */
    private Date regDt;               /** 등록 일시 */
    private Integer rgtrid;           /** 등록자아이디 */

    private String cd;                /** 코드 */
    private String cdNm;              /** 코드명 */

}
