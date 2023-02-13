package com.kbrainc.plum.front.envPrpsl.service;

import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslAnsVo;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslVo;

import java.util.List;

/**
 * 환경교육 제안 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.envPrpsl.service
 * - EnvPrpslSerivce.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvPrpslSerivce
 * @Description : 환경교육 제안 서비스 인터페이스
 * @date : 2023. 02. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface EnvPrpslSerivce {

    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVo
     * @return list
     */
    List<EnvPrpslVo> selectEnvPrpslList(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 정보 조회
     * Title : selectEnvPrpsl
     * Description : 환경교육제안 정보 조회
     *
     * @param envPrpslVo
     * @return env prpsl vo
     */
    EnvPrpslVo selectEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 답변 정보 조회
     * Title : selectEnvPrpslAnsInfo
     * Description : 환경교육제안 답변 정보 조회
     *
     * @param envPrpslVo
     * @return env prpsl ans vo
     */
    EnvPrpslAnsVo selectEnvPrpslAns(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 등록
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : insertEnvPrpsl
     * @Description : 환경교육제안 등록
     */
    public int insertEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 수정
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : updateEnvPrpsl
     * @Description : 환경교육제안 수정
     */
    public int updateEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 삭제
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : deleteEnvPrpsl
     * @Description : 환경교육제안 삭제
     */
    public int deleteEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception;

    /**
     * 환경교육제안 취소
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : cancelEnvPrpsl
     * @Description : 환경교육제안 취소
     */
    public int cancelEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception;

}
