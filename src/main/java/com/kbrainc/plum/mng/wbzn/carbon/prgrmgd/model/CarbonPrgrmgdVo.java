package com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 탄소중립환경교육 -> 프로그램안내관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.model
* - PrgrmgdVo.java
* </pre>
*
* @ClassName : PrgrmgdVo
* @Description : 환경교육NOW -> 프로그램안내관리 VO 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CarbonPrgrmgdVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 프로그램안내아이디 */
    private int prgrmid;
    /** 분류_코드 */
    private String clsfCd;
    /** 지역구분_코드 */
    @NotEmpty(message = "지역구분을 선택해주십시오.")
    private String rgnCd;
    /** 연도 */
    @NotEmpty(message = "연도를 선택해주십시오.")
    private String yr;
    /** 분기 */
    @NotEmpty(message = "분기를 선택해주십시오.")
    private String qu;
    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String ttl;
    /** 일시 */
    @NotEmpty(message = "일시를 입력해주십시오.")
    private String dt;
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
    
    /** 연-분기 */
    private String yrQu;
    
    /** 첨부파일 관련*/
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련*/
    private String searchRgnCd;
    private String searchYr;
    private String searchQu;
}
