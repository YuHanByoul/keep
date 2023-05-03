/**
 * 
 */
package com.kbrainc.plum.front.mypage.seeEnvEduInstHist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model.SeeEnvEduInstHistDao;
import com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model.SeeEnvEduInstHistVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 마이페이지 > 사회환경교육기관 지정 관리 > 신청내역 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.seeEnvEduInstHist.service
* - SeeEnvEduInstHistServiceImpl.java
* </pre> 
*
* @ClassName : SeeEnvEduInstHistServiceImpl
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class SeeEnvEduInstHistServiceImpl extends PlumAbstractServiceImpl implements SeeEnvEduInstHistService {

    @Autowired
    private SeeEnvEduInstHistDao seeEnvEduInstHistDao;
    
    /**
    * 정산관리 목록 조회. 
    *
    * @Title : selectSeeEnvEduInstHistList
    * @Description : TODO
    * @param seeEnvEduInstHistVo
    * @return
    * @throws Exception
    * @return List<SeeEnvEduInstHistVo>
     */
    @Override
    public List<SeeEnvEduInstHistVo> selectSeeEnvEduInstHistList(SeeEnvEduInstHistVo seeEnvEduInstHistVo) throws Exception {
        return seeEnvEduInstHistDao.selectSeeEnvEduInstHistList(seeEnvEduInstHistVo);
    }
    
    @Override
    public SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvEduInstHistDao.selectSplmntInfo(supplementVo);
    }

    @Transactional
    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result += seeEnvEduInstHistDao.updateSplmnt(supplementVo);
        //result += seeEnvEduInstHistDao.updateStts(supplementVo);
        return result;
    }

}
