package com.kbrainc.plum.mng.scheduling.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* 스케줄이력Vo 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.model
* - SchedulingHistVo.java
* </pre>
*
* @ClassName   : SchedulingHistVo 
* @Description : 스케줄이력Vo 클래스. 
* @author      : ZENIEL
* @date        : 2021. 3. 17.
* @Version     :  
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class SchedulingHistVo extends ParentRequestVo {

    /** 트리거아이디 */
    private Integer triggerid;
    
    /** 작업_시작_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date jobStrtDt;
    
    /** 작업_종료_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date jobEndDt;
    
    /** 상태코드(S:성공, F:실패) */
    private String sttsCd;
    
    
    public void setJobStrtDt(Date jobStrtDt) {
        this.jobStrtDt = jobStrtDt != null ? (Date) jobStrtDt.clone() : null;
    }
    
    public Date getmdfcnDt() {
        return jobStrtDt != null ? (Date) jobStrtDt.clone() : null;
    }
    
    public void setJobEndDt(Date jobEndDt) {
        this.jobEndDt = jobEndDt != null ? (Date) jobEndDt.clone() : null;
    }
    
    public Date getUpdtD() {
        return jobEndDt != null ? (Date) jobEndDt.clone() : null;
    }

    
    
    
}