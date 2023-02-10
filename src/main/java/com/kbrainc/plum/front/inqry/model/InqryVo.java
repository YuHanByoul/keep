package com.kbrainc.plum.front.inqry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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
    private SiteInfoVo site;

    private UserVo user;

    private Integer inqryid;

    private int siteid;

    @NotEmpty(message = "제목을 입력해 주십시오.")
    @Size(max = 500, message = "제목은 500자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty(message="내용을 입력해 주십시오.")
    private String cntnts;

    private String acnt;

    private Integer filegrpid;

    private Integer userid;

    private String inqryClCd;

    private String inqryClCdNm;

    private String sttsCd;

    private String sttsCdNm;

    private String rlsYn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    private List<FileVo> fileList;

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
