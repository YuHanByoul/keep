package com.kbrainc.plum.mng.inqry.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

@Data
public class ManagerVo extends ParentRequestVo {
    private Integer telinqryid;
    private Integer userid;
    private String nm;
    private String acnt;
}
