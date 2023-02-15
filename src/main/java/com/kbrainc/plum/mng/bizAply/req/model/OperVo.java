/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 운영개요 VO 클래스. 
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
public class OperVo {

    /** 로그인세션정보 */
    private UserVo user;
    
    /** 신청아이디 */ 
    private Integer aplyid;
    
    /** 동아리_이름 */
    private String clubNm;
    
    /** 교육_인원수 */
    private Integer eduNope;

    /** 교육주제 구분상위코드 */
    private String eduSbjctCd1;
    
    /** 교육주제 구분코드 */
    private String eduSbjctCd;
    
    /** 교육_주제_코드 */
    private List<String> eduSbjctCds;

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
    
    /** 주제_구분_코드 */
    private String sbjctSeCd;
    
    /** 주제_구분_코드 */
    private String[] sbjctSeCdArr;
    
    /** 차시 */
    private Integer[] rnd;
    
    /** 교육_주제_상위 코드 */
    private String[] eduSbjctCdArr1;
    
    /** 교육_주제_코드 */
    private String[] eduSbjctCdArr;
}
