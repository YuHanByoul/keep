package com.kbrainc.plum.front.exprtPool.envtcher.service;

import com.kbrainc.plum.front.exprtPool.envtcher.model.EnvtcherAgncyVo;

import java.util.List;

/**
 * 전문가/강사 > 환경교육사 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.envtcher.service
 * - EnvtcherService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvtcherService
 * @Description : 전문가/강사 > 환경교육사 서비스 인터페이스
 * @date : 2023. 04. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface EnvtcherService {
    /**
     * 환경교육사 연동테이블로부터 양성기관 목록 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectEnvtcherAgencyList
     * @Description : 환경교육사 연동테이블로부터 양성기관 목록 조회
     */
    List<EnvtcherAgncyVo> selectEnvtcherAgncyList() throws Exception;
}
