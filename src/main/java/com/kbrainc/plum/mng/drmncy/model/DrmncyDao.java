package com.kbrainc.plum.mng.drmncy.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 사용자관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.drmncy.model
 * - DrmncyDao.java
 * </pre> 
 *
 * @ClassName : DrmncyDao
 * @Description : 사용자관리 DAO 클래스. 
 * @author : 이한명
 * @date : 2023. 3. 21.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface DrmncyDao {

    /**
    * 휴면사용자정보 목록 조회.
    *
    * @Title       : selectDrmncyList 
    * @Description : 휴면사용자정보 목록 조회.
    * @param param DrmncyVo객체
    * @return List<DrmncyVo> 휴면사용자정보 목록
    * @throws Exception 예외
    */
    public List<DrmncyVo> selectDrmncyList(DrmncyVo drmncyVo) throws Exception;
   
}