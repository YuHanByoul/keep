package com.kbrainc.plum.mng.envtcherTrnngInst.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.model
* - EnvtcherTrnngInstDao.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstDao
* @Description : TODO
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface EnvtcherTrnngInstDao {

    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;
    
    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;
}
