package com.kbrainc.plum.mng.cmnty.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 커뮤니티Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.model
 * - CmntyVo.java
 * </pre> 
 *
 * @ClassName : CmntyVo
 * @Description : 커뮤니티Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class CmntyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 커뮤니티 아이디 */
    private int cmntyid;
    
    /** 커뮤니티 명 */
    private String cmntyNm;
    
    /** 커뮤니티 소개 */
    private String cmntyIntrcn;
    
    /** 개설자 아이디 */
    private int esterid;
    
    /** 개설자 계정 */
    private String acnt;
    
    /** 개설자 명 */
    private String nm;
    
    /** 가입 승인 방식 코드 */
    private String joinaprvmthdCd;
    
    /** 공개 여부 */
    private String rlsYn;
    
    /** 검색 노출 여부 */
    private String srchExpsrYn;
    
    /** 운영 여부 */
    private String operYn;
    
    /** 로고 파일 아이디 */
    private Integer cmntyLogoFileid;
    
    /** 회원수 */
    private int mbrCnt;
    
    /** 검색 운영 여부 */
    private String searchOperYn;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
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