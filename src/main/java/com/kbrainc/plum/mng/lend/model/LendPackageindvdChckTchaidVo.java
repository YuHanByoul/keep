package com.kbrainc.plum.mng.lend.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
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
* - LendPackageindvdChckTchaidVo.java
* </pre>   
*
* @ClassName   : LendPackageindvdChckTchaidVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.03.24
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendPackageindvdChckTchaidVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 교구 아이디 */
    @NotEmpty
    private Integer tchaidid;
    
    /** 점검 아이디 */
    @NotEmpty
    private Integer chckid;
    
    /** 수량 */
    private Integer qnty;
    
    /** 구성품 */
    @Size(max = 500, message = "구성품은 500자를 넘을 수 없습니다.")
    private String cmpnt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
       
    /** 등록자아이디 */
    private int rgtrid;
    
}