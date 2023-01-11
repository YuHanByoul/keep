package com.kbrainc.plum.mng.prtpn.cntst.service;

import com.kbrainc.plum.mng.prtpn.cntst.model.CntstVO;
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
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
        return null;
    }

    /**
     * 공모전 등록
     * Title : insertCntst
     * Description : 공모전 등록
     *
     * @param cntstVO
     */
    @Override
    @Transactional
    public void insertCntst(CntstVO cntstVO) {
    }

    /**
     * 공모전 수정
     * Title : updateCntst
     * Description : 공모전 수정
     *
     * @param cntstVO
     */
    @Override
    @Transactional
    public void updateCntst(CntstVO cntstVO) {
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
        return null;
    }
}
