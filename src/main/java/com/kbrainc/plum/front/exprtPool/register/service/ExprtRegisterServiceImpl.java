package com.kbrainc.plum.front.exprtPool.register.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.exprtPool.register.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${crypto.key}")
    private String encryptKey;

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

    /**
     * 로그인한 사용자의 전문가 상태 코드 조회
     *
     * @param user
     * @return string
     * @throws Exception
     * @Title : selectExprtStts
     * @Description : 로그인한 사용자의 전문가 상태 코드 조회
     */
    @Override
    public String selectExprtStts(UserVo user) throws Exception {
        return exprtRegisterDao.selectExprtStts(user);
    }

    /**
     * 전문가 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertExprt
     * @Description : 전문가 등록
     */
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
            retVal += exprtRegisterDao.updateExprt(exprtRegisterVo);
        } else {
            retVal += exprtRegisterDao.insertExprt(exprtRegisterVo);
        }

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

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        encryptor.setPassword(encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        String encStr = encryptor.encrypt(exprtRegisterVo.getGndr());
        exprtRegisterVo.setGndr(encStr);

        retVal += exprtRegisterDao.insertDefaultInfo(exprtRegisterVo);

        return retVal;
    }

    /**
     * 임시저장된 전문가 정보 조회
     *
     * @param exprtRegisterVo
     * @return exprt register vo
     * @throws Exception
     * @Title : selectExprtRegister
     * @Description : 임시저장된 전문가 정보 조회
     */
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

    /**
     * 환경교육사 연동테이블에 있는 전문자격증 테이블 목록 조회
     *
     * @param exprtRegisterVo
     * @return list
     * @throws Exception
     * @Title : selectMmbrQlfcList
     * @Description : 환경교육사 연동테이블에 있는 전문자격증 테이블 목록 조회
     */
    @Override
    public List<MmbrQlfcVo> selectMmbrQlfcList(ExprtRegisterVo exprtRegisterVo) throws Exception {
        return exprtRegisterDao.selectMmbrQlfcList(exprtRegisterVo);
    }
}
