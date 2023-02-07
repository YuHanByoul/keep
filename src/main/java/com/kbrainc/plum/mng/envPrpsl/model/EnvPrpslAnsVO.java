package com.kbrainc.plum.mng.envPrpsl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.Date;

/**
 * 환경교육제안 답변 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.model
 * - EnvPrpslAnsVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslAnsVO
 * @Description : 환경교육제안 답변 VO
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class EnvPrpslAnsVO extends ParentRequestVo {

    private UserVo user;

    /** 답변아이디 */
    private Integer ansid;

    /** 제안아이디 */
    private Integer prpslid;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 답변자아이디 */
    private Integer userid;

    /** 답변자이름 */
    private String usernm;

    /** 공개_여부 */
    private String rlsYn;

    /** 답변_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date ansDt;

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 처리_상태_코드 */
    private String prcsSttsCd;
}
