package com.kbrainc.plum.mng.bbs.model;/** **/

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
    
    /** **/
    private int cmntid;
    /** **/
    private Integer pstid; 
    /** **/
    private String cntnts;
    /** **/
    private Integer prntsCmntid;
    /** **/
    private Integer cmntGrp;
    /** **/
    private Integer dpth;
    /** **/
    private Integer ord;
    /** **/
    private String opnYn;
    /** **/
    private String mdfcnDt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String regDt;
    /** 등록_일시 **/
    private int reguserid; /** 등록_아이디 **/

    private String delYn;
    
    private String acnt;
    private String nm;
    
    private String paddingStr;
    private String dpthStr;
    
    public int totalPage;
    public int pageNumber;
    
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