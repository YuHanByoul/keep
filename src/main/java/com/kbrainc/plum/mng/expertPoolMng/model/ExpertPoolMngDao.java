package com.kbrainc.plum.mng.expertPoolMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 전문가 풀 관리 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertPoolMngDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertPoolMngDao
 * @Description : 전문가 풀 관리 Dao 인터페이스
 * @date : 2022. 12. 30.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ExpertPoolMngDao {
    /**
     * 전문가 목록 조회
     *
     * @param expertVo
     * @return list
     * @throws Exception
     * @Title : selectExpertList
     * @Description : 전문가 목록 조회
     */
    public List<ExpertVo> selectExpertList(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 회원 정보 조회
     *
     * @param expertVo
     * @return expert vo
     * @throws Exception
     * @Title : selectExpertInfo
     * @Description : 전문가 회원 정보 조회
     */
    public ExpertVo selectExpertInfo(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 상세 정보 조회
     *
     * @param expertVo
     * @return expert vo
     * @throws Exception
     * @Title : selectExpertApplyInfo
     * @Description : 전문가 상세 정보 조회
     */
    public ExpertVo selectExpertApplyInfo(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 상태 변경
     *
     * @param expertVo
     * @return boolean
     * @throws Exception
     * @Title : updateExpertStatus
     * @Description : 전문가 상태 변경
     */
    public boolean updateExpertStatus(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 로그 생성
     *
     * @param expertLogVo
     * @return boolean
     * @throws Exception
     * @Title : insertExpertLog
     * @Description : 전문가 로그 생성
     */
    public boolean insertExpertLog(ExpertLogVo expertLogVo) throws Exception;


    /**
     * 전문가 로그 조회
     *
     * @param expertLogVo
     * @return ExpertLogVo
     * @throws Exception
     * @Title : selectExpertLogList
     * @Description : 전문가 로그 조회
     */
    public List<ExpertLogVo> selectExpertLogList(ExpertLogVo expertLogVo) throws Exception;

    /**
     * 전문가 후기 이력 조회
     *
     * @param expertReviewHistoryVo
     * @return list
     * @throws Exception
     * @Title : selectExpertReviewHistoryList
     * @Description : 전문가 후기 이력 조회
     */
    public List<ExpertReviewHistoryVo> selectExpertReviewHistoryList(ExpertReviewHistoryVo expertReviewHistoryVo) throws Exception;

    /**
     * 전문가 후기 평균 별점 조회
     *
     * @param expertReviewHistoryVo
     * @return double
     * @throws Exception
     * @Title : getExpertReviewScrAvg
     * @Description : 전문가 후기 평균 별점 조회
     */
    public Double getExpertReviewScrAvg(ExpertReviewHistoryVo expertReviewHistoryVo) throws Exception;

    public List<ExpertCareerVo> selectExpertCareerList(ExpertVo expertVo) throws Exception;
    public List<ExpertCrtfctVo> selectExpertCrtfctList(ExpertVo expertVo) throws Exception;
    public List<ExpertHdofVo> selectExpertHdofList(ExpertVo expertVo) throws Exception;
}
