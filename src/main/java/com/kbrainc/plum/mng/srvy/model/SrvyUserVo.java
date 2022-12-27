package com.kbrainc.plum.mng.srvy.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 설문회원Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srvy.model
 * - TrprVo.java
 * </pre> 
 *
 * @ClassName : SrvyUserVo
 * @Description : 설문Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class SrvyUserVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 사용자 아이디 */
    private int userid;
    
    /** 계정 */
    private String acnt;
    
    /** 이름 */
    private String nm;
    
    /** 소속 */
    private String instNm;
    
    /** 개인회원 전체 대상 여부 */
    private String indvdlMbrYn;
    
    /** 회원 존재 여부 */
    private String isExist;
    
    /** 회원 유형 */
    private String kndCd;
    
    /** 대상자 추가 회원 아이디 목록 */
    private String[] insertUserids;
    
    /** 대상자 삭제 회원 아이디 목록 */
    private String[] deleteUserids;
    
    
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