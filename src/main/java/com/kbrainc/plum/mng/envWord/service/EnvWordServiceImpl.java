package com.kbrainc.plum.mng.envWord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.envWord.model.EnvWordDao;
import com.kbrainc.plum.mng.envWord.model.EnvWordVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육용어사전 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.envWord.service
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
@Service
public class EnvWordServiceImpl extends PlumAbstractServiceImpl implements EnvWordService{
    
    @Autowired
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
    * 환경교육용어사전 게시글 등록
    *
    * @Title : insertEnvWord
    * @Description : 환경교육용어사전 게시글 등록
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int insertEnvWord(EnvWordVo envWordVo) throws Exception {
        return envWordDao.insertEnvWord(envWordVo);
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
    
    /**
    * 환경교육용어사전 게시글 수정
    *
    * @Title : updateEnvWord
    * @Description : 환경교육용어사전 게시글 수정
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int updateEnvWord(EnvWordVo envWordVo) throws Exception {
        return envWordDao.updateEnvWord(envWordVo);
    }
    
    /**
    * 환경교육용어사전 게시글 삭제
    *
    * @Title : deleteEnvWord
    * @Description : 환경교육용어사전 게시글 삭제
    * @param nscvrgids 게시글 id값
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int deleteEnvWord(String[] nscvrgids) throws Exception {
        return envWordDao.deleteEnvWord(nscvrgids);
    }

}
