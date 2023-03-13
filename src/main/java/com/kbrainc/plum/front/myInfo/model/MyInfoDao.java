package com.kbrainc.plum.front.myInfo.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.member.model.MemberVo;

/**
* 내 정보 수정 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.myInfo.model
* - MyInfoDao.java
* </pre>
*
* @ClassName : MyInfoDao
* @Description : 내 정보 수정 Dao 클래스
* @author : JD
* @date : 2023. 2. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
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

    /**
    * 내 정보 수정
    *
    * @Title : updateMyInfo
    * @Description : 내 정보 수정
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int updateMyInfo(MemberVo memberVo) throws Exception;
    
    /**
    * 맞춤환경정보 등록
    *
    * @Title : insertEnvfld
    * @Description : 맞춤환경정보 등록
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int insertEnvfld(MemberVo memberVo) throws Exception;

    /**
    * 맞춤환경정보 삭제
    *
    * @Title : deleteEnvfld
    * @Description : 맞춤환경정보 삭제
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int deleteEnvfld(MemberVo memberVo) throws Exception;

    /**
    * 관심분야 등록
    *
    * @Title : insertItrstfld
    * @Description : 관심분야 등록
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int insertItrstfld(MemberVo memberVo) throws Exception;

    /**
    * 관심분야 삭제
    *
    * @Title : deleteItrstfld
    * @Description : 관심분야 삭제
    * @param memberVo
    * @throws Exception
    * @return int
    */
    public int deleteItrstfld(MemberVo memberVo) throws Exception;

    /**
    * 비밀번호 변경
    *
    * @Title : updatePswd
    * @Description : 비밀번호 변경
    * @param memberVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updatePswd(MemberVo memberVo) throws Exception;
    
}