package com.kbrainc.plum.mng.expertPoolMng.model;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

/**
 * 전문가 재직Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertHdofVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertHdofVo
 * @Description : 전문가 재직사항Vo 클래스
 * @date : 2023. 01. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertHdofVo {
    /** 재직 아이디 */
    private Integer exprtHdofId;
    /** 재직 구분 코드 */
    private String hdofSeCd;
    /** 재직 구분 코드 이름 */
    private String hdofSeCdNm;
    /** 기관 이름 */
    private String instNm;
    /** 부서 이름 */
    private String deptNm;
    /** 직급 이름 */
    private String jbgdNm;
    /** 재직 시작 일자 */
    private String hdofBgngDe;
    /** 재직 종료 일자 */
    private String hdofEndDe;
    /** 재직 여부*/
    private String hdofYn;
    /** 재직증명서 파일아이디 */
    private Integer hdofcrtfFileid;
    /** 순서 */
    private Integer ordr;

    public void setHdofSeCd(String hdofSeCd) {
        this.hdofSeCd = hdofSeCd;

        if (CommonUtil.isEmpty(this.hdofSeCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.hdofSeCd);
                this.hdofSeCdNm = code.getCdNm();
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
