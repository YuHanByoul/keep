/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 사업수행계획 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - ProcPlanVo.java
* </pre> 
*
* @ClassName : ProcPlanVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 8.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ProcPlanVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 인건비_합계 */
    private Integer lbcstSum;
    
    /** 운영비_합계 */
    private Integer orpnsSum;
    
    /** 여비_합계 */
    private Integer trvctSum;
    
    /** 경비_합계 */
    private Integer expensSum;
    
    /** 반환금_합계 */
    private Integer refundSum;
    
    /** 총계 */
    private Integer totalSum;
    
    /** 운영_이전_홍보_구분 */
    private String operBfrPrmtnSe;
    
    /** 운영_이전_홍보_시기 */
    private String operBfrPrmtnSess;
    
    /** 운영_이전_홍보_방법 */
    private String operBfrPrmtnMthd;
    
    /** 운영_중간_홍보_구분 */
    private String operMdlPrmtnSe;
    
    /** 운영_중간_홍보_시기 */
    private String operMdlPrmtnSess;
    
    /** 운영_중간_홍보_방법 */
    private String operMdlPrmtnMthd;
    
    /** 운영_이후_홍보_구분 */
    private String operAftrPrmtnSe;
    
    /** 운영_이후_홍보_시기 */
    private String operAftrPrmtnSess;
    
    /** 운영_이후_홍보_방법 */
    private String operAftrPrmtnMthd;
    
    /** 전화_접수_여부 */
    @Pattern(regexp="[YN]")
    private String telRcptYn;
    
    /** 신청_접수_여부 */
    @Pattern(regexp="[YN]")
    private String aplyRcptYn;
    
    /** 온라인_접수_여부 */
    @Pattern(regexp="[YN]")
    private String onlnRcptYn;
    
    /** 기타_여부 */
    @Pattern(regexp="[YN]")
    private String etcYn;
    
    /** 기타_내용 */
    private String etcCn;
    
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
