package com.kbrainc.plum.cmm.esylgn.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
* 간편로그인 Vo클래스
*
* <pre>
* com.kbrainc.plum.cmm.esylgn.model
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
@Alias("cmm.EsylgnVo")
public class EsylgnVo extends ParentRequestVo {
    
    /** 사용자 아이디 */
    private String userid;
    
    /** CI */
    private String ci;
    
    /** USERKEY */
    private String userkey; 
    
    /** 간편로그인 코드 */
    private String esylgnCd;
    
    /** 사용자계정(간편로그인계정아님) */
    private String acnt;
    
    /** 디지털원패스 연동여부 */
    private String onepassLinkYn;
    
    /** 간편로그인 연결갯수 */
    private int esylgnLinkCnt;
}