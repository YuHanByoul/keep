package com.kbrainc.plum.mng.rcpmnyBfe.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 * 시설 VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.rcpmnyBfe.model
 * - RcpmnyBfeVo.java
 * </pre>
 *
 * @ClassName : RcpmnyBfeVo
 * @Description : 시설 VO 클래스
 * @author : NTK
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Data
public class RcpmnyBfeVo extends ParentRequestVo {

    public static String fcltVal;

    /** 로그인사용자정보 */
    private UserVo user;

    /** 신청아이디 */
    private int aplyid;

    /** 접수번호 */
    private int rcptNo;

    /** 신청자 아이디 */
    private String aplcntid;

    /** 신청자 이름 */
    private String aplcntNm;

    /** 신청자 휴대폰 */
    private String aplcntMoblphon;

    /** 신청자 이메일 */
    private String aplcntEml;

    /** 인원수 성인 */
    private int nopeAdult;

    /** 인원수 아동 */
    private int nopeChil;

    /** 인원수 유아 */
    private int nopeInfnt;

    /** 이용 목적 */
    private String utztnPrps;

    /** 금액 */
    private int amt;

    /** 입금 금액 */
    private int dpstAmt;

    /** 환불 요청 금액 */
    private int rfndDmndAmt;

    /** 환불 금액 */
    private int rfndAmt;

    /** 결제 방법 코드 */
    private String stlmMthdCd;
    
    /** 입금 계좌 코드*/
    private String dpstBankCd;
    
    /** 입금 계좌 코드명*/
    private String dpstBankCdNm;
    
    /** 입금 계좌 */
    private String dpstBacnt;

    /** 입금자 이름 */
    private String pyrNm;

    /** 신청 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date aplyDt;

    /** 입금 일자 */
    private String dpstDe;

    /** 입금 확인 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date dpstIdntyDt;

    /** 취소 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cnclDt;

    /** 환불 요청 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date rfndDmndDt;

    /** 환불 일자 */
    private String rfndDe;

    /** 환불 완료 일시 */
    private Date rfndCmptnDt;

    /** 환불 은행 코드 */
    private String rfndBankCd;
    
    /** 환불 은행 코드 명*/
    private String rfndBankCdNm;

    /** 환불 계좌 */
    private String rfndBacnt;

    /** 환불 입금자 이름 */
    private String rfndPyrNm;

    /** 신청 상태 코드 */
    private String aplySttsCd;

    /** 신청 상태 코드명 */
    private String aplySttsCdNm;

    /** 결제 상태 코드 */
    private String stlmSttsCd;

    /** 결제 상태 코드명 */
    private String stlmSttsCdNm;

    /** 취소 사유 코드 */
    private String cnclRsnCd;
    
    /** 취소 사유 코드명*/
    private String cnclRsnCdNm;

    /** 예약 취소 사유 */
    private String rsvtCnclRsn;

    /** 환불 거절 사유 코드*/
    private String rfndRejectRsnCd;
    
    /** 환불 거절 사유 */
    private String rfndRejectRsn;
    
    /** 환불 거절 사유 코드명*/ 
    private String rfndRejectRsnCdNm;

    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;

    /** 수정자 아이디 */
    private int mdfrid;

    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;

    /** 등록자아이디 */
    private int rgtrid;

    /** 예약일자아이디 */
    private int rsvtdeid;

    /** 일자 */
    private String de;

    /** 시작 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bgngDt;

    /** 종료 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date endDt;

    /** 공간 이름 */
    private String spceNm;

    /** 회원 유형 */
    private String rolecdnm;

    /** 기관 유형 */
    private String instTypeCd;
    
    /** 기관 유형 코드명 */
    private String instTypeCdNm;

    /** 신청 상태 */
    private String reqstSttus;

    /** 결제 상태 */
    private String paySttus;

    /** 신청자 기관명 */
    private String userInstNm;

    /** 신청자 이메일 */
    private String eml;

    /** 신청자 휴대전화 */
    private String moblphon;

    /** 입금정보 결제방법 */
    private String stlmMthdCdNm;

    /** 상태변경 사유 */
    private String chgRsn;

    /** 히스토리 아이디 */
    private Integer hstryid;

    /** 후기 내용 */
    private String rvwCn;
    
    /** 후기점수 */
    private Integer rvwScr;
    
    /** 후기 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date rvwDt;

    /** MODE */
    private String mode;
    
    /** 첨부 파일 그룹아이디 */
    private String filegrpid;

    /** 시설 */
    private Integer fcltid;

    /** 시설 */
    private String fcltNm;

    /** 시설번호 */
    public String fcltNo;

    /** 기관명 */
    private String instNm;

    /** 우편번호 */
    private String zip;

    /** 주소 상세 */
    private String addrDtl;

    /** 지역 코드 */
    private String rgnCd;
    
    /** 신청 방법 코드 */
    private String aplyMthdCd;
    
    /** 외부 URL */
    private String extnlUrl;
    
    /** 은행코드 */
    private String bankCd;

    /** 입금계좌 */
    private String bankCdNm;

    /** 계좌번호 */
    private String bacntNo;

    /** 예금주 이름 */
    private String dpstrNm;

    /** 대표 이미지 파일아이디 */
    private Integer rprsImgFileid;

    /** 상세 이미지 파일그룹아이디 */
    private Integer dtlImgFilegrpid;

    /** 안내 파일아이디 */
    private Integer gdncFileid;

    /** 사용 여부 */
    @Pattern(regexp="[YN]")
    private String useYn;

    /** 상세 내용 */
    private String dtlCn;

    /** 지역명 */
    private String rgnNm;

    /** 예약신청방법 */
    private String aplyMthdNm;

    /** 코드명 */
    private String cdNm;

    /** 보유 공간 수 */
    private Integer ownCnt;

    /** 이름 */
    private String nm;

    /** 아이디 */
    private String acnt;
    
    /** 예약 목록 명  */
    private String resveList;
    
    private String alldayYn;
    
    /** 중복 시설명 수 */
    public static Integer dupCnt;
    
    /** 사용일자 */        
    public static String resveDt;
    
    /** 신청아이디(s) */
    private String[] aplyids;
    
    /** 검색 관련*/
    private String searchSeCd;
    private String searchKeyword;
    private String searchRgnCd;
    private String searchAplySttsCd;
    private String searchBgngDt;
    private String searchEndDt;
    private String searchStlmSttsCd;
    private String selectedApplyid;
    
    public void setCnclRsnCd(String cnclRsnCd) throws Exception{
        this.cnclRsnCd = cnclRsnCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.cnclRsnCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.cnclRsnCd);
                this.cnclRsnCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
             }catch(Exception e) {
                return ;
             }
        }
    }
    public void setRfndRejectRsnCd(String rfndRejectRsnCd) throws Exception{
        this.rfndRejectRsnCd = rfndRejectRsnCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.rfndRejectRsnCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.rfndRejectRsnCd);
                this.rfndRejectRsnCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setRfndBankCd(String rfndBankCd) throws Exception{
        this.rfndBankCd = rfndBankCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.rfndBankCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.rfndBankCd);
                this.rfndBankCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setAplySttsCd(String aplySttsCd) throws Exception{
        this.aplySttsCd = aplySttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.aplySttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplySttsCd);
                this.aplySttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setStlmSttsCd(String stlmSttsCd) throws Exception{
        this.stlmSttsCd = stlmSttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.stlmSttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.stlmSttsCd);
                this.stlmSttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    public void setInstTypeCd(String instTypeCd) throws Exception{
        this.instTypeCd = instTypeCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.instTypeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.instTypeCd);
                this.instTypeCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    public void setStlmMthdCd(String stlmMthdCd) throws Exception{
        this.stlmMthdCd = stlmMthdCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.stlmMthdCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.stlmMthdCd);
                this.stlmMthdCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    public void setDpstBankCd(String dpstBankCd) throws Exception{
        this.dpstBankCd = dpstBankCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.dpstBankCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.dpstBankCd);
                this.dpstBankCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }

}
