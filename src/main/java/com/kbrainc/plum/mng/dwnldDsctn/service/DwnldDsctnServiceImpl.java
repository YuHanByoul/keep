package com.kbrainc.plum.mng.dwnldDsctn.service;

import com.kbrainc.plum.mng.dwnldDsctn.model.DwnldDsctnDao;
import com.kbrainc.plum.mng.dwnldDsctn.model.DwnldDsctnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 다운로드 사유 등록 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.service
 * - DwnldDsctnServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DwnldDsctnServiceImpl
 * @Description : 다운로드 사유 등록 서비스 구현 클래스
 * @date : 2023. 04. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class DwnldDsctnServiceImpl implements DwnldDsctnService {

    @Autowired
    private DwnldDsctnDao dwnldDsctnDao;

    /**
     * 다운로드 사유 등록
     *
     * @param dwnldDsctnVo
     * @return int
     * @throws Exception
     * @Title : insertDwnldDsctn
     * @Description : 다운로드 사유 등록
     */
    @Override
    @Transactional
    public int insertDwnldDsctn(DwnldDsctnVo dwnldDsctnVo) throws Exception {
        return dwnldDsctnDao.insertDwnldDsctn(dwnldDsctnVo);
    }
}
