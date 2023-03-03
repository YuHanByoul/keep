/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 경력 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SmrLeaderCarrVo.java
* </pre> 
*
* @ClassName : SmrLeaderCarrVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.SmrLeaderCarrVo")
public class SmrLeaderCarrVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 경력아이디 */
    private Integer careerid;
    
    /** 기간 */
    private String prd;
    
    /** 활동_이름 */
    private String actvtNm;
    
    /** 활동_유형 */
    private String actvtType;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private Integer mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
}
