package com.kbrainc.plum.rte.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * 코드정보VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - CodeInfoVo.java
 * </pre> 
 *
 * @ClassName : CodeInfoVo
 * @Description : 코드정보VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CodeInfoVo extends ParentRequestVo implements Serializable {

    //코드정보
    //private ResCodeService resCodeService;

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
    private String cd_nm;

    /** 코드_설명 */
    @Size(max = 200, message = "코드설명은 200자를 넘을 수 없습니다.")
    private String cd_dc;

    /** 코드그룹아이디 */
    private String cdgrpid;

    /** 코드그룹명 */
    private String cdgrp_nm;

    /** 상위_코드 */
    private String uppr_cd;

    /** 상위_코드_이름 */
    private String uppr_cd_nm;

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
    private String use_yn;

    /** 대상코드 */
    private String tcd;

    /** jQuery dynatree hitMode */
    private String hitMode;

    /** upperYn */
    private String upperYn;

    /** 수정자아이디 */
    private Integer updtuserid;

    /** 수정자이름 */
    private String updt_nm;

    /** 수정_일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updt_d;

    /** 수정_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updt_dt;
    
    public void setCd(String cd) throws Exception{
        this.cd = cd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isNotEmpty(this.cd_nm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                if( resCodeService != null ) {
                    String cdkey = "CODE|" + this.cd;
                    //다른 코드들을 고려한다.(JSSFC, CERT, ADDR, INDUTY)
                    if(CommonUtil.isNotEmpty(this.cdgrpid)) {
                        cdkey = this.cdgrpid + "|" + this.cd;
                    }
                    CodeInfoVo code = resCodeService.getCodeInfo(cdkey);
                    this.cd_nm = code.getCd_nm();
                    this.uppr_cd = code.getUppr_cd();
                }
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    
    public void setCdgrpid(String cd) throws Exception{
        this.cdgrpid = cd;
        
        //코드값이 있고, 이름이 비었다면...
        if(CommonUtil.isNotEmpty(this.cd) && CommonUtil.isEmpty(this.cd_nm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                if( resCodeService != null ) {
                    CodeInfoVo code = resCodeService.getCodeInfo(this.cdgrpid + "|" + this.cd);
                    this.cd_nm = code.getCd_nm();
                    this.uppr_cd = code.getUppr_cd();
                }
            }catch(Exception e) {
                return ;
            }
        }
    }
    
    public String getUppr_cd_nm() {
        //이미 값이 있으면, 현재값을 리턴한다.
        if(CommonUtil.isNotEmpty(this.uppr_cd_nm)) {
            return this.uppr_cd_nm;
        }
        //uppr_cd코드가 없으면 ""을 리턴한다.
        if(CommonUtil.isEmpty(this.uppr_cd)) {
            return "";
        }
        
        //uppr_cd_nm를 구해서 저장 후 리턴한다.
        try {
            ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
            if( resCodeService != null ) {
                CodeInfoVo code = resCodeService.getCodeInfo(this.cdgrpid + "|" + this.uppr_cd);
                this.uppr_cd_nm = code.getCd_nm();
                return this.uppr_cd_nm;
            }
        }catch(Exception e) {
            return "";
        }
        
        return "";
    }
    
}