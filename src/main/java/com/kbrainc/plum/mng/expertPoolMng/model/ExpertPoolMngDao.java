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
    public int updateExpertStatus(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 로그 생성
     *
     * @param expertLogVo
     * @return boolean
     * @throws Exception
     * @Title : insertExpertLog
     * @Description : 전문가 로그 생성
     */
    public int insertExpertLog(ExpertLogVo expertLogVo) throws Exception;


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

    /**
     * 전문가 경력사항 조회
     *
     * @param expertVo
     * @return list
     * @throws Exception
     * @Title : selectExpertCareerList
     * @Description : 전문가 경력사항 조회
     */
    public List<ExpertCareerVo> selectExpertCareerList(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 자격증 조회
     *
     * @param expertVo
     * @return list
     * @throws Exception
     * @Title : selectExpertCrtfctList
     * @Description : 전문가 자격증 조회
     */
    public List<ExpertCrtfctVo> selectExpertCrtfctList(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 재직사항 조회
     *
     * @param expertVo
     * @return list
     * @throws Exception
     * @Title : selectExpertHdofList
     * @Description : 전문가 재직사항 조회
     */
    public List<ExpertHdofVo> selectExpertHdofList(ExpertVo expertVo) throws Exception;

    /**
     * 정보변경 테이블 존재 여부 조회
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : getMdfcnDmndCount
     * @Description : 정보변경 테이블 존재 여부 조회
     */
    public int getMdfcnDmndCount(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 요청 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnExprt
     * @Description : 전문가 수정 요청 테이블 생성
     */
    public int insertMdfcnExprt(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 경력 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnCareer
     * @Description : 전문가 수정 경력 생성
     */
    public int insertMdfcnCareer(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 자격증 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnCrtfct
     * @Description : 전문가 수정 자격증 생성
     */
    public int insertMdfcnCrtfct(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 재직 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnHdof
     * @Description : 전문가 수정 재직 생성
     */
    public int insertMdfcnHdof(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 대상 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnTrgtCds
     * @Description : 전문가 수정 대상 생성
     */
    public int insertMdfcnTrgtCds(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 주제 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnSbjctCds
     * @Description : 전문가 수정 주제 생성
     */
    public int insertMdfcnSbjctCds(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 지역 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnActvtRgnCds
     * @Description : 전문가 수정 지역 생성
     */
    public int insertMdfcnActvtRgnCds(ExpertVo expertVo) throws Exception;

    /**
     * 전문가 수정 범위 생성
     *
     * @param expertVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnActvtScopeCds
     * @Description : 전문가 수정 범위 생성
     */
    public int insertMdfcnActvtScopeCds(ExpertVo expertVo) throws Exception;
}
