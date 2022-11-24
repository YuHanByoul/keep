package com.kbrainc.plum.mng.prgrm.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 
* 프로그램 도메인 클래스
*
* <pre>
* com.kbrainc.plum.mng.prgrm.model
* - PrgrmVo.java
* </pre> 
*
* @ClassName : PrgrmVo
* @Description : 프로그램 도메인 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Data
public class PrgrmVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 이름 */
    @NotEmpty(message = "프로그램명을 입력해 주십시오.")
    @Size(max = 60, message = "프로그램명은 60자를 넘을 수 없습니다.")
    private String nm;

    /** 타입 */
    private String typeCd;

    /** 설명 */
    @Size(max = 130, message = "프로그램설명은 130자를 넘을 수 없습니다.")
    private String dc;

    /** URL */
    @Size(max = 160, message = "URL은 160자를 넘을 수 없습니다.")
    private String url;

    /** 상위_프로그램아이디 */
    private Integer upprPrgrmid;

    /** 상위_프로그램명 */
    private String upprPrgrmNm;

    /** 로그인_여부 */
    private String loginYn;

    /** 순서 */
    private Integer ord;

    /** 트리_순서 */
    private String treeOrd;

    /** 트리_프로그램_이름 */
    private String treePrgrmNm;

    /** 트리_프로그램아이디 */
    private String treePrgrmid;

    /** 대상프로그램아이디 */
    private Integer tprgrmid;

    /** jQuery dynatree hitMode */
    private String hitMode;

    /** upperYn */
    private String upperYn;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;
    
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