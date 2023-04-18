/**
 * 
 */
package com.kbrainc.plum.front.mypage.ntcnHist.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.mypage.ntcnHist.model.NtcnHistDao;
import com.kbrainc.plum.front.mypage.ntcnHist.model.NtcnHistVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 알림 내역 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.ntcnHist.service
* - NtcnHistServiceImpl.java
* </pre>
*
* @ClassName : NtcnHistServiceImpl
* @Description : 알림 내역 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.ntcnHistServiceImpl")
@Alias("front.ntcnHistServiceImpl")
public class NtcnHistServiceImpl extends PlumAbstractServiceImpl implements NtcnHistService {

    @Resource(name = "front.ntcnHistDao")
    private NtcnHistDao ntcnHistDao;
    
    /**
     * 알림 내역 조회
     *
     * @Title : selectNtcnHistList
     * @Description : 알림 내역 조회
     * @param ntcnHistVo
     * @return
     * @throws Exception
     * @return List<NtcnHistVo>
     */
    @Override
    public List<NtcnHistVo> selectNtcnHistList(NtcnHistVo ntcnHistVo) throws Exception{
        return ntcnHistDao.selectNtcnHistList(ntcnHistVo);
    }        
    
    /**
    * 알림 내역 삭제
    **
    * @Title : updateDeleteNtcn
    * @Description : 알림 내역 삭제
    * @param ntcnHistVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional    
    public int updateDeleteNtcn(NtcnHistVo ntcnHistVo) throws Exception{
        int retVal = 0;
        retVal += ntcnHistDao.updateDeleteNtcn(ntcnHistVo);
        return retVal;
    }

    /**
     * 알림 내역 조회여부 변경
     **
     * @Title : updateInqMsg
     * @Description : 알림 내역 조회여부 변경
     * @param ntcnHistVo
     * @return
     * @throws Exception
     * @return int
     */
    @Override
    @Transactional    
    public int updateInqMsg(NtcnHistVo ntcnHistVo) throws Exception{
        int retVal = 0;
        retVal += ntcnHistDao.updateInqMsg(ntcnHistVo);
        return retVal;
    }
}
