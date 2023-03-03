package com.kbrainc.plum.front.intro.envEduPlcyDta.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경교육 정책자료실 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.model
 * - EnvEduPlcyDtaDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyDtaDao
 * @Description : 환경교육 정책자료실 Dao 클래스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.envEduPlcyDtaDao")
public interface EnvEduPlcyDtaDao {
    List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    List<SpcltyDtaVo> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception;

    SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    int updateBsnsOperDtaHits(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;
    int updateSpcltyDtaHits(SpcltyDtaVo spcltyDtaVo) throws Exception;
}
