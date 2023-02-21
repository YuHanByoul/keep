package com.kbrainc.plum.front.member.model;

import lombok.Data;

/**
* 회원가입 최초진입(회원가입유형 선택화면) VO 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberParamVo.java
* </pre> 
*
* @ClassName : MemberParamVo
* @Description : 회원가입 최초진입(회원가입유형 선택화면) VO 클래스
* @author : KBRAINC
* @date : 2023. 2. 20.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Data
public class MemberParamVo {
    
    /** 디지털원패스 사용자정보 인코딩데이터 */
    private String onepassEncodeData;
    
    /** 회원가입완료화면에서 로그인 버튼 클릭후 로그인 성공시 되돌아갈 URL */
    private String returnUrl;
}