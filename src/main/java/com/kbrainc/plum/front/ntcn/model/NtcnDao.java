package com.kbrainc.plum.front.ntcn.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 알림 사용자 DAO 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.model
 * - NtcnDao.java
 * </pre> 
 *
 * @ClassName : NtcnDao
 * @Description : 알림 사용자 DAO
 * @author : KBRAINC
 * @date : 2021. 2. 27.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.ntcnDao")
public interface NtcnDao {
    
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림리스트(5개) 호출 
     * @param NtcnVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public List<NtcnVo> selectMainNtcnList(NtcnVo ntcnVo) throws Exception;
    

}