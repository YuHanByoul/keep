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
 * - BsnsOperDtaVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaVo
 * @Description :
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.BsnsOperDtaVo")
public class BsnsOperDtaVo extends ParentRequestVo {
    private Integer dtaid;

    private Integer yy;

    private String instNm;

    private String ttl;

    private String cn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    private String cpyrhtCd;

    private Integer hits;

    private String typeCdNm;

    private String clsfCdNm;

    private Integer prevDtaid;

    private String prevDtaTtl;

    private Integer nextDtaid;

    private String nextDtaTtl;

    private String searchFromRegDt;

    private String searchToRegDt;

    private String searchClsfCd;

    private String searchClsfCdNm;

    private Integer pdfFileid;

    private Integer atchFilegrpid;

    private List<FileVo> pdfFileList;

    private List<FileVo> atchFileList;

    public void setSearchClsfCd(String searchClsfCd) {
        this.searchClsfCd = searchClsfCd;

        if (CommonUtil.isEmpty(this.searchClsfCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.searchClsfCd);
                this.searchClsfCdNm = code.getCdNm();
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
