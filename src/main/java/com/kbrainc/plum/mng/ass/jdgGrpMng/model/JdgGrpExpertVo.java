package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

import java.util.Date;

@Data
public class JdgGrpExpertVo extends ParentRequestVo {
    private String sbjctCdgrpid = "126";
    private String actvtRgnCdgrpid = "125";
    private String acnt;
    private String nm;
    private String eml;
    private String moblphon;
    private String crtfctNm;
    private String sbjctCd;
    private String rgnCd;
    private String sbjctCdNm;
    private String rgnCdNm;
    private Date regDt;
    private Date mdfcnDt;
}
