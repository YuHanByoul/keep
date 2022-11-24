package com.kbrainc.plum.mng.menu.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 
* 메뉴 도메인 클래스
*
* <pre>
* com.kbrainc.plum.mng.menu.model
* - MenuVo.java
* </pre> 
*
* @ClassName : MenuVo
* @Description : 메뉴 도메인 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Data
public class MenuVo {

    private static final Map<String, String> TYPE_STRING;
    static {
        Map<String, String> typeMap = new HashMap<String, String>();
        typeMap.put("P", "프로그램");
        typeMap.put("D", "디렉토리");

        TYPE_STRING = Collections.unmodifiableMap(typeMap);
    }

    /** 로그인사용자정보 */
    private UserVo user;

    /** 메뉴아이디 */
    private Integer menuid;

    /** 사이트아이디 */
    private Integer siteid;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 메뉴명 */
    @NotEmpty(message = "메뉴명을 입력해 주십시오.")
    @Size(max = 100, message = "메뉴명은 100자를 넘을 수 없습니다.")
    private String nm;

    /** 메뉴설명 */
    @Size(max = 400, message = "메뉴설명은 400자를 넘을 수 없습니다.")
    private String dc;

    /** 메뉴타입코드(P:프로그램, D:디렉토리) */
    private String typeCd;
    private String typeStr;

    /** URL */
    @Size(max = 500, message = "URL은 500자를 넘을 수 없습니다.")
    private String url;

    /** 상위메뉴아이디 */
    private Integer upprMenuid;

    /** 참조메뉴아이디 */
    private Integer refMenuid;

    /** 팝업여부 */
    private String popupYn;

    /** 팝업종류 */
    private String popupKnd;

    /** 팝업_높이 */
    @Digits(integer = 4, fraction = 0, message = "팝업크기는 정수이어야 합니다.")
    private Integer popupHg;

    /** 팝업_너비 */
    @Digits(integer = 4, fraction = 0, message = "팝업크기는 정수이어야 합니다.")
    private Integer popupWd;

    /** 팝업_대상_코드 */
    private String popupTrgtCd;

    /** 로그인여부 */
    private String loginYn;

    /** 숨김여부 */
    private String hideYn;

    /** HTTPS사용여부 */
    private String httpsUseYn;

    /** 메뉴명 노출 대상 N: 모두 A: 로그인 사용자(Auth) */    
    private String nmExpsrTrgtCd;

    /** 깊이 */
    private Integer dpth;

    /** 순서 */
    private Integer ord;

    /** 트리순서 */
    private String treeOrd;

    /** 프로그램타입 */
    private String ptypeCd;

    /** 프로그램URL */
    private String purl;

    /** 모드 */
    private String mode;

    /** 대상메뉴아이디 */
    private Integer tmenuid;

    /** jQuery dynatree hitMode. */
    private String hitMode;

    /** upperYn. */
    private String upperYn;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    public void setType(String t) {
        this.typeCd = t;
        this.typeStr = getTypeStr();
    }

    /**
    * 메뉴타입코드명을 가져온다.
    *
    * @Title       : getTypeStr 
    * @Description : 메뉴타입코드명을 가져온다.
    * @return String 메뉴타입코드명
    */
    public String getTypeStr() {
        if (typeCd == null) {
            return "NONE";
        }

        return TYPE_STRING.get(this.typeCd);
    }
    
    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setmdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

    public Date getmdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
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