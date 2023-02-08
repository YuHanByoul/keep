package com.kbrainc.plum.front.book.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 우수환경도서 관리 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.book.model
* - BookVo.java
* </pre>
*
* @ClassName : BookVo
* @Description : 우수환경도서 관리 Vo 클래스
* @author : JD
* @date : 2023. 1. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.BookVo")
public class BookVo  extends ParentRequestVo{
    
    private UserVo user;
    
    
    /** 도서아이디 */
    private Integer bookid;
    /** 교육_주제_코드(중분류) */
    @NotEmpty(message = "교육주제(중분류)를 선택해주십시오.")
    private String eduSbjctCd;
    /** 교육_주제_코드(대분류) */
    @NotEmpty(message = "교육주제(대분류) 선택해주십시오.")
    private String mainEduSbjctCd;
    /** 교육_대상_코드 */
    @NotEmpty(message = "교육대상 선택해주십시오.")
    private String eduTrgtCd;
    /** 작가 */
    private String writr;
    /** 작가_그림 */
    private String writrPictr;
    /** 출판사 */
    private String plscmpn;
    /** 금액 */
    private String amt;
    /** 도서명(제목) */
    @NotEmpty(message = "도서명을 입력해주십시오.")
    private String ttl;
    /** 내용 */
    @NotEmpty(message = "내용을 입력해주십시오.")
    private String cn;
    /** 내용 */
    private Integer hits;
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
    
    private String nextBookid;
    private String nextBookTtl;
    private String beforeBookid;
    private String beforeBookTtl;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    private String ext;
    
    /** 필터 */
    private String searchManinEduSbjctCd;
    private String searchMdleEduSbjctCd;
    private String searchEduTrgtCd;
    private String searchPlscmpn;
    private String startDt;
    private String endDt;
    
    
}
