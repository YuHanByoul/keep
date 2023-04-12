package com.kbrainc.plum.mng.ntcn.model;

import java.util.Date;

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
 * 알림 VO
 *  
 * <pre>
 * com.kbrainc.plum.mng.pop.model
 * - NtcnVo.java
 * </pre> 
 *
 * @ClassName : NtcnVo
 * @Description : NtcnVo
 * @author : KBRAINC
 * @date : 2021. 2. 27.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class NtcnVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 알림아이디 */
    private Integer ntcnid;
    
    /** 사용자 아이디 */
    private Integer userid;
    
    /** 종류 코드*/
    @Size(max = 20, message = "종류 코드는 20자를 초과할 수 없습니다.")
    private String kndCd;
    
    /** 종류 코드명*/
    private String kndCdNm;
    
    /** 제목 */
    @Size(max = 200, message = "제목 200자를 초과할 수 없습니다.")
    private String ttl;
    
    /** 알림내용 */
    @Size(max = 500, message = "알림 내용은 20자를 초과할 수 없습니다.")
    private String cn;
    
    /** 이동URL */
    @Size(max = 500, message = "이동 URL은 500자를 초과할 수 없습니다.")
    private String mvmnurl;
    
    /** 이동URL */
    @Pattern(regexp="[YN]", message = "조회여부를 선택해주세요.")
    private String inqYn;
    
    /** 등록 일자 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    
    
    public void setKndCd(String kndCd) throws Exception{
        this.kndCd = kndCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.kndCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.kndCd);
                this.kndCdNm = code.getCdNm();
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