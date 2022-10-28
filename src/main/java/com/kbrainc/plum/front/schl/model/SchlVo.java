package com.kbrainc.plum.front.schl.model;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 학교정보VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.Shcl.model
* - MemberVo.java
* </pre> 
*
* @ClassName : ShclVo
* @Description : 학교정보VO 클래스.
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
@Alias("front.SchlVo")
public class SchlVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 학교코드 */
    private String schlCd;
    
    /** 학교명 */
    private String schlNm;
    
    /** 학교_계열_이름 */
    private String schlLineNm;
    
    /** 시도_이름 */
    private String sidoNm;
    
    /** 마이스터고 여부 */
    private String meisterYn;
    
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