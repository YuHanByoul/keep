package com.kbrainc.plum.front.ntcn.service;

import java.util.List;

import com.kbrainc.plum.front.ntcn.model.NtcnVo;

/**
 * 
 * 알림 사용자 서비스 
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.service
 * - NtcnService.java
 * </pre> 
 *
 * @ClassName : NtcnService
 * @Description : 알림 사용자 서비스
 * @author : KBRAINC
 * @date : 2021. 2. 27.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface NtcnService {
	
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림리스트(5개) 호출 
     * @param NtcnVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public List<NtcnVo> selectMainNtcnList(NtcnVo NtcnVo) throws Exception;
    
}