package com.kbrainc.plum.mng.seeEnvEduInst.service;

import com.kbrainc.plum.mng.seeEnvEduInst.model.SeeEnvEduInstVo;

import java.util.List;

/**
 * 교육기관/시설관리 > 사회환경교육기관 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.seeEnvEduInst.service
 * - SeeEnvEduInstService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstService
 * @Description : 교육기관/시설관리 > 사회환경교육기관 서비스 인터페이스
 * @date : 2023. 04. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SeeEnvEduInstService {
    /**
     * 교육기관/시설관리 > 사회환경교육기관 목록 조회
     *
     * @param seeEnvEduInstVo
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 교육기관/시설관리 > 사회환경교육기관 목록 조회
     */
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList(SeeEnvEduInstVo seeEnvEduInstVo) throws Exception;
}
