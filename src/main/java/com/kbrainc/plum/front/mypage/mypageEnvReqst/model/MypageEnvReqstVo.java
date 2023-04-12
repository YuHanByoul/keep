package com.kbrainc.plum.front.mypage.mypageEnvReqst.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 지역 환경교육센처 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.mypageEnvReqst.model
 * - MypageEnvReqstVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MypageEnvReqstVo
 * @Description : 지역 환경교육센처 Vo
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Data
public class MypageEnvReqstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 시설명 */
    private String fcltNm;

    /** 공간명 */
    private String spceNm;

    /** 공간id */
    private Integer spceid;

    /** 주소 */
    private String addr;

    /** 주소상세 */
    private String addrDtl;

    /** 신청방법코드 */
    private String aplyMthdCd;

    /** 신청방법명 */
    private String aplyMthdNm;

    /** 기관아이디 */
    private String instid;

    /** 기관명 */
    private String instNm;

    /** 대표이미지 파일키 */
    private String fileIdntfcKey;

    /** 대표 이미지 파일아이디 */
    private Integer rprsImgFileid;

    /** 상세 이미지 파일그룹아이디 */
    private Integer dtlImgFilegrpid;

    /** 안내 파일아이디 */
    private Integer gdncFileid;

    /** 시설 */
    private Integer fcltid;

    /** 은행코드 */
    private String bankCd;

    /** 입금계좌 */
    private String bankCdNm;

    /** 계좌번호 */
    private String bacntNo;

    /** 예금주 이름 */
    private String dpstrNm;

    /** rsvtdeid */
    private Object rsvtdeid;

    /** 예약일자 */
    private String de;

    /** 금액 */
    private int amt;

    /** 예약가능여부 */
    @Pattern(regexp="[YN]")
    private String rsvtPsbltyYn;

    /** 예약id리스트 */
    private String[] rsvtdeids;

    /** 예약구분코드 */
    private String utztnSeCd;

    private Date bgngDt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;

    private Date endDt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Asia/Seoul")
    private Date chkinHr;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Asia/Seoul")
    private Date chcktHr;

    private String aplcntNm;

    private String aplcntMoblphon;

    private String pyrNm;

    private String aplcntEml;

    private String aplcntEmlId;

    private String aplcntEmlDomain;

    private int nopeAdult;

    private int nopeChil;

    private int nopeInfnt;

    private int aplyid;

    private String aplcntid;

    private String dpstBacnt;

    private String utztnPrps;

    private String aplyDt;

    /** 상태변경 사유 */
    private String chgRsn;

    /** 지역 코드 */
    private String rgnCd;

    /** 최대 인원수 */
    private Integer maxNope;

    /** 공간 사이즈 (평수)*/
    private String size;

    /** 취소사유 */
    private String cnclRsnCdNm;

    /** 입금 금액 */
    private int dpstAmt;

    /** 환불 요청 금액 */
    private int rfndDmndAmt;

    /** 환불 금액 */
    private int rfndAmt;

    /** 결제 방법 코드 */
    private String stlmMthdCd;

    /** 입금 일자 */
    private String dpstDe;

    /** 입금 확인 일시 */
    private Date dpstIdntyDt;

    /** 취소 일시 */
    private Date cnclDt;

    /** 환불 요청 일시 */
    private Date rfndDmndDt;

    /** 환불 일자 */
    private String rfndDe;

    /** 환불 완료 일시 */
    private Date rfndCmptnDt;

    /** 환불 은행 코드 */
    private String rfndBankCd;

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

    /** 예약 거절 사유 */
    private String rsvtRejectRsn;

    /** 예약 취소 사유 */
    private String rsvtCnclRsn;

    /** 환불 거절 사유 */
    private String rfndRejectRsn;

    /** 검색 관련*/
    private String searchSeCd;
    private String searchKeyword;
    private String searchRgnCd;
    private String searchAplySttsCd;
    private String searchBgngDt;
    private String searchEndDt;
    private String searchStlmSttsCd;
}
