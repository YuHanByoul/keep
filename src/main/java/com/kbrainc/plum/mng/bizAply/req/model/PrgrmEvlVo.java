/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - PrgrmEvlVo.java
* </pre> 
*
* @ClassName : PrgrmEvlVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class PrgrmEvlVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 평가_목적 */
    private String evlPrps;
    
    /** 사전_지도자_평가_항목 */
    private String bfrLdrEvlArtcl;
    
    /** 사전_지도자_평가_도구 */
    private String bfrLdrEvlTl;
    
    /** 사후_지도자_평가_항목 */
    private String aftrLdrEvlArtcl;
    
    /** 사후_지도자_평가_도구 */
    private String aftrLdrEvlTl;
    
    /** 사전_참여자_평가_항목 */
    private String bfrPrtpntEvlArtcl;
    
    /** 사전_참여자_평가_도구 */
    private String bfrPrtpntEvlTl;
    
    /** 사후_참여자_평가_항목 */
    private String aftrPrtpntEvlArtcl;
    
    /** 사후_참여자_평가_도구 */
    private String aftrPrtpntEvlTl;
    
    /** 사전_인솔자_평가_항목 */
    private String bfrGdrEvlArtcl;
    
    /** 사전_인솔자_평가_도구 */
    private String bfrGdrEvlTl;
    
    /** 사후_인솔자_평가_항목 */
    private String aftrGdrEvlArtcl;
    
    /** 사후_인솔자_평가_도구 */
    private String aftrGdrEvlTl;
    
    /** 데이터베이스_작성_여부 */
    private String databaseWrtYn;
    
    /** 수업일지_작성_여부 */
    private String clsjrnlWrtYn;
    
    /** 기타_여부 */
    private String etcYn;
    
    /** 기타_내용 */
    private String etcCn;
    
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
}
