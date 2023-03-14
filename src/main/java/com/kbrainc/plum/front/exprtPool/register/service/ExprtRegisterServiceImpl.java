package com.kbrainc.plum.front.exprtPool.register.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.exprtPool.register.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 전문가 등재신청 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.service
 * - ExprtRegisterServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterServiceImpl
 * @Description : 전문가 등재신청 서비스 구현 클래스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
@RequiredArgsConstructor
public class ExprtRegisterServiceImpl extends PlumAbstractServiceImpl implements ExprtRegisterService {

    private final ExprtRegisterDao exprtRegisterDao;
    private final FileDao fileDao;

    /**
     * 사용자 기본정보 조회
     *
     * @param exprtRegisterVo
     * @return default member info vo
     * @Title : selectDefaultMemberInfo
     * @Description : 사용자 기본정보 조회
     */
    public DefaultMemberInfoVo selectDefaultMemberInfo(ExprtRegisterVo exprtRegisterVo) throws Exception {
        return exprtRegisterDao.selectDefaultMemberInfo(exprtRegisterVo);
    }

    @Override
    public String selectExprtStts(UserVo user) throws Exception {
        return exprtRegisterDao.selectExprtStts(user);
    }

    @Override
    @Transactional
    public int insertExprt(ExprtRegisterVo exprtRegisterVo) throws Exception {
        int retVal = 0;

        /* 임시 저장된 글이 있는 경우 */
        if (exprtRegisterVo.getNewYn().equals("N")) {
            retVal += exprtRegisterDao.deleteTrgtCds(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteSbjctCds(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteActvtRgnCds(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteActvtScopeCds(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteHdof(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteCrtfct(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteCareer(exprtRegisterVo);
            retVal += exprtRegisterDao.deleteExprt(exprtRegisterVo);
        }

        retVal += exprtRegisterDao.insertExprt(exprtRegisterVo);

        retVal += exprtRegisterVo.getCareers().size() > 0 ? exprtRegisterDao.insertCareer(exprtRegisterVo) : 0;
        retVal += exprtRegisterVo.getCrtfcts().size() > 0 ? exprtRegisterDao.insertCrtfct(exprtRegisterVo) : 0;
        retVal += exprtRegisterVo.getHdofs().size() > 0 ? exprtRegisterDao.insertHdof(exprtRegisterVo) : 0;

        String[] trgtCds = exprtRegisterVo.getTrgtCds().split(",");
        String[] sbjctCds = exprtRegisterVo.getSbjctCds().split(",");
        String[] actvtRgnCds = exprtRegisterVo.getActvtRgnCds().split(",");
        String[] actvtScopeCds = exprtRegisterVo.getActvtScopeCds().split(",");
        retVal += exprtRegisterDao.insertTrgtCds(trgtCds, exprtRegisterVo.getUser());
        retVal += exprtRegisterDao.insertSbjctCds(sbjctCds, exprtRegisterVo.getUser());
        retVal += exprtRegisterDao.insertActvtRgnCds(actvtRgnCds, exprtRegisterVo.getUser());
        retVal += exprtRegisterDao.insertActvtScopeCds(actvtScopeCds, exprtRegisterVo.getUser());

        retVal += exprtRegisterDao.insertDefaultInfo(exprtRegisterVo);

        /* 최초 신청시 정보 변경 테이블에 레코드 생성 */
        if (exprtRegisterVo.getNewYn().equals("Y")) {
            retVal += exprtRegisterDao.insertMdfcnExprt(exprtRegisterVo);
            retVal += exprtRegisterVo.getCareers().size() > 0 ? exprtRegisterDao.insertMdfcnCareer(exprtRegisterVo) : 0;
            retVal += exprtRegisterVo.getCrtfcts().size() > 0 ? exprtRegisterDao.insertMdfcnCrtfct(exprtRegisterVo) : 0;
            retVal += exprtRegisterVo.getHdofs().size() > 0 ? exprtRegisterDao.insertHdof(exprtRegisterVo) : 0;
            retVal += exprtRegisterDao.insertMdfcnTrgtCds(exprtRegisterVo.getMdfcnDmndId(), trgtCds, exprtRegisterVo.getUser());
            retVal += exprtRegisterDao.insertMdfcnSbjctCds(exprtRegisterVo.getMdfcnDmndId(), sbjctCds, exprtRegisterVo.getUser());
            retVal += exprtRegisterDao.insertMdfcnActvtRgnCds(exprtRegisterVo.getMdfcnDmndId(), actvtRgnCds, exprtRegisterVo.getUser());
            retVal += exprtRegisterDao.insertMdfcnActvtScopeCds(exprtRegisterVo.getMdfcnDmndId(), actvtScopeCds, exprtRegisterVo.getUser());
        }

        return retVal;
    }

    @Override
    public ExprtRegisterVo selectExprtRegister(ExprtRegisterVo exprtRegisterVo) throws Exception {
        ExprtRegisterVo exprtRegister = exprtRegisterDao.selectExpertRegister(exprtRegisterVo);

        if (exprtRegister != null) {
            List<HdofVo> expertHdofList = exprtRegisterDao.selectExpertHdofList(exprtRegisterVo);

            for (HdofVo item : expertHdofList) {
                if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                    FileGrpVo fileGrpVo = new FileGrpVo();
                    fileGrpVo.setFilegrpid(item.getHdofcrtfFileid());
                    FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                    item.setHdofCrtfFile(fileInfo);
                }
            }

            List<CareerVo> expertCareerList = exprtRegisterDao.selectExpertCareerList(exprtRegisterVo);
            for (CareerVo item : expertCareerList) {
                if (item.getCrtfFileid() != null && !item.getCrtfFileid().equals(0)) {
                    FileGrpVo fileGrpVo = new FileGrpVo();
                    fileGrpVo.setFilegrpid(item.getCrtfFileid());
                    FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                    item.setCrtfFile(fileInfo);
                }
                if (item.getArtclassFileid() != null && !item.getArtclassFileid().equals(0)) {
                    FileGrpVo fileGrpVo = new FileGrpVo();
                    fileGrpVo.setFilegrpid(item.getArtclassFileid());
                    FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                    item.setArtClassFile(fileInfo);
                }
            }

            List<CrtfctVo> expertCrtfctList = exprtRegisterDao.selectExpertCrtfctList(exprtRegisterVo);
            for (CrtfctVo item : expertCrtfctList) {
                if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                    FileGrpVo fileGrpVo = new FileGrpVo();
                    fileGrpVo.setFilegrpid(item.getCrtfctFileid());
                    FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                    item.setCrtfctFile(fileInfo);
                }
            }

            exprtRegister.setHdofs(expertHdofList);
            exprtRegister.setCareers(expertCareerList);
            exprtRegister.setCrtfcts(expertCrtfctList);
        }

        return exprtRegister;
    }
}
