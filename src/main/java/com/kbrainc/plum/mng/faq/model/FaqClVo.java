package com.kbrainc.plum.mng.faq.model;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class FaqClVo extends ParentRequestVo {
	
    /** 로그인사용자정보 */
	private UserVo user;/** **/
	
	private Integer clid;

    @NotEmpty(message= "분류명을 입력해 주십시오.")
    @Size(max = 100, message = "분류명은 100자를 넘을 수 없습니다.")
	private String clNm;

	private Integer siteid;

	private Integer ord;

	private Integer newOrd;

	private String useYn;

    private String searchSite;

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
