/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 프로그램 정보 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - ProgramInfoVo.java
* </pre> 
*
* @ClassName : ProgramInfoVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 8.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.ProgramInfoVo")
public class ProgramInfoVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;

    /** 공모아이디 */
    private Integer pcntstid;
    
    /** 분야 코드 */
    private String fldCd;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 교육_대상 */
    private String eduTrgt;
    
    /** 교육_장소 */
    private String eduPlc;
    
    /** 교육_방식_코드 */
    private String eduMthdCd;
    
    /** 교육_방식_코드명 */
    private String eduMthdNm;
    
    /** 강의_장소_구분_코드 */
    private String lctrPlcSeCd;
    
    /** 강의_장소_구분_코드명 */
    private String lctrPlcSeNm;
    
    /** 강의_형태_코드 */
    private String lctrFrmCd;
    
    /** 강의_형태_코드명 */
    private String lctrFrmNm;
    
    /** 강의_회차_방식_코드 */
    private String lctrTmeMthdCd;
    
    /** 강의_회차_방식_코드명 */
    private String lctrTmeMthdNm;
    
    /** 필수_주제_이름 */
    private String essntlSbjctNm;
    
    /** 필수_주제_차시 */
    private String essntlSbjctRnd;
    
    /** 선택_주제_이름 */
    private String choiseSbjctNm;
    
    /** 선택_주제_차시 */
    private String choiseSbjctRnd;
    
    /** 운영_회기 */
    private String operSesn;
    
    /** 회기_교육_차시 */
    private String sesnEduRnd;
    
    /** 전체_운영_차시 */
    private String wholOperRnd;
    
    /** 차시_교육_인원수 */
    private String rndEduNope;
    
    /** 전체_교육_인원수 */
    private String wholEduNope;
    
    /** 프로그램_배경 */
    private String prgrmBcrn;
    
    /** 교육_적절성 */
    private String eduAppro;
    
    /** 프로그램_우수성 */
    private String prgrmDstnctn;
    
    /** 운영_계획 */
    private String operPlan;
    
    /** 연간_이수_예정_인원수 */
    private Integer fyerComplPrearngeNope;
    
    /** 1월_횟수 */
    private String janCnt;
    
    /** 2월_횟수 */
    private String febCnt;
    
    /** 3월_횟수 */
    private String marCnt;
    
    /** 4월_횟수 */
    private String aprCnt;
    
    /** 5월_횟수 */
    private String mayCnt;
    
    /** 6월_횟수 */
    private String juneCnt;
    
    /** 7월_횟수 */
    private String julyCnt;
    
    /** 8월_횟수 */
    private String augCnt;
    
    /** 9월_횟수 */
    private String septCnt;
    
    /** 10월_횟수 */
    private String octCnt;
    
    /** 11월_횟수 */
    private String novCnt;
    
    /** 12월_횟수 */
    private String dcmCnt;
    
    /** 프로그램_이름 */
    private String[] prgrmNm;
    
    /** 교육_차시 */
    private String[] eduRnd;
    
    /** 교육_인원수 */
    private Integer[] eduNope;
    
    /** 순서 */
    private Integer[] ordr;
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
