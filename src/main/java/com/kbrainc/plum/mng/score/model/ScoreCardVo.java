/**
 * 
 */
package com.kbrainc.plum.mng.score.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.score.model
* - ScoreCardVo.java
* </pre> 
*
* @ClassName : ScoreCardVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ScoreCardVo extends ParentRequestVo {

    /** 사용자 접속정보 */
    private UserVo user;
    
    /** 양식 아이디 */
    private Integer formid;
    
    /** 양식명 */
    @NotEmpty(message = "심사양식명을 입력해주십시오.")
    private String formNm;
    
    /** 양식설명 */
    @NotEmpty(message = "심사양식 설명을 입력해주십시오.")
    private String formExpln;
    
    /** 총점수 */
//    @Pattern(regexp="^[0-9]*$")
    private Integer totScr;
    
    /** 사용여부 */
    @Pattern(regexp="[YN]")
    private String useYn;
    
    /** 심사양식 제출수 */
    private Integer cnt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private Integer mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 심사양식명 검색 키워드 */
    private String searchKeyword;
    
    /** 검색 키워드 */
    private String searchKeywordType;
}
