package com.kbrainc.plum.mng.chklst.model;

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
 * 체크리스트문항Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.model
 * - ChklstQitemVo.java
 * </pre> 
 *
 * @ClassName : ChklstQitemVo
 * @Description : 체크리스트문항Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ChklstQitemVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 문항 아이디 */
    private int qitemid;
    
    /** 구분 코드 */
    private String seCd;
    
    /** 구분 코드명 */
    private String cdNm;
    
    /** 상위 구분 코드명 */
    private String upprCdNm;
    
    /** 내용 */
    @NotEmpty(message = "내용을 입력해주세요.")
    @Size(max = 200, message = "내용은 200자 이하여야 합니다.")
    private String cn;
    
    /** 확인 사항 */
    private String idntyMttr;
    
    /** 배점 */
    private int altm;
    
    /** 사용 여부 */
    private String useYn;
    
    /** 검색 문항 구분코드 */
    private String searchSeCd;
    
    
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