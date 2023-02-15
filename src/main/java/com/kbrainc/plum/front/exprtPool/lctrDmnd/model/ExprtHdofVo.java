package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("front.ExprtHdofVo")
public class ExprtHdofVo {
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
    /**재직 기간*/
    private String hdofDe;
    /** 재직 여부*/
    private String hdofYn;
    /** 재직증명서 파일아이디 */
    private Integer hdofcrtfFileid;
    /** 순서 */
    private Integer ordr;

    private FileVo hdofCrtfFile;

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