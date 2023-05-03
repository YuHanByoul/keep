package com.kbrainc.plum.front.envReqst.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.Data;

/**
 * 교육시설 신청 VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.envReqst.model
 * - EnvReqstVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvReqstVo
 * @Description : 교육시설 신청 VO 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Data
@Alias("front.EnvReqstVo")
public class EnvReqstVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 시설명 */
    private String fcltNm;

    /** 공간명 */
    private String spceNm;

    /** 공간id */
    private Integer spceid;

    /** 주소 */
    private String addr;

    /** 주소상세 */
    private String addrDtl;
    
    /** 우편번호 */
    private String zip;

    /** 신청방법코드 */
    private String aplyMthdCd;

    /** 신청방법명 */
    private String aplyMthdCdNm;

    /** 기관아이디 */
    private String instid;

    /** 기관명 */
    private String instNm;

    /** 대표이미지 파일키 */
    private String fileIdntfcKey;

    /** 대표 이미지 파일아이디 */
    private Integer rprsImgFileid;
    
    /** 대표 이미지 파일 키 */
    private String rprsImgFileKey;

    /** 상세 이미지 파일그룹아이디 */
    private Integer dtlImgFilegrpid;

    /** 안내 파일아이디 */
    private Integer gdncFileid;

    /** 시설 아이디 */
    private Integer fcltid;

    /** 시설 은행코드 */
    private String bankCd;

    /** 시설 은행코드명 */
    private String bankCdNm;
    
    /** 입금 은행코드 */
    private String dpstBankCd;
    
    /** 입금 은행코드명 */
    private String dpstBankCdNm;

    /** 상세 내용 */
    private String dtlCn;

    /** 계좌번호 */
    private String bacntNo;

    /** 예금주 이름 */
    private String dpstrNm;

    /** rsvtdeid */
    private Object rsvtdeid;

    /** 예약일자 */
    private String de;

    /** 금액 */
    private BigDecimal amt;

    /** 예약가능여부 */
    @Pattern(regexp="[YN]")
    private String rsvtPsbltyYn;

    private String alldayYn;

    /** 예약id리스트 */
    private String[] rsvtdeids;

    /** 예약구분코드 */
    private String utztnSe;

    /** 이용구분코드 */
    private String utztnSeCd;
    
    /** 이용구분코드명 */
    private String utztnSeCdNm;

    /** 시작일시  */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date bgngDt;

    /** 종료일시  */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String startDt;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String enddDt;

    /** 신청자명  */
    private String aplcntNm;

    /** 신청자 휴대전화  */
    private String aplcntMoblphon;

    /** 입금자명  */
    private String pyrNm;

    /** 신청자 이메일 */
    private String aplcntEml;

    /** 신청 성인 인원*/
    private Integer nopeAdult;

    /** 신청 아동 인원*/
    private Integer nopeChil;

    /** 신청 유아 인원 */
    private Integer nopeInfnt;

    /** 신청 아이디 */
    private Integer aplyid;

    /** 신청자 아이디 */
    private String aplcntid;

    /** 입금 계좌 */
    private String dpstBacnt;

    /** 입금 계좌 */
    private String utztnPrps;

    /** 이용 목적 */
    @Size(max = 200, message = "이용 목적은 200자를 넘을 수 없습니다.")
    private String aplyDt;

    /** 최대 인원수 */
    private Integer maxNope;

    /** 외부 Url */
    private String extnlUrl;

    /** 일괄등록용 list */
    private List<EnvReqstVo> rsvtdeList;

    private int fullYn;

    /** 시작일시   */
    private String strtTm;

    /** 종료일시   */
    private String endTm;

    /** 후기 내용   */
    @Size(max = 1000, message = "후기 내용은 1000자를 넘을 수 없습니다.")
    private String rvwCn;
    
    /** 후기 점수    */
    private int rvwScr;
    
    /** 후기 등록 일자     */
    private Date rvwDt;

    /** 계정 */
    private String acnt;

    /** 마스킹 계정 */
    private String maskAcnt;

    /** 예약 가능 일수 */
    private String rsvtPsbltyDaycnt;
    
    /** 당일 예약 여부 */
    private String todayRsvtPsbltyYn;

    /** 당일 예약 여부 */
    private ArrayList<FileVo> fileMap;

    private Integer currentFileCnt;

    /** 대표 이미지 맵 */
    private FileVo rprsImgFileMap;

    /** 검색 관련*/
    private String searchSeCd;
    private String searchKeyword;
    private String searchRgnCd;
    private String searchAplyMthdCd;
    private String searchBgngDt;
    private String searchEndDt;
    private String searchSiGunGuCd;
    private String searchAplySttsCd;
    private String searchStlmSttsCd;
    
    public void setAplyMthdCd(String aplyMthdCd) throws Exception{
        this.aplyMthdCd = aplyMthdCd;
        
        if(CommonUtil.isEmpty(this.aplyMthdCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplyMthdCd);
                this.aplyMthdCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setDpstBankCd(String dpstBankCd) throws Exception{
        this.dpstBankCd = dpstBankCd;
        
        if(CommonUtil.isEmpty(this.dpstBankCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.dpstBankCd);
                this.dpstBankCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setBankCd(String bankCd) throws Exception{
        this.bankCd = bankCd;
        
        if(CommonUtil.isEmpty(this.bankCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.bankCd);
                this.bankCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setUtztnSeCd(String utztnSeCd) throws Exception{
        this.utztnSeCd = utztnSeCd;
        
        if(CommonUtil.isEmpty(this.utztnSeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.utztnSeCd);
                this.utztnSeCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public void setAcnt(String acnt) throws Exception{
        this.maskAcnt = StringUtil.maskingAccount(acnt); 
    }
    
}
