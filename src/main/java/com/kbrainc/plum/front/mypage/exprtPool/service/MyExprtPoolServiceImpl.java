package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * 마이페이지 > 환경교육 전문가 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyExprtPoolServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtPoolServiceImpl
 * @Description : 마이페이지 > 환경교육 전문가 서비스 구현 클래스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Service
public class MyExprtPoolServiceImpl extends PlumAbstractServiceImpl implements MyExprtPoolService {
    @Autowired
    private MyExprtDao myExprtDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileGrpDao fileGrpDao;

    /**
     * 전문가 정보 조회
     *
     * @param exprtVo
     * @return my exprt vo
     * @Title : selectMyExprt
     * @Description : 전문가 정보 조회
     */
    public MyExprtVo selectMyExprt(CommonExprtVo exprtVo) throws Exception {
        MyExprtVo exprt = myExprtDao.selectMyExprt(exprtVo);

        List<MyHdofVo> expertHdofList = myExprtDao.selectExpertHdofList(exprtVo);
        getMyHdofFiles(expertHdofList, exprtVo.getUser(), false);

        List<MyCareerVo> expertCareerList = myExprtDao.selectExpertCareerList(exprtVo);
        getMyCareerFiles(expertCareerList, exprtVo.getUser(), false);

        List<MyCrtfctVo> expertCrtfctList = myExprtDao.selectExpertCrtfctList(exprtVo);
        getCrtfctFiles(expertCrtfctList, exprtVo.getUser(), false);

        if (exprt != null) {
            exprt.setHdofs(expertHdofList);
            exprt.setCareers(expertCareerList);
            exprt.setCrtfcts(expertCrtfctList);
        }

        return exprt;
    }

    @Override
    @Transactional
    public MyExprtMdfcnVo selectMyExprtMdfcn(CommonExprtVo myExprtMdfcnVo) throws Exception {
        MyExprtMdfcnVo exprtMdfcn = myExprtDao.selectMyExprtMdfcn(myExprtMdfcnVo);

        List<MyHdofVo> expertHdofList = myExprtDao.selectExpertHdofList(myExprtMdfcnVo);
        getMyHdofFiles(expertHdofList, myExprtMdfcnVo.getUser(), true);

        List<MyCareerVo> expertCareerList = myExprtDao.selectExpertCareerList(myExprtMdfcnVo);
        getMyCareerFiles(expertCareerList, myExprtMdfcnVo.getUser(), true);

        List<MyCrtfctVo> expertCrtfctList = myExprtDao.selectExpertCrtfctList(myExprtMdfcnVo);
        getCrtfctFiles(expertCrtfctList, myExprtMdfcnVo.getUser(), true);

        if (exprtMdfcn != null) {
            exprtMdfcn.setHdofs(expertHdofList);
            exprtMdfcn.setCareers(expertCareerList);
            exprtMdfcn.setCrtfcts(expertCrtfctList);
        }
        return exprtMdfcn;
    }

    /**
     * 공개 범위 및 서비스 수신 여부 변경
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : updateRlsAndRcptn
     * @Description : 공개 범위 및 서비스 수신 여부 변경
     */
    @Override
    @Transactional
    public int updateRlsAndRcptn(MyExprtVo exprtVo) throws Exception {
        int retVal = 0;

        retVal += myExprtDao.updateRlsAndRcptn(exprtVo);

        return retVal;
    }

    /**
     * 수정 요청 등록
     *
     * @param myExprtMdfcnVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnDmnd
     * @Description : 수정 요청 등록
     */
    @Override
    @Transactional
    public int insertMdfcnDmnd(MyExprtMdfcnVo myExprtMdfcnVo) throws Exception {
        int retVal = 0;

        retVal += myExprtDao.insertMdfcnDmnd(myExprtMdfcnVo);

        String[] trgtCds = myExprtMdfcnVo.getTrgtCds().split(",");
        String[] sbjctCds = myExprtMdfcnVo.getSbjctCds().split(",");
        String[] actvtRgnCds = myExprtMdfcnVo.getActvtRgnCds().split(",");
        String[] actvtScopeCds = myExprtMdfcnVo.getActvtScopeCds().split(",");
        retVal += myExprtDao.insertTrgtCds(myExprtMdfcnVo.getMdfcnDmndId(), trgtCds, myExprtMdfcnVo.getUser());
        retVal += myExprtDao.insertSbjctCds(myExprtMdfcnVo.getMdfcnDmndId(), sbjctCds, myExprtMdfcnVo.getUser());
        retVal += myExprtDao.insertActvtRgnCds(myExprtMdfcnVo.getMdfcnDmndId(), actvtRgnCds, myExprtMdfcnVo.getUser());
        retVal += myExprtDao.insertActvtScopeCds(myExprtMdfcnVo.getMdfcnDmndId(), actvtScopeCds, myExprtMdfcnVo.getUser());

        retVal += !CollectionUtils.isEmpty(myExprtMdfcnVo.getCareers()) ? myExprtDao.insertCareer(myExprtMdfcnVo) : 0;
        retVal += !CollectionUtils.isEmpty(myExprtMdfcnVo.getHdofs()) ? myExprtDao.insertHdof(myExprtMdfcnVo) : 0;
        retVal += !CollectionUtils.isEmpty(myExprtMdfcnVo.getCrtfcts()) ? myExprtDao.insertCrtfct(myExprtMdfcnVo) : 0;

        return retVal;
    }

    private void getMyHdofFiles(List<MyHdofVo> expertHdofList, UserVo user, boolean needCopy) throws Exception {
        for (MyHdofVo item : expertHdofList) {
            if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getHdofcrtfFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);

                if (needCopy) {
                    copyFile(user, fileInfo);
                    item.setHdofcrtfFileid(fileInfo.getFilegrpid());
                }

                item.setHdofCrtfFile(fileInfo);
            }
        }
    }


    private void getMyCareerFiles(List<MyCareerVo> expertCareerList, UserVo userVo, boolean needCopy) throws Exception {
        for (MyCareerVo item : expertCareerList) {
            if (item.getCrtfFileid() != null && !item.getCrtfFileid().equals(0)) {
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getCrtfFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);

                if (needCopy) {
                    copyFile(userVo, fileInfo);
                    item.setCrtfFileid(fileInfo.getFilegrpid());
                }
                item.setCrtfFile(fileInfo);
            }
            if (item.getArtclassFileid() != null && !item.getArtclassFileid().equals(0)) {
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getArtclassFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                if (needCopy) {
                    copyFile(userVo, fileInfo);
                    item.setArtclassFileid(fileInfo.getFilegrpid());
                }

                item.setArtClassFile(fileInfo);
            }
        }
    }

    private void getCrtfctFiles(List<MyCrtfctVo> expertCrtfctList, UserVo userVo, boolean needCopy) throws Exception {
        for (MyCrtfctVo item : expertCrtfctList) {
            if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getCrtfctFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);

                if (needCopy) {
                    copyFile(userVo, fileInfo);
                    item.setCrtfctFileid(fileInfo.getFilegrpid());
                }
                item.setCrtfctFile(fileInfo);
            }
        }
    }

    private void copyFile(UserVo userVo, FileVo fileVo) throws Exception {
        /* 파일 그룹 생성 */
        FileGrpVo fileGrpVo = new FileGrpVo();
        fileGrpVo.setFilegrpNm("expert_file");
        Integer rgtrid = userVo.getUserid() != null ? Integer.parseInt(userVo.getUserid()) : null;
        fileGrpVo.setRgtrid(rgtrid);
        fileGrpDao.newFileGrp(fileGrpVo);

        /* 파일 생성 */
        String fileNm = fileVo.getOrginlFileNm();
        String saveFileNm = fileVo.getSaveFileNm();
        UUID uuid = UUID.randomUUID();
        String copyFileNm = uuid + "_" + fileNm;
        String fileExt = fileNm.substring(fileNm.lastIndexOf("."));

        fileVo.setFilegrpid(fileGrpVo.getFilegrpid());
        fileVo.setFileExtsn(fileExt);
        fileVo.setRgtrid(fileGrpVo.getRgtrid());
        fileVo.setFileIdntfcKey(CommonUtil.getUUIdGnrBean().getNextBigDecimalId().toString());
        fileVo.setSaveFileNm(copyFileNm);

        fileDao.addFile(fileVo);

        File source = new File(fileVo.getFilePath() + "/" + saveFileNm);
        File target = new File(fileVo.getFilePath() + "/" + copyFileNm);
        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
