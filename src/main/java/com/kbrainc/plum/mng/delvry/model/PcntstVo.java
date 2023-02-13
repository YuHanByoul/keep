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