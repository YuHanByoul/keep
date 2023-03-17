package com.kbrainc.plum.front.pltfomImprvmPropsl.model;

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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 플랫폼 개선 제안 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.model
 * - PltfomImprvmPropslVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslVo
 * @Description : 플랫폼 개선 제안 Vo 클래스
 * @date : 2023. 02. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.PltfomImprvmPropslVo")
@Data
@NoArgsConstructor
public class PltfomImprvmPropslVo extends ParentRequestVo {
    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 제안 아이디 */
    private Integer prpslid;

    /** 사용자 아이디 */
    private Integer userid;

    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다.")
    private String ttl;

    /** 내용 */
    @NotEmpty(message = "내용을 입력해주십시오.")
    @Size(max = 4000, message = "내용은 4000자를 넘을 수 없습니다.")
    private String cn;

    /** 사용자 계정 */
    private String acnt;

    /** 사용자 이름 */
    private String nm;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 분류 코드 */
    private String clsfCd;

    /** 분류 코드 이름 */
    private String clsfCdNm;

    /** 처리 상태 코드 */
    private String prcsSttsCd;

    /** 처리 상태 코드 이름 */
    private String prcsSttsCdNm;

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

    public void setPrcsSttsCd(String prcsSttsCd) {
        this.prcsSttsCd = prcsSttsCd;

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
