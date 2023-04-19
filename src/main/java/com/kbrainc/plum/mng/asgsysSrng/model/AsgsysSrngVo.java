package com.kbrainc.plum.mng.asgsysSrng.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.dsgnPrgrm.model.OperPrfmncVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

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

	/** 사용자 ip */
	private String userIp;

	/** 호출화면 */
	private String opner;

	/** RANK */
	private String rnk;

	/** rowSpan */
	private String rowSpan;

    /** 검색 진행상태 */
    private String searchSttsCd;

    /** 검색 사용자아이디 */
    private String searchUserid;

    /** 검색 자격증명 */
    private String searchCrtfctNm;

    /** 검색 전문분야(환경주제) */
    private String searchSbjctCd;

    /** 검색 활동가능지역 */
    private String searchRgnCd;

    /** 검색 그룹ID */
    private String grpId;

    /** 검색 그룹 명 */
    private String grpNm;

    /** 검색 계정 명 */
    private String acnt;

    /** 검색 사용여부 */
    private String useYn;

    /** 검색 등록전문가 수 */
    private String regExprtCnt;

    /** 사용자ID(계정명) 그룹*/
    private String userAcntGrp;

    /** 사용자ID(userid) 그룹*/
    private String useridGrp;

    /** 사용자 명 그룹*/
    private String userNmGrp;

    /** 사용자 이메일 그룹 */
    private String userEmlGrp;

    /** 사용자 모바일 그룹 */
    private String userMoblphonGrp;

    /** 지원단 계정 */
    private String sprtgrpAcnt;

    /** 지원단 이름 */
    private String sprtgrpNm;

    /** 지원단 이름(계정) */
    private String sprtgrpNmAcnt;

    /** 지원단 이메일 */
    private String sprtgrpEml;

    /** 지원단 모바일번호 */
    private String sprtgrpMoblphon;

    /** 검색 숙박여부 */
    private String searchStyYn;

    /** 신청일 검색시작일 */
    private String searchAplyStartDt;

    /** 신청일 검색종료일 */
    private String searchAplyEndDt;

    /** 심사위원 심사상태 */
    private String searchSrngSttsCd;

    /** 심사위원 심사 총점평균 */
    private BigDecimal sumAvg;

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

	/** 심사상태코드_명 */
	private String srgnSttsCdNm;

	/** 지원단상태코드*/
	private String srngSttsCd;

	/** 지원단상태코드_명*/
	private String srngSttsCdNm;

	/** 방문일자 */
	private String vstDe;

	/** 방문시간 */
	private String vstHr;

	/** 방문분 */
	private String vstMnt;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	/** 방문일시 */
	private String vstDt;

    /** 프로그램아이디 */
	private Integer prgrmid;

	/** 프로그램 평가 아이디 */
	private Integer evlid;

	/** 프로그램 이력 아이디 */
	private Integer hstryid;

	/** 운영관리 프로그램아이디 */
	private Integer operMngPrgrmid;

	/** 안전관리 아이디*/
	private Integer sftyMngId;

	/** 트리순서 */
	private Integer treeOrd;

	/** 컨설팅 아이디*/
	private Integer cnsltngid;

	/** 컨설팅 진행여부*/
	private String cnsltngPrgrsYn;

	/** 컨설팅 종류코드*/
	private String cnsltngKndCd;

	/** 우수성KEY */
	private Integer prgrmKey;

    /** 기관아이디 */
	private Integer instid;

    /** 신청자아이디 */
	private Integer aplcntid;

    /** 신청자_이름 */
	private String aplcntNm;

    /** 신청자_이메일 */
	private String aplcntEml;

    /** 신청자_휴대폰 */
	private String aplcntMoblphon;

	/** 사업자_등록번호 */
	private String brno;

	/** 사업자_등록번호 */
	private String rprsvNm;

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

	/** 기관_지역_코드 명*/
	private String instRgnCdNm;

    /** 기관_우편번호 */
	private String instZip;

    /** 기관_주소 */
	private String instAddr;

    /** 기관_상세주소 */
	private String instDtladdr;

    /** 파일그룹아이디 */
	private Integer filegrpid;

	/** 신청 첨부파일 아이디 */
	private Integer aplyFilegrpid;

    /** 상태_코드 */
	private String sttsCd;

	/** 상태_코드_명 */
	private String sttsCdNm;

    /** 지정_번호 */
	private String dsgnNo;

	/** 지정_차수 */
	private int dsgnCycl;

	/** (지정)차수번호 */
	private String cyclNo;

	/** 지정_기간 */
	private String dsgnPrd;

    /** 지정_일자 */
	private String dsgnDe;

	/** 시작_일자 */
	private String  bgngDe;

	/** 종료_일자 */
	private String  endDe;

	/** 학교 명 */
	private String  schlNm;

	/** 전공  */
	private String  mjr;

	/** 학위  */
	private String  dgr;

    /** 지정_시작_일자 */
	private String dsgnBgngDe;

    /** 지정_종료_일자 */
	private String dsgnEndDe;

    /** 승인자아이디 */
	private Integer autzrid;

    /** 체크리스트아이디 */
	private Integer chklstid;

	/** 체크리스트 1LV 이름 */
	private String lv1key;

	/** 체크리스트 2LV 코드 */
	private String lv2key;

	/** 체크리스트 1LV 이름 */
	private String chkLv1Cd;

	/** 체크리스트 2LV 코드 */
	private String chkLv2Cd;

	/** 체크리스트 구분 코드 */
	private String chklstSeCd;

	/** 체크리스트 내용 */
	private String cn;

	/** 체크리스트 결과 내용*/
    private String chklstRsltCn;

    /** 체크리스트 결과 내용*/
    private Integer aplcntSbmsnid;

    /** 신청자 자가진단 점수*/
    private Integer aplcntScr;

    /** 체출구분코드*/
    private String sbmsnSeCd;

	/** 문항 id*/
	private Integer qitemid;

	/** 문항순서 */
	private Integer ordr;

	/** 점수 */
	private Integer scr;

	/** 제출상태코드 */
	private String sbmsnSttsCd;

	/** 배점 id*/
	private String altm;

	/** 항목코드*/
	private String dsgncrtrCd;

    /** 신청_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date  aplyDt;

    /** 수정_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  mdfcnDt;

    /** 수정자아이디 */
	private Integer mdfrid;

    /** 등록_일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
	private Date  regDt;

    /** 등록자아이디 */
	private Integer rgtrid;

	/** 보완요청아이디 */
	private Integer splmntDmndid;

	/** 의견내용 */
	@Size(max = 1000, message = "의견내용은 1000자를 넘을 수 없습니다.")
	private  String opnnCn;

	/** 팝업mode */
	private  String mode;

	/** 완료처리일자 */
	private  String cmptnPrcsDe;

	/**숙박_여부 */
	private String styYn;

    /**숙박_밤 */
	private Integer styNight;

    /**숙박_낮 */
	private Integer styDaytm;

    /**운영_형태_코드 */
	private String operFrmCd;

	/**체크리스트 운영_형태_코드 */
	private String chkOperFrmCd;

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
	private Integer eduNope;

    /**교육_횟수 */
	private Integer  eduCnt;

    /**교육_시간 */
	private Integer  eduHr;

    /**교육_분 */
	private Integer  eduMnt;

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

	/**교육 주제 코드 목록*/
	private String eduSbjctCdLst;

    /**교육_사진_파일그룹아이디 */
	private Integer  eduPhotoFilegrpid;

    /**평가_목적 */
	@Size(max = 50, message = "평가 목적은 50자를 넘을 수 없습니다.")
    private String evlPrps;


    /**사전_지도자_평가_항목 */
	@Size(max = 50, message = "사전 지도자 평가 항목은 50자를 넘을 수 없습니다." )
    private String bfrLdrEvlArtcl;

    /**사전_지도자_평가_도구 */
	@Size(max = 50, message = "사전 지도자 평가 도구는 50자를 넘을 수 없습니다." )
    private String bfrLdrEvlTl;

    /**사후_지도자_평가_항목 */
	@Size(max = 50, message = "사후 지도자 평가 항목은 50자를 넘을 수 없습니다." )
    private String aftrLdrEvlArtcl;

    /**사후_지도자_평가_도구 */
	@Size(max = 50, message = "사후 지도자 평가 도구는 50자를 넘을 수 없습니다." )
    private String aftrLdrEvlTl;

    /**사전_참여자_평가_항목 */
	@Size(max = 50, message = "사전 참여자 평가 항목은 50자를 넘을 수 없습니다." )
    private String bfrPrtpntEvlArtcl;

    /**사전_참여자_평가_도구 */
	@Size(max = 50, message = "사전 참여자 평가 도구는 50자를 넘을 수 없습니다." )
    private String bfrPrtpntEvlTl;

    /**사후_참여자_평가_항목 */
	@Size(max = 50, message = "사후 참여자 평가 항목은 50자를 넘을 수 없습니다." )
    private String aftrPrtpntEvlArtcl;

    /**사후_참여자_평가_도구 */
	@Size(max = 50, message = "사후 참여자 평가 도구는 50자를 넘을 수 없습니다." )
    private String aftrPrtpntEvlTl;

    /**사전_인솔자_평가_항목 */
	@Size(max = 50, message = "사전 인솔자 평가 항목은 50자를 넘을 수 없습니다." )
    private String bfrGdrEvlArtcl;

    /**사전_인솔자_평가_도구 */
	@Size(max = 50, message = "사전 인솔자 평가 도구는 50자를 넘을 수 없습니다." )
    private String bfrGdrEvlTl;

    /**사후_인솔자_평가_항목 */
	@Size(max = 50, message = "사후 인솔자 평가 항목은 50자를 넘을 수 없습니다." )
    private String aftrGdrEvlArtcl;

    /**사후_인솔자_평가_도구 */
	@Size(max = 50, message = "사후 인솔자 평가 도구는 50자를 넘을 수 없습니다." )
    private String aftrGdrEvlTl;

    /**데이터베이스_작성_여부 */
    private String databaseWrtYn;

    /**수업일지_작성_여부 */
    private String clsjrnlWrtYn;

    /**기타_여부 */
    @Size(max = 50, message = "기타내용은 50자를 넘을 수 없습니다.")
    private String etcYn;

    /**기타_내용 */
    private String etcCn;

    /**심사_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date srngDt;

    /**심사위원_ID */
    private String jdgsid;

    /** 심사 위원 이름 */
    private String jdgsNm;

    /**안전관리 사전인증여부 */
    private String sftyMngYn;

    /** 지도자_교육_시기 */
	private String ldrEduSess;

	/** 지도자아이디 */
	private Integer ldrid;

	/** 구분 */
	private String se;

	/** 업무 내용 */
	private String taskCn;

	/** 생년월일 */
	private String brdt;

	/** 책임개발자 여부 */
	private String snrstfdvlprYn;

	/** 활동명 */
	private String actvtNm;

	/** 자격명 */
	private String qlfcNm;

	/** 취득일 */
	private String acqsDe;

	/** 급수  */
	private String grd;

	/** 발령청  */
	private String wrkplc;

	/** 활동 유형 */
	private String actvtType;

    /** 지도자_교육_담당자 */
    private String ldrEduPic;

    /** 지도자_교육_내용 */
    private String ldrEduCn;

    /** 참가자_교육_시기 */
    private String prtcpntEduSess;

    /** 참가자_교육_담당자 */
    private String prtcpntEduPic;

    /** 참가자_교육_내용 */
    private String prtcpntEduCn;

    /** 사전_인증_여부 */
    private String bfrCertYn;

    /** 사전_인증_파일그룹아이디 */
    private Integer bfrCertFilegrpid;

    /** (심사)양식_id */
    private Integer formid;

    /** (심사)합계 점수 */
    private Integer sumScr;

    /** 심사 의견 */
    private String srngOpnn;

    /** 심사 결과 ID */
    private String rsltid;

	/** 참가비_수입 */
	private BigDecimal etrfeeIncm;

	/** 기타_수입 */
	private BigDecimal etcIncm;

	/** 자부담 */
	private BigDecimal slfpy;

	/** 예산 구성 총계 */
	private BigDecimal btgSum;

	/** 운영_이전_홍보_시기 */
	private String operBfrPrmtnSess;

	/** 운영_이전_홍보_방법 */
	private String operBfrPrmtnMthd;

	/** 운영_중간_홍보_시기 */
	private String operMdlPrmtnSess;

	/** 운영_중간_홍보_방법 */
	private String operMdlPrmtnMthd;

	/** 운영_이후_홍보_시기 */
	private String operAftrPrmtnSess;

	/** 운영_이후_홍보_방법 */
	private String operAftrPrmtnMthd;

	/** 전화_접수_여부 */
	private String telRcptYn;

	/** 신청서_접수_여부 */
	private String aplfrmRcptYn;

	/** 온라인_신청_여부 */
	private String onlnAplyYn;

	/** 교구_시설_ID */
	private String tchaidfcltid;

	/** 세부_프로그램_이름 */
    private String dtlPrgrmNm;

    /** 세부_프로그램_교구 */
    private String tchaid;

    /** 수량 */
    private String qnty;

    /** 지원단 ID */
    private String sprtgrpid;

    /** 지원단 ID (이전)*/
    private String bfrSprtgrpid;

    /** 보완요청 ID */
    private String splmntDmndOpnn;

    /** 사용자 ID */
    private String userid;

    /** 이름 */
    private String nm;

    /** 이메일 */
    private String eml;

    /** 전화번호 */
    private String telno;

    /** 자격증명 */
    private String crtfctNm;

    /** 주제코드 */
    private String sbjctCd;

    /** 주제코드 명*/
    private String sbjctCdNm;

    /** 지역코드 */
    private String rgnCd;

    /** 지역코드 */
    private String rgnCdNm;

    /** 제출ID */
    private Integer sbmsnid;

    /** 확인사항 */
    private String idntyMttr;

    /** 코드명 */
    private String cdNm;

    /** 합계 배점 */
    private Integer sumAltm;

    /** 체크리스트 점수 */
    private Integer chkLstScr;

    /** 제출 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date sbmsnDt;

    private Integer colCnt;

    /** 동적 항목을 위한 변수 */
    /** id */
    private Integer colId1;
    private Integer colId2;
    private Integer colId3;
    private Integer colId4;
    private Integer colId5;
    private Integer colId6;
    private Integer colId7;
    private Integer colId8;
    private Integer colId9;
    private Integer colId10;

    private String colVal1;
    private String colVal2;
    private String colVal3;
    private String colVal4;
    private String colVal5;
    private String colVal6;
    private String colVal7;
    private String colVal8;
    private String colVal9;
    private String colVal10;

    /** 동적 항목을 위한 변수 */

    /** KEY COUNT */
    private Integer keyCnt;

    /** 프로그램 운영일정 목록*/
    private List<PrgrmSchdlVo> prgrmSchdlLst;

    /** 프로그램 비상조치계획 목록*/
    private List<EmrgcyActnPlanVo> emrgcyActnPlanLst;

    /** 체크리스트 답변 목록 List*/
    private List<ChklstAnsVo> ansLst;

    /** 심사양식 목록*/
    private List<DsgnSrngFormVo> dsgnSrngFormLst;

    /** 지출항목 목록*/
    private List<ExpndArtclVo> expndArtclLst;

    /** 교구 및 시설 목록*/
    private List<TchaidFcltVo> tchaidFcltLst;

    /** 학력 목록 */
    private List<AcbgVo> acbgLst;

    /** 자격 목록 */
    private List<QlfcVo> qlfcLst;

    /** 경력 목록 */
    private List<CareerVo> careerLst;

    /** 지도자 목록 */
    private List<LdrVo> ldrLst;

    /** 운영실적 목록 */
    private List<OperPrfmncVo> operPrfmncLst;

    /** 학력 ID */
    private Integer acbgid;

    private String dp1;
    private String dp2;
    private String lv;
    private String qrow;

    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }

    /** 호출구분*/
    private String callSe;

    /** 상태코드 */
	public void setSttsCd(String sttsCd) throws Exception{

        this.sttsCd = sttsCd;

        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.sttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
             }catch(Exception e) {
                //e.printStackTrace();
                return;
             }
        }
	}

	/** 심사위원 상태코드 */
	public void setSrgnSttsCd(String srgnSttsCd) throws Exception{

		this.srgnSttsCd = srgnSttsCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.srgnSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.srgnSttsCd);
				this.srgnSttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 지원단 상태코드 */
	public void setSrngSttsCd(String srngSttsCd) throws Exception{

		this.srngSttsCd = srngSttsCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.srngSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.srngSttsCd);
				this.srngSttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 전문분야 코드 */
	public void setSbjctcd(String sbjctCd) throws Exception{

		this.sbjctCd = sbjctCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.sbjctCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.sbjctCd);
				this.sbjctCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** (활동가능) 지역 코드 */
	public void setRgnCd(String rgnCd) throws Exception{

		this.rgnCd = rgnCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.rgnCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.rgnCd);
				this.rgnCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

}
