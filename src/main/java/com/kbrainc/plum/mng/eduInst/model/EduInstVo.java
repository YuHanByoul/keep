/**
 *
 */
package com.kbrainc.plum.mng.eduInst.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 사회환경교육기관 지정 > 신청/결과관리 VO 클래스.
*
* <pre>
* com.kbrainc.plum.mng.eduInst.model
* - EduInstVo.java
* </pre>
*
* @ClassName : EduInstVo
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class EduInstVo extends ParentRequestVo {

    private UserVo user;

    /** 신청 아이디 */
    private Integer aplyid;
    /** 지정내역 아이디 */
    private Integer dsctnid;
    /** 지정내역 아이디 */
    private Integer did;
    // 검색조건 start
    /** 시도_코드_검색 */
    private String searchCtprvn;
    /** 감색 유형 */
    private String searchType;
    /** 검색 keyword */
    private String searchKeyword;
    /** 검색 신청상태 */
    private String searchSttsCd;
    /** 검색 신청일 */
    private String searchRegDt;

    private String searchPrgrmNm;  /** 검색.프로그램아이디 */
    private String searchDsgnNo;   /** 검색.지정번호 */


    private String cd;                /** 코드 */
    private String cdNm;              /** 코드명 */
    private String idx;

    /** 시도_코드 */
    private String ctprvnCd;
    /** 시도_코드명 */
    private String ctprvnNm;
    /** 접수번호 */
    private String rcptno;
    /** 기관아이디 */
    private String instid;
    /** 기관_이름 */
    private String instNm;
    /** 기관_유형 */
    private String instTypeCd;
    /** 기관_유형명 */
    private String instTypeNm;
    /** 신청자아이디 */
    private String aplcntid;
    /** 신청자명 */
    private String aplcntNm;
    /** 상태_코드 */
    private String sttsCd;
    /** 상태_코드명 */
    private String sttsNm;
    /** 지정내역 상태_코드 */
    private String dsctnSttsCd;
    /** 지정내역 상태_코드명 */
    private String dsctnSttsNm;
    /** 답변_상태_코드 */
    private String ansSttsCd;
    /** 답변_상태_코드명 */
    private String ansSttsNm;
    /** 기관주소 */
    private String instAddr;
    /** 기관상세주소 */
    private String instAddrDtl;
    private String callSe;

    private String dsgnCnclDe;
    private String dsgnNo;
    private String chkVal;

    private Integer planKey;          /** 운영계획KEY */
    private String aplcntEml;         /** 신청자 이메일 */
    private String aplyTelno;         /** 신청자 전화번호 */
    private String aplcntMoblphon;    /** 신청자 휴대폰 */
    private String instEml;           /** 기관 이메일 */
    private String instCntct;         /** 기관 연락처 */
    private String addr;              /** 기관 주소*/
    private String addrDtl;           /** 기관 상세 주소*/
    private String rprsvBrdt;         /** 대표자 생년월일 */
    private String rprsvNm;           /** 대표자 명 */
    private Integer atchFilegrpid;    /** 첨부 파일그룹아이디 */
    private String dsgnDe;            /** 지정 일자 */
    private String brno;              /** 사업자등록번호 */
    private Integer autzrid;          /** 승인자아이디 */
    private String issuDe;            /** 발급일자*/
    private String isgnDe;            /** 재발급일자*/

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

    private Integer schdlid;           /** 일정아이디 */
    private String  seCd;             /** 구분_코드    */

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;

    /** 파일그룹아이디 */
    private Integer filegrpid;

    private List<SchdlVo> schdlList;
    private List<EduExprtVo> eduExprtList;
    private List<SeePrgrmVo> seePrgrmList;
    private List<LctrumVo> lctrumList;
    private List<EqpVo> eqpList;






    /** 신청기간 검색 종료일 */
    private String searchAplyEndDe;

    /** 심사기간 검색 시작일 */
    private String searchSrngBgngDe;

    /** 심사기간 검색 종료일 */
    private String searchSrngEndDe;

    /** 신청상태 */
    private String searchAplyStts;

    /** 심사상태 */
    private String searchSrngStts;
    // 검색조건 end

    /** 심사위원 아이디 */
    private Integer jdgsid;

    /**  공모아이디 */
    private Integer pcntstid;

    /** 분야_코드 */
    private String fldCd;

    /** 분야_코드명 */
    private String fldNm;

    /** 공모_이름 */
    private String pcntstNm;

    /** 신청상태 */
    private String aplySttsNm;

    /** 심사상태 */
    private String srngSttsNm;

    /** 신청_기간 */
    private String aplyDt;

    /** 심사_기간 */
    private String srngDt;

    /** 신청건수 */
    private String aplynoCnt;

    /** 1차심사 건수 */
    private String frtSrngCnt;

    /** 2차심사 건수 */
    private String scdSrngCnt;

    /** 선정완료 건수 */
    private String slctnYCnt;

    /** 선정탈락 건수 */
    private String slctnNCnt;

    /** 엑셀다운로드 여부 */
    private String excelYn;

    /** 1차 심사양식 */
    private Integer srngFormidFirst;

    /** 2차 심사양식 */
    private Integer srngFormidScnd;

    /** 온라인 심사유형 코드 */
    private String onlnSrngTypeCd;

    /** 심사위원 이름 */
    private String nm;

    /** 프로그램명 */
    private String prgrmNm;

    /** 심사 시작일자 */
    private String srngBgngDt;

    /** 심사 종료일자 */
    private String srngEndDt;

    /** 심사 시작시간 */
    private String srngBgngTm;

    /** 심사 종료시간 */
    private String srngEndTm;

    /** 심사 종료 여부 */
    private String srngEndYn;

    /** 2차 심사 종료 여부 */
    private String scndSrngEndYn;
}
