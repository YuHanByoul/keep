/**
 * 
 */
package com.kbrainc.plum.front.mvmnAply.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;


/**
* 푸름이 이동환경교실 교육신청 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.mvmn.model
* - MvmnVo.java
* </pre>
*
* @ClassName : MvmnVo
* @Description : 푸름이 이동환경교실 교육신청 Vo 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.MvmnAplyVo")
public class MvmnAplyVo extends ParentRequestVo {
    
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
    /** 프로그램 회차 아이디 */
    private Integer tmeid;
    /** 프로그램 회차명 */
    private String tmeNm;
    /** 교육관 아이디 */
    private Integer clssrmid;
    /** 교육관_명 */
    private String clssrmNm;
    /** 교육주제_명 */
    private String clsfNm;
    /** 교육시간_분 */
    private String eduHrMnt;
    /** 교육사진_파일ID */
    private int eduPhotoFileid;
    /** 교육대상 */
    private String trgtNm;
    /** 접수상태설명 */
    private String rcptDesc;
    /** 지역명 */
    private String ctprvnNm;
    /** 교육_인원수 */
    private String eduNope;
    /** 접수_방법_코드 */
    private String rcptMthdCd;
    /** 접수_방법_코드명 */
    private String rcptMthdNm;
    /** 교육_설명 */
    private String eduExpln;
    /** 유의사항 */
    private String mttrat;
    /** 교육_소개_파일아이디 */
    private int eduIntrcnFileid;
    /** 마감상태_설명 */
    private String sttsDesc;
    /** 대기팀 카운팅 */
    private String waitCnt;
    /** 신청날짜 */
    private String de;
    
    /** 개인정보 수집 이용동의 */
    @NotEmpty(message = "필수항목을 동의해야 교육신청이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 교육신청이 가능합니다.")
    private String prvcClctAgreYn;
    /** 개인정보 제3자 제공동의 */
    @NotEmpty(message = "필수항목을 동의해야 교육신청이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 교육신청이 가능합니다.")
    private String prvcThptyPvsnAgreYn;
    
    /** 기업아이디 */
    private String instid;
    /** 기업명 */
    private String instNm;
    /** 전화번호 */
    private String telno;
    /** 신청자ID */
    private String aplyUserid;
    /** 신청자명 */
    private String aplyUserNm;
    /** 이메일 */
    private String eml;
    /** 기관주소 */
    private String addr;
    /** 기관주소상세 */
    private String addrDtl;
    /** 방문교사 휴대전화 */
    private String moblphon;
    /** 교육대상 코드 저장용 */
    private String [] trgtCds;
    /** 교육대상 */
    private String trgtCd;
    /** 인솔교사인원 */
    private String tcherNope;
    /** 신청인지역 */
    private String ctprvnCd;
    /** 비고 */
    private String rmrk;
    /** 메모 */
    private String memo;
    
    
    /** 운영권역ID */
    private Integer sareaid;
    /** 운영권역명 */
    private String sareaNm;
    /** 교육구분코드 */
    private String eduSeCd;
    /** 교육구분명 */
    private String eduSeNm;
    /** 운영형식코드 */
    private String operFomCd;
    /** 운영형식명 */
    private String operFomNm;
    /** 시군구코드 */
    private String signguCd;
    /** 시군구코드명 */
    private String signguNm;
    /** 시군구설명 */
    private String signguDesc;
    /** 세부설정 시군구코드(조회용) */
    private String sareaSignguCd;
    /** 세부설정 시군구코드 설명(조회용) */
    private String sareaSignguDesc;
    /** 신청기관_팩스번호 */
    private String fxno;
    /** 희망 교육 시작시간 */
    private String hopeEduBgngTm;
    /** 희망 교육 종료시간 */
    private String hopeEduEndTm;
    
    
    /** 교육대상 */
    private String eduTarget;
    /** 전체_월 */
    private String wholAge;
    /** 참가비 */
    private String etrfee;
    /** 지정_번호 */
    private String dsgnNo;
    /** 지정_일자 */
    private String dsgnDe;
    /** 대표자명 */
    private String rprsvNm;
    /** 기관_주소*/
    private String instAddr;
    /** 기관_상세주소*/
    private String instDtlAddr;
    /** 기관_홈페이지*/
    private String instHmpg;
    
    /** 숙박_밤(박) */
    private String styNight;
    /** 숙박_낮(일) */
    private String styDaytm;
    /** 숙박_여부 */
    private String styYn;
    /** */
    private String cdNm;
    /** 운영일정 */
    private String targetMm;
    /** 교육주제 */
    private String eduSbjctCdNm;
    /** 교육_장소 */
    private String eduPlc;
    /** 교육_시간 */
    private String eduTime;
    /** 유료_여부 */
    private String pchrgYn;
    /** 담당자명 */
    private String aplcntNm;
    /** 담당자_연락처 */
    private String aplcntMoblphon;
    
    /** 교육_목적*/
    private String eduPrps;
    /** 환경교육_적절성 */
    private String appro;
    /** 푸름이 이동환경교실 교육신청_우수성 */
    private String dstnctn;
    /** 교육_내용 */
    private String eduCn;
    
    /** 등록일 */
    private String regDt;

    /** 첨부파일 관련 */
    private int fileid;
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    private String eduIntrcnFilegrpid;
    private String eduIntrcnFileIdntfcKey;
    private String eduIntrcnOrginlFileNm;    
    
    /** 필터 */
    private String searchPrgrmNm;
    private String searchEduSeCd;
    private String searchSareaId;
    private String searchTrgtCd;
    private String searchOperFomCd;
    private String searchClsfCd_1;
    private String searchClsfCd_2;
}
