package com.kbrainc.plum.front.bbs.model;/** **/

import org.apache.ibatis.type.Alias;

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
    /** **/

    private int pstid;
    /** **/
    private Integer bbsid;
    /** **/
    private String title;
    /** **/
    private String cntnts;
    /** **/
    private Integer prnts_pstid;
    /** 부모게시글 아이디 **/
    private String grp;
    /** **/
    private Integer dpth;
    /** **/
    private Integer ord;
    /** **/
    private Integer hits;
    /** **/
    private Integer bbs_clid;
    /** **/
    private String fxd_ntc_yn;
    /** **/
    private String fxd_ntc_strt_dt;
    /** **/
    private String fxd_ntc_end_dt;
    /** **/
    private String login_yn;
    /** **/
    private Integer filegrpid;
    /** **/
    private String updt_dt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String reg_dt;
    /** 등록_일시 **/
    private int reguserid;
    /** 등록_아이디 **/

    // 이후 추가
    
    private int file_cnt;
    
    
    
    //BBS 정보  
    private String nm;
    private String user_nm;
    private String userid;
    private String acnt;
    
    private String atchfile_use_yn;
	private Integer atchfile_cnt   ;
	private Integer atchfile_size;
	private String cmnt_use_yn   ;
	private String rply_use_yn;
	private String cl_use_yn ;
	private String fxd_ntc_use_yn;
	
	private Integer fxd_ntc_cnt;
	

	
	//현재 고정 게시글 수 (insert or update시 제한 두기 위함 ) 
    private Integer cur_fxd_ntc_cnt;
	
	

}