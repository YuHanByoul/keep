package com.kbrainc.plum.mng.notice.model;


import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVo extends ParentRequestVo  {
    private UserVo user;
    private String siteNm;
    private String title;
    private String fxdNtcYn;
    private Integer hits;
    private String regUserNm;
    private String regDt;
    private String searchSite;
}
