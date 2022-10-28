package com.kbrainc.plum.front.member.model;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 회원정보VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberVo.java
* </pre> 
*
* @ClassName : MemberVo
* @Description : 회원정보Vo 클래스
* @author : KBRAINC
* @date : 2021. 4. 12.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
@Alias("front.TeacherVo")
public class TeacherVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 학교이름 */
    private String schlNm;
    
    /** 학교 코드  */
    private String schlCd;
    
    /** 담당 과목 이름 */
    private String chrgCrsNm;
    
    /** 담당 과목 코드 */
    private String chrgCrsCd;
    
    /** 담임 여부  */
    private String clsTchrYn;
    
    /** 수정 일시  */
    private String updtDt;
    
    /** 수정자 아이디  */
    private Integer updtuserid;
    
    /** 등록 일시  */
    private String regDt;
    
    /** 등록자 아이디 */
    private Integer reguserid;
    
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