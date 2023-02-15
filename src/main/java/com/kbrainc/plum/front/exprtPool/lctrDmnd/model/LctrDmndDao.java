package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경교육 전문가 풀 > 섭외 요청 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - LctrDmndDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndDao
 * @Description : 환경교육 전문가 풀 > 섭외 요청 Dao 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.lctrDmndDao")
public interface LctrDmndDao {
    /**
     * 전문가 목록 조회
     *
     * @param searchVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    public List<ExprtVo> selectExprtList(ExprtVo searchVo) throws Exception;

    /**
     * 전문가 상세 조회
     *
     * @param exprtVo
     * @return exprt vo
     * @throws Exception
     * @Title : selectExprt
     * @Description : 전문가 상세 조회
     */
    public ExprtVo selectExprt(ExprtVo exprtVo) throws Exception;

    /**
     * 전문가 재직 목록 조회
     *
     * @param exprtVo
     * @return list
     * @throws Exception
     * @Title : selectExprtHdofList
     * @Description : 전문가 재직 목록 조회
     */
    public List<ExprtHdofVo> selectExprtHdofList(ExprtVo exprtVo) throws Exception;

    /**
     * 전문가 자격 목록 조회
     *
     * @param exprtVo
     * @return list
     * @throws Exception
     * @Title : selectExprtCrtfctList
     * @Description : 전문가 자격 목록 조회
     */
    public List<ExprtCrtfctVo> selectExprtCrtfctList(ExprtVo exprtVo) throws Exception;

    /**
     * 전문가 경력 목록 조회
     *
     * @param exprtVo
     * @return list
     * @throws Exception
     * @Title : selectExprtCareerList
     * @Description : 전문가 경력 목록 조회
     */
    public List<ExprtCareerVo> selectExprtCareerList(ExprtVo exprtVo) throws Exception;

    /**
     * 전문가 섭외 요청 등록
     *
     * @param lctrDmndVo
     * @return int
     * @throws Exception
     * @Title : insertLctrDmnd
     * @Description : 전문가 섭외 요청 등록
     */
    public int insertLctrDmnd(LctrDmndVo lctrDmndVo) throws Exception;
}
