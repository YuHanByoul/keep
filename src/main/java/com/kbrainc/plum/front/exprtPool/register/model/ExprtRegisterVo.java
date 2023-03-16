package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 전문가 등록Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - ExprtRegisterVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterVo
 * @Description : 전문가 등록Vo 클래스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class ExprtRegisterVo {
    private UserVo user;

    /*이용약관_동의_여부*/
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String tosAgreYn;

    /*개인정보_수집_동의_여부*/
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String prvcClctAgreYn;

    /*개인정보_제3자_제공_동의_여부*/
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp = "[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String prvcThptyPvsnAgreYn;

    private Integer envEduCareerYy;
    private Integer envEduCareerMm;

    private String exprtTypeCd;
    private String fldLctrYn;
    private String fldPlanngYn;
    private String fldCnsltngYn;
    private String fldEtcYn;
    private String fldEtcCn;

    private String moblphonRlsYn;
    private String telnoRlsYn;
    private String emlRlsYn;
    private String qlfcRlsYn; //자격 공개 여부
    private String hdofRlsYn; //재직 공개 여부
    private String careerRlsYn; //경력 공개 여부
    private String entLctrDmndRcptnYn; // 강의요청 수신 여부
    private String lctrGdncRcptnYn;// 강의안내 수신 여부

    private String telno;
    private String brdt;

    List<HdofVo> hdofs;
    List<CrtfctVo> crtfcts;
    List<CareerVo> careers;
    private String trgtCds;
    private String sbjctCds;
    private String actvtRgnCds;
    private String actvtScopeCds;

    private String tempSaveYn;
    private String newYn;

    /*최초 신청 시 정보변경 테이블에 값을 생성하기 위한 용도 */
    private Integer mdfcnDmndId;
}
