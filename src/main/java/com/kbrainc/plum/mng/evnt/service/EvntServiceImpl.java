package com.kbrainc.plum.mng.evnt.service;

import com.kbrainc.plum.mng.evnt.model.EvntDao;
import com.kbrainc.plum.mng.evnt.model.EvntVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 참여신청 관리 > 이벤트 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.evnt.service
 * - EvntServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntServiceImpl
 * @Description : 참여신청 관리 > 이벤트 서비스 구현 클래스
 * @date : 2023. 01. 26.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
@RequiredArgsConstructor
public class EvntServiceImpl extends PlumAbstractServiceImpl implements EvntService {

    private final EvntDao evntDao;

    /**
     * 이벤트 목록 조회
     *
     * @param evntVo
     * @return list
     * @throws Exception
     * @Title : selectEvntList
     * @Description : 이벤트 목록 조회
     */
    @Override
    public List<EvntVo> selectEvntList(EvntVo evntVo) throws Exception {
        return evntDao.selectEvntList(evntVo);
    }

    /**
     * 이벤트 상세 조회
     *
     * @param evntVo
     * @return evnt vo
     * @throws Exception
     * @Title : selectEvntInfo
     * @Description : 이벤트 상세 조회
     */
    @Override
    public EvntVo selectEvntInfo(EvntVo evntVo) throws Exception {
        return evntDao.selectEvntInfo(evntVo);
    }

    /**
     * 이벤트 등록
     *
     * @param evntVo
     * @return int
     * @throws Exception
     * @Title : insertEvnt
     * @Description : 이벤트 등록
     */
    @Override
    public int insertEvnt(EvntVo evntVo) throws Exception {
        int retVal = 0;
        retVal += evntDao.insertEvnt(evntVo);

        return retVal;
    }

    /**
     * 이벤트 수정
     *
     * @param evntVo
     * @return int
     * @throws Exception
     * @Title : updateEvnt
     * @Description : 이벤트 수정
     */
    @Override
    public int updateEvnt(EvntVo evntVo) throws Exception {
        int retVal = 0;
        retVal += evntDao.updateEvnt(evntVo);

        return retVal;
    }
}
