package com.kbrainc.plum.mng.dsgnPrgrm.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/*****
* 지정프로그램VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.dsgnPrgrm.model
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
public class DsgnPrgrmVo extends ParentRequestVo {

	/** 로그인사용자정보 */
	private UserVo user;

	/** 검색.키워드*/
	private String searchKeyword;

	/** 검색 진행상태 */
    private String searchSttsCd;

    /** 검색.지정시작일*/
    private String searchStartDsgnDe;

    /** 검색.지정시작일*/
    private String searchEndDsgnDe;

    /** 검색.차수*/
    private String searchDsgnCycl;

    /** 호출구분*/
    private String callSe;

    /** 이력 ID*/
    private int hstryid;

    /** 지정차수 */
    private int dsgnCycl;

    /** 지정일자 */
    private String dsgnDe;

    /** 프로그램이름 */
    private String prgrmNm;

    /** 기관이름 */
    private String instNm;

    /** 상태코드 */
    private String sttsCd;

    /** 상태코드 이름*/
    private String sttsCdNm;

    /** 변경신청 건 수 */
    private String chgAplyCnt;

    /** 운영결과 건 수*/
    private String operRsltCnt;

    /** 이행심사 건 수 */
    private String implmntIdntyCnt;

    /** 이의신청 건 수 */
    private String objcInfoCnt;

    /** 지정기간*/
    private String dsgnPrd;

    /** 등록자 이름*/
    private String rgtrNm;

    /**지원단 점수*/
    private String finalScr;

    /**신청자 자가진단 점수(서류심사) */
    private String dcmntSrngSrc;

    /**방문점검 점수(현장점검) */
    private String vstChkSrc;

    /**현장점검총평(체크리스트결과내용) */
    private String chklstRsltCn;

    @Size(max = 10, message = "지정시작일은 10자를 넘을 수 없습니다.")
    /** 지정획득일자*/
    private String dsgnObtainDe;

    @Size(max = 10, message = "지정시작일은 10자를 넘을 수 없습니다.")
    /** 지정시작일자 */
    private String dsgnBgngDe;

    @Size(max = 10, message = "지정종료일은 10자를 넘을 수 없습니다.")
    /** 지정종료일자 */
    private String dsgnEndDe;

    @Size(max = 10, message = "지정취소일은 10자를 넘을 수 없습니다.")
    /** 지정취소일자 */
    private String dsgnCnclDe;

    /** 사업자등록번호 */
    private String brno;

    /** 지정 번호 차수 */
    private String cyclNo;

    /** cell idx */
    private String cidx;

    /** 신청 ID*/
    private String aplyid;

    /** 변경 사유 */
    private String chgRsn;

    /** 신청상태코드 */
    private String aplySttsCd;

    /** 신청상태코드 이름*/
    private String aplySttsCdNm;

    /** 상태수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date sttsMdfcnDt;

    /** 동일내용 여부 */
    private String samenssYn;

    /** 동일내용 */
    private String samenssCn;

    /** 프로그램변경 여부 */
    private String prgrmChgYn;

    /** 프로그램 변경 내용 */
    private String prgrmChgCn;

    /** 예산 변경 여부 */
    private String bgtChgYn;

    /** 예산 변경 내용 */
    private String bgtChgCn;

    /** 지도자 변경 여부 */
    private String ldrChgYn;

    /** 지도자 변경 내용 */
    private String ldrChgCn;

    /** 교육환경 변경 여부 */
    private String eduEnvChgYn;

    /** 교육환경 변경 내용 */
    private String eduEnvChgCn;

    /** 보완 요청 내용 */
    @Size(max = 1000, message = "보완 요청 내용은 1000자를 넘을 수 없습니다.")
    private String splmntDmndCn;

	/*******************************************************/

    /** 신청자이메일 */
    private String aplcntEml;

    /** 컨설팅진행여부 */
    private String cnsltngPrgrsYn;

    /** 기관홈페이지 */
    private String instHmpg;

    /** 기관상세주소 */
    private String instDtladdr;

    /** 지정번호 */
    @Size(max = 20, message = "지정번호는 20자를 넘을 수 없습니다.")
    private String dsgnNo;

    /** 신청자이름 */
    private String aplcntNm;

    /** 체크리스트아이디 */
    private int chklstid;

    /** 기관연락처 */
    private String instCntct;

    /** 기관주소 */
    private String instAddr;

    /** 수정자아이디 */
    private int mdfrid;

    /** 신청자아이디 */
    private int aplcntid;

    /** 기관이메일 */
    private String instEml;

    /** 승인자아이디 */
    private int autzrid;

    /** 기관우편번호 */
    private String instZip;

    /** 기관아이디 */
    private int instid;

    /** 기관지역코드 */
    private String instRgnCd;

    /** 신청일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date aplyDt;

    /** 파일그룹아이디 */
    private Integer filegrpid;

    /** 신청자휴대폰 */
    private String aplcntMoblphon;

    /** 컨설텅아이디 */
    private Integer cnsltngid;

    /** 기관유형코드 */
    private String instTypeCd;

    /** 우수성 */
    private String dstnctn;

    /** 숙박여부 */
    private String styYn;

    /** 전체월 */
    private String wholMm;

    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;

    /** 2월 */
    private String feb;

    /** 성인 */
    private String adult;

    /** 7월 */
    private String july;

    /** 교육시간 */
    private Integer eduHr;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 12월 */
    private String dcm;

    /** 교육사진파일그룹아이디 */
    private Integer eduPhotoFilegrpid;

    /** 1월 */
    private String jan;

    /** 고등학생 */
    private String hgschst;

    /** 교육횟수 */
    private Integer eduCnt;

    /** 6월 */
    private String june;

    /** 11월 */
    private String nov;

    /** 중학생 */
    private String msklsd;

    /** 조치방법 */
    private String actnMthd;

    /** 운영형태코드 */
    private String operFrmCd;

    /** 교육인원수 */
    private Integer eduNope;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 5월 */
    private String may;

    /** 10월 */
    private String oct;

    /** 초등학생 */
    private String schboy;

    /** 기상상황 */
    private String wetherSttn;

    /** 숙박낮 */
    private Integer styDaytm;

    /** 교육장소 */
    private String eduPlc;

    /** 4월 */
    private String apr;

    /** 9월 */
    private String sept;

    /** 유료여부 */
    private String pchrgYn;

    /** 유아 */
    private String infnt;

    /** 교육내용 */
    private String eduCn;

    /** 숙박밤 */
    private Integer styNight;

    /** 3월 */
    private String mar;

    /** 전체연령 */
    private String wholAge;

    /** 8월 */
    private String aug;

    /** 교육분 */
    private Integer eduMnt;

    /** 교육주제코드 */
    private String eduSbjctCd;

    /** 사후지도자평가도구 */
    private String aftrLdrEvlTl;

    /** 사전인솔자평가항목 */
    private String bfrGdrEvlArtcl;

    /** 수업일지작성여부 */
    private String clsjrnlWrtYn;

    /** 사후지도자평가항목 */
    private String aftrLdrEvlArtcl;

    /** 등록일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDt;

    /** 사후참여자평가도구 */
    private String aftrPrtpntEvlTl;

    /** 데이터베이스작성여부 */
    private String databaseWrtYn;

    /** 사전지도자평가도구 */
    private String bfrLdrEvlTl;

    /** 사후참여자평가항목 */
    private String aftrPrtpntEvlArtcl;

    /** 사후인솔자평가도구 */
    private String aftrGdrEvlTl;

    /** 사전지도자평가항목 */
    private String bfrLdrEvlArtcl;

    /** 사전참여자평가도구 */
    private String bfrPrtpntEvlTl;

    /** 사후인솔자평가항목 */
    private String aftrGdrEvlArtcl;

    /** 평가목적 */
    private String evlPrps;

    /** 사전참여자평가항목 */
    private String bfrPrtpntEvlArtcl;

    /** 사전인솔자평가도구 */
    private String bfrGdrEvlTl;

    /** 심사진행상태 */
    private String srngSttsCd;

    /** 심사위원아이디 */
    private Integer jdgsid;

    /** 업무내용 */
    private String taskCn;

    /** 이름 */
    private String nm;

    /** 구분 */
    private String se;

    /** 지도자아이디 */
    private Integer ldrid;

    /** 책임개발자여부 */
    private String snrstfdvlprYn;

    /** 운영이전홍보시기 */
    private String operBfrPrmtnSess;

    /** 운영이후홍보방법 */
    private String operAftrPrmtnMthd;

    /** 기타내용 */
    private String etcCn;

    /** 운영이후홍보시기 */
    private String operAftrPrmtnSess;

    /** 기타여부 */
    private String etcYn;

    /** 운영중간홍보방법 */
    private String operMdlPrmtnMthd;

    /** 온라인신청여부 */
    private String onlnAplyYn;

    /** 운영중간홍보시기 */
    private String operMdlPrmtnSess;

    /** 신청서접수여부 */
    private String aplfrmRcptYn;

    /** 운영이전홍보방법 */
    private String operBfrPrmtnMthd;

    /** 전화접수여부 */
    private String telRcptYn;

    /** 종료분 */
    private Integer endMnt;

    /** 일정아이디 */
    private Integer schdlid;

    /** 종료시간 */
    private Integer endHr;

    /** 시작분 */
    private Integer bgngMnt;

    /** 시작시간 */
    private Integer bgngHr;

    /** 과정 */
    private String crs;

    /** 차시 */
    private String rnd;

    /** 완료처리일자 */
    private String cmptnPrcsDe;

    /** 의견내용 */
    private String opnnCn;

    /** 보완요청아이디 */
    private Integer splmntDmndid;

    /** 요청아이디 */
    private Integer dmndid;

    /** 요청 내용 */
    private String dmndCn;

    /** 요청 서류 */
    private String dmndDcmnt;

    /** 차수아이디 */
    private Integer cyclid;

    /** 결과차수아이디 */
    private Integer rsltCyclid;

    /** 책임개발자_이름 */
    private String snrstfdvlprNm;

    /** 책임개발자_이메일 */
    private String snrstfdvlprEml;

    /** 책임개발자_전화번호 */
    private String snrstfdvlprTelno;

    /** 책임개발자_휴대폰번호 */
    private String snrstfdvlprMoblphon;

    /** 담당자_이름 */
    private String picNm;

    /** 담당자_이메일 */
    private String picEml;

    /** 담당자_전화번호 */
    private String picTelno;

    /** 담당자_휴대폰번호 */
    private String picMoblphon;

    /** 프로그램_유형_코드 */
    private String prgrmTypeCd;

    /** 보험_가입_여부 */
    private String insrncJoinYn;

    /** 보험_사유 */
    private String insrncRsn;

    /** 변경_여부 */
    private String chgYn;

    /** 활동_장소 */
    private String actvtPlc;

    /** 안전사고_유무 */
    private String sftyacdntEnnc;

    /** 발급_여부 */
    private String issuYn;

    /** 발급_건수 */
    private String issuNocs;

    /** 발급_합계 */
    private String issuSum;

    /** 교육목적 */
    private String eduPrps;

    /** 적절성 */
    private String appro;

    /** 프로그램_우수성 */
    private String prgrmDstnctn;

    /** 운영_관리 */
    private String operMng;

    /** 평가 */
    private String evl;

    /** 자격_배치 */
    private String qlfcPostng;

    /** 안전_관리 */
    private String sftyMng;

    /** 다음_연도_프로그램 */
    private String nextYrPrgrm;

    /** 다음_연도_지도자 */
    private String nextYrLdr;

    /** 다음_연도_교육_환경 */
    private String nextYrEduEnv;

    /** 차수           */
    private String cycl;

    /** 제출_시작_일자 */
    private String sbmsnBgngDe;

    /** 제출_종료_일자 */
    private String sbmsnEndDe;

    /** 제출_기간      */
    private String sbmsnPrd;

    /** 제출_상태_코드      */
    private String sbmsnSttsCd;

    /** 제출_상태_이름      */
    private String sbmsnSttsCdNm;

    /** 운영 형태 이름    */
	private String operFrmNm;

	/** 교육 주제 목록    */
	private String eduSbjctNmLst;

	/** 교육 대상 */
	private String eduTarget;

	/** 참가비 */
    private Integer etrfee;

	/** 자부담 */
	private Integer slfpy;

	/** 기타(후원) */
	private Integer etcIncm;

	/** 이력건수 */
	private Integer hstryCnt;

	/** 지정번호 그룹 */
	private String grpDsgnNo;

	/** 기관유형코드 이름*/
	private String instTypeCdNm ;

    /** 제출_확인 */
    private String sbmsnIdnty;
    /** 심사아이디 */
    private String srngid;

    /** 담당자아이디 */
    private String picid;

    /** 방문_일자 */
    private String vstDe;

    /** 방문_시간 */
    private String vstHr;

    /** 방문_분 */
    private String vstMnt;

    /** 종합_의견 */
    @Size(max = 2000, message = "종합의견은 2000자를 넘을 수 없습니다.")
    private String gnrlzOpnn;

    /** 계획 ID */
    private String planid;

    /** 등급_코드 */
    private String grdCd;

    /** 작성자아이디 */
    private String wrtrid;

    /** 보완개선 요청서 */
    private String splmntImprvDmndln;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    /** 보완요청 완료일자 */
    private String dmndCmptnDe;

    /** 보완요청 계획서 */
    private String splmntImprvWrtpln;

    /** 보완요청 결과보고서 */
    private String splmntImprvltRptp;

    /** 보안개선계획 */
    @Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다.")
    private String cn;

    /** 총괄의견 */
    @Size(max = 2000, message = "총괄의견은 2000자를 넘을 수 없습니다.")
    private String smrizeOpnn;

    /** 최종_제출_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date lastSbmsnDt;



    /** 체크 값 */
    private String chkVal;

    /** 운영실적 목록*/
    private List<OperPrfmncVo> operPrfmncLst;

    /*------------------------------------------------------------------------------------------*/

    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }

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

	/** 변경신청 상태코드 */
	public void setAplySttsCd(String aplySttsCd) throws Exception{

		this.aplySttsCd = aplySttsCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.aplySttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.aplySttsCd);
				this.aplySttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 제출 상태코드 */
	public void setSbmsnSttsCd(String sbmsnSttsCd) throws Exception{

		this.sbmsnSttsCd = sbmsnSttsCd;

		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.sbmsnSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.sbmsnSttsCd);
				this.sbmsnSttsCdNm = code.getCdNm();
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
