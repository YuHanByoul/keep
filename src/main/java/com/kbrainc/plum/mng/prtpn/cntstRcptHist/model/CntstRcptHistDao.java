package com.kbrainc.plum.mng.prtpn.cntstRcptHist.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 공모전 접수내역Dao 맵퍼 인터페이스.
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.model
 * - CntstRcptHistDao.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistDao
 * @Description : 공모전 접수내역Dao 맵퍼 인터페이스.
 * @date : 2023. 01. 12.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CntstRcptHistDao {

    /**
     * 공모전 접수 이력 목록 조회
     * Title : selectCntstAplyList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstAplyVO
     * @return list
     */
    List<CntstAplyVO> selectCntstAplyList(CntstAplyVO cntstAplyVO);

    /**
     * 공모전 접수 정보 조회
     * Title : selectCntstAplyInfo
     * Description : 공모전 접수 정보 조회
     *
     * @param aplyid
     * @return CntstRcptHistVO
     */
    CntstAplyVO selectCntstAplyInfo(Integer aplyid);

    /**
     * 환경방학 일기장 프로젝트 접수 정보 조회
     * Title : selectCntstAplySchlList
     * Description : 환경방학 일기장 프로젝트 접수 정보 조회
     *
     * @param aplyid
     * @return list
     */
    List<CntstAplySchlVO> selectCntstAplySchlList(Integer aplyid);
}
