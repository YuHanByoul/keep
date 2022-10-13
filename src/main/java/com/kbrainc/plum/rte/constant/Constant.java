package com.kbrainc.plum.rte.constant;

/**
 * 
 * 각종 상수를 관리하는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.constant
 * - Constant.java
 * </pre> 
 *
 * @ClassName : Constant
 * @Description : 각종 상수를 관리하는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class Constant {
    
    // REST Response result
    public static final String REST_API_RESULT_SUCCESS = "success";
    public static final String REST_API_RESULT_FAIL = "fail";
    
    // 사용자 구분 코드
    public static final String MEMBER_SE_CD_PERSONAL = "P"; //개인회원
    public static final String MEMBER_SE_CD_COMPANY = "C";  //기업회원
    
    // 사용자 상태 코드
    public static final String MEMBER_STATE_CD_JOIN = "1";          //가입
    public static final String MEMBER_STATE_CD_WAIT = "2";          //대기
    public static final String MEMBER_STATE_CD_WITHDRAWAL = "3";    //탈퇴
    
    // 사용자 역할 구분 코드
    public static final String MEMBER_ROLE_CD_USER = "U";  //사용자
    public static final String MEMBER_ROLE_CD_ADMIN = "A"; //관리자
    
    // 메뉴 유형 코드
    public static final String MENU_SE_CD_DIRECTORY = "D"; //디렉토리
    public static final String MENU_SE_CD_PROGRAM = "P";   //프로그램
    
    // 팝업 대상 코드
    public static final String POPUP_TARGET_CD_SELF = "P"; //현재창
    public static final String POPUP_TARGET_CD_NEW = "N";  //새창
    
    // 이름 노출 대상 코드
    public static final String NAME_SHOW_TARGET_CD_ALL = "N";   //모두
    public static final String NAME_SHOW_TARGET_CD_LOGIN = "A"; //로그인 사용자
    
    // 프로그램 유형 코드
    public static final String PROGRAM_TYPE_CD_DIRECTORY = "01";    //디렉토리
    public static final String PROGRAM_TYPE_CD_MENU = "02";         //메뉴
    public static final String PROGRAM_TYPE_CD_MENUDETAIL = "03";   //메뉴상세
    public static final String PROGRAM_TYPE_CD_FUNCTION = "04";     //기능
    public static final String PROGRAM_TYPE_CD_POPUP = "05";        //팝업
    
    // 로그인 내역 - 디바이스 코드
    public static final String DEVICE_CD_WEB = "W"; //웹
    public static final String DEVICE_CD_APP = "A"; //앱
    
    // 이메일 인증 구분
    public static final String EMAIL_AUTH_SE_CD_NUMBER = "1"; //번호인증
    public static final String EMAIL_AUTH_SE_CD_LINK = "2";   //링크인증

    // 게시판 분류 코드
    public static final String BBS_CL_CD_NORMAL = "1"; //일반형
    public static final String BBS_CL_CD_NOTICE = "2"; //공지형
    
    // 팝업 공지 노출 위치 코드
    public static final String POPUP_NOTICE_LOCATION_CD_MAIN = "M"; //메인
    public static final String POPUP_NOTICE_LOCATION_CD_MENU = "S"; //메뉴
    
    // 팝업 공지 유형 코드
    public static final String POPUP_NOTICE_TYPE_CD_POPUP = "P";        //팝업
    public static final String POPUP_NOTICE_TYPE_CD_LAYER_NORMAL = "L"; //레이어 일반
    public static final String POPUP_NOTICE_TYPE_CD_LAYER_MODAL = "M";  //레이어 모달
    
    // 팝업 공지 대상 코드
    public static final String POPUP_NOTICE_TARGET_CD_ALL = "T";  //전체
    public static final String POPUP_NOTICE_TARGET_CD_ROLE = "R"; //역할
    
    // 사이트 시스템 구분 코드
    public static final String SITE_SYSTEM_SE_CD_USER = "U";  //사용자
    public static final String SITE_SYSTEM_SE_CD_ADMIN= "A";  //관리자
    
    //스케쥴 상태 코드
    public static final String SCHEDULE_STATE_CD_SUCCESS = "S"; //성공
    public static final String SCHEDULE_STATE_CD_FAIL = "F";    //실패
    
}