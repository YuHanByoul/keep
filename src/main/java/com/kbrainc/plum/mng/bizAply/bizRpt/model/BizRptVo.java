package com.kbrainc.plum.mng.bizAply.bizRpt.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* 사업보고VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.model
* - BizRptVo.java
* </pre>
*
* @ClassName : BizRptVo
* @Description : 사업보고VO 클래스
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Datapublic class BizRptVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

    /** (검색) 사용자 아이디 */
    private Integer searchUserid;

    /** (검색) 분야 코드 */
    private String searchFldCd;

    /** (검색) 공모 이름 */
    private String searchPcntstNm;

    /** (검색) 시작 일시 */
    private String searchBgngDt;

    /** (검색) 종료 일시 */
    private String searchEndDt;

    /** (검색) 진행상태 코드 */
    private String searchPrgrsSttsCd;

    /** (검색) 상태 코드 */
    private String searchSttsCd;

    /** (검색) 제출상태 코드 */
    private String searchReportSttsCd;

    /** 공모아이디 */
    private Integer pcntstid;

    /** 컨설턴트 ids */
    private List<Integer> cnstntIds;

    /** 컨설팅 대상 내용 */
    private String cnsltngTrgtCn;

    /** 분야 코드 */
    private String fldCd;

    /** 분야 코드 이름*/
    private String fldCdNm;

    /** 공모 이름 */
    private String pcntstNm;

    /** 중간 보고 시작 일시 */
    private Date mdlReportBgngDt;

    /** 결과 보고 종료 일시 */
    private Date mdlReportEndDt;

    /** 결과 보고 기간 */
    private String mdlReportPrd;

    /** 결과 보고 시작 일시 */
    private Date rsltReportBgngDt;

    /** 중간 보고 종료 일시 */
    private Date rsltReportEndDt;

    /** 중간 보고 기간 */
    private String rsltReportPrd;

    /** 진행상태 코드 */
    private String prgrsSttsCd;

    /** 진행상태 코드 이름*/
    private String prgrsSttsCdNm;

    /** 선정사업 건수 */
    private String slctnBizCnt;

    /** 제출 건수 */
    private String sbmsnCnt;

    /** 컨설팅 대상 건수 */
    private String cnsltngUseCnt;

    /** 컨설팅 대상 여부 */
    private String cnsltngTrgtYn;

    /** 접수번호 */
    private String rcptno;

    /** 증빙서류 여부 */
    private String evdncDcmntYn;

    /** 보고 구분 코드 */
    private String reportSeCd;

    /** 보고 상태 코드 */
    private String reportSttsCd;

    /** 보고 상태 코드 이름*/
    private String reportSttsCdNm;

    /** 기관 아이디 */
    private String instid;

    /** 기관 이름 */
    private String instNm;

    /** 사용자 이름(계정) */
    private String userNmAcnt;

    /** 프로그램 이름 */
    private String prgrmNm;

    /** 보고id */
	private Integer reportid;

    /** 제출 일시 */
    private String sbmsnDt;

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

	/** 교육 대상 코드 */
	private String eduTrgtCd;

	/** 필수 주제 이름 */
	private String essntlSbjctNm;

	/** 교육 대상 */
	private String trgt;

	/** 필수 주제 이름 */
	private String sbjct;

	/** 기타내용 */
	private String etcCn;

	/** 교육 대상 코드 */
	private String eduTrgt;

	/** 첨부파일그룹아이디 */
	private int atchFilegrpid;

	/** 운영 아이디 */
	private Integer operid;

	/** 시작일자 */
	private String bgngDe;

	/** 종료일자 */
	private String endDe;

	/** 구분코드 */
	private String seCd;

	/** 구분코드 이름*/
	private String seCdNm;

	/** 차시 */
	private Integer rnd;

	/** 인원 */
	private Integer nope;

	/** 분 */
	private Integer mnt;

	/** 비고 */
	private String rmrk;

	/** 차시 합계 */
	private Integer sumRnd;

	/** 인원 합계 */
	private Integer sumNope;

	/** 분 합계 */
	private Integer sumMnt;

	/** 계획 건수 */
	private Integer planCnt;

	/** 현재 건수 */
	private Integer nowCnt;

	/** 보완요청 아이디 */
	private Integer splmntid;

	/** 답변상태코드 */
	private String ansSttsCd;

	/** 답변상태코드 명 */
	private String ansSttsCdNm;

	/** 요청내용 */
	private String dmndCn;

	/** 답변내용 */
	private String ansCn;

	/** 권역코드 */
	private String instSareaCd;

	/** 권역코드 명*/
	private String instSareaCdNm;

	/** 요청일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date dmndDt;

	/** 완료처리일 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	private Date ansDt;

	/** mode */
	private String mode;

	/** 컨설팅상태 이름 */
	private String cnsltngSttsNm;

	/** 컨설팅아이디 */
	private Integer cnsltngid;

	/** 컨설턴트 */
	private Integer cnstntid;

	/** 신청아이디 */
	private Integer aplyid;

	/** 신청자 이름 */
	private String aplcntNm;

	/** 신청자 전화번호 */
	private String aplcntTelno;

	/** 신청자 이메일 */
	private String aplcntEml;

	/** 지역 코드 */
	private String rgnCd;

	/** 지역 코드 명*/
	private String rgnCdNm;

	/** 기관 우편번호 */
	private String instZip;

	/** 기관 주소 */
	private String instAddr;

	/** 기관 주소 상세 */
	private String instAddrDtl;

	/** 방문 일시 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd 00:00")
    private String vstDt;

    /** 사전 내용 */
    private String cnBfr;

    /** 사후 내용 */
    private String cnAftr;

    /** 결과 */
    private String rslt;

    /** 사업취소 아이디 */
    private Integer dmndid;

    /** 반환시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String rturnBgngDe;


    /** 반환종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String rturnEndDe;

    /** 취소일시(취소신청일) */
    private String cnclDt;

    /** 사업취소 상태 코드 */
    private String dmndSttsCd;

    /** 사업취소 상태 코드 이름*/
    private String dmndSttsCdNm;

    /** 사업취소 여부 */
    private String bsnsCnclYn;

    /** 은행 코드 */
    private String bankCd;

    /** 계좌번호 */
    private String bacntno;

    /** 예금주 이름 */
    private String dpstrNm;

    /** 내용 */
    private String cn;

    /** 내용 */
    private String userid;

    /** 이름 */
    private String nm;

    /** 그룹 아이디 */
    private Integer grpid     ;

    /** 그룹명 */
    private String grpNm      ;

    /** 전문가수 */
    private String exprtCnt   ;

    /** 그룹전문가 이름 */
    private String grpExprtNm ;

    /** 검색 그룹 이름 */
    private String searchGrpNm ;

    /** 그룹전문가 아이디*/
    private String grpExprtIds;

    /** 그룹 세부사항  */
    private String grpExprtStr;

    private Integer cycl;






    // ********************************
    //  코드 명
    // *******************************/

    /** 분야코드 */
	public void setFldCd(String fldCd) throws Exception{
		this.fldCd = fldCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.fldCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.fldCd);
				this.fldCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 진행상태코드 */
	public void setPrgrsSttsCd(String prgrsSttsCd) throws Exception{
		this.prgrsSttsCd = prgrsSttsCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.prgrsSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.prgrsSttsCd);
				this.prgrsSttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 보고상태코드 */
	public void setReportSttsCd(String reportSttsCd) throws Exception{
		this.reportSttsCd = reportSttsCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.reportSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.reportSttsCd);
				this.reportSttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 보고운영구분 */
	public void setSeCd(String seCd) throws Exception{
		this.seCd = seCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.seCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.seCd);
				this.seCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 보고상태코드 */
	public void setAnsSttsCd(String ansSttsCd) throws Exception{
		this.ansSttsCd = ansSttsCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.ansSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.ansSttsCd);
				this.ansSttsCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 권역코드 */
	public void setInstSareaCd(String instSareaCd) throws Exception{
		this.instSareaCd = instSareaCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.instSareaCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.instSareaCd);
				this.instSareaCdNm = code.getCdNm();
			}catch(NoClassDefFoundError e) {
				//e.printStackTrace();
				return;
			}catch(Exception e) {
				//e.printStackTrace();
				return;
			}
		}
	}

	/** 권역코드 */
	public void setDmndSttsCd(String dmndSttsCd) throws Exception{
		this.dmndSttsCd = dmndSttsCd;
		//이미 코드이름이 있다면, 무시.
		if(CommonUtil.isEmpty(this.dmndSttsCdNm)) {
			try {
				ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
				CodeInfoVo code = resCodeService.getCodeInfo(this.dmndSttsCd);
				this.dmndSttsCdNm = code.getCdNm();
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
