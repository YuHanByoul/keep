package com.kbrainc.plum.mng.spcltyDta.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 전문자료 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.spcltyDta.model
 * - SpcltyDtaDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaDao
 * @Description : 전문자료 Dao 인터페이스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface SpcltyDtaDao {
    /**
     * 전문자료 목록 조회
     *
     * @param spcltyDtaVo
     * @return list
     * @throws Exception
     * @Title : selectSpcltyDtaList
     * @Description : 전문자료 목록 조회
     */
    List<SpcltyDtaVo> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 상세 조회
     *
     * @param spcltyDtaVo
     * @return spclty dta vo
     * @throws Exception
     * @Title : selectSpcltyDta
     * @Description : 전문자료 상세 조회
     */
    public SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 등록
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : insertSpcltyDta
     * @Description : 전문자료 등록
     */
    int insertSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 수정
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : updateSpcltyDta
     * @Description : 전문자료 수정
     */
    int updateSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 삭제
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteSpcltyDta
     * @Description : 전문자료 삭제
     */
    int deleteSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 분류 매핑 등록
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : insertSpcltyDtaClsfMapping
     * @Description : 전문자료 분류 매핑 등록
     */
    public int insertSpcltyDtaClsfMapping(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 분류 매핑 삭제
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteBsnsOperClsfMapping
     * @Description : 전문자료 분류 매핑 삭제
     */
    public int deleteSpcltyDtaClsfMapping(SpcltyDtaVo spcltyDtaVo) throws Exception;

}
