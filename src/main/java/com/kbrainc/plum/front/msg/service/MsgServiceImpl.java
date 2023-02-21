package com.kbrainc.plum.front.msg.service;

import com.kbrainc.plum.front.msg.model.MsgDao;
import com.kbrainc.plum.front.msg.model.MsgVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 쪽지 ServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.front.msg.service
 * - MsgServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MsgServiceImpl
 * @Description : 쪽지 ServiceImpl
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service
public class MsgServiceImpl extends PlumAbstractServiceImpl implements MsgService {
    @Autowired
    private MsgDao msgDao;

    /**
     * 쪽지 발송
     * Title : insertMsg
     * Description : 쪽지 발송
     *
     * @param msgVo
     * @return boolean
     */
    @Override
    public boolean insertMsg(MsgVo msgVo) {
        boolean result = false;
        if(msgDao.insertMsg(msgVo)) result = true;
        return result;
    }

    /**
     * 쪽지 발송 대상자 정보 조회
     * Title : selectTrgtInfo
     * Description : 쪽지 발송 대상자 정보 조회
     *
     * @param trgtId
     * @return msg vo
     */
    @Override
    public MsgVo selectTrgtInfo(String trgtId) {
        return msgDao.selectTrgtInfo(trgtId);
    }
}
