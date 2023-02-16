package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* 회원가입 기관 VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberInstVo.java
* </pre> 
*
* @ClassName : MemberInstVo
* @Description : 회원가입 기관 VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 15.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class MemberInstVo extends ParentRequestVo {

    /** 기관 이름 */
    @NotEmpty(message = "기관명을 입력 해주십시오.")
    @Size(max = 100, message = "기관명은 100자를 초과할 수 없습니다.")
    private String instNm;

    /** 기관 유형 코드 */
    @NotEmpty(message = "기관유형을 입력 해주십시오.")
    private String instTypeCd;
    
    /** 기관 유형 이름 */
    private String instTypeNm;
        
    /** 사업자등록번호 */
    @NotEmpty(message = "사업자등록번호를 입력 해주십시오.")
    @Size(min = 10, max = 10, message = "사업자등록번호는 10자이어야 합니다.")
    private String brno;
    
    /** 대표자 이름 */
    @NotEmpty(message = "대표자 이름을 입력 해주십시오.")
    @Size(max = 20, message = "대표자 이름은 20자를 초과할 수 없습니다.")
    private String rprsvNm;
    
    /** 전화번호 */
    @NotEmpty(message = "대표전화번호를 입력 해주십시오.")
    @Size(max = 12, message = "대표전화번호는 12자를 초과할 수 없습니다.")
    private String telno;
    
    /** 팩스번호 */
    private String fxno;
    
    /** 우편번호 */
    @NotEmpty(message = "우편번호를 입력 해주십시오.")
    @Size(max = 10, message = "우편번호는 10자를 초과할 수 없습니다.")
    private String instZip;

    /** 주소 */
    @NotEmpty(message = "주소를 입력 해주십시오.")
    @Size(max = 200, message = "주소는 200자를 초과할 수 없습니다.")
    private String instAddr;
    
    /** 주소 상세 */
    @NotEmpty(message = "상세주소를 입력 해주십시오.")
    @Size(max = 400, message = "상세주소는 400자를 초과할 수 없습니다.")
    private String instAddrDtl;
    
    /** 시군구코드 */
    @NotEmpty(message = "시군구코드를 입력 해주십시오.")
    @Size(min = 5, max = 5, message = "시군구코드 형식이 올바르지 않습니다.")
    private String instSignguCd;
    
    /** 홈페이지 */
    private String hmpg;
    
    /** 사업자등록증 파일그룹아이디 */
    private Integer bizfileFilegrpid;
    
    /** 로고 파일아이디 */
    private Integer bizlogoFileid;

    public void setInstTypeCd(String instTypeCd) {
        this.instTypeCd = instTypeCd;
        
        //이미 코드이름이 있다면, 무시.
        if(CommonUtil.isEmpty(this.instTypeNm)) { 
            try {
                ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
                CodeInfoVo code = resCodeService.getCodeInfo(this.instTypeCd);
                this.instTypeNm = code.getCdNm();
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