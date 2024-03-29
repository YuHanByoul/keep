package com.kbrainc.plum.mng.evnt.service;

import com.kbrainc.plum.mng.evnt.model.EvntVo;

import java.util.List;

/**
 * 참여신청 관리 > 이벤트 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.evnt.service
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

    /**
     * 이벤트 상세 조회
     *
     * @param evntVo
     * @return evnt vo
     * @throws Exception
     * @Title : selectEvntInfo
     * @Description : 이벤트 상세 조회
     */
    public EvntVo selectEvntInfo(EvntVo evntVo) throws Exception;

    /**
     * 이벤트 등록
     *
     * @param evntVo
     * @return int
     * @throws Exception
     * @Title : insertEvnt
     * @Description : 이벤트 등록
     */
    public int insertEvnt(EvntVo evntVo) throws Exception;

    /**
     * 이벤트 수정
     *
     * @param evntVo
     * @return int
     * @throws Exception
     * @Title : updateEvnt
     * @Description : 이벤트 수정
     */
    public int updateEvnt(EvntVo evntVo) throws Exception;
}
