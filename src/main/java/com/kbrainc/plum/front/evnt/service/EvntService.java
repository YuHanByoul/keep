package com.kbrainc.plum.front.evnt.service;

import com.kbrainc.plum.front.evnt.model.EvntVo;

import java.util.List;

/**
 * 참여신청 관리 > 이벤트 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.evnt.service
 * - EvntService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntService
 * @Description : 참여신청 관리 > 이벤트 서비스 인터페이스
 * @date : 2023. 01. 26.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface EvntService {

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
