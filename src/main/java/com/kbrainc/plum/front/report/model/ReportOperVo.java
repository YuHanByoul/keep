/**
 * 
 */
package com.kbrainc.plum.front.report.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.front.report.model
* - ReportOperVo.java
* </pre> 
*
* @ClassName : ReportOperVo
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ReportOperVo {

    private UserVo user;
    private Integer operid;
    private Integer reportid;
    private String seCd;
    private String bgngDe;
    private String endDe;
    private Integer rnd;
    private Integer nope;
    private Integer mnt;
    private String rmrk;
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
