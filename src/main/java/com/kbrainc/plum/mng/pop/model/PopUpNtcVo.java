package com.kbrainc.plum.mng.pop.model;/** **/

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 팝업 공지 VO 
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.model
 * - PopUpNtcVo.java
 * </pre> 
 *
 * @ClassName : PopUpNtcVo
 * @Description : PopUpNtcVo
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class PopUpNtcVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** **/

    private int popupntcid;
    private String title;
    private String cntnts;
    private String expsrLcCd;
    private Integer menuid;
    private Integer siteid;
    private String loginNeedYn;
    private String popupTypeCd;
    private String strtDt;
    private String endDt;
    private Integer widthSize;
    private Integer vrtclSize;
    private Integer topLc;
    private Integer leftLc;
    private String ntcTrgtCd;
    private Integer ntcTrgtRoleid;
    private String mdfcnDt;
    private int mdfrid;
    private String regDt;
    private int reguserid;

    // 추가
    private String popSts;
    private String popSts1;
    private String popSts2;
    private String popSts3;
    private String menuNm;
    private String updNm;
    private String roleNm;
    
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