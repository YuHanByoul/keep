package com.kbrainc.plum.mng.packageindvdChck.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.Date;

/**
 * 꾸러미 교구 구성Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.model
 * - PackageindvdTchaidVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdTchaidVo
 * @Description : 꾸러미 교구 구성Vo 클래스
 * @date : 2023. 03. 28.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class PackageindvdTchaidVo {
    /** 로그인사용자정보 */
    private UserVo user;

    /** 꾸러미 개체 아이이디 */
    private int packageindvdid;

    /** 교구 아이이디 */
    private Integer tchaidid;

    /** 교구명 */
    private String tchaidNm;

    /** 교구 구성품명 */
    private String cmpntNms;

    /** 구성품 구성 수량 */
    private Integer qntyCmpstn;

    /** 교구 재고 */
    private Integer qntyInvntry;

    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;

    /** 수정자 아이디 */
    private String mdfrid;

    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;

    /** 등록자 아이디 */
    private String rgtrid;

}
