/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - ReqUserVo.java
* </pre> 
*
* @ClassName : ReqUserVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.ReqUserVo")
public class ReqUserVo extends ParentRequestVo {

    /** 분야 코드 */
    private String fldCd;
    
    /** 공모_이름 */
    private String pcntstNm;
    
    /** 로그인사용자 정보 */
    private UserVo user;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 공모아이디 */
    private Integer pcntstid;
    
    /** 신청번호 */
    private String aplyno;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 사용자 영문 아이디 */
    private String acnt;
    
    /** 사용자명 */
    private String userNm;
    
    /** 기관명 */
    private String instNm;
    
    /** 프로그램명 */
    @NotEmpty(message = "프로그램명을 입력해주십시오.")
    private String prgrmNm;
    
    /** 대표자명 */
    private String rprsvNm;
    
    /** 등록기관명 */
    private String regInstNm;
    
    /** 등록번호 */
    private String regNo;
    
    /** 등록일자 */
    private String regDe;
    
    /** 기관유형코드 */
    private String instTypeCd;
    
    /** 기관유형코드명 */
    private String instTypeNm;
    
    /** 기타내용 */
    private String etcCn;
    
    /** 홈페이지 */
    private String hmpg;
    
    /** 종교코드 */
    private String relgnCd;
    
    /** 종교코드명 */
    private String relgnNm;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청자 전화번호 */
    private String aplcntTelno;
    
    /** 신청자 이메일 */
    private String aplcntEml;
    
    /** 신청기관 전화번호 */
    private String aplyInstTelno;
    
    /** 기관 권역코드 */
    @NotEmpty(message = "권역을 선택해주십시오.")
    private String instSareaCd;
    
    /** 기관 권역코드명 */
    private String instSareaNm;
    
    /** 기관 우편번호 */
    private String instZip;
    
    /** 기관 주소 */
    private String instAddr;
    
    /** 주소 */
    private String addr;
    
    /** 기관 상세 주소 */
    private String instAddrDtl;
    
    /** 지역코드 */
    @NotEmpty(message = "지역을 선택해주십시오.")
    private String rgnCd;
    
    /** 지역코드명 */
    private String rgnNm;
    
    /** 지도자 관리 방법 */
    private String ldrMngMthd;
    
    /** 파일그룹아이디1 */
    private Integer filegrpid1;
    
    /** 파일그룹아이디2 */
    private Integer filegrpid2;
    
    /** 파일그룹아이디3 */
    private Integer filegrpid3;
    
    /** 신청상태 코드 */
    private String aplySttsCd;
    
    /** 신청상태 코드명 */
    private String aplySttsNm;
    
    /** 선정상태 코드 */
    private String slctnSttsCd;
    
    /** 선정상태 코드명 */
    private String slctnSttsNm;
    
    /** 사업취소여부 */
    private String bsnsCnclYn;
    
    /** 개인정보수집및이용_동의_여부 */
    private String privcyAgreYn;
    
    /** 개인정보_제3자_제공_동의_여부 */
    private String prvcThptyPvsnAgreYn;

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
    
    /** 신청건수 */
    private Integer cnt;
    
    /** 신규,수정 모드 */
    private String mode;
}
