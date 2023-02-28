package com.kbrainc.plum.front.jntpurchs.model;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 환경교육 교구 공동구매 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.model
 * - JntpurchsVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsVo
 * @Description : 환경교육 교구 공동구매 Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 17.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.JntpurchsVo")
public class JntpurchsVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매 아이디 */
    private int jntpurchsid;
    
    /** 공동구매 번호 */
    private String jntpurchsno;
    
    /** 모집상태 코드명 */
    private String sttsCdNm;
    
    /** 공동구매 모집명 */
    private String jntpurchsNm;
    
    /** 총 판매 수량 */
    private Integer qntyWhol;
    
    /** 총 판매 수량 제한여부 */
    private String qntyWholLmtYn;
    
    /** 총 주문 수량 */
    private Integer orderQnty;
    
    /** 1인당 신청 가능 수량 */
    private Integer qntyLmt;
    
    /** 1인당 신청 가능 제한여부 */
    private String qntyLmtYn;
    
    /** 상세내용 */
    private String dtlCn;
    
    /** 동영상 URL */
    private String mvpUrl;
    
    /** 동영상 위치 코드 */
    private String mvpPstnCd;
    
    /** 대표이미지 파일 아이디 */
    private Integer rprsImgFileid;
    
    /** 대표이미지 파일 식별키 */
    private String rprsImgFilekey;
    
    /** 상세이미지 파일 그룹 아이디 */
    private Integer dtlImgFilegrpid;
    
    /** 지도 파일 그룹 아이디 */
    private Integer mapFilegrpid;
    
    /** 교육사진 파일 그룹 아이디 */
    private Integer eduPhotoFilegrpid;
    
    /** 유의사항 */
    private String atentMttr;
    
    /** 교구명 */
    private String tchaidNm;
    
    /** 등록처 */
    private String instNm;
    
    /** 등록자 계정 */
    private String acnt;
    
    /** 시작일시  */
    private String bgngDtStr;
    
    /** 종료일시  */
    private String endDtStr;
    
    /** 검색 모집상태 */
    private String searchSttsCd;
    
    /** 검색 등록처 */
    private String searchJntpurchsNm;
    
    /** 교구목록 */
    //private List<JntpurchsTchaidVo> goodsList;
    
    /** 수량별 가격설정 목록 */
    //private List<JntpurchsAmtVo> amtList;
    
    
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