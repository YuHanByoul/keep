package com.kbrainc.plum.front.envWord.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.envWord.model.EnvWordDao;
import com.kbrainc.plum.front.envWord.model.EnvWordVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육용어사전 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.envWord.service
* - EnvWordServiceImpl.java
* </pre>
*
* @ClassName : EnvWordServiceImpl
* @Description : 환경교육용어사전 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.envWordServiceImpl")
@Alias("front.envWordServiceImpl")
public class EnvWordServiceImpl extends PlumAbstractServiceImpl implements EnvWordService{
    
    @Resource(name = "front.envWordDao")
    private EnvWordDao envWordDao;
    
    /**
    * 환경교육용어사전 게시글 목록 조회
    *
    * @Title : selectEnvWordList
    * @Description : 환경교육용어사전 게시글 목록 조회
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return List<EnvWordVo>
    */
    @Override
    public List<EnvWordVo> selectEnvWordList(EnvWordVo envWordVo) throws Exception{
        return envWordDao.selectEnvWordList(envWordVo);
    }
    
    /**
    * 환경교육용어사전 게시글 상세정보
    *
    * @Title : selectEnvWordInfo
    * @Description : 환경교육용어사전 게시글 상세정보
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return EnvWordVo
    */
    @Override
    public EnvWordVo selectEnvWordInfo(EnvWordVo envWordVo) throws Exception {
        return envWordDao.selectEnvWordInfo(envWordVo);
    }

}
