package com.kbrainc.plum.mng.envPrpsl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * 환경교육제안 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.model
 * - EnvPrpslVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslVO
 * @Description : 환경교육제안 VO
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class EnvPrpslVO extends ParentRequestVo {

    private UserVo user;

    /** 제안아이디 */
    private Integer prpslid;

    /** 분류_코드 */
    private String clsfCd;

    /** 분류_코드명 */
    private String clsfCdNm;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 사용자아이디 */
    private Integer userid;

    /** 사용자계정 */
    private String acnt;

    /** 사용자이름 */
    private String userNm;

    /** 답변자계정 */
    private String ansAcnt;

    /** 답변자이름 */
    private String ansNm;

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

    private String searchClsfCd;

    private String searchPrcsSttsCd;

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
