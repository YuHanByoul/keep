/**
 * 
 */
package com.kbrainc.plum.front.clclnReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.clclnMng.model.ClclnDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산보고관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.clclnReport.service
* - ClclnReportServiceImpl.java
* </pre> 
*
* @ClassName : ClclnReportServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ClclnReportServiceImpl extends PlumAbstractServiceImpl implements ClclnReportService {

    @Autowired
    private ClclnDao clclnDao;
    
    @Override
    public SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDao.selectSplmntInfo(supplementVo);
    }

    @Transactional
    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result += clclnDao.updateSplmnt(supplementVo);
        result += clclnDao.updateStts(supplementVo);
        return result;
    }

    @Override
    public int updateClclnReport(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDao.updateStts(supplementVo);
    }
}
