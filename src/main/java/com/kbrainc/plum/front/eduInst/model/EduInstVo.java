package com.kbrainc.plum.front.eduInst.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EduInstVo")
public class EduInstVo extends ParentRequestVo {

	/** 로그인사용자정보 */
    private UserVo user;

    private String searchPrgrmNm;  /** 검색.프로그램아이디 */
    private String searchDsgnNo;   /** 검색.지정번호 */

    private Integer aplyid;           /** 신청아이디 */
    private Integer planKey;          /** 운영계획KEY */
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
    private String addrDtl;           /** 기관 상세 주소*/
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

    private String mainBsns;          /** 주요 사업 */
    private String operMthd;          /** 운영 방법 */
    private String operPlan;          /** 운영 계획 */
    private String mngPlan;           /** 관리 계획 */
    private String bgtPlan;           /** 예산 계획 */
    private String expcEffect;        /** 기대 계획 */
    private String etcDta;            /** 기타 자료 */

    private Integer fcltKey;          /** 시설 개요 KEY*/
    private String  rentYn;           /** 임대 여부 */
    private String  rentPrd;          /** 임대 기간 */
    private Integer totar;            /** 총면적 */
    private String  styFcltHoldYn;    /** 숙박 시설 보유 여부 */
    private Integer styFcltAr;        /** 숙박 시설 면적 */
    private Integer styFcltNope;      /** 숙박 시설 인원수 */

    private Integer schdlid;	       /** 일정아이디 */
    private String  seCd;             /** 구분_코드    */

    private String cd;                /** 코드 */
    private String cdNm;              /** 코드명 */
    private String idx;

    private List<SchdlVo> schdlList;
    private List<EduExprtVo> eduExprtList;
    private List<SeePrgrmVo> seePrgrmList;
    private List<LctrumVo> lctrumList;
    private List<EqpVo> eqpList;

}
