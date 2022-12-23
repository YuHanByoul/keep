package com.kbrainc.plum.mng.inqry.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

@Data
public class PopupMemberVo extends ParentRequestVo {
    private Integer USERID;
    private String NM;
    private String ACNT;
    private String MOBLPHON;
    private String EML;
    private String searchSite;
}
