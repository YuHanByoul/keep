package com.kbrainc.plum.front.srvy.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 설문제출 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.srvy.model
 * - SrvySbmsnVo.java
 * </pre> 
 *
 * @ClassName : SrvySbmsnVo
 * @Description : 설문제출 Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 3. 02.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.SrvySbmsnVo")
@Data
public class SrvySbmsnVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문 제출 아이디 */
    private int sbmsnid;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 설문지 아이디 */
    private int qestnrid;
    
    /** 설문명 */
    private String srvyNm;
    
    /** 설문 상태명 */
    private String sttsNm;
    
    /** 설문기간 코드 */
    private String srvyPrdCd;
    
    /** 사용자 아이피 */
    private String userIp;
    
    /** 사용자 브라우저명 */
    private String brwsrNm;
    
    /** 디바이스 종류 */
    private String deviceKndCd;

    /** 유아_프로그램_신청아이디 */
    private int infntPrgrmAplyid;
    
    /** 이동_프로그램_신청아이디 */
    private int mvmnPrgrmAplyid;
    
    /** 참가자_이름 */
    private String prtpntNm;
    
//    List<SrvySbmsnAnsVo> ansList;
    
    
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