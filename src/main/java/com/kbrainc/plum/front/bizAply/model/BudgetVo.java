/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 소요예산 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - BudgetVo.java
* </pre> 
*
* @ClassName : BudgetVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.BudgetVo")
public class BudgetVo extends ParentRequestVo {

    /** 로그인세션정보 */
    private UserVo user;
    
    /** 공모아이디 */ 
    private Integer pcntstid;
    
    /** 신청아이디 */ 
    private Integer aplyid;
    
    /** 예산 아이디 */
    private Integer bgtid;
    
    /** 사업신청구분 코드 */
    private String fldCd;
    
    /** 예산항목 코드 */
    private String expitmCd;

    /** 예산항목 코드 */
    private String code;
    
    /** 예산항목 코드명 */
    private String expitmNm;
    
    /** 비용 */
    private Integer amt;
    
    /** 총 비용 */
    private Integer  totalAmt;
    
    /** 내역 */
    private String dsctn;
    
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
    
    private Integer[] bgtidArr;
    private String[] codeArr;
    private Integer[] amtArr;
    private String[] dsctnArr;
    
}
