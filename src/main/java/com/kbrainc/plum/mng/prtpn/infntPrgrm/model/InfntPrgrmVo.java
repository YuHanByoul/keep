package com.kbrainc.plum.mng.prtpn.infntPrgrm.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 유아교육프로그램 -> 교육프로그램관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.model
* - InfntPrgrmVo.java
* </pre>
**
@ClassName : InfntPrgrmVo
* @Description : 유아교육프로그램 -> 교육프로그램관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class InfntPrgrmVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private List<InfntPrgrmVo> infntPrgrmVoList;
    
    /** 프로그램아이디 */
    private int prgrmId;
    /** 교육관아이디 */
    private int clssrmId;    
    /** 교육_연도 */
    private String eduYr;
    /** 프로그램_이름 */
    private String prgrmNm;
    /** 교육관_이름 */
    private String clssrmNm;
    /** 교육_인원수 */
    private String eduNope;
    /** 교육_시간_분 */
    private String eduHrMnt;
    /** 접수_방법_코드 */
    private String rcptMthdCd;
    /** 접수_방법_코드명 */
    private String rcptMthdNm;
    /** 신청자_만족도_설문아이디 */
    private String aplcntDgstfnSrvyid;
    /** 학생_만족도_설문아이디 */
    private String stdntDgstfnSrvyid;
    /** 교육_설명 */
    private String eduExpln;
    /** 유의사항 */
    private String mttrat;
    /** 교육_소개_파일아이디 */
    private int eduIntrcnFileid;
    /** 교육_사진_파일아이디 */
    private int eduPhotoFileid;
    /** 월요일_여부 */
    private String monYn;
    /** 화요일_여부 */
    private String tuesYn;
    /** 수요일_여부 */
    private String wedYn;
    /** 목요일_여부 */
    private String thurYn;
    /** 금요일_여부 */
    private String friYn;
    /** 토요일_여부 */
    private String satYn;
    /** 일요일_여부 */
    private String sunYn;
    /** 사용_여부 */
    private String useYn;
    
    /** 분류_상위 코드 */
    private String upprClsfCd;
    /** 분류_코드 */
    private String clsfCd;
    /** 분류_코드명 */
    private String clsfNm;
    /** 대상_코드 */
    private String trgtCd;
    /** 대상_코드명 */
    private String trgtNm;
    /** 회차_아이디 */
    private String tmeId;
    /** 회차_이름 */
    private String tmeNm;
    /** 시작_일시 */
    private String bgngTm;
    /** 종료_일시 */
    private String endTm;
    /** 순서 */
    private String ordr;
    /** 회차별 월요일_여부 */
    private String tmeMonYn;
    /** 회차별 화요일_여부 */
    private String tmeTuesYn;
    /** 회차별 수요일_여부 */
    private String tmeWedYn;
    /** 회차별 목요일_여부 */
    private String tmeThurYn;
    /** 회차별 금요일_여부 */
    private String tmeFriYn;
    /** 회차별 토요일_여부 */
    private String tmeSatYn;
    /** 회차별 일요일_여부 */
    private String tmeSunYn;
    /** 회차별 삭제_여부 */
    private String delFlag;
    
    /** 프로그램복사 ID */
    private String copyPrgrmId;
    
    /** 교육대상 코드 저장용 */
    @NotEmpty(message = "교육대상을 선택해주십시오.")
    private String [] trgtCds;
    /** 교육주제 코드 저장용 */
    @NotEmpty(message = "교육주제를 선택해주십시오.")
    private String [] clsfCds;
    /** 프로그램복사 저장용 */
    private String [] copyPrgrmIds;

    
    /** 교육_유형_코드 */
    private String eduTypeCd;
    
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;    
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련 */
    private String searchEduYr;
    private String searchClssrmId;
    private String searchClsfCd;
    private String searchRcptMthdCd;
    private String searchClsfCd1;
    private String searchClsfCd2;
    private String searchUseYn;
    
    /** 첨부파일 관련*/
    private String eduIntrcnFilegrpid;
    private String eduPhotoFilegrpid;
    private String eduIntrcnFileIdntfcKey;
    private String eduIntrcnOrginlFileNm;    
    private String eduPhotoFileIdntfcKey;
    private String eduPhotoOrginlFileNm;    
}
