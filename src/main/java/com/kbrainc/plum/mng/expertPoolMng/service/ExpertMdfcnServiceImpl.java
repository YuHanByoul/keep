package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.expertPoolMng.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private FileDao fileDao;

    @Autowired
    private ExpertPoolMngDao expertPoolMngDao;

    @Override
    public List<ExpertMdfcnVo> selectExpertMdfcnList(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        return expertMdfcnDao.selectExpertMdfcnList(expertMdfcnVo);
    }

    @Override
    public ExpertVo selectExistingExpertInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        int prevDmndId = expertMdfcnDao.getPrevDmndId(expertMdfcnVo);
        ExpertMdfcnVo expertMdfcnVo1 = new ExpertMdfcnVo();
        expertMdfcnVo1.setMdfcnDmndId(prevDmndId);
        expertMdfcnVo1.setUserid(expertMdfcnVo.getUserid());
        return selectExpertMdfcnInfo(expertMdfcnVo1);
    }

    @Override
    public ExpertVo selectNewExpertInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        return selectExpertMdfcnInfo(expertMdfcnVo);
    }

    private ExpertVo selectExpertMdfcnInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception {
        ExpertVo expertVo = expertMdfcnDao.selectExpertMdfcnInfo(expertMdfcnVo);
        List<ExpertHdofVo> expertHdofList = expertMdfcnDao.selectExpertHdofList(expertMdfcnVo);

        for (ExpertHdofVo item : expertHdofList) {
            if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getHdofcrtfFileid().toString()));
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
    @Transactional
    public int updateStts(ExpertMdfcnVo expertMdfcnVo, ExpertLogVo expertLogVo) throws Exception {
        int retVal = 0;
        /*변경 승인시 기존의 전문가 정보를 모두 제거후 새로운 정보로 교체*/
        if (expertMdfcnVo.getSttsCd().equals("152102")) {
            retVal += expertMdfcnDao.deleteExpertCareer(expertMdfcnVo); // 경력
            retVal += expertMdfcnDao.deleteExpertCrtfct(expertMdfcnVo); // 자격
            retVal += expertMdfcnDao.deleteExpertHdof(expertMdfcnVo); // 재직
            retVal += expertMdfcnDao.deleteExpertActvtRgn(expertMdfcnVo); // 활동지역
            retVal += expertMdfcnDao.deleteExpertActvtScope(expertMdfcnVo); // 활동범위
            retVal += expertMdfcnDao.deleteExpertSbjct(expertMdfcnVo); // 분야 주제
            retVal += expertMdfcnDao.deleteExpertTrgt(expertMdfcnVo); // 분야 대상

            ExpertVo expertVo = selectExpertMdfcnInfo(expertMdfcnVo);
            expertVo.setUser(expertLogVo.getUser());

            if (expertVo.getExprtActvtRgnCd() != null) {
                String[] split = expertVo.getExprtActvtRgnCd().split(",");
                expertVo.setExprtActvtRgnArr(split);
                retVal += expertMdfcnDao.insertExpertActvtRgn(expertVo); // 활동 지역
            }

            if (expertVo.getExprtActvtScopeCd() != null) {
                String[] split = expertVo.getExprtActvtScopeCd().split(",");
                expertVo.setExprtActvtScopeArr(split);
                retVal += expertMdfcnDao.insertExpertActvtScope(expertVo);// 활동 범위
            }

            if (expertVo.getExprtSbjctCd() != null) {
                String[] split = expertVo.getExprtSbjctCd().split(",");
                expertVo.setExprtSbjctArr(split);
                retVal += expertMdfcnDao.insertExpertSbjct(expertVo);// 분야 주제
            }

            if (expertVo.getExprtTrgtCd() != null) {
                String[] split = expertVo.getExprtTrgtCd().split(",");
                expertVo.setExprtTrgtArr(split);
                retVal += expertMdfcnDao.insertExpertTrgt(expertVo);// 분야 대상
            }

            retVal += (expertVo.getExpertCareerList().size() == 0 ? 0 : expertMdfcnDao.insertExpertCareer(expertVo)); // 경력사항
            retVal += (expertVo.getExpertCrtfctList().size() == 0 ? 0 : expertMdfcnDao.insertExpertCrtfct(expertVo)); // 자격
            retVal += (expertVo.getExpertHdofList().size() == 0 ? 0 : expertMdfcnDao.insertExpertHdof(expertVo)); // 재직
            retVal += expertMdfcnDao.updateExpertInfo(expertVo); //기본 정보
        }

        retVal += expertMdfcnDao.updateStts(expertMdfcnVo);
        expertPoolMngDao.insertExpertLog(expertLogVo);

        return retVal;
    }
}
