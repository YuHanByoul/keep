package com.kbrainc.plum.mng.prtpn.cntstRcptHist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

/**
 * 공모전 접수내역 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.model
 * - CntstVO.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistVO
 * @Description : 공모전 접수내역  VO
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class CntstRcptHistVO extends ParentRequestVo {

    /** 신청아이디 */
    private String aplyid;
    /** 공모전아이디 */
    private String cntstid;
    /** 사용자아이디 */
    private String userid;
    /** 기관아이디 */
    private String instid;
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
    private String mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록_일시 */
    private String regDt;
    /** 등록자아이디 */
    private String rgtrid;
    /** 사용자이름*/
    private String userNm;

    /** 사용자ID*/
    private String userAcnt;

    /** 기관명*/
    private String instNm;


    /** 검색 영역 */
    private String searchClsfCd;

    private String searchCntstSttsCd;
}
