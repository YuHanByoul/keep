package com.kbrainc.plum.mng.jntpurchs.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 공동구매신청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.jntpurchs.model
 * - JntpurchsOrderVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsOrderVo
 * @Description : 공동구매신청Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 30.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JntpurchsOrderVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매신청 아이디 */
    private int orderid;
    
    /** 공동구매모집 아이디 */
    private int jntpurchsid;
    
    /** 공동구매모집 번호 */
    private String jntpurchsno;
    
    /** 공동구매모집 이름 */
    private String jntpurchsNm;
    
    /** 공동구매모집 상태 코드 */
    private String JntpurchsSttsCd;
    
    /** 공동구매모집 상태 코드명 */
    private String JntpurchsSttsCdNm;
    
    /** 신청자 계정 */
    private String acnt;
    
    /** 신청자 회원유형 */
    private String userType;
    
    /** 신청자 계정 */
    private String nm;
    
    /** 신청자 기관 아이디 */
    private Integer instid;
    
    /** 신청자 기관명 */
    private String instNm;
    
    /** 신청자 기관 유형 코드명 */
    private String instTypeCdNm;
    
    /** 신청자 연락처 */
    private String telno;
    
    /** 신청자 이메일 */
    private String eml;
    
    /** 수령인 */
    private String recptr;
    
    /** 수령인 연락처 */
    private String recptrTelno;
    
    /** 수령인 이메일 */
    private String recptrEml;
    
    /** 배송지 우편번호 */
    private String dlvyZip;
    
    /** 배송지 주소 */
    private String dlvyAddr;
    
    /** 배송지 상세주소 */
    private String dlvyAddrDtl;
    
    /** 접수번호 */
    private String orderno;
    
    /** 교구명 */
    private String tchaidNm;
    
    /** 신청수량 */
    private int qnty;
    
    /** 구매가격 */
    private int amt;
    
    /** 신청상태 코드 */
    private String sttsCd;
    
    /** 신청상태 코드명 */
    private String sttsCdNm;
    
    /** 후기 점수 */
    private Integer rvwScr;
    
    /** 후기 내용 */
    private String rvwCn;
    
    /** 후기 파일 그룹아이디 */
    private Integer rvwFilegrpid;
    
    /** 신청취소 가능 여부 */
    private String cancelYn;
    
    /** 신청일시 */
    private String regDtStr;
    
    /** 검색 신청정보 유형 */
    private String searchOrderType;
    
    /** 검색 신청정보 키워드 */
    private String searchOrderKeyword;
    
    /** 검색 신청상태 */
    private String searchSttsCd;
    
    /** 검색 신청일 */
    private String searchRegDt;
    
    /** 검색 공동구매 모집명 */
    private String searchJntpurchsNm;
    
    /** 신청상품 목록 */
    private List<JntpurchsTchaidVo> goodsList;
    
    
    /** 시작일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bgngDt;
    
    /** 시작일시 정보 */
    public Date getBgngDt() {
        return bgngDt != null ? (Date) bgngDt.clone() : null;
    }
    
    public void setBgngDt(Date bgngDt) {
        this.bgngDt = bgngDt != null ? (Date) bgngDt.clone() : null;
    }
    
    /** 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date endDt;
    
    /** 종료일시 정보 */
    public Date getEndDt() {
        return endDt != null ? (Date) endDt.clone() : null;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt != null ? (Date) endDt.clone() : null;
    }
    
    /** 후기 작성 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date rvwRegDt;
    
    /** 후기 작성 일시 정보 */
    public Date getRvwRegDt() {
        return rvwRegDt != null ? (Date) rvwRegDt.clone() : null;
    }
    
    public void setRvwRegDt(Date rvwRegDt) {
        this.rvwRegDt = rvwRegDt != null ? (Date) rvwRegDt.clone() : null;
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