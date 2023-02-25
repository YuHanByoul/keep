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
    List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo);

    BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo);

    List<SpcltyDtaVo> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo);

    SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo);
}
