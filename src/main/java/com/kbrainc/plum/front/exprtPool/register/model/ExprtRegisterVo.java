package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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

    private String envEduCareerYy;
    private String envEduCareerMm;

    private String exprtTypeCd;
    private String fldLctrYn;
    private String fldPlanngYn;
    private String fldCnsltngYn;
    private String fldEtcYn;
    private String fldEtcCn;
    private String[] trgt;
    private String[] sbjct;
    private String[] actvtRgn;
    private String[] actvtScope;
    private String moblphonRlsYn;
    private String telnoRlsYn;
    private String emlRlsYn;
    private String qlfcRlsYn; //자격 공개 여부
    private String hdofRlsYn; //재직 공개 여부
    private String careerRlsYn; //경력 공개 여부
    private String entLctrDmndRcptnYn; // 강의요청 수신 여부
    private String lctrGdncRcptnYn;// 강의안내 수신 여부

    private String telNo;
    private String brdt;

    /*
      재직사항 프로퍼티
    */
    private String hdofSeCd;
    private String instNm;
    private String deptNm;
    private String jbgdNm;
    private String hdofBgngDe;
    private String hdofEndDe;
    private String hdofYn;
    private Integer hdofcrtfFileid;
    private String ordr1;

    /*
      자격취득사항 프로퍼티
    */
    private String crtfctNm;
    private String acqsInst;
    private String acqsNo;
    private String acqsGrd;
    private String acqsDe;
    private String crtfctFileid;
    private String ordr2;

    /*
      경력사항 프로퍼티
    */
    private String fldNm;
    private String actvtBgngDe;
    private String actvtEndDe;
    private String actvtYn;
    private String actvtHr;
    private String idntyInstNm;
    private String actvtCn;
    private String idntyDe;
    private String crtfFileid;
    private String artclassFileid;
    private String ordr3;

}
