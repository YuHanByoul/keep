package com.kbrainc.plum.mng.tchaid.model;

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
* com.kbrainc.plum.tchaid.model
* - TchaidCmpntCmpstnVo.java
* </pre>
*
* @ClassName   : TchaidCmpntCmpstnVo 
* @Description : 교구 구성품 구성 VO 
* @author      : KBRAINC
* @date        : 2023.02.06
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class TchaidCmpntCmpstnVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 교구 아이이디 */
    private int tchaidid;
    
    /** 구성품 아이이디 */
    private Integer cmpntid;
    
    /** 구성품명 */
    private String cmpntNm;
    
    /** 구성품 구성 수량 */
    private Integer qntyCmpstn;
    
    /** 구성품 재고 */
    private Integer qntyInvntry;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
}