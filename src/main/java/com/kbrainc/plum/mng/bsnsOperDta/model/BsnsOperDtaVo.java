package com.kbrainc.plum.mng.bsnsOperDta.model;

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
    @Digits(integer = 5, fraction = 0, message = "정확한 년도를 입력해 주십시오.")

    private Integer yy;

    private Integer instid;

    @NotEmpty(message = "제목을 입력해 주십시오.")
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다.")
    private String ttl;

    @NotEmpty(message = "내용을 입력해 주십시오.")
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

    private String clsfCd;

    @NotEmpty(message = "기관을 선택해 주십시오.")
    private String instNm;

    private String rgtrNm;

    private String rgtrAcnt;

    private List<FileVo> pdfFileList;

    private List<FileVo> atchFileList;

    /* 검색용 파라미터 */
    private String searchBgngDt;

    private String searchInstNm;

    private String searchClsfCd;

    private String searchClsfCdNm;

    private String searchEndDt;

    /*삭제용 파라미터 */
    private Integer[] deleteDtaids;

    /* 등록/수정용 파라미터*/
    private String[] clsfCds; /*227*/


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
