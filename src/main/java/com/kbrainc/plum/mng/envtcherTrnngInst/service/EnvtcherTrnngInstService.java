package com.kbrainc.plum.mng.envtcherTrnngInst.service;

import java.util.List;

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.service
* - EnvtcherTrnngInstService.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstService
* @Description : TODO
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EnvtcherTrnngInstService {

    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

}
