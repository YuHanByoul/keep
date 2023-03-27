package com.kbrainc.plum.mng.lend.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.pack.model.PackageindvdTchaidCmpstnVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.lend.model
* - LendPackageindvdChkVo.java
* </pre>   
*
* @ClassName   : LendPackageindvdChkVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.20
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendPackageindvdChckVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    
    /** 점검  아이디 */
    private Integer chckid;
    
    /** 양식  아이디 */
    private Integer formid;
    
    /** 대여 신청  아이디 */
    private Integer aplyid;
    
    /** 대여 신청 차시 아이디 */
    private Integer rndid;
    
    /** 꾸러미 아이디 */
    private Integer packageid;
    
    /** 꾸러미 개체 아이디 */
    private Integer packageindvdid;
    
    /** 꾸러미 개체 아이디(s) */
    private String[] packageindvdids;
    
    /** 이상 아이디 */
    private Integer abnrmlid;
    
    /** 이상 아이디 */
    @Size(max = 500, message = "이상 내용은 500자를 초과할 수 없습니다.")
    private String cn;
    
    /** 점검 코드 */
    @Size(max = 20, message = "점검코드 20자를 넘을 수 없습니다.")
    private String chckCd;
    
    /** 점검 코드 명 */
    private String chckCdNm;
    
    /** 담당자 아이디 */
    private Integer picid;
    
    /** 점검일   */
    @Size(max = 10, message = "점검일은 10자를 넘을 수 없습니다.") 
    private String chckDe;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private Integer mdfrid;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /*****추가 *******/
    /** 답변 리스트 (등록용) */
    private List<LendPackageindvdChckAnsVo> lendPackageindvdChkAnsList ;
    
    /** 점검교구 리스트 (등록용) */
    private List<LendPackageindvdChckTchaidVo> lendPackageindvdChckTchaidList ;
    
    /** 개체 교구 구성품 보충 리스트  (등록용)*/
    private List<PackageindvdTchaidCmpstnVo> packageindvdTchaidCmpstnVoList;
    
    /** 둥록수정 모드 */
    private String modifyStts;
    
    public void setSttsCd(String chckCd) throws Exception{
        this.chckCd = chckCd;
        if(CommonUtil.isEmpty(this.chckCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.chckCd);
                this.chckCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                return ;
             }catch(Exception e) {
                return ;
             }
        }
    }
    
    
    
    
}