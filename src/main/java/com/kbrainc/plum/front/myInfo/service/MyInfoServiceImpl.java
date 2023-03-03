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
    
}