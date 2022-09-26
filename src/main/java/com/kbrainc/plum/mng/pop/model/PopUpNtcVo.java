package com.kbrainc.plum.mng.pop.model;/** **/

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;

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
    private String expsr_lc_cd;
    private Integer menuid;
    private Integer siteid;
    private String login_need_yn;
    private String popup_type_cd;
    private String strt_dt;
    private String end_dt;
    private Integer width_size;
    private Integer vrtcl_size;
    private Integer top_lc;
    private Integer left_lc;
    private String ntc_trgt_cd;
    private Integer ntc_trgt_roleid;
    private String updt_dt;
    private int updtuserid;
    private String reg_dt;
    private int reguserid;

    // 추가
    private String pop_sts;
    private String pop_sts1;
    private String pop_sts2;
    private String pop_sts3;
    private String menu_nm;
    private String upd_nm;
    private String role_nm;
    
    

}