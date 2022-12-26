package com.kbrainc.plum.mng.bbs.model;/** **/

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 게시글 VO  클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.model
 * - PstVo.java
 * </pre> 
 *
 * @ClassName : PstVo
 * @Description : 게시글 VO  클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class PstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /**게시글  아이디 **/
    private int pstid;
    
    /**게시판 아이디 **/
    private Integer bbsid;
    
    /**사이트 아이디 **/
    private Integer siteid;
    
    /**제목 **/
    @NotEmpty(message = "제목을 입력해주십시오.")
    @Size(max = 500, message = "제목은 500자를 넘을 수 없습니다.")
    private String title;
    
    /**내용 **/
    @NotEmpty(message = "내용을 입력해주십시오.")
    private String cntnts;
    
    /** 부모게시글 아이디 **/
    private Integer prntsPstid;
    
    /** 그룹 **/
    private Integer grp;
    
    /** 깊이 **/
    private Integer dpth;
    
    /** 순서**/
    private Integer ord;
    
    /** 조회수**/
    private Integer hits;
    
    /** 분류아이디**/
    private Integer bbsClid;
    
    /** 고정공지 여부 **/
    private String fxdNtcYn;
    
    /**고정공지 시작일자 **/
    private String fxdNtcStrtDt;
    
    /**고정공지 종료일자 **/
    private String fxdNtcEndDt;
    
    /** 로그인 여부 **/
    @Pattern(regexp="[YN]")
    private String loginYn;
    
    /** 삭제 여부 **/
    @Pattern(regexp="[YN]")
    private String delYn;
    
    /**파일그룹 아이디 **/
    private Integer filegrpid;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;

    /** 다수 삭제용  */
    private String [] delPstids;
    
    /** 삭제 여부 **/
    @Pattern(regexp="[YN]")
    private String rplyYn;
    
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