package com.kbrainc.plum.mng.packageindvdChck.model;

import lombok.Data;

/**
 * 꾸러미개체 점검 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageIndvdChck.model
 * - PackageindvdChckAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckAnsVo
 * @Description : 꾸러미개체 점검 답변Vo 클래스
 * @date : 2023. 03. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class PackageindvdChckAnsVo {
    private Integer chckid;
    private Integer artclid;
    private Integer tchaidid;
    private Integer exid;
    private String exCn;
    private String actnMttr;
}
