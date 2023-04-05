package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 전문가 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - ExprtVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtVo
 * @Description : 전문가 Vo 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.ExprtVo")
public class ExprtVo extends ParentRequestVo {
    private UserVo user;

    private Integer userid;

    private String exprtTypeCd;

    private String exprtTypeCdNm;

    private String fldLctrYn;

    private String fldPlanngYn;

    private String fldCnsltngYn;

    private String fldEtcYn;

    private String fldEtcCn;

    private String envEduCareerYy;

    private String envEduCareerMm;

    private String exprtTrgtCd;

    private String exprtSbjctCd;

    private String exprtActvtScopeCd;

    private String exprtTrgtCdNm;

    private String exprtSbjctCdNm;

    private String exprtActvtScopeCdNm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private Date mdfcnDt;

    private String moblphonRlsYn;

    private String telnoRlsYn;

    private String emlRlsYn;

    private String qlfcRlsYn;

    private String hdofRlsYn;

    private String careerRlsYn;

    private String nm;

    private String brdt;

    private String gndr;

    private String moblphon;

    private String telno;

    private String eml;

    private List<ExprtCareerVo> exprtCareerList;

    private List<ExprtCrtfctVo> exprtCrtfctList;

    private List<ExprtHdofVo> exprtHdofList;

    private String exprtField;

    private String lctrDmndYn;

    private String itrstYn;

    private Integer age;

    private String exprtCrtfctNm;

    private String exprtTypePath;

    private String flds;

    private String itsMe;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }

    public void setMoblphon(String moblphon) {
        this.moblphon = StringUtil.maskingMobilePhone(moblphon);
    }

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

    public void setExprtTrgtCd(String exprtTrgtCd) {
        this.exprtTrgtCd = exprtTrgtCd;

        if (CommonUtil.isEmpty(this.exprtTrgtCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtTrgtCd);
                this.exprtTrgtCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setExprtSbjctCd(String exprtSbjctCd) {
        this.exprtSbjctCd = exprtSbjctCd;

        if (CommonUtil.isEmpty(this.exprtSbjctCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtSbjctCd);
                this.exprtSbjctCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setExprtActvtScopeCd(String exprtActvtScopeCd) {
        this.exprtActvtScopeCd = exprtActvtScopeCd;

        if (CommonUtil.isEmpty(this.exprtActvtScopeCdNm)) {
            try {
                CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());
                List<Map<String, Object>> ctprvnList = commonService.selectCtprvnList();

                for (Map<String, Object> ctprvnInfo : ctprvnList) {
                    if (ctprvnInfo.get("CTPRVN_CD").equals(this.exprtActvtScopeCd)) {
                        this.exprtActvtScopeCdNm = (String) ctprvnInfo.get("CTPRVN_NM");
                        break;
                    }
                }

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
