/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 공모신청 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.front.bizAply.model
* - PublicContestAplyVo.java
* </pre> 
*
* @ClassName : PublicContestAplyVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.reqMngVo")
public class ReqMngVo extends ParentRequestVo {
    
    /** 분야_코드_검색 */
    private String searchFldCd;
    
    /** 공모_이름_검색 */
    private String searchPcntstNm;
    
    /** 신청기간 검색 시작일 */
    private String searchAplyBgngDe;
    
    /** 신청기간 검색 종료일 */
    private String searchAplyEndDe;

    /** 사업_시작_일자_검색 */
    private String searchBsnsBgngDe;
    
    /** 사업_종료_일자_검색 */
    private String searchBsnsEndDe;
    
    /** 프로그램명 */
    private String searchPrgrmNm;
    
    /** 진행상태 */
    private String status;

    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    /**  공모아이디 */
    private Integer pcntstid;
    
    /** 분야_코드 */
    @NotEmpty(message = "사업분야를 선택해주십시오.")
    private String fldCd;

    /** 분야_코드명 */
    private String fldNm;
    
    /** 공모_이름 */
    @Size(max = 100, message = "공모명은 100자를 넘을 수 없습니다.")
    private String pcntstNm;
    
    /** 신청_시작_일시 */
    @NotEmpty(message = "신청기간 시작일시를 선택해주십시오.")
    private String aplyBgngDt;
    
    /** 신청_종료_일시 */
    @NotEmpty(message = "신청기간 종료일시를 선택해주십시오.")
    private String aplyEndDt;
    
    /** 담당자 ids */
    private List<String> pcntstids1;
    
    /** 담당자 ids */
    private List<String> pcntstids2;
    
    
    /** 대상_코드 */
    @NotEmpty(message = "신청대상을 선택해주십시오.")
    private String trgtCd;
    
    /** 대상_코드명 */
    private String trgtNm;
    
    /** 사업_시작_일자 */
    @NotEmpty(message = "사업기간을 선택해주십시오.")
    private String bsnsBgngDe;
    
    /** 사업_종료_일자 */
    @NotEmpty(message = "사업기간을 선택해주십시오.")
    private String bsnsEndDe;

    /** 중간_보고_여부 */
    @Pattern(regexp="[YN]")
    private String mdlReportYn;
    
    /** 컨설팅_사용_코드 */
    private String cnsltngUseCd;
    
    /** 컨설팅_사용_코드명 */
    private String cnsltngUseNm;
    
    /** 컨설팅_운영_코드 */
    private String cnsltngOperCd;
    
    /** 사업비_교부_횟수 */
    private Integer wctDelvryCnt;
    
    /** 배분율 사용여부 */
    private String rtUseYn;
    
    /** 심사 배분율 사용여부 */
    private String srngRtUseYn;
    
    /** 1차 비율 */
    private Integer rtFirst;
    
    /** 2차 비율 */
    private Integer rtScnd;

    /** 최고점 제외 사용여부 */
    private String topScrExclYn;
    
    /** 최저점 제외 사용여부 */
    private String lwetScrExclYn;
    
    /** 인원수 제한 사용여부 */
    private String nopeLmtUseYn;
    
    /** 인원수 제한 */
    private Integer nopeLmt;
    
    /** 최저 기준 평균점수 사용여부 1차 */
    private String lwetCrtrScrUseYnFirst;
    
    /** 최저 기준 평균점수 1차 */
    private String lwetCrtrScrFirst;
    
    /** 최저 기준 평균점수 사용여부 2차 */
    private String lwetCrtrScrUseYnScnd;
    
    /** 최저 기준 평균점수 2차 */
    private String lwetCrtrScrScnd;
    
    /** 사업비_교부_횟수_YN */
    private String wctDelvryCntYn;
    
    /** 온라인_심사_유형_코드 */
    @NotEmpty(message = "온라인 심사유형을 선택해주십시오.")
    private String onlnSrngTypeCd;
    
    /** 온라인_심사_유형_코드명 */
    private String onlnSrngTypeNm;
    
    /** 사업_내용 */
    private String bsnsCn;
    
    /** 심사_양식아이디_1차 */
//    @NotEmpty(message = "1차 심사표를 선택해주십시오.")
    private Integer srngFormidFirst;
    
    /** 심사_양식아이디_2차 */
    private Integer srngFormidScnd;
    
    /** 심사_시작_일시 */
    private String srngBgngDt;
    
    /** 심사_종료_일시 */
    private String srngEndDt;
    
    /** 교부_신청_발표_시작_일시 */
    private String delvryAplyPrsntnBgngDt;
    
    /** 교부_신청_발표_종료_일시 */
    private String delvryAplyPrsntnEndDt;
    
    /** 교부_아이디_1차 */
    private Integer delvryidFirst;
    
    /** 교부_아이디_2차 */
    private Integer delvryidScnd;
    
    /** 교부_신청_시작_일시_1차 */
    @NotEmpty(message = "교부신청기간 시작일시를 선택해주십시오.")
    private String delvryAplyBgngDtFirst;
    
    /** 교부_신청_종료_일시_1차 */
    @NotEmpty(message = "교부신청기간 종료일시를 선택해주십시오.")
    private String delvryAplyEndDtFirst;
    
    /** 교부_신청_시작_일시_2차 */
    private String delvryAplyBgngDtScnd;
    
    /** 교부_신청_종료_일시_2차 */
    private String delvryAplyEndDtScnd;
    
    /** 교부_확정_발표_시작_일시 1차 */    
    @NotEmpty(message = "교부확정발표기간 시작일시를 선택해주십시오.")
    private String delvryCfmtnPrsntnBgngDtFirst;
    
    /** 교부_확정_발표_종료_일시 1차 */
    @NotEmpty(message = "교부확정발표기간 종료일시를 선택해주십시오.")
    private String delvryCfmtnPrsntnEndDtFirst;
    
    /** 교부_확정_발표_시작_일시 2차 */    
//    @NotEmpty(message = "교부확정발표기간 시작일시를 선택해주십시오.")
    private String delvryCfmtnPrsntnBgngDtScnd;
    
    /** 교부_확정_발표_종료_일시 2차 */
//    @NotEmpty(message = "교부확정발표기간 종료일시를 선택해주십시오.")
    private String delvryCfmtnPrsntnEndDtScnd;
    
    /** 자금_집행_시작_일시_1차 */
    @NotEmpty(message = "자금집행기간 시작일시를 선택해주십시오.")
    private String cptalExcutBgngDtFirst;
    
    /** 자금_집행_종료_일시_1차 */
    @NotEmpty(message = "자금집행기간 종료일시를 선택해주십시오.")
    private String cptalExcutEndDtFirst;
    
    /** 자금_집행_시작_일시_2차 */
    private String cptalExcutBgngDtScnd;
    
    /** 자금_집행_종료_일시_2차 */
    private String cptalExcutEndDtScnd;
    
    /** 컨설팅 신청기간 시작일시 */
    private String cnsltngAplyBgngDt;
    
    /** 컨설팅 신청기간 종료일시 */
    private String cnsltngAplyEndDt;
    
    /** 중간_보고_시작_일시 */
    private String mdlReportBgngDt;
    
    /** 중간_보고_종료_일시 */
    private String mdlReportEndDt;
    
    /** 결과_보고_시작_일시 */
    @NotEmpty(message = "결과보고기간 시작일시를 선택해주십시오.")
    private String rsltReportBgngDt;
    
    /** 결과_보고_종료_일시 */
    @NotEmpty(message = "결과보고기간 종료일시를 선택해주십시오.")
    private String rsltReportEndDt;
    
    /** 정산_보고_시작_일시 */
    private String excclcReportBgngDt;
    
    /** 정산_보고_종료_일시 */
    private String excclcReportEndDt;
    
    /** 파일그룹아이디 */
    private int filegrpid;
    
    /** 사용_여부 */
    @Pattern(regexp="[YN]")
    private String useYn;
    
    /** 차수 */
    private int cycl;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자아이디 */
    private String rgtrid;
    
    /** 조회수 */
    private Integer hits;
    
    /** 다음 공모아이디 */
    private String nextId;
    
    /** 다음 공모명 */
    private String nextTtl;
    
    /** 이전 공모아이디 */
    private String beforeId;
    
    /** 이전 공모명 */
    private String beforeTtl;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /******* 심사배분율 *******/
    private String jsonString;
    
    /*********** 교부 정보 ************/
    private Integer delvryid;
    private String delvryAplyBgngDt;
    private String delvryAplyEndDt;
    private String delvryCfmtnPrsntnBgngDt;
    private String delvryCfmtnPrsntnEndDt;
    private String cptalExcutBgngDt;
    private String cptalExcutEndDt;

    /*********** 엑셀 출력용 ***********/
    /** 신청_기간 */
    private String aplyDt;
    /** 사업_기간 */
    private String bsnsDe;
    /** 심사_기간 */
    private String srngDt;
    /** 교부신청기간 */
    private String delvryAplyDtFirst;
    /** 교부신청기간(2차) */
    private String delvryAplyDtScnd;
    /** 교부확정발표기간 */
    private String delvryCfmtnPrsntnDt;
    /** 자금집행기간 */
    private String cptalExcutDtFirst;
    /** 자금집행기간(2차) */
    private String cptalExcutDtScnd;
    /** 중간보고기간 */
    private String mdlReportDt;
    /** 결과보고기간 */
    private String rsltReportDt;
    /** 정산보고기간 */
    private String excclcReportDt;
}
