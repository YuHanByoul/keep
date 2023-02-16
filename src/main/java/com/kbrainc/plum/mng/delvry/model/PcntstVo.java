package com.kbrainc.plum.mng.delvry.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 공모Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - PcntstVo.java
 * </pre> 
 *
 * @ClassName : PcntstVo
 * @Description : 공모Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class PcntstVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공모 아이디 */
    private int pcntstid;
    
    /** 차수 */
    private int cycl;
    
    /** 사업분야 코드 */
    private String fldCd;
    
    /** 사업분야 코드명 */
    private String fldCdNm;
    
    /** 공모명 */
    private String pcntstNm;
    
    /** 진행상태 */
    private String prgrsStts;
    
    /** 진행상태 순서 */
    private int prgrsSttsOrdr;
    
    /** 사업 선정 수 */
    private int slctnCnt;
    
    /** 사업비 교부 횟수 */
    private int wctDelvryCnt;
    
    /** 교부 신청 수 */
    private int aplyCnt;
    
    /** 교부 확정 수 */
    private int cfmtnCnt;
    
    /** 교부 신청 수 1차 */
    private int aplyCntFirst;
    
    /** 교부 신청 수 2차 */
    private int aplyCntScnd;
    
    /** 교부 확정 수 1차 */
    private int cfmtnCntFirst;
    
    /** 교부 신청 수 2차 */
    private int cfmtnCntScnd;
    
    /** 검색 사업분야 코드 */
    private String searchFldCd;
    
    /** 검색 공모명 */
    private String searchPcntstNm;
    
    /** 검색 신청 발표 시작일 */
    private String searchBgngDt;
    
    /** 검색 신청 발표 종료일 */
    private String searchEndDt;
    
    /** 검색 진행 상태 */
    private String searchPrgrsStts;
    
    /** 교부 신청발표 시작일시 */
    private String delvryAplyPrsntnBgngDtStr;
    
    /** 교부 신청발표 종료일시 */
    private String delvryAplyPrsntnEndDtStr;
    
    
    /** 교부 신청발표 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyPrsntnBgngDt;
    
    /** 교부 신청발표 시작일시 정보 */
    public Date getDelvryAplyPrsntnBgngDt() {
        return delvryAplyPrsntnBgngDt != null ? (Date) delvryAplyPrsntnBgngDt.clone() : null;
    }
    
    public void setDelvryAplyPrsntnBgngDt(Date delvryAplyPrsntnBgngDt) {
        this.delvryAplyPrsntnBgngDt = delvryAplyPrsntnBgngDt != null ? (Date) delvryAplyPrsntnBgngDt.clone() : null;
    }
    
    /** 교부 신청발표 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyPrsntnEndDt;
    
    /** 교부 신청발표 종료일시 정보 */
    public Date getDelvryAplyPrsntnEndDt() {
        return delvryAplyPrsntnEndDt != null ? (Date) delvryAplyPrsntnEndDt.clone() : null;
    }
    
    public void setDelvryAplyPrsntnEndDt(Date delvryAplyPrsntnEndDt) {
        this.delvryAplyPrsntnEndDt = delvryAplyPrsntnEndDt != null ? (Date) delvryAplyPrsntnEndDt.clone() : null;
    }
    
    /** 교부 신청 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyBgngDt;
    
    /** 교부 신청 시작일시 정보 */
    public Date getDelvryAplyBgngDt() {
        return delvryAplyBgngDt != null ? (Date) delvryAplyBgngDt.clone() : null;
    }
    
    public void setDelvryAplyBgngDt(Date delvryAplyBgngDt) {
        this.delvryAplyBgngDt = delvryAplyBgngDt != null ? (Date) delvryAplyBgngDt.clone() : null;
    }
    
    /** 교부 신청 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyEndDt;
    
    /** 교부 신청 종료일시 정보 */
    public Date getDelvryAplyEndDt() {
        return delvryAplyEndDt != null ? (Date) delvryAplyEndDt.clone() : null;
    }
    
    public void setDelvryAplyEndDt(Date delvryAplyEndDt) {
        this.delvryAplyEndDt = delvryAplyEndDt != null ? (Date) delvryAplyEndDt.clone() : null;
    }
    
    /** 교부 신청 시작일시 1차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyBgngDtFirst;
    
    /** 교부 신청 시작일시 1차 정보 */
    public Date getDelvryAplyBgngDtFirst() {
        return delvryAplyBgngDtFirst != null ? (Date) delvryAplyBgngDtFirst.clone() : null;
    }
    
    public void setDelvryAplyBgngDtFirst(Date delvryAplyBgngDtFirst) {
        this.delvryAplyBgngDtFirst = delvryAplyBgngDtFirst != null ? (Date) delvryAplyBgngDtFirst.clone() : null;
    }
    
    /** 교부 신청 종료일시 1차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyEndDtFirst;
    
    /** 교부 신청 종료일시 1차 정보 */
    public Date getDelvryAplyEndDtFirst() {
        return delvryAplyEndDtFirst != null ? (Date) delvryAplyEndDtFirst.clone() : null;
    }
    
    public void setDelvryAplyEndDtFirst(Date delvryAplyEndDtFirst) {
        this.delvryAplyEndDtFirst = delvryAplyEndDtFirst != null ? (Date) delvryAplyEndDtFirst.clone() : null;
    }
    
    /** 교부 신청 시작일시 2차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyBgngDtScnd;
    
    /** 교부 신청 시작일시 2차 정보 */
    public Date getDelvryAplyBgngDtScnd() {
        return delvryAplyBgngDtScnd != null ? (Date) delvryAplyBgngDtScnd.clone() : null;
    }
    
    public void setDelvryAplyBgngDtScnd(Date delvryAplyBgngDtScnd) {
        this.delvryAplyBgngDtScnd = delvryAplyBgngDtScnd != null ? (Date) delvryAplyBgngDtScnd.clone() : null;
    }
    
    /** 교부 신청 종료일시 2차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyEndDtScnd;
    
    /** 교부 신청 종료일시 2차 정보 */
    public Date getDelvryAplyEndDtScnd() {
        return delvryAplyEndDtScnd != null ? (Date) delvryAplyEndDtScnd.clone() : null;
    }
    
    public void setDelvryAplyEndDtScnd(Date delvryAplyEndDtScnd) {
        this.delvryAplyEndDtScnd = delvryAplyEndDtScnd != null ? (Date) delvryAplyEndDtScnd.clone() : null;
    }
    
    /** 교부 확정 발표 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryCfmtnPrsntnBgngDt;
    
    /** 교부 확정 발표 시작일시 정보 */
    public Date getDelvryCfmtnPrsntnBgngDt() {
        return delvryCfmtnPrsntnBgngDt != null ? (Date) delvryCfmtnPrsntnBgngDt.clone() : null;
    }
    
    public void setDelvryCfmtnPrsntnBgngDt(Date delvryCfmtnPrsntnBgngDt) {
        this.delvryCfmtnPrsntnBgngDt = delvryCfmtnPrsntnBgngDt != null ? (Date) delvryCfmtnPrsntnBgngDt.clone() : null;
    }
    
    /** 교부 확정 발표 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryCfmtnPrsntnEndDt;
    
    /** 교부 확정 발표 종료일시 정보 */
    public Date getDelvryCfmtnPrsntnEndDt() {
        return delvryCfmtnPrsntnEndDt != null ? (Date) delvryCfmtnPrsntnEndDt.clone() : null;
    }
    
    public void setDelvryCfmtnPrsntnEndDt(Date delvryCfmtnPrsntnEndDt) {
        this.delvryCfmtnPrsntnEndDt = delvryCfmtnPrsntnEndDt != null ? (Date) delvryCfmtnPrsntnEndDt.clone() : null;
    }
    
    /** 자금 집행 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutBgngDt;
    
    /** 자금 집행 시작일시 정보 */
    public Date getCptalExcutBgngDt() {
        return cptalExcutBgngDt != null ? (Date) cptalExcutBgngDt.clone() : null;
    }
    
    public void setCptalExcutBgngDt(Date cptalExcutBgngDt) {
        this.cptalExcutBgngDt = cptalExcutBgngDt != null ? (Date) cptalExcutBgngDt.clone() : null;
    }
    
    /** 자금 집행 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutEndDt;
    
    /** 자금 집행 종료일시 정보 */
    public Date getCptalExcutEndDt() {
        return cptalExcutEndDt != null ? (Date) cptalExcutEndDt.clone() : null;
    }
    
    public void setCptalExcutEndDt(Date cptalExcutEndDt) {
        this.cptalExcutEndDt = cptalExcutEndDt != null ? (Date) cptalExcutEndDt.clone() : null;
    }
    
    /** 자금 집행 시작일시 1차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutBgngDtFirst;
    
    /** 자금 집행 시작일시 1차 정보 */
    public Date getCptalExcutBgngDtFirst() {
        return cptalExcutBgngDtFirst != null ? (Date) cptalExcutBgngDtFirst.clone() : null;
    }
    
    public void setCptalExcutBgngDtFirst(Date cptalExcutBgngDtFirst) {
        this.cptalExcutBgngDtFirst = cptalExcutBgngDtFirst != null ? (Date) cptalExcutBgngDtFirst.clone() : null;
    }
    
    /** 자금 집행 종료일시 1차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutEndDtFirst;
    
    /** 자금 집행 종료일시 1차 정보 */
    public Date getCptalExcutEndDtFirst() {
        return cptalExcutEndDtFirst != null ? (Date) cptalExcutEndDtFirst.clone() : null;
    }
    
    public void setCptalExcutEndDtFirst(Date cptalExcutEndDtFirst) {
        this.cptalExcutEndDtFirst = cptalExcutEndDtFirst != null ? (Date) cptalExcutEndDtFirst.clone() : null;
    }
    
    /** 자금 집행 시작일시 2차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutBgngDtScnd;
    
    /** 자금 집행 시작일시 2차 정보 */
    public Date getCptalExcutBgngDtScnd() {
        return cptalExcutBgngDtScnd != null ? (Date) cptalExcutBgngDtScnd.clone() : null;
    }
    
    public void setCptalExcutBgngDtScnd(Date cptalExcutBgngDtScnd) {
        this.cptalExcutBgngDtScnd = cptalExcutBgngDtScnd != null ? (Date) cptalExcutBgngDtScnd.clone() : null;
    }
    
    /** 자금 집행종료일시 2차 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutEndDtScnd;
    
    /** 자금 집행 종료일시 2차 정보 */
    public Date getCptalExcutEndDtScnd() {
        return cptalExcutEndDtScnd != null ? (Date) cptalExcutEndDtScnd.clone() : null;
    }
    
    public void setCptalExcutEndDtScnd(Date cptalExcutEndDtScnd) {
        this.cptalExcutEndDtScnd = cptalExcutEndDtScnd != null ? (Date) cptalExcutEndDtScnd.clone() : null;
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