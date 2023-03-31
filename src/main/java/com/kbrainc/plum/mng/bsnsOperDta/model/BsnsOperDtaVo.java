package com.kbrainc.plum.mng.bsnsOperDta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 사업운영자료 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bsnsOperDta.model
 * - BsnsOperDtaVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaVo
 * @Description : 사업운영자료 Vo 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class BsnsOperDtaVo extends ParentRequestVo {
    private UserVo user;
    private Integer dtaid;
    private Integer yy;
    private Integer instid;
    private String ttl;
    private String cn;
    private Integer pdfFileid;
    private Integer atchFilegrpid;
    private Integer hits;
    private Date mdfcnDt;
    private Integer mdfrid;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date regDt;
    private String regD;

    private Integer rgtrid;
    private String[] clsfCds; /*227*/
    private String instNm;
    private String rgtrNm;
    private List<FileVo> pdfFileList;
    private List<FileVo> atchFileList;

    private String searchBgngDt;
    private String searchEndDt;

    /*삭제용 파라미터 */
    private Integer[] deleteDtaids;

}
