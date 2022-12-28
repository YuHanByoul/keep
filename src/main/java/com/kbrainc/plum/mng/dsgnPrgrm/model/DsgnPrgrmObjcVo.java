package com.kbrainc.plum.mng.dsgnPrgrm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;

/**
 * 지정프로그램 이의신청 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.model
 * - DsgnPrgrmObjcVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DsgnPrgrmObjcVo
 * @Description : 지정프로그램 이의신청 Vo 클래스
 * @date : 2022. 12. 28.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class DsgnPrgrmObjcVo extends ParentRequestVo {
    /**
     * 프로그램 아이디
     */
    private Integer prgrmid;

    /**
     * 이의신청 아이디
     */
    private Integer aplyid;

    /**
     * 제목
     */
    private String ttl;

    /**
     * 내용
     */
    private String cn;

    /**
     * 신청 상태 코드
     */
    private String aplySttsCd;

    /**
     * 신청 상태 코드 이름
     */
    private String aplySttsCdNm;

    /**
     * 등록일
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date regDt;

    /**
     * 수정일
     */
    private Date mdfcnDt;

    /*완료처리일 필요*/


    public void setAplySttsCd(String aplySttsCd) throws Exception {
        this.aplySttsCd = aplySttsCd;
        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.aplySttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplySttsCd);
                this.aplySttsCdNm = code.getCdNm();
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
