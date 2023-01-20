/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.sprtBizPcntst.model
* - PublicContestVo.java
* </pre> 
*
* @ClassName : PublicContestVo
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class PublicContestVo extends ParentRequestVo {

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
    
    /** 진행상태 */
    private String status;

    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    private Integer[] deletePublicContestIds;
    
    /**  공모아이디 */
    private Integer pcntstid;
    
    /** 분야_코드 */
    private String fldCd;

    /** 분야_코드명 */
    private String fldNm;
    
    /** 공모_이름 */
    private String pcntstNm;
    
    /** 신청_시작_일시 */
    private String aplyBgngDt;
    
    /** 신청_종료_일시 */
    private String aplyEndDt;
    
    
    
    
    /** 대상_코드 */
    private String trgtCd;
    
    /** 대상_코드명 */
    private String trgtNm;
    
    /** 사업_시작_일자 */
    private String bsnsBgngDe;
    
    /** 사업_종료_일자 */
    private String bsnsEndDe;

    
    
    /** 중간_보고_여부 */
    private String mdlReportYn;
    
    /** 컨설팅_사용_코드 */
    private String cnsltngUseCd;
    
    /** 컨설팅_사용_코드명 */
    private String cnsltngUseNm;
    
    /** 사업비_교부_횟수 */
    private Integer wctDelvryCnt;
    
    /** 사업비_교부_횟수_YN */
    private String wctDelvryCntYn;
    
    /** 온라인_심사_유형_코드 */
    private String onlnSrngTypeCd;
    
    /** 온라인_심사_유형_코드명 */
    private String onlnSrngTypeNm;
    
    /** 사업_내용 */
    private String bsnsCn;
    
    /** 심사_양식아이디_1차 */
    private String srngFormidFirst;
    
    /** 심사_양식아이디_2차 */
    private String srngFormidScnd;
    
    /** 심사_시작_일시 */
    private String srngBgngDt;
    
    /** 심사_종료_일시 */
    private String srngEndDt;
    
    /** 교부_신청_발표_시작_일시 */
    private String delvryAplyPrsntnBgngDt;
    
    /** 교부_신청_발표_종료_일시 */
    private String delvryAplyPrsntnEndDt;
    
    /** 교부_신청_시작_일시_1차 */
    private String delvryAplyBgngDtFirst;
    
    /** 교부_신청_종료_일시_1차 */
    private String delvryAplyEndDtFirst;
    
    /** 교부_신청_시작_일시_2차 */
    private String delvryAplyBgngDtScnd;
    
    /** 교부_신청_종료_일시_2차 */
    private String delvryAplyEndDtScnd;
    
    /** 교부_확정_발표_시작_일시 */    
    private String delvryCfmtnPrsntnBgngDt;
    
    /** 교부_확정_발표_종료_일시 */
    private String delvryCfmtnPrsntnEndDt;
    
    /** 자금_집행_시작_일시_1차 */
    private String cptalExcutBgngDtFirst;
    
    /** 자금_집행_종료_일시_1차 */
    private String cptalExcutEndDtFirst;

    /** 자금_집행_시작_일시_2차 */
    private String cptalExcutBgngDtScnd;
    
    /** 자금_집행_종료_일시_2차 */
    private String cptalExcutEndDtScnd;
    
    /** 중간_보고_시작_일시 */
    private String mdlReportBgngDt;
    
    /** 중간_보고_종료_일시 */
    private String mdlReportEndDt;
    
    /** 결과_보고_시작_일시 */
    private String rsltReportBgngDt;
    
    /** 결과_보고_종료_일시 */
    private String rsltReportEndDt;
    
    /** 정산_보고_시작_일시 */
    private String excclcReportBgngDt;
    
    /** 정산_보고_종료_일시 */
    private String excclcReportEndDt;
    
    /** 파일그룹아이디 */
    private int filegrpid;
    
    /** 사용_여부 */
    private String useYn;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private String rgtrid;
    
    
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
