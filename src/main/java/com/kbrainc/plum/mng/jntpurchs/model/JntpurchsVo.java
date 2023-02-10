package com.kbrainc.plum.mng.jntpurchs.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 공동구매모집Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.jntpurchs.model
 * - JntpurchsVo.java
 * </pre> 
 *
 * @ClassName : JntpurchsVo
 * @Description : 공동구매모집Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 01. 18.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JntpurchsVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 공동구매 아이디 */
    private int jntpurchsid;
    
    /** 공동구매 번호 */
    private String jntpurchsno;
    
    /** 공동구매 모집명 */
    @NotEmpty(message = "공동구매 모집명을 입력해주세요.")
    @Size(max = 40, message = "공동구매 모집명은 40자 이하여야 합니다.")
    private String jntpurchsNm;
    
    /** 모집상태 코드 */
    private String sttsCd;
    
    /** 총 판매 수량 */
    private Integer qntyWhol;
    
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
    
    /** 모집상태 코드명 */
    private String sttsCdNm;
    
    /** 등록자 계정 */
    private String acnt;
    
    /** 시작일시  */
    private String bgngDtStr;
    
    /** 종료일시  */
    private String endDtStr;
    
    /** 검색 모집상태 */
    private String searchSttsCd;
    
    /** 검색 등록처 */
    private String searchInstType;
    
    /** 검색 모집 시작일 */
    private String searchBgngDt;
    
    /** 검색 모집 종료일 */
    private String searchEndDt;
    
    private String isExistOrder;
    
    
    /** 교구목록 */
    private List<JntpurchsTchaidVo> goodsList;
    
    /** 수량별 가격설정 목록 */
    private List<JntpurchsAmtVo> amtList;
    
    
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