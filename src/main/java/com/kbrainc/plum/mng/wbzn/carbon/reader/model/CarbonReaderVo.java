package com.kbrainc.plum.mng.wbzn.carbon.reader.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
* 탄소중립환경교육 -> 구독자 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.reader.model
* - ReaderVo.java
* </pre>
*
* @ClassName : CarbonReaderVo
* @Description : 탄소중립환경교육 -> 구독자 VO 클래스
* @author : JD
* @date : 2022. 12. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CarbonReaderVo extends ParentRequestVo {
    
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
