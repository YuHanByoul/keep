package com.kbrainc.plum.front.lend.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.lend.model.LendDao;
import com.kbrainc.plum.front.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교구 대여 (사용자) 서비스    
 *
 * <pre>
 * com.kbrainc.plum.front.lend.service
 * - LendServiceImpl.java
 * </pre> 
 *
 * @ClassName : LendServiceImpl
 * @Description :교구 대여 (사용자) 서비스
 * @author : KBRAINC
 * @date : 2021. 03.03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("front.lendServiceImpl")
@Alias("front.lendServiceImpl")
public class LendServiceImpl extends PlumAbstractServiceImpl implements LendService { 

	@Resource(name = "front.lendDao")
	LendDao lendDao;
	
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림리스트(5개) 호출 
     * @param NtcnVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    //public List<NtcnVo> selectMainNtcnList(NtcnVo NtcnVo) throws Exception{
    //    return lendDao.selectMainNtcnList(NtcnVo);
    //}
}
