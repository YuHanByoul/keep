package com.kbrainc.plum.mng.seeEnvEduInst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 교육기관/시설관리 > 사회환경교육기관 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.seeEnvEduInst.model
 * - SeeEnvEduInstDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstDao
 * @Description : 교육기관/시설관리 > 사회환경교육기관 Dao 인터페이스
 * @date : 2023. 04. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface SeeEnvEduInstDao {
    /**
     * 교육기관/시설관리 > 사회환경교육기관 목록 조회
     *
     * @param seeEnvEduInstVo
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 교육기관/시설관리 > 사회환경교육기관 목록 조회
     */
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList(SeeEnvEduInstVo seeEnvEduInstVo) throws Exception;
}
