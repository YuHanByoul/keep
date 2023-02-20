package com.kbrainc.plum.mng.pack.model;

import java.util.Date;
import java.util.List;

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
* com.kbrainc.plum.package.model
* - PackageVo.java
* </pre>
*
* @ClassName   : PackageVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.13
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class PackageVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 꾸러미 아이이디 */
    private int packageid;
    
    /** 꾸러미 개체 수량 */
    private Integer packageindvdCnt;
    
    /** 꾸러미명   */
    @Size(max = 200, message = "꾸러미명은 200자를 넘을 수 없습니다.")
    private String packageNm;
    
    /** 꾸러미 번호   */
    //@Size(max = 20, message = "꾸러미 번호는 20자를 넘을 수 없습니다.")
    //private String packageNo;
    
    /** 유형 코드   */
    @Size(max = 20, message = "유형 코드는 20자를 넘을 수 없습니다.")
    private String typeCd;
    
    /** 꾸러미 유형 코드명 */
    private String typeCdNm;
    
    /** 교육 유형 코드 */
    @Size(max = 20, message = "교구 유형 코드는 100자를 넘을 수 없습니다.")
    private String eduTypeCd;
    
    /** 교육 유형 코드명 */
    private String eduTypeCdNm;
    
    /** 모둠 구성 코드 */
    @Size(max = 20, message = "교구 유형 코드는 100자를 넘을 수 없습니다.")
    private String teamCmpstnCd;
    
    /** 모둠 구성 코드명 */
    private String teamCmpstnCdNm;
    
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private String mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자 아이디 */
    private String rgtrid;
    
    /****** 등록용****/
    /** 교육 주제 코드(s) */
    private String[] eduSbjctCds;
    
    /** 교육 대상 코드(s) */
    private String[] eduTrgtCds;
    
    /** 꾸러미 교구 리스트  */
    private List<PackageTchaidCmpstnVo> packageTchaidCmpstnList;
    
    /** 재고 추가분 */
    private Integer plusQnty;
    
    private String mode;
    
    /****** 추가 검색용****/
    /** 교육 유형 코드 */
    private String searchEduTypeCd;
    /** 교구 유형 코드 */
    private String searchTypeCd;
    /** 모둠 구성 코드 */
    private String searchTeamCmpstnCd;
    
    
    
    public void setTypeCd(String typeCd) throws Exception{
        this.typeCd = typeCd;
        
        //이미 코드이름이 있다면, 무시.
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
        
        //이미 코드이름이 있다면, 무시.
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
        
        //이미 코드이름이 있다면, 무시.
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