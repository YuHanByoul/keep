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

    /** 키워드.프로그램명 */
    private String searchPrgrmNm;

    /** 키워드.기관명 */
    private String searchInstNm;

    private String searchBrno;

    /** 검색 진행상태 */
    private String searchSttsCd;

    /** 검색 숙박여부 */
    private String searchStyYn;

    /** 신청일 검색시작일 */
    private String searchAplyStartDt;

    /** 신청일 검색종료일 */
    private String searchAplyEndDt;

    /** 심사위원 심사상태 */
    private String searchSrngSttsCd;

    /** 지원단 심사상태 */
    private String searchSrgnSttsCd;

    /** 현장점검지정일 시작일 */
    private String searchChckDsgnStartDt;

    /** 현장점검지정일 종료일 */
    private String searchChckDsgnEndDt;

    /** 검색 등록시작일 */
    private String searchRegStartDt;

    /** 검색 등록종료일 */
    private String searchRegEndDt;

	/** 프로그램 명 */
	private String prgrmNm;

	/** 기관 명 */
	private String instNm;

	/** 심사상태코드 */
	private String srgnSttsCd;

	/** 지원단상태코드*/
	private String srngSttsCd;

	/** 방문일자 */
	private String vstDe;

	/** 방문시간 */
	private String vstHr;

	/** 방문분 */
	private String vstMnt;

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
	private String aplcntMoblphon;

	/** 사업자_등록번호 */
	private String brno;

    /** 기관_이메일 */
	private String instEml;

    /** 기관_연락처 */
	private String instCntct;

    /** 기관_홈페이지 */
	private String instHmpg;

    /** 기관_유형_코드 */
	private String instTypeCd;

    /** 기관_지역_코드 */
	private String instRgnCd;

    /** 기관_우편번호 */
	private String instZip;

    /** 기관_주소 */
	private String instAddr;

    /** 기관_상세주소 */
	private String instDtladdr;

    /** 파일그룹아이디 */
	private int filegrpid;

    /** 상태_코드 */
	private String sttsCd;

    /** 지정_번호 */
	private String dsgnNo;

    /** 지정_일자 */
	private String dsgnDe;

    /** 지정_시작_일자 */
	private String dsgnBgngDe;

    /** 지정_종료_일자 */
	private String dsgnEndDe;

    /** 승인자아이디 */
	private int autzrid;

    /** 체크리스트아이디 */
	private int chklstid;

    /** 신청_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  aplyDt;

    /** 수정_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  mdfcnDt;

    /** 수정자아이디 */
	private int mdfrid;

    /** 등록_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  regDt;

    /** 등록자아이디 */
	private int rgtrid;

	/** 보완요청아이디 */
	private int splmntdmndid;

	/** 의견내용 */
	private  String opnnCn;
	/** 완료처리일자 */
	private  String cmptnPrcsDe;

	/**숙박_여부 */
	private String styYn;

    /**숙박_밤 */
	private int styNight;

    /**숙박_낮 */
	private int styDaytm;

    /**운영_형태_코드 */
	private String operFrmCd;

    /**1월 */
	private String jan;

    /**2월 */
	private String feb;

    /**3월 */
	private String mar;

    /**4월 */
	private String apr;

    /**5월 */
	private String may;

    /**6월 */
	private String june;

    /**7월 */
	private String july;

    /**8월 */
	private String aug;

    /**9월 */
	private String sept;

    /**10월 */
	private String oct;

    /**11월 */
	private String nov;

    /**12월 */
	private String dcm;

    /**전체_월 */
	private String wholMm;

    /**유아 */
	private String infnt;

    /**초등학생 */
	private String schboy;

    /**중학생 */
	private String msklsd;

    /**고등학생 */
	private String hgschst;

    /**성인 */
	private String adult;

    /**전체_연령 */
	private String wholAge;

    /**교육_장소 */
	private String eduPlc;

    /**교육_인원수 */
	private int eduNope;

    /**교육_횟수 */
	private int  eduCnt;

    /**교육_시간 */
	private int  eduHr;

    /**교육_분 */
	private int  eduMnt;

    /**유료_여부 */
	private String pchrgYn;

    /**참가비 */
	private String etrfee;

    /**교육_목적 */
	private String eduPrps;

    /**적절성 */
	private String appro;

    /**우수성 */
	private String dstnctn;

    /**교육_내용 */
	private String eduCn;

    /**기상_상황 */
	private String wetherSttn;

    /**조치_방법 */
	private String actnMthd;

	/**교육 주제 코드 */
	private String eduSbjctCd;

    /**교육_사진_파일그룹아이디 */
	private int  eduPhotoFilegrpid;

    /**평가_목적 */
    private String evlPrps;

    /**사전_지도자_평가_항목 */
    private String bfrLdrEvlArtcl;

    /**사전_지도자_평가_도구 */
    private String bfrLdrEvlTl;

    /**사후_지도자_평가_항목 */
    private String aftrLdrEvlArtcl;

    /**사후_지도자_평가_도구 */
    private String aftrLdrEvlTl;

    /**사전_참여자_평가_항목 */
    private String bfrPrtpntEvlArtcl;

    /**사전_참여자_평가_도구 */
    private String bfrPrtpntEvlTl;

    /**사후_참여자_평가_항목 */
    private String aftrPrtpntEvlArtcl;

    /**사후_참여자_평가_도구 */
    private String aftrPrtpntEvlTl;

    /**사전_인솔자_평가_항목 */
    private String bfrGdrEvlArtcl;

    /**사후_인솔자_평가_도구 */
    private String aftrGdrEvlTl;

    /**데이터베이스_작성_여부 */
    private String databaseWrtYn;

    /**수업일지_작성_여부 */
    private String clsjrnlWrtYn;

    /**기타_여부 */
    private String etcYn;

    /**기타_내용 */
    private String etcCn;

    /**심사_일시 */
    private Date srngDt;

    /**심사위원_ID */
    private String jdgsid;

    /**안전관리 사전인증여부 */
    private String sftyMngYn;

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
