/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestDao;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.sprtBizPcntst.service
* - PublicContestServiceImpl.java
* </pre> 
*
* @ClassName : PublicContestServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class PublicContestServiceImpl extends PlumAbstractServiceImpl implements PublicContestService {

    @Autowired
    private PublicContestDao publicContestDao;

    @Override
    public List<PublicContestVo> selectContestList(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.selectContestList(publicContestVo);
    }

    @Override
    public PublicContestVo detailContest(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.detailContest(publicContestVo);
    }

    @Override
    public int insertContest(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.insertContest(publicContestVo);
    }

    @Override
    public int updateContest(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.updateContest(publicContestVo);
    }

    @Override
    public int deleteContest(Integer[] deleteContestIds) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.deleteContest(deleteContestIds);
    }
}
