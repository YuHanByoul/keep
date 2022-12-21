package com.kbrainc.plum.mng.siteApply.model;

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
public class SiteApplyVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;

    /** 사이트 신청 아이디 */
    private int aplyid;
    
    /** 기관 아이디 */
    private int instid;
    
    /** 기관 코드 */
    private String instCd;
    
    /** 기관_이름 */
    @NotEmpty(message = "기관명을 입력해주십시오.")
    @Size(max = 100, message = "기관명은 100자를 넘을 수 없습니다.")
    private String instNm;
    
    /** 기관_연락처 */
    @Size(max = 40, message = "기관연락처는 40자를 넘을 수 없습니다.")
    private String instCntct;
    
    /** 기관_이메일 */
    @Size(max = 200, message = "기관_이메일은 200자를 넘을 수 없습니다.")
    private String instEml;
    
    /** 사용자 아이디 */
    private int userid;
    
    /** 신청자 이름 */
    @Size(max = 20, message = "신청자 이름은 20자를 넘을 수 없습니다.")
    private String aplcntNm;
    
    /** 우편번호 */
    @Size(max = 10, message = "우편번호는 10자를 넘을 수 없습니다.")
    private String zip;
    
    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;
    
    /** 도메인 */
    @Size(max = 60, message = "도메인은 60자를 넘을 수 없습니다.")
    private String domn;
    
    /** 신청 내용 */
    @Size(max = 2000, message = "신청 내용은 2000자를 넘을 수 없습니다.")
    private String cn;
    
    /** 카피라이트 */
    @Size(max = 2000, message = "카피라이트는 2000자를 넘을 수 없습니다.")
    private String cpyrht;
    
    /** 신청일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date aplyDt;
    
    /** 신청 상태 코드  */
    @Size(max = 20, message = "신청 상태 코드는 20자를 넘을 수 없습니다.")
    private String aplySttsCd;
    
    /** 신청 상태 코드 명  */
    private String aplySttsCdNM;
    
    /** 승인자 아이디 */
    private int autzrid;
    
    /** 로고 파일 아이디 */
    private Integer logoFileid;
    
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
    
    /** 로고파일 식별 키  */
    private String fileIdntfcKey;
    
    /** 로고파일 식별 키  */
    private String aplyMenuCds;
    
    /*******검색용 추가 */
    /** 승인 상태 코드 */
    private String searchAplySttsCd;
    
    
    public void setAplySttsCd(String aplySttsCd) throws Exception{
        this.aplySttsCd = aplySttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.aplySttsCdNM)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplySttsCd);
                this.aplySttsCdNM = code.getCdNm();
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
