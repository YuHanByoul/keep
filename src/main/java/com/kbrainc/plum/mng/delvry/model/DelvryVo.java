package com.kbrainc.plum.mng.delvry.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 교부Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryVo.java
 * </pre> 
 *
 * @ClassName : DelvryVo
 * @Description : 교부Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class DelvryVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 교부 아이디 */
    private int delvryid;
    
    /** 차수 */
    private int cycl;
    
    /** 사업분야 코드 */
    private String fldCd;
    
    /** 사업분야 코드명 */
    private String fldCdNm;
    
    /** 공모 아이디 */
    private int pcntstid;
    
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
    
    /** 확정발표 시작일시 */
    private String delvryCfmtnPrsntnBgngDtStr;
    
    /** 확정발표 종료일시 */
    private String delvryCfmtnPrsntnEndDtStr;
    
    /** 신청 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyBgngDt;
    
    /** 신청 시작일시 정보 */
    public Date getDelvryAplyBgngDt() {
        return delvryAplyBgngDt != null ? (Date) delvryAplyBgngDt.clone() : null;
    }
    
    public void setDelvryAplyBgngDt(Date delvryAplyBgngDt) {
        this.delvryAplyBgngDt = delvryAplyBgngDt != null ? (Date) delvryAplyBgngDt.clone() : null;
    }
    
    /** 신청 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryAplyEndDt;
    
    /** 신청 종료일시 정보 */
    public Date getDelvryAplyEndDt() {
        return delvryAplyEndDt != null ? (Date) delvryAplyEndDt.clone() : null;
    }
    
    public void setDelvryAplyEndDt(Date delvryAplyEndDt) {
        this.delvryAplyEndDt = delvryAplyEndDt != null ? (Date) delvryAplyEndDt.clone() : null;
    }
    
    /** 확정발표 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryCfmtnPrsntnBgngDt;
    
    /** 확정발표 시작일시 정보 */
    public Date getDelvryCfmtnPrsntnBgngDt() {
        return delvryCfmtnPrsntnBgngDt != null ? (Date) delvryCfmtnPrsntnBgngDt.clone() : null;
    }
    
    public void setDelvryCfmtnPrsntnBgngDt(Date delvryCfmtnPrsntnBgngDt) {
        this.delvryCfmtnPrsntnBgngDt = delvryCfmtnPrsntnBgngDt != null ? (Date) delvryCfmtnPrsntnBgngDt.clone() : null;
    }
    
    /** 확정발표 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date delvryCfmtnPrsntnEndDt;
    
    /** 확정발표 종료일시 정보 */
    public Date getDelvryCfmtnPrsntnEndDt() {
        return delvryCfmtnPrsntnEndDt != null ? (Date) delvryCfmtnPrsntnEndDt.clone() : null;
    }
    
    public void setDelvryCfmtnPrsntnEndDt(Date delvryCfmtnPrsntnEndDt) {
        this.delvryCfmtnPrsntnEndDt = delvryCfmtnPrsntnEndDt != null ? (Date) delvryCfmtnPrsntnEndDt.clone() : null;
    }
    
    /** 자금집행 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutBgngDt;
    
    /** 자금집행 시작일시 정보 */
    public Date getCptalExcutBgngDt() {
        return cptalExcutBgngDt != null ? (Date) cptalExcutBgngDt.clone() : null;
    }
    
    public void setCptalExcutBgngDt(Date cptalExcutBgngDt) {
        this.cptalExcutBgngDt = cptalExcutBgngDt != null ? (Date) cptalExcutBgngDt.clone() : null;
    }
    
    /** 자금집행 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date cptalExcutEndDt;
    
    /** 자금집행 종료일시 정보 */
    public Date getCptalExcutEndDt() {
        return cptalExcutEndDt != null ? (Date) cptalExcutEndDt.clone() : null;
    }
    
    public void setCptalExcutEndDt(Date cptalExcutEndDt) {
        this.cptalExcutEndDt = cptalExcutEndDt != null ? (Date) cptalExcutEndDt.clone() : null;
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