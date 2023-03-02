/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 운영개요 운영차시 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - OperVo.java
* </pre> 
*
* @ClassName : OperVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.OperRndVo")
public class OperRndVo {

    /** 로그인세션정보 */
    private UserVo user;
    
    /** 신청아이디 */ 
    private Integer aplyid;
    
    /** 차시아이디 */
    private Integer rndid;
    
    /** 주제_구분_코드 */
    private String sbjctSeCd;
    
    /** 주제_구분_코드명 */
    private String sbjctSeNm;

    /** 차시 */
    private Integer rnd;

    /** 차시 */
    private Integer rowSpan;
    
    /** 교육_주제_상위 코드 */
    private String eduSbjctCd1;
    
    /** 교육_주제_코드 */
    private String eduSbjctCd;

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
