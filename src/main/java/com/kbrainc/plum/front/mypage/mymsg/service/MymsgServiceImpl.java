/**
 * 
 */
package com.kbrainc.plum.front.mypage.mymsg.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.mypage.mymsg.model.MymsgDao;
import com.kbrainc.plum.front.mypage.mymsg.model.MymsgVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 쪽지함 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.mymsg.service
* - MymsgServiceImpl.java
* </pre>
*
* @ClassName : MymsgServiceImpl
* @Description : 쪽지함 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.mymsgServiceImpl")
@Alias("front.mymsgServiceImpl")
public class MymsgServiceImpl extends PlumAbstractServiceImpl implements MymsgService {

    @Resource(name = "front.mymsgDao")
    private MymsgDao mymsgDao;
    
    /**
     * 받은쪽지함 조회
     *
     * @Title : selectRecvMsgList
     * @Description : 받은쪽지함 조회
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return List<MymsgVo>
     */
    @Override
    public List<MymsgVo> selectRecvMsgList(MymsgVo mymsgVo) throws Exception{
        return mymsgDao.selectRecvMsgList(mymsgVo);
    }        
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
    @Override
    public MymsgVo selectRecvMsgInfo(MymsgVo mymsgVo) throws Exception{
        return mymsgDao.selectRecvMsgInfo(mymsgVo);
    }

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
    @Override
    @Transactional    
    public int insertSendMsg(MymsgVo mymsgVo) throws Exception{
        int retVal = 0;
        retVal += mymsgDao.insertSendMsg(mymsgVo);
        return retVal;
    }
    
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
    @Override
    @Transactional    
    public int updateUserDelMsg(MymsgVo mymsgVo) throws Exception{
        int retVal = 0;
        retVal += mymsgDao.updateUserDelMsg(mymsgVo);
        return retVal;
    }

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
    @Override
    public List<MymsgVo> selectSendMsgList(MymsgVo mymsgVo) throws Exception{
        return mymsgDao.selectSendMsgList(mymsgVo);
    }        
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
    @Override
    public MymsgVo selectSendMsgInfo(MymsgVo mymsgVo) throws Exception{
        return mymsgDao.selectSendMsgInfo(mymsgVo);
    }
    
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
    @Override
    @Transactional    
    public int updateTrgtDelMsg(MymsgVo mymsgVo) throws Exception{
        int retVal = 0;
        retVal += mymsgDao.updateTrgtDelMsg(mymsgVo);
        return retVal;
    }

    /**
     * 보낸쪽지함 조회상태 수정
     **
     * @Title : updateInqMsg
     * @Description : 보낸쪽지함 조회상태 수정
     * @param mymsgVo
     * @return
     * @throws Exception
     * @return int
     */
    @Override
    @Transactional    
    public int updateInqMsg(MymsgVo mymsgVo) throws Exception{
        int retVal = 0;
        retVal += mymsgDao.updateInqMsg(mymsgVo);
        return retVal;
    }
}
