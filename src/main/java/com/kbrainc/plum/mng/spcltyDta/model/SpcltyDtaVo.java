package com.kbrainc.plum.mng.spcltyDta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @Digits(integer = 5, fraction = 0, message = "정확한 년도를 입력해 주십시오.")
    private Integer yy;

    @NotEmpty(message = "저자를 입력해 주십시오.")
    @Size(max = 100, message = "저자는 60자를 넘을 수 없습니다.")
    private String writr;

    @NotEmpty(message = "제목을 입력해 주십시오.")
    @Size(max = 100, message = "제목은 200자를 넘을 수 없습니다.")
    private String ttl;

    @NotEmpty(message = "내용을 입력해 주십시오.")
    private String cn;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date regDt;

    private String regD;

    private Integer rgtrid;

    private String rgtrNm;

    private String rgtrAcnt;

    private Integer hits;

    private Integer pdfFileid;

    private Integer atchFilegrpid;

    private List<FileVo> pdfFileList;

    private List<FileVo> atchFileList;

    private String clsfCd;

    @NotEmpty(message = "저작권 종류를 선택해 주십시오.")
    private String cpyrhtCd;

    /* 등록/수정용 파라미터*/
    @NotEmpty(message = "분류를 선택해 주십시오.")
    private String[] clsfCds; /*228*/

    /* 검색용 파라미터 */
    private String searchBgngDt;

    private String searchEndDt;

    private String searchClsfCd;

    private String searchClsfCdNm;

    /*삭제용 파라미터 */
    private Integer[] deleteDtaids;

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
