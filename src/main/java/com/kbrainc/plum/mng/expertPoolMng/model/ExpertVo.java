package com.kbrainc.plum.mng.expertPoolMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 전문가 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertVo
 * @Description : 전문가 Vo 클래스
 * @date : 2022. 12. 30.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertVo extends ParentRequestVo {
    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 사용자 아이디 */
    private Integer userid;

    /** 전문가_유형_코드 */
    private String exprtTypeCd;

    /** 분야_강의_여부 */
    private String fldLctrYn;

    /** 분야_기획_여부 */
    private String fldPlanngYn;

    /** 분야_컨설팅_여부 */
    private String fldCnsltngYn;

    /** 분야_기타_여부 */
    private String fldEtcYn;

    /** 분야_기타_내용 */
    private String fldEtcCn;

    /** 휴대폰_공개_여부 */
    private String moblphonRlsYn;

    /** 이메일_공개_여부 */
    private String emlRlsYn;

    /** 자격_공개_여부 */
    private String qlfcRlsYn;

    /** 재직_공개_여부 */
    private String hdofRlsYn;

    /** 경력_공개_여부 */
    private String careerRlsYn;

    /** 기업_강의_요청_수신_여부 */
    private String entLctrDmndRcptnYn;

    /** 강의_안내_수신_여부 */
    private String lctrGdncRcptnYn;

    /** 환경_교육_경력_년 */
    private int envEduCareerYy;

    /** 환경_교육_경력_월 */
    private int envEduCareerMm;

    /** 상태_코드 */
    private String sttsCd;

    /** 신청_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date aplyDt;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private String mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자 아이디 */
    private String rgtrid;

    /** 승인_일시 */
    private Date aprvDt;

    /** 반려_일시 */
    private Date rjctDt;

    /** 취소_일시 */
    private Date cnclDt;

    /** 전문가 유형 코드 이름 */
    private String expertTypeCdNm;

    /** 전문가 아이디 */
    private String acnt;

    /** 전문가 이름*/
    private String nm;

    /** 회원 상태 코드*/
    private String userSttsCd;

    /** 회원 상태 코드 이름*/
    private String userSttsCdNm;

    /** 전문가 상태 코드 이름*/
    private String expertSttsCdNm;

    /** 성별 */
    private String gndr;

    /** 생년월일 */
    private String brdt;

    /** 전화번호 */
    private String telno;

    /** 휴대폰번호 */
    private String moblphon;

    /** 이메일 */
    private String eml;

    /** 전문분야(대상) 코드 */
    private String exprtTrgtCd;

    /** 전문분야(환경주제) 코드 */
    private String exprtSbjctCd;

    /** 가능 활동범위 코드 */
    private String exprtActvtScopeCd;

    /** 주요활동지역 코드 */
    private String exprtActvtRgnCd;

    /** 전문분야(대상) 코드 이름 */
    private String exprtTrgtCdNm;

    /** 전문분야(환경주제) 코드 이름 */
    private String exprtSbjctCdNm;

    /** 가능 활동범위 코드 이름 */
    private String exprtActvtScopeCdNm;

    /** 주요활동지역 코드 이름 */
    private String exprtActvtRgnCdNm;

    /** 경력사항 리스트 */
    List<ExpertCareerVo> expertCareerList;

    /** 자격사항 리스트 */
    List<ExpertCrtfctVo> expertCrtfctList;

    /**재직사항 리스트 */
    List<ExpertHdofVo> expertHdofList;

    /** 검색용 필드 (전문분야) */
    private String exprtField;

    /**
     * 이용약관 동의 여부
     */
    private String tosAgreYn;

    /**
     * 개인정보 수집 동의 여부
     */
    private String prvcClctAgreYn;

    /**
     * 개인정보 제3자 제공 동의 여부
     */
    private String prvcThptyPvsnAgreYn;

    /* 저장용 */
    private String[] exprtTrgtArr;
    /* 저장용 */
    private String[] exprtActvtRgnArr;
    /* 저장용 */
    private String[] exprtActvtScopeArr;
    /* 저장용 */
    private String[] exprtSbjctArr;

    public void setSttsCd(String sttsCd) {
        this.sttsCd = sttsCd;

        if (CommonUtil.isEmpty(this.expertSttsCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.expertSttsCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }
    public void setExprtTypeCd(String exprtTypeCd) {
        this.exprtTypeCd = exprtTypeCd;

        //이미 코드이름이 있다면, 무시.
        if (CommonUtil.isEmpty(this.expertTypeCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtTypeCd);
                this.expertTypeCdNm = code.getCdNm();
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
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtActvtScopeCd);
                this.exprtActvtScopeCdNm = code.getCdNm();
            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setExprtActvtRgnCd(String exprtActvtRgnCd) {
        this.exprtActvtRgnCd = exprtActvtRgnCd;
        if (CommonUtil.isEmpty(this.exprtActvtRgnCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.exprtActvtRgnCd);
                this.exprtActvtRgnCdNm = code.getCdNm();
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
