package com.kbrainc.plum.mng.srng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 심사 문항 VO클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngQitemVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngQitemVO
 * @Description : 심사 문항 VO클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class SrngQitemVO extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 문항아이디 */
    private int qitemid;

    /** 지정기준_코드 */
    @NotEmpty(message = "지정기준을 선택해주세요.")
    private String dsgncrtrCd;

    /** 지정기준_코드명 */
    private String dsgncrtrCdNm;

    /** 확인_사항 */
    @NotEmpty(message = "확인사항을 입력해주세요.")
    @Size(max = 200, message = "확인사항은 200자를 넘을 수 없습니다.")
    private String idntyMttr;

    /** 배점 */
    @NotEmpty(message = "배점을 입력해주세요.")
    private int altm;

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;

    /** 수정자아이디 */
    private int mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;

    /** 등록자아이디 */
    private int rgtrid;

    /** 지정기준검색 */
    private String searchDsgncrtrCd;

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
