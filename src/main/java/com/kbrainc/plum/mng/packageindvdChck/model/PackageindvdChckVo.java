package com.kbrainc.plum.mng.packageindvdChck.model;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * 꾸러미개체 점검Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.model
 * - PackageindvdChckVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckVo
 * @Description : 꾸러미개체 점검Vo 클래스
 * @date : 2023. 03. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class PackageindvdChckVo extends ParentRequestVo {
    /** 점검 아이디 */
    private Integer chckid;
    /** 양식 아이디 */
    private Integer formid;
    /** 신청 아이디 */
    private Integer aplyid;
    /** 차시 아이디 */
    private Integer rndid;
    /** 꾸러미개체 아이디 */
    private Integer packageindvdid;
    /** 이상 아이디 */
    private Integer abnrmlid;
    /** 점검 코드 */
    private String chckCd;
    /** 담당자 아이디 */
    private Integer picid;
    /** 담당자 이름 */
    private String picNm;
    /** 점검 일자 */
    private String chckDe;
    /** 수정 일시 */
    private Date mdfcnDt;
    /** 수정자 아이디 */
    private Integer mdfrid;
    /** 등록 일시 */
    private Date regDt;
    /** 등록자 아이디 */
    private Integer rgtrid;
    /** 꾸러미개체 이름 */
    private String packageindvdNm;
    /** 점검 코드 이름 */
    private String chckCdNm;
    /** 개체 코드 */
    private String indvdno;
    /** 대여모집 차시 */
    private Integer ordr;
    /** 대여모집 이름 */
    private String rcritNm;
    /** 기관 이름 */
    private String instNm;
    /** 신청자 이름 */
    private String aplcntNm;

    public void setChckCd(String chckCd) {
        this.chckCd = chckCd;

        if(CommonUtil.isEmpty(this.chckCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.chckCd);
                this.chckCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
}
