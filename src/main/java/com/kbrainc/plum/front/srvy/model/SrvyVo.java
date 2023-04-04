package com.kbrainc.plum.front.srvy.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 설문Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.model
 * - SrvyVo.java
 * </pre> 
 *
 * @ClassName : SrvyVo
 * @Description : 설문Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 2. 28.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.SrvyVo")
@Data
public class SrvyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 설문지 아이디 */
    private int qestnrid;
    
    /** 사이트 아이디 */
    private int siteid;
    
    /** 설문명 */
    private String srvyNm;
    
    /** 설문 분류 */
    private String srvyCtgry;
    
    /** 설문 상태명 */
    private String sttsNm;
    
    /** 설문기간 코드 */
    private String srvyPrdCd;
    
    /** 설문 제출일 String */
    private String sbmsnDtStr;
    
    /** 설문 제출일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date sbmsnDt;
    
    /** 제출일자 정보 */
    public Date getSbmsnDt() {
        return sbmsnDt != null ? (Date) sbmsnDt.clone() : null;
    }
    
    public void setSbmsnDt(Date sbmsnDt) {
        this.sbmsnDt = sbmsnDt != null ? (Date) sbmsnDt.clone() : null;
    }
    
    
    /** 시작 일자 */
    @NotEmpty(message = "설문 시작일을 입력해주세요.")
    private String bgngDe;
    
    /** 종료 일자 */
    @NotEmpty(message = "설문 종료일을 입력해주세요.")
    private String endDe;
    
    /** 사용여부 */
    private String useYn;
    
    
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