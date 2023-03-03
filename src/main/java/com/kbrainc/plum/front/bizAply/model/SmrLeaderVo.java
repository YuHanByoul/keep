/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 전문강사 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SmrLeaderVo.java
* </pre> 
*
* @ClassName : SmrLeaderVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.SmrLeaderVo")
public class SmrLeaderVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 공모아이디 */
    private Integer pcntstid;
    
    /** 분야 코드 */
    private String fldCd;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 성명 */
    private String nm;
    
    /** 생년월일 */
    private String brdt;
    
    /** 지도자 관리방안 */
    private String ldrMngMthd;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private Integer mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
    
    /** 구분 */
    private String[] se;
    
    /** 성명 */
    private String[] ldrnm;
    
    /** 업무내용 */
    private String[] taskCn;
    
    /** 시작_일자 */
    private String[] bgngDe;
    
    /** 종료_일자 */
    private String[] endDe;
    
    /** 기간 */
    private String[] eduDe;
    
    /** 학교_이름 */
    private String[] schlNm;
    
    /** 전공 */
    private String[] mjr;
    
    /** 학위 */
    private String[] dgr;
    
    /** 자격_이름 */
    private String[] qlfcNm;
    
    /** 등급 */
    private String[] grd;
    
    /** 취득일자 */
    private String[] acqsDe;
    
    /** 발령처 */
    private String[] wrkplc;
    
    /** 기간 */
    private String[] prd;
    
    /** 활동_이름 */
    private String[] actvtNm;
    
    /** 활동_유형 */
    private String[] actvtType;
}
