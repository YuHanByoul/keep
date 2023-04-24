package com.kbrainc.plum.front.seeEnvEduInst.service;

import com.kbrainc.plum.front.seeEnvEduInst.model.SeeEnvEduInstDao;
import com.kbrainc.plum.front.seeEnvEduInst.model.SeeEnvEduInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 교육기관/시설 > 사회환경교육기관 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.seeEnvEduInst.service
 * - SeeEnvEduInstServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstServiceImpl
 * @Description : 교육기관/시설 > 사회환경교육기관 서비스 구현 클래스
 * @date : 2023. 04. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.seeEnvEduInstService")
@Alias("front.seeEnvEduInstService")
public class SeeEnvEduInstServiceImpl extends PlumAbstractServiceImpl implements SeeEnvEduInstService {

    @Resource(name="front.seeEnvEduInstDao")
    private SeeEnvEduInstDao seeEnvEduInstDao;

    /**
     * 사회환경교육기관 기관 현황 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectSeeEnvEduInstList
     * @Description : 사회환경교육기관 기관 현황 조회
     */
    @Override
    public List<SeeEnvEduInstVo> selectSeeEnvEduInstList() throws Exception {
        return seeEnvEduInstDao.selectSeeEnvEduInstList();
    }
}
