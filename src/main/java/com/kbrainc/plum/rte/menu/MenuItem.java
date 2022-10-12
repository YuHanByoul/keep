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
    private String typeCd;
    /* URL */
    private String url;
    /* 상위메뉴ID */
    private String upprMenuid;
    /* 참조메뉴ID */
    private String refMenuid;
    /* 팝업여부 */
    private String popupYn;
    /* 팝업사이즈_높이 */
    private String popupHg;
    /* 팝업사이즈_너비 */
    private String popupWd;
    /* 대상코드 */
    private String popupTrgtCd;
    /* 로그인여부 */
    private String loginYn;
    /* 숨김여부 */
    private String hideYn;
    /* HTTPS사용여부 */
    private String httpsUseYn;
    /* 메뉴명 노출 대상 */
    private String nmExpsrTrgtCd;
    /* 깊이 */
    private String dpth;
    /* 정렬순서 */
    private String ord;
    /* 트리메뉴명 */
    private String treeMenuNm;
    /* 트리메뉴ID */
    private String treeMenuid;
    /*  */
    private String treeOrd;
    /* 프로그램유형코드 */
    private String ptypeCd;
}