package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.expertPoolMng.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 전문가 정보변경 서비스 구현클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertMdfcnServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertMdfcnServiceImpl
 * @Description : 전문가 정보변경 서비스 구현클래스
 * @date : 2023. 01. 12.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class ExpertMdfcnServiceImpl extends PlumAbstractServiceImpl implements ExpertMdfcnService {
    @Autowired
    private ExpertMdfcnDao expertMdfcnDao;

    @Autowired
    private ExpertPoolMngDao expertPoolMngDao;

    @Autowired
    private FileDao fileDao;

    @Override
    public List<ExpertMdfcnVo> selectExpertMdfcnList(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        return expertMdfcnDao.selectExpertMdfcnList(expertMdfcnVo);
    }

    @Override
    public ExpertVo selectExpertMdfcnInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        ExpertVo expertVo = expertMdfcnDao.selectExpertMdfcnInfo(expertMdfcnVo);
        List<ExpertHdofVo> expertHdofList = expertMdfcnDao.selectExpertHdofList(expertMdfcnVo);
        for (ExpertHdofVo item : expertHdofList) {
            if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(Integer.parseInt(item.getHdofcrtfFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setHdofCrtfFile(fileInfo);
            }
        }

        List<ExpertCareerVo> expertCareerList = expertMdfcnDao.selectExpertCareerList(expertMdfcnVo);
        for (ExpertCareerVo item : expertCareerList) {
            if (item.getCrtfFileid() != null && !item.getCrtfFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getCrtfFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setCrtfFile(fileInfo);
            }
            if (item.getArtclassFileid() != null && !item.getArtclassFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getArtclassFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setArtClassFile(fileInfo);
            }
        }

        List<ExpertCrtfctVo> expertCrtfctList = expertMdfcnDao.selectExpertCrtfctList(expertMdfcnVo);
        for (ExpertCrtfctVo item : expertCrtfctList) {
            if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getCrtfctFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setCrtfctFile(fileInfo);
            }
        }

        expertVo.setExpertHdofList(expertHdofList);
        expertVo.setExpertCareerList(expertCareerList);
        expertVo.setExpertCrtfctList(expertCrtfctList);

        return expertVo;
    }

    @Override
    public ExpertMdfcnVo selectExpertMdfcn(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        return expertMdfcnDao.selectExpertMdfcn(expertMdfcnVo);
    }

    @Override
    public boolean updateSttsCd(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        /*변경 승인시 기존의 전문가 정보를 모두 제거후 새로운 정보로 교체*/
        return expertMdfcnDao.updateSttsCd(expertMdfcnVo);
    }
}
