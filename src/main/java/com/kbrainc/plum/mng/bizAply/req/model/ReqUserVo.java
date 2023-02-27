/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - ReqUserVo.java
* </pre> 
*
* @ClassName : ReqUserVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ReqUserVo extends ParentRequestVo {

    // 검색조건 start
    /** 신청상태 */
    private String searchAplySttsCd;
    
    /** 키워드 구분 */
    private String searchKeywordType;
    
    /** 키워드 */
    private String searchCondition;
    
    /** 선정결과 */
    private String searchSlctnSttsCd;

    /** 접수일시 시작일 */
    private String searchRegStartDe;
    
    /** 접수일시 종료일 */
    private String searchRegEndDe;
    
    /** 심사상태 */
    private String searchSrngStts;
    // 검색조건 end
    
    /** 분야 코드 */
    private String fldCd;
    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 공모아이디 */
    private Integer pcntstid;
    
    /** 신청번호 */
    private String aplyno;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 사용자 영문 아이디 */
    private String acnt;
    
    /** 사용자명 */
    private String userNm;
    
    /** 기관명 */
    private String instNm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 대표자명 */
    private String rprsvNm;
    
    /** 등록기관명 */
    private String regInstNm;
    
    /** 등록번호 */
    private String regNo;
    
    /** 등록일자 */
    private String regDe;
    
    /** 기관유형코드 */
    private String instTypeCd;
    
    /** 기관유형코드명 */
    private String instTypeNm;
    
    /** 기타내용 */
    private String etcCn;
    
    /** 기관유형상세코드 */
    private String instTypeDtlCd;
    
    /** 기관유형상세코드명 */
    private String instTypeDtlNm;
    
    /** 홈페이지 */
    private String hmpg;
    
    /** 종교코드 */
    private String relgnCd;
    
    /** 종교코드명 */
    private String relgnNm;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청자 전화번호 */
    private String aplcntTelno;
    
    /** 신청자 이메일 */
    private String aplcntEml;
    
    /** 신청기관 전화번호 */
    private String aplyInstTelno;
    
    /** 기관 권역코드 */
    private String instSareaCd;
    
    /** 기관 권역코드명 */
    private String instSareaNm;
    
    /** 기관 우편번호 */
    private String instZip;
    
    /** 기관 주소 */
    private String instAddr;
    
    /** 주소 */
    private String addr;
    
    /** 기관 상세 주소 */
    private String instAddrDtl;
    
    /** 지역코드 */
    private String rgnCd;
    
    /** 지역코드명 */
    private String rgnNm;
    
    /** 지도자 관리 방법 */
    private String ldrMngMthd;
    
    /** 파일그룹아이디1 */
    private Integer filegrpid1;
    
    /** 파일그룹아이디2 */
    private Integer filegrpid2;
    
    /** 파일그룹아이디3 */
    private Integer filegrpid3;
    
    /** 신청상태 코드 */
    private String aplySttsCd;
    
    /** 신청상태 코드명 */
    private String aplySttsNm;
    
    /** 선정상태 코드 */
    private String slctnSttsCd;
    
    /** 선정상태 코드명 */
    private String slctnSttsNm;
    
    /** 사업취소여부 */
    @Pattern(regexp="[YN]")
    private String bsnsCnclYn;
    
    /** 정산_상태_코드 */
    private String excclcSttsCd;
    
    /** 정산_상태_코드명 */
    private String excclcSttsNm;
    
    /** 은행_파일아이디 */
    private Integer bnkbFileid;
    
    /** 정산_첨부_파일그룹아이디 */
    private Integer excclcAtchFilegrpid;
    
    /** 반납_여부 */
    @Pattern(regexp="[YN]")
    private String rturnYn;
    
    /** 반납_금액 */
    private Integer rturnAmt;
    
    /** 반납일시 */
    private String rturnDt;
    
    /** 2차심사 대상여부 */
    private String scndSrngTrgtYn;
    
    /** 1차 점수 */
    private String frstScr;
    
    /** 1차 등급 */
    private String firstGrd;
    
    /** 1차 순위 */
    private String firstRkng;
    
    /** 2차 점수 */
    private String scndScr;
    
    /** 2차 등급 */
    private String scndGrd;
    
    /** 2차 순위 */
    private String scndRkng;

    /** 심사 종료 여부 */
    private String srngEndYn;
    
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
    
    /** 체크 항목 */
    private List<String> aplyids;
    
    /** 엑셀다운로드 여부 */
    private String excelYn;
    
    /** 1차 심사양식 */
    private String srngFormidFirst;
    
    /** 2차 심사양식 */
    private String srngFormidScnd;
    
    private String formid;
    
    /** 온라인 심사유형 코드 */
    private String onlnSrngTypeCd;
    private String srngSttsNm;
    private String firstScr;
    private String secondScr;
    private String totalScr;
    
}
