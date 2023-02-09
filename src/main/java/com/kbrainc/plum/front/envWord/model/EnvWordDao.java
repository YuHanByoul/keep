package com.kbrainc.plum.front.envWord.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 환경교육용어사전 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.envWord.model
* - EnvWordDao.java
* </pre>
*
* @ClassName : EnvWordDao
* @Description : 환경교육용어사전 Dao 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.envWordDao")
public interface EnvWordDao {
    
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
    * 환경교육용어사전 게시글 상세정보 조회
    *
    * @Title : selectEnvWordInfo
    * @Description : 환경교육용어사전 게시글 상세정보
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return EnvWordVo
    */
    public EnvWordVo selectEnvWordInfo(EnvWordVo envWordVo) throws Exception;
}
