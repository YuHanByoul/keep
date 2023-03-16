package com.kbrainc.plum.mng.expertPoolMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 전문가 섭외 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertRelationVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertRelationVo
 * @Description : 전문가 섭외 Vo 클래스
 * @date : 2023. 01. 05.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertRelationVo extends ParentRequestVo {
    private Integer dmndid;
    private Integer exprtid;
    private String exprtTypeCd;
    private String exprtTypeCdNm;
    private String exprtAcnt;
    private String exprtNm;
    private Integer userid;
    private String userNm;
    private String userAcnt;
    private String userEml;
    private String userMoblphon;
    private String dmndSttsCd;
    private String dmndSttsCdNm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updateDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date lctrBgngDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date lctrEndDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    private String addr;
    private String addrDtl;
    private String eml;
    private String dmndCn;
    private Integer filegrpid;
    private String ttl;
    private List<FileVo> fileVo;
    private Integer instid;
    private String instNm;
    private String moblphon;

    public void setExprtTypeCd(String exprtTypeCd) {
        this.exprtTypeCd = exprtTypeCd;

        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.exprtTypeCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtTypeCd);
                this.exprtTypeCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setDmndSttsCd(String dmndSttsCd) {
        this.dmndSttsCd = dmndSttsCd;

        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.dmndSttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.dmndSttsCd);
                this.dmndSttsCdNm = code.getCdNm();
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
