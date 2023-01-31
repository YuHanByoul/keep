package com.kbrainc.plum.mng.jntpurchs.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 공동구매교구Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaidJntpurchs.model
 * - JntpurchsTchaidVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsTchaidVo
 * @Description : 공동구매교구Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 25.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JntpurchsTchaidVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매 아이디 */
    private int jntpurchsid;
    
    /** 교구 아이디 */
    private int tchaidid;
    
    /** 교구 번호 */
    private String tchaidNo;
    
    /** 교구명 */
    private String tchaidNm;
    
    /** 교구유형 코드 */
    private String tchaidTypeCd;
    
    /** 교구유형명 */
    private String tchaidTypeNm;
    
    /** 교육유형 코드 */
    private String eduTypeCd;
    
    /** 교육유형명 */
    private String eduTypeNm;
    
    /** 검색 유형 */
    private String tchaidSearchType;
    
    /** 검색 키워드 */
    private String tchaidSearchKeyword;

    
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