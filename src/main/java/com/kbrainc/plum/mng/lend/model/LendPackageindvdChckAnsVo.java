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
* - LendPackageindvdChkAnsVo.java
* </pre>   
*
* @ClassName   : LendPackageindvdChkAnsVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.03.22
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendPackageindvdChckAnsVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 점검  아이디 */
    private Integer chckid;
    
    /** 항목  아이디 */
    private Integer artclid;
    
    /** 교구  아이디 */
    private Integer tchaidid;
    
    /** 보기아이디 */
    private Integer exid;
    
    /** 보기_내용 */
    @Size(max = 200, message = "보기내용 200자를 넘을 수 없습니다.")
    private String exCn;
    
    /** 조치_사항 */
    @Size(max = 200, message = "조치사항 200자를 넘을 수 없습니다.")
    private String actnMttr;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private Integer mdfrid;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
}