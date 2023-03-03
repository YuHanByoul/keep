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
* 자격 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SmrLeaderLicVo.java
* </pre> 
*
* @ClassName : SmrLeaderLicVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.SmrLeaderLicVo")
public class SmrLeaderLicVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 자격아이디 */
    private Integer qlfcid;
    
    /** 자격_이름 */
    private String qlfcNm;
    
    /** 등급 */
    private String grd;
    
    /** 취득일자 */
    private String acqsDe;
    
    /** 발령처 */
    private String wrkplc;
    
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
