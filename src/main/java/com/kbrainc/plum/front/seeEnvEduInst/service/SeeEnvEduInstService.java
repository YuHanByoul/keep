package com.kbrainc.plum.front.seeEnvEduInst.service;

import com.kbrainc.plum.front.seeEnvEduInst.model.SeeEnvEduInstVo;

import java.util.List;

/**
 * 교육기관/시설 > 사회환경교육기관 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.seeEnvEduInst.service
 * - SeeEnvEduInstService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstService
 * @Description : 교육기관/시설 > 사회환경교육기관 서비스 인터페이스
 * @date : 2023. 04. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SeeEnvEduInstService {
    /**
     * 사회환경교육기관 기관 현황 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 사회환경교육기관 기관 현황 조회
     */
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList() throws Exception;

}
