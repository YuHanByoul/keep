package com.kbrainc.plum.mng.ntcn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 알림 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.pack.service
 * - NtcnServiceImpl.java
 * </pre> 
 * @ClassName : NtcnServiceImpl
 * @Description : 알림 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 04. 12
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class NtcnServiceImpl extends PlumAbstractServiceImpl implements NtcnService { 

    @Autowired
    private NtcnDao ntcnDao;
    
    /**
    * 알림 등록
    *
    * @Title       : insertNtcn 
    * @Description : 단일 푸쉬 발송(비동기).
    * @param  Map
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public int insertNtcn (NtcnVo ntcnvo) throws Exception{
        return  ntcnDao.insertNtcn(ntcnvo);
    }
}
