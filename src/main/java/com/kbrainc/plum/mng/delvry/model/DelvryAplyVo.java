package com.kbrainc.plum.mng.delvry.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 교부신청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryAplyVo.java
 * </pre> 
 *
 * @ClassName : DelvryAplyVo
 * @Description : 교부신청Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 13.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class DelvryAplyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 신청 아이디 */
    private int aplyid;
    
    /** 교부 신청 아이디 */
    private int delvryAplyid;
    
    /** 공모 아이디 */
    private int pcntstid;
    
    /** 차수 */
    private int cycl;
    
    /** 접수번호 */
    private String rcptno;
    
    /** 교부상태 코드 */
    private String delvrySttsCd;
    
    /** 교부상태 코드명 */
    private String delvrySttsCdNm;
    
    /** 기관명 */
    private String instNm;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청자 아이디 */
    private String acnt;
    
    /** 사업분야 코드 */
    private String fldCd;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 동아리명 */
    private String clubNm;
    
    /** 신청금액 */
    private String totAmt;
    
    /** 은행코드 */
    private String bankCd;
    
    /** 은행코드명 */
    private String bankCdNm;
    
    /** 계좌번호 */
    private String bacntno;
    
    /** 예금주명 */
    private String dpstrNm;
    
    /** 검색 교부상태 코드 */
    private String searchDelvrySttsCd;
    
    /** 검색 교부신청일 시작일 */
    private String searchRegBgngDt;
    
    /** 검색 교부신청일 종료일 */
    private String searchRegEndDt;
    
    /** 업데이트 교부신청 아이디 목록 */
    private String[] updateDelvryAplyids;
    
    /** 교부 신청일 */
    private String regDtStr;
    
    /** 첨부파일 아이디 */
    private Integer atchFilegrpid;
    
    /** 산출내역 목록 */
    private List<DelvryAplyComputVo> computList;
    
    /** 첨부파일 그룹아이디 목록 */
    private String[] atchFilegrpids;
    
    /** 사업 시작 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bsnsBgngDe;
    
    /** 사업 시작 일시 정보 */
    public Date getBsnsBgngDe() {
        return bsnsBgngDe != null ? (Date) bsnsBgngDe.clone() : null;
    }
    
    public void setBsnsBgngDe(Date bsnsBgngDe) {
        this.bsnsBgngDe = bsnsBgngDe != null ? (Date) bsnsBgngDe.clone() : null;
    }
    
    /** 사업 종료 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bsnsEndDe;
    
    /** 사업 종료 일시 정보 */
    public Date getBsnsEndDe() {
        return bsnsEndDe != null ? (Date) bsnsEndDe.clone() : null;
    }
    
    public void setBsnsEndDe(Date bsnsEndDe) {
        this.bsnsEndDe = bsnsEndDe != null ? (Date) bsnsEndDe.clone() : null;
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