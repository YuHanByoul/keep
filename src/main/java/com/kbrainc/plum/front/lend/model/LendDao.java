package com.kbrainc.plum.front.lend.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 *  교구 대여 DAO 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.lend.model
 * - LendDao.java
 * </pre> 
 *
 * @ClassName : LendDao
 * @Description : 교구 대여 (사용자) DAO
 * @author : KBRAINC
 * @date : 2021.03.03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.lendDao")
public interface LendDao {
    
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림리스트(5개) 호출  
     * @param NtcnVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    //public List<NtcnVo> selectMainNtcnList(NtcnVo NtcnVo) throws Exception;
    

}