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
 * 설문기관Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srvy.model
 * - SrvyInstVo.java
 * </pre> 
 *
 * @ClassName : SrvyInstVo
 * @Description : 설문기관Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 27.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class SrvyInstVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 기관 아이디 */
    private int instid;
    
    /** 기관 코드 */
    private String instCd;
    
    /** 기관명 */
    private String instNm;
    
    /** 지역명 */
    private String rgnNm;
    
    /** 기관 유형 */
    private String instType;
    
    /** 기관 전체 대상 여부 */
    private String instYn;
    
    /** 기관 존재 여부 */
    private String isExist;
    
    /** 대상기관 추가 아이디 목록 */
    private String[] insertInstids;
    
    /** 대상기관 삭제 아이디 목록 */
    private String[] deleteInstids;
    
    /** 검색 기관 유형 코드 */
    private String searchInstTypeCd;
    
    /** 검색 지역 코드 */
    private String searchRgnCd;
    
    /** 컨설팅 아이디 */
    private int cnsltngid;
    
    /** 컨설팅 상태 코드 */
    private String sttsCd;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    
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