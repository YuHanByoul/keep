package com.kbrainc.plum.mng.envPrpsl.service;

import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslAnsVO;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslVO;

import java.util.List;

/**
 * 환경교육제안 관리 Service
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.service
 * - EnvPrpslService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslService
 * @Description : 환경교육제안 관리 Service
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface EnvPrpslService {

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
     * 환경교육제안 관리 답변 등록
     * Title : insertEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 등록
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    boolean insertEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO);

    /**
     * 환경교육제안 관리 답변 수정
     * Title : updateEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 수정
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    boolean updateEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO);
}
