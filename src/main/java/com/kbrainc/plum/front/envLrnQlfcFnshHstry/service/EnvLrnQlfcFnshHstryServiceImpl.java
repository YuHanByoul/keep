package com.kbrainc.plum.front.envLrnQlfcFnshHstry.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryDao;
import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경학습 수료/자격 이력 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.envLrnQlfcFnshHstry.service
* - EnvLrnQlfcFnshHstryServiceImpl.java
* </pre>
*
* @ClassName : EnvLrnQlfcFnshHstryServiceImpl
* @Description : 환경학습 수료/자격 이력 Service 구현 클래스
* @author : JD
* @date : 2023. 4. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.envLrnQlfcFnshHstryServiceImpl")
@Alias("front.envLrnQlfcFnshHstryServiceImpl")
public class EnvLrnQlfcFnshHstryServiceImpl extends PlumAbstractServiceImpl implements EnvLrnQlfcFnshHstryService{
    
    @Resource(name = "front.envLrnQlfcFnshHstryDao")
    private EnvLrnQlfcFnshHstryDao envLrnQlfcFnshHstryDao;

    /**
    * 수료정보 목록 조회
    *
    * @Title : selectFnshInfoList
    * @Description : 수료정보 목록 조회
    * @param envLrnQlfcFnshHstryVo
    * @return List<EnvLrnQlfcFnshHstryVo>
    */
    @Override
    public List<EnvLrnQlfcFnshHstryVo> selectFnshInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectFnshInfoList(envLrnQlfcFnshHstryVo);
    }

    /**
    * 수료정보 상세정보 조회
    *
    * @Title : selectFnshInfoDetail
    * @Description : 수료정보 상세정보 조회
    * @param envLrnQlfcFnshHstryVo
    * @return EnvLrnQlfcFnshHstryVo
    */
    @Override
    public EnvLrnQlfcFnshHstryVo selectFnshInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectFnshInfoDetail(envLrnQlfcFnshHstryVo);
    }

    /**
    * 자격정보 목록 조회
    *
    * @Title : selectQlfcInfoList
    * @Description : 자격정보 목록 조회
    * @param envLrnQlfcFnshHstryVo
    * @return List<EnvLrnQlfcFnshHstryVo>
    */
    @Override
    public List<EnvLrnQlfcFnshHstryVo> selectQlfcInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectQlfcInfoList(envLrnQlfcFnshHstryVo);
    }

    /**
    * 자격정보 상세정보 조회
    *
    * @Title : selectQlfcInfoDetail
    * @Description : 자격정보 상세정보 조회
    * @param envLrnQlfcFnshHstryVo
    * @return
    * @return EnvLrnQlfcFnshHstryVo
    */
    @Override
    public EnvLrnQlfcFnshHstryVo selectQlfcInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectQlfcInfoDetail(envLrnQlfcFnshHstryVo);
    }
}
