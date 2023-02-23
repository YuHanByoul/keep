/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.srng.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 심사관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.srng.model
* - BizAplySrngVo.java
* </pre> 
*
* @ClassName : BizAplySrngVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class BizAplySrngVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 제출아이디 */
    private Integer sbmsnid;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 양식아이디 */
    private Integer formid;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 방문_일시 */
    private String vstDt;
    
    /** 심사_의견 */
    private String srngOpnn;
    
    /** 의견1 */
    private String opnn1;
    
    /** 의견2 */
    private String opnn2;
    
    /** 의견3 */
    private String opnn3;
    
    /** 의견4 */
    private String opnn4;
    
    /** 총_점수 */
    private Integer totScr;
    
    /** 점수 */
    private Integer scr;
    
    /** 제출_일시 */
    private String sbmsnDt;
    
    /** 제출_상태_코드 */
    private String sbmsnSttsCd;

    /** 제출_상태_코드명 */
    private String sbmsnSttsNm;
    
    /** 사용자_아이피 */
    private String userIp;
    
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private Integer mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 문항 데이터 */
    private String jsonString;
    
    /** 총 심사배점 */
    private Integer totalSum;
    
    /** 심사위원 이름 */
    private String nm;
}
