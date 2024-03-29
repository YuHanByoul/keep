/**
 * 
 */
package com.kbrainc.plum.front.mypage.infntAplyHist.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;


/**
* 유아환경교육관 교육신청 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.infntAplyHist.model
* - InfntVo.java
* </pre>
*
* @ClassName : InfntAplyHistVo
* @Description : 유아환경교육관 교육신청 Vo 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.InfntAplyHistVo")
public class InfntAplyHistVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 프로그램 아이디 */
    private Integer prgrmid;
    /** 신청아이디 */
    private Integer aplyid;
    /** 프로그램명 */
    private String prgrmNm;
    /** 회차_일정아이디 */
    private Integer tmeSchdlid;
    /** 교육관 아이디 */
    private Integer clssrmid;
    /** 교육관_명 */
    private String clssrmNm;
    /** 교육주제_명 */
    private String clsfNm;
    /** 지역명 */
    private String ctprvnNm;
    /** 신청날짜 */
    private String de;
    /** 프로그램 회차 아이디 */
    private Integer tmeid;
    /** 프로그램 회차명 */
    private String tmeNm;
    /** 신청상태 변경코드 */
    private String updCd;
    /** 신청상태 코드 */
    private String sttsCd;
    /** 신청상태 코드명 */
    private String sttsNm;
    /** 교육_인원수 */
    private String eduNope;
    /** 교육시간_분 */
    private String eduHrMnt;
    /** 교육대상 */
    private String trgtNm;
    /** 신청일 */
    private String aplyDt;
    /** 기업아이디 */
    private String instid;
    /** 기업명 */
    private String instNm;
    /** 신청자ID */
    private String aplyUserid;
    /** 신청자명 */
    private String aplyUserNm;
    /** 기관주소 */
    private String addr;
    /** 기관주소상세 */
    private String addrDtl;
    /** 신청인지역 */
    private String ctprvnCd;
    /** 전화번호 */
    private String telno;
    /** 방문교사 휴대전화 */
    private String moblphon;
    /** 이메일 */
    private String eml;
    /** 인솔교사인원 */
    private String tcherNope;
    /** 기타사항 */
    private String etc;
    /** 쌍방향 온라인 시범교육 신청 동의 */
    private String towwayOnlnEduAgreYn;
    /** 교육대상 코드 저장용 */
    private String [] trgtCds;
    /** 교육대상 */
    private String trgtCd;
    /** 신청자_만족도_설문아이디 */
    private Integer aplcntDgstfnSrvyid;
    /** 신청자_만족도_설문지아이디 */
    private Integer aplcntDgstfnQestnrid;
    /** 학생_만족도_설문아이디 */
    private Integer stdntDgstfnSrvyid;
    /** 학생_만족도_설문지아이디 */
    private Integer stdntDgstfnQestnrid;
    /** 설문제출유무 */
    private String sbmsnYn;
    /** 설문제출ID */
    private Integer sbmsnid;

    private String canSrvy;
    private String submitSrvy;
    private String canShareSrvy;

    private String cd;
    private String cdNm;
    
    
    /** 등록일 */
    private String regDt;
    
    /** 필터 */
    private String searchPrgrmNm;
    private String searchEduClssRmNm;
    private String searchKeyword;
    private String searchTrgtCd;
    private String searchCtprvnCd;
}
