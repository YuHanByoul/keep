package com.kbrainc.plum.mng.prtpn.infntAply.model;

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
* 유아교육신청 -> 교육신청관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.model
* - InfntAplyVo.java
* </pre>
**
@ClassName : InfntAplyVo
* @Description : 유아교육신청 -> 교육신청관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class InfntAplyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private List<InfntAplyVo> infntAplyVoList;
    
    /** 프로그램_일정아이디 */
    private String prgrmSchdlid;
    
    /** 신청아이디 */
    private int aplyId;
    /** 교육관아이디 */
    private int clssrmId;    
    /** 교육_연도 */
    private String eduYr;
    /** 교육_월 */
    private String eduMm;
    /** 프로그램아이디 */
    private int prgrmId;
    /** 프로그램_이름 */
    private String prgrmNm;
    /** 교육_인원수 */
    private String eduNope;
    /** 교육관_이름 */
    private String clssrmNm;
    /** 신청상태_신청완료_수 */
    private String aplyCmpltCnt;
    /** 신청상태_대기신청_수 */
    private String wtngAplyCnt;
    /** 신청상태_승인대기_수 */
    private String wtngApprvCnt;
    /** 신청상태_신청취소_수 */
    private String cnclAplyCnt;
    /** 신청상태_승인거부_수 */
    private String dnlApprvCnt;

    
    
    
    
    /** 신청_이름 */
    private String aplyNm;
    /** 교육_시간_분 */
    private String eduHrMnt;
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
    /** 교육_사진_파일아이디 */
    private int eduPhotoFileid;
    /** 신청복사 ID */
    private String copyAplyId;
    
    /** 교육대상 코드 저장용 */
    private String [] trgtCds;
    /** 교육주제 코드 저장용 */
    private String [] clsfCds;
    /** 신청복사 저장용 */
    private String [] copyAplyIds;

    
    /** 교육_유형_코드 */
    @NotEmpty(message = "교육유형을 선택해주십시오.")
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
    private String searchKeyword;
    private String searchEduYr;
    private String searchClssrmId;
    private String searchClssrmMm;
    private String searchRcptMthdCd;
    private String searchSttsCd;
}
