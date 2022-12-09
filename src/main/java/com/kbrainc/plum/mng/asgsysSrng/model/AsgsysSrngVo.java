package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/***
* 지정제심사VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - AsgsysSrngVo.java
* </pre>
*
* @ClassName : asgsysSrngVo
* @Description : 지정제심사VO 클래스
* @author : kbrain
* @date : 2022. 12. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class AsgsysSrngVo extends ParentRequestVo {

    /** 로그인사용자정보 */
	private UserVo user;



    /** 지정신청목록 검색용 파라메터*/
    /** 키워드.프로그램명  */
    private String searchPrgrmNm;

    /** 키워드.기관명  */
    private String searchInstNm;

    /** 키워드.사업자번호  */
    private String brno;

    /** 진행상태   */
    private String searchSttsCd;

    /** 신청일 검색시작일   */
    private String searchAplyStartDt;

    /** 신청일 검색종료일   */
    private String searchAplyEndDt;

    /** 심사위원 심사상태 */
    private String searchSrngSttsCd;

    /** 지원단 심사상태 */
    private String searchSrgnSttsCd;

    /** 현장점검지정일 시작일 */
    private String searchChckDsgnStartDt;

    /** 현장점검지정일 종료일 */
    private String searchChckDsgnEndDt;


	/** 프로그램 명 */
	private String PrgrmNm;

	/** 기관 명 */
	private String InstNm;

	/** 심사상태코드 */
	private String SrgnSttsCd;

	/** 지원단상태코드*/
	private String SrngSttsCd;

	/** 방문일자 */
	private String VstDe;

	/** 방문시간 */
	private String VstHr;

	/** 방문분 */
	private String VstMnt;

    /** 프로그램아이디 */
	private int prgrmid;

    /** 기관아이디 */
	private int instid;

    /** 신청자아이디 */
	private int aplcntid;

    /** 신청자_이름 */
	private String aplcntNm;

    /** 신청자_이메일 */
	private String aplcntEml;

    /** 신청자_휴대폰 */
	private String AplcntMoblphon;

    /** 기관_이메일 */
	private String InstEml;

    /** 기관_연락처 */
	private String InstCntct;

    /** 기관_홈페이지 */
	private String InstHmpg;

    /** 기관_유형_코드 */
	private String InstTypeCd;

    /** 기관_지역_코드 */
	private String InstRgnCd;

    /** 기관_우편번호 */
	private String InstZip;

    /** 기관_주소 */
	private String InstAddr;

    /** 기관_상세주소 */
	private String InstDtladdr;

    /** 파일그룹아이디 */
	private int filegrpid;

    /** 상태_코드 */
	private String SttsCd;

    /** 지정_번호 */
	private String DsgnNo;

    /** 지정_일자 */
	private String DsgnDe;

    /** 지정_시작_일자 */
	private String DsgnBgngDe;

    /** 지정_종료_일자 */
	private String DsgnEndDe;

    /** 승인자아이디 */
	private int autzrid;

    /** 체크리스트아이디 */
	private int chklstid;

    /** 신청_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  AplyDt;

    /** 수정_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  MdfcnDt;

    /** 수정자아이디 */
	private int mdfrid;

    /** 등록_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  RegDt;

    /** 등록자아이디 */
	private int RGTRID;


    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }
}
