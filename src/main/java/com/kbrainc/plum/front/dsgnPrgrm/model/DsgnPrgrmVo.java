package com.kbrainc.plum.front.dsgnPrgrm.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.mng.asgsysSrng.model.EmrgcyActnPlanVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/*****
* 지정프로그램VO 클래스
*
* <pre>
* com.kbrainc.plum.front.dsgnPrgrm.model
* - AsgsysSrngVo.java
* </pre>
*
* @ClassName : DsgnPrgrmVo
* @Description : 지정프로그램VO 클래스
* @author : kbrain
* @date : 2022. 12. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.dsgnPrgrmVo")
public class DsgnPrgrmVo extends ParentRequestVo {

	/** 로그인사용자정보 */
    private UserVo user;

    /** 프로그램 운영일정 목록*/
    private List<PrgrmSchdlVo> prgrmSchdlLst;

    /** 프로그램 비상조치계획 목록*/
    private List<EmrgcyActnPlanVo> emrgcyActnPlanLst;

    /** 검색.프로그램 명 */
    private String searchPrgrmNm;

    /** 검색.운영기관 명 */
    private String searchInstNm;

    /** 검색.지역 코드 */
    private String searchRgnCd;

    /** 검색.교육주제 코드*/
    private String searchSbjctCd;

    /** 검색.교육대상 코드*/
    private String searchEduTarget;

    /** 검색.참가비*/
    private String searchEtrfee;

	/** 프로그램아이디 */
	private Integer prgrmid;

	/** 우수성 아이디*/
	private Integer dstnctnid;

	/** 프로그램 명 */
	private String prgrmNm;

	/** 기관지역코드 */
	private Integer instRgnCd;

	/** 지역 명 */
	private String ctprvnNm;

	/** 지역 코드 */
	private Integer ctprvnCd;

	/** 숙박 여부 */
	private String styYn;

	/** 숙박 밤 */
	private Integer styNight;

	/** 숙박 낮 */
	private Integer styDaytm;

	/** 운영 형태 코드 */
	private String operFrmCd;

	/** 1월 */
	private String jan;

	/** 2월 */
	private String feb;

	/** 3월 */
	private String mar;

	/** 4월 */
	private String apr;

	/** 5월 */
	private String may;

	/** 6월 */
	private String june;

	/** 7월 */
	private String july;

	/** 8월 */
	private String aug;

	/** 9월 */
	private String sept;

	/** 10월 */
	private String oct;

	/** 11월 */
	private String nov;

	/** 12월 */
	private String dcm;

	/** 전체 월 */
	private String wholMm;

	/** 유아 */
	private String infnt;

	/** 초등학생 */
	private String schboy;

	/** 중학생 */
	private String msklsd;

	/** 고등학생 */
	private String hgschst;

	/** 성인 */
	private String adult;

	/** 전체 연령 */
	private String wholAge;

	/** 교육 장소 */
	private String eduPlc;

	/** 교육 인원수 */
	private Integer eduNope;

	/** 교육 횟수 */
	private Integer eduCnt;

	/** 교육 시간 */
	private Integer eduHr;

	/** 교육 분 */
	private Integer eduMnt;

	/** 운영 형태 이름 */
	private String operFrmNm;

	/** 유료 여부 */
	private String pchrgYn;

	/** 참가비 */
	private double etrfee;

	/** 자부담 */
	private String slfpy;

	/** 후원 */
	private String etcIncm;

	/** 교육 목적 */
	private String eduPrps;

	/** 교육 주제 명 목록 */
	private String eduSbjctNmLst;

	/** 교육 주제 코드 목록 */
	private String eduSbjctCdLst;

	/** 교육대상*/
	private String eduTarget;

	/** 적절성 */
	private String appro;

	/** 우수성 */
	private String dstnctn;

	/** 교육 내용 */
	private String eduCn;

	/** 기상 상황 */
	private String wetherSttn;

	/** 조치 방법 */
	private String actnMthd;

	/** 교육 사진 파일그룹아이디 */
	private Integer eduPhotoFilegrpid;

	/** 지정_번호 */
	private String dsgnNo;

	/** 기관 주소 */
	private String instAddr;

	/** 지정일 */
	private String dsgnDe;

	/** 기관_상세주소*/
    private String instDtlAddr;

    /** 기관_홈페이지*/
    private String instHmpg;

    /** 기관_이메일*/
    private String instEml;

    /** 코드 명*/
    private String cdNm;

    /** 운영일정 */
    private String targetMm;

    /** 교육주제 */
    private String eduSbjctCdNm;

    /** 교육_시간 */
    private String eduTime;

    /** 담당자명 */
    private String aplcntNm;

    /** 담당자_아이디 */
    private Integer aplcntid;

    /** 담당자_연락처 */
    private String aplcntMoblphon;

    /** 담당자_이메일 */
    private String aplcntEml;

    /** 일정 */
    private String schdl;

    /** 계획 아이디 */
    private Integer planid;

    /** 대체 프로그램 명*/
    private String sbstnPrgrmNm;

    /** 차시*/
	private String rnd;

    /** 등록자아이디*/;
	private Integer rgtrid;

    /** 종료_분*/
	private Integer endMnt;

    /** 일정아이디*/
	private Integer schdlid;

    /** 종료_시간*/
	private Integer endHr;

    /** 시작_분*/
	private Integer bgngMnt;

    /** 시작_시간*/
	private Integer bgngHr;

    /** 과정*/
	private String crs;

	/** 수정 일시 */
	private Date mdfcnDt;

	/** 수정자아이디 */
	private Integer mdfrid;

	/** 등록 일시 */
	private Date regDt;

	/** 기관 연락처 */
	private String instCntct;

	/** 기관 우편번호 */
	private String instZip;

	/** 기관 상세 주소*/
	private String instDtladdr;

	/** 상태 코드*/
	private String sttsCd;

	/** 지정 차수 */
	private String dsgnCycl;

	/** 지정 시작 일시 */
	private String dsgnBgngDe;

	/** 지정 종료 일시 */
	private String dsgnEndDe;

	/** 승인자 id */
	private String autzrid;

	/** 체크리스트 id  */
	private String chklstid;

	/** 컨설팅 진행 여부   */
	private String cnsltngPrgrsYn;

	/** 컨설팅 id */
	private String cnsltngid;

	/** 신청일시 */
	private String aplyDt;

	/** 평가아이디 */
    private String evlid;

    /** 사전지도자평가항목 */
    private String bfrLdrEvlArtcl;

    /** 사전지도자평가도구 */
    private String bfrLdrEvlTl;

    /** 사후지도자평가항목 */
    private String aftrLdrEvlArtcl;

    /** 사후지도자평가도구 */
    private String aftrLdrEvlTl;

    /** 사전참여자평가항목 */
    private String bfrPrtpntEvlArtcl;

    /** 사전참여자평가도구 */
    private String bfrPrtpntEvlTl;

    /** 사후참여자평가항목 */
    private String aftrPrtpntEvlArtcl;

    /** 사후참여자평가도구 */
    private String aftrPrtpntEvlTl;

    /** 사전인솔자평가항목 */
    private String bfrGdrEvlArtcl;

    /** 사전인솔자평가도구 */
    private String bfrGdrEvlTl;

    /** 사후인솔자평가항목 */
    private String aftrGdrEvlArtcl;

    /** 사후인솔자평가도구 */
    private String aftrGdrEvlTl;

    /** 데이터베이스작성여부 */
    private String databaseWrtYn;

    /** 수업일지작성여부 */
    private String clsjrnlWrtYn;

    /** 기타여부 */
    private String etcYn;

    /** 기타내용 */
    private String etcCn;

	/** 첨부파일 */
    private String fileid;
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;

    /** 기관정보 */
    /** 기관 아이디 */
    private int instid;

    /** 기관 이름 */
    @Size(max = 100, message = "기관 이름은 100자를 넘을 수 없습니다.")
    private String instNm;

    /** 기관코드 */
    @Size(max = 20, message = "기관코드는 20자를 넘을 수 없습니다.")
    private String instCd;

    /** 기관유형코드 */
    @Size(max = 20, message = "기관유형코드는 20자를 넘을 수 없습니다.")
    private String instTypeCd;

    /** 기관 코드명 */
    private String instCdNm;

    /** 기관유형코드명 */
    private String instTypeCdNm;

    /** 지역 코드 */
    private String rgnCd;

    /** 시도 */
    private String sidoNm;

    /** 시군구*/
    private String signguNm;

    /** 시군구_코드*/
    private String signguCd;

    /** 사업자번호 */
    @Size(max = 10, message = "사업자번호는 10자를 넘을 수 없습니다.")
    private String brno;

    /** 대표자이름 */
    @Size(max = 10, message = "대표자 이름은 10자를 넘을 수 없습니다.")
    private String rprsvNm;

    /** 전화번호 */
    @Size(max = 40, message = "전화번호는 40자를 넘을 수 없습니다.")
    private String telno;

    /** 팩스번호 */
    @Size(max = 40, message = "팩스번호는 40자를 넘을 수 없습니다.")
    private String fxno;

    /** 우편번호 */
    @Size(max = 10, message = "우편번호는 10자를 넘을 수 없습니다.")
    private String zip;

    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;

    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;

    /** 홈페이지 */
    @Size(max = 100, message = "홈페이지는 100자를 넘을 수 없습니다.")
    private String hmpg;




}
