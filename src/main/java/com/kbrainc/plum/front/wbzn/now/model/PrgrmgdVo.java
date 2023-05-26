package com.kbrainc.plum.front.wbzn.now.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 웹진 > 환경교육NOW 프로그램 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.now.model
* - PrgrmgdVo.java
* </pre>
*
* @ClassName : PrgrmgdVo
* @Description : 웹진 > 환경교육NOW 프로그램 Vo 클래스
* @author : JD
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.PrgrmgdVo")
public class PrgrmgdVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 프로그램안내아이디 */
    private int prgrmid;
    /** 분류_코드 */
    private String clsfCd;
    /** 지역구분_코드 */
    @NotEmpty(message = "지역구분을 선택해주십시오.")
    private String rgnCd;
    /** 지역구분_코드명*/
    private String rgnCdNm;
    /** 연도 */
    @NotEmpty(message = "연도를 선택해주십시오.")
    private String yr;
    /** 월 */
    @NotEmpty(message = "월을 선택해주십시오.")
    private String mm;
    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String ttl;
    /** 일시 */
    @NotEmpty(message = "일시를 입력해주십시오.")
    private String schdl;
    /** 유형 */
    private String typeCd;
    /** URL */
    private String url;
    /** 썸네일_파일아이디 */
    private int thmbnFileid;
    /** 내용_STYLE */
    private String cnStyle;
    /** 내용 */
    private String cn;
    /** 내용_요약 */
    @NotEmpty(message = "요약 내용을 입력해주십시오.")
    @Size(max = 200, message = "요약 내용은 200자 이하여야 합니다.")
    private String cnSumry;
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
    
    /** 연-월*/
    private String yrMm;
    private int nextCnt;
    private int prevCnt;
    
    private String beforeMonth;
    private String nextMonth;
    private String nowDate;
    private String compareDate;
    
    /** 첨부파일 관련*/
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련*/
    private String searchRgnCd;
    private String prgrmgdSearchYr;
    private String prgrmgdSearchMm;
}
