package com.kbrainc.plum.front.msg.service;

import com.kbrainc.plum.front.msg.model.MsgVo;

/**
 * 쪽지 Serivce
 *
 * <pre>
 * com.kbrainc.plum.front.msg.service
 * - MsgService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MsgService
 * @Description : 쪽지 Serivce
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface MsgService {

    /**
     * 쪽지 발송
     * Title : insertMsg
     * Description : 쪽지 발송
     *
     * @param msgVo
     * @return boolean
     */
    boolean insertMsg(MsgVo msgVo);

    /**
     * 쪽지 발송 대상자 정보 조회
     * Title : selectTrgtInfo
     * Description : 쪽지 발송 대상자 정보 조회
     *
     * @param trgtId
     * @return msg vo
     */
    MsgVo selectTrgtInfo(String trgtId);
}
