package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtCareerVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtCrtfctVo;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtHdofVo;
import com.kbrainc.plum.front.exprtPool.register.model.CareerVo;
import com.kbrainc.plum.front.exprtPool.register.model.CrtfctVo;
import com.kbrainc.plum.front.exprtPool.register.model.HdofVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 마이페이지 > 전문가Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - ExprtVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtVo
 * @Description : 마이페이지 > 전문가Vo 클래스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MyExprtVo extends CommonExprtVo {
    private String exprtTypeCdNm;

    private String moblphonRlsYn;
    private String telnoRlsYn;
    private String emlRlsYn;
    private String qlfcRlsYn; //자격 공개 여부
    private String hdofRlsYn; //재직 공개 여부
    private String careerRlsYn; //경력 공개 여부
    private String entLctrDmndRcptnYn; // 강의요청 수신 여부
    private String lctrGdncRcptnYn;// 강의안내 수신 여부
    private String sbjctCdNm;
    private String actvtScopeCdNm;
    private String trgtCdNm;
    private String actvtRgnCdNm;
    private String exprtField;

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
}
