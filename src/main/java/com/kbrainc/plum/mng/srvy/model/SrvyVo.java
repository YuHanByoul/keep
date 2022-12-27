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
 * 설문Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srvy.model
 * - SrvyVo.java
 * </pre> 
 *
 * @ClassName : SrvyVo
 * @Description : 설문Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class SrvyVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 사이트 아이디 */
    private int siteid;
    
    /** 설문지 아이디 */
    private int qestnrid;
    
    /** 설문지명 */
    private String qestnrNm;
    
    /** 문항수 */
    private int qitemCnt;
    
    /** 대상자수 */
    private int trprCnt;
    
    
    /** 설문명 */
    @NotEmpty(message = "설문명을 입력해주세요.")
    @Size(max = 20, message = "설문명은 20자 이하여야 합니다.")
    private String srvyNm;
    
    /** 설명 */
    @NotEmpty(message = "설문 설명을 입력해주세요.")
    @Size(max = 400, message = "설문 설명은 400자 이하여야 합니다.")
    private String expln;
    
    /** 설문기간 코드 */
    private String srvyPrdCd;
    
    /** 시작 일자 */
    @NotEmpty(message = "설문 시작일을 입력해주세요.")
    private String bgngDe;
    
    /** 종료 일자 */
    @NotEmpty(message = "설문 종료일을 입력해주세요.")
    private String endDe;
    
    /** 이후 일수 */
    private int aftrDaycnt;
    
    /** 이전 일수 */
    private int bfrDaycnt;
    
    /** 사용여부 */
    private String useYn;
    
    /** 설문지 종류 코드 */
    private String qestnrKndCd;
    
    
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