package com.kbrainc.plum.front.envtcherTrnngInst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경교육사 양성기관 현황 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.envtcherTrnngInst.model
 * - EnvtcherTrnngInstDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvtcherTrnngInstDao
 * @Description : 환경교육사 양성기관 현황 Dao
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper("front.EnvtcherTrnngInstDao")
public interface EnvtcherTrnngInstDao {

    /**
     * 환경교육사 양성기관 현황 목록 화면
     * Title : selectEnvtcherTrnngInstSituList
     * Description : 환경교육사 양성기관 현황 목록 화면
     *
     * @return list
     */
    List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstSituList();
}
