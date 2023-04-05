package com.kbrainc.plum.front.cntst.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import org.apache.ibatis.type.Alias;

import java.sql.Date;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.front.cntst.model
* - CntstAplyVo.java
* </pre>
*
* @ClassName : CntstAplyVo
* @Description : TODO
* @author : JD
* @date : 2023. 2. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.CntstAplyVo")
public class CntstAplyVo extends ParentRequestVo {

    private UserVo user;
    
    /** 공모전Vo */
    private CntstVo cntst;

    /** 신청아이디 */
    private Integer aplyid;
    /** 신청번호 */
    private String aplyno;
    /** 공모전아이디 */
    private Integer cntstid;
    /** 사용자아이디 */
    private Integer userid;
    /** 기관아이디 */
    private Integer instid;
    /** 신청_구분_코드 */
    private String aplySeCd;
    /** 공모_분야_코드 */
    private String pcntstFldCd;
    /** 신청_경로_코드 */
    private String aplyPathCd;
    /** 대표자_이름 */
    private String rprsvNm;
    /** 기관_이름 */
    private String instNm;
    /** 생년월일 */
    private String brdt;
    /** 대표자_휴대폰번호 */
    private String rprsvMoblphon;
    /** 이메일 */
    private String eml;
    /** 우편번호 */
    private String zip;
    /** 주소 */
    private String addr;
    /** 주소_상세 */
    private String addrDtl;
    /** 공동_참가자_이름 */
    private String jntPrtcpntNm;
    /** 공동_참가자_생년월일 */
    private String jntPrtcpntBrdt;
    /** 작품_제목 */
    private String prdctTtl;
    /** 작품_설명 */
    private String prdctExpln;
    /** 작품_파일그룹아이디 */
    private String prdctFilegrpid;
    /** 약관_동의_여부 */
    private String trmsAgreYn;
    /** 수정_일시 */
    private Date mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록_일시 */
    private Date regDt;
    /** 등록자아이디 */
    private String rgtrid;
    
    //환경일기장신청
    /** 학교_이름 */
    private String schlNm;
    /** 학년 */
    private String grade;
    /** 학생_인원수*/
    private String stdntNope;
    /** 학생_파일아이디*/
    private String stdntFileid;
}
