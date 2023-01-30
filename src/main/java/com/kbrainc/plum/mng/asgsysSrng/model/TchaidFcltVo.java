package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 교구 및 시설 VO
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - TchaidFcltVo.java
* </pre>
*
* @ClassName : TchaidFcltVo
* @Description : 교구 및 시설 VO
* @author : kbrain
* @date : 2023. 1. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class TchaidFcltVo extends ParentRequestVo {
	/** 로그인 사용자 정보*/
    private UserVo user;

    /** 교구시설아이디 */
    private Integer tchaidfcltid;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 세부 프로그램 이름 */
    private String dtlPrgrmNm;

    /** 교육 장소 */
    private String eduPlc;

    /** 교구 */
    private String tchaid;

    /** 수량 */
    private Integer qnty;

    /** 수정 일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;


}
