package com.kbrainc.plum.mng.cmnty.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 커뮤니티댓글Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.model
 * - CmntyCmntVo.java
 * </pre> 
 *
 * @ClassName : CmntyCmntVo
 * @Description : 커뮤니티댓글Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class CmntyCmntVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 댓글 아이디 */
    private int cmntid;
    
    /** 게시글 아이디 */
    private int pstid;
    
    /** 내용 */
    private String cn;
    
    /** 부모 댓글 아이디 */
    private int parntsCmntid;
    
    /** 댓글 그룹 */
    private int cmntGrp;
    
    /** 깊이 */
    private int dpth;
    
    /** 정렬 순서 */
    private int sortOrdr;
    
    /** 공개 여부 */
    private String rlsYn;
    
    /** 삭제 여부 */
    private String delYn;
    
    /** 삭제 댓글 아이디 목록 */
    private String[] deleteCmntids;
    
    /** 작성자 계정 */
    private String acnt;
    
    /** 작성자 명 */
    private String nm;
    
    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private int rgtrid;
    
    /** 수정 일자 정보 */
    public Date getMdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    /** 등록 일자 정보 */
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