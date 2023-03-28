/**
 * 
 */
package com.kbrainc.plum.front.cncl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.cncl.model.CancelDao;
import com.kbrainc.plum.front.cncl.model.CancelVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 사업포기신청 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.cncl.service
* - CancelServiceImpl.java
* </pre> 
*
* @ClassName : CancelServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CancelServiceImpl extends PlumAbstractServiceImpl implements CancelService { 

    @Autowired
    private CancelDao cancelDao;

    @Override
    public List<CancelVo> selectCancelList(CancelVo cancelVo) throws Exception {
        // TODO Auto-generated method stub
        return cancelDao.selectCancelList(cancelVo);
    }
    
    @Override
    public List<CancelVo> selectFldList(CancelVo cancelVo) throws Exception {
        // TODO Auto-generated method stub
        return cancelDao.selectFldList(cancelVo);
    }
    
    @Override
    public List<CancelVo> selectBaseInfo(CancelVo cancelVo) throws Exception {
        // TODO Auto-generated method stub
        return cancelDao.selectBaseInfo(cancelVo);
    }

    @Transactional
    @Override
    public int saveCancel(CancelVo cancelVo) throws Exception {
        // TODO Auto-generated method stub
        int retVal = 0;
        if (cancelVo.getDmndid() > 0) {
            retVal = cancelDao.updateCancel(cancelVo);
        } else {
            retVal += cancelDao.insertCancel(cancelVo);
        }
        retVal += cancelDao.updateAplyCancel(cancelVo);
        retVal += cancelDao.updateDelvryInfo(cancelVo);
        return retVal;
    }
}
