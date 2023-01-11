package com.kbrainc.plum.mng.pvtEnveduGrp.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
* 민간 환경교육단체 현황 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.model
* - PvtEnvEduGrpVo.java
* </pre>
*
* @ClassName : PvtEnvEduGrpVo
* @Description : 민간 환경교육단체 현황 Vo 클래스
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class PvtEnvEduGrpVo extends ParentRequestVo {

    /** 기관 아이디 */
    private int instid;
    /** 지역_코드 */
    private String rgnCd;
    /** 기관명 */
    private String instNm;
    /** 기관_유형 */
    private String cdNm;
    /** 대표자명 */
    private String rprsvNm;
    /** 전화번호 */
    private String telno;
    /** 홈페이지 */
    private String hmpg;
    /** 등록일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 지역 코드(공통코드) */
    private int ctprvnCd;
    private String ctprvnNm;
    
}
