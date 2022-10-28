package com.kbrainc.plum.mng.bbs.model;/** **/

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * BBS 분류 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.bbs.model
 * - BbsClVo.java
 * </pre> 
 *
 * @ClassName : BbsClVo
 * @Description : BBS 분류 VO
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class BbsClVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** **/

    private int bbsClid;
    /** **/
    private Integer bbsid;
    /** **/
    private String clNm;
    /** **/
    private int ord;
    /**  **/
    private String useYn;
    /** **/
    private String updtDt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String regDt;
    /** 등록_일시 **/
    private int reguserid;/** 등록_아이디 **/
    
    public BbsClVo(Integer bbsid) {
        this.setBbsid(bbsid);
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