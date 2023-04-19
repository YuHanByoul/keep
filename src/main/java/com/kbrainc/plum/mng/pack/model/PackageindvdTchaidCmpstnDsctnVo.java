package com.kbrainc.plum.mng.pack.model;

import java.util.Date;

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
* com.kbrainc.plum.pack.model
* - PackageindvdCmpntCmpstnDsctnVo.java
* </pre>
*
* @ClassName   : PackageindvdCmpntCmpstnDsctnVo 
* @Description : 꾸러미 교구 구성 내역 VO 
* @author      : KBRAINC
* @date        : 2023.02.13
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class PackageindvdTchaidCmpstnDsctnVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 내역 아이이디 */
    private int dsctnid;
    
    /** 꾸러미 아이이디 */
    private int packageindvdid;
    
    /** 교구 아이이디 */
    private Integer tchaidid;
    
    /** 수량 */
    private Integer qnty;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
}