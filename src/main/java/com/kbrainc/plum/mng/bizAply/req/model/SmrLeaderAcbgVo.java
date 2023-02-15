/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 학력 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SmrLeaderAcbgVo.java
* </pre> 
*
* @ClassName : SmrLeaderAcbgVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SmrLeaderAcbgVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 학력아이디 */
    private Integer acbgid;
    
    /** 시작_일자 */
    private String bgngDe;
    
    /** 종료_일자 */
    private String endDe;
    
    /** 기간 */
    private String eduDe;
    
    /** 학교_이름 */
    private String schlNm;
    
    /** 전공 */
    private String mjr;
    
    /** 학위 */
    private String dgr;
    
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
