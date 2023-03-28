package com.kbrainc.plum.front.clclnMng.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.front.clclnMng.model
* - ClclnVo.java
* </pre> 
*
* @ClassName : ClclnVo
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ClclnVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 검색 조건 */
    private String searchFldCd;
    private String searchPcntstNm;
    private String searchPrgrmNm;
    private String searchSttsCd;
    
    /** 집행내역 검색 조건 */
    private String searchUtztnSecd;
    private String searchSeCd;
    private String searchDeStart;
    private String searchDeEnd;
    
    /** 집행내역 아이디 */
    private String dsctnid;
    
    /** 공모아이디 */
    private Integer pcntstid;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 사업분야코드 */
    private String fldCd;
    
    /** 구분 코드(입금, 출금) */
    private String seCd;
    
    /** 사업분야코드명 */
    private String fldNm;
    
    /** 공모명 */
    private String pcntstNm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 사업시작일 */
    private String bsnsBgngDe;
    
    /** 사업종료일 */
    private String bsnsEndDe;
    
    /** 사업기간 */
    private String bsnsDe;
    
    /** 정산율 */
    private String clclnRate;
    
    /** 예산액 */
    private Integer totAmt;
    
    /** 집행금액 */
    private Integer minusAmt;
    
    /** 입금액 */
    private Integer plusAmt;
    
    /** 잔액 */
    private Integer nowAmt;
    
    /** 잔액(예산잔액+입금액) */
    private Integer amt;
    
    /** 정산상태코드 */
    private String excclcSttsCd;
    
    /** 정산상태코드명 */
    private String excclcSttsNm;
    
    /** 보완아이디 */
    private Integer splmntid;
    
    /** 버튼타입 */
    private String crudBtn;
    
    /** 정산보고시작일 */
    private String excclcReportBgngDt;
    
    /** 정산보고종료일 */
    private String excclcReportEndDt;
    
    /** 신청자아이디 */
    private Integer userid;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청기관아이디 */
    private Integer instid;
    
    /** 신청기관명 */
    private String instNm;
    
    /** 통장사본 첨부파일 */
    private Integer bnkbFileid;
    
    /** 정산첨부파일 */
    private Integer excclcAtchFilegrpid;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private Integer mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
}
