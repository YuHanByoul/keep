package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileGrpVo;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.expertPoolMng.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getHdofcrtfFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
                item.setHdofCrtfFile(fileInfo);
            }
        }

        List<ExpertCareerVo> expertCareerList = expertPoolMngDao.selectExpertCareerList(expertVo);
        for (ExpertCareerVo item : expertCareerList) {
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

        List<ExpertCrtfctVo> expertCrtfctList = expertPoolMngDao.selectExpertCrtfctList(expertVo);
        for (ExpertCrtfctVo item : expertCrtfctList) {
            if (item.getCrtfctFileid() != null && !item.getCrtfctFileid().equals(0)) {
                FileGrpVo fileGrpVo = new FileGrpVo();
                fileGrpVo.setFilegrpid(item.getCrtfctFileid());
                FileVo fileInfo = fileDao.selectFileInfo(fileGrpVo);
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
    @Transactional
    public int updateExpertStatus(ExpertVo expertVo, ExpertLogVo expertLogVo) throws Exception {
        int retVal = 0;

//        승인 시 정보변경 이력테이블에 값이 없는 경우
        if (expertVo.getSttsCd().equals("134103")) {
            int mdfcnDmndCount = expertPoolMngDao.getMdfcnDmndCount(expertVo);
            if (mdfcnDmndCount == 0) {
                ExpertVo expert = selectExpertApplyInfo(expertVo);
//                 정보변경 테이블에 전문가 정보 복사
                retVal += expertPoolMngDao.insertMdfcnExprt(expert);
                retVal += expert.getExpertCareerList().size() > 0 ? expertPoolMngDao.insertMdfcnCareer(expert) : 0;
                retVal += expert.getExpertCrtfctList().size() > 0 ? expertPoolMngDao.insertMdfcnCrtfct(expert) : 0;
                retVal += expert.getExpertHdofList().size() > 0 ? expertPoolMngDao.insertMdfcnHdof(expert) : 0;

                expert.setExprtTrgtArr(expert.getExprtTrgtCd().split(","));
                expert.setExprtSbjctArr(expert.getExprtSbjctCd().split(","));
                expert.setExprtActvtRgnArr(expert.getExprtActvtRgnCd().split(","));
                expert.setExprtActvtScopeArr(expert.getExprtActvtScopeCd().split(","));
                retVal += expertPoolMngDao.insertMdfcnTrgtCds(expert);
                retVal += expertPoolMngDao.insertMdfcnSbjctCds(expert);
                retVal += expertPoolMngDao.insertMdfcnActvtRgnCds(expert);
                retVal += expertPoolMngDao.insertMdfcnActvtScopeCds(expert);
            }
        }

        if (expertLogVo.getPrcsSeCd() != null && !expertLogVo.getPrcsSeCd().equals("")) {
            retVal += expertPoolMngDao.updateExpertStatus(expertVo);
            retVal += expertPoolMngDao.insertExpertLog(expertLogVo);
            return retVal;
        } else {
            retVal += expertPoolMngDao.updateExpertStatus(expertVo);
            return retVal;
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
        return expertPoolMngDao.insertExpertLog(expertLogVo) > 0;
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
        return expertPoolMngDao.selectExpertLogList(expertLogVo);
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
