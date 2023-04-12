package com.kbrainc.plum.front.envLrnQlfcFnshHstry.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 환경학습 수료/자격 이력 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.envLrnQlfcFnshHstry.model
* - EnvLrnQlfcFnshHstryDao.java
* </pre>
*
* @ClassName : EnvLrnQlfcFnshHstryDao
* @Description : 환경학습 수료/자격 이력 Dao 클래스
* @author : JD
* @date : 2023. 4. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.envLrnQlfcFnshHstryDao")
public interface EnvLrnQlfcFnshHstryDao {

    /**
    * 수료정보 목록 조회
    *
    * @Title : selectFnshInfoList
    * @Description : 수료정보 목록 조회
    * @param envLrnQlfcFnshHstryVo
    * @return List<EnvLrnQlfcFnshHstryVo>
    */
    List<EnvLrnQlfcFnshHstryVo> selectFnshInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    /**
    * 수료정보 상세정보 조회
    *
    * @Title : selectFnshInfoDetail
    * @Description : 수료정보 상세정보 조회
    * @param envLrnQlfcFnshHstryVo
    * @return EnvLrnQlfcFnshHstryVo
    */
    EnvLrnQlfcFnshHstryVo selectFnshInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    /**
    * 자격정보 목록 조회
    *
    * @Title : selectQlfcInfoList
    * @Description : 자격정보 목록 조회
    * @param envLrnQlfcFnshHstryVo
    * @return List<EnvLrnQlfcFnshHstryVo>
    */
    List<EnvLrnQlfcFnshHstryVo> selectQlfcInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

    /**
    * 자격정보 상세정보 조회
    *
    * @Title : selectQlfcInfoDetail
    * @Description : 자격정보 상세정보 조회
    * @param envLrnQlfcFnshHstryVo
    * @return
    * @return EnvLrnQlfcFnshHstryVo
    */
    EnvLrnQlfcFnshHstryVo selectQlfcInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo);

}
