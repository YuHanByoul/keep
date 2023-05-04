/**
 * 
 */
package com.kbrainc.plum.mng.eduInst.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 보완요청 VO 클래스
*
* <pre>
* - SupplementVo.java
* </pre> 
*
* @ClassName : SupplementVo
* @Description : TODO
* @author : LHM
* @date : 2023. 5. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("EduSupplementVo")
public class SupplementVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 보완아이디 */
    private Integer splmntid;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 요청_내용 */
    private String dmndCn;
    
    /** 요청자아이디 */
    private Integer rqstrid;
    
    /** 답변_내용 */
    private String ansCn;
    
    /** 답변자아이디 */
    private Integer answrrid;
    
    /** 요청_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dmndDt;
    
    /** 답변_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date ansDt;
    
    /** 답변_상태_코드 */
    private String ansSttsCd;
    
    /** 답변_상태_코드명 */
    private String ansSttsNm;
    
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
