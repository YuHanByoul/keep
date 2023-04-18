/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 운영성과 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - CapabilityResultVo.java
* </pre> 
*
* @ClassName : CapabilityResultVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 14.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CapabilityResultVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 운영성과 아이디 */
    private String rsltid;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 구분_코드 */
    private String seCd;
    
    /** 구분_코드명 */
    private String seNm;
    
    /** 사업_이름 */
    private String bsnsNm;
    
    /** 사업_기간 */
    private String bsnsPrd;
    
    /** 교육_인원수 */
    private Integer eduNope;
    
    /** 사업_예산 */
    private String bsnsBgt;
    
    /** 운영_내용 */
    private String operCn;
    
    /** 순서 */
    private Integer ordr;
    
    /** ROWSPAN */
    private Integer rowSpan;       
}
