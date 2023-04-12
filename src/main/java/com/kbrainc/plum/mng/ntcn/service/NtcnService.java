package com.kbrainc.plum.mng.ntcn.service;

import java.util.Map;

import com.kbrainc.plum.mng.ntcn.model.NtcnVo;

/**
 * <pre>
 * com.kbrainc.plum.cmm.service - NtcnService.java
 * </pre>
 *
 * @ClassName : NtcnService
 * @Description : 알림 등록 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2023. 04. 12.
 * @Version : 1.0
 * @Company : CopyrightⒸ KBRAINC. All Rights Reserved
 *
 */
public interface NtcnService {

    /**
    * 알림 등록
    *
    * @Title       : insertNtcn 
    * @Description : 단일 푸쉬 발송(비동기).
    * @param  Map
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public int insertNtcn (NtcnVo ntcnvo) throws Exception;
}
