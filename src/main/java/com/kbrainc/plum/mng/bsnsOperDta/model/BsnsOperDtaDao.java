package com.kbrainc.plum.mng.bsnsOperDta.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 사업운영자료 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.bsnsOperDta.model
 * - BsnsOperDtaDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaDao
 * @Description : 사업운영자료 Dao 인터페이스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface BsnsOperDtaDao {
    /**
     * 사업운영자료 목록 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    public List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 상세 조회
     *
     * @param bsnsOperDtaVo
     * @return bsns oper dta vo
     * @throws Exception
     * @Title : selectBsnsOperDta
     * @Description : 사업운영자료 상세 조회
     */
    public BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 등록
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : insertBsnsOperDta
     * @Description : 사업운영자료 등록
     */
    public int insertBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 수정
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : updateBsnsOperDta
     * @Description : 사업운영자료 수정
     */
    public int updateBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 삭제
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteBsnsOperDta
     * @Description : 사업운영자료 삭제
     */
    public int deleteBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 분류 매핑 등록
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : insertBsnsOperClsfMapping
     * @Description : 사업운영자료 분류 매핑 등록
     */
    public int insertBsnsOperDtaClsfMapping(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 분류 매핑 삭제
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteBsnsOperDtaClsfMapping
     * @Description : 사업운영자료 분류 매핑 삭제
     */
    public int deleteBsnsOperDtaClsfMapping(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;
}
