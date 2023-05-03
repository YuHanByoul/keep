/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvDelvry.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 사회환경교육기관 지정 > 교부관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.seeEnvDelvry.model
* - SeeEnvDelvryVo.java
* </pre> 
*
* @ClassName : SeeEnvDelvryVo
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SeeEnvDelvryVo extends ParentRequestVo {
    
    /** 사용자 세션 */
    private UserVo user;
    
    /** 검색_시도코드 */
    private String searchCtprvnCd;
    
    /** 검색_키워드 구분 */
    private String searchKeywordType;
    
    /** 검색_키워드 */
    private String searchKeyword;
    
    /** 검색_교부상태 */
    private String searchSttsCd;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 접수번호 */
    private String rcptno;
    
    /** 기관아이디 */
    private String instid;
    
    /** 신청자아이디 */
    private String aplcntid;
    
    /** 신청자 이름 */
    private String aplcntNm;
    
    /** 신청자 계정 */
    private String acnt;
    
    /** 신청자 이메일 */
    private String aplcntEml;
    
    /** 신청자 전화번호 */
    private String aplcntMoblphon;
    
    /** 기관이름 */
    private String instNm;
    
    /** 기관유형코드 */
    private String instTypeCd;
    
    /** 기관유형코드명 */
    private String instTypeNm;
    
    /** 사업자번호 */
    private String brno;
    
    /** 기관 우편번호 */
    private String zip;
    
    /** 기관 주소 */
    private String addr;
    
    /** 기관 상세주소 */
    private String addrDtl;
    
    /** 기관 이메일 */
    private String instEml;
    
    /** 기관 전화번호 */
    private String instCntct;
    
    /** 시도 코드 */
    private String ctprvnCd;
    
    /** 시도 코드명 */
    private String ctprvnNm;
    
    /** 대표자명 */
    private String rprsvNm;
    
    /** 대표자 생년월일 */
    private String rprsvBrdt;
    
    /** 첨부파일 그룹 아이디 */
    private String atchFilegrpid;
    
    /** 지정번호 */
    private String dsgnno;
    
    /** 지정일자 */
    private String dsgnDe;
    
    /** 승인자 아이디 */
    private String autzrid;
    
    /** 발급일자 */
    private String issuDe;
    
    /** 재발급일자 */
    private String isgnDe;
    
    /** 상태코드 */
    private String sttsCd;
    
    /** 상태코드명 */
    private String sttsNm;
    
    /** 교부상태코드명 */
    private String delvrySttsNm;
    
    /** 발급 유형(최초, 재발급) */
    private String issueType;
    
    /** 엑셀 다운로드 여부 */
    private String excelYn;

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
}
