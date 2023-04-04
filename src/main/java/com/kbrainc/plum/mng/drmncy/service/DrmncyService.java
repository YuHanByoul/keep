package com.kbrainc.plum.mng.drmncy.service;

import java.util.List;

import com.kbrainc.plum.mng.drmncy.model.DrmncyVo;

/**
 * 
 * 사용자관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.drmncy.service
 * - DrmncyService.java
 * </pre> 
 *
 * @ClassName : DrmncyService
 * @Description : 사용자관리 서비스 인터페이스.
 * @author : 이한명
 * @date : 2023. 3. 21.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface DrmncyService {

    /**
    * 사용자정보 목록 조회.
    *
    * @Title       : selectDrmncyList 
    * @Description : 사용자정보 목록 조회.
    * @param drmncyVo DrmncyVo객체
    * @return List<DrmncyVo> 사용자정보 목록
    * @throws Exception 예외
    */
    public List<DrmncyVo> selectDrmncyList(DrmncyVo drmncyVo) throws Exception;
    
    
}