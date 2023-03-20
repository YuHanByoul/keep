package com.kbrainc.plum.mng.lend.model;

import java.util.Date;
import java.util.List;

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
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.lend.model
* - LendVo.java
* </pre>   
*
* @ClassName   : LendVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.20
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 꾸러미 아이디 */
    private int rcritid;
    
    /** 꾸러미 아이디 */
    private Integer packageid;
    
    /** 꾸러미(개체) 재고 */
    private Integer packageindvdCnt;
    
    /** 대여 모집명   */
    @Size(max = 100, message = "대여명은 100자를 넘을 수 없습니다.") 
    private String rcritNm;
    
    /** 접수일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bgngDt;
    
    /** 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date endDt;
    
    /** 접수일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String bgngDtTm;
    
    /** 종료일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String endDtTm;
    
    /** 제한 코드  */
    @Size(max = 20, message = "제한 코드는 20자를 넘을 수 없습니다.")
    private String lmtCd;
    
    /** 제한 코드 명 */
    private String lmtCdNm;
    
    /** 상세 내용  */
    private String dtlCn;
    
    /** 동영상 URL  */
    @Size(max = 500, message = "동영상 URL은 500자를 넘을 수 없습니다.")
    private String mvpUrl;
    
    /** 동영상_위치_코드  */
    @Size(max = 20, message = "동영상_위치_코드는 20자를 넘을 수 없습니다.")
    private String mvpPstnCd;
    
    /** 동영상_위치_코드 명 */
    private String mvpPstnCdNm;
    
    /**유의 사항 */
    private String atentMttr;
    
    /** 대표 이미지 파일 아이디 */
    private Integer rprsImgFileid;
    
    /**상세 이미지 파일그룹 아이디 */
    private Integer dtlImgFilegrpid;
    
    /** 지도 파일그룹 아이디 */
    private Integer mapFilegrpid;
    
    /** 교육사진  파일그룹 아이디 */
    private Integer eduPhotoFilegrpid;
    
    /** 운영여부 */
    @Pattern(regexp="[YN]")
    private String operYn;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 등록자 계정 */
    private String rgtrAcnt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 꾸러미 */
    /** 꾸러미명  */
    private String packageNm;
    /** 꾸러미 유형 코드  */
    private String typeCd;
    
    /** 꾸러미 유형 코드명  */
    private String typeCdNm;
    
    /** 교육 유형 코드  */
    private String eduTypeCd;
    
    /** 교육 유형 코드명  */
    private String eduTypeCdNm;
    
    /** 주제 코드명(S)  */
    private String eduSbjctCdNm;
    
    /** 교육 대상 코드명(S)  */
    private String eduTrgtCdNm;
    
    /** 모둠구성 코드 */
    private String teamCmpstnCd;
    
    /** 모둠구성 코드 명  */
    private String teamCmpstnCdNm;
     
    /****** 추가 검색용****/
    /** 대여 상태  코드 */
    private String searchRcritSttsCd;
    
    /** 검색 시작일 */
    private String searchStartDt;
    
    /** 검색 종료일 */
    private String searchEndDt;
    
    /** 등록용 대여 차시   */
    private List<LendRndVo> lendRndList ;
    
    /** 삭제용   */
    private List<Integer> deleteIds ;
    
    /** 등록용 대여 차시 꾸러미개체  */
    //private List<LendRndPackageindvdVo> lendRndPackageindvdList ;
    
    public void setLmtCd(String lmtCd) throws Exception{
        this.lmtCd = lmtCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.lmtCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.lmtCd);
                this.lmtCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    public void setTypeCd(String typeCd) throws Exception{
        this.typeCd = typeCd;
        if(CommonUtil.isEmpty(this.typeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.typeCd);
                this.typeCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    
    public void setEduTypeCd(String eduTypeCd) throws Exception{
        this.eduTypeCd = eduTypeCd;
        if(CommonUtil.isEmpty(this.eduTypeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.eduTypeCd);
                this.eduTypeCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    public void setTeamCmpstnCd(String teamCmpstnCd) throws Exception{
        this.teamCmpstnCd = teamCmpstnCd;
        if(CommonUtil.isEmpty(this.teamCmpstnCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.teamCmpstnCd);
                this.teamCmpstnCdNm = code.getCdNm();
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