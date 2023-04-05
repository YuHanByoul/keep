package com.kbrainc.plum.mng.prtpn.eduClssRm.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 유아교육관 -> 교육관관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduClssRm.model
* - EduClssRmVo.java
* </pre>
**
@ClassName : EduClssRmVo
* @Description : 유아교육관 -> 교육관관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class EduClssRmVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 교육관아이디 */
    private int clssrmId;
    /** 교육관_이름 */
    @NotEmpty(message = "교육관명을 입력해주십시오.")
    private String clssrmNm;
    /** 시도_코드 */
    @NotEmpty(message = "교육관지역을 선택해주십시오.")
    private String ctprvnCd;
    /** 시도명 */
    private String ctprvnNm;
    /** 주소 */
    @NotEmpty(message = "주소를 입력해주십시오.")
    private String addr;
    /** 상세주소 */
    @NotEmpty(message = "상세주소를 입력해주십시오.")
    private String addrDtl;
    /** 교육_유형_코드 */
    @NotEmpty(message = "교육유형을 선택해주십시오.")
    private String eduTypeCd;
    /** 교육_유형명 */
    private String eduTypeNm;    
    /** 위탁_기관아이디 */
    @NotEmpty(message = "위탁기관을 선택해주십시오.")
    private String cnsgnInstId;
    /** 위탁_기관명 */
    private String cnsgnInstNm;    
    /** 접수_시작_일자 */
    private String rcptBgngDt;
    /** 접수_종료_일자 */
    private String rcptEndDt;
    /** 교육관_연간_신청_제한_개수 */
    @NotEmpty(message = "교육관 신청 기관별 연간 횟수 제한을 입력해주십시오.")
    private String clssrmFyerAplyLmtCnt;
    /** 교육관_프로그램_최대_신청_기관_개수 */
    @NotEmpty(message = "교육관 프로그램별 최대 신청 기관수를 입력해주십시오.")
    private String clssrmPrgrmMaxAplyInstCnt;
    /** 교육관_관람_최대_신청_인원수 */
    @NotEmpty(message = "교육관 관람 최대 신청 인원수를 입력해주십시오.")
    private String clssrmViewngMaxAplyNope;
    /** 접수_가능_이전_일수 */
    @NotEmpty(message = "교육 당일 이전 접수 가능 일수를 입력해주십시오.")
    private String rcptPsbltyBfrDaycnt;
    /** 비고 */
    private String rmrk;    
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
    private String searchCtprvnCd;
    private String searchEduTypeCd;
    private String searchCnsgnInstId;
}
