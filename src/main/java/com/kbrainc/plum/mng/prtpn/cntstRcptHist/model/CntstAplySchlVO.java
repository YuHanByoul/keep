package com.kbrainc.plum.mng.prtpn.cntstRcptHist.model;
import lombok.Data;

import java.util.Date;

/**
 * 공모전 접수내역 학교(환경방학 일기장 프로젝트) VO
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.model
 * - CntstRcptHistSchlVO.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistSchlVO
 * @Description : 공모전 접수내역 학교(환경방학 일기장 프로젝트) VO
 * @date : 2023. 01. 12.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class CntstAplySchlVO {

    /** 신청_학교아이 */
    private Integer aplySchlid;
    /** 신청아이디 */
    private Integer aplyid;
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
    /** 학생_남자 */
    private Integer stdntMale;
    /** 학생_여자 */
    private Integer stdntFemale;
    /** 수정_일시 */
    private Date mdfcnDt;
    /** 수정자아이디 */
    private Integer mdfrid;
    /** 등록_일시 */
    private Date regDt;
    /** 등록자아이디 */
    private Integer rgtrid;
}
