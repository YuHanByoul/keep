package com.kbrainc.plum.mng.cmpnt.model;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.tchaid.CmpntVo.model
* - CmpntVo.java
* </pre>
*
* @ClassName   : CmpntVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.02
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class CmpntVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 구성품 아이이디 */
    private int cmpntid;
    
    /** 구성품 이름   */
    @Size(max = 100, message = "구성품명은 100자를 넘을 수 없습니다.")
    private String cmpntNm;
    
    /** 수량_재고   */
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
    
    /** 등록,수정 모드 */
    private String mode;
    
}