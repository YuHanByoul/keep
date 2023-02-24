package com.kbrainc.plum.front.intro.envEduPlcyDta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.controller
 * - SpcltyDtaVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaVo
 * @Description :
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.SpcltyDtaVo")
public class SpcltyDtaVo extends ParentRequestVo {
    public Integer dtaid;
    public String clsfCd;
    public String yy;
    private String typeCd;
    private String writr;
    private String ttl;
    private String cn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    private String clsfCdNm;

    private Integer pdfFileid;
    private Integer atchFilegrpid;
    private List<FileVo> pdfFileList;
    private List<FileVo> atchFileList;

    private Integer prevDtaid;
    private String prevDtaTtl;
    private Integer nextDtaid;
    private String nextDtaTtl;

    private String searchFromRegDt;
    private String searchToRegDt;
    private String[] searchClsfCd;

    public void setClsfCd(String clsfCd) {
        this.clsfCd = clsfCd;

        if (CommonUtil.isEmpty(this.clsfCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.clsfCd);
                this.clsfCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }
}
