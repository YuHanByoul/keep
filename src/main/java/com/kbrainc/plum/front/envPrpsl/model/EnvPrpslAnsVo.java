package com.kbrainc.plum.front.envPrpsl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * 환경교육 제안 답변 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.envPrpsl.model
 * - EnvPrpslAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvPrpslAnsVo
 * @Description : 환경교육 제안 답변 Vo 클래스
 * @date : 2023. 02. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.EnvPrpslAnsVo")
@Data
public class EnvPrpslAnsVo {

    /** 답변아이디 */
    private Integer ansid;

    /** 제안아이디 */
    private Integer prpslid;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 답변자이름 */
    private String acnt;

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

    private Integer filegrpid;

    private List<FileVo> fileList;
}
