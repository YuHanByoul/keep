package com.kbrainc.plum.front.bbs.model;/** **/

import com.kbrainc.plum.rte.model.SiteInfoVo;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

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
@Alias("front.BbsVo")
public class BbsVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 사이트 정보 */
    private SiteInfoVo site;
    
    /** 게시판 아이디 **/
    private Integer bbsid;
    /** 이름 **/
    private String nm;
    /** 설명 **/
    private String dc;
    /** 분류_코드 **/
    private String clCd;
    private String clNm;
    
    
    /** 고정_공지_사용_여부 **/
    private String fxdNtcUseYn;
    /** 고정_공지_갯수 **/
    private Integer fxdNtcCnt;
    /** 첨부파일_사용_여부 **/
    private String atchfileUseYn;

    /** 첨부파일_갯수 **/
    private Integer atchfileCnt;
    /** 첨부파일_사이즈 **/
    private Integer atchfileSize;
    
    
    /** 답글_사용_여부 **/
    private String rplyUseYn;
    /** 댓글 사용여부 **/
    private String cmntUseYn;
    /** NEW_사용_여부 **/
    private String newUseYn;
    /** NEW_표시일수 **/
    private Integer newIndictDaycnt;
    /** HOT_사용_여부 **/
    private String hotUseYn;
    /** HOT_사용_기준_조회수 **/
    private Integer hotUseStdrHits;
    /** 분류_사용_여부 **/
    private String clUseYn;
    /** 페이지_게시글_수 **/
    private Integer pagePstCnt;
    /** 비로그인_파일다운로드_여부 **/
    private String nloginDwnldPermYn;
    /** 사용_여부 **/
    private String useYn;
    /** 수정_일시 **/
    private String mdfcnDt;
    /** 수정자_아이디 **/
    private Integer mdfrid;
    /** 등록_일시 **/
    private String regDt;
    /** 등록_아이디 **/
    private Integer rgtrid;

    private Integer pstCnt; // 추가 :게시글 수
    private Integer searchCl; // 유형 검색 값
    
    private Integer bbsClid; // 유형 검색 값
    
    //현재 고정 게시글 수 (insert or update시 제한 두기 위함 ) 
    private Integer curFxdNtcCnt;

    private Integer tabType;

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