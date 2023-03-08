/**
 * 
 */
package com.kbrainc.plum.front.mypage.ntcnHist.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 알림 내역 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.ntcnHist.model
* - NtcnHistDao.java
* </pre>
*
* @ClassName : NtcnHistDao
* @Description : 알림 내역 Dao 클래스
* @author : 이한명
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.ntcnHistDao")
public interface NtcnHistDao {
    
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
    public List<NtcnHistVo> selectNtcnHistList(NtcnHistVo ntcnHistVo) throws Exception;        

    /**
    * 알림 내역 취소
    **
    * @Title : updateDeleteNtcn
    * @Description : 알림 내역 취소
    * @param ntcnHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateDeleteNtcn(NtcnHistVo ntcnHistVo) throws Exception;
}
