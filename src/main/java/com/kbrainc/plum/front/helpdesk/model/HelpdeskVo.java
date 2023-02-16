package com.kbrainc.plum.front.helpdesk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 헬프데스크Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.helpdesk.model
 * - HelpdeskVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpdeskVo
 * @Description : 헬프데스크Vo 클래스
 * @date : 2023. 02. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.HelpdeskVo")
@Data
@NoArgsConstructor
public class HelpdeskVo extends ParentRequestVo {
    private UserVo user;

    private Integer inqryid;

    private Integer userid;

    @NotEmpty(message = "제목을 입력해 주십시오")
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다")
    private String ttl;

    private String clsfCd;

    private String clsfCdNm;

    @NotEmpty(message = "내용을 입력해 주십시오")
    @Size(max = 4000, message = "내용은 4000자를 넘을 수 없습니다.")
    private String cn;

    private Integer filegrpid;

    private String sttsCd;

    private String sttsCdNm;

    private String rlsYn;

    private String acnt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    private List<FileVo> fileList;

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
