/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvOper.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.seeEnvOper.model
* - SeeEnvOperationVo.java
* </pre> 
*
* @ClassName : SeeEnvOperationVo
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SeeEnvOperationVo extends ParentRequestVo {

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
    private Integer atchFilegrpid;
    
    /** 지정번호 */
    private String dsgnno;
    
    /** 지정일자 */
    private String dsgnDe;
    
    /** 지정취소일자 */
    private String dsgnCnclDe;
    
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
    
    /** 엑셀 다운로드 여부 */
    private String excelYn;

    /** 변경신청아이디 */
    private Integer dmndid;
    
    /** 요청내용 */
    private String dmndCn;
    
    /** 요청일시 */
    private String dmndDt;
    
    /** 답변내용 */
    private String ansCn;

    /** 지정내역아이디 */
    private Integer dsctnid;
    
    /** 지정신청건수 */
    private Integer aplyCnt;
    
    /** 담당자 변경 팝업 검색어 */
    private String searchCondition;
    
    /** 변경신청 건수 */
    private String chgCnt;
    
    /** 변경신청 탭 이동여부 */
    private String changeTab;
    
    /** 답변일시 */
    private String ansDt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private String rgtrid;
}
