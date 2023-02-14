/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 기관역량 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - CapabilityVo.java
* </pre> 
*
* @ClassName : CapabilityVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 8.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CapabilityVo extends ParentRequestVo {

    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 사업_목적 */
    private String bsnsPrps;
    
    /** 사업_개요 */
    private String bsnsSumry;
    
    /** 설립_일자 */
    private String fondDe;
    
    /** 운영_기간 */
    private String operPrd;
    
    /** 강사_경력 */
    private String instrctrCareer;
    
    /** 지정_여부 */
    private String dsgnYn;
    
    /** 지도사_보유_여부 */
    private String instrctrHoldYn;
    
    /** 이전년_지원사업_운영_여부 */
    private String bfryySportbsnsOperYn;
    
    /** 지정_번호 */
    private String dsgnNo;
    
    /** 자격_번호 */
    private String qlfcNo;
    
    /** 운영사업_이름 */
    private String operbsnsNm;
    
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
    
    /** 프로그램_주제_상위_코드 */
    private String prgrmSbjctCd1;

    /** 프로그램_주제_코드 */
    private String prgrmSbjctCd;
    
    /** 프로그램_주제_코드명 */
    private String prgrmSbjctNm;
    
    /** 프로그램 주제 코드 리스트 */
    private List<String> prgrmSbjctCds; 
    
    /** 구분_코드 */
    private String[] seCd;
    
    /** 구분_코드명 */
    private String[] seNm;
    
    /** 사업_이름 */
    private String[] bsnsNm;
    
    /** 사업_기간 */
    private String[] bsnsPrd;
    
    /** 교육_인원수 */
    private Integer[] eduNope;
    
    /** 사업_예산 */
    private String[] bsnsBgt;
    
    /** 운영_내용 */
    private String[] operCn;
    
    /** 순서 */
    private Integer[] ordr;  
}
