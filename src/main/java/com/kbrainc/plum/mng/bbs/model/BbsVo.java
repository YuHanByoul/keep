package com.kbrainc.plum.mng.bbs.model;/** **/

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;

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
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class BbsVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** **/

    private Integer bbsid;
    /** 게시판 아이디 **/
    private String nm;
    /** 이름 **/
    private String dc;
    /** 설명 **/
    private String cl_cd;
    private String cl_nm;
    
    
    /** 분류_코드 **/
    private String fxd_ntc_use_yn;
    /** 고정_공지_사용_여부 **/
    private Integer fxd_ntc_cnt;
    /** 고정_공지_갯수 **/
    private String atchfile_use_yn;
    /** 첨부파일_사용_여부 **/
    private Integer atchfile_cnt;
    /** 첨부파일_갯수 **/
    private Integer atchfile_size;
    /** 첨부파일_사이즈 **/
    
    
    private String rply_use_yn;
    /** 답글_사용_여부 **/
    private String cmnt_use_yn;
    /** 댓글 사용여부 **/
    private String new_use_yn;
    /** NEW_사용_여부 **/
    private Integer new_indict_daycnt;
    /** NEW_표시일수 **/
    private String hot_use_yn;
    /** HOT_사용_여부 **/
    private Integer hot_use_stdr_hits;
    /** HOT_사용_기준_조회수 **/
    private String cl_use_yn;
    /** 분류_사용_여부 **/
    private Integer page_pst_cnt;
    /** 페이지_게시글_수 **/
    private String nlogin_perm_yn;
    /** 비로그인_허용_여부 **/
    private String use_yn;
    /** 사용_여부 **/
    private String updt_dt;
    /** 수정_일시 **/
    private Integer updtuserid;
    /** 수정자_아이디 **/
    private String reg_dt;
    /** 등록_일시 **/
    private Integer reguserid;
    /** 등록_아이디 **/

    private Integer pst_cnt; // 추가 :게시글 수
    private Integer search_cl; // 유형 검색 값
    
    //현재 고정 게시글 수 (insert or update시 제한 두기 위함 ) 
    private Integer cur_fxd_ntc_cnt;
    
    public BbsVo(Integer bbsid) {
        this.setBbsid(bbsid);
        this.setSearchKeyword("All");
    }
    
    public void setCl_cd(String clCd) {
        this.cl_cd = clCd;
        if("1".equals(clCd)) {
            this.cl_nm = "일반형";
        }else if("2".equals(clCd)) {
            this.cl_nm = "공지형";
        }
    }

}