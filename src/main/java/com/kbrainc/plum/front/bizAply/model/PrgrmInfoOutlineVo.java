/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 프로그램 개요 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - PrgrmInfoOutlineVo.java
* </pre> 
*
* @ClassName : PrgrmInfoOutlineVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 14.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.PrgrmInfoOutlineVo")
public class PrgrmInfoOutlineVo {
    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 프로그램개요아이디 */
    private Integer prgrmsumryid;
    
    /** 프로그램_이름 */
    private String prgrmNm;
    
    /** 교육_차시 */
    private String eduRnd;
    
    /** 교육_인원수 */
    private Integer eduNope;
    
    /** 순서 */
    private Integer ordr;
}
