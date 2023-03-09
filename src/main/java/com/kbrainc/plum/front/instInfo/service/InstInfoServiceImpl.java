package com.kbrainc.plum.front.instInfo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.instInfo.model.InstInfoDao;
import com.kbrainc.plum.front.instInfo.model.InstInfoVo;
import com.kbrainc.plum.front.instInfo.model.InstPicVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service("front.instInfoServiceImpl")
@Alias("front.instInfoServiceImpl")
public class InstInfoServiceImpl extends PlumAbstractServiceImpl implements InstInfoService{
    
    @Resource(name = "front.instInfoDao")
    private InstInfoDao instInfoDao;
    
    @Autowired
    private FileDao fileDao;

    @Override
    public InstInfoVo selectInstInfo(InstInfoVo instInfoVo) throws Exception {
        InstInfoVo instInfo = instInfoDao.selectInstInfo(instInfoVo);
        
        if(instInfo != null && !instInfo.getLogoFileid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFileid(Integer.parseInt(instInfo.getLogoFileid().toString()));
            FileVo fileInfo= fileDao.getFileInfo(fileVo);
            instInfo.setFileInfo(fileInfo);
        }
        return instInfo;
    }

    @Override
    public List<InstPicVo> selectInstPictList(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectInstPictList(instPicVo);
    }

    @Override
    public InstPicVo selectInstPicInfo(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectInstPicInfo(instPicVo);
    }

    @Override
    public List<InstPicVo> selectPicSearchList(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectPicSearchList(instPicVo);
    }

    @Override
    public int updateRegInstPic(InstPicVo instPicVo) throws Exception {
        return instInfoDao.updateRegInstPic(instPicVo);
    }

    @Override
    public int updateCancelInstPic(InstPicVo instPicVo) throws Exception {
        return instInfoDao.updateCancelInstPic(instPicVo);
    }
    
}
