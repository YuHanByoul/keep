package com.kbrainc.plum.mng.seeEnvEduInst.model;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 교육기관/시설관리 > 사회환경교육기관Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.seeEnvEduInst.model
 * - SeeEnvEduInstVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstVo
 * @Description : 교육기관/시설관리 > 사회환경교육기관Vo 클래스
 * @date : 2023. 04. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class SeeEnvEduInstVo extends ParentRequestVo {
    /**  신청아이디 */
    private Integer aplyid;

    /** 접수번호 */
    private String rcptno;

    /** 기관아이디 */
    private Integer instid;

    /** 신청자아이디 */
    private Integer aplcntid;

    /** 신청자_이름 */
    private String aplcntNm;

    /** 신청자_이메일 */
    private String aplcntEml;

    /** 신청자_휴대폰 */
    private String aplcntMoblphon;

    /** 기관_이름 */
    private String instNm;

    /** 기관 유형 */
    private String instTypeCd;

    /** 기관 유형 이름 */
    private String instTypeCdNm;

    /** 기관_이메일 */
    private String instEml;

    /** 기관_연락처 */
    private String instCntct;

    /** 시도_코드 */
    private String ctprvnCd;

    /** 시도_코드_이름 */
    private String ctprvnCdNm;

    /** 대표자_생년월일 */
    private String rprsvBrdt;

    /** 첨부_파일그룹아이디 */
    private Integer atchFilegrpid;

    /** 지정번호 */
    private String dsgnno;

    /** 지정_일자 */
    private String dsgnDe;

    /** 승인자아이디 */
    private Integer autzrid;

    /** 발급_일자 */
    private String issuDe;

    /** 재발급_일자 */
    private String isgnDe;

    /** 상태_코드 */
    private String sttsCd;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 기관 전화번호 */
    private String telno;

    /** 기관 대표자 이름 */
    private String rprsvNm;

    /** 기관 홈페이지 */
    private String hmpg;

    /** 검색용 파라미터 */
    private String searchCtprvn;

    /** 검색용 파라미터 */
    private String searchInstNm;

    /** 검색용 파라미터 */
    private String searchBgngDt;

    /** 검색용 파라미터 */
    private String searchEndDt;

    public void setCtprvnCd(String ctprvnCd) {
        this.ctprvnCd = ctprvnCd;

        if (CommonUtil.isEmpty(this.ctprvnCdNm)) {
            try {
                CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());
                List<Map<String, Object>> ctprvnList = commonService.selectCtprvnList();

                for (Map<String, Object> ctprvnInfo : ctprvnList) {
                    if (ctprvnInfo.get("CTPRVN_CD").equals(this.ctprvnCd)) {
                        this.ctprvnCdNm = (String) ctprvnInfo.get("CTPRVN_NM");
                        break;
                    }
                }

            } catch (NoClassDefFoundError e) {
                //e.printStackTrace();
                return;
            } catch (Exception e) {
                //e.printStackTrace();
                return;
            }
        }
    }

    public void setInstTypeCd(String instTypeCd) {
        this.instTypeCd = instTypeCd;

        if(CommonUtil.isEmpty(this.instTypeCdNm)) {
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.instTypeCd);
                this.instTypeCdNm = code.getCdNm();
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
