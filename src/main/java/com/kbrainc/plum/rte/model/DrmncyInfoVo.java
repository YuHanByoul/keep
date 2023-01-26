package com.kbrainc.plum.rte.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * 휴면회원 상태 정보를 담는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - DrmncyInfoVo.java
 * </pre> 
 *
 * @ClassName : DrmncyInfoVo
 * @Description : 휴면회원 상태 정보를 담는 클래스
 * @author : KBRAINC
 * @date : 2023. 1. 25.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class DrmncyInfoVo extends ParentVo implements Serializable {

    private static final long serialVersionUID = -2227971712464044142L;

    /** 사용자아이디 */
    private String userid;
    
    /** 개인정보_유효기간 */
    private Integer prvcVldty;
    
    /** 휴면전환일자 */
    private String regDt;
    
    /** 사용여부 */
    private boolean isUsed;
}