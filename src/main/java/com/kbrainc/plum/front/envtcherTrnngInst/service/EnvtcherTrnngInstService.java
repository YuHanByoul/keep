package com.kbrainc.plum.front.envtcherTrnngInst.service;

import com.kbrainc.plum.front.envtcherTrnngInst.model.EnvtcherTrnngInstVo;

import java.util.List;

/**
 * 환경교육사 양성기관 현황 Service
 *
 * <pre>
 * com.kbrainc.plum.front.envtcherTrnngInst.service
 * - EnvtcherTrnngInstService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvtcherTrnngInstService
 * @Description : 환경교육사 양성기관 현황 Service
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface EnvtcherTrnngInstService {

    /**
     * 환경교육사 양성기관 현황 목록 화면
     * Title : selectEnvtcherTrnngInstSituList
     * Description : 환경교육사 양성기관 현황 목록 화면
     *
     * @return list
     */
    List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstSituList();
}
