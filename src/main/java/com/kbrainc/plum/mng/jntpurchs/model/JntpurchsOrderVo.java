package com.kbrainc.plum.mng.jntpurchs.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 공동구매신청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaidJntpurchs.model
 * - JntpurchsOrderVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsOrderVo
 * @Description : 공동구매신청Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 30.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JntpurchsOrderVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매 아이디 */
    private int jntpurchsid;
    
    /** 공동구매 번호 */
    private String jntpurchsno;
    
    /** 공동구매 모집명 */
    private String jntpurchsNm;
    
    /** 신청자 계정 */
    private String acnt;
    
    /** 신청자 계정 */
    private String nm;
    
    /** 신청자 기관명 */
    private String instNm;
    
    /** 접수번호 */
    private String orderno;
    
    /** 교구명 */
    private String tchaidNm;
    
    /** 신청수량 */
    private int qnty;
    
    /** 신청상태 코드 */
    private String sttsCd;
    
    /** 신청상태 코드명 */
    private String sttsCdNm;
    
    /** 모집상태 코드 */
    private String JntpurchsSttsCd;
    
    /** 모집상태 코드명 */
    private String JntpurchsSttsCdNm;
    
    /** 신청일시 */
    private String regDtStr;
    
    /** 검색 신청정보 유형 */
    private String searchOrderType;
    
    /** 검색 신청정보 키워드 */
    private String searchOrderKeyword;
    
    /** 검색 신청상태 */
    private String searchSttsCd;
    
    /** 검색 신청일 */
    private String searchRegDt;
    
    
    /** 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bgngDt;
    
    /** 시작일시 정보 */
    public Date getBgngDt() {
        return bgngDt != null ? (Date) bgngDt.clone() : null;
    }
    
    public void setBgngDt(Date bgngDt) {
        this.bgngDt = bgngDt != null ? (Date) bgngDt.clone() : null;
    }
    
    /** 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date endDt;
    
    /** 종료일시 정보 */
    public Date getEndDt() {
        return endDt != null ? (Date) endDt.clone() : null;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt != null ? (Date) endDt.clone() : null;
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