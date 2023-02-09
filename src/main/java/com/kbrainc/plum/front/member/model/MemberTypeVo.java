package com.kbrainc.plum.front.member.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 회원가입 회원가입유형 VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberTypeVo.java
* </pre> 
*
* @ClassName : MemberTypeVo
* @Description : 회원가입 회원가입유형 VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 3.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class MemberTypeVo {
    
    /** 회원가입 유형 */
    @NotEmpty(message = "회원가입 유형을 선택해주세요.")
    @Pattern(regexp="[PCI]", message = "회원가입 유형을 선택해주세요.")
    private String type;
    
    /** 회원가입완료호면에서 로그인 버튼 클릭후 로그인 성공시 되돌아갈 URL */
    private String returnUrl;

    public String getTosAgreYn() {
        return null;
    }

    public String getPrivcyAgreYn() {
        return null;
    }

    public String getPrvcThptyPvsnAgreYn() {
        return null;
    }

    public String getChildJoinAgreYn() {
        return null;
    }

    public String getWbznAplyAgreYn() {
        return null;
    }
    
    public String getAlertMsg() {
        return null;
    }
}