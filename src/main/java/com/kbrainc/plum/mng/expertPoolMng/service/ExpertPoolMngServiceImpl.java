package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.mng.expertPoolMng.model.ExpertLogVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertPoolMngDao;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return expertPoolMngDao.selectExpertApplyInfo(expertVo);
    }

    /**
     * 전문가 상태 변경
     *
     * @param expertVo
     * @return boolean
     * @throws Exception
     * @Title : updateExpertStatus
     * @Description : 전문가 상태 변경
     */
    @Override
    public boolean updateExpertStatus(ExpertVo expertVo) throws Exception {
        return expertPoolMngDao.updateExpertStatus(expertVo);
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
}
