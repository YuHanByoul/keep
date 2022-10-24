package com.kbrainc.plum.mng.faq.model;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FaqVo extends ParentRequestVo {

    private UserVo user;

    private Integer clid;
    private int faqid;
    private String title;
    private String cntnts;
    private String userSeCd;
    private Integer ord;
    private Integer newOrd;
    private String useYn;
    private String updtDt;
    private int updtuserid;
    private String regDt;
    private int reguserid;
    
    private String clNm;

    /**
     * 
     * Desc : Constructor of FaqVo.java class
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

}
