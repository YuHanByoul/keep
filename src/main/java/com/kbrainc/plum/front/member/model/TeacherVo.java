package com.kbrainc.plum.front.member.model;

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
    private String schl_nm;
    
    /** 학교 코드  */
    private String schl_cd;
    
    /** 담당 과목 이름 */
    private String chrg_crs_nm;
    
    /** 담당 과목 코드 */
    private String chrg_crs_cd;
    
    /** 담임 여부  */
    private String cls_tchr_yn;
    
    /** 수정 일시  */
    private String updt_dt    ;
    
    /** 수정자 아이디  */
    private Integer updtuserid ;
    
    /** 등록 일시  */
    private String reg_dt     ;
    
    /** 등록자 아이디 */
    private Integer reguserid  ;
    
    
}