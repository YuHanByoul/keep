package com.kbrainc.plum.mng.bbs.model;/** **/

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * Comment VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.model
 * - CmntVo.java
 * </pre> 
 *
 * @ClassName : CmntVo
 * @Description : Comment VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CmntVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 댓글 아이디 **/
    private int cmntid;
    
    /**게시글 아이디 **/
    private Integer pstid;
    
    /**내용 **/
    @NotEmpty(message = "댓글 내용을 입력해주십시오.")
    private String cntnts;
    
    /** 부모 댓글 아이디**/
    private Integer prntsCmntid;
    
    /**댓글 그룹 **/
    private Integer cmntGrp;
    
    /**깊이 **/
    private Integer dpth;
    
    /**순서 **/
    private Integer ord;
    
    /** **/
    private String opnYn;
    
    /** **/
    private String mdfcnDt;
    /** 수정_일시 **/
    private int mdfrid;
    /** 수정자_아이디 **/
    private String regDt;
    /** 등록_일시 **/
    private int rgtrid; /** 등록_아이디 **/

    /** 삭제 여부  **/
    private String delYn;
    
    /*******추가*********/
    /** 작성자 계정  **/
    private String acnt;
    /** 작성자 이름  **/
    private String nm;
    /** 여백 (depth 에 따른)  **/
    private String paddingStr;
    /** 여백 문자(깊이)  **/
    private String dpthStr;
    
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