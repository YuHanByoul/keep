package com.kbrainc.plum.mng.inst.model;

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
 * 사용자VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - MemberVo.java
 * </pre> 
 *
 * @ClassName : MemberVo
 * @Description : 사용자VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.member.model
* - InstVo.java
* </pre>
*
* @ClassName   : InstVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2021. 3. 11.
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class InstVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 기관 아이디 */
    private int instid;
    
    /** 기관 이름 */
    @Size(max = 100, message = "기관 이름은 100자를 넘을 수 없습니다.")
    private String instNm;
    
    /** 기관코드 */
    @Size(max = 20, message = "기관코드는 20자를 넘을 수 없습니다.")
    private String instCd;
    
    /** 기관유형코드 */
    @Size(max = 20, message = "기관유형코드는 20자를 넘을 수 없습니다.")
    private String instTypeCd;
    
    /** 기관유형코드명 */
    private String instTypeCdNm;
    
    /** 지역 코드 */
    private String rgnCd;
    
    /** 사업자번호 */
    @Size(max = 10, message = "사업자번호는 10자를 넘을 수 없습니다.")
    private String brno;
    
    /** 대표자이름 */
    @Size(max = 10, message = "대표자 이름은 10자를 넘을 수 없습니다.")
    private String rprsvNm;
    
    /** 전화번호 */
    @Size(max = 40, message = "전화번호는 40자를 넘을 수 없습니다.")
    private String telno;
    
    /** 팩스번호 */
    @Size(max = 40, message = "팩스번호는 40자를 넘을 수 없습니다.")
    private String fxno;
    
    /** 우편번호 */
    @Size(max = 10, message = "우편번호는 10자를 넘을 수 없습니다.")
    private String zip;
    
    /** 주소 */
    @Size(max = 200, message = "주소는 200자를 넘을 수 없습니다.")
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;
    
    /** 홈페이지 */
    @Size(max = 100, message = "홈페이지는 100자를 넘을 수 없습니다.")
    private String hmpg;
    
    /** 로고파일 아이디 */
    private Integer logoFileid;
    
    /** 첨부 파일 그룹아이디 */
    private Integer filegrpid;
    
    /** 승인 상태 코드 */
    @Size(max = 20, message = "승인상태 코드는 20자를 넘을 수 없습니다.")
    private String aprvSttsCd;
    
    /** 승인 상태 코드명 */
    private String aprvSttsCdNm;
    
    /** 사용여부 */
    @Pattern(regexp="[YN]")
    private String useYn;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    
    /*** 담당자명 ***/
    private String mngnms;
    
    /*** 사용자아이디 (삭제용) ***/
    private String[] userids;
    
    /** 역할 아이디 */
    private int roleid;
    
    /**** 검색용 파라메터 추가****/
    /** 기관유형 */
    private String searchInstCd;
    
    /** 승인 상태 코드 */
    private String searchApprovalSttsCd;
    
    /** MODE */
    private String mode;
    
    public void setInstTypeCd(String instTypeCd) throws Exception{
        this.instTypeCd = instTypeCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.instTypeCdNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.instTypeCd);
                this.instTypeCdNm = code.getCdNm();
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