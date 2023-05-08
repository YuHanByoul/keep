package com.kbrainc.plum.mng.inqry.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

@Data
public class PopupMemberVo extends ParentRequestVo {
    private Integer userid;
    private String nm;
    private String acnt;
    private String moblphon;
    private String eml;
    private String searchSite;
}
