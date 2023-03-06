/**
 * 
 */
package com.kbrainc.plum.front.expEnv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.bizAply.model.ReqUserVo;
import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.expEnv.model.MyReqMngDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 마이페이지 > 체험환경교육 프로그램 지원 관리 > 공모신청 내역 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.front.expEnv.service
* - MyReqMngServiceImpl.java
* </pre> 
*
* @ClassName : MyReqMngServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MyReqMngServiceImpl extends PlumAbstractServiceImpl implements MyReqMngService {

    @Autowired
    private MyReqMngDao myReqMngDao;

    @Override
    public List<ReqUserVo> selectMyAplyList(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return myReqMngDao.selectMyAplyList(reqUserVo);
    }
    
    @Override
    public SupplementVo detailSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return myReqMngDao.detailSplmnt(supplementVo);
    }

    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return myReqMngDao.updateSplmnt(supplementVo);
    }
}
