package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

/**
 * 전문가 관련 코드정보 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - ExprtEnum.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtEnum
 * @Description : 전문가 관련 코드정보 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public enum ExprtEnum {
    TYPE_CD("133"),
    ACTVT_RGN_CD("125"),
    ACTVT_SCOPE_CD("125"),
    TRGT_CD("135"),
    SJBCT_CD("126");

    private final String cd;

    ExprtEnum(String cd) {
        this.cd = cd;
    }

    public String getCd() {
        return cd;
    }
}
