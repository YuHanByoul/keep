package com.kbrainc.plum.mng.cnsltng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 * 
 * SiteApplyVO
 *
 * <pre>
 * com.kbrainc.plum.mng.site.model
 * - SiteApplyVo.java
 * </pre> 
 *
 * @ClassName : SiteApplyVo Vo
 * @Description : SiteApplyVo VO
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CnsltngVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;

    /** 컨설팅 아이디 */
    private int cnsltngid;
    
    /** 기관 아이디 */
    private Integer instid;
    
    /** 기관 이름  */
    private String instNm;
    
    /** 기관 담당자 이름*/
    private String instManagerNm;
    
    /** 기관 담당자  전화번호 */
    private String instManagerPhon;
    
    /**기관 담당자  이메일 */
    private String instManagerEml;
    
    /** 기관 전화번호*/
    private String instTelno;
    
    /** 기관 유형 코드*/
    private String instTypeCd;
    
    /** 기관 유형 코드*/
    private String instTypeCdNm;
    
    /** 기관 담당자  전화번호 */
    private String picTelno;
    
    /**기관 담당자  이메일 */
    private String picEml;
    
    /** 사용자 아이디 */
    private Integer userid;
    
    /** 사용자 이름 */
    @Size(max = 60, message = "사용자 이름은 60자를 넘을 수 없습니다.")
    private String userNm;
    
    /** 프로그램 이름 */
    @Size(max = 100, message = "프로그램명은 100자를 넘을 수 없습니다.")
    private String prgrmNm;
    
    /** 컨성팅 종류 코드 */
    @Size(max = 20, message = "컨성팅 종류 코드은 20자를 넘을 수 없습니다.")
    private String cnsltngKndCd;
    
    /** 컨성팅 종류 코드 명 */
    private String cnsltngKndCdNm;
    
    /** 희망일자1 */
    @Size(max = 10, message = "희망일자1은 10자를 넘을 수 없습니다.")
    private String hopeDe1;
    
    /** 희망일자1 오전 오후 코드 */
    @Size(max = 20, message = "희망일자1 오전 오후 코드는 20자를 넘을 수 없습니다.")
    private String hopeDe1AmPmCd;
    
    /** 희망일자1 오전 오후 코드 명*/
    private String hopeDe1AmPmCdNm;
    
    /** 희망일자2 */
    @Size(max = 10, message = "희망일자1은 10자를 넘을 수 없습니다.")
    private String hopeDe2;
    
    /** 희망일자2 오전 오후 코드 */
    @Size(max = 20, message = "희망일자2 오전 오후 코드는 20자를 넘을 수 없습니다.")
    private String hopeDe2AmPmCd;
    
    /** 희망일자2 오전 오후 코드 명*/
    private String hopeDe2AmPmCdNm;
    
    /** 프로그램 */
    @Size(max = 2000, message = "프로그램은 2000자를 넘을 수 없습니다.")
    private String prgrm;
    
    /** 지도자 */
    @Size(max = 2000, message = "지도자는 2000자를 넘을 수 없습니다.")
    private String ldr;
    
    /** 안전관리 */
    @Size(max = 2000, message = "안전관리는 2000자를 넘을 수 없습니다.")
    private String sftyMng;
    
    /** 기타 */
    @Size(max = 2000, message = "기타 2000자를 넘을 수 없습니다.")
    private String etc;
    
    /** 파일 그룹 아이디 */
    private Integer filegrpid;
    
    /** 컨설턴트 아이디 */
    private Integer cnstntid;
    
    /** 컨설턴트 이름  */
    private String cnstntNm;
    
    /** 상태 코드 */
    @Size(max = 20, message = "상태코드는 20자를 넘을 수 없습니다.")
    private String sttsCd;
    
    /** 상태 코드명 */
    private String sttsCdNm;
    
    /** 설문아이디 */
    private Integer srvyid;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 방문 일시 */
    private String vstDe;
    
    /** 방문 시작 시각 */
    private String vstBgngDt;
    
    /** 방문 종료 시각 */
    private String vstEndDt;
    
    /** 담당자 계정 */
    private String cnstntAcnt;
    
    /** 담당자 핸드폰 */
    private String cnstntMoblphon;
    
    /** 담당자 이메일 */
    private String cnstntEml;
    
    /** 컨설턴트 ids */
    private List<String> cnstntids;
    
    /** 컨설턴트 등록시 그룹 등록여부 확인용 */
    private String grpYn;
    
    /** 컨설턴트 등록시 그룹 확인용 */
    private Integer grpId;
    
    /** 기관 주소 */
    private String addr;
    
    /** 기관 주소 상세 */
    private String addrDtl;
    
    /******** 검색용 ********/
    /** 프로그램 명 */
    private String searchPrgrmNm;
    
    /** 기관명 */
    private String searchInstNm;
    
    /** 신청일 시작 일자 */
    private String searchRegStrtDt;
    
    /** 신청일 종료 일자 */
    private String searchRegEndDt;
    
    /** 컨설팅 유형 */
    private String searchCsltngKndCd;
    
    /** 진행상태 */
    private String searchSttsCd;
    
    /** 방문일 시작 일자 */
    private String searchHopeStrtDt;
    
    /** 방문일 종료 일자 */
    private String searchHopeEndDt;

    
    public void setSttsCd(String sttsCd) throws Exception{
        this.sttsCd = sttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.sttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.sttsCd);
                this.sttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    
    public void setCnsltngKndCd(String cnsltngKndCd) throws Exception{
        this.cnsltngKndCd = cnsltngKndCd;
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.cnsltngKndCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.cnsltngKndCd);
                this.cnsltngKndCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    
    public void setInstTypeCd(String instTypeCd) throws Exception{
        this.instTypeCd = instTypeCd;
        //이미 코드이름이 있다면, 무시.
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
