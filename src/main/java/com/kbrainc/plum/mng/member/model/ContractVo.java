package com.kbrainc.plum.mng.member.model;
import lombok.Data;

/**
 * 
 * 연락처정보VO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - ContractVo.java
 * </pre> 
 *
 * @ClassName : ContractVo
 * @Description : 연락처정보VO 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class ContractVo {
    
    /** 번호 */
    private int rowNumber;
    
    /** 사용자아이디배열 */
    private String[] userids;
    
    /** 사용자이이디 */
    private int userid;
    
    /** 계정 */
    private String acnt;
    
    /** 사용자_이름 */
    private String nm;
    
    /** 휴대폰번호 */
    private String mobno;
    
    /** 이메일 */
    private String email;
    
    /** 휴면여부 */
    private String drmncyYn;
}