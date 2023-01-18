package com.kbrainc.plum.mng.prtpn.cntstRcptHist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

import java.util.Date;

/**
 * 공모전 접수내역 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.model
 * - CntstAplyVO.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstAplyVO
 * @Description : 공모전 접수내역  VO
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class CntstAplyVO extends ParentRequestVo {

    /** 신청아이디 */
    private Integer aplyid;
    /** 공모전아이디 */
    private Integer cntstid;

    /** 신청번호 */
    private String aplyno;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private String rgtrid;

    /** 사용자이름*/
    private String userNm;

    /** 사용자ID*/
    private String userAcnt;

    /** 기관명*/
    private String instNm;

    /** 작성일 YMD */
    private String regDtYmd;

    /** 공모전 제목 */
    private String cntstTtl;

    /** 공모전 분류 */
    private String cntstClsfCd;

    /** 검색 영역 */
    private String searchCntstClsfCd;

    private String searchCntstSttsCd;
}
