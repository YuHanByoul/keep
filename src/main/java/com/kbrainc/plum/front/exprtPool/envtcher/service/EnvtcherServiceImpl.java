package com.kbrainc.plum.front.exprtPool.envtcher.service;

import com.kbrainc.plum.front.exprtPool.envtcher.model.EnvtcherAgncyVo;
import com.kbrainc.plum.front.exprtPool.envtcher.model.EnvtcherDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 전문가/강사 > 환경교육사 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.envtcher.service
 * - EnvtcherServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvtcherServiceImpl
 * @Description : 전문가/강사 > 환경교육사 서비스 구현 클래스
 * @date : 2023. 04. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class EnvtcherServiceImpl extends PlumAbstractServiceImpl implements EnvtcherService {
    @Autowired
    private EnvtcherDao envtcherDao;
    /**
     * 환경교육사 연동테이블로부터 양성기관 목록 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectEnvcherAgencyList
     * @Description : 환경교육사 연동테이블로부터 양성기관 목록 조회
     */
    @Override
    public List<EnvtcherAgncyVo> selectEnvtcherAgncyList() throws Exception {
        return envtcherDao.selectEnvtcherAgncyList();
    }
}
