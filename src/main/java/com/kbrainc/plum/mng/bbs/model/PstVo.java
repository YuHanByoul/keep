package com.kbrainc.plum.mng.bbs.model;/** **/

import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 프로그램 도메인 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.model
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
public class PstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** **/

    private int pstid;
    /** **/
    private Integer bbsid;
    /** **/
    private String title;
    /** **/
    private String cntnts;
    /** **/
    private Integer prntsPstid;
    /** 부모게시글 아이디 **/
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
    private String fxdNtcYn;
    /** **/
    private String fxdNtcStrtDt;
    /** **/
    private String fxdNtcEndDt;
    /** **/
    private String loginYn;
    /** **/
    private Integer filegrpid;
    /** **/
    private String mdfcnDt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String regDt;
    /** 등록_일시 **/
    private int reguserid;
    /** 등록_아이디 **/

    // 이후 추가
    
    private int fileCnt;
    
    
    
    //BBS 정보  
    private String nm;
    private String userNm;
    private String userid;
    
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