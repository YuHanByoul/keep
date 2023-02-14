package com.kbrainc.plum.front.enveduCntr.service;

import com.kbrainc.plum.front.enveduCntr.model.EnveduCntrDao;
import com.kbrainc.plum.front.enveduCntr.model.EnveduCntrVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.enveduCntr.service
 * - EnveduCntrServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnveduCntrServiceImpl
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Service
public class EnveduCntrServiceImpl extends PlumAbstractServiceImpl implements EnveduCntrService{

    @Autowired
    private EnveduCntrDao enveduCntrDao;

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnveduCntrList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param enveduCntrVO
     * @return list
     */
    @Override
    public List<EnveduCntrVO> selectEnveduCntrList(EnveduCntrVO enveduCntrVO) {
        return enveduCntrDao.selectEnveduCntrList(enveduCntrVO);
    }
}
