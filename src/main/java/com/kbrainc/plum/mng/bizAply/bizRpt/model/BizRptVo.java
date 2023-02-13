package com.kbrainc.plum.mng.bizAply.bizRpt.model;

import java.util.Date;

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
@Data
public class BizRptVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;

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

    /** (검색) 제출상태 코드 */
    private String searchReportSttsCd;

    /** 공모아이디 */
    private Integer pcntstid;

    /** 분야 코드 */
    private String fldCd;

    /** 분야 코드 이름*/
    private String fldCdNm;

    /** 공모 이름 */
    private String pcntstNm;

    /** 중간 보고 시작 일시 */
    private Date mdlReportBgngDt;

    /** 중간 보고 종료 일시 */
    private Date mdlReportEndDt;

    /** 중간 보고 기간 */
    private String mdlReportPrd;

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
    private String cnsltngUseYn;

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

	/** 기타내용 */
	private String etcCn;

	/** 교육 대상 코드 */
	private String eduTrgt;

	/** 첨부파일그룹아이디 */
	private int atchFilegrpid;



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



}
