package com.kbrainc.plum.mng.srng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 심사양식관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngDao
 * @Description : 심사양식관리
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface SrngDao {

    /**
     * 심사 문항 정보 조회
     * Title : selectSrng
     * Description : 심사 문항 정보 조회
     *
     * @param srngQitemVO
     * @return srng qitem vo
     * @throws Exception
     */
    public SrngQitemVO selectSrng(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 목록 조회
     * Title : selectSrngList
     * Description : 심사 문항 목록 조회
     *
     * @param srngQitemVO SrngQitemVO
     * @return List<SrngQitemVO>  심사 문항 목록
     * @throws Exception
     */
    List<SrngQitemVO> selectSrngList(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 등록
     * Title : insertSrng
     * Description : 심사 문항 등록
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    int insertSrng(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 수정
     * Title : updateSrng
     * Description : 심사 문항 수정
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    int updateSrng(SrngQitemVO srngQitemVO) throws Exception;
}
