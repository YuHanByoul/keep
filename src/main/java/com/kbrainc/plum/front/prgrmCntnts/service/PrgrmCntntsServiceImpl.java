package com.kbrainc.plum.front.prgrmCntnts.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.prgrmCntnts.model.PrgrmCntntsDao;
import com.kbrainc.plum.front.prgrmCntnts.model.PrgrmCntntsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service("front.prgrmCntntsServiceImpl")
@Alias("front.prgrmCntntsServiceImpl")
public class PrgrmCntntsServiceImpl extends PlumAbstractServiceImpl implements PrgrmCntntsService{
    
    @Resource(name = "front.prgrmCntntsDao")
    private PrgrmCntntsDao prgrmCntntsDao;
    
    @Override
    public List<PrgrmCntntsVo> selectPrgrmCntntsList(PrgrmCntntsVo prgrmCntntsVo) {
        return prgrmCntntsDao.selectPrgrmCntntsList(prgrmCntntsVo);
    }

}
