package com.kbrainc.plum.front.cntst.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

import org.apache.ibatis.type.Alias;

/**
* 공모전 신청(환경방학 일기장 프로젝트) Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.cntst.model
* - CntstAplySchlVo.java
* </pre>
*
* @ClassName : CntstAplySchlVo
* @Description : 공모전 신청(환경방학 일기장 프로젝트) Vo 클래스
* @author : JD
* @date : 2023. 2. 14.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.CntstAplySchlVo")
public class CntstAplySchlVo extends ParentRequestVo {
    
    private UserVo user;
    
    /** 신청아이디 */
    private String aplyid;
    /** 학교_이름 */
    private String schlNm;
    /** 교사_이름 */
    private String tcherNm;
    /** 교사_성별 */
    private String tcherGndr;
    /** 전화번호 */
    private String telno;
    /** 공모_분야_코드 */
    private String pcntstFldCd;
    /** 학년 */
    private String grade;
    /** 학생_인원수*/
    private String stdntNope;
    /** 학생_파일아이디*/
    private String stdntFileid;
    
}
