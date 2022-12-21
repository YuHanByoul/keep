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
 * SiteApplyMenuVo
 *
 * <pre>
 * com.kbrainc.plum.mng.site.model
 * - SiteApplyVo.java
 * </pre> 
 *
 * @ClassName : SiteApplyMenuVo Vo
 * @Description : SiteApplyMenuVo VO
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class SiteApplyMenuVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;

    /** 사이트 신청 아이디 */
    private int aplyid;
    
    /** 신청 메뉴 코드 */
    @Size(max = 20, message = "신청 메뉴 코드는 100자를 넘을 수 없습니다.")
    private String aplyMenuCd;
    
    /** 신청 메뉴 코드 명*/
    private String aplyMenuCdNm;
    
    /** 프로그램 아이디 : 옵션1*/
    private String prgrmid;
    
    /** 필수 여부 : 옵션2*/
    private String requiredYn;
    
    /** 표출여부 : 옵션3*/
    private String showYn;
    
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
    
    
    public void setAplySttsCd(String aplyMenuCd) throws Exception{
        this.aplyMenuCd = aplyMenuCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.aplyMenuCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplyMenuCd);
                this.aplyMenuCd = code.getCdNm();
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
