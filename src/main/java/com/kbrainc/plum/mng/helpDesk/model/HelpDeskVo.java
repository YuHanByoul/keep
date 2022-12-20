package com.kbrainc.plum.mng.helpDesk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

import java.util.Date;

@Data
public class HelpDeskVo extends ParentRequestVo {
    private String clsfCdgrpid = "112";
    private String sttsCdgrpid = "113";
    private Integer inqryid;
    private String clsfCd;
    private String clsfNm;
    private String sttsCd;
    private String sttsCdNm;
    private String ttl;
    private String cn;
    private Integer filegrpid;
    private String instNm;
    private String nm;
    private String acnt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    private String prcrNm;

}
