package com.kbrainc.plum.front.exprtPool.envtcher.model;

import lombok.Data;

/**
 * 환경교육사 양성기관Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.envtcher.model
 * - EnvtcherAgencyVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvtcherAgencyVo
 * @Description : 환경교육사 양성기관Vo 클래스
 * @date : 2023. 04. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class EnvtcherAgncyVo {
    /** 기관ID */
    public String agncyId;

    /** 기관명 */
    public String agncyNm;

    /** 기관구분 */
    public String agncyDvsn;

    /** 과정등급코드값 */
    public String crsGrdCdv;

    /** 전화번호 */
    public String telno;

    /** FAX */
    public String fax;

    /** 은행코드값 */
    public String bnkCdv;

    /** 계좌번호 */
    public String accno;

    /** 예금주 */
    public String acchdr;

    /** 우편번호 */
    public String zipc;

    /** 주소 */
    public String addr;

    /** 주소시도 */
    public String addrSido;

    /** 주소시군구 */
    public String addrSigungu;

    /** 주소상세 */
    public String addrDtl;

    /** 지역코드값 */
    public String areaCdv;

    /** 지역상세 */
    public String areaDtl;

    /** 기관URL */
    public String agncyUrl;

    /** 로고파일ID */
    public String logoFileId;
}
