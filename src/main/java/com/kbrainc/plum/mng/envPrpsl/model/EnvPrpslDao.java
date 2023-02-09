package com.kbrainc.plum.mng.envPrpsl.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경교육제안 관리 Dao
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.model
 * - EnvPrpslDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslDao
 * @Description : 환경교육제안 관리 Dao
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface EnvPrpslDao {

    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVO
     * @return list
     */
    List<EnvPrpslVO> selectEnvPrpslList(EnvPrpslVO envPrpslVO);

    /**
     * 환경교육제안 정보 조회
     * Title : selectEnvPrpsInfo
     * Description : 환경교육제안 정보 조회
     *
     * @param envPrpslVO
     * @return env prpsl vo
     */
    EnvPrpslVO selectEnvPrpsInfo(EnvPrpslVO envPrpslVO);

    /**
     * 환경교육제안 답변 정보 조회
     * Title : selectEnvPrpslAnsInfo
     * Description : 환경교육제안 답변 정보 조회
     *
     * @param envPrpslAnsVO
     * @return env prpsl ans vo
     */
    EnvPrpslAnsVO selectEnvPrpslAnsInfo(EnvPrpslAnsVO envPrpslAnsVO);

    /**
     * 환경교육제안 정보 처리 상태 수정
     * Title : updatePrcsSttsCd
     * Description : 환경교육제안 정보 처리 상태 수정
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    boolean updatePrcsSttsCd(EnvPrpslAnsVO envPrpslAnsVO);

    /**
     * 환경교육제안 답변 등록
     * Title : insertEnvPrpslAnswer
     * Description : 환경교육제안 답변 등록
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    boolean insertEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO);

    /**
     * 환경교육제안 답변 수정
     * Title : updateEnvPrpslAnswer
     * Description : 환경교육제안 답변 수정
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    boolean updateEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO);
}
