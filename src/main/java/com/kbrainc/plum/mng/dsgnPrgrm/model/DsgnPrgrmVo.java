package com.kbrainc.plum.mng.dsgnPrgrm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/*****
* 지정제심사VO 클래스
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



    /** 지정종료일자 */
    private String dsgnEndDe;

    /** 기관지역코드 */
    private String instRgnCd;

    /** 신청일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date aplyDt;

    /** 파일그룹아이디 */
    private int filegrpid;

    /** 신청자휴대폰 */
    private String aplcntMoblphon;

    /** 지정시작일자 */
    private String dsgnBgngDe;

    /** 컨설텅아이디 */
    private int cnsltngid;

    /** 기관유형코드 */
    private String instTypeCd;



    /** 우수성 */
    private String dstnctn;

    /** 숙박여부 */
    private String styYn;

    /** 전체월 */
    private String wholMm;

    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private Date mdfcnDt;

    /** 2월 */
    private String feb;

    /** 성인 */
    private String adult;

    /** 7월 */
    private String july;

    /** 교육시간 */
    private int eduHr;

    /** 적절성 */
    private String appro;

    /** 프로그램아이디 */
    private int prgrmid;

    /** 12월 */
    private String dcm;

    /** 교육사진파일그룹아이디 */
    private int eduPhotoFilegrpid;

    /** 1월 */
    private String jan;

    /** 고등학생 */
    private String hgschst;

    /** 교육횟수 */
    private int eduCnt;

    /** 6월 */
    private String june;

    /** 교육목적 */
    private String eduPrps;

    /** 11월 */
    private String nov;

    /** 중학생 */
    private String msklsd;

    /** 조치방법 */
    private String actnMthd;

    /** 운영형태코드 */
    private String operFrmCd;

    /** 교육인원수 */
    private int eduNope;

    /** 등록자아이디 */
    private int rgtrid;

    /** 5월 */
    private String may;

    /** 참가비 */
    private BigDecimal etrfee;

    /** 10월 */
    private String oct;

    /** 초등학생 */
    private String schboy;

    /** 기상상황 */
    private String wetherSttn;

    /** 숙박낮 */
    private int styDaytm;

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
    private int styNight;

    /** 3월 */
    private String mar;

    /** 전체연령 */
    private String wholAge;

    /** 8월 */
    private String aug;

    /** 교육분 */
    private int eduMnt;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
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
    private int jdgsid;

    /** 업무내용 */
    private String taskCn;

    /** 이름 */
    private String nm;

    /** 구분 */
    private String se;

    /** 지도자아이디 */
    private int ldrid;

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
    private int endMnt;

    /** 일정아이디 */
    private int schdlid;

    /** 종료시간 */
    private int endHr;

    /** 시작분 */
    private int bgngMnt;

    /** 시작시간 */
    private int bgngHr;

    /** 과정 */
    private String crs;

    /** 차시 */
    private String rnd;

    /** 완료처리일자 */
    private String cmptnPrcsDe;

    /** 의견내용 */
    private String opnnCn;


    /** 보완요청아이디 */
    private int splmntDmndid;

    /*------------------------------------------------------------------------------------------*/
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


}
