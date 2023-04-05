package com.kbrainc.plum.front.intro.envEduPlcyEtcDta.service;

import java.util.List;

import com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model.EtcDtaVo;

/**
 * 환경교육 정책자료실 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyEtcDta.service
 * - EnvEduPlcyEtcDtaService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyEtcDtaService
 * @Description : 환경교육 정책자료실 서비스 인터페이스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface EnvEduPlcyEtcDtaService {
    /**
     * 사업운영자료 목록 조회
     *
     * @param etcDtaVo
     * @return list
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 사업운영자료 목록 조회
     */
    public List<EtcDtaVo> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception;

    /**
     * 사업운영자료 상세 조회
     *
     * @param etcDtaVo
     * @return bsns oper dta vo
     * @throws Exception
     * @Title : selectEtcDta
     * @Description : 사업운영자료 상세 조회
     */
    public EtcDtaVo selectEtcDta(EtcDtaVo etcDtaVo) throws Exception;

}
