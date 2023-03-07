package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 섭외 요청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyRelationVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyRelationVo
 * @Description : 섭외 요청Vo 클래스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MyRelationVo extends ParentRequestVo {
    private UserVo user;
    private Date lctrBgngDt;
    private Date lctrEndDt;
    private String ttl;
    private String zip;
    private String addr;
    private String addrDtl;
    private Integer userid;
    private String eml;
    private String moblphon;
    private String dmndCn;
    private Integer filegrpid;
    private List<FileVo> fileList;
    private String exprtTypeCd;
    private String dmndSttsCd;
    private Integer exprtid;
    private Integer dmndid;
    private String exprtTypeCdNm;
    private String nm;
    private String flds;
    private String dmndSttsCdNm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    private String reviewYn;
    private String lctrEndYn;

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
