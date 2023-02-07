package com.kbrainc.plum.front.inqry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 1:1문의 요청Vo
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.model
 * - InqryVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryReqVo
 * @Description : 1:1문의 요청Vo
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Data
@Alias("front.InqryVo")
@NoArgsConstructor
public class InqryVo extends ParentRequestVo {
    private UserVo user;
    private Integer inqryid;
    private int siteid;
    private String title;
    private String cntnts;
    private String acnt;
    private Integer filegrpid;
    private String inqryClCd;
    private String inqryClCdNm;
    private String sttsCd;
    private String sttsCdNm;
    private String rlsYn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    public void setInqryClCd(String inqryClCd) {
        this.inqryClCd = inqryClCd;

        if (CommonUtil.isEmpty(this.inqryClCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.inqryClCd);
                this.inqryClCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setSttsCd(String sttsCd) {
        this.sttsCd = sttsCd;

        if (CommonUtil.isEmpty(this.sttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
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