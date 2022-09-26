package com.kbrainc.plum.rte.menu;

import lombok.Data;

/**
 * 
 * MenuItem 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.menu
 * - MenuItem.java
 * </pre> 
 *
 * @ClassName : MenuItem
 * @Description : MenuItem 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class MenuItem {

    /* 메뉴ID */
    private String menuid;
    /* 사이트아이디 */
    private String siteid;
    /* 프로그램ID */
    private String prgrmid;
    /* 메뉴명 */
    private String nm;
    /* 메뉴설명 */
    private String dc;
    /* 메뉴타입코드 */
    private String type_cd;
    /* URL */
    private String url;
    /* 상위메뉴ID */
    private String uppr_menuid;
    /* 참조메뉴ID */
    private String ref_menuid;
    /* 팝업여부 */
    private String popup_yn;
    /* 팝업사이즈_높이 */
    private String popup_hg;
    /* 팝업사이즈_너비 */
    private String popup_wd;
    /* 대상코드 */
    private String popup_trgt_cd;
    /* 로그인여부 */
    private String login_yn;
    /* 숨김여부 */
    private String hide_yn;
    /* HTTPS사용여부 */
    private String https_use_yn;
    /* 메뉴명 노출 대상 */
    private String nm_expsr_trgt_cd;
    /* 깊이 */
    private String dpth;
    /* 정렬순서 */
    private String ord;
    /* 트리메뉴명 */
    private String tree_menu_nm;
    /* 트리메뉴ID */
    private String tree_menuid;
    /*  */
    private String tree_ord;
    /* 프로그램유형코드 */
    private String ptype_cd;
}