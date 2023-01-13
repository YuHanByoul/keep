package com.kbrainc.plum.mng.prtpn.cntstRcptHist.service;

import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstRcptHistDao;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplySchlVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplyVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 공모전 접수 이력 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.service
 * - CntstRcptHistServiceImpl.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistServiceImpl
 * @Description : 공모전 접수 이력 서비스 구현 클래스
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service
public class CntstRcptHistServiceImpl extends PlumAbstractServiceImpl implements CntstRcptHistService {

    @Autowired
    CntstRcptHistDao cntstRcptHistDao;

    /**
     * 공모전 접수 이력 목록 조회
     * Title : selectCntstAplyList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstAplyVO
     * @return list
     */
    @Override
    public List<CntstAplyVO> selectCntstAplyList(CntstAplyVO cntstAplyVO) {
        return cntstRcptHistDao.selectCntstAplyList(cntstAplyVO);
    }

    /**
     * 공모전 접수 정보 조회
     * Title : selectCntstAplyInfo
     * Description : 공모전 접수 정보 조회
     *
     * @param aplyid
     * @return CntstRcptHistVO
     */
    @Override
    public CntstAplyVO selectCntstAplyInfo(Integer aplyid) {
        return cntstRcptHistDao.selectCntstAplyInfo(aplyid);
    }

    /**
     * 환경방학 일기장 프로젝트 접수 정보 조회
     * Title : selectCntstAplySchlList
     * Description : 환경방학 일기장 프로젝트 접수 정보 조회
     *
     * @param aplyid
     * @return list
     */
    @Override
    public List<CntstAplySchlVO> selectCntstAplySchlList(Integer aplyid) {
        return cntstRcptHistDao.selectCntstAplySchlList(aplyid);
    }
}
