package com.kbrainc.plum.mng.spcltyDta.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 전문자료 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spcltyDta.model
 * - SpcltyDtaVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaVo
 * @Description : 전문자료 Vo 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class SpcltyDtaVo extends ParentRequestVo {
    private UserVo user;

    private Integer dtaid;

    private Integer yy;

    @NotEmpty(message = "")
    private String writr;

    @NotEmpty(message = "")
    private String ttl;

    @NotEmpty(message = "")
    private String cn;

    private Date regDt;

    private String regD;

    private Integer rgtrid;

    private String rgtrNm;

    private Integer instid;

    private Integer hits;

    private Integer pdfFileid;

    private Integer atchFilegrpid;

    private List<FileVo> pdfFileList;

    private List<FileVo> atchFileList;

    private Integer[] deleteDtaids;
}
