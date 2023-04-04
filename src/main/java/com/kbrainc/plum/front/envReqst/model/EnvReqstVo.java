package com.kbrainc.plum.front.envReqst.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 지역 환경교육센처 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.envReqst.model
 * - EnvReqstVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvReqstVo
 * @Description : 지역 환경교육센처 Vo
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Data
public class EnvReqstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 시설명 */
    private String fcltNm;

    /** 공간명 */
    private String spceNm;

    /** 공간id */
    private Integer spceid;

    /** 주소 */
    private String addr;

    /** 주소상세 */
    private String addrDtl;

    /** 신청방법코드 */
    private String aplyMthdCd;

    /** 신청방법명 */
    private String aplyMthdNm;

    /** 기관아이디 */
    private String instid;

    /** 기관명 */
    private String instNm;

    /** 대표이미지 파일키 */
    private String fileIdntfcKey;

    /** 대표 이미지 파일아이디 */
    private Integer rprsImgFileid;

    /** 상세 이미지 파일그룹아이디 */
    private Integer dtlImgFilegrpid;

    /** 안내 파일아이디 */
    private Integer gdncFileid;

    /** 시설 */
    private Integer fcltid;

    /** 은행코드 */
    private String bankCd;

    /** 입금계좌 */
    private String bankCdNm;

    /** 계좌번호 */
    private String bacntNo;

    /** 예금주 이름 */
    private String dpstrNm;

    /** rsvtdeid */
    private Object rsvtdeid;

    /** 예약일자 */
    private String de;

    /** 금액 */
    private BigDecimal amt;

    /** 예약가능여부 */
    @Pattern(regexp="[YN]")
    private String rsvtPsbltyYn;

    /** 예약id리스트 */
    private String[] rsvtdeids;

    /** 예약구분코드 */
    private String utztnSeCd;

    private String bgngDt;

    private String endDt;

    private String aplcntNm;

    private String aplcntMoblphon;

    private String pyrNm;

    private String aplcntEml;

    private int nopeAdult;

    private int nopeChil;

    private int nopeInfnt;

    private int aplyid;

    private String aplcntid;

    private String dpstBacnt;

    private String utztnPrps;

    private String aplyDt;
}
