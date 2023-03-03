package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        getMyHdofFiles(expertHdofList);

        List<MyCareerVo> expertCareerList = myExprtDao.selectExpertCareerList(exprtVo);
        getMyCareerFiles(expertCareerList);

        List<MyCrtfctVo> expertCrtfctList = myExprtDao.selectExpertCrtfctList(exprtVo);
        getCrtfctFiles(expertCrtfctList);

        exprt.setHdofs(expertHdofList);
        exprt.setCareers(expertCareerList);
        exprt.setCrtfcts(expertCrtfctList);

        return exprt;
    }

    @Override
    public MyExprtMdfcnVo selectMyExprtMdfcn(CommonExprtVo myExprtMdfcnVo) throws Exception {
        MyExprtMdfcnVo exprtMdfcn = myExprtDao.selectMyExprtMdfcn(myExprtMdfcnVo);

        List<MyHdofVo> expertHdofList = myExprtDao.selectExpertHdofList(myExprtMdfcnVo);
        getMyHdofFiles(expertHdofList);

        List<MyCareerVo> expertCareerList = myExprtDao.selectExpertCareerList(myExprtMdfcnVo);
        getMyCareerFiles(expertCareerList);

        List<MyCrtfctVo> expertCrtfctList = myExprtDao.selectExpertCrtfctList(myExprtMdfcnVo);
        getCrtfctFiles(expertCrtfctList);

        exprtMdfcn.setHdofs(expertHdofList);
        exprtMdfcn.setCareers(expertCareerList);
        exprtMdfcn.setCrtfcts(expertCrtfctList);
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

    private void getMyHdofFiles(List<MyHdofVo> expertHdofList) throws Exception {
        for (MyHdofVo item : expertHdofList) {
            if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getHdofcrtfFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setHdofCrtfFile(fileInfo);
            }
        }
    }

    private void getMyCareerFiles(List<MyCareerVo> expertCareerList) throws Exception {
        for (MyCareerVo item : expertCareerList) {
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
    }

    private void getCrtfctFiles(List<MyCrtfctVo> expertCrtfctList) throws Exception {
        for (MyCrtfctVo item : expertCrtfctList) {
            if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getCrtfctFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setCrtfctFile(fileInfo);
            }
        }
    }
}
