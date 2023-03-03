package com.kbrainc.plum.front.cntstAplyHist.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntst.model.CntstAplyVo;
import com.kbrainc.plum.front.cntst.model.CntstDao;
import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistDao;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service("front.cntstAplyHistServiceImpl")
@Alias("front.cntstAplyHistServiceImpl")
public class CntstAplyHistServiceImpl extends PlumAbstractServiceImpl implements CntstAplyHistService {

    @Resource(name = "front.cntstAplyHistDao")
    CntstAplyHistDao cntstAplyHistDao;
    
    @Autowired
    private FileDao fileDao;
    
    @Override
    public List<CntstAplyHistVo> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.selectCntstAplyHistList(cntstAplyHistVo);
    }
    
    @Override
    public CntstAplyHistVo selectCntstAplyHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        CntstAplyHistVo cntstHist = cntstAplyHistDao.selectCntstAplyHistInfo(cntstAplyHistVo);

        if(cntstHist != null && !cntstHist.getPrdctFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(cntstHist.getPrdctFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            cntstHist.setFileList(fileList);
        }
        
        return cntstHist;
    }

    @Override
    public List<CntstAplyHistVo> selectCntstFldMapngInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.selectCntstFldMapngInfo(cntstAplyHistVo);
    }

    @Override
    public int updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.updateCntstAplyHist(cntstAplyHistVo);
    }

    @Override
    public List<CntstAplyHistVo> selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.selectCntstAplySchlHistInfo(cntstAplyHistVo);
    }

    @Override
    public int updateCntstAplySchlHist(List<CntstAplyHistVo> cntstAplyHistVoList) throws Exception {
        return cntstAplyHistDao.updateCntstAplySchlHist(cntstAplyHistVoList);
    }
}
