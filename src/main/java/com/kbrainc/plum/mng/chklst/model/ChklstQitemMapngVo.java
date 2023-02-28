package com.kbrainc.plum.mng.chklst.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Date;

/**
 * 
 * 체크리스트문항구성Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.model
 * - ChklstQitemMapngVo.java
 * </pre> 
 *
 * @ClassName : ChklstQitemMapngVo
 * @Description : 체크리스트문항구성Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 10.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ChklstQitemMapngVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 체크리스트 아이디 */
    private Integer chklstid;
    
    /** 문항 아이디 */
    private Integer qitemid;
    
    /** 상위 구분 코드 */
    private String pseCd;
    
    /** 구분 코드 */
    private String seCd;
    
    /** 구분 코드명 */
    private String cn;
    
    /** 트리 뎁스 */
    private Integer treeOrd;
    
    /** 배점 */
    private Integer altm;

    /** 구분 코드 배열 */
    private String[] seCds;

    /** 문항 아이디 배열 */
    private Integer[] qitemids;

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