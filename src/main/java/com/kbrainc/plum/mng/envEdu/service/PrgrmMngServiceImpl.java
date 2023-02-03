/**
 * 
 */
package com.kbrainc.plum.mng.envEdu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.envEdu.model.PrgrmMngDao;
import com.kbrainc.plum.mng.envEdu.model.PrgrmMngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 우수환경교육 프로그램 관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.envEdu.service
* - PrgrmMngServiceImpl.java
* </pre> 
*
* @ClassName : PrgrmMngServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class PrgrmMngServiceImpl extends PlumAbstractServiceImpl implements PrgrmMngService {

    @Autowired
    private PrgrmMngDao prgrmMngDao;

    @Override
    public List<PrgrmMngVo> selectPrgrmMngList(PrgrmMngVo prgrmMngVo) throws Exception {
        // TODO Auto-generated method stub
        return prgrmMngDao.selectPrgrmMngList(prgrmMngVo);
    }

    @Override
    public int insertPrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception {
        // TODO Auto-generated method stub
        return prgrmMngDao.insertPrgrmMng(prgrmMngVo);
    }

    @Override
    public int updatePrgrmMng(PrgrmMngVo prgrmMngVo) throws Exception {
        // TODO Auto-generated method stub
        return prgrmMngDao.updatePrgrmMng(prgrmMngVo);
    }
    
}
