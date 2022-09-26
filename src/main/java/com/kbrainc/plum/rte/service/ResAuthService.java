package com.kbrainc.plum.rte.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.rte.model.RoleInfoVo;

/**
* 
* 자원에 따른 역할ID를 메모리에 적재하는 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.rte.service
* - ResAuthService.java
* </pre> 
*
* @ClassName : ResAuthService
* @Description : 자원에 따른 역할ID를 메모리에 적재하는 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
public interface ResAuthService {

    /**
    * 캐시를 삭제한다.
    *
    * @Title       : removeCacheForAuth 
    * @Description : 캐시를 삭제한다.
    * @param siteid 사이트아이디
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void removeCacheForAuth() throws Exception;
}
