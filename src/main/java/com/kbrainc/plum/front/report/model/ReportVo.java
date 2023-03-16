/**
 * 
 */
package com.kbrainc.plum.front.report.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.front.report.model
* - ReportVo.java
* </pre> 
*
* @ClassName : ReportVo
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ReportVo extends ParentRequestVo {

    private String searchFldCd;
    private String searchPcntstNm;
    private String searchPrgrmNm;
    private String searchReportSttsCd;
    
    private UserVo user;
    
    private Integer pcntstid;
    private Integer aplyid;
    private String fldCd;
    private String fldNm;
    private String pcntstNm;
    private String aplyno;
    private String prgrmNm;
    private String reportSttsCd;
    private String reportSttsNm;
    private String reportSeCd;
    private String reportSeNm;
    private String reportBgngDt;
    private String reportEndDt;
    private String bsnsDe;
    private String crudBtn;
    private Integer splmntid;
    private Integer userid;
    private String aplcntNm;
    private Integer instid;
    private String instNm;
    private String cnsltngTrgtCn;
    private String cnBfr;
    private String cnAftr;
    private String rslt;
    private String vstDt;
    private Integer reportid;
    private Integer atchFilegrpid;
    private String etcCn;
    private String trgt;
    private String sbjct;
    private Integer cnsltngid;
    private String jsonString;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자아이디 */
    private String rgtrid;
}
