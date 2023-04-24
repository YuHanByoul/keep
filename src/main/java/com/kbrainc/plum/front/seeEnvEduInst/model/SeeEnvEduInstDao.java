package com.kbrainc.plum.front.seeEnvEduInst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 교육기관/시설 > 사회환경교육기관 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.seeEnvEduInst.model
 * - SeeEnvEduInstDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstDao
 * @Description : 교육기관/시설 > 사회환경교육기관 Dao 인터페이스
 * @date : 2023. 04. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.seeEnvEduInstDao")
public interface SeeEnvEduInstDao {
    /**
     * 사회환경교육기관 기관 현황 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 사회환경교육기관 기관 현황 조회
     */
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList() throws Exception;
}
