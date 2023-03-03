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
* 전문인력 보유현황 및 업무내용 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SmrLeaderJobVo.java
* </pre> 
*
* @ClassName : SmrLeaderJobVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.SmrLeaderJobVo")
public class SmrLeaderJobVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 지도자아이디 */
    private Integer ldrid;
    
    /** 구분 */
    private String se;
    
    /** 성명 */
    private String nm;
    
    /** 업무내용 */
    private String taskCn;
    
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
