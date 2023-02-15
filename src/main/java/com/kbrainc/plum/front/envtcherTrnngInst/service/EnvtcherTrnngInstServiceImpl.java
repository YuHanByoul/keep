package com.kbrainc.plum.front.envtcherTrnngInst.service;

import com.kbrainc.plum.front.envtcherTrnngInst.model.EnvtcherTrnngInstDao;
import com.kbrainc.plum.front.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 환경교육사 양성기관 현황 ServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.front.envtcherTrnngInst.service
 * - EnvtcherTrnngInstServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvtcherTrnngInstServiceImpl
 * @Description : 환경교육사 양성기관 현황 ServiceImpl
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Alias("front.EnvtcherTrnngInstService")
@Service("front.EnvtcherTrnngInstService")
public class EnvtcherTrnngInstServiceImpl extends PlumAbstractServiceImpl implements EnvtcherTrnngInstService{
    @Resource(name="front.EnvtcherTrnngInstDao")
    private EnvtcherTrnngInstDao envtcherTrnngInstDao;

    /**
     * 환경교육사 양성기관 현황 목록 화면
     * Title : selectEnvtcherTrnngInstSituList
     * Description : 환경교육사 양성기관 현황 목록 화면
     *
     * @return list
     */
    @Override
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstSituList() {
        return envtcherTrnngInstDao.selectEnvtcherTrnngInstSituList();
    }
}
