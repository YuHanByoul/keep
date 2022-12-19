package com.kbrainc.plum.mng.wbzn.carbon.envedu.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 환경교육NOW -> 환경교육관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.model
* - EnveduVo.java
* </pre>
*
* @ClassName : EnveduVo
* @Description : 환경교육NOW -> 환경교육관리 VO 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CarbonEnveduVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 환경교육아이디 */
    private int enveduid;
    /** 분류_코드 */
    private String clsfCd;
    /** 환경교육_구분_코드 */
    @NotEmpty(message = "환경교육구분을 선택해주십시오.")
    private String enveduSeCd;
    /** 연도 */
    @NotEmpty(message = "연도를 선택해주십시오.")
    private String yr;
    /** 분기 */
    @NotEmpty(message = "분기를 선택해주십시오.")
    private String qu;
    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String ttl;
    /** 유형 코드 */
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
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련 */
    private String searchSeCd;
    private String searchYr;
    private String searchQu;
}
