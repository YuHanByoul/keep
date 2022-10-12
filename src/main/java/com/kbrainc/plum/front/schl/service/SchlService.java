package com.kbrainc.plum.front.schl.service;

import java.util.List;

import com.kbrainc.plum.front.schl.model.SchlVo;

/**
* 학교정보 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.front.schl.service
* - MemberService.java
* </pre> 
*
* @ClassName : MemberService
* @Description : 회원정보 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
public interface SchlService {
	
    /**
    *
    * 학교리스트 호출 
    *
    * @Title : selectSchlList
    * @Description : 
    * @param ShclVo ShclVo객체
    * @return List<ShclVo> 
    * @throws Exception 예외
    */
   public List<SchlVo> selectSchlList(SchlVo schlVo) throws Exception;
	
	
}