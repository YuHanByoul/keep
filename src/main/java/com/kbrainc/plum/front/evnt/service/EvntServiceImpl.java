package com.kbrainc.plum.front.evnt.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.evnt.model.EvntDao;
import com.kbrainc.plum.front.evnt.model.EvntVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 참여신청 관리 > 이벤트 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.evnt.service
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
@Service("front.evntServiceImpl")
@Alias("front.evntServiceImpl")
public class EvntServiceImpl extends PlumAbstractServiceImpl implements EvntService {
    
    @Resource(name = "front.evntDao")
    private EvntDao evntDao;

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
    
    public EvntVo selectEvntInfo(EvntVo evntVo) throws Exception {
        return evntDao.selectEvntInfo(evntVo);
    }
    
    public int updateEvntHits(EvntVo evntVo) throws Exception {
        return evntDao.updateEvntHits(evntVo);
    };
    
    public List<EvntVo> selectEvntFileList(EvntVo evntVo) throws Exception {
        return evntDao.selectEvntFileList(evntVo);
    };
}
