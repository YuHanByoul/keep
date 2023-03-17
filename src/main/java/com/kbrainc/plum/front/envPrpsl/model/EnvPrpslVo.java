package com.kbrainc.plum.front.envPrpsl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.apache.poi.poifs.property.Parent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 환경교육 제안 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.envPrpsl.model
 * - EnvPrpslVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvPrpslVo
 * @Description : 환경교육 제안 Vo 클래스
 * @date : 2023. 02. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.EnvPrpslVo")
@Data
@NoArgsConstructor
public class EnvPrpslVo extends ParentRequestVo {

    private UserVo user;

    /** 제안아이디 */
    private Integer prpslid;

    /** 분류_코드 */
    @NotEmpty
    private String clsfCd;

    /** 분류_코드명 */
    private String clsfCdNm;

    /** 제목 */
    @NotEmpty
    @Size(max=100, message = "제목은 100자를 넘을 수 없습니다")
    private String ttl;

    /** 내용 */
    @NotEmpty
    private String cn;

    /** 사용자아이디 */
    private Integer userid;

    /** 사용자계정 */
    private String acnt;

    /** 사용자이름 */
    private String nm;

    /** 기관아이디 */
    private Integer instid;

    /** 기관명 */
    private String instNm;

    /** 공개_여부 */
    private String rlsYn;

    /** 삭제_여부 */
    private String delYn;

    /** 처리_상태_코드 */
    private String prcsSttsCd;

    /** 처리_상태_코드명 */
    private String prcsSttsCdNm;

    /** 파일그룹아이디 */
    private Integer filegrpid;

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;

    /** 수정자아이디' */
    private Integer mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 파일 목록 */
    private List<FileVo> fileList;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }

    public void setClsfCd(String clsfCd) {
        this.clsfCd = clsfCd;
        //이미 코드이름이 있다면, 무시.
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

    public void setPrcsSttsCd(String prcsSttsCd) {
        this.prcsSttsCd = prcsSttsCd;
        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.prcsSttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.prcsSttsCd);
                this.prcsSttsCdNm = code.getCdNm();
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
