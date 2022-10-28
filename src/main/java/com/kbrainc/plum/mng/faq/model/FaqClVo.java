package com.kbrainc.plum.mng.faq.model;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
public class FaqClVo extends ParentRequestVo {
	
    /** 로그인사용자정보 */
	private UserVo user;/** **/
	
	private int clid;
	private String clNm;
	private String userSeCd;
	private Integer ord;
	private Integer newOrd;
	private String useYn;

    @Override
    public void chkParams() {
        super.chkParams();
        
        this.orderField = "ORD";
        this.orderDirection = ORDER_DIRECTION.asc;

    }
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   
   
}
