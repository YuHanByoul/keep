package com.kbrainc.plum.front.myInfo.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.member.model.MemberVo;

@Mapper("front.myInfoDao")
public interface MyInfoDao {

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

    public int updateMyInfo(MemberVo memberVo) throws Exception;
    
    public int insertEnvfld(MemberVo memberVo) throws Exception;

    public int deleteEnvfld(MemberVo memberVo) throws Exception;

    public int insertItrstfld(MemberVo memberVo) throws Exception;

    public int deleteItrstfld(MemberVo memberVo) throws Exception;

    public int updatePswd(MemberVo memberVo) throws Exception;
    
}