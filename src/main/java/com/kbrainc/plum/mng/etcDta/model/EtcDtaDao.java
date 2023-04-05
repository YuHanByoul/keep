package com.kbrainc.plum.mng.etcDta.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 기타자료 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.etcDta.model
 * - EtcDtaDao.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : EtcDtaDao
 * @Description : 기타자료 Dao 인터페이스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface EtcDtaDao {
    /**
     * 기타자료 목록 조회
     *
     * @param etcDtaVo
     * @return list
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 기타자료 목록 조회
     */
    List<EtcDtaVo> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception;

    /**
     * 기타자료 상세 조회
     *
     * @param etcDtaVo
     * @return etc dta vo
     * @throws Exception
     * @Title : selectEtcDta
     * @Description : 기타자료 상세 조회
     */
    public EtcDtaVo selectEtcDta(EtcDtaVo etcDtaVo) throws Exception;

    /**
     * 기타자료 등록
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : insertEtcDta
     * @Description : 기타자료 등록
     */
    int insertEtcDta(EtcDtaVo etcDtaVo) throws Exception;

    /**
     * 기타자료 수정
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : updateEtcDta
     * @Description : 기타자료 수정
     */
    int updateEtcDta(EtcDtaVo etcDtaVo) throws Exception;

    /**
     * 기타자료 삭제
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteEtcDta
     * @Description : 기타자료 삭제
     */
    int deleteEtcDta(EtcDtaVo etcDtaVo) throws Exception;
}
