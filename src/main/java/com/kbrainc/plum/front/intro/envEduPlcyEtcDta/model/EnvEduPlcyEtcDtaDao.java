package com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경교육 정책자료실 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model
 * - EnvEduPlcyEtcDtaDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyEtcDtaDao
 * @Description : 환경교육 정책자료실 Dao 클래스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.envEduPlcyEtcDtaDao")
public interface EnvEduPlcyEtcDtaDao {
    List<EtcDtaVo> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception;

    EtcDtaVo selectEtcDta(EtcDtaVo etcDtaVo) throws Exception;

    int updateEtcDtaHits(EtcDtaVo etcDtaVo) throws Exception;
}
