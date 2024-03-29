package com.kbrainc.plum.front.wbzn.carbon.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 웹진 > 탄소중립 환경교육 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.carbon.model
* - CarbonEnveduVo.java
* </pre>
*
* @ClassName : CarbonEnveduVo
* @Description : 웹진 > 탄소중립 환경교육 Vo 클래스
* @author : JD
* @date : 2023. 2. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.CarbonEnveduVo")
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
    /** 환경교육_구분_코드명 */
    private String enveduSeCdNm;
    /** 연도 */
    @NotEmpty(message = "연도를 선택해주십시오.")
    private String yr;
    /** 분기 */
    private String qu;
    /** 월 */
    @NotEmpty(message = "월을 선택해주십시오.")
    private String mm;
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
    
    /** 연-월*/
    private String yrMm;
    private int nextCnt;
    private int prevCnt;
    
    private String beforeQuarter;
    private String nextQuarter;
    private String nowDate;
    private String compareQuarter;
    private String compareYear;
    
    
    /** 첨부파일 관련*/
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련*/
    private String searchSeCd;
    private String enveduSearchYr;
    private String enveduSearchMm;
}
