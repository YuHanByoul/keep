package com.kbrainc.plum.mng.tchaid.model;

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
public class TchaidWrhousngVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 입고 아이이디 */
    private int wrhousngid;
    
    /** 구성품 아이이디 */
    private Integer cmpntid;
    
    /** 입고 일자*/
    @Size(max = 10, message = "입고일자는 10자를 넘을 수 없습니다.")
    private String wrhousngde;
    
    /** 수량   */
    private Integer qnty;
    
    /** 비고*/
    @Size(max = 200, message = "비고는 100자를 넘을 수 없습니다.")
    private String rmrk;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
}