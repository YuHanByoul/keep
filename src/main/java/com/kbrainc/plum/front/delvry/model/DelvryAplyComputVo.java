package com.kbrainc.plum.front.delvry.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 교부신청 산출내역Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryAplyComputVo.java
 * </pre> 
 *
 * @ClassName : DelvryAplyComputVo
 * @Description : 교부신청 산출내역Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 15.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.DelvryAplyComputVo")
public class DelvryAplyComputVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 산출내역 아이디 */
    private int computid;
    
    /** 신청 아이디 */
    private int delvryAplyid;
    
    /** 산출내역 항목 코드 */
    private String expitmCd;
    
    /** 산출내역 상위항목 코드명 */
    private String expitmUpprNm;
    
    /** 산출내역 항목 코드명 */
    private String expitmNm;
    
    /** 산출내역 항목 코드 */
    private String code;
    
    /** 금액 */
    private int amt;
    
    /** 세부내역 */
    private String cn;
    
    /** 순서 */
    private int ordr;

    /** 그룹별 순서 */
    private int groupNum;
    
    /** 로우스팬값 */
    private int rowspan;
    
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