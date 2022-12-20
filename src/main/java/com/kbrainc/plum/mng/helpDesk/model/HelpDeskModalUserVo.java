package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class HelpDeskModalUserVo extends ParentRequestVo {
    private UserVo user;
    private String nm;
    private String acnt;
    private String instNm;
    private Integer userid;
    private String searchInst;
    private String roleid;
    private ArrayList<String> roleNm;
}
