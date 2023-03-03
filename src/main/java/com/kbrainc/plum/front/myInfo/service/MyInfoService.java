package com.kbrainc.plum.front.myInfo.service;

import com.kbrainc.plum.front.member.model.MemberVo;

public interface MyInfoService {
    
    /**
    * 사용자정보 조회.
    *
    * @Title       : selectMemberInfo 
    * @Description : 사용자정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberVo MemberVo객체
    * @throws Exception 예외
    */
    public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception;
    
    
}