/**
 * 
 */
package com.kbrainc.plum.mng.envEdu.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 우수환경교육 프로그램 관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.envEdu.model
* - PrgrmMngVo.java
* </pre> 
*
* @ClassName : PrgrmMngVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class PrgrmMngVo extends ParentRequestVo {

    // 검색조건 start 
    /** 키워드 구분 */
    private String searchKeywordType;
    /** 키워드 */
    private String searchKeyword;
    /** 교육주제(대분류) */
    private String searchSbjctCd_1;
    /** 교육주제(중분류) */
    private String searchSbjctCd_2;
    /** 대상 */
    private String searchTrgtCd;
    /** 검색기간_시작일 */
    private String searchStartRegDt;
    /** 검색기간_종료일 */
    private String searchEndRegDt;
    // 검색조건 end
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 프로그램아이디 */
    private int prgrmid;
    /** 프로그램_이름 */
    private String prgrmNm;
    /** 기관_이름 */
    private String instNm;
    /** 주제_코드(대분류) */
    private String sbjctCd1;
    /** 주제_코드 */
    private String sbjctCd;
    /** 주제_코드명 */
    private String sbjctCdNm;
    /** 대상_코드 */
    private String trgtCd;
    /** 대상_코드명 */
    private String trgtCdNm;
    /** 내용 */
    private String cn;
    /** 대표_이미지_파일아아디 */
    private int rprsImgFileid;
    /** 첨부_파일그룹아이디 */
    private int atchFilegrpid;
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    /** 등록자아이디 */
    private String rgtrid;
    /** 등록자명 */
    private String rgtrNm;

    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
}
