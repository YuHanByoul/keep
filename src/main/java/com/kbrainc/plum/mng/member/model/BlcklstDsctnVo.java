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
 * 블랙리스트 VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - BlcklstDsctnVo.java
 * </pre> 
 *
 * @ClassName : BlcklstDsctnVo
 * @Description : 사용자VO 클래스
 * @author : KBRAINC
 * @date : 2022. 12. 05.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.member.model
* - BlcklstDsctnVo.java
* </pre>
*
* @ClassName   : BlcklstDsctnVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2022. 12. 05.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class BlcklstDsctnVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 블랙리스트 아이디 */
    private int blcklstid;
    
    /** 사용자 아이디 */
    private int userid;
    
    /** 블랙리스트 사유 */
    @Size(max = 1000, message = "블랙리스트 사유는 20자를 넘을 수 없습니다.")
    private String blcklstRsn;
    
    /**  블랙리스트_여부 */
    @Pattern(regexp="[YN]")
    private String blcklstYn;
    
    /** 처리_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date prcsDt;
    
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
    
    /*******저장 및 검색용 추가*******/
    /** 블랙리스트 적용자 아이디 **/
    private String[]  blcklstIds;
    
    /** 블래리스트 적용 및 해제 CRUD 코드 **/
    private String  updtCd;
    
    private String userNm;
    private String userAcnt;
    private String rgtrNm;
    private String rgtrAcnt;
    
    
    
    
       
}