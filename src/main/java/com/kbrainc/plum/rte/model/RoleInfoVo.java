package com.kbrainc.plum.rte.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * 
 * 역할정보를 담는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - RoleInfoVo.java
 * </pre> 
 *
 * @ClassName : RoleInfoVo
 * @Description : 역할정보를 담는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@AllArgsConstructor
public class RoleInfoVo extends ParentVo implements Serializable {

    private static final long serialVersionUID = -2981250066670198530L;
    /** 역할아이디 */
    private String roleid;
    /** 이름 */
    private String nm;
    /** 구분_코드 */
    private String seCd;
    /** 대상_기관_코드 */
    private String trgtInsttCd;
}