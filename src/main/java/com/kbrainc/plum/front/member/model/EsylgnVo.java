package com.kbrainc.plum.front.member.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
* 간편로그인 Vo클래스
*
* <pre>
* com.kbrainc.plum.front.member.model
* - EsylgnVo.java
* </pre>
*
* @ClassName   : EsylgnVo 
* @Description : 간편로그인 Vo클래스 
* @author      : KBRAINC
* @date        : 2023. 1. 30.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
@Alias("front.EsylgnVo")
public class EsylgnVo extends ParentRequestVo {
    
    /** 사용자 아이디 */
    private String userid;
    
    /** CI */
    private String ci;
    
    /** USERKEY */
    private String userkey; 
    
    /** 간편로그인 코드 */
    private String esylgnCd;
}