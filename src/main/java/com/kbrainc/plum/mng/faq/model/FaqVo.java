package com.kbrainc.plum.mng.faq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Date;

@Data
@NoArgsConstructor
public class FaqVo extends ParentRequestVo {
    private UserVo user;
    private Integer clid;
    private Integer faqid;
    private String title;
    private String cntnts;
    private Integer siteid;
    private Integer ord;
    private Integer newOrd;
    private String useYn;
    private String mdfcnDt;
    private int mdfrid;
    private String nm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    private int rgtrid;
    private String clNm;
    private String searchSite;

    /*순서 변경용*/
    private String mode;

    /**
     * Desc : Constructor of FaqVo.java class
     *
     * @param clid
     * @param title
     * @param cntnts
     * @param ord
     */
    public FaqVo(int clid, String title, String cntnts, String ord) {
        this.clid = clid;
        this.title = title;
        this.cntnts = cntnts;
        this.ord = Integer.parseInt(ord);
    }

    @Override
    public void chkParams() {
        super.chkParams();

        this.orderField = "ORD";
        this.orderDirection = ORDER_DIRECTION.asc;

    }

    /**
     * 로그인사용자정보
     */
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }

    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return clone;
    }


}
