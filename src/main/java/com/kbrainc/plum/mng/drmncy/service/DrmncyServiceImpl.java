package com.kbrainc.plum.mng.drmncy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.drmncy.model.DrmncyDao;
import com.kbrainc.plum.mng.drmncy.model.DrmncyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 휴면사용자관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.drmncy.service
 * - DrmncyServiceImpl.java
 * </pre> 
 *
 * @ClassName : DrmncyServiceImpl
 * @Description : 휴면사용자관리 서비스 구현 클래스.
 * @author : 이한명
 * @date : 2023. 3. 21.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class DrmncyServiceImpl extends PlumAbstractServiceImpl implements DrmncyService {

    @Autowired
    private DrmncyDao drmncyDao;

    /**
    * 휴면사용자정보 목록 조회.
    *
    * @Title       : selectDrmncyList 
    * @Description : 휴면사용자정보 목록 조회.
    * @param drmncyVo DrmncyVo객체
    * @return List<DrmncyVo> 휴면사용자정보 목록
    * @throws Exception 예외
    */
    @Override
    public List<DrmncyVo> selectDrmncyList(DrmncyVo drmncyVo) throws Exception {
        return drmncyDao.selectDrmncyList(drmncyVo);
    }

    
}