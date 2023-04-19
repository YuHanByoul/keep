package com.kbrainc.plum.mng.qlityChk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.qlityChk.model.QlityChkArtclVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChkDao;
import com.kbrainc.plum.mng.qlityChk.model.QlityChkVo;
import com.kbrainc.plum.mng.qlityChk.model.QlityChklstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service
public class QlityChkServiceImpl extends PlumAbstractServiceImpl implements QlityChkService{
    
    @Autowired
    private QlityChkDao qlityChkDao;

    @Override
    public List<QlityChklstVo> selectQlityChkList() throws Exception {
        return qlityChkDao.selectQlityChkList();
    }

    @Override
    public List<QlityChkArtclVo> selectQlityChkArtclList(String cntntsid) throws Exception {
        return qlityChkDao.selectQlityChkArtclList(cntntsid);
    }

    @Override
    @Transactional
    public int insertQlityChkList(QlityChkVo qlityChkVo, QlityChkArtclVo qlityChkArtclVo) throws Exception {
        int retVal = 0;
        retVal = qlityChkDao.insertQlityChkList(qlityChkVo);
        qlityChkArtclVo.setCeckid(qlityChkVo.getCeckid());
        retVal = qlityChkDao.insertQlityChkArtclList(qlityChkArtclVo);
        
        return retVal;
    }


}
