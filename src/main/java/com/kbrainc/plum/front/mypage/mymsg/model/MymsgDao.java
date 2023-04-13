/**
 * 
 */
package com.kbrainc.plum.front.mypage.mymsg.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 쪽지함 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.mymsg.model
* - MymsgDao.java
* </pre>
*
* @ClassName : MymsgDao
* @Description : 쪽지함 Dao 클래스
* @author : 이한명
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.mymsgDao")
public interface MymsgDao {
    
    /**
     * 받은쪽지함 조회
     *
     * @Title : selectRecvMsgList
     * @Description : 쪽지함 조회
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return List<MymsgVo>
     */
    public List<MymsgVo> selectRecvMsgList(MymsgVo mymsgVo) throws Exception;        

    /**
     * 받은쪽지함 정보 조회
     *
     * @Title : selectRecvMsgInfo
     * @Description : 받은쪽지함 정보 조회
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return MymsgVo
     */
    public MymsgVo selectRecvMsgInfo(MymsgVo mymsgVo) throws Exception;        

    /**
    * 받은쪽지함 답장 보내기
    **
    * @Title : insertSendMsg
    * @Description : 받은쪽지함 답장 보내기
    * @param mymsgVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertSendMsg(MymsgVo mymsgVo) throws Exception;

    /**
     * 받은쪽지함 삭제상태 수정
     **
     * @Title : updateUserDelMsg
     * @Description : 받은쪽지함 삭제상태 수정
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateUserDelMsg(MymsgVo mymsgVo) throws Exception;

    /**
     * 보낸쪽지함 조회
     *
     * @Title : selectSendMsgList
     * @Description : 보낸쪽지함 조회
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return List<MymsgVo>
     */
    public List<MymsgVo> selectSendMsgList(MymsgVo mymsgVo) throws Exception;        
    
    /**
     * 보낸쪽지함 정보 조회
     *
     * @Title : selectSendMsgInfo
     * @Description : 보낸쪽지함 정보 조회
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return MymsgVo
     */
    public MymsgVo selectSendMsgInfo(MymsgVo mymsgVo) throws Exception;        
    
    /**
     * 보낸쪽지함 삭제상태 수정
     **
     * @Title : updateTrgtDelMsg
     * @Description : 보낸쪽지함 삭제상태 수정
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateTrgtDelMsg(MymsgVo mymsgVo) throws Exception;
    
    /**
     * 받은쪽지함 조회상태 수정
     **
     * @Title : updateInqMsg
     * @Description : 받은쪽지함 조회상태 수정
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateInqMsg(MymsgVo mymsgVo) throws Exception;    
}
