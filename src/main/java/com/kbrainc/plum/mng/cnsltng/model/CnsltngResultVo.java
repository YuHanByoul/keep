package com.kbrainc.plum.mng.cnsltng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class CnsltngResultVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;

    /** 컨설팅 아이디 */
    private int cnsltngid;
    
    /** 방문일자 */
    @Size(max = 10, message = "현장방문일은 10자를 넘을 수 없습니다.")
    private String vstDe;
    
    @Size(max = 100, message = "프로그램명은 100자를 넘을 수 없습니다.")
    private String prgrmNm;
    
    /** 컨성팅 종류 코드 */
    @Size(max = 20, message = "컨성팅 종류 코드은 20자를 넘을 수 없습니다.")
    private String cnsltngKndCd;
    
    /** 컨성팅 종류 코드 */
    @Size(max = 20, message = "컨성팅 종류 코드은 20자를 넘을 수 없습니다.")
    private String cnsltngKndCdNm;
    
    /** 희망일자1 */
    @Size(max = 10, message = "희망일자1은 10자를 넘을 수 없습니다.")
    private String hopeDe1;
    
    /** 희망일자2 */
    @Size(max = 10, message = "희망일자1은 10자를 넘을 수 없습니다.")
    private String hopeDe2;
    
    /** 신청일자 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date reqDt;
    
    /** 컨설턴트 이름  */
    private String cnstntNm;
    
    /** 방문일자 시작일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private String vstBgngDt;
    
    /** 방문일자 종료일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private String vstEndDt;
    
    /** 방문일자 시작일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date vstBgngDate;
    
    /** 방문일자 종료일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date vstEndDate;
    
    /** 종합의견*/
    private String gnrlzopnn;
    
    /** 우수성*/
    private String dstnctn;
    
    /** 우수성  비고 */
    @Size(max = 500, message = "우수성 비고는 500자를 넘을 수 없습니다.")
    private String dstnctnRmrk;
    
    /** 운영관리 */
    private String operMng;
    
    /** 운영관리  비고 */
    @Size(max = 500, message = "운영관리 비고는 500자를 넘을 수 없습니다.")
    private String operMngRmrk;
    
    /** 평가 체계  */
    private String evlSys;
    
    /** 평가 체계   비고 */
    @Size(max = 500, message = "평가 체계 비고는 500자를 넘을 수 없습니다.")
    private String evlSysRmrk;
    
    /** 지도자  */
    private String ldr;
    
    /** 지도자   비고 */
    @Size(max = 500, message = "지도자 비고는 500자를 넘을 수 없습니다.")
    private String ldrRmrk;
    
    /** 안전관리  */
    private String sftyMng;
    
    /** 안전관리 비고 */
    @Size(max = 500, message = "안전관리 비고는 500자를 넘을 수 없습니다.")
    private String sftyMngRmrk;
    
    /** 기타  */
    private String etc;
    
    /** 기타 비고 */
    @Size(max = 500, message = "안전관리 비고는 500자를 넘을 수 없습니다.")
    private String etcRmrk;
    
    /** 파일 그룹 아이디 */
    private Integer filegrpid;
    
    /** 임시 저장 여부 */
    @Pattern(regexp="[YN]")
    private String tmprSaveYn;
    
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
    
    /** 신청 기관명  */
    private String instNm;
    
    /** 신청자명 */
    private String userNm;
    
    /** 설문아이디 */
    private Integer srvyid;
    
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
}
