package com.kbrainc.plum.front.mypage.exprtPool.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 *
 * 정보변경이력 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyMdfcnHistoryDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyMdfcnHistoryDao
 * @Description : 정보변경이력 Dao 인터페이스
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface MyMdfcnHistoryDao {
    /**
     * 정보변경이력 목록 조회
     *
     * @param myMdfcnHistoryVo
     * @return list
     * @throws Exception
     * @Title : selectMdfcnHistoryList
     * @Description : 정보변경이력 목록 조회
     */
    public List<MyMdfcnHistoryVo> selectMdfcnHistoryList(MyMdfcnHistoryVo myMdfcnHistoryVo) throws Exception;

    public String selectMdfcnRsnByDmndId(Integer mdfcnDmndId) throws Exception;

    public String selectLogRsnByDmndId(Integer mdfcnDmndId) throws Exception;
}
