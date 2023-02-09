package com.kbrainc.plum.front.envWord.service;

import java.util.List;

import com.kbrainc.plum.front.envWord.model.EnvWordVo;

/**
* 환경교육용어사전 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.front.envWord.service
* - EnvWordService.java
* </pre>
*
* @ClassName : EnvWordService
* @Description : 환경교육용어사전 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EnvWordService {
    
    /**
    * 환경교육용어사전 게시글 목록 조회
    *
    * @Title : selectEnvWordList
    * @Description : 환경교육용어사전 게시글 목록 조회
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return List<EnvWordVo>
    */
    public List<EnvWordVo> selectEnvWordList(EnvWordVo envWordVo) throws Exception;

    /**
    * 환경교육용어사전 게시글 상세정보
    *
    * @Title : selectEnvWordInfo
    * @Description : 환경교육용어사전 게시글 상세정보
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return EnvWordVo
    */
    public EnvWordVo selectEnvWordInfo(EnvWordVo envWordVo) throws Exception;
}
