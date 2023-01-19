package com.kbrainc.plum.mng.cntnts.model;

import java.sql.Date;

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
    /** 교육_주제_코드(중분류) */
    private String eduSbjctCd;
    /** 교육_주제_코드(대분류) */
    private String mainEduSbjctCd;
    /** 유형_코드(중분류) */
    private String typeCd;
    /** 유형_코드(대분류) */
    private String mainTypeCd;
    /** 교육_대상_코드 */
    private String eduTrgtCd;
    /** 출처 */
    private String origin;
    /** 제작_년 */
    private Integer mnfctYy;
    /** 재생_초 */
    private Integer plyMinute;
    /** 재생_초 */
    private Integer plySecnd;
    /** 제목 */
    private String ttl;
    /** 내용 */
    private String cn;
    /** 대표_이미지_파일아이디 */
    private Integer rprsImgFileid;
    /** 첨부_파일그룹아이디 */
    private Integer atchFilegrpid;
    /** 수정_일시 */
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    private String rgtridNm;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 필터 */
    /** 교육주제 */
    private String searchManinEduSbjctCd;
    private String searchMdleEduSbjctCd;
    private String searchEduTrgtCd;
    private String searchMainTypeCd;
    private String searchMdleTypeCd;
    private String startDt;
    private String endDt;
    
    
}
