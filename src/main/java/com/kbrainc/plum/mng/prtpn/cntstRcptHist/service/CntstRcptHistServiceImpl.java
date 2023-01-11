package com.kbrainc.plum.mng.prtpn.cntstRcptHist.service;

import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstRcptHistVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
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
    @Override
    public List<CntstRcptHistVO> selectCntstRcptHistList(CntstRcptHistVO cntstRcptHistVO) {
        return null;
    }

    @Override
    public CntstRcptHistVO selectCntstRcptHistInfo(Integer aplyid) {
        return null;
    }
}
