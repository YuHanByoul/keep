package com.kbrainc.plum.front.envLrnQlfcFnshHstry.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryDao;
import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service("front.envLrnQlfcFnshHstryServiceImpl")
@Alias("front.envLrnQlfcFnshHstryServiceImpl")
public class EnvLrnQlfcFnshHstryServiceImpl extends PlumAbstractServiceImpl implements EnvLrnQlfcFnshHstryService{
    
    @Resource(name = "front.envLrnQlfcFnshHstryDao")
    private EnvLrnQlfcFnshHstryDao envLrnQlfcFnshHstryDao;

    @Override
    public List<EnvLrnQlfcFnshHstryVo> selectFnshInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectFnshInfoList(envLrnQlfcFnshHstryVo);
    }

    @Override
    public EnvLrnQlfcFnshHstryVo selectFnshInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectFnshInfoDetail(envLrnQlfcFnshHstryVo);
    }

    @Override
    public List<EnvLrnQlfcFnshHstryVo> selectQlfcInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectQlfcInfoList(envLrnQlfcFnshHstryVo);
    }

    @Override
    public EnvLrnQlfcFnshHstryVo selectQlfcInfoDetail(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) {
        return envLrnQlfcFnshHstryDao.selectQlfcInfoDetail(envLrnQlfcFnshHstryVo);
    }
}
