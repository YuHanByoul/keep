package com.kbrainc.plum.mng.qestnr.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;

import lombok.Data;

/**
 * 
 * 설문지문항Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.qestnr.model
 * - QitemVo.java
 * </pre> 
 *
 * @ClassName : QitemVo
 * @Description : 설문지문항Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 11. 29.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class QitemVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 문항 아이디 */
    private int qitemid;
    
    /** 설문지 아이디 */
    private int qestnrid;
    
    /** 문항 유형 코드 */
    private String qitemTypeCd;
    
    /** 문항 유형 코드명 */
    private String qitemTypeCdNm;
    
    /** 내용 */
    private String cn;
    
    /** 보기 개수 */
    private int exCnt;
    
    /** 척도 */
    private int scale;
    
    /** 순서 */
    private int ordr;
    
    /** 사용 여부 */
    private String useYn;
    
    /** 설문 아이디 */
    private int srvyid;
    
    /** 순서 변경 방향 */
    private String changeDir;
    
    /** 삭제 문항 아이디 목록 */
    private String[] deleteQitemids;
    
    /** 보기 목록 */
    private List<QitemExVo> exampleList;
    
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