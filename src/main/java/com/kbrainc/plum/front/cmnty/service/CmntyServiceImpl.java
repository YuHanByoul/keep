package com.kbrainc.plum.front.cmnty.service;

import com.kbrainc.plum.front.cmnty.model.CmntyDao;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 환경동아리 ServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.service
 * - CmntyServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyServiceImpl
 * @Description : 환경동아리 ServiceImpl
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service("front.CmntyService")
@Alias("front.CmntyService")
public class CmntyServiceImpl extends PlumAbstractServiceImpl implements CmntyService {
    @Resource(name = "front.CmntyDao")
    private CmntyDao cmntyDao;

    /**
     * 환경동아리 목록
     * Title : selectCmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @return list
     */
    @Override
    public List<CmntyVo> selectCmntyList(CmntyVo cmntyVo) {
        return cmntyDao.selectCmntyList(cmntyVo);
    }
}
