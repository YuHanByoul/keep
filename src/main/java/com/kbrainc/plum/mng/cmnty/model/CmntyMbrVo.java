package com.kbrainc.plum.mng.cmnty.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 * 
 * 커뮤니티회원Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.model
 * - CmntyMbrVo.java
 * </pre> 
 *
 * @ClassName : CmntyMbrVo
 * @Description : 커뮤니티회원Vo 클래스 
 * @author : KBRAINC
 * @date : 2022. 12. 15.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class CmntyMbrVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 커뮤니티 아이디 */
    private int cmntyid;
    
    /** 회원 아이디 */
    private int userid;
    
    /** 회원 계정 */
    private String acnt;
    
    /** 회원명 */
    private String nm;
    
    /** 회원 상태 코드 */
    private String mbrSttsCd;
    
    /** 회원 상태 코드명 */
    private String mbrSttsCdNm;
    
    /** 권한 코드 */
    private String authrtCd;
    
    /** 권한 코드명 */
    private String authrtCdNm;
    
    public void setMbrSttsCd(String mbrSttsCd) {
        this.mbrSttsCd = mbrSttsCd;
        if(CommonUtil.isEmpty(this.mbrSttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.mbrSttsCd);
                this.mbrSttsCdNm = code.getCdNm();
            } catch(NoClassDefFoundError e) {
                return;
             } catch(Exception e) {
                return;
             }
        }
    }
    
    public void setAuthrtCd(String authrtCd) {
        this.authrtCd = authrtCd;
        if(CommonUtil.isEmpty(this.authrtCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.authrtCd);
                this.authrtCdNm = code.getCdNm();
            } catch(NoClassDefFoundError e) {
                return;
             } catch(Exception e) {
                return;
             }
        }
    }
    
    /** 가입신청 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date joinAplyDt;
    
    /** 가입승인 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date joinAprvDt;
    
    /** 승인자 아이디 */
    private String autzrId;
    
    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    /** 가입신청 일자 정보 */
    public Date getJoinAplyDt() {
        return joinAplyDt != null ? (Date) joinAplyDt.clone() : null;
    }
    
    public void setJoinAplyDt(Date joinAplyDt) {
        this.joinAplyDt = joinAplyDt != null ? (Date) joinAplyDt.clone() : null;
    }
    
    /** 가입승인 일자 정보 */
    public Date getJoinAprvDt() {
        return joinAprvDt != null ? (Date) joinAprvDt.clone() : null;
    }
    
    public void setJoinAprvDt(Date joinAprvDt) {
        this.joinAprvDt = joinAprvDt != null ? (Date) joinAprvDt.clone() : null;
    }
    
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