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

    /**
    * 내 정보 수정
    *
    * @Title : updateMyInfo
    * @Description : 사용자 정보 수정
    * @param memberVo
    * @param itrstfldCdEpty
    * @param envfldCdEpty
    * @throws Exception
    * @return int
    */
    public int updateMyInfo(MemberVo memberVo) throws Exception;

    /**
    * 비밀번호 변경
    *
    * @Title : updatePswd
    * @Description : 비밀번호 변경
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int updatePswd(MemberVo memberVo) throws Exception; 
}