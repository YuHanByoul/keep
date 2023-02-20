package com.kbrainc.plum.mng.pack.model;

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
* com.kbrainc.plum.pack.model
* - PackageindvdAbnrmlVo.java
* </pre>
*
* @ClassName   : PackageindvdAbnrmlVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.13
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class PackageindvdAbnrmlVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 이상 아이이디 */
    private int abnrmlid;
    
    /** 꾸러미 개체  아이이디 */
    private Integer packageindvdid;
    
    /** 꾸러미 아이이디 */
    private Integer packageid;
    
    /** 신청 아이이디 */
    private Integer aplyid;
    
    /** 차시 아이이디 */
    private Integer rndid;
    
    /** 내용   */
    @Size(max = 500, message = "이상 사유는 500자를 넘을 수 없습니다.")
    private String cn;
    
    /** 정상_여부 */
    @Pattern(regexp="[YN]")
    private String nrmltYn;
    
    /** 정상_처리_여부 */
    @Pattern(regexp="[YN]")
    private String nrmltPrcsYn;
    
    /** 정상 처리자 아이이디 */
    private Integer nrmltPrcsPicid;
    
    /** 정상처리 일자   */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date nrmltPrcsDt;
    
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
    
    /** 등록자명  */
    private String regNm;
    
}