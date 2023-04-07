/**
 * 
 */
package com.kbrainc.plum.front.mypage.infntAplyHist.service;

import java.util.List;

import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;

/**
* 유아환경교육관 교육신청 서비스 클래스
*
* <pre>
* com.kbrainc.plum.front.infntAplyHist.service
* - InfntAplyHistService.java
* </pre>
*
* @ClassName : InfntAplyHistService
* @Description : 유아환경교육관 교육신청 서비스 클래스
* @author : 이한명
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InfntAplyHistService {

    /**
     * 유아환경교육관 신청이력 조회
     *
     * @Title : selectInfntAplyHistList
     * @Description : 유아환경교육관 신청이력 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return List<InfntAplyHistVo>
     */
    public List<InfntAplyHistVo> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo) throws Exception;    

    /**
     * 유아환경교육관 신청이력 정보 조회
     *
     * @Title : selectInfntAplyHistInfo
     * @Description : 유아환경교육관 신청이력 정보 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return InfntAplyHistVo
     */
    public InfntAplyHistVo selectInfntAplyHistInfo(InfntAplyHistVo infntAplyHistVo) throws Exception;        

    /**
     * 유아환경교육관 신청이력 상세 조회
     *
     * @Title : selectInfntAplyHistDetail
     * @Description : 유아환경교육관 신청이력 조회
     * @param infntAplyHistVo
     * @return
     * @throws Exception
     * @return InfntAplyHistVo
     */
    public InfntAplyHistVo selectInfntAplyHistDetail(InfntAplyHistVo infntAplyHistVo) throws Exception;   
    
    
    /**
    * 유아환경교육관 신청취소
    **
    * @Title : updateCancelInfntAply
    * @Description : 유아환경교육관 신청취소
    * @param infntAplyHistVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateCancelInfntAply(InfntAplyHistVo infntAplyHistVo) throws Exception;
    
    /**
     * 유아환경교육관 설문 정보 조회
     **
     * @Title : selectInfntSrvyInfo
     * @Description : 유아환경교육관 설문 정보 조회
     * @param srvyVo
     * @return
     * @throws Exception
     * @return SrvyVo
     */
    public SrvyVo selectInfntSrvyInfo(SrvyVo srvyVo) throws Exception;
    
    /**
     * 유아환경교육관 설문 공유 리스트 조회
     *
     * @Title : selectInfntSrvySendList
     * @Description : 유아환경교육관 설문 공유 리스트 조회
     * @param srvyVo
     * @return
     * @throws Exception
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectInfntSrvySendList(SrvyVo srvyVo) throws Exception;    
    
}
