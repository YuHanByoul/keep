package com.kbrainc.plum.mng.lend.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.lend.model
* - LendAplyDlivyVo.java
* </pre>   
*
* @ClassName   : LendAplyDlivyVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.03.20
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendAplyDlivyVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    
    /** 신청 아이디 */
    private Integer aplyid;
    
    /** 차시  아이디 */
    private Integer rndid;
    
    /** 꾸러미 개체   아이디 */
    private Integer packageindvdid;
    
    /** 꾸러미 개체   번호 */
    private String packageindvdNo;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dlivyPrcsDt;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date wrhousngPrcsDt;
    
    /** 등록자아이디 */
    private Integer dlivyPicid;
    
    /** 등록자아이디 */
    private Integer wrhousngPicid;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    
    /** 꾸러미 개체 번호*/
    private String indvdno;
    
    /** 꾸러미 명*/
    private String packageindvdNm;
    
}