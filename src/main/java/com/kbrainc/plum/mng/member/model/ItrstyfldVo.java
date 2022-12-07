package com.kbrainc.plum.mng.member.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 관심분야 VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - ItrstyfldVo.java
 * </pre> 
 *
 * @ClassName : ItrstyfldVo
 * @Description : 관심분야 VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.member.model
* - MemberVo.java
* </pre>
*
* @ClassName   : ItrstyfldVo 
* @Description : TODO 
* @author      : Zeniel
* @date        : 2021. 3. 11.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class ItrstyfldVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자 아이디 */
    private int userid;
    
    /** 관심분야 코드 */
    private int itrstfldCd;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
       
}