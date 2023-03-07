package com.kbrainc.plum.front.myInfo.service;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.front.myInfo.model.MyInfoDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

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

    @Override
    public int updatePswd(MemberVo memberVo) throws Exception {
        return myInfoDao.updatePswd(memberVo);
    }
    
}