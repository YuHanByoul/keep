package com.kbrainc.plum.mng.srng.model;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 심사 양식 문항 매핑 VO클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngFormQitemMapngVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngFormQitemMapngVO
 * @Description : 심사 양식 문항 매핑 VO클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class SrngFormQitemMapngVO extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 양식아이디 */
    private int formid;

    /** 문항아이디 */
    private int qitemid;

    /** 문항순서 */
    private String qitemOrdr;

    /** 체크리스트_구분_코드 */
    private String chklstSeCd;

    /** 지정기준_코드 */
    @NotEmpty(message = "지정기준을 선택해주세요.")
    private String dsgncrtrCd;

    /** 지정기준_코드명 */
    private String dsgncrtrCdNm;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private String mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private String rgtrid;

    /** 문항배열 */
    private String[] qitemArr;

    public void setDsgncrtrCd(String dsgncrtrCd) throws Exception{
        this.dsgncrtrCd = dsgncrtrCd;
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.dsgncrtrCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.dsgncrtrCd);
                this.dsgncrtrCdNm = code.getCdNm();
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
