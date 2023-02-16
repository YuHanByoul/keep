/**
 * 
 */
package com.kbrainc.plum.mng.score.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 문항 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.score.model
* - QuestionVo.java
* </pre> 
*
* @ClassName : QuestionVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class QuestionVo extends ParentRequestVo {
    
    /** 사용자정보 */
    private UserVo user;
    
    /** 심사양식아이디 */
    private Integer formid;
    
    /** 문항아이디 */
    private Integer qitemid;
    
    /** 문항구분코드 */
    private String qitemSeCd;
    
    /** 문항구분코드명 */
    private String qitemSeNm;
    
    /** 문항 */
    private String qitem;
    
    /** 순서 */
    private Integer ordr;
    
    /** 배점 */
    private Integer altm;
    
    /** 심사양식 총 배점 */
    private Integer totScr;
    
    /** 총배점 */
    private Integer totalAltm;
    
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
    
    
    /** 문항구분코드 */
    private String[] qitemSeCdArr;
    
    /** 문항 */
    private String[] qitemArr;
    
    /** 순서 */
    private Integer[] ordrArr;
    
    /** 배점 */
    private Integer[] altmArr;
}
