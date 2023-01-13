package com.kbrainc.plum.mng.spce.model;

import java.util.Date;

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
 * 사용자VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spce.model
 * - SpceVo.java
 * </pre> 
 *
 * @ClassName : SpceVo
 * @Description : 공간 VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.spce.model
* - InstVo.java
* </pre>
*
* @ClassName   : SpceVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.01.09
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class SpceVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 공간 아이디 */
    private int spceid;
    
    /** 시설 아이디 */
    private Integer fcltid;
    
    /** 공간 이름 */
    @Size(max = 100, message = "공간 이름은 100자를 넘을 수 없습니다.")
    private String spceNm;
    
    /** 공간 번호 */
    @Size(max = 60, message = "공간 번호은 60자를 넘을 수 없습니다.")
    private String spceNo;
    
    /** 최대 인원수 */
    private Integer maxNope;
    
    /** 공간 사이즈 (평수)*/
    @Size(max = 20, message = "크기는 20자를 넘을 수 없습니다.")
    private String size;
    
    /** 체크인 시간 */
    private String chkinHr;
    
    /** 체크 아웃 시간 */
    private String chcktHr;
    
    /** 체크인 시간 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date chkinHour;
    
    /** 체크 아웃 시간 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date chcktHour;
    
    /** 익일 체크아웃 가능 여부 */
    @Pattern(regexp="[YN]")
    private String nxtyChcktYn;
    
    /** 예약 가능일수*/
    private Integer rsvtPsbltyDaycnt;
    
    /** 당일 예약 가능여부 */
    @Pattern(regexp="[YN]")
    private String todayRsvtPsbltyYn;
    
    /** 사용여부 */
    @Pattern(regexp="[YN]")
    private String useYn;
    
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
    
    /** 기관 아이디 */
    private Integer instid;
    
    /** 시설명 */
    private String fcltNm;
    
    /** 시설 번호 */
    private String fcltNo;
    
    /** 시설 주소 */
    private String addr;
    
    /** 시설 상세 주소 */
    private String addrDtl;
    
    /** 시설 예약 신청 방법 코드  */
    private String aplyMthdCd;
    
    /** 시설 예약 신청 방법 코드 명 */
    private String aplyMthdCdNm;
    
    /** 시도 구분 코드 */
    private String rgnCd;
    
    /** 기관 명 */
    private String instNm;
    
    /** mode */
    private String mode;
    
    /** 시도 명 */
    private String ctprvnNm;
    
    /** 삭제용 파라메터*/
    private String[] spceids;
    
    /***********검색용 추가***********/
    /** 지역 코드*/
    private String searchSiGunGuCd;
    
    /** 검색용 사용여부 */
    private String searchUseYn;
    
    /** 검색용 예약 신청 코드 */
    private String searchAplyMthdCd;
    
    public void setAplyMthdCd(String aplyMthdCd) throws Exception{
        this.aplyMthdCd = aplyMthdCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.aplyMthdCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.aplyMthdCd);
                this.aplyMthdCdNm = code.getCdNm();
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