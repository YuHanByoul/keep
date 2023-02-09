package com.kbrainc.plum.mng.expertPoolMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 전문가 섭외 현황 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertRelationMngDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertRelationMngDao
 * @Description : 전문가 섭외 현황 Dao 인터페이스
 * @date : 2023. 01. 05.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ExpertRelationMngDao {

    /**
     * 섭외 현황 목록 조회
     *
     * @param expertRelationVo
     * @return list
     * @throws Exception
     * @Title : selectExpertRelationList
     * @Description : 섭외 현황 목록 조회
     */
    public List<ExpertRelationVo> selectExpertRelationList(ExpertRelationVo expertRelationVo) throws Exception;

    /**
     * 섭외 상세 정보 조회
     *
     * @param expertRelationVo
     * @return expert relation vo
     * @throws Exception
     * @Title : selectExpertRelationInfo
     * @Description : 섭외 상세 정보 조회
     */
    public ExpertRelationVo selectExpertRelationInfo(ExpertRelationVo expertRelationVo) throws Exception;


}
