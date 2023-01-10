package com.kbrainc.plum.mng.pvtEnveduGrp.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.model
* - PvtEnvEduGrpVo.java
* </pre>
*
* @ClassName : PvtEnvEduGrpVo
* @Description : TODO
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class PvtEnvEduGrpVo extends ParentRequestVo {

    private int instid;
    private String rgnCd;
    private String instNm;
    private String cdNm;
    private String rprsvNm;
    private String telno;
    private String hmpg;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 지역 코드(공통코드) */
    private int ctprvnCd;
    private String ctprvnNm;
    
}
