package com.kbrainc.plum.mng.bbs.model;/** **/

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * BBS VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.model
 * - BbsVo.java
 * </pre> 
 *
 * @ClassName : BbsVo
 * @Description : BBS VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class BbsVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 게시판 아이디 **/
    private Integer bbsid;
    
    /** 이름 **/
    @Size(max = 60, message = "아이디는 60자를 넘을 수 없습니다.")
    private String nm;
    
    /** 설명 **/
    @Size(max = 400, message = "아이디는 400자를 넘을 수 없습니다.")
    private String dc;
    
    /** 분류_코드 **/
    @Size(max = 20, message = "아이디는 20자를 넘을 수 없습니다.")
    private String clCd;
    
    /** 분류_코드 명**/
    private String clNm;
    
    /** 고정_공지_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String fxdNtcUseYn;
    
    /** 고정_공지_갯수 **/
    private Integer fxdNtcCnt;
    
    /** 첨부파일_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String atchfileUseYn;
    
    /** 첨부파일_갯수 **/
    private Integer atchfileCnt;
    
    /** 첨부파일_사이즈 **/
    private Integer atchfileSize;
    
    /** 답글_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String rplyUseYn;
    
    /** 댓글 사용여부 **/
    @Pattern(regexp="[YN]")
    private String cmntUseYn;
    
    /** NEW_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String newUseYn;
    
    /** NEW_표시일수 **/
    private Integer newIndictDaycnt;
    
    /** HOT_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String hotUseYn;
    
    /** HOT_사용_기준_조회수 **/
    private Integer hotUseStdrHits;
    
    /** 분류_사용_여부 **/
    @Pattern(regexp="[YN]")
    private String clUseYn;
    
    /** 페이지_게시글_수 **/
    private Integer pagePstCnt;
    
    /** 비로그인_파일다운로드_여부 **/
    @Pattern(regexp="[YN]")
    private String nloginDwnldPermYn;
    
    /** 사용_여부 **/
    @Pattern(regexp="[YN]")
    private String useYn;
    
    /** 수정_일시 **/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자_아이디 **/
    private Integer mdfrid;
    
    /** 등록_일시 **/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록_아이디 **/
    private Integer rgtrid;
    
    /** 답글_여부 **/
    @Pattern(regexp="[YN]")
    private String rplyYn;

    private Integer pstCnt; // 추가 :게시글 수
    
    private Integer searchCl; // 유형 검색 값
    
    //현재 고정 게시글 수 (insert or update시 제한 두기 위함 ) 
    private Integer curFxdNtcCnt;
    
    public BbsVo(Integer bbsid) {
        this.setBbsid(bbsid);
        this.setSearchKeyword("All");
    }
    
    public void setClCd(String clCd) {
        this.clCd = clCd;
        if("1".equals(clCd)) {
            this.clNm = "일반형";
        }else if("2".equals(clCd)) {
            this.clNm = "공지형";
        }
    }
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   


}