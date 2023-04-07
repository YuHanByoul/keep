package com.kbrainc.plum.mng.cntnts.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 컨텐츠 관리 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.model
* - CntntsVo.java
* </pre>
*
* @ClassName : CntntsVo
* @Description : 컨텐츠 관리 Vo 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CntntsVo  extends ParentRequestVo{
    
    private UserVo user;
    
    
    /** 콘텐츠아이디 */
    private int cntntsid;
    /** 교육주제 */
    private String eduSbjct;
    /** 교육대상 */
    private String eduTrgt;
    /** 교육 주제 코드(s) */
    private String[] eduSbjctCds;
    /** 교육 대상 코드(s) */
    private String[] eduTrgtCds;
    /** 유형_코드(중분류) */
    @NotEmpty(message = "콘텐츠 유형(중분류) 선택해주십시오.")
    private String typeCd;
    /** 유형_코드(대분류) */
    @NotEmpty(message = "콘텐츠 유형(대분류) 선택해주십시오.")
    private String mainTypeCd;
    /** 출처 */
    private String origin;
    /** 제작_년 */
    private Integer mnfctYy;
    /** 재생_분 */
    private Integer plyHour;
    /** 재생_분 */
    private Integer plyMinute;
    /** 재생_초 */
    private Integer plySecnd;
    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String ttl;
    /** 내용 */
    @NotEmpty(message = "내용을 입력해주십시오.")
    private String cn;
    /** 조회수 */
    private Integer hits;
    /** 대표_이미지_파일아이디 */
    private Integer rprsImgFileid;
    /** 첨부_파일그룹아이디 */
    private Integer atchFilegrpid;
    private String delYn;
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 저작권_코드 */
    private String cpyrhtCd;
    
    private String rgtridNm;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 필터 */
    /** 교육주제 */
    private String searchEduSbjctCd;
    private String searchEduTrgtCd;
    private String searchMainTypeCd;
    private String searchMdleTypeCd;
    private String searchBgngDt;
    private String searchEndDt;
    
    
}
