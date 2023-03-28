package com.kbrainc.plum.front.bbs.model;/** **/

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 프로그램 도메인 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.model
 * - PstVo.java
 * </pre> 
 *
 * @ClassName : PstVo
 * @Description : 프로그램 도메인 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.PstVo")
public class PstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 사이트 정보 */
    private SiteInfoVo site;
    
    /** 게시글 아이디 **/
    private Integer pstid;

    private Integer siteid;

    private Integer fileCount;

    /** **/
    private Integer bbsid;
    /** **/
    private String title;
    /** **/
    private String cntnts;
    /** 부모게시글 아이디 **/
    private Integer prntsPstid;
    /** **/
    private String grp;
    /** **/
    private Integer dpth;
    /** **/
    private Integer ord;
    /** **/
    private Integer hits;
    /** **/
    private Integer bbsClid;
    /** **/
    private String clNm;
    /** **/
    private String fxdNtcYn;
    /** **/
    private String fxdNtcStrtDt;
    /** **/
    private String fxdNtcEndDt;
    /** **/
    private String loginYn;
    /** **/
    private Integer filegrpid;
    
    /** 수정_일시 **/
    private String mdfcnDt;
    /** 수정자_아이디 **/
    private int mdfrid;
    /** 등록_일시 **/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    /** 등록_아이디 **/
    private int rgtrid;
    
    /** HOT 게시글 여부  **/
    private String hotYn;
    
    /** NEW 게시글 여부  **/
    private String newYn;

    // 이후 추가
    
    private int fileCnt;
    
    //BBS 정보  
    private String nm;
    private String userNm;
    private String userid;
    private String acnt;
    
    private String atchfileUseYn;
	private Integer atchfileCnt;
	private Integer atchfileSize;
	private String cmntUseYn;
	private String rplyUseYn;
	private String clUseYn;
	private String fxdNtcUseYn;
	private Integer fxdNtcCnt;
	
	//답글 리스트용 추가 
	private ArrayList<FileVo> fileMap;
	private Integer currentFileCnt;
	
	//현재 고정 게시글 수 (insert or update시 제한 두기 위함 ) 
    private Integer curFxdNtcCnt;

    private Integer nextPstid;
    private String nextTitle;
    private Integer prevPstid;
    private String prevTitle;

    /*탭 식별용(공지사항에만 사용)*/
    private Integer tabType = 1;
    private String instNm;
	
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