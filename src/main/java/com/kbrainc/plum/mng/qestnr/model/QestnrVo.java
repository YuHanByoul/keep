package com.kbrainc.plum.mng.qestnr.model;

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
 * 설문지Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.model
 * - QestnrVo.java
 * </pre> 
 *
 * @ClassName : QestnrVo
 * @Description : 설문지Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 11. 29.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class QestnrVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문지 아이디 */
    private int qestnrid;
    
    /** 사이트 아이디 */
    @NotEmpty(message = "사이트(기관을) 선택해주세요.")
    private int siteid;
    
    /** 설문지 종류 코드 */
    @NotEmpty(message = "설문지 종류를 선택해주세요.")
    private String qestnrKndCd;
    
    /** 설문지 이름 */
    @NotEmpty(message = "설문지명을 입력해주세요.")
    @Size(max = 20, message = "설문지명은 20자 이하여야 합니다.")
    private String qestnrNm;
    
    /** 설명 */
    @NotEmpty(message = "설문지 설명을 입력해주세요.")
    @Size(max = 400, message = "설문지명은 20자 이하여야 합니다.")
    private String expln;
    
    /** 사용 여부 */
    private String useYn;
    
    /** 사이트아이디 */
    private String searchSiteid;
    
    /** 설문종류 */
    private String searchQestnrKndCd;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 문항수 */
    private int qitemCnt;
    
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