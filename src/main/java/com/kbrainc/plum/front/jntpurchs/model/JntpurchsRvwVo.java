package com.kbrainc.plum.front.jntpurchs.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.Data;

/**
 * 
 * 환경교육 교구 공동구매 후기 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.model
 * - JntpurchsRvwVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsRvwVo.java
 * @Description : 환경교육 교구 공동구매 후기 Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 22.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.JntpurchsRvwVo")
public class JntpurchsRvwVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매 아이디 */
    private int jntpurchsid;
    
    /** 후기 등록 계정 */
    private String acnt;
    
    /** 공동구매 모집명 */
    private String jntpurchsNm;
    
    /** 후기 내용 */
    private String rvwCn;
    
    /** 후기 점수 */
    private Integer rvwScr;
    
    /** 계정 마스킹 */
    public void setAcnt(String acnt) {
        this.acnt = StringUtil.maskingAccount(acnt);
    }
    
    /** 후기 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date rvwRegDt;
    
    /** 후기 등록일자 정보 */
    public Date getRvwRegDt() {
        return rvwRegDt != null ? (Date) rvwRegDt.clone() : null;
    }
    
    public void setRvwRegDt(Date rvwRegDt) {
        this.rvwRegDt = regDt != null ? (Date) rvwRegDt.clone() : null;
    }
        
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    /** 수정일자 정보 */
    public Date getMdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    /** 등록일자 정보 */
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    /** 로그인 사용자 정보 */
    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }
    
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    
}