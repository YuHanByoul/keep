/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.pcntst.model
* - GradeVo.java
* </pre> 
*
* @ClassName : GradeVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class GradeVo {

    /** 사용자정보 */
    private UserVo user;
    
    /**  배분율아이디 */
    private Integer stngid;
    
    /**  공모아이디 */
    private Integer pcntstid;
    
    /**  등급 */
    private String grdNm;
    
    /**  배분율 */
    private Integer rt;
    
    /**  순위 */
    private Integer rkng;
    
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
