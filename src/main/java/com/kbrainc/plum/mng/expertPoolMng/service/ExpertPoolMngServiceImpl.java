package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.expertPoolMng.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 전문가 풀 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertPoolMngServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertPoolMngServiceImpl
 * @Description : 전문가 풀 관리 서비스 인터페이스
 * @date : 2022. 12. 30.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Service
public class ExpertPoolMngServiceImpl extends PlumAbstractServiceImpl implements ExpertPoolMngService {

    @Autowired
    private ExpertPoolMngDao expertPoolMngDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 전문가 목록 조회
     *
     * @param expertVo
     * @return list
     * @throws Exception
     * @Title : selectExpertList
     * @Description : 전문가 목록 조회
     */
    @Override
    public List<ExpertVo> selectExpertList(ExpertVo expertVo) throws Exception {
        return expertPoolMngDao.selectExpertList(expertVo);
    }

    /**
     * 전문가 회원 정보 조회
     *
     * @param expertVo
     * @return expert vo
     * @throws Exception
     * @Title : selectExpertInfo
     * @Description : 전문가 회원 정보 조회
     */
    @Override
    public ExpertVo selectExpertInfo(ExpertVo expertVo) throws Exception {
        return expertPoolMngDao.selectExpertInfo(expertVo);
    }

    /**
     * 전문가 상세 정보 조회
     *
     * @param expertVo
     * @return expert vo
     * @throws Exception
     * @Title : selectExpertApplyInfo
     * @Description : 전문가 상세 정보 조회
     */
    @Override
    public ExpertVo selectExpertApplyInfo(ExpertVo expertVo) throws Exception {
        ExpertVo expertApplyInfo = expertPoolMngDao.selectExpertApplyInfo(expertVo);
        List<ExpertHdofVo> expertHdofList = expertPoolMngDao.selectExpertHdofList(expertVo);
        for (ExpertHdofVo item : expertHdofList) {
            if (item.getHdofcrtfFileid() != null && !item.getHdofcrtfFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(Integer.parseInt(item.getHdofcrtfFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setHdofCrtfFile(fileInfo);
            }
        }

        List<ExpertCareerVo> expertCareerList = expertPoolMngDao.selectExpertCareerList(expertVo);
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

        List<ExpertCrtfctVo> expertCrtfctList = expertPoolMngDao.selectExpertCrtfctList(expertVo);
        for (ExpertCrtfctVo item : expertCrtfctList) {
            if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFileid(Integer.parseInt(item.getCrtfctFileid().toString()));
                FileVo fileInfo = fileDao.getFileInfo(fileVo);
                item.setCrtfctFile(fileInfo);
            }
        }

        expertApplyInfo.setExpertHdofList(expertHdofList);
        expertApplyInfo.setExpertCareerList(expertCareerList);
        expertApplyInfo.setExpertCrtfctList(expertCrtfctList);

        return expertApplyInfo;
    }

    /**
     * 전문가 상태 변경
     *
     * @param expertVo
     * @param expertLogVo
     * @return boolean
     * @throws Exception
     * @Title : updateExpertStatus
     * @Description : 전문가 상태 변경
     */
    @Override
    public boolean updateExpertStatus(ExpertVo expertVo, ExpertLogVo expertLogVo) throws Exception {
        if(expertLogVo.getPrcsSeCd() != null && !expertLogVo.getPrcsSeCd().equals("")) {
            return expertPoolMngDao.updateExpertStatus(expertVo) && expertPoolMngDao.insertExpertLog(expertLogVo);
        } else{
            return expertPoolMngDao.updateExpertStatus(expertVo);
        }
    }

    /**
     * 전문가 로그 생성
     *
     * @param expertLogVo
     * @return boolean
     * @throws Exception
     * @Title : insertExpertLog
     * @Description : 전문가 로그 생성
     */
    @Override
    public boolean insertExpertLog(ExpertLogVo expertLogVo) throws Exception {
        return expertPoolMngDao.insertExpertLog(expertLogVo);
    }

    /**
     * 전문가 로그 조회
     *
     * @param expertLogVo
     * @return ExpertLogVo
     * @throws Exception
     * @Title : selectExpertLogList
     * @Description : 전문가 로그 조회
     */
    @Override
    public List<ExpertLogVo> selectExpertLogList(ExpertLogVo expertLogVo) throws Exception {
        return  expertPoolMngDao.selectExpertLogList(expertLogVo);
    }

    /**
     * 전문가 후기 이력 조회
     *
     * @param expertReviewHistoryVo
     * @return list
     * @throws Exception
     * @Title : selectExpertReviewHistoryList
     * @Description : 전문가 후기 이력 조회
     */
    @Override
    public List<ExpertReviewHistoryVo> selectExpertReviewHistoryList(ExpertReviewHistoryVo expertReviewHistoryVo) throws Exception {
        return expertPoolMngDao.selectExpertReviewHistoryList(expertReviewHistoryVo);
    }

    /**
     * 전문가 후기 평균 별점 조회
     *
     * @param expertReviewHistoryVo
     * @return double
     * @throws Exception
     * @Title : getExpertReviewScrAvg
     * @Description : 전문가 후기 평균 별점 조회
     */
    @Override
    public Double getExpertReviewScrAvg(ExpertReviewHistoryVo expertReviewHistoryVo) throws Exception {
        return expertPoolMngDao.getExpertReviewScrAvg(expertReviewHistoryVo);
    }
}
