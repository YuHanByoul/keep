package com.kbrainc.plum.mng.rsvtAply.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.rsvtAply.model
* - RsvtAplyVo.java
* </pre>
*
* @ClassName   : RsvtAplyVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.01.09
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@NoArgsConstructor 
@Data
public class FclAplyRsvtdeVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 예약일자 아이이디 */
    private int aplyid;
    
    /** 예약일자 아이이디 */
    private Integer rsvtdeid;
      
    /** 공간 아이디 */
    private Integer spceid;
    
    /** 이용구분 코드  */
    @Size(max = 20, message = "이용구분 코드는 20자를 넘을 수 없습니다.")
    private String utztnSeCd;
    
    /** 이용구분 코드명  */
    private String utztnSeCdNm;
    
    /** 일자   */
    @Size(max = 10, message = "일자는 10자를 넘을 수 없습니다.")
    private String de;
    
    /** 시작일시   */
    private String bgngDt;
    
    /** 종료일시   */
    private String endDt;
    
    /** 시작일   */
    private String strtDe;
    
    /** 종료일   */
    private String endDe;
    
    /** 종일 여부 */
    @Pattern(regexp="[YN]")
    private String alldayYn;
    
    /** 금액 */
    private BigDecimal amt;
    
    /** 예약가능여부 */
    @Pattern(regexp="[YN]")
    private String rsvtPsbltyYn;
    
    /** 예약존재 여부  */
    @Pattern(regexp="[YN]")
    private String rsvtYn;
    
    /** 공간 번호 */
    @Size(max = 60, message = "공간 번호은 60자를 넘을 수 없습니다.")
    private String spceNo;
    
    /** 최대 인원수 */
    private Integer maxNope;
    
    /** 공간 번호 */
    @Size(max = 20, message = "크기는 20자를 넘을 수 없습니다.")
    private String size;
    
    /** 체크인 시간 */
    private String chkinHr;
    
    /** 체크 아웃 시간 */
    private String chcktHr;
    
    /** 일괄등록용 list */
    private List<SpceRsvtdeVo> rsvtdeList;
    
    /** 결제 코드 */
    private String stlmSttsCd;
    
    /** 상태 코드 */
    private String aplySttsCd;
    
    /** 결제 코드명 */
    private String stlmSttsCdNm;
    
    /** 상태 코드명 */
    private String aplySttsCdNm;
    
    /** 공간명 */
    private String spceNm;
    
    /** 기관 아이이디 */
    private int instid;
    
    /** 시설 아이이디 */
    private Integer fcltid;
    
    /** 상태 코드 */
    private String[] aplySttsCds;
    
    
    public void setUtztnSeCd(String utztnSeCd) throws Exception{
        this.utztnSeCd = utztnSeCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.utztnSeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.utztnSeCd);
                this.utztnSeCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
             }catch(Exception e) {
                //e.printStackTrace();
                return ;
             }
        }
    }
    public void setStlmSttsCd(String stlmSttsCd) throws Exception{
        this.stlmSttsCd = stlmSttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.stlmSttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.stlmSttsCd);
                this.stlmSttsCdNm = code.getCdNm();
            }catch(NoClassDefFoundError e) {
                //e.printStackTrace();
                return ;
            }catch(Exception e) {
                //e.printStackTrace();
                return ;
            }
        }
    }
    public void setAplySttsCd(String aplySttsCd) throws Exception{
        this.aplySttsCd = aplySttsCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.aplySttsCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplySttsCd);
                this.aplySttsCdNm = code.getCdNm();
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