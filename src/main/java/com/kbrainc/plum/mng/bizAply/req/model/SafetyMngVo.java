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
* 안전관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SafetyMngVo.java
* </pre> 
*
* @ClassName : SafetyMngVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SafetyMngVo extends ParentRequestVo {
    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 자격_여부 */
    private String qlfcYn;
    
    /** 자격_번호 */
    private String qlfcNo;
    
    /** 지도자_교육_시기 */
    private String ldrEduSess;
    
    /** 지도자_교육_담당자 */
    private String ldrEduPic;
    
    /** 지도자_교육_내용 */
    private String ldrEduCn;
    
    /** 참가자_교육_시기 */
    private String prtcpntEduSess;
    
    /** 참가자_교육_담당자 */
    private String prtcpntEduPic;
    
    /** 참가자_교육_내용 */
    private String prtcpntEduCn;
    
    /** 담당자_이름 */
    private String picNm;
    
    /** 담당자_전화번호 */
    private String picTelno;
    
    /** 주요_교사_역할 */
    private String mainTcherRole;
    
    /** 보조_교사_역할 */
    private String asstnTcherRole;
    
    /** 소방서_이름 */
    private String frsttNm;
    
    /** 소방서_전화번호 */
    private String frsttTelno;
    
    /** 경찰서_이름 */
    private String polcsttnNm;
    
    /** 경찰서_전화번호 */
    private String polcsttnTelno;
    
    /** 약국_이름 */
    private String parmacyNm;
    
    /** 약국_전화번호 */
    private String parmacyTelno;
    
    /** 병원_이름 */
    private String hsptlNm;
    
    /** 병원_전화번호 */
    private String hsptlTelno;
    
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
    
    private String popupYn;
}
