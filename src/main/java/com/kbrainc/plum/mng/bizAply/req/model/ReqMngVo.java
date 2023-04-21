/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - ReqMngVo.java
* </pre> 
*
* @ClassName : ReqMngVo
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ReqMngVo extends ParentRequestVo {

    private UserVo user;
    
    // 검색조건 start
    /** 분야_코드_검색 */
    private String searchFldCd;
    
    /** 공모_이름_검색 */
    private String searchPcntstNm;
    
    /** 신청기간 검색 시작일 */
    private String searchAplyBgngDe;
    
    /** 신청기간 검색 종료일 */
    private String searchAplyEndDe;

    /** 심사기간 검색 시작일 */
    private String searchSrngBgngDe;
    
    /** 심사기간 검색 종료일 */
    private String searchSrngEndDe;
    
    /** 신청상태 */
    private String searchAplyStts;
    
    /** 심사상태 */
    private String searchSrngStts;
    // 검색조건 end
    
    /** 심사위원 아이디 */
    private Integer jdgsid;
    
    /**  공모아이디 */
    private Integer pcntstid;
    
    /** 분야_코드 */
    private String fldCd;
    
    /** 분야_코드명 */
    private String fldNm;
    
    /** 공모_이름 */
    private String pcntstNm;

    /** 신청상태 */
    private String aplySttsNm;
    
    /** 심사상태 */
    private String srngSttsNm;
    
    /** 신청_기간 */
    private String aplyDt;

    /** 심사_기간 */
    private String srngDt;
    
    /** 신청건수 */
    private String aplynoCnt;
    
    /** 1차심사 건수 */
    private String frtSrngCnt;
    
    /** 2차심사 건수 */
    private String scdSrngCnt;
    
    /** 선정완료 건수 */
    private String slctnYCnt;
    
    /** 선정탈락 건수 */
    private String slctnNCnt;
    
    /** 엑셀다운로드 여부 */
    private String excelYn;
    
    /** 1차 심사양식 */
    private Integer srngFormidFirst;
    
    /** 2차 심사양식 */
    private Integer srngFormidScnd;
    
    /** 온라인 심사유형 코드 */
    private String onlnSrngTypeCd;
    
    /** 심사위원 이름 */
    private String nm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 심사 시작일자 */
    private String srngBgngDt;
    
    /** 심사 종료일자 */
    private String srngEndDt;
    
    /** 심사 시작시간 */
    private String srngBgngTm;
    
    /** 심사 종료시간 */
    private String srngEndTm;
    
    /** 심사 종료 여부 */
    private String srngEndYn;
    
    /** 2차 심사 종료 여부 */
    private String scndSrngEndYn;
}
