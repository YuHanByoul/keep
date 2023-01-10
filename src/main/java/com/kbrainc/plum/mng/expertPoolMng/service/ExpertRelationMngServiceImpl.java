package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertHdofVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertRelationMngDao;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertRelationVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 전문가 섭외 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertRelationMngServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertRelationMngServiceImpl
 * @Description : 전문가 섭외 관리 서비스 구현 클래스
 * @date : 2023. 01. 05.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class ExpertRelationMngServiceImpl extends PlumAbstractServiceImpl implements ExpertRelationMngService {
    @Autowired
    private ExpertRelationMngDao expertRelationMngDao;

    @Autowired
    private FileDao fileDao;

    @Override
    public List<ExpertRelationVo> selectExpertRelationList(ExpertRelationVo expertRelationVo) throws Exception {
        return expertRelationMngDao.selectExpertRelationList(expertRelationVo);
    }

    @Override
    public ExpertRelationVo selectExpertRelationInfo(ExpertRelationVo expertRelationVo) throws Exception {
        ExpertRelationVo expertRelationInfo = expertRelationMngDao.selectExpertRelationInfo(expertRelationVo);
        if (expertRelationInfo.getFilegrpid() != null && !expertRelationInfo.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(expertRelationInfo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            expertRelationInfo.setFileVo(fileList);
        }
        return expertRelationInfo;
    }
}
