package com.kbrainc.plum.mng.member.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* 로그인히스토리Vo 클래스.
*
* <pre>
* com.kbrainc.plum.mng.member.model
* - LoginHistVo.java
* </pre>
*
* @ClassName   : LoginHistVo 
* @Description : 로그인히스토리Vo 클래스. 
* @author      : KBRAINC
* @date        : 2021. 3. 11.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LoginHistVo extends ParentRequestVo {
    
    /** IP */
    private String ip;
    
    /** 디바이스_코드 */
    private String deviceCd;
    
    /** 로그인_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date loginDt;
    
    public void setLoginDt(Date loginDt) {
        this.loginDt = loginDt != null ? (Date) loginDt.clone() : null;
    }
    
    
}