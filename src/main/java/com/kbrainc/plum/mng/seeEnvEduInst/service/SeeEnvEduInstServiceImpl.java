package com.kbrainc.plum.mng.seeEnvEduInst.service;

import com.kbrainc.plum.mng.seeEnvEduInst.model.SeeEnvEduInstDao;
import com.kbrainc.plum.mng.seeEnvEduInst.model.SeeEnvEduInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 교육기관/시설관리 > 사회환경교육기관 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.seeEnvEduInst.service
 * - SeeEnvEduInstServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstServiceImpl
 * @Description : 교육기관/시설관리 > 사회환경교육기관 서비스 구현 클래스
 * @date : 2023. 04. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class SeeEnvEduInstServiceImpl extends PlumAbstractServiceImpl implements SeeEnvEduInstService {

    @Autowired
    private SeeEnvEduInstDao seeEnvEduInstDao;

    /**
     * 교육기관/시설관리 > 사회환경교육기관 목록 조회
     *
     * @param seeEnvEduInstVo
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 교육기관/시설관리 > 사회환경교육기관 목록 조회
     */
    @Override
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList(SeeEnvEduInstVo seeEnvEduInstVo) throws Exception {
        return seeEnvEduInstDao.selectSeeEnvEduInstList(seeEnvEduInstVo);
    }
}
