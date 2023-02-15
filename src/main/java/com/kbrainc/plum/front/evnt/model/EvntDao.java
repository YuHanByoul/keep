package com.kbrainc.plum.front.evnt.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 참여신청 관리 > 이벤트 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.evnt.model
 * - EvntDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntDao
 * @Description : 참여신청 관리 > 이벤트 Dao 클래스
 * @date : 2023. 01. 26.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.evntDao")
public interface EvntDao {

    /**
     * 이벤트 목록 조회
     *
     * @param evntVo
     * @return list
     * @throws Exception
     * @Title : selectEvntList
     * @Description : 이벤트 목록 조회
     */
    public List<EvntVo> selectEvntList(EvntVo evntVo) throws Exception;
    
    public EvntVo selectEvntInfo(EvntVo evntVo) throws Exception;

    public int updateEvntHits(EvntVo evntVo) throws Exception;
    
    public List<EvntVo> selectEvntFileList(EvntVo evntVo) throws Exception;
}
