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
 * 교부신청 보완요청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryAplySplmntVo.java
 * </pre> 
 *
 * @ClassName : DelvryAplySplmntVo
 * @Description : 교부신청 보완요청Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 16.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.DelvryAplySplmntVo")
public class DelvryAplySplmntVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 보완요청 아이디 */
    private int splmntid;
    
    /** 신청 아이디 */
    private int delvryAplyid;
    
    /** 요청내용 */
    private String dmndCn;
    
    /** 답변내용 */
    private String ansCn;
    
    /** 답변상태 코드 */
    private String ansSttsCd;
    
    /** 답변상태 코드명 */
    private String ansSttsCdNm;
    
    /** 요청일 */
    private String regDtStr;
    
    /** 답변일 */
    private String ansDtStr;
    
    /** 답변일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date ansDt;
    
    /** 답변일자 정보 */
    public Date getAnsDt() {
        return ansDt != null ? (Date) ansDt.clone() : null;
    }
    
    public void setAnsDt(Date ansDt) {
        this.ansDt = ansDt != null ? (Date) ansDt.clone() : null;
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