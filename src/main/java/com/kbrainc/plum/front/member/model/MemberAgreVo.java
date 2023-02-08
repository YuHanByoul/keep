package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 회원가입 약관동의 VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberTypeVo.java
* </pre> 
*
* @ClassName : MemberTypeVo
* @Description : 회원가입 약관동의 VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 7.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class MemberAgreVo {
    
    /** 회원가입 유형 */
    @NotEmpty(message = "회원가입 유형을 선택해주세요.")
    @Pattern(regexp="[PCI]", message = "회원가입 유형을 선택해주세요.")
    private String type;
    
    /** 이용약관 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp="[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String tosAgreYn;
    
    /** 개인정보수집및이용 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp="[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String privcyAgreYn;
    
    /** 개인정보 제3자 제공 동의 여부 */
    @NotEmpty(message = "필수항목을 동의해야 회원가입이 가능합니다.")
    @Pattern(regexp="[Y]", message = "필수항목을 동의해야 회원가입이 가능합니다.")
    private String prvcThptyPvsnAgreYn;
    
    /** 어린이 가입 동의 여부 */
    private String childJoinAgreYn;
    
    /** 웹진 신청 동의 여부 */
    @Pattern(regexp="[YN]", message = "올바른 값을 입력해주세요.")
    private String wbznAplyAgreYn = "N";
    
    /** 회원가입완료호면에서 로그인 버튼 클릭후 로그인 성공시 되돌아갈 URL */
    private String returnUrl;
    
    /** 화면에 띄울 alert메시지 */
    private String alertMsg;

}