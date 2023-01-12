package com.kbrainc.plum.mng.prtpn.cntst.model;

import com.kbrainc.plum.rte.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 공모전등록Dao 맵퍼 인터페이스.
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.model
 * - CntstDao.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstDao
 * @Description :
 * @date : 2023. 01. 12.
 * @Version : 공모전등록Dao 맵퍼 인터페이스.
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CntstDao {
    /**
     * 공모전 목록 조회
     * Title : selectCntstList
     * Description : 공모전 목록 조회
     *
     * @param cntstVO
     * @return list
     */
    List<CntstVO> selectCntstList(CntstVO cntstVO);

    /**
     * 공모전 등록
     * Title : insertCntst
     * Description : 공모전 등록
     *
     * @param cntstVO
     * @return int
     */
    int insertCntst(CntstVO cntstVO);

    /**
     * 공모전 수정
     * Title : updateCntst
     * Description : 공모전 수정
     *
     * @param cntstVO
     */
    void updateCntst(CntstVO cntstVO);

    /**
     * 공모전 정보 조회
     * Title : selectCntstInfo
     * Description : 공모전 정보 조회
     *
     * @param cntstId
     * @return cntst vo
     */
    CntstVO selectCntstInfo(Integer cntstId);

    /**
     * 공모전 분야 삭제
     * Title : deleteCntstFldMapng
     * Description : 공모전 분야 삭제
     *
     * @param cntstId
     */
    void deleteCntstFldMapng(Integer cntstId);

    /**
     * 공모전 분야 등록
     * Title : insertCntstFldMapng
     * Description : 공모전 분야 등록
     *
     * @param cntstId
     * @param usercntstfldCdArr
     * @param user
     */
    void insertCntstFldMapng(@Param("cntstId") Integer cntstId, @Param("cntstfldCdArr") String[] usercntstfldCdArr, @Param("user") UserVo user);

    /**
     * 공모전 분야 조회
     * Title : selectCntstFldCdList
     * Description : 공모전 분야 조회
     *
     * @param cntstId
     * @return list
     */
    List<String> selectCntstFldCdList(Integer cntstId);
}
