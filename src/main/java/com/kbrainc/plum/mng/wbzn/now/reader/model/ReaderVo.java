package com.kbrainc.plum.mng.wbzn.now.reader.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 환경교육NOW -> 프로그램안내관리 VO 클래스
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
public class ReaderVo extends ParentRequestVo {
    
    /** 구독자아이디 */
    private int readerid;
    /** 분류_코드 */
    private String clsfCd;
    /** 이름 */
    private String nm;
    /** 휴대폰 */
    private String moblphon;
    /** 이메일 */
    private String eml;
    /** 직업 */
    private String cr;
    /** 가입일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date joinDt;
    
    
    /** 검색 관련*/
    private String searchRgnCd;
    private String searchYr;
    private String searchMm;
}
