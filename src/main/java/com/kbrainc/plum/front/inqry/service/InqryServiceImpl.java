package com.kbrainc.plum.front.inqry.service;

import com.kbrainc.plum.front.inqry.model.InqryDao;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 1:1문의 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.service
 * - InqryServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryServiceImpl
 * @Description : 1:1문의 서비스 구현 클래스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.inqryService")
@Alias("front.inqryService")
public class InqryServiceImpl extends PlumAbstractServiceImpl implements InqryService {

    @Resource(name = "front.inqryDao")
    private InqryDao inqryDao;

    @Override
    public List<InqryVo> selectInqryList(InqryVo inqryVo) throws Exception {
        return inqryDao.selectInqryList(inqryVo);
    }

    @Override
    public InqryVo selectInqryDetail(InqryVo inqryVo) throws Exception {
        return inqryDao.selectInqryDetail(inqryVo);
    }

    @Override
    public int insertInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.insertInqry(inqryVo);
        return retVal;
    }

    @Override
    public int updateInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.updateInqry(inqryVo);
        return retVal;
    }

    @Override
    public int deleteInqry(InqryVo inqryVo) throws Exception {
        int retVal = 0;
        retVal += inqryDao.deleteInqry(inqryVo);
        return retVal;
    }
}
