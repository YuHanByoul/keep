package com.kbrainc.plum.mng.cntnts.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.cntnts.model.CntntsDao;
import com.kbrainc.plum.mng.cntnts.model.CntntsVo;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 컨텐츠 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.service
* - CntntsServiceImpl.java
* </pre>
*
* @ClassName : CntntsServiceImpl
* @Description : 컨텐츠 관리 서비스 구현 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CntntsServiceImpl extends PlumAbstractServiceImpl implements CntntsService{

    @Autowired
    private CntntsDao cntntsDao;
    
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsList(cntntsVo);
    };
    
    public int insertCntnts(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.insertCntnts(cntntsVo);
    }
    
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsInfo(cntntsVo);
    }
    
    public int updateCntnts(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.updateCntnts(cntntsVo);
    }
    
    public int deleteCntnts(String[] cntntsids) throws Exception {
        return cntntsDao.deleteCntnts(cntntsids);
    }
}
