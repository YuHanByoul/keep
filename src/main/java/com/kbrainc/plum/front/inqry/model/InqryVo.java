package com.kbrainc.plum.front.inqry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
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
    /** 사이트 정보 */
    private SiteInfoVo site;

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 문의 아이디 */
    private Integer inqryid;

    /** 사이트 아이디 */
    private int siteid;

    /** 제목 */
    @NotEmpty(message = "제목을 입력해 주십시오.")
    @Size(max = 500, message = "제목은 500자를 넘을 수 없습니다.")
    private String title;

    /** 내용 */
    @NotEmpty(message="내용을 입력해 주십시오.")
    private String cntnts;

    /** 사용자 이름 */
    private String nm;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 사용자 아이디 */
    private Integer userid;

    /** 문의 코드 */
    private String inqryClCd;

    /** 문의 코드 이름 */
    private String inqryClCdNm;

    /** 상태 코드 */
    private String sttsCd;

    /** 상태 코드 이름 */
    private String sttsCdNm;

    /** 공개 여부 */
    private String rlsYn;

    /** 등록일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    /** 파일 목록 */
    private List<FileVo> fileList;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }

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
