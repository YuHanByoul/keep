package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
* 회원가입 기관검색 VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberInstSearchVo.java
* </pre> 
*
* @ClassName : MemberInstSearchVo
* @Description : 회원가입 기관검색 VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 14.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class MemberInstSearchVo extends ParentRequestVo {
    
    /** 항목아이디 */
    private Integer artclid;
  
    /** 기관 이름 */
    private String instNm;

    /** 기관 유형 코드 */
    private String instTypeCd;
    
    /** 기관 유형 이름 */
    private String instTypeNm;
        
    /** 사업자등록번호 */
    private String brno;
    
    /** 대표자 이름 */
    private String rprsvNm;
    
    /** 전화번호 */
    private String telno;
    
    /** 팩스번호 */
    private String fxno;
    
    /** 우편번호 */
    private String zip;

    /** 주소 */
    private String addr;
    
    /** 주소 상세 */
    private String addrDtl;
    
    /** 시군구코드 */
    private String signguCd;

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