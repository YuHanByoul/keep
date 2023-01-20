package com.kbrainc.plum.mng.prtpn.cntst.service;

import com.kbrainc.plum.mng.prtpn.cntst.model.CntstDao;
import com.kbrainc.plum.mng.prtpn.cntst.model.CntstVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 공모전 등록 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.service
 * - CntstServiceImpl.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstServiceImpl
 * @Description : 공모전 등록 서비스 구현 클래스
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service
public class CntstServiceImpl extends PlumAbstractServiceImpl implements CntstService {

    @Autowired
    CntstDao cntstDao;

    /**
     * 공모전 등록 목록 조회
     * Title : selectCntstList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstVO
     * @return list
     */
    @Override
    public List<CntstVO> selectCntstList(CntstVO cntstVO) {
        return cntstDao.selectCntstList(cntstVO);
    }

    /**
     * 공모전 등록
     * Title : insertCntst
     * Description : 공모전 등록
     *
     * @param cntstVO
     * @param cntstFldCdArr
     */
    @Override
    @Transactional
    public void insertCntst(CntstVO cntstVO, String[] cntstFldCdArr) {
        cntstDao.insertCntst(cntstVO);
        cntstDao.insertCntstFldMapng(cntstVO.getCntstid(), cntstFldCdArr, cntstVO.getUser());
    }

    /**
     * 공모전 등록
     * Title : insertCntst
     * Description : 공모전 등록
     *
     * @param cntstVO
     * @param cntstFldCdArr
     */
    @Override
    @Transactional
    public void updateCntst(CntstVO cntstVO, String[] cntstFldCdArr) {
        cntstDao.updateCntst(cntstVO);
        cntstDao.deleteCntstFldMapng(cntstVO.getCntstid());
        cntstDao.insertCntstFldMapng(cntstVO.getCntstid(), cntstFldCdArr, cntstVO.getUser());
    }

    /**
     * 공모전 조회
     * Title : selectCntst
     * Description : 공모전 조회
     *
     * @param cntstId
     * @return CntstVO
     */
    @Override
    public CntstVO selectCntstInfo(Integer cntstId) {
        return cntstDao.selectCntstInfo(cntstId);
    }


    /**
     * 공모전 조회
     * Title : selectCntstFldCdList
     * Description : 공모전 분야 조회
     *
     * @param cntstId
     * @return CntstVO
     */
    @Override
    public List<String> selectCntstFldCdList(Integer cntstId) {
        return cntstDao.selectCntstFldCdList(cntstId);
    }
}
