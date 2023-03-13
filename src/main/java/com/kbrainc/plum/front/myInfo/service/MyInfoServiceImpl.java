package com.kbrainc.plum.front.myInfo.service;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.myInfo.model.MyInfoDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 내 정보 수정 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.myInfo.service
* - MyInfoServiceImpl.java
* </pre>
*
* @ClassName : MyInfoServiceImpl
* @Description : 내 정보 수정 Service 구현 클래스
* @author : JD
* @date : 2023. 2. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.myInfoServiceImpl")
@Alias("front.myInfoServiceImpl")
public class MyInfoServiceImpl extends PlumAbstractServiceImpl implements MyInfoService {

    @Resource(name = "front.myInfoDao")
    private MyInfoDao myInfoDao;
    
    /**
    * 사용자정보 조회.
    *
    * @Title       : selectMemberInfo 
    * @Description : 사용자정보 조회.
    * @param memberVo MemberVo객체
    * @return MemberVo MemberVo객체
    * @throws Exception 예외
    */
    @Override
    public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception {
        return myInfoDao.selectMemberInfo(memberVo);
    }
    
    
    /**
    * 내 정보 수정
    *
    * @Title : updateMyInfo
    * @Description : 사용자 정보 수정
    * @param memberVo
    * @param itrstfldCdEpty 맞춤환경정보 선택 값
    * @param envfldCdEpty 관심분야 선택 값
    * @throws Exception
    * @return int
    */
    @Override
    public int updateMyInfo(MemberVo memberVo, String itrstfldCdEpty, String envfldCdEpty) throws Exception {
        int retVal = 0;
        
        retVal += myInfoDao.updateMyInfo(memberVo);
        
        // 맞춤환경정보 입력
        if (itrstfldCdEpty.equals("Empty")) {
            retVal += myInfoDao.insertEnvfld(memberVo);
        }else {
            retVal += myInfoDao.deleteEnvfld(memberVo);
            retVal += myInfoDao.insertEnvfld(memberVo);
        }
        
        // 관심분야 입력
        if (envfldCdEpty.equals("Empty")) {
            retVal += myInfoDao.insertItrstfld(memberVo);
        }else {
            retVal += myInfoDao.deleteItrstfld(memberVo);
            retVal += myInfoDao.insertItrstfld(memberVo);
        }
        return retVal;
    }

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
    @Override
    public int updatePswd(MemberVo memberVo) throws Exception {
        return myInfoDao.updatePswd(memberVo);
    }
    
}