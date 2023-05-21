package com.kbrainc.plum.mng.prtpn.infntAply.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 유아교육신청 -> 교육신청관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.model
* - InfntAplyVo.java
* </pre>
**
@ClassName : InfntAplyVo
* @Description : 유아교육신청 -> 교육신청관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class InfntAplyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private List<InfntAplyVo> infntAplyVoList;
    
    /** 프로그램_일정아이디 */
    private String prgrmSchdlid;
    /** 신청아이디 */
    private int aplyId;
    /** 신청계정 */
    private String acnt;
    /** 교육관아이디 */
    private int clssrmId;    
    /** 교육_연도 */
    private String eduYr;
    /** 교육_월 */
    private String eduMm;
    /** 프로그램아이디 */
    private int prgrmId;
    /** 프로그램_이름 */
    private String prgrmNm;
    /** 교육관_이름 */
    private String clssrmNm;
    /** 신청상태_신청완료_수 */
    private String aplyCmpltCnt;
    /** 신청상태_대기신청_수 */
    private String wtngAplyCnt;
    /** 신청상태_승인대기_수 */
    private String wtngApprvCnt;
    /** 신청상태_신청취소_수 */
    private String cnclAplyCnt;
    /** 신청상태_승인거부_수 */
    private String dnlApprvCnt;
    
    /** 접수_방법_코드 */
    private String rcptMthdCd;
    /** 접수_방법_코드명 */
    private String rcptMthdNm;
    
    /** 교육일자 */
    private String de;
    /** 회차ID */
    private String tmeId;
    /** 회차명 */
    private String tmeNm;
    /** 신청일자 */
    private String rcptDt;
    /** 기관아이디 */
    private int instid;
    /** 기관명 */
    private String instNm;
    /** 신청인아이디 */
    private int aplyUserid;
    /** 신청인명 */
    private String aplyUserNm;
    /** 회차_일정아이디 */
    private int tmeSchdlid;
    /** 우편번호 */
    private String zip;
    /** 주소 */
    private String addr;
    /** 주소_상세 */
    private String addrDtl;
    /** 시도_코드 */
    private String ctprvnCd;
    /** 전화번호 */
    private String telno;
    /** 휴대폰번호 */
    private String moblphon;
    /** 이메일 */
    private String eml;
    /** 교육_인원수 */
    private String eduNope;
    /** 교사_인원수 */
    private String tcherNope;
    /** 기타 */
    private String etc;
    /** 쌍방향_온라인_교육_동의_여부 */
    private String towwayOnlnEduAgreYn;
    /** 상태_코드 */
    private String sttsCd;
    /** 상태_이름 */
    private String sttsNm;
    /** 교육대상 코드 저장용 */
    @NotEmpty(message = "교육대상을 선택해주십시오.")
    private String [] trgtCds;
    /** 대상_코드 */
    private String trgtCd;

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;    
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련 */
    private String searchKeyword;
    private String searchEduYr;
    private String searchClssrmId;
    private String searchClssrmMm;
    private String searchRcptMthdCd;
    private String searchSttsCd;
    private String searchType;
    private String searchDeDt;    
    private String searchRcptDt;    
    private String searchStartRcptDt;    
    private String searchEndRcptDt;    
    private String searchStartDeDt;    
    private String searchEndDeDt;    
}
