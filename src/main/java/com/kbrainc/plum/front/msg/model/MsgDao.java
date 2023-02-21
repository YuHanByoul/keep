package com.kbrainc.plum.front.msg.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 쪽지 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.msg.model
 * - MsgDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MsgDao
 * @Description : 쪽지 Dao
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Mapper
public interface MsgDao {

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
