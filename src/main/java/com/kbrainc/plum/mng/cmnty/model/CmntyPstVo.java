package com.kbrainc.plum.mng.cmnty.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 커뮤니티게시글Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.model
 * - QestnrVo.java
 * </pre> 
 *
 * @ClassName : CmntyPstVo
 * @Description : 커뮤니티게시글Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 15.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class CmntyPstVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 게시글 아이디 */
    private int pstid;
    
    /** 게시판 아이디 */
    private int bbsid;
    
    /** 게시판 분류 아이디 */
    private String bbsClsfid;
    
    /** 제목 */
    private String ttl;
    
    /** 내용 */
    private String cn;
    
    /** 부모 게시글 아이디 */
    private String parntsPstid;
    
    /** 그룹 */
    private String grp;
    
    /** 깊이 */
    private String dpth;
    
    /** 정렬순서 */
    private String sortordr;
    
    /** 조회수 */
    private String hits;
    
    /** 고정 공지 여부 */
    private String fxNtcYn;
    
    /** 로그인 여부 */
    private String lgnYn;
    
    /** 파일 그룹 아이디 */
    private String filegrpid;
    
    /** 작성자 계정 */
    private String acnt;
    
    /** 작성자 명 */
    private String nm;
    
    
    /** 고정 공지 시작 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date fxNtcBgngDt;
    
    /** 고정 공지 종료 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date fxNtcEndDt;
    
    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private int rgtrid;
    
    /** 고정 공지 시작 일자 정보 */
    public Date getFxNtcBgngDt() {
        return fxNtcBgngDt != null ? (Date) fxNtcBgngDt.clone() : null;
    }
    
    public void setFxNtcBgngDt(Date fxNtcBgngDt) {
        this.fxNtcBgngDt = fxNtcBgngDt != null ? (Date) fxNtcBgngDt.clone() : null;
    }
    
    /** 고정 공지 종료 일자 정보 */
    public Date getFxNtcEndDt() {
        return fxNtcEndDt != null ? (Date) fxNtcEndDt.clone() : null;
    }
    
    public void setFxNtcEndDt(Date fxNtcEndDt) {
        this.fxNtcEndDt = fxNtcEndDt != null ? (Date) fxNtcEndDt.clone() : null;
    }
    
    /** 수정 일자 정보 */
    public Date getMdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    /** 등록 일자 정보 */
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