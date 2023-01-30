package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 지출항목 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - ExpndArtclVo.java
* </pre>
*
* @ClassName : ExpndArtclVo
* @Description : 지출항목 VO 클래스
* @author : kbrain
* @date : 2023. 1. 25.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class ExpndArtclVo extends ParentRequestVo {

    /** 로그인 사용자 정보*/
    private UserVo user;

    /** 항목ID */
    private Integer artclid;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 지출 항목 */
    private String expndArtcl;

    /** 금액 */
    private Integer amt;

    /** 합계 금액 */
    private Integer sumAmt;

    /** 수정 일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;



}
