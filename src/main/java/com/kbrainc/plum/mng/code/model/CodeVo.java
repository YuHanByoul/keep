package com.kbrainc.plum.mng.code.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * 코드VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.code.model
 * - CodeVo.java
 * </pre> 
 *
 * @ClassName : CodeVo
 * @Description : 코드VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CodeVo extends ParentRequestVo implements Serializable {

    /**  */
	private static final long serialVersionUID = 1L;

	/** 로그인사용자정보 */
    private UserVo user;

    /** 코드 */
    @NotEmpty(message = "코드를 입력해 주십시오.")
    @Size(max = 6, message = "코드는 6자를 넘을 수 없습니다.")
    private String cd;

    /** 코드_이름 */
    @NotEmpty(message = "코드명을 입력해 주십시오.")
    @Size(max = 200, message = "코드명은 200자를 넘을 수 없습니다.")
    private String cdNm;

    /** 코드_설명 */
    @Size(max = 200, message = "코드설명은 200자를 넘을 수 없습니다.")
    private String cdDc;

    /** 코드그룹아이디 */
    private String cdgrpid;

    /** 코드그룹명 */
    private String cdgrpNm;

    /** 상위_코드 */
    private String upprCd;

    /** 상위_코드_이름 */
    private String upprCdNm;

    /** 순서 */
    private Integer ord;

    /** 옵션1 */
    @Size(max = 200, message = "옵션1은 200자를 넘을 수 없습니다.")
    private String optn1;

    /** 옵션2 */
    @Size(max = 200, message = "옵션2는 200자를 넘을 수 없습니다.")
    private String optn2;
    
    /** 옵션3 */
    @Size(max = 200, message = "옵션3는 200자를 넘을 수 없습니다.")
    private String optn3;

    /** 사용_여부 */
    private String useYn;

    /** 대상코드 */
    private String tcd;

    /** jQuery dynatree hitMode */
    private String hitMode;

    /** upperYn */
    private String upperYn;

    /** 수정자아이디 */
    private Integer updtuserid;

    /** 수정자이름 */
    private String updtNm;

    /** 수정_일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updtD;

    /** 수정_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mdfcnDt;
    
    public void setCd(String cd) throws Exception{
        this.cd = cd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isNotEmpty(this.cdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                    String cdkey = "CODE|" + this.cd;
                    //다른 코드들을 고려한다.(JSSFC, CERT, ADDR, INDUTY)
                    if(CommonUtil.isNotEmpty(this.cdgrpid)) {
                        cdkey = this.cdgrpid + "|" + this.cd;
                    }
                    CodeInfoVo code = resCodeService.getCodeInfo(cdkey);
                    this.cdNm = code.getCdNm();
                    this.upprCd = code.getUpprCd();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    
    public void setCdgrpid(String cd) throws Exception{
        this.cdgrpid = cd;
        
        //코드값이 있고, 이름이 비었다면...
        if(CommonUtil.isNotEmpty(this.cd) && CommonUtil.isEmpty(this.cdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                    CodeInfoVo code = resCodeService.getCodeInfo(this.cdgrpid + "|" + this.cd);
                    this.cdNm = code.getCdNm();
                    this.upprCd = code.getUpprCd();
            }catch(NoClassDefFoundError e) {
                return ;
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public String getUpprCdNm() {
        //이미 값이 있으면, 현재값을 리턴한다.
        if(CommonUtil.isNotEmpty(this.upprCdNm)) {
            return this.upprCdNm;
        }
        //uppr_cd코드가 없으면 ""을 리턴한다.
        if(CommonUtil.isEmpty(this.upprCd)) {
            return "";
        }
        
        //uppr_cd_nm를 구해서 저장 후 리턴한다.
        try {
            ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.cdgrpid + "|" + this.upprCd);
                this.upprCdNm = code.getCdNm();
                return this.upprCdNm;
        }catch(NoClassDefFoundError e) {
            return "";
        }catch(Exception e) {
            return "";
        }
    }
    
    
    public void setmdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public Date getmdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public void setUpdtD(Date updtD) {
        this.updtD = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    public Date getUpdtD() {
        return updtD != null ? (Date) updtD.clone() : null;
    }
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   

    
}