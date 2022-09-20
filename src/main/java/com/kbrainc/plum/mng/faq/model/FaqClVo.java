package com.kbrainc.plum.mng.faq.model;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

@Data
public class FaqClVo extends ParentRequestVo {
	
    /** 로그인사용자정보 */
	private UserVo user;/** **/
	
	private int clid;
	private String cl_nm;
	private String user_se_cd;
	private Integer ord;
	private String use_yn;

    @Override
    public void chkParams() {
        super.chkParams();
        
        this.orderField = "ORD";
        this.orderDirection = ORDER_DIRECTION.asc;

    }
    
}
