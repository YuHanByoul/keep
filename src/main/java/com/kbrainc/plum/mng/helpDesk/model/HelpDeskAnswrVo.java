package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

@Data
public class HelpDeskAnswrVo {
    private UserVo user;
    private Integer inqryid;
    private Integer ansid;
    private String ttl;
    private String cn;
    private Integer filegrpid;
    private Integer prcrid;
    private Integer prcrNm;
    private String rlsYn;
    private String ansDe;
    private String[] helpDeskManager;
    private String sttsCd;
}
