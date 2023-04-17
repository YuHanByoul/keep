/**
 * 
 */
package com.kbrainc.plum.front.mypage.ntcnHist.service;

import java.util.List;

import com.kbrainc.plum.front.mypage.ntcnHist.model.NtcnHistVo;

/**
* 알림내역 서비스 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.ntcnHist.service
* - NtcnHistService.java
* </pre>
*
* @ClassName : NtcnHistService
* @Description : 알림내역 서비스 클래스
* @author : 이한명
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface NtcnHistService {

    /**
     * 알림내역 조회
     *
     * @Title : selectNtcnHistList
     * @Description : 알림내역 조회
     * @param ntcnHistVo
     * @return
     * @throws Exception
     * @return List<NtcnHistVo>
     */
    public List<NtcnHistVo> selectNtcnHistList(NtcnHistVo ntcnHistVo) throws Exception;    

    /**
    * 알림내역 삭제
    **
    * @Title : updateDeleteNtcn
    * @Description : 알림내역 삭제
    * @param ntcnHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateDeleteNtcn(NtcnHistVo ntcnHistVo) throws Exception;

    /**
     * 알림내역 조회여부 변경
     **
     * @Title : updateInqMsg
     * @Description : 알림내역 조회여부 변경
     * @param ntcnHistVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateInqMsg(NtcnHistVo ntcnHistVo) throws Exception;
}
