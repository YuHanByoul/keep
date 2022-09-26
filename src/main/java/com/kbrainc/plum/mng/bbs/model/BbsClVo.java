package com.kbrainc.plum.mng.bbs.model;/** **/

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

    private int bbs_clid;
    /** **/
    private Integer bbsid;
    /** **/
    private String cl_nm;
    /** **/
    private int ord;
    /**  **/
    private String use_yn;
    /** **/
    private String updt_dt;
    /** 수정_일시 **/
    private int updtuserid;
    /** 수정자_아이디 **/
    private String reg_dt;
    /** 등록_일시 **/
    private int reguserid;/** 등록_아이디 **/
    
    public BbsClVo(Integer bbsid) {
        this.setBbsid(bbsid);
    }

}